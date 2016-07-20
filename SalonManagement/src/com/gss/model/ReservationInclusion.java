package com.gss.model;

import java.util.List;

public class ReservationInclusion {
	
	private List<ProductOrder> productList;
	private List<ReservedService> serviceList;
	private List<ReservedPackage> packageList;
	private List<ReservedPromo> promoList;

	public ReservationInclusion(List<ProductOrder> productList, List<ReservedService> serviceList,
			List<ReservedPackage> packageList, List<ReservedPromo> promoList) {
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
	public List<ReservedService> getServiceList() {
		return serviceList;
	}
	public void setServiceList(List<ReservedService> serviceList) {
		this.serviceList = serviceList;
	}
	public List<ReservedPackage> getPackageList() {
		return packageList;
	}
	public void setPackageList(List<ReservedPackage> packageList) {
		this.packageList = packageList;
	}
	public List<ReservedPromo> getPromoList() {
		return promoList;
	}
	public void setPromoList(List<ReservedPromo> promoList) {
		this.promoList = promoList;
	}
	
	
}
