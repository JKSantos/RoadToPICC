package com.gss.testers;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.gss.utilities.*;
import com.itextpdf.text.DocumentException;
import com.gss.Receipts.HomeServiceReceipt;
import com.gss.model.*;
import com.gss.service.ReservationService;
import com.gss.service.ReservationServiceImpl;

public class CreateReservation {                                                                                                                                                                                                                                                   
	
	public static void main(String[] args) throws SQLException{
		
		ReservationService service = new ReservationServiceImpl();
		
		Reservation reservation;
		
		int intReservationID = 46;
		Customer customer;
		ReservationInclusion includedItems;
		int intReservationType = 2;
		Date dateCreated = DateHelper.parseDate("2016-03-01");
		Date datFrom = DateHelper.parseDate("2016-03-04");
		Date datTo = DateHelper.parseDate("2016-03-04");
		Time timFrom = TimeHelper.parseTime("2:30PM");
		Time timTo = TimeHelper.parseTime("5:00PM");
		String strVenue = "Pasig Municipality";
		int headCount = 3;
		List<EmployeeAssigned> employeeAssigned = new ArrayList<EmployeeAssigned>();
		String strStatus = "PENDING";
		Invoice invoice;
		Discount discount = Discount.searchDiscount(27, Discount.getAllDiscount());
		
		//for invoice
				List<ExtraCharge> extraCharges = new ArrayList<ExtraCharge>();
				ExtraCharge extra = new ExtraCharge(26, "", "", headCount, headCount);
				List<Discount> dicounts = new ArrayList<Discount>();
				invoice = Invoice.createNullInvoice(extraCharges, dicounts, 740.00, "HALF PAYMENT");
		
		//for customer
		String strName = "JOSELITO RULL SANTOS";
		String strAddress = "189-Dr. Sixto Antonio Avenue, Rosario Pasig City";
		String strContactNo = "09361144842";
		String strEmailAddress = "santos.jeffrey0023@gmail.com";
		customer = new Customer(1, "COMPANY", "MICROSOFT", strName, strAddress, strContactNo, strEmailAddress);
		
		//for ReservationInlusion
		List<ProductOrder> products = new ArrayList<ProductOrder>();
		List<ReservedService> services = new ArrayList<ReservedService>();
		List<ReservedPackage> packages = new ArrayList<ReservedPackage>();
		List<ReservedPromo> promos = new ArrayList<ReservedPromo>();
		includedItems = new ReservationInclusion(products, services, packages, promos);
		
			//ProductOrder
			Product product = new Product(35, strEmailAddress, strEmailAddress, strEmailAddress, headCount, null, headCount, strEmailAddress, headCount);
			ProductOrder productOrder = new ProductOrder(35, product, 4, 1);
			products.add(productOrder);
			
			//Service
			Service service1 = Service.createNullService(75);
			ReservedService reserved = new ReservedService(1, 1, service1, 2, 1);
			services.add(reserved);
			
			//Package
			
			//Promo
		
		
		//for Employee Assigned
		Employee emp = new Employee(79, strEmailAddress, strEmailAddress, strEmailAddress, datTo, strEmailAddress, strEmailAddress, strEmailAddress, strEmailAddress, strEmailAddress, strEmailAddress, strEmailAddress, strEmailAddress, null, null, false, "NOT AVAILABLE", null);
		Employee emp2 = new Employee(68, strEmailAddress, strEmailAddress, strEmailAddress, datTo, strEmailAddress, strEmailAddress, strEmailAddress, strEmailAddress, strEmailAddress, strEmailAddress, strEmailAddress, strEmailAddress, null, null, false, "NOT AVAILABLE", null);
		employeeAssigned.add(new EmployeeAssigned(1, 1, emp, headCount));
		employeeAssigned.add(new EmployeeAssigned(1, 1, emp2, headCount));
		
		reservation = new Reservation(intReservationID, customer, includedItems, intReservationType, dateCreated, datFrom, datTo, timFrom, timTo, strVenue, 2, headCount, employeeAssigned, invoice, strStatus, "");
		
		System.out.println(service.createReservation(reservation));
		
		System.out.println("Reservation Successfully Saved");
	}

}
