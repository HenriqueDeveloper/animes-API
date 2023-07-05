package br.com.henrique.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.henrique.exceptions.AnimeNotCompatible;
import br.com.henrique.exceptions.AnimeNotFoundException;
import br.com.henrique.exceptions.AnimeTypeNotCompatible;
import br.com.henrique.exceptions.ExceptionResponse;
import br.com.henrique.exceptions.NumberException;

import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerCustomized extends ResponseEntityExceptionHandler {

	@ExceptionHandler(AnimeNotFoundException.class)
	public ResponseEntity<ExceptionResponse> responseBodyExceptionAnime(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(NumberException.class)
	public ResponseEntity<ExceptionResponse> responseBodyExceptionNumber(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(AnimeTypeNotCompatible.class)
	public ResponseEntity<ExceptionResponse> responseBodyExceptionTypeOrder(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false),HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);

	}
	
	
	@ExceptionHandler(AnimeNotCompatible.class)
	public ResponseEntity<ExceptionResponse> responseBodyExceptionTipe(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> responseBodyExceptionGlobal(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
