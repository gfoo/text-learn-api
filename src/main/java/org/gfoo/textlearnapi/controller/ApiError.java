package org.gfoo.textlearnapi.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ApiError {
	private List<String> errors;
	private Instant timestamp;
	private HttpStatus status;
}
