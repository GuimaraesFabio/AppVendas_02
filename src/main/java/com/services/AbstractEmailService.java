package com.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.models.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Autowired
	private ClienteService _clienteService;

	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendOrderConfirmationPedido(Pedido obj) {

		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	private SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {

		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(_clienteService.findById(obj.getCliente().getId()).getEmail());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setFrom(sender);
		sm.setSubject("Pedido Confirmado! Pedido nº: " + obj.getId());
		sm.setText(obj.toString());
		return sm;
	}
}
