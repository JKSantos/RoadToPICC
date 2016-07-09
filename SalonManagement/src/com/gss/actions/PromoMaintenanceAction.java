package com.gss.actions;

import java.util.ArrayList;
import java.util.List;

import com.gss.model.Product;
import com.gss.model.Promo;
import com.gss.model.Service;
import com.gss.model.Package;
import com.gss.model.PackagePackage;
import com.gss.service.PackageService;
import com.gss.service.PackageServiceImpl;
import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;
import com.gss.service.PromoService;
import com.gss.service.PromoServiceImpl;
import com.gss.service.ServiceService;
import com.gss.service.ServiceServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class PromoMaintenanceAction extends ActionSupport {
	
	private List<Product> productList;
	private List<Service> serviceList;
	private List<Promo> promoList;
	private List<Package> packageList;
	
	public String execute(){
		
		ProductService product = new ProductServiceImpl();
		ServiceService service = new ServiceServiceImpl();
		PromoService promo = new PromoServiceImpl();
		PackageService servicePack = new PackageServiceImpl();
		
		this.setPackageList(servicePack.getAllPackage());
		this.productList = product.getAllProducts();
		this.serviceList = service.getAllService();
		this.promoList = promo.getAllPromo();
		
		return SUCCESS;
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
