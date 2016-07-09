package com.gss.actions;

import java.util.List;

import com.gss.model.Discount;
import com.gss.service.DiscountService;
import com.gss.service.DiscountServiceImpl;

public class DiscountMaintenanceAction {


	private List<Discount> discountList;


	public String execute(){

		DiscountService discountService = new DiscountServiceImpl();
		this.setDiscountList(discountService.getAllDiscount());
		return "success";
	}


	public List<Discount> getDiscountList() {
		return discountList;
	}


	public void setDiscountList(List<Discount> discountList) {
		this.discountList = discountList;
	}

}
