package com.gss.testers;

import com.gss.model.Product;
import com.gss.service.ProductServiceImpl;

public class CreateProduct {
	
	public static void main(String[] args){
		
		Product product = new Product(1, "Grips Wax 150mL", "Hair Wax", "For styling your hair", 0, null, 90.00, "resource/jeff.jpg");
		
		ProductServiceImpl prodService = new ProductServiceImpl();
		
		if(prodService.createProduct(product) == true)
			System.out.println("success");
		else
			System.out.println("failed");
	}
}
