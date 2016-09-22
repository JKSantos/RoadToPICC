package com.gss.testers;

import java.security.Security;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.gss.model.Employee;

public class EmployeeCredentialsSender {
	
	final static String username = "salonmanagement2016@gmail.com";
	final static String password = "salon2016";
	
	public static void sendEmail(Employee employee) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("salonmanagement2016@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(employee.getStrEmpEmail()));
			message.setSubject("Employee Username and Password");
			message.setText("Good day Mr./Ms. " + employee.getStrEmpLastName() + ", your username is "+employee.getStrEmpUsername()+" and your password is " + employee.getStrEmpPassword() + ". You can change it using Salon App or Salon Management System if you are granted the system access.");

			Transport.send(message);

			System.out.println("USERNAME AND PASSWORD WAS SUCCESSFULLY SENT..");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
