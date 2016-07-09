package com.gss.testers;

import com.gss.model.Discount;
import com.gss.service.DiscountServiceImpl;

public class CreateDiscount {
	
	public static void main(String[] args){
		
		DiscountServiceImpl service = new DiscountServiceImpl();
		
		Discount discount = new Discount(1, "Senior", "Exclusive for senior citizens with valid senior id", 1, 20);
		
		if(service.createDiscount(discount) == true)
			System.out.println("success");
		else
			System.out.println("failed");
	}

}
