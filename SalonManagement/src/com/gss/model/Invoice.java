package com.gss.model;

import java.util.Date;
import java.util.List;

public class Invoice {
	
	private int intInvoiceID;
	private Date datInvoiceDate;
	private String strInvoiceTime;
	private List<Discount> discountList;
	private List<ExtraCharge> extraChargeList;
	private double dblTotalPrice;
	private double dblRemainingBalance;
	private List<Payment> paymentList;
	private String paymentStatus;

	public Invoice(int intInvoiceID, Date datInvoiceDate, String strInvoiceTime, List<Discount> discountList,
			List<ExtraCharge> extraChargeList, double dblTotalPrice, double dblRemainingBalance,
			List<Payment> paymentList, String paymentStatus) {
		super();
		this.intInvoiceID = intInvoiceID;
		this.datInvoiceDate = datInvoiceDate;
		this.strInvoiceTime = strInvoiceTime;
		this.discountList = discountList;
		this.extraChargeList = extraChargeList;
		this.dblTotalPrice = dblTotalPrice;
		this.dblRemainingBalance = dblRemainingBalance;
		this.paymentList = paymentList;
		this.paymentStatus = paymentStatus;
	}

	public int getIntInvoiceID() {
		return intInvoiceID;
	}

	public void setIntInvoiceID(int intInvoiceID) {
		this.intInvoiceID = intInvoiceID;
	}

	public Date getDatInvoiceDate() {
		return datInvoiceDate;
	}

	public void setDatInvoiceDate(Date datInvoiceDate) {
		this.datInvoiceDate = datInvoiceDate;
	}

	public String getStrInvoiceTime() {
		return strInvoiceTime;
	}

	public void setStrInvoiceTime(String strInvoiceTime) {
		this.strInvoiceTime = strInvoiceTime;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public List<Discount> getDiscountList() {
		return discountList;
	}

	public void setDiscountList(List<Discount> discountList) {
		this.discountList = discountList;
	}

	public List<ExtraCharge> getExtraChargeList() {
		return extraChargeList;
	}

	public void setExtraChargeList(List<ExtraCharge> extraChargeList) {
		this.extraChargeList = extraChargeList;
	}

	public double getDblTotalPrice() {
		return dblTotalPrice;
	}

	public void setDblTotalPrice(double dblTotalPrice) {
		this.dblTotalPrice = dblTotalPrice;
	}

	public double getDblRemainingBalance() {
		return dblRemainingBalance;
	}

	public void setDblRemainingBalance(double dblRemainingBalance) {
		this.dblRemainingBalance = dblRemainingBalance;
	}

	public List<Payment> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<Payment> paymentList) {
		this.paymentList = paymentList;
	}
	
	public static Invoice createNullInvoice(List<ExtraCharge> extraCharges, List<Discount> discounts){
		return new Invoice(1, new Date(), "", discounts, extraCharges, 0, 0, null, "");
	}
}
