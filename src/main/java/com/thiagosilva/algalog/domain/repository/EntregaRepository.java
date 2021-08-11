package com.thiagosilva.algalog.domain.repository;

import com.thiagosilva.algalog.domain.model.Entrega;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {

}