package com.thiagosilva.algalog.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thiagosilva.algalog.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
//	Procura um nome com correspondência exata seguindo um padrão no nome
//	dos métodos, o próprio jpa já se encarrega de fornecer a implementação
	List<Cliente> findByNome(String nome);
	
//	O nome deste método instrui o JPA a implementar um método que encontra re-
//	gistros com base na entidade Cliente contendo o nome do parâmetro com cor-
//	espondência não exata (devido ao sufixo Containing, que vai usar o Like do SQL
//	para realizar a consulta)
	List<Cliente> findByNomeContaining(String nome);
}
