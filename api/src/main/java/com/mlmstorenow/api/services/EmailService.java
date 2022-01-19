package com.mlmstorenow.api.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	JavaMailSender emailSender;

	public void sendRegistrationemail(String to){

		try {    
			MimeMessage message = emailSender.createMimeMessage();

			MimeMessageHelper helper;
			helper = new MimeMessageHelper(message, true);
			helper.setFrom("mlmstorenow@gmail.com");
			helper.setTo(to);
			helper.setSubject("MLMstore Account Created");
			helper.setText(
					"Thank you for registering a new account with mlmstore. \n We truely appreciate your business");

			/*
			 * FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
			 * helper.addAttachment("Invoice", file);
			 */

			emailSender.send(message);
		} catch (MessagingException e) {

		}

	}
}