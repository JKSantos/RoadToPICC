package com.gss.actions.Promo;

import java.util.List;

import com.gss.dao.PromoJDBCRepository;
import com.gss.model.Promo;

public class GetPromoByType {

	private List<Promo> promoList;
	
	private String type;
	
	public String execute(){
		this.promoList = PromoJDBCRepository.getPromoByType(type);
		
		return "success";
	}

	public List<Promo> getPromoList() {
		return promoList;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
