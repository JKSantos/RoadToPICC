package com.gss.testers;

import com.gss.utilities.NotifyCustomerViaSMS;

public class FowizTester {
	
	public static void main(String[] args){
		
		NotifyCustomerViaSMS test = new NotifyCustomerViaSMS();
		
		test.sendSMS("Hello po", "+639361144842");
		
	}
}
