package com.gss.model;

public class ReservedPackage {
	
	private int intReservedPackageID;
	private int intReservationID;
	private Package packages;
	private int intQuantity;
	private String discountType;
	private double discountAmount;
	private int intStatus;
	
	public ReservedPackage(int intReservedPackageID, int intReservationID, Package packages, int intQuantity, int intStatus){
		this.setIntReservedPackageID(intReservedPackageID);
		this.setIntReservationID(intReservationID);
		this.setPackages(packages);
		this.setIntQuantity(intQuantity);
		this.setIntStatus(intStatus);
	}

	public int getIntReservedPackageID() {
		return intReservedPackageID;
	}

	public void setIntReservedPackageID(int intReservedPackageID) {
		this.intReservedPackageID = intReservedPackageID;
	}

	public int getIntReservationID() {
		return intReservationID;
	}

	public void setIntReservationID(int intReservationID) {
		this.intReservationID = intReservationID;
	}

	public Package getPackages() {
		return packages;
	}

	public void setPackages(Package packages) {
		this.packages = packages;
	}

	public int getIntQuantity() {
		return intQuantity;
	}

	public void setIntQuantity(int intQuantity) {
		this.intQuantity = intQuantity;
	}

	public int getIntStatus() {
		return intStatus;
	}

	public void setIntStatus(int intStatus) {
		this.intStatus = intStatus;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	
	
}
