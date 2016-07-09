package com.gss.service;

import java.util.List;

import com.gss.model.Promo;

public interface PromoService {
	
	public boolean createPromo(Promo promo);

	public boolean updatePromo(Promo promo);

	public List<Promo> getAllPromo();
	
	public boolean deactivatePromo(int promoID);

}
