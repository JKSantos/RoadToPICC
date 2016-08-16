package com.gss.model;

import java.util.List;

public class PromoDetails {
	
	private int intPromoID;
	private List<ServiceDetails> serviceList;
	private List<PackageDetails> packageList;

	public PromoDetails(int intPromoID, List<ServiceDetails> serviceList, List<PackageDetails> packageList) {
		super();
		this.intPromoID = intPromoID;
		this.serviceList = serviceList;
		this.packageList = packageList;
	}
	public int getIntPromoID() {
		return intPromoID;
	}
	public void setIntPromoID(int intPromoID) {
		this.intPromoID = intPromoID;
	}
	public List<ServiceDetails> getServiceList() {
		return serviceList;
	}
	public void setServiceList(List<ServiceDetails> serviceList) {
		this.serviceList = serviceList;
	}
	public List<PackageDetails> getPackageList() {
		return packageList;
	}
	public void setPackageList(List<PackageDetails> packageList) {
		this.packageList = packageList;
	}
	

}
