package br.com.henrique.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.henrique.dtos.AnimeDTO;


public class ConsumerApi {
	
	public static ResponseEntity<AnimeDTO> response(MultiValueMap<String, String> params) {
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("api.jikan.moe")
				.path("v4/anime")
				.queryParams(params)
				.build();
		RestTemplate rest = new RestTemplate();
		ResponseEntity<AnimeDTO> response = rest.getForEntity(uri.toUriString(), AnimeDTO.class);
		return response;
	}

}
