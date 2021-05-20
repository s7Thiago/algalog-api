package com.thiagosilva.algalog.api.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiagosilva.algalog.domain.model.Cliente;
import com.thiagosilva.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

// Torna a classe identificável para o Spring framework, fazendo  com que os
// endpoints cadastrados nela fiquem disponíveis
@RestController
@AllArgsConstructor
public class ClienteController {
	
	private ClienteRepository clienteRepository;
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		return clienteRepository.findByNomeContaining("ia");
	}

}
