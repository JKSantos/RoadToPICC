package com.gss.testers;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.gss.dao.PaymentJDBCRepositoryImpl;
import com.gss.model.Payment;
import com.gss.model.ProductSales;
import com.gss.service.PaymentService;
import com.gss.service.PaymentServiceImpl;
import com.gss.utilities.ProductSalesReceiptThread;

public class PayOrderTester {
	
	public static void main(String[] args) throws SQLException{
		
		Payment payment = new Payment(1, 181, "order", 500, "FULL PAYMENT", new Date());
		
		ProductSalesReceiptThread prod = new ProductSalesReceiptThread();
		prod.createProductSalesReceipt(176, "JEFFREY SANTOS", "2016-02-23", payment, "C:/Java/sample.pdf");
		
		prod.createProductSalesReceipt(176, "JEFFREY SANTOS", "2016-02-23", payment, "C:/Java/sample2.pdf");
	}
}