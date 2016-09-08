package com.gss.actions.Promo;

import java.util.List;

import org.quartz.SchedulerException;

import com.gss.model.Promo;
import com.gss.testers.PromoChecker;

public class GetAllPromoNoDetails {
	
	
	private List<Promo> promoList;
	private String result = "success";
	
	public String execute() throws SchedulerException{
		
		PromoChecker.check();
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
