package com.thiagosilva.algalog.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import com.thiagosilva.algalog.domain.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Cliente {

	@NotNull(groups = ValidationGroups.ClienteId.class) // Definindo um validation group personalizado para o id. Isso
														// ajuda a eliminar a obrigatoriedade de especificar o id no
														// cadastro de cliente. Problema criado pela adição da
														// annotation @NotNull que é necessária para validar uma
														// entrega, mas não é necessária na criação de um cliente.
														// Normalmente o validation group padrão é o Default.class
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 60)
	private String nome;

	@NotBlank
	@Size(max = 255)
	@Email
	private String email;

	@Column(name = "fone")
	@Size(max = 20)
	@NotBlank
	private String telefone;

}
