package com.gss.testers;

import java.util.List;

import com.gss.model.Discount;
import com.gss.service.DiscountServiceImpl;

public class GetAllDiscount {
	
	public static void main(String[] args){
		
		DiscountServiceImpl service = new DiscountServiceImpl();
		List<Discount> discountList = service.getAllDiscount();
		
		if(discountList != null){
			System.out.println("Available Discount:");
			
			for(int i = 0; i < discountList.size(); i++){
				Discount discount = discountList.get(i);
				String type = null;
				
				if(discount.getIntDiscountType() == 1)
					System.out.println(discount.getIntDiscountID() + "\t" + discount.getStrDiscountName() + "\t" + discount.getDblDiscountAmount() + "%");
				else
					System.out.println(discount.getIntDiscountID() + "\t" + discount.getStrDiscountName() + "\tP " + discount.getDblDiscountAmount());
			}
		}
		else
			System.out.println("failed");
		
	}

}
