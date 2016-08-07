package com.gss.actions.Discount;

import java.util.List;

import com.gss.model.Discount;

public class GetAllDiscountNoDetails {
	
	private List<Discount> discountList;
	private String result = "success";
	
	public String execute(){
		
		this.discountList = Discount.getAllDiscountNoDetails();
		
		return result;
	}

	public List<Discount> getDiscountList() {
		return discountList;
	}

	public String getResult() {
		return result;
	}
}
