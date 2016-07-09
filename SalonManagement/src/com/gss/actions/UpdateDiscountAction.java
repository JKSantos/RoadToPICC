package com.gss.actions;

import com.gss.model.Discount;
import com.gss.service.DiscountServiceImpl;

public class UpdateDiscountAction {

	private int intDiscountID;
	private String strDiscountName;
	private String strDiscountDetails;
	private String strDiscountType;
	private Double dblDiscountPrice;

	public String execute(){

		DiscountServiceImpl service = new DiscountServiceImpl();
		Discount discount;
		String result = "failed";
		
		try{
			
				discount = new Discount(intDiscountID, strDiscountName, strDiscountDetails, Integer.parseInt(strDiscountType), dblDiscountPrice,1);
				
				if(service.updateDiscount(discount) == true)
					result = "success";
				
			return result;
		}
		catch(NullPointerException e){
			discount = new Discount(intDiscountID, strDiscountName, strDiscountDetails, 2, dblDiscountPrice, 1);

			if(service.updateDiscount(discount) == true)
				return "success";
			else
				return "failed";
		}
	}

	public String getStrDiscountName() {
		return strDiscountName;
	}

	public void setStrDiscountName(String strDiscountName) {
		this.strDiscountName = strDiscountName;
	}

	public String getStrDiscountDetails() {
		return strDiscountDetails;
	}

	public void setStrDiscountDetails(String strDiscountDetails) {
		this.strDiscountDetails = strDiscountDetails;
	}

	public Double getDblDiscountPrice() {
		return dblDiscountPrice;
	}

	public void setDblDiscountPrice(Double dblDiscountPrice) {
		this.dblDiscountPrice = dblDiscountPrice;
	}

	

	public String getStrDiscountType() {
		return strDiscountType;
	}

	public void setStrDiscountType(String strDiscountType) {
		this.strDiscountType = strDiscountType;
	}

	public int getIntDiscountID() {
		return intDiscountID;
	}

	public void setIntDiscountID(int intDiscountID) {
		this.intDiscountID = intDiscountID;
	}


}
