package com.gss.testers;

import java.util.List;

import com.gss.model.Product;
import com.gss.service.ProductServiceImpl;

public class GetAllProducts {
	
public static void main(String[] args){
		
		ProductServiceImpl service = new ProductServiceImpl();
		
		List<Product> productList = service.getAllProducts();
		
		if(productList != null){
			System.out.println("success");
			
			
			for(int i = 0; i < productList.size(); i++){
				Product service1 = productList.get(i);
				System.out.println(service1.getIntProductID() + " " + service1.getStrProductName() + "\t P " + service1.getDblProductPrice());
			}
		}
	}

}
