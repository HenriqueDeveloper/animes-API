package br.com.henrique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.henrique.entities.UserAuthenticate;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserAuthenticate, Integer> {

    Optional<UserAuthenticate> findByLogin(String login);
}
