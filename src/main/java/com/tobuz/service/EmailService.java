package com.tobuz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService 
{
	@Autowired
	private JavaMailSender emailSender;

	public void sendResetEmail(String email,String emailContent) 
	{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("contact@tobuz.com");
		message.setTo(email);
		message.setSubject("Tobuz Forgot Password OTP");
		message.setText(emailContent);
		emailSender.send(message);
	
	}
}
