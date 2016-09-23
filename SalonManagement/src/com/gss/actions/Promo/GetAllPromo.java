package com.gss.actions.Promo;

import java.sql.SQLException;
import java.util.List;

import org.quartz.SchedulerException;

import com.gss.model.Promo;
import com.gss.service.PromoService;
import com.gss.service.PromoServiceImpl;

public class GetAllPromo {
	
	private List<Promo> promoList;
	
	public String execute() throws SQLException{
		Promo.checkExpiredPromo();
		PromoService service = new PromoServiceImpl();
		this.promoList = service.getAllPromo();
		
		
		return "success";
	}

	public List<Promo> getPromoList() {
		return promoList;
	}
}
