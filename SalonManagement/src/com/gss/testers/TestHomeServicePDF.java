package com.gss.testers;

import java.io.IOException;
import java.util.Date;

import com.gss.Receipts.HomeServiceReceipt;
import com.gss.model.Payment;
import com.gss.model.Reservation;
import com.gss.utilities.DateHelper;
import com.itextpdf.text.DocumentException;

public class TestHomeServicePDF {
	
	private int id;
	
	public String execute() {
		HomeServiceReceipt receipt = new HomeServiceReceipt();
		Payment payment = new Payment(1, 386, "reservation", 1000.00, "FULL", new Date());
		try {
			receipt.createProductSalesReceipt(Reservation.getReservationByID(id), "Jeffrey", DateHelper.stringDate(new Date()), payment, "WEB-INF/Receipts/Test.pdf");
		} catch (NullPointerException | IOException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
	public void setId(int id) {
		this.id = id;
	}

}
