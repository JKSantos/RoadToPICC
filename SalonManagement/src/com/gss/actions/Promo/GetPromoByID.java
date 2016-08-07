package com.gss.actions.Promo;

import com.gss.model.Promo;

public class GetPromoByID {
	
	private Promo promo;
	private int intPromoID;
	private String result = "success";
	
	public String execute(){
		
		this.promo = Promo.getPromoByID(intPromoID);
		
		return result;
	}

	public Promo getPromo() {
		return promo;
	}

	public String getResult() {
		return result;
	}

	public void setIntPromoID(int intPromoID) {
		this.intPromoID = intPromoID;
	}
}
