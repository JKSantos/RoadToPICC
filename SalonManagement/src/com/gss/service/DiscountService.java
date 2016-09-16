package com.gss.service;

import java.sql.SQLException;
import java.util.List;
import com.gss.model.Discount;

public interface DiscountService {
	
	public List<Discount> getAllDiscount();
	
	public String createDiscount(Discount discount) throws SQLException;
	
	public String updateDiscount(Discount discount) throws SQLException;
	
	public boolean deactivateDiscount(int intDiscountID);
	
	public List<Discount> getAllDiscountNoDetails();
	
	public Discount getDiscountByID(int discountID);

	public List<Discount> queryAllDiscount();
	
}
