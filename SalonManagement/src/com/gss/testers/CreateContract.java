package com.gss.testers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gss.model.Contract;
import com.gss.model.Customer;
import com.gss.model.Discount;
import com.gss.model.Employee;
import com.gss.model.EmployeeAssigned;
import com.gss.model.ExtraCharge;
import com.gss.model.Invoice;
import com.gss.model.Product;
import com.gss.model.ProductOrder;
import com.gss.model.Reservation;
import com.gss.model.ReservationInclusion;
import com.gss.model.ReservedPackage;
import com.gss.model.ReservedPromo;
import com.gss.model.ReservedService;
import com.gss.model.Service;
import com.gss.service.ReservationService;
import com.gss.service.ReservationServiceImpl;
import com.gss.utilities.ContractGenerator;
import com.gss.utilities.DateHelper;
import com.gss.utilities.DiscountChecker;
import com.gss.utilities.TimeHelper;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;

public class CreateContract {
	
	public static void main(String[] args) throws BadElementException, MalformedURLException, DocumentException, IOException{
		
		Contract contract = new Contract(DateHelper.stringDate(), "JEFFREY SANTOS", "SALON MANAGEMENT SYSTEM", "189-DR. SIXTO ANTONIO AVENU, ROSARIO PASIG CITY", "CZARINA PARAS", "JAN LANG",  createReservation());
		contract.setDate();
		
		ContractGenerator generator = new ContractGenerator();
		generator.createContract(contract);
	}
	
	public static Reservation createReservation(){
		ReservationService service = new ReservationServiceImpl();
		
		Reservation reservation;
		
		int intReservationID = 1;
		Customer customer;
		ReservationInclusion includedItems;
		int intReservationType = 1;
		Date dateCreated = DateHelper.parseDate("2016-03-01");
		Date datFrom = DateHelper.parseDate("2016-03-04");
		Date datTo = DateHelper.parseDate("2016-03-04");
		Time timFrom = TimeHelper.parseTime("2:30PM");
		Time timTo = TimeHelper.parseTime("5:00PM");
		String strVenue = "Tanghalang Pasigueño";
		int headCount = 3;
		List<EmployeeAssigned> employeeAssigned = new ArrayList<EmployeeAssigned>();
		String strStatus = "PENDING";
		Invoice invoice;
		Discount discount = Discount.searchDiscount(27, Discount.getAllDiscount());
		
		//for invoice
				List<ExtraCharge> extraCharges = new ArrayList<ExtraCharge>();
				ExtraCharge extra = new ExtraCharge(26, "", "", headCount, headCount);
				List<Discount> dicounts = new ArrayList<Discount>();
				invoice = new Invoice(1, datTo, dicounts, extraCharges, 0, 0, null, null, strStatus, "");
		
		//for customer
		String strName = "JOSELITO SANTOS";
		String strAddress = "189-Dr. Sixto Antonio Avenue, Rosario Pasig City";
		String strContactNo = "09361144842";
		String strEmailAddress = "santos.jeffrey0023@gmail.com";
		customer = new Customer(1, strName, strAddress, strContactNo, strEmailAddress, strEmailAddress, strEmailAddress);
		
		//for ReservationInlusion
		List<ProductOrder> products = new ArrayList<ProductOrder>();
		List<ReservedService> services = new ArrayList<ReservedService>();
		List<ReservedPackage> packages = new ArrayList<ReservedPackage>();
		List<ReservedPromo> promos = new ArrayList<ReservedPromo>();
		includedItems = new ReservationInclusion(products, services, packages, promos);
		
			//ProductOrder
			Product product = new Product(38, strEmailAddress, strEmailAddress, strEmailAddress, headCount, null, headCount, strEmailAddress, headCount);
			ProductOrder productOrder = new ProductOrder(1, product, 4,1);
			products.add(productOrder);
			
			//Service
			Service service1 = Service.createNullService(61);
			ReservedService reserved = new ReservedService(1, 1, service1, 3, 1);
			services.add(reserved);
			
			//Package
			
			//Promo
		
		
		//for Employee Assigned
		Employee emp = new Employee(79, strEmailAddress, strEmailAddress, strEmailAddress, datTo, strEmailAddress, strEmailAddress, strEmailAddress, strEmailAddress, strEmailAddress, strEmailAddress, strEmailAddress, strEmailAddress, null, null, false, strEmailAddress);
		employeeAssigned.add(new EmployeeAssigned(1, 1, emp, headCount));
		employeeAssigned.add(new EmployeeAssigned(1, 1, emp, headCount));
		
		reservation = new Reservation(intReservationID, customer, includedItems, intReservationType, dateCreated, datFrom, datTo, timFrom, timTo, strVenue, headCount, headCount, employeeAssigned, invoice, strStatus, strEmailAddress);
		
		return reservation;
	}

}
