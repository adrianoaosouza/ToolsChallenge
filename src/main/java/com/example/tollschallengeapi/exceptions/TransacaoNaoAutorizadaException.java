package com.example.tollschallengeapi.exceptions;

@SuppressWarnings("serial")
public class TransacaoNaoAutorizadaException extends RuntimeException {
	public TransacaoNaoAutorizadaException(String message) {
		super(message);
	}

}
