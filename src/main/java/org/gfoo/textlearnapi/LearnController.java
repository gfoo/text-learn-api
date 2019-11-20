package org.gfoo.textlearnapi;

import java.time.Instant;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LearnController {

	@Autowired
	private LearnMessageService learnMessageSrv;

	@PostMapping("/learn")
	@ResponseStatus(HttpStatus.CREATED)
	public LearningForm learn(@Valid @RequestBody LearningForm learningForm) {
		log.debug(learningForm.toString());
		learningForm.setTimestamp(Instant.now());
		learnMessageSrv.sendLearnMessage(learningForm);
		return learningForm;
	}
}
