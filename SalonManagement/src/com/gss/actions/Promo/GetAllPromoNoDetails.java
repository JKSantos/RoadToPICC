package com.gss.actions.Promo;

import java.util.List;

import com.gss.model.Promo;

public class GetAllPromoNoDetails {
	
	
	private List<Promo> promoList;
	private String result = "success";
	
	public String execute(){
		
		this.promoList = Promo.getAllPromoNoDetails();
		
		return result;
	}

	public List<Promo> getPromoList() {
		return promoList;
	}

	public String getResult() {
		return result;
	}

}
