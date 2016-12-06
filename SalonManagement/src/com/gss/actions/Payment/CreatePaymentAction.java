package com.gss.actions.Payment;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.struts2.StrutsStatics;

import com.gss.Receipts.EventReservationReceipt;
import com.gss.Receipts.HomeServiceReceipt;
import com.gss.Receipts.WalkInReceipt;
import com.gss.dao.PaymentJDBCRepositoryImpl;
import com.gss.dao.ReservationJDBCRepository;
import com.gss.dao.WalkInJDBCRepository;
import com.gss.dao.WalkInTransRepository;
import com.gss.model.Payment;
import com.gss.model.Reservation;
import com.gss.utilities.DateHelper;
import com.gss.utilities.NumberGenerator;
import com.gss.utilities.PriceFormatHelper;
import com.gss.utilities.ProductSalesReceiptThread;
import com.gss.utilities.Receipt;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CreatePaymentAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		int reservationID = ReservationJDBCRepository.getReservationID(intInvoiceID);
		Reservation reservation = Reservation.getReservationByID(reservationID);
		Payment payment = null;
		
		
		if(strPaymentType.equalsIgnoreCase("reservation"))
			payment = new Payment(intPaymentID, intInvoiceID, paymentType, reservation.getInvoice().getDblTotalPrice(),this.paymentType,DateHelper.parseDate(unconvertedDate));
		else
			payment = new Payment(intPaymentID, intInvoiceID, paymentType, PriceFormatHelper.convertToDouble(dblPaymentAmount, "Php "),this.paymentType,DateHelper.parseDate(unconvertedDate));
		
		payment.setDblPaymentAmount(PriceFormatHelper.convertToDouble(this.dblPaymentAmount, "Php "));
		this.url = "WEB-INF/Receipts/" + NumberGenerator.localDateTime() + ".pdf";
				
		boolean recorded = Payment.createPayment(strPaymentType, payment, url);
		
		if(strPaymentType.equals("order")){
			ProductSalesReceiptThread thread = new ProductSalesReceiptThread();
			new Receipt().createProductSalesReceipt(new PaymentJDBCRepositoryImpl().getProductBySalesID(this.intInvoiceID), "JEFFREY SANTOS", this.datDateOfPayment, payment, url);
			//thread.createProductSalesReceipt(this.intInvoiceID, "JEFFREY SANTOS", this.datDateOfPayment, payment, url);
		}else if(strPaymentType.equals("walkin")){
			WalkInReceipt walkin = new WalkInReceipt();
			int walkinID = WalkInTransRepository.getWalkInID(this.intInvoiceID);
			walkin.createProductSalesReceipt(WalkInTransRepository.getWalkInByID(walkinID), "JEFFREY SANTOS", this.datDateOfPayment, payment, url);
		}else if(strPaymentType.equalsIgnoreCase("reservation")) {
			
			HomeServiceReceipt receipt = new HomeServiceReceipt();
			
			if(reservation.getIntReservationType() == 2) {
				receipt.createProductSalesReceipt(reservation, "JEFFREY SANTOS", datDateOfPayment, payment, url);
				System.out.println("Receipt was successfully created");
			} else {
				EventReservationReceipt receipt2 = new EventReservationReceipt(); 
				receipt2.createProductSalesReceipt(reservation, "", datDateOfPayment, payment, url);
				System.out.println("HHHHHH");
			}
		}
		
		if(recorded == true){
			
			/*this.url = "file:///"+((ServletContext) ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT)) 
            .getRealPath(url);
			*/
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