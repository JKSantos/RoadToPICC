package com.gss.utilities;

import java.util.ArrayList;
import java.util.List;

import com.gss.model.Product;
import com.gss.model.ProductOrder;

public class ProductOrderConverter {
	
	public static List<ProductOrder> convertToProductObject(String[] selectedProduct, String[] productQuantity, List<Product> productList ){
		
		List<ProductOrder> orderList = new ArrayList<ProductOrder>();
		
		for(int i = 0; i < selectedProduct.length; i++){
			
			for(int j = 0; j < productList.size(); j++){
				if(selectedProduct[i].equals(String.valueOf(productList.get(j).getIntProductID()))){
					Product product = productList.get(j);
					ProductOrder order = new ProductOrder(1, product, Integer.parseInt(productQuantity[i]), 1);
					orderList.add(order);
				}
			}
		}
		
		return orderList;
	}

}
