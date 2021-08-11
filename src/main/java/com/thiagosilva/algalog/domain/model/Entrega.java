package com.thiagosilva.algalog.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Setter
// Define que os métodos equals e hashcode serão gerados apenas para o atributo
// que estiver explicitamente incluso com @EqualsAndHashCode.Include
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity // Mapeia a classe em questão como uma entidade do jakarta persistence
public class Entrega {

    @EqualsAndHashCode.Include // Faz com que o atributo abaixo seja levado em consideração nos métodos equals
                               // e hashcode
    @Id // Define que o atributo abaixo é a chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O próprio banco de dados vai incrementar o Id
    private Long id;

    @ManyToOne // Mapeia o relacionamento entre as entidades Entrega e Cliente (Muitas entregas
               // para um cliente (uma mesma entrega não vai estar associada a mais de um
               // cliente)). A propriedade por padrão será criada como cliente_id (poderia ser
               // modificada com a annotation @JoinColumn(name = "id_cliente") por exemplo)
    private Cliente cliente;

    @Embedded // Usada para abstrair os dados do destinatário para a classe Entrega,
              // abstraindo os dados para a mesma
    private Destinatario destinatario; // Como destinatário não é mapeado como uma tabela a parte no banco de dados,
                                       // todas as informações do destinatário serão transferidas para a tabela
                                       // entrega. Por isso não é necessário mapear o relacionamento entre as
                                       // entidades. Por questões de aproveitamento das vantagens da orientação a
                                       // objetos, os dados do destinatário foram abstraídos para a classe Entrega.
                                       // para uma classe separada, a fim de tornar o código na classe Entrega mais
                                       // limpo.

    private BigDecimal taxa; // Taxa de entrega

    @Enumerated(EnumType.STRING) // Define que a coluna do status na tabela vai armazenar a string que representa
                                 // a constante da enumeração (ORDINAL armazenaria o índice, e nesse caso não
                                 // seria muito interessante)
    @JsonProperty(access = Access.READ_ONLY) // Define que o atributo não pode sofrer alteração externa, evitando assim
                                             // que o consumidor da api modifique o status de uma entrega durante a
                                             // criação da solicitação de entrega por exemplo
    private StatusEntrega status;

    @JsonProperty(access = Access.READ_ONLY) // Define que o atributo não pode sofrer alteração externa, evitando assim
                                             // que o consumidor da api modifique a data do pedido de uma entrega
                                             // durante a criação da solicitação de entrega por exemplo
    private LocalDateTime dataPedido;

    @JsonProperty(access = Access.READ_ONLY) // Define que o atributo não pode sofrer alteração externa, evitando assim
                                             // que o consumidor da api modifique a data de finalização de uma entrega
                                             // durante a criação da solicitação de entrega por exemplo
    private LocalDateTime dataFinalizacao;
}
