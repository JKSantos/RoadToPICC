package com.gss.model;

import java.util.Date;
import java.util.List;

public class Invoice {
	
	private int intInvoiceID;
	private Date datInvoiceDate;
	private List<Discount> discountList;
	private List<ExtraCharge> extraChargeList;
	private double dblTotalPrice;
	private double dblRemainingBalance;
	private List<Payment> paymentList;
	private String paymentStatus;

	public Invoice(int intInvoiceID, Date datInvoiceDate, List<Discount> discountList,
			List<ExtraCharge> extraChargeList, double dblTotalPrice, double dblRemainingBalance,
			List<Payment> paymentList, String paymentStatus) {
		super();
		this.intInvoiceID = intInvoiceID;
		this.datInvoiceDate = datInvoiceDate;
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
		return new Invoice(1, new Date(), discounts, extraCharges, 0, 0, null, "");
	}
	
	public static String convertToString(int intPaymentStatus){
		
		String converted = "";
		
		switch(intPaymentStatus){
			case 0:
				converted = "UNPAID";
				break;
			case 1:
				converted = "INCOMPLETE";
				break;
			case 2:
				converted = "PAID";
				break;
		}
		
		return converted;
	}
	
	public static double computeTotalAmount(List<ProductOrder> productList, List<ReservedService> serviceList,List<ReservedPackage> packageList, List<ReservedPromo> promoList, List<ExtraCharge> extraList){
		
		double productPrice = 0;
		double servicePrice = 0;
		double packagePrice = 0;
		double promoPrice = 0;
		double extraPrice = 0;
		
		for(int index = 0; index < productList.size(); index++){
			double price = productList.get(index).getProduct().getDblProductPrice();
			double quantity = productList.get(index).getIntQuantity();
			
			productPrice +=  price * quantity;
		}
		
		for(int index = 0; index < serviceList.size(); index++){
			double price = serviceList.get(index).getService().getDblServicePrice();
			double quantity = serviceList.get(index).getIntQuantity();
			
			servicePrice +=  price * quantity;
		}

		for(int index = 0; index < packageList.size(); index++){
			double price = packageList.get(index).getPackages().getDblPackagePrice();
			double quantity = packageList.get(index).getIntQuantity();
			
			packagePrice +=  price * quantity;
		}

		for(int index = 0; index < promoList.size(); index++){
			double price = promoList.get(index).getPromo().getDblPromoPrice();
			double quantity = promoList.get(index).getIntQuantity();
			
			promoPrice +=  price * quantity;
		}
		
		for(int index = 0; index < extraList.size(); index++){
			double price = extraList.get(index).getDblECPrice();
			
			promoPrice +=  price;
		}
		
		return 0;
	}
	
	public static double getRemainingBalance(double totalAmount, List<Payment> paymentList){
		
		double remainingAmount = 0;
		double payment = 0;
		
		for(int i = 0; i < paymentList.size(); i++){
			payment += paymentList.get(i).getDblPaymentAmount();
		}
		
		if(payment > remainingAmount)
			return 0;
		else
			return remainingAmount - payment;
		
	}
}
