package io.github.rafaelcorsini.study.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.rafaelcorsini.study.domain.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	@Query(value = "select c from Cliente c where c.nome like :nome")
	List<Cliente> encontrarClientePorNome(@Param("nome") String nome);
	
	@Query(value= "delete from Cliente c where c.nome = :nome")
	@Modifying
	void deleteByNome(@Param("nome") String nome);
	
	boolean existsByNome(String nome);
	
	@Query("select c from Cliente c left join fetch c.pedidos where c.id = :id")
	Cliente findClienteFetchPedidos(@Param("id") Integer id);
}
