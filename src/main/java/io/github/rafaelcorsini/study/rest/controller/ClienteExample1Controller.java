package io.github.rafaelcorsini.study.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.rafaelcorsini.study.domain.entity.Cliente;
import io.github.rafaelcorsini.study.domain.repository.ClienteRepository;

@Controller
@RequestMapping("/api/clientesExample1")
public class ClienteExample1Controller {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping("{id}")
	public ResponseEntity<Object> getClienteById(@PathVariable Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);

		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Object> save(@RequestBody Cliente cliente) {
		Cliente clienteSalvo = clienteRepository.save(cliente);

		return ResponseEntity.ok(clienteSalvo);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);

		if (cliente.isPresent()) {
			clienteRepository.delete(cliente.get());
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Cliente cliente) {
		return clienteRepository.findById(id).map(clienteExistente -> {
			cliente.setId(clienteExistente.getId());
			clienteRepository.save(cliente);
			return ResponseEntity.noContent().build();
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping
	public ResponseEntity<Object> find(Cliente filtro) {
		
		ExampleMatcher matcher = ExampleMatcher
									.matching()
									.withIgnoreCase()
									.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		
		Example<Cliente> example = Example.of(filtro, matcher);
		List<Cliente> lista = clienteRepository.findAll(example);
		return ResponseEntity.ok(lista);
	}
	
}
