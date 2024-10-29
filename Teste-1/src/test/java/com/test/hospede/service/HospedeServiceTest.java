package com.test.hospede.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.entity.Hospede;
import com.test.repository.HospedeRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class HospedeServiceTest {
	@Autowired
	private HospedeServiceTest hospedeService;
	@Autowired
	private HospedeRepository hospedeRepository;
	@BeforeEach
	void setUp() {
		hospedeRepository.deleteAll();// Limpa o banco de dados antes de cada teste
	}
	@DisplayName("Testando salvar Hóspede")
	@Test
	void testSalvarHospede() {
		Hospede hospede = new Hospede(null, "jp", "jp@gmail.com","(00)0000-0000");

		Hospede resultado = hospedeService.salvarHospede(hospede);

		assertNotNull(resultado);
		assertEquals("jp", resultado.getNome());
		assertTrue(resultado.getId()> 0);
	}
	@DisplayName("Testando listar hospedes")
	@Test
	void testListarTodos() {
		Hospede hospede1 = new Hospede(null, "Julia", "julia@gmail.com", "(00)0000-0000");
		Hospede hospede2 = new Hospede(null, "Julio", "julio@gmail.com", "(00)0000-0000");
		hospedeService.salvarHospede(hospede1);
		hospedeService.salvarHospede(hospede2);
		List<Hospede> resultado = hospedeService.listarTodos();
		assertNotNull(resultado);
		assertEquals(2, resultado.size());
	}

}