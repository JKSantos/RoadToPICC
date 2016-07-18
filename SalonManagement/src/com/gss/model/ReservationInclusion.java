package com.gss.model;

import java.util.List;

public class ReservationInclusion {
	
	private List<ProductOrder> productList;
	private List<Service> serviceList;
	private List<Package> packageList;
	private List<Promo> promoList;
	
	public ReservationInclusion(List<ProductOrder> productList, List<Service> serviceList, List<Package> packageList,
			List<Promo> promoList) {
		super();
		this.productList = productList;
		this.serviceList = serviceList;
		this.packageList = packageList;
		this.promoList = promoList;
	}
	public List<ProductOrder> getProductList() {
		return productList;
	}
	public void setProductList(List<ProductOrder> productList) {
		this.productList = productList;
	}
	public List<Service> getServiceList() {
		return serviceList;
	}
	public void setServiceList(List<Service> serviceList) {
		this.serviceList = serviceList;
	}
	public List<Package> getPackageList() {
		return packageList;
	}
	public void setPackageList(List<Package> packageList) {
		this.packageList = packageList;
	}
	public List<Promo> getPromoList() {
		return promoList;
	}
	public void setPromoList(List<Promo> promoList) {
		this.promoList = promoList;
	}
}
