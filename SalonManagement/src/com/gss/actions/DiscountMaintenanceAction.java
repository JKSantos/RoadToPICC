package com.gss.actions;

import java.util.List;

import com.gss.model.Discount;
import com.gss.model.Product;
import com.gss.model.Promo;
import com.gss.model.Package;
import com.gss.model.Service;
import com.gss.service.DiscountService;
import com.gss.service.DiscountServiceImpl;

public class DiscountMaintenanceAction {


	private List<Discount> discountList;
	private List<Product> productList;
	private List<Service> serviceList;
	private List<Package> packageList;
	private List<Promo> promoList;

	public String execute(){
		
		this.productList = Product.getAllProduct();
		this.serviceList = Service.getAllService();
		this.packageList = Package.getAllPackage();
		this.promoList = Promo.getAllPromo();
		
		DiscountService discountService = new DiscountServiceImpl();
		this.setDiscountList(discountService.getAllDiscount());
		return "success";
	}


	public List<Discount> getDiscountList() {
		return discountList;
	}


	public void setDiscountList(List<Discount> discountList) {
		this.discountList = discountList;
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
	
	

}
