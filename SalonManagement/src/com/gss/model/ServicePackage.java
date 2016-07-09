package com.gss.model;

public class ServicePackage {
	
	private int intServicePackageID;
	private int intPackageID;
	private Service service;
	private int intQuantity;
	private int intStatus;
	
	public ServicePackage(int intServicePackageID, int intPackageID, Service service, int intQuantity, int intStatus){
		
		this.setIntServicePackageID(intServicePackageID);
		this.service = service;
		this.intStatus = intStatus;
		this.setIntQuantity(intQuantity);
		this.intPackageID = intPackageID;
		
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public int getIntStatus() {
		return intStatus;
	}

	public void setIntStatus(int intStatus) {
		this.intStatus = intStatus;
	}

	public int getIntServicePackageID() {
		return intServicePackageID;
	}

	public void setIntServicePackageID(int intServicePackageID) {
		this.intServicePackageID = intServicePackageID;
	}

	public int getIntQuantity() {
		return intQuantity;
	}

	public void setIntQuantity(int intQuantity) {
		this.intQuantity = intQuantity;
	}

	public int getIntPackageID() {
		return intPackageID;
	}

	public void setIntPackageID(int intPackageID) {
		this.intPackageID = intPackageID;
	}

}
