package br.com.henrique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.henrique.entities.QuerySave;


public interface AnimeRepository extends JpaRepository<QuerySave, Long> {

}
