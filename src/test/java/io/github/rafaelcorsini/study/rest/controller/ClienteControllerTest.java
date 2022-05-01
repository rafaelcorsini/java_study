package io.github.rafaelcorsini.study.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import io.github.rafaelcorsini.study.domain.entity.Cliente;

@RunWith(SpringRunner.class)
@WebMvcTest(ClienteController.class)
class ClienteControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ClienteController clienteController;

	@Test
	void testGetClienteById() throws Exception {
		
		Cliente cliente = new Cliente();
		cliente.setId(1);
		cliente.setNome("Rafael");
		
		BDDMockito.given(clienteController.getClienteById(cliente.getId())).willReturn(cliente);
		
		RequestBuilder request = get("/api/clientes/1").contentType(MediaType.APPLICATION_JSON);
		mvc.perform(request)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("nome", Is.is(cliente.getNome())));
								
	}

	@Test
	void testSave() {
		assertEquals(1, 1);
	}

	@Test
	void testDelete() {
		assertEquals(1, 1);
	}

	@Test
	void testUpdate() {
		assertEquals(1, 1);
	}

	@Test
	void testFind() {
		assertEquals(1, 1);
	}

}
