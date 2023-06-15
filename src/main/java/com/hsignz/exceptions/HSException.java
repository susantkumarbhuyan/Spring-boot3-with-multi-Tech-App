package com.hsignz.exceptions;

public class HSException extends Exception {

	private static final long serialVersionUID = -3965140316789517795L;

	public HSException() {

	}

	public HSException(String message) {
		super(message);
	}

	public HSException(Throwable e) {
		super(e);
	}

	public HSException(String message, Throwable e) {
		super(message, e);
	}

}
