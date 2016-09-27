package com.gss.testers;

import com.gss.utilities.MailSender;
import com.gss.utilities.SendMail;

public class SendMailTester {
	
	public static void main(String[] args){
		MailSender mail = new MailSender();
		MailSender.sendEmail("santos.jeffrey0023@gmail.com", "HELLO JEFFREY!");
	}

}
