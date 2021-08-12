package com.thiagosilva.algalog.api.controller;

import java.util.EnumMap;
import java.util.List;

import javax.validation.Valid;

import com.thiagosilva.algalog.domain.model.Entrega;
import com.thiagosilva.algalog.domain.repository.EntregaRepository;
import com.thiagosilva.algalog.domain.service.SolicitacaoEntregaService;
import com.thiagosilva.algalog.domain.service.model.DestinatarioModel;
import com.thiagosilva.algalog.domain.service.model.EntregaModel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    private EntregaRepository entregaRepository;
    private SolicitacaoEntregaService solicitacaoEntregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Se este método rodar com sucesso, significa que um recurso novo foi criado,
                                        // por isso a escolha de retornar o status 201
    public Entrega solicitar(@Valid @RequestBody Entrega entrega) {
        return solicitacaoEntregaService.solicitarEntrega(entrega);
    }

    @GetMapping
    public List<Entrega> listar() {
        return entregaRepository.findAll();
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
        return entregaRepository.findById(entregaId).map(entrega -> {
            EntregaModel entregaModel = new EntregaModel();
            entregaModel.setId(entrega.getId());
            entregaModel.setNomeCliente(entrega.getCliente().getNome());
            entregaModel.setDestinatario(new DestinatarioModel());
            entregaModel.getDestinatario().setNome(entrega.getDestinatario().getNome());
            entregaModel.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());
            entregaModel.getDestinatario().setNumero(entrega.getDestinatario().getNumero());
            entregaModel.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());
            entregaModel.getDestinatario().setBairro(entrega.getDestinatario().getBairro());
            entregaModel.setTaxa(entrega.getTaxa());
            entregaModel.setStatus(entrega.getStatus());
            entregaModel.setDataPedido(entrega.getDataPedido());
            entregaModel.setDataFinalizacao(entrega.getDataFinalizacao());
            return ResponseEntity.ok(entregaModel);
        }) // Caso exista alguma coisa dentro deste
           // Optional, será retornado
           // ResponseEntity.ok com a própria entrega
           // que estiver dentro desse Optional como
           // corpo da resposta convertida para um objeto EntregaModel
                .orElse(ResponseEntity.notFound().build()); // Cai no orElse se não existir nada dentro do Optional,
                                                            // retornado pelo findById, dessa forma, retornando 404 (Not
                                                            // Found)
    }
}
