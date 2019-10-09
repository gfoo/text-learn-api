package org.gfoo.textlearnapi;

import java.util.Date;

import javax.validation.Valid;

import org.gfoo.textlearnapi.model.LearningForm;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LearnController {

	@PostMapping("/learn")
	@ResponseStatus(HttpStatus.CREATED)
	public LearningForm learn(@Valid @RequestBody LearningForm learningForm) {
		log.debug(learningForm.toString());
		learningForm.setDate(new Date());
		return learningForm;
	}
}
