package org.gfoo.textlearnapi.controller;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.gfoo.textlearnapi.service.LearnMessageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CustomGlobalExceptionHandler
    extends ResponseEntityExceptionHandler {

	// error handle for @Valid
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
	    MethodArgumentNotValidException ex, HttpHeaders headers,
	    HttpStatus status, WebRequest request) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
		    .map(x -> x.getDefaultMessage()).collect(Collectors.toList());
		return new ResponseEntity<>(
		    new ApiError(errors, Instant.now(), HttpStatus.BAD_REQUEST), headers,
		    HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ LearnMessageException.class })
	public ResponseEntity<Object> handleLearnMessageException(RuntimeException ex,
	    HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error(ex.toString(), ex);
		return new ResponseEntity<>(ex.toString(), headers,
		    HttpStatus.SERVICE_UNAVAILABLE);
	}
}