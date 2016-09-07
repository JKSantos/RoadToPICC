package com.gss.service;

import java.sql.SQLException;
import java.util.List;

import com.gss.dao.PromoJDBCRepository;
import com.gss.dao.PromoRepository;
import com.gss.model.Promo;

public class PromoServiceImpl implements PromoService{

	public boolean createPromo(Promo promo) {
		
		PromoRepository repo = new PromoJDBCRepository();
		
		return repo.createPromo(promo);
	}

	public boolean updatePromo(Promo promo) {
		
		PromoRepository repo = new PromoJDBCRepository();
		
		return repo.updatePromo(promo);
	}

	public List<Promo> getAllPromo() {
		
		PromoRepository repo = new PromoJDBCRepository();
		
		return repo.getAllPromo();
	}

	@Override
	public boolean deactivatePromo(int promoID) {
		
		PromoRepository repo = new PromoJDBCRepository();
		
		return repo.deactivatePromo(promoID);
	}

	@Override
	public List<Promo> getAllPromoNoDetails() {

		PromoRepository repo = new PromoJDBCRepository();
		
		return repo.getAllPromoNoDetails();
	}

	@Override
	public Promo getPromoByID(int promoID) {
		
		PromoRepository repo = new PromoJDBCRepository();
		
		return repo.getPromoByID(promoID);
	}

	@Override
	public List<Promo> queryAllPromo() {

		PromoRepository repo = new PromoJDBCRepository();
		
		return repo.queryAllPromo();
	}

	@Override
	public void checkExpiredPomo() throws SQLException {

		PromoRepository repo = new PromoJDBCRepository();
		
		repo.checkExpiredPomo();
	}

}
