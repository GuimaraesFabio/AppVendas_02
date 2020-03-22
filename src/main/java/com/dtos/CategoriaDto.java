package com.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.models.Categoria;

public class CategoriaDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;

	@NotEmpty(message = "Campo nome não pode ser vazio/nulo.")
	@Length(min = 5, max = 60, message = "O nome deve conter de 5 a 60 caracteres.")
	private String nome;

	public CategoriaDto() {
	}

	public CategoriaDto(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
