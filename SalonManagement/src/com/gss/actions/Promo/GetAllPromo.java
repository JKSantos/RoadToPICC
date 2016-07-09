package com.gss.actions.Promo;

import java.util.List;

import com.gss.model.Promo;
import com.gss.service.PromoService;
import com.gss.service.PromoServiceImpl;

public class GetAllPromo {
	
	private List<Promo> promoList;
	
	public String execute(){
		
		PromoService service = new PromoServiceImpl();
		this.promoList = service.getAllPromo();
		
		return "success";
	}

	public List<Promo> getPromoList() {
		return promoList;
	}
}
