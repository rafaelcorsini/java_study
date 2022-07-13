package io.github.rafaelcorsini.study.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.rafaelcorsini.study.domain.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Optional<Usuario> findByLogin(String login);
}
