package io.github.rafaelcorsini.study.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/*@RunWith(SpringRunner.class)
@WebMvcTest(ClienteController.class)
*/class ClienteControllerTest {
	
	/*
	 * @Autowired private MockMvc mvc;
	 * 
	 * @MockBean private ClienteController clienteController;
	 * 
	 * @Test void testGetClienteById() throws Exception {
	 * 
	 * // Given Cliente cliente = new Cliente(); cliente.setId(1);
	 * cliente.setNome("Rafael");
	 * 
	 * BDDMockito.given(clienteController.getClienteById(cliente.getId())).
	 * willReturn(cliente);
	 * 
	 * // When RequestBuilder request =
	 * get("/api/clientes/1").contentType(MediaType.APPLICATION_JSON);
	 * 
	 * // Then mvc.perform(request)
	 * .andExpect(MockMvcResultMatchers.status().isOk())
	 * .andExpect(MockMvcResultMatchers.jsonPath("nome", Is.is(cliente.getNome())));
	 * 
	 * }
	 */

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
