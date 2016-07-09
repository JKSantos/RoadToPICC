package com.gss.model;

import java.util.List;

public class PackageWalkIn {
	
	private int intPackageWalkInID;
	private Package packages;
	private List<ServiceAssignment> employees;
	
	public PackageWalkIn(int intPackageWalkInID, Package packages, List<ServiceAssignment> employees){
		
		this.intPackageWalkInID = intPackageWalkInID;
		this.packages = packages;
		this.employees = employees;
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

	public List<ServiceAssignment> getEmployees() {
		return employees;
	}

	public void setEmployees(List<ServiceAssignment> employees) {
		this.employees = employees;
	}
}
