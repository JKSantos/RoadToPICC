package com.gss.testers;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.gss.utilities.*;

import com.gss.model.*;
import com.gss.service.ReservationService;
import com.gss.service.ReservationServiceImpl;

public class CreateReservation {
	
	public static void main(String[] args) throws SQLException{
		
		ReservationService service = new ReservationServiceImpl();
		
		Reservation reservation;
		
		int intReservationID = 1;
		Customer customer;
		ReservationInclusion includedItems;
		int intReservationType = 1;
		Date dateCreated = DateHelper.parseDate("2016-03-01");
		Date datFrom = DateHelper.parseDate("2016-03-04");
		Date datTo = DateHelper.parseDate("2016-03-04");
		Time timFrom = TimeHelper.parseTime("2:30", "PM");
		Time timTo = TimeHelper.parseTime("5:00", "PM");
		String strVenue = "Tanghalang Pasigue�o";
		int headCount = 3;
		List<EmployeeAssigned> employeeAssigned = new ArrayList<EmployeeAssigned>();
		String strStatus = "PENDING";
		Invoice invoice;
		
		//for customer
		String strName = "Czarina Paras";
		String strAddress = "189-Dr. Sixto Antonio Avenue, Rosario Pasig City";
		String strContactNo = "09361144842";
		String strEmailAddress = "santos.jeffrey0023@gmail.com";
		customer = new Customer(1, strName, strAddress, strContactNo, strEmailAddress);
		
		//for ReservationInlusion
		List<ProductOrder> products = new ArrayList<ProductOrder>();
		List<ReservedService> services = new ArrayList<ReservedService>();
		List<ReservedPackage> packages = new ArrayList<ReservedPackage>();
		List<ReservedPromo> promos = new ArrayList<ReservedPromo>();
		includedItems = new ReservationInclusion(products, services, packages, promos);
		
			//ProductOrder
			Product product = new Product(35, strEmailAddress, strEmailAddress, strEmailAddress, headCount, null, headCount, strEmailAddress, headCount);
			ProductOrder productOrder = new ProductOrder(headCount, product, headCount, headCount);
			products.add(productOrder);
			
			//Service
			
			//Package
			
			//Promo
		
		
		//for Employee Assigned
		Employee emp = new Employee(79, strEmailAddress, strEmailAddress, strEmailAddress, datTo, strEmailAddress, strEmailAddress, strEmailAddress, strEmailAddress, strEmailAddress, strEmailAddress, strEmailAddress, strEmailAddress, null, null, false);
		employeeAssigned.add(new EmployeeAssigned(1, 1, emp, headCount));
		employeeAssigned.add(new EmployeeAssigned(1, 1, emp, headCount));
		
		//for invoice
		List<ExtraCharge> extraCharges = new ArrayList<ExtraCharge>();
		ExtraCharge extra = new ExtraCharge(26, strEmailAddress, strEmailAddress, headCount, headCount);
		List<Discount> dicounts = new ArrayList<Discount>();
		invoice = new Invoice(1, datTo, strStatus, dicounts, extraCharges, headCount, headCount, null, strStatus);
		
		
		reservation = new Reservation(intReservationID, customer, includedItems, intReservationType, dateCreated, datFrom, datTo, timFrom, timTo, strVenue, headCount, employeeAssigned, invoice, strStatus);
		
		System.out.println(service.createReservation(reservation));
	}

}
