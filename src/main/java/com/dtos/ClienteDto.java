package com.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.models.Cliente;
import com.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotEmpty(message = "Campo nome não pode ser vazio/nulo.")
	@Length(min = 5, max = 60, message = "O nome deve conter de 5 a 120 caracteres.")
	private String nome;
	@NotEmpty(message = "Campo email não pode ser vazio/nulo.")
	@Email(message = "Email invalido.")
	private String email;
	
	public ClienteDto() {
	}

	public ClienteDto(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
