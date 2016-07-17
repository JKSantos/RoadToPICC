package com.gss.utilities;

import java.util.ArrayList;
import java.util.List;

import com.gss.model.Promo;

public class SearchPromo {
	
	public List<Promo> searchList(String[] strPromoID, List<Promo> promoList){
		
		List<Promo> selectedPromo = new ArrayList<Promo>();
		
		for(int intCtr = 0; intCtr < strPromoID.length; intCtr++){
			
			for(int intCtr2 = 0; intCtr2 < promoList.size(); intCtr2++){
				Promo promo = promoList.get(intCtr2);
				
				if(Integer.parseInt(strPromoID[intCtr]) == promo.getIntPromoID())
					selectedPromo.add(promo);
			}
		}
		
		return selectedPromo;
	}

}
