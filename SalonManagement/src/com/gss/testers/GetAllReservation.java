package com.gss.testers;

import com.gss.service.ReservationService;
import com.gss.service.ReservationServiceImpl;

public class GetAllReservation {
	
	public static void main(String[] args){
		
		ReservationService service = new ReservationServiceImpl();
		
		System.out.println(service.getAllReservation().size());
	}

}
 