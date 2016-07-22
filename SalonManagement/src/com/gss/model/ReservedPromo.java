package com.gss.model;

public class ReservedPromo {
	
	private int intReservedPromoID;
	private int intReservationID;
	private Promo promo;
	private int intQuantity;
	private String discountType;
	private double discountAmount;
	private int intStatus;
	
	public ReservedPromo(int intReservedPromoID, int intReservationID, Promo promo, int intQuantity, int intStatus) {
		super();
		this.intReservedPromoID = intReservedPromoID;
		this.intReservationID = intReservationID;
		this.promo = promo;
		this.intQuantity = intQuantity;
		this.intStatus = intStatus;
	}

	public int getIntReservedPromoID() {
		return intReservedPromoID;
	}

	public void setIntReservedPromoID(int intReservedPromoID) {
		this.intReservedPromoID = intReservedPromoID;
	}

	public int getIntReservationID() {
		return intReservationID;
	}

	public void setIntReservationID(int intReservationID) {
		this.intReservationID = intReservationID;
	}

	public Promo getPromo() {
		return promo;
	}

	public void setPromo(Promo promo) {
		this.promo = promo;
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
