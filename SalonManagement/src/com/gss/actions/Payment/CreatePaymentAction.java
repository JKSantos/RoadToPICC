package com.gss.actions.Payment;

import org.apache.commons.lang3.time.StopWatch;

import com.gss.Receipts.WalkInReceipt;
import com.gss.dao.WalkInJDBCRepository;
import com.gss.dao.WalkInTransRepository;
import com.gss.model.Payment;
import com.gss.utilities.DateHelper;
import com.gss.utilities.NumberGenerator;
import com.gss.utilities.PriceFormatHelper;
import com.gss.utilities.ProductSalesReceiptThread;

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

		Payment payment = new Payment(intPaymentID, intInvoiceID, paymentType, PriceFormatHelper.convertToDouble(dblPaymentAmount, "Php "),this.paymentType,DateHelper.parseDate(unconvertedDate));
		
		this.url = "WEB-INF/Receipts/" + NumberGenerator.localDateTime() + ".pdf";
				
		boolean recorded = Payment.createPayment(strPaymentType, payment, url);
		
		if(strPaymentType.equals("order")){
			ProductSalesReceiptThread thread = new ProductSalesReceiptThread();
			thread.createProductSalesReceipt(this.intInvoiceID, "JEFFREY SANTOS", this.datDateOfPayment, payment, url);
		}else if(strPaymentType.equals("walkin")){
			WalkInReceipt walkin = new WalkInReceipt();
			int walkinID = WalkInTransRepository.getWalkInID(this.intInvoiceID);
			walkin.createProductSalesReceipt(WalkInTransRepository.getWalkInByID(walkinID), "JEFFREY SANTOS", this.datDateOfPayment, payment, url);
		}
		
		if(recorded == true){
			
			result = "success";
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