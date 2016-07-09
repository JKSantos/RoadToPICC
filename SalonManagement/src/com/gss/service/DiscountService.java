package com.gss.service;

import java.util.List;
import com.gss.model.Discount;

public interface DiscountService {
	
	public List<Discount> getAllDiscount();
	
	public boolean createDiscount(Discount discount);
	
	public boolean updateDiscount(Discount discount);
	
	public boolean deactivateDiscount(int intDiscountID);
	
	
}
