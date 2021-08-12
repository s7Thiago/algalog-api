package com.thiagosilva.algalog.api.controller;

import javax.validation.Valid;

import com.thiagosilva.algalog.domain.model.Entrega;
import com.thiagosilva.algalog.domain.service.SolicitacaoEntregaService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private SolicitacaoEntregaService solicitacaoEntregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Se este método rodar com sucesso, significa que um recurso novo foi criado,
                                        // por isso a escolha de retornar o status 201
    public Entrega solicitar(@Valid @RequestBody Entrega entrega) {
        return solicitacaoEntregaService.solicitarEntrega(entrega);
    }
}
