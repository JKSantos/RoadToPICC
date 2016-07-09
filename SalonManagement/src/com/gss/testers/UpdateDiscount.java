package com.gss.testers;

import com.gss.model.Discount;
import com.gss.service.DiscountServiceImpl;

public class UpdateDiscount {
	
	public static void main(String[] args){
		
		DiscountServiceImpl service = new DiscountServiceImpl();
		Discount discount = new Discount(1, "Senior", "For senior citizens", 1, 20);
		
		if(service.updateDiscount(discount) == true)
			System.out.println("success");
		else
			System.out.println("failed");
		
	}

}
