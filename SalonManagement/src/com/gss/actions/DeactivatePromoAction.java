package com.gss.actions;

import com.gss.service.PromoService;
import com.gss.service.PromoServiceImpl;

public class DeactivatePromoAction {
	
	private int intPromoID;
	
	public String execute(){
			
		PromoService service = new PromoServiceImpl();
		boolean result = service.deactivatePromo(intPromoID);
		
		if(result == true)
			return "success";
		else
			return "failed";
	}

	public int getIntPromoID() {
		return intPromoID;
	}

	public void setIntPromoID(int intPromoID) {
		this.intPromoID = intPromoID;
	}

}
