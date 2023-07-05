package br.com.henrique.exceptions;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
	
	private Date date;
	private String response;
	private String details;
	private long status;
	
	
	
	
	

}
