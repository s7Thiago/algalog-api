package com.thiagosilva.algalog.domain.service;

import java.time.LocalDateTime;

import com.thiagosilva.algalog.domain.model.Entrega;
import com.thiagosilva.algalog.domain.model.StatusEntrega;
import com.thiagosilva.algalog.domain.repository.EntregaRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

/**
 * Representa o caso de uso/ processo de negócio do do serviço criação de uma
 * entrega de produtos.
 */
@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

    private EntregaRepository entregaRepository;

    @Transactional
    public Entrega solicitarEntrega(Entrega entrega) {
        entrega.setStatus(StatusEntrega.PENDENTE); // Quando é feita uma solicitação de entrega, o status é PENDENTE por
                                                   // padrão

        entrega.setDataPedido(LocalDateTime.now());
        return entregaRepository.save(entrega);
    }
}
