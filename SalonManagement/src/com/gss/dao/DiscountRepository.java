package com.gss.dao;

import java.sql.SQLException;
import java.util.List;

import com.gss.model.Discount;

public interface DiscountRepository {
	
	public List<Discount> getAllDiscount();
	
	public String createDiscount(Discount discount) throws SQLException ;
	
	public String updateDiscount(Discount discount) throws SQLException ;
	
	public boolean deactivateDiscount(int intDiscountID);
	
	public List<Discount> getAllDiscountNoDetails();
	
	public Discount getDiscountByID(int discountID);

	public List<Discount> queryAllDiscount();
}
