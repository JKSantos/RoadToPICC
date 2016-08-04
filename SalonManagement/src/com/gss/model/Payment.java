package com.gss.model;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.gss.service.PaymentService;
import com.gss.service.PaymentServiceImpl;

public class Payment {
	
	private int intPaymentID;			//dummy data
	private int intInvoiceID;			//real data
	private String strPaymentType;		//"order", "walkin", or "reservation"
	private double dblPaymentAmount;	//real data
	private Date datDateOfPayment;		//dummy data
	
	/*	ang pagset ng data nyan galing sa jsp
	 * 	gagamitin mong variables payment.intPaymentID, payment.intInvoiceID, payment.strPaymentType, etc.
	 */

	public Payment(int intPaymentID, int intInvoiceID, String strPaymentType, double dblPaymentAmount,
			Date datDateOfPayment) {
		super();
		this.intPaymentID = intPaymentID;
		this.intInvoiceID = intInvoiceID;
		this.strPaymentType = strPaymentType;
		this.dblPaymentAmount = dblPaymentAmount;
		this.datDateOfPayment = datDateOfPayment;
	}

	public int getIntPaymentID() {
		return intPaymentID;
	}

	public void setIntPaymentID(int intPaymentID) {
		this.intPaymentID = intPaymentID;
	}

	public String getStrPaymentType() {
		return strPaymentType;
	}

	public void setStrPaymentType(String strPaymentType) {
		this.strPaymentType = strPaymentType;
	}

	public double getDblPaymentAmount() {
		return dblPaymentAmount;
	}

	public void setDblPaymentAmount(double dblPaymentAmount) {
		this.dblPaymentAmount = dblPaymentAmount;
	}

	public Date getDatDateOfPayment() {
		return datDateOfPayment;
	}

	public void setDatDateOfPayment(Date datDateOfPayment) {
		this.datDateOfPayment = datDateOfPayment;
	}

	public int getIntInvoiceID() {
		return intInvoiceID;
	}

	public void setIntInvoiceID(int intInvoiceID) {
		this.intInvoiceID = intInvoiceID;
	}
	
	public static String convertPaymentStatus(int status){
		
		if(status == 1){
			return "INCOMPLETE";
		}
		else if(status == 2){
			return "COMPLETE";
		}
		else
			return "UNPAID";
		
	}
	
	public static String convertToString(int payment){
		
		String converted = "";
		
		switch(payment){
			case 1:
				converted = "DOWN PAYMENT";
				break;
			case 2:
				converted = "COMPLEMENTARY PAYMENT";
				break;
			case 3:
				converted = "FULL PAYMENT";
				break;
			default:
				converted = "Update Payment.convertToString()";
		}
		
		return converted;
	}
	
	public static boolean createPayment(String paymentType, Payment payment) throws SQLException{
		
		PaymentService service = new PaymentServiceImpl();
		
		if(paymentType.equals("reservation")){
			return service.createReservationPayment(payment);
		}
		else if(paymentType.equals("walkin")){
			return service.createWalkInPayment(payment);
		}
		else
			return service.createProductSalesPayment(payment);
		
	}
	
	public static List<ProductSales> getAllUnpaid() throws SQLException{
		
		PaymentService service = new PaymentServiceImpl();
		
		return service.getAllUnpaidOrder();
	}
}
