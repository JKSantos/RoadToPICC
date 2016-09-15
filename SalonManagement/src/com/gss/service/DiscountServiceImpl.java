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
	
	public String createDiscount(Discount discount) throws SQLException{
		
		DiscountRepository repo = new DiscountJDBCRepository();
		
		return repo.createDiscount(discount);
	}
	
	public String updateDiscount(Discount discount) throws SQLException{
	
		DiscountRepository repo = new DiscountJDBCRepository();
		
		return repo.updateDiscount(discount);
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

	@Override
	public List<Discount> queryAllDiscount() {

		DiscountRepository repo = new DiscountJDBCRepository();
		
		return repo.queryAllDiscount();
	}
}
