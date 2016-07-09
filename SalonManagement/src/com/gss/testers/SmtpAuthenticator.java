package com.gss.testers;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SmtpAuthenticator extends Authenticator{
	
	    private String username = "salon.management.system@gmail.com";
	    private String password = "salonsalon06";

	    public PasswordAuthentication getPasswordAuthentication() {
	        return new PasswordAuthentication(username, password);
	    }
}
