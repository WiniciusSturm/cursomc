package com.winiciussturm.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.winiciussturm.cursomc.domain.Pedido;

public interface EmailService 
{

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

	
}
