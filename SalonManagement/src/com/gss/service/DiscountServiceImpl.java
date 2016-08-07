package com.gss.service;

import java.sql.SQLException;
import java.util.List;

import com.gss.dao.DiscountJDBCRepository;
import com.gss.dao.DiscountRepository;
import com.gss.model.Discount;

public class DiscountServiceImpl implements DiscountService{

	public List<Discount> getAllDiscount(){
		
		DiscountRepository repo = new DiscountJDBCRepository();
		
		return repo.getAllDiscount();
	}
	
	public boolean createDiscount(Discount discount){
		
		DiscountRepository repo = new DiscountJDBCRepository();
		
		try {
			return repo.createDiscount(discount);
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateDiscount(Discount discount){
	
		DiscountRepository repo = new DiscountJDBCRepository();
		
		try {
			return repo.updateDiscount(discount);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deactivateDiscount(int intDiscountID) {

		DiscountRepository repo = new DiscountJDBCRepository();
		
		return repo.deactivateDiscount(intDiscountID);
	}

	@Override
	public List<Discount> getAllDiscountNoDetails() {

		DiscountRepository repo = new DiscountJDBCRepository();
		
		return repo.getAllDiscountNoDetails();
	}

	@Override
	public Discount getDiscountByID(int discountID) {

		DiscountRepository repo = new DiscountJDBCRepository();
		
		return repo.getDiscountByID(discountID);
	}
}
