package com.gss.testers;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.gss.model.Discount;
import com.gss.model.ExtraCharge;
import com.gss.model.Invoice;
import com.gss.model.Payment;
import com.gss.model.ProductSales;
import com.gss.model.Reservation;
import com.gss.service.PaymentService;
import com.gss.service.PaymentServiceImpl;

public class PayReservation {

	public static void main(String[] args) throws SQLException{
		
//		List<Discount> discountList = new ArrayList<Discount>();
//		List<ExtraCharge> extraChargeList = new ArrayList<ExtraCharge>();
//		
//		Payment payment = new Payment(62, 50, "FULL PAYMENT", 500, new Date());
//		
//		
//		PaymentService service = new PaymentServiceImpl();
//		
//		System.out.println(service.createReservationPayment(payment));
		
		searchReservation();
		
		
	}
	
	public static void searchReservation(){
		
		List<Reservation> reservationList = Reservation.getAllReservationNoDetails();
		
		System.out.println(reservationList.size());
	}
}
