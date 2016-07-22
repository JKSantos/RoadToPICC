package com.gss.model;

public class ReservedService {
	
	private int intReservedServiceID;
	private int intReservationID;
	private Service service;
	private int intQuantity;
	private String discountType;
	private double discountAmount;
	private int intStatus;
	
	public ReservedService(int intReservedServiceID, int intReservationID, Service service, int intQuantity, int intStatus){
		this.setIntReservedServiceID(intReservedServiceID);
		this.setIntReservationID(intReservationID);
		this.setService(service);
		this.setIntQuantity(intQuantity);
		this.setIntStatus(intStatus);
	}

	public int getIntReservedServiceID() {
		return intReservedServiceID;
	}

	public void setIntReservedServiceID(int intReservedServiceID) {
		this.intReservedServiceID = intReservedServiceID;
	}

	public int getIntReservationID() {
		return intReservationID;
	}

	public void setIntReservationID(int intReservationID) {
		this.intReservationID = intReservationID;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
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
