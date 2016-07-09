package com.gss.dao;

import java.util.List;

import com.gss.model.Discount;

public interface DiscountRepository {
	
	public List<Discount> getAllDiscount();
	
	public boolean createDiscount(Discount discount);
	
	public boolean updateDiscount(Discount discount);
	
	public boolean deactivateDiscount(int intDiscountID);
}
