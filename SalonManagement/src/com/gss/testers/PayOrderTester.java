package com.gss.testers;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.gss.model.Payment;
import com.gss.model.ProductSales;
import com.gss.service.PaymentService;
import com.gss.service.PaymentServiceImpl;

public class PayOrderTester {
	
	public static void main(String[] args) throws SQLException{
		
		Payment payment = new Payment(1, 66, "FULL PAYMENT", 500, null, new Date());
		
		System.out.println(Payment.createPayment("order", payment));
		
		PaymentService service = new PaymentServiceImpl();

		
		
		
		List<ProductSales> unpaid = service.getAllUnpaidOrder();
		
		
		
		System.out.println(unpaid.size());
		
	}
}
