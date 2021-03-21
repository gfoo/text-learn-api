package org.gfoo.textlearnapi.model;

import java.time.Instant;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class LearningForm {

	/**
	 * Text content to learn
	 */
	@NotEmpty(message = "Please provide a 'text'")
	private String text;

	/**
	 * Topic name of the learning
	 */
	@NotEmpty(message = "Please provide a 'topic'")
	private String topic;

	/**
	 * Method name of the learning
	 */
	@NotEmpty(message = "Please provide a 'method'")
	private String method;

	/**
	 * Learning call date
	 */
	@Setter
	private Instant timestamp;

}
