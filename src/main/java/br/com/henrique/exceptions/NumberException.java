package br.com.henrique.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NumberException extends RuntimeException {
	
private static final long serialVersionUID = 1L;
	
	
	public NumberException(String erro) {
		super(erro);
	}

}
