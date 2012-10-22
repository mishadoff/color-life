package com.mindbar.life.exception;

public class PopulationIsDeadException extends RuntimeException {
	
	private static final long serialVersionUID = 2021193029843781469L;

	public PopulationIsDeadException(String message) {
		super(message);
	}
}
