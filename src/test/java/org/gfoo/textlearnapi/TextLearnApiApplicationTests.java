package org.gfoo.textlearnapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TextLearnApiApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private LearnMessageService learnMessageSrv;

	@Test
	public void unknownRoute() throws Exception {
		this.mvc.perform(get("/")).andExpect(status().isNotFound());
	}

	@Test
	public void unknownMethodLearn() throws Exception {
		this.mvc.perform(get("/learn")).andExpect(status().isMethodNotAllowed());
	}

	@Test
	public void emptyLearn() throws Exception {
		this.mvc.perform(post("/learn")).andExpect(status().isBadRequest())
		    .andExpect(content().string(""));
	}

	@Test
	public void incompleteLearn() throws Exception {
		Instant date = Instant.now();
		ResultActions resultActions = this.mvc
		    .perform(post("/learn").contentType(MediaType.APPLICATION_JSON)
		        .content("{ \"topic\": \"mytopic\" }"))
		    .andExpect(status().isBadRequest());

		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		ApiError response = objectMapper.readValue(contentAsString, ApiError.class);
//		assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST);
//		assertEquals(response.getErrors().size(), 2);
//		assertEquals(new HashSet<String>(response.getErrors()),
//		    new HashSet<>(Arrays.asList(new String[] { "Please provide a 'text'",
//		        "Please provide a 'method'" })));
		assertThat(response.getTimestamp().compareTo(date) > 0);
		assertThat(response.getTimestamp().compareTo(Instant.now()) < 0);
	}

	@Test
	public void completeLearn() throws Exception {

		Instant date = Instant.now();
		ResultActions resultActions = this.mvc
		    .perform(post("/learn").contentType(MediaType.APPLICATION_JSON).content(
		        "{ \"topic\": \"mytopic\", \"method\": \"mymethod\", \"text\": \"mytext\" }"))
		    .andExpect(status().isCreated());

		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		LearningForm response = objectMapper.readValue(contentAsString,
		    LearningForm.class);
		assertEquals(response.getTopic(), "mytopic");
		assertEquals(response.getMethod(), "mymethod");
		assertEquals(response.getText(), "mytext");
		assertThat(response.getTimestamp().compareTo(date) > 0);
		assertThat(response.getTimestamp().compareTo(Instant.now()) < 0);
	}
}
