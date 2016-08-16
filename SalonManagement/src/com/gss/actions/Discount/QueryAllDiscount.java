package com.gss.actions.Discount;

import java.util.List;

import com.gss.model.Discount;

public class QueryAllDiscount {
	
	private List<Discount> discountList;
	
	public String execute(){
		
		this.discountList = Discount.queryAllDiscount();
		
		return "success";
	}
	
	public List<Discount> getDiscountList(){
		return this.discountList;
	}

}
