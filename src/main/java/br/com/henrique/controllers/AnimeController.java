package br.com.henrique.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.henrique.dtos.AnimeDTO;
import br.com.henrique.entities.QuerySave;
import br.com.henrique.exceptions.AnimeNotCompatible;
import br.com.henrique.exceptions.AnimeNotFoundException;
import br.com.henrique.exceptions.AnimeTypeNotCompatible;
import br.com.henrique.exceptions.NumberException;
import br.com.henrique.services.AnimeService;
import br.com.henrique.utils.AnimeUtil;
import br.com.henrique.utils.ConsumerApi;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/anime")
@Tag(name = "Anime")
public class AnimeController {

	@Autowired
	private AnimeService animeService;

	@GetMapping("/{name}")
	public ResponseEntity<AnimeDTO> getForName(@PathVariable("name") String name) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("q", name);
		ResponseEntity<AnimeDTO> resultado = ConsumerApi.response(params);
		if (!AnimeUtil.validar(resultado)) {
			throw new AnimeNotFoundException("Anime não encontrado!");
		}
		this.animeService.save(new QuerySave(name));
		return resultado;
	}
	
	@GetMapping("/page/{name}/{page}")
	public ResponseEntity<AnimeDTO> getForName(@PathVariable("name") String name, @PathVariable("page") String page) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("q", name);
		params.add("page", page);
		ResponseEntity<AnimeDTO> resultado = ConsumerApi.response(params);
		if (!AnimeUtil.validar(resultado)) {
			throw new AnimeNotFoundException("Anime não encontrado!");
		} else if (!AnimeUtil.isNumeric(page)) {
			throw new NumberException("Informe um número válido!");
		}
		this.animeService.save(new QuerySave(name));
		return resultado;
	}

	@GetMapping("/{name}/{type}")
	public ResponseEntity<AnimeDTO> getForNameAndType(@PathVariable("name") String name,
			@PathVariable("type") String type) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("q", name);
		params.add("type", type);
		ResponseEntity<AnimeDTO> resultado = ConsumerApi.response(params);
		if (!AnimeUtil.validar(resultado)) {
			throw new AnimeNotFoundException("Anime não encontrado!");
		} else if (!AnimeUtil.isCompatible(type)) {
			throw new AnimeNotCompatible("Tipo do anime não encontrado!");
		}
		this.animeService.save(new QuerySave(name));
		return resultado;
	}

	@GetMapping("/limit/{name}/{limit}")
	public ResponseEntity<AnimeDTO> getForNameAndLimit(@PathVariable("name") String name,
			@PathVariable("limit") String limit) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("q", name);
		params.add("limit", limit);
		ResponseEntity<AnimeDTO> resultado = ConsumerApi.response(params);
		if (!AnimeUtil.validar(resultado)) {
			throw new AnimeNotFoundException("Anime não encontrado!");
		} else if (!AnimeUtil.isNumeric(limit)) {
			throw new NumberException("Informe um número válido!");
		}
		this.animeService.save(new QuerySave(name));
		return resultado;

	}

	@GetMapping("/order/{name}/{orderBy}")
	public ResponseEntity<AnimeDTO> getByOrder(@PathVariable("name") String name,
			@PathVariable("orderBy") String orderBy) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("q", name);
		params.add("order_by", orderBy);
		ResponseEntity<AnimeDTO> resultado = ConsumerApi.response(params);
		if (!AnimeUtil.validar(resultado)) {
			throw new AnimeNotFoundException("Anime não encontrado!");
		} else if (!AnimeUtil.isTypeCompative(orderBy)) {
			throw new AnimeTypeNotCompatible("Tipo de ordenação inválida!");
		}
		this.animeService.save(new QuerySave(name));
		return resultado;
	}

}
