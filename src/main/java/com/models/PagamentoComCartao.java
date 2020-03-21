package com.models;

import javax.persistence.Entity;

import com.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer numeroDeParcelas;

	public PagamentoComCartao() {
	}

	public PagamentoComCartao(Integer id, EstadoPagamento descricao, Pedido pedido, Integer numeroDeParcelas) {
		super(id, descricao, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

}
