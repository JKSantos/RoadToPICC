package com.gss.actions.Promo;

import java.util.List;

import com.gss.model.Promo;

public class QueryAllPromo {
	
	private List<Promo> promoList;
	
	public String execute(){
		
		this.promoList = Promo.queryAllPromo();
		
		return "success";
	}
	
	public List<Promo> getPromoList(){
		return this.promoList;
	}

}
