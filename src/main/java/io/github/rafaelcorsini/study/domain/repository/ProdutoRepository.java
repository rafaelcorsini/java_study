package io.github.rafaelcorsini.study.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.rafaelcorsini.study.domain.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
