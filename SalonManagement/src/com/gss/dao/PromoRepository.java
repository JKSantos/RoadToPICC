package com.gss.dao;

import java.sql.SQLException;
import java.util.List;

import com.gss.model.Promo;

public interface PromoRepository {
	
	public boolean createPromo(Promo promo);
	
	public boolean updatePromo(Promo promo);
	
	public List<Promo> getAllPromo();
	
	public boolean deactivatePromo(int promoID);
	
	public List<Promo> getAllPromoNoDetails();

	public Promo getPromoByID(int promoID);
	
	public List<Promo> queryAllPromo();
	
	public void checkExpiredPomo() throws SQLException;
}
