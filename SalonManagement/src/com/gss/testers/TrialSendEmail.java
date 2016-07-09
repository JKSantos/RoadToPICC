package com.gss.testers;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TrialSendEmail {

	 public static void main(String[] args)
	   {    
	      String to = "labidabssy.jeffrey23@yahoo.com";
	      String from = "salon.management.system@gmail.com";
	      
	      Properties props = createConfiguration();

	        Session session = Session.getDefaultInstance(props,  
	        	    new javax.mail.Authenticator() {
	        	       protected PasswordAuthentication getPasswordAuthentication() {  
	        	       return new PasswordAuthentication(from,"salonsalon06");  
	        	   }});

	        try {

	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(from));
	            message.addRecipient(Message.RecipientType.TO,
	                new InternetAddress(to));

	            message.setSubject("From SalonManagementSystem");
	            message.setText("Hi! Youre Username is " + "123" + " and your pasword is " + "123" + ".");
	           Transport.send(message);

	           System.out.println("MESSAGE SUCCESSFULLY SENT....");
	        } catch (MessagingException mex) {
	           mex.printStackTrace();
	     }
	 }
	 
	 private static Properties createConfiguration() {
		    return new Properties() {
				private static final long serialVersionUID = 1L;
				{
					  put("mail.transport.protocol", "smtp");     
					  put("mail.host", "smtp.mail.yahoo.com");  
					  put("mail.smtp.auth", "true");  
					  put("mail.smtp.port", "465");  
					  put("mail.debug", "true");  
					  put("mail.smtp.socketFactory.port", "465");  
					  put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
					  put("mail.smtp.socketFactory.fallback", "false");
		        }
		    };
		 }

}


 