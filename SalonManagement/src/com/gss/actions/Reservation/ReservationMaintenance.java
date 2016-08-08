package com.gss.actions.Reservation;

import java.util.List;

import com.gss.model.*;
import com.gss.model.Package;

public class ReservationMaintenance {
	
	private List<Product> productList;
	private List<Service> serviceList;
	private List<Package> packageList;
	private List<Promo> promoList;
	private List<Employee> employeeList;
	private List<Discount> discountList;
	private List<ExtraCharge> extraChargeList;
	private List<Reservation> reservationList;
	//private List<Customer> customerList;
	
	public String execute(){
		
//		this.productList = Product.getAllProduct();
//		this.serviceList = Service.getAllService();
//		this.packageList = Package.getAllPackage();
//		this.promoList = Promo.getAllPromo();
//		this.employeeList = Employee.getEmployeeList();
//		this.discountList = Discount.getAllDiscount();
//		this.extraChargeList = ExtraCharge.getAllExtraCharge();
//		this.reservationList = Reservation.getAllReservation();
		
		return "success";
	}

	public List<Product> getProductList() {
		return productList;
	}

	public List<Service> getServiceList() {
		return serviceList;
	}

	public List<Package> getPackageList() {
		return packageList;
	}

	public List<Promo> getPromoList() {
		return promoList;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public List<Discount> getDiscountList() {
		return discountList;
	}

	public List<ExtraCharge> getExtraChargeList() {
		return extraChargeList;
	}
}
