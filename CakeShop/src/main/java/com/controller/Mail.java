package com.controller;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Model.Lessonsignup;

@Controller
public class Mail {
	
	@RequestMapping(value="/sendEmail.do", method = RequestMethod.GET, params= {"email"})
	public String sendEmail(Model model, @RequestParam("email") String email) throws Exception {
		String recepient=email;
		sendEmail(recepient);
		model.addAttribute("result", "ok");
		return "viewsuccess";
	}
	
	
	
	public static void sendEmail(String recepient) throws Exception {
		System.out.println("準備中...");
		Properties properties=new Properties();
		
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String account="1231paamuyl@gmail.com";
		String password="paamuyl1231";
		
		Session session=Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(account, password);
			}
		});
		
		Message message=prepareMessage(session, account, recepient);
		Transport.send(message);
		System.out.println("成功");
	}
	
	private static Message prepareMessage(Session session, String account, String recepient) {
		try {
			Message message=new MimeMessage(session);
			message.setFrom(new InternetAddress(account));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("巨匠烘焙坊");
			message.setText("謝謝您的報名，我們將盡快與您聯絡。");
			return message;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}