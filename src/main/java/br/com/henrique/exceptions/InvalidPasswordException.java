package br.com.henrique.exceptions;


public class InvalidPasswordException extends RuntimeException {
	
    private static final long serialVersionUID = 1L;

	public InvalidPasswordException(String erro){
        super(erro);
    }

}
