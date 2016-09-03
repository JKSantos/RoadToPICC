package com.gss.testers;

import java.sql.SQLException;
import java.util.Date;

import com.gss.model.Payment;

public class CreateWalkinPayment {
	
	public static void main(String[] args){
		
		Payment payment = new Payment(1, 193, "walkin", 125, "FULL PAYMENT", new Date());
		
		try {
			System.out.println(Payment.createPayment("walkin", payment, "C://Java//WalkinReceipt.pdf"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
