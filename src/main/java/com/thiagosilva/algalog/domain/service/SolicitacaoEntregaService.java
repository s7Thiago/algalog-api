package com.thiagosilva.algalog.domain.service;

import java.time.OffsetDateTime;

import com.thiagosilva.algalog.domain.model.Cliente;
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

    private CatalogoClienteService catalogoClienteService;
    private EntregaRepository entregaRepository;

    @Transactional
    public Entrega solicitarEntrega(Entrega entrega) {
        entrega.setStatus(StatusEntrega.PENDENTE); // Quando é feita uma solicitação de entrega, o status é PENDENTE por
                                                   // padrão

        // Verificando se existe o cliente com o id passado nos dados da entrega para
        // retornar a mensagem adequada caso o mesmo não exista
        Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());

        entrega.setCliente(cliente); // Resolve o problema de as informações do cliente estarem todas null no json de
                                     // retorno deste método
        entrega.setDataPedido(OffsetDateTime.now());
        return entregaRepository.save(entrega);
    }
}
