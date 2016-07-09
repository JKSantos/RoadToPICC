package com.gss.actions;

import java.util.List;

import com.gss.model.Product;
import com.gss.model.Promo;
import com.gss.model.Service;
import com.gss.model.Package;
import com.gss.service.PackageService;
import com.gss.service.PackageServiceImpl;
import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;
import com.gss.service.PromoService;
import com.gss.service.PromoServiceImpl;
import com.gss.service.ServiceService;
import com.gss.service.ServiceServiceImpl;

public class TransactionReservationAction {
	
	
	private List<Product> productList;
	private List<Service> serviceList;
	private List<Promo> promoList;
	private List<Package> packageList;
	
	public String execute(){
		
		ProductService productService = new ProductServiceImpl();
		ServiceService serviceService = new ServiceServiceImpl();
		PromoService promoService = new PromoServiceImpl();
		PackageService packageService = new PackageServiceImpl();
		
		this.setProductList(productService.getAllProducts());
		this.setServiceList(serviceService.getAllService());
		this.setPromoList(promoService.getAllPromo());
		this.setPackageList(packageService.getAllPackage());
		
		return "success";
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public List<Service> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<Service> serviceList) {
		this.serviceList = serviceList;
	}

	public List<Promo> getPromoList() {
		return promoList;
	}

	public void setPromoList(List<Promo> promoList) {
		this.promoList = promoList;
	}

	public List<Package> getPackageList() {
		return packageList;
	}

	public void setPackageList(List<Package> packageList) {
		this.packageList = packageList;
	}
}
