package com.thiagosilva.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.thiagosilva.algalog.domain.model.Entrega;
import com.thiagosilva.algalog.domain.service.model.EntregaModel;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

/**
 * Esta classe é responsável por fazer o mapeamento de objetos de um tipo para
 * outro. A responsabilidade de mapear os objetos foi transferida para esta
 * classe com o objetivo de tornar a aplicação o menos dependente possível do
 * ModelMapper. Isso torna a aplicação mais escalável e faz com que uma uma
 * possível futura mudança seja menos trabalhosa de realizar caso seja decidido
 * usar outra lib de mapeamento de objetos.
 */
@AllArgsConstructor
@Component // Declara que esta classe é um tipo que será gerenciado pelo Spring
public class EntregaAssembler {
    private ModelMapper modelMapper;

    public EntregaModel toModel(Entrega entrega) {
        return modelMapper.map(entrega, EntregaModel.class);// Usando ModelMapper para atribuir todas as propriedades de
                                                            // cada entrega em um respectivo objeto equivalente da
                                                            // camada de Representation Model a partir de cada instância
                                                            // da entidade Entrega.
    }

    public List<EntregaModel> toCollectionModel(List<Entrega> entregas) {
        return entregas.stream().map(this::toModel) // Convertendo uma uma stream de Entrega para uma stream de
                                                    // EntregaModel
                .collect(Collectors.toList());
    }

}
