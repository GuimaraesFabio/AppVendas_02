package com.models;

import java.util.Date;

import javax.persistence.Entity;

import com.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date dataVencimento;
	private Date dataPagamento;

	public PagamentoComBoleto() {
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento descricao, Pedido pedido, Date dataVencimento,
			Date dataPagamento) {
		super(id, descricao, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
