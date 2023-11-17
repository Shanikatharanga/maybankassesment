package com.springbatch.maybank.exception;

public class TransactionNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TransactionNotFoundException() {
        super();
    }

    public TransactionNotFoundException(String message) {
        super(message);
    }

    public TransactionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionNotFoundException(Throwable cause) {
        super(cause);
    }
}

