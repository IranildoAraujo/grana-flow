package com.granaflow.exception;

public class DataInvalidaException extends RuntimeException {

	private static final long serialVersionUID = 568914110540343052L;

	public DataInvalidaException(String mensagem, Throwable throwable) {
		super(mensagem, throwable);
	}

	public DataInvalidaException(String mensagem) {
		super(mensagem);
	}

}
