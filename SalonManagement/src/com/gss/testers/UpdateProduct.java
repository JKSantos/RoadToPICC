package com.gss.testers;

import com.gss.model.Product;
import com.gss.service.ProductServiceImpl;

public class UpdateProduct {
	
	public static void main(String[] args){
		
		Product product = new Product(7, "GATSBY 150ML", "Hair Wax", "To be HANDSOME YEAH", 0, null, 90.00, "resource/jeff.jpg");
		ProductServiceImpl service = new ProductServiceImpl();
		
		if(service.updateProduct(product) == true)
			System.out.println("success");
		else
			System.out.println("failed");
	}
}
