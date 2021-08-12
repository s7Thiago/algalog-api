package com.thiagosilva.algalog.domain.service.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.thiagosilva.algalog.domain.model.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

/**
 * Esta classe implementa o padrão DTO para transportar informações de entrega,
 * removendo a responsabilidade de apresentar os os recursos modelados na classe
 * Entrega da mesma. o padrão DTO ajuda na melhor componentização da aplicação,
 * com o objetivo de não misturar responsabilidades que deveriam ser da camada
 * de presentation com as atribuições das classes da camada de domínio (domain).
 */
@Getter
@Setter
public class EntregaModel {
    private Long id;
    private String nomeCliente;
    private DestinatarioModel destinatario;
    private BigDecimal taxa;
    private StatusEntrega status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;

}
