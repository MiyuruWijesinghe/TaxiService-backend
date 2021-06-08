package com.af.taxi_service.exception;

public class UserNotFound extends RuntimeException {
	public UserNotFound(String exception) {
		super(exception);
	}
}
