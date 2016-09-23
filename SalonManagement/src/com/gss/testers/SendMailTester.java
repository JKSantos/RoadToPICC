package com.gss.testers;

import com.gss.utilities.SendMail;

public class SendMailTester {
	
	public static void main(String[] args){
		SendMail mail = new SendMail();
		mail.sendEmail("santos.jeffrey0023@gmail.com", "username", "pass");
	}

}
