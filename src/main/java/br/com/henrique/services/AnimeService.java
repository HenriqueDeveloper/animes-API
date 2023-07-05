package br.com.henrique.services;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.henrique.entities.QuerySave;
import br.com.henrique.repositories.AnimeRepository;

@Service
public class AnimeService {
	
	@Autowired
	private AnimeRepository animeRepository;
	
	public boolean save(QuerySave querySave) {
		if(querySave.getConsulta() != null) {
			querySave.setData(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
			this.animeRepository.save(querySave);
			return true;
		}else {
			return false;
		}
		
	}
}
