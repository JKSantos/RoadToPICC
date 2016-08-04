package com.gss.model;

import java.util.List;

public class PackageWalkIn {
	
	private int intPackageWalkInID;
	private Package packages;
	private List<ServiceWalkIn> serviceAssignment;
	
	public PackageWalkIn(int intPackageWalkInID, Package packages, List<ServiceWalkIn> serviceAssignment) {
		super();
		this.intPackageWalkInID = intPackageWalkInID;
		this.packages = packages;
		this.serviceAssignment = serviceAssignment;
	}
	
	public int getIntPackageWalkInID() {
		return intPackageWalkInID;
	}
	public void setIntPackageWalkInID(int intPackageWalkInID) {
		this.intPackageWalkInID = intPackageWalkInID;
	}
	public Package getPackages() {
		return packages;
	}
	public void setPackages(Package packages) {
		this.packages = packages;
	}
	public List<ServiceWalkIn> getServiceAssignment() {
		return serviceAssignment;
	}
	public void setServiceAssignment(List<ServiceWalkIn> serviceAssignment) {
		this.serviceAssignment = serviceAssignment;
	}
}
