package com.gss.actions.Payment;

import java.sql.SQLException;
import java.util.Date;

import com.gss.dao.PaymentJDBCRepositoryImpl;
import com.gss.model.Payment;
import com.gss.model.ProductSales;
import com.gss.utilities.DateHelper;
import com.gss.utilities.PriceFormatHelper;
import com.gss.utilities.Receipt;

public class CreatePaymentAction {

	private int intPaymentID;			//dummy data
	private int intInvoiceID;			//real data
	private String strPaymentType;		//"order", "walkin", or "reservation"
	private String dblPaymentAmount;	//real data
	private String datDateOfPayment;		
	
	private String paymentType;
	private String result = "failed";
	
	private String url = "none";
	public String execute() throws Exception{
		
		String unconvertedDate = new DateHelper().convert(this.datDateOfPayment.split("/"));
		Payment payment = new Payment(intPaymentID, intInvoiceID, strPaymentType, PriceFormatHelper.convertToDouble(dblPaymentAmount, "Php "),this.paymentType,DateHelper.parseDate(unconvertedDate));
		
		boolean recorded = Payment.createPayment(paymentType, payment);
		
		if(recorded == true){
			result = "success";
			
			if(this.strPaymentType.equals("order")){
				Receipt receipt = new Receipt();
				
				this.url = receipt.createProductSalesReceipt(new PaymentJDBCRepositoryImpl().getProductBySalesID(this.intInvoiceID), "JEFFREY SANTOS", this.datDateOfPayment, payment);
			}
			
		}

		
		return result;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getResult() {
		return result;
	}

	public int getIntPaymentID() {
		return intPaymentID;
	}

	public void setIntPaymentID(int intPaymentID) {
		this.intPaymentID = intPaymentID;
	}

	public int getIntInvoiceID() {
		return intInvoiceID;
	}

	public void setIntInvoiceID(int intInvoiceID) {
		this.intInvoiceID = intInvoiceID;
	}

	public String getStrPaymentType() {
		return strPaymentType;
	}

	public void setStrPaymentType(String strPaymentType) {
		this.strPaymentType = strPaymentType;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDatDateOfPayment() {
		return datDateOfPayment;
	}

	public void setDatDateOfPayment(String datDateOfPayment) {
		this.datDateOfPayment = datDateOfPayment;
	}

	public void setDblPaymentAmount(String dblPaymentAmount) {
		this.dblPaymentAmount = dblPaymentAmount;
	}

	public String getUrl() {
		return url;
	}
	
	
	
}