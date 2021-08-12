package com.thiagosilva.algalog.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable // Define que esta classe está habilitada para ser usada como embedded em alguma
            // entidade
public class Destinatario {

    @NotBlank
    @Column(name = "destinatario_nome") // Personalizando o nome da coluna no banco de dados
    private String nome;

    @NotBlank
    @Column(name = "destinatario_logradouro") // Personalizando o nome da coluna no banco de dados
    private String logradouro;

    @NotBlank
    @Column(name = "destinatario_numero") // Personalizando o nome da coluna no banco de dados
    private String numero;

    @NotBlank
    @Column(name = "destinatario_complemento") // Personalizando o nome da coluna no banco de dados
    private String complemento;

    @NotBlank
    @Column(name = "destinatario_bairro") // Personalizando o nome da coluna no banco de dados
    private String bairro;

    // Considerando que é um sistema local que não entrega em outra cidade e nem
    // outro estado, não serão incluídos esses campos
}
