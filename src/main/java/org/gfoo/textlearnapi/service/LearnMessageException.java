package org.gfoo.textlearnapi.service;

@SuppressWarnings("serial")
public class LearnMessageException extends Exception {

	public LearnMessageException() {
	}

	public LearnMessageException(String message) {
		super(message);
	}

	public LearnMessageException(Throwable cause) {
		super(cause);
	}

	public LearnMessageException(String message, Throwable cause) {
		super(message, cause);
	}

	public LearnMessageException(String message, Throwable cause,
	    boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
