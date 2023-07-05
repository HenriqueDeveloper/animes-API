package br.com.henrique.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DataDTO {

	private String title;
	private String episodes;
	private String type;
	private String duration;
	private String score;
	private String rank;
	private String year;

}
