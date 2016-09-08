package com.gss.actions.Promo;

import java.util.List;

import org.quartz.SchedulerException;

import com.gss.model.Promo;
import com.gss.service.PromoService;
import com.gss.service.PromoServiceImpl;
import com.gss.testers.PromoChecker;

public class GetAllPromo {
	
	private List<Promo> promoList;
	
	public String execute() throws SchedulerException{
		
		PromoChecker.check();
		PromoService service = new PromoServiceImpl();
		this.promoList = service.getAllPromo();
		
		return "success";
	}

	public List<Promo> getPromoList() {
		return promoList;
	}
}
