package com.gss.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.gss.dao.PaymentJDBCRepositoryImpl;
import com.gss.model.Payment;
import com.gss.model.ProductSales;
import com.itextpdf.text.DocumentException;

public class ProductSalesReceiptThread implements Runnable{

	private Thread thread;
	private int intInvoiceID;
	private String cashier;
	private String date;
	private Payment payment;
	private String url;

	public void createProductSalesReceipt(int intInvoiceID, String cashier, String date, Payment payment, String url){
		
		this.intInvoiceID = intInvoiceID;
		this.cashier = cashier;
		this.date = date;
		this.payment = payment;
		this.url = url;
		
		start();
	}
	
	@Override
	public void run() {
		Receipt receipt = new Receipt();
		
		try {
			receipt.createProductSalesReceipt(new PaymentJDBCRepositoryImpl().getProductBySalesID(intInvoiceID), cashier, date, payment, url);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		thread.stop();
	}
	
	public void start(){
		
		if(thread == null){
			thread = new Thread(this, "Receipt");
			thread.start();
		}
		else{
			thread.start();
		}
	}

}
