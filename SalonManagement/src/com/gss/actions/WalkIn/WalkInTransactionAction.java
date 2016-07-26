package com.gss.actions.WalkIn;

import java.util.List;

import com.gss.model.Discount;
import com.gss.model.Employee;
import com.gss.model.ExtraCharge;
import com.gss.model.Package;
import com.gss.model.Product;
import com.gss.model.Promo;
import com.gss.model.Reservation;
import com.gss.model.Service;

public class WalkInTransactionAction {
	
	private List<Product> productList;
	private List<Service> serviceList;
	private List<Package> packageList;
	private List<Promo> promoList;
	private List<Employee> employeeList;
	private List<Discount> discountList;
	private List<ExtraCharge> extraChargeList;
	
	public String execute(){
		
		this.productList = Product.getAllProduct();
		this.serviceList = Service.getAllService();
		this.packageList = Package.getAllPackage();
		this.promoList = Promo.getAllPromo();
		this.employeeList = Employee.getEmployeeList();
		this.discountList = Discount.getAllDiscount();
		this.extraChargeList = ExtraCharge.getAllExtraCharge();
		
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
