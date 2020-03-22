package com.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.models.Pedido;

@Service
public interface EmailService {

	void sendOrderConfirmationPedido(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
