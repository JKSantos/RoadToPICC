package com.gss.model;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

public class PackageDetails {
	
	private int intPackageID;
	private List<ServiceDetails> serviceList;
	
	public PackageDetails(int intPackageID, List<ServiceDetails> serviceList) {
		super();
		this.intPackageID = intPackageID;
		this.serviceList = serviceList;
	}
	public int getIntPackageID() {
		return intPackageID;
	}
	public void setIntPackageID(int intPackageID) {
		this.intPackageID = intPackageID;
	}
	public List<ServiceDetails> getServiceList() {
		return serviceList;
	}
	public void setServiceList(List<ServiceDetails> serviceList) {
		this.serviceList = serviceList;
	}
	public static List<PackageDetails> fromJSON(JSONObject jsonList) {
		List<PackageDetails> packageList = new ArrayList<PackageDetails>();
		
		System.out.println("-----------");		
		return packageList;
		
	}	
	
	
}
