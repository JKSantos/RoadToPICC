package com.gss.service;

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
		
		return repo.createDiscount(discount);
	}
	
	public boolean updateDiscount(Discount discount){
	
		DiscountRepository repo = new DiscountJDBCRepository();
		
		return repo.updateDiscount(discount);
	}

	@Override
	public boolean deactivateDiscount(int intDiscountID) {

		DiscountRepository repo = new DiscountJDBCRepository();
		
		return repo.deactivateDiscount(intDiscountID);
	}
}
