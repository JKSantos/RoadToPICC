package com.gss.actions;
import java.util.List;

import com.gss.model.Product;
import com.gss.model.Service;
import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;
import com.gss.service.ServiceService;
import com.gss.service.ServiceServiceImpl;

public class ReservationTransactionAction {

	private List<Product> productList;
	private List<Service> serviceList;
	private List<String> productCategory;
	private List<String> serviceCategory;

	public String execute(){
		
		ProductService productService = new ProductServiceImpl();
		ServiceService serviceService = new ServiceServiceImpl();
		
		this.productList = productService.getAllProducts();
		this.serviceList = serviceService.getAllService();
		this.productCategory = productService.getAllCategory();
		this.serviceCategory = serviceService.getAllCategory();
		
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

	public List<String> getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(List<String> productCategory) {
		this.productCategory = productCategory;
	}

	public List<String> getServiceCategory() {
		return serviceCategory;
	}

	public void setServiceCategory(List<String> serviceCategory) {
		this.serviceCategory = serviceCategory;
	}
	
	
}

