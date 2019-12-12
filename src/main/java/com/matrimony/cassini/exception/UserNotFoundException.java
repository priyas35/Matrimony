package com.matrimony.cassini.exception;

public class UserNotFoundException extends RuntimeException {
	/**
	 * default serialVersionUID
	 */
	private static final long serialVersionUID = -6782010313316599660L;

	/**
	 * @param arg0
	 */
	public UserNotFoundException(String arg0) {
		super(arg0);
	}
}
