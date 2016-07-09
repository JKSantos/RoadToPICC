package com.gss.model;

import com.gss.model.Package;

public class PackagePackage {
	
	private int intPackagePackageID;
	private int intPackageID;
	private Package pack;
	private int intPackageQuantity;
	private int intStatus;
	
	public PackagePackage(int intPackagePackageID,int intPackageID, Package pack, int intPackageQuantity, int intStatus){
		
		this.intPackagePackageID = intPackagePackageID;
		this.intPackageID = intPackageID;
		this.pack = pack;
		this.intPackageQuantity = intPackageQuantity;
		this.intStatus = intStatus;
		
	}

	public int getIntPackagePackageID() {
		return intPackagePackageID;
	}

	public void setIntPackagePackageID(int intPackagePackageID) {
		this.intPackagePackageID = intPackagePackageID;
	}

	public int getIntPackageID() {
		return intPackageID;
	}

	public void setIntPackageID(int intPackageID) {
		this.intPackageID = intPackageID;
	}

	public Package getPack() {
		return pack;
	}

	public void setPack(Package pack) {
		this.pack = pack;
	}

	public int getIntPackageQuantity() {
		return intPackageQuantity;
	}

	public void setIntPackageQuantity(int intPackageQuantity) {
		this.intPackageQuantity = intPackageQuantity;
	}

	public int getIntStatus() {
		return intStatus;
	}

	public void setIntStatus(int intStatus) {
		this.intStatus = intStatus;
	}
	
	

}
