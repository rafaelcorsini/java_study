package io.github.rafaelcorsini.study.service;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class PedidoServiceTest {

	@Autowired
	PedidoService pedidoService;

	@Test
	void testSalvar() {
		fail("Not yet implemented");
	}

	@Test
	void testObterPedidoCompleto() {
		fail("Not yet implemented");
	}

	@Test
	void testAtualizarStatus() {
		fail("Not yet implemented");
	}

	@Test
	void testTest() {
		boolean test = pedidoService.test();
		Assertions.assertEquals(true, test);
	}
}
