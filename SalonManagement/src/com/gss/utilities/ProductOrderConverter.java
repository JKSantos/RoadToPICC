package com.gss.utilities;

import java.util.ArrayList;
import java.util.List;

import com.gss.model.Product;
import com.gss.model.ProductOrder;

public class ProductOrderConverter {
	
	public static List<ProductOrder> convertToProductObject(String[] selectedProduct, String[] productQuantity, List<Product> productList ){
		
		List<ProductOrder> orderList = new ArrayList<ProductOrder>();
		
		for(int i = 0; i < productList.size(); i++){
			if(selectedProduct[i].equals(String.valueOf(productList.get(i).getIntProductID()))){
				Product product = productList.get(i);
				ProductOrder order = new ProductOrder(1, product, Integer.parseInt(productQuantity[i]), 1);
				orderList.add(order);
			}
		}
		
		return orderList;
	}

}
