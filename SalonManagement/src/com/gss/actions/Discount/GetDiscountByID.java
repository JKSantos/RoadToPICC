package com.gss.actions.Discount;

import com.gss.model.Discount;

public class GetDiscountByID {
	
	private int intDiscountID;
	private Discount discount;
	private String result = "success";
	
	public String execute(){
		
		this.discount = Discount.getDiscountByID(this.intDiscountID);
		
		return result;
	}

	public Discount getDiscount() {
		return discount;
	}

	public String getResult() {
		return result;
	}

	public void setIntDiscountID(int intDiscountID) {
		this.intDiscountID = intDiscountID;
	}

}
