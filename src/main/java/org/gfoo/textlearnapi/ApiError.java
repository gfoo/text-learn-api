package org.gfoo.textlearnapi;

import java.util.Date;
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
	private Date timestamp;
	private HttpStatus status;
}
