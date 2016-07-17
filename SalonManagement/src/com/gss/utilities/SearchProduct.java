package com.gss.utilities;

import java.util.ArrayList;
import java.util.List;

import com.gss.model.Product;
import com.gss.model.ProductPackage;

public class SearchProduct {

	public Product search(int intProductID, List<Product> productList){
		
		for(int intCtr = 0; intCtr < productList.size(); intCtr++){
			
			Product prod = productList.get(intCtr);
			
			if(intProductID == prod.getIntProductID())
				return prod;
		}
		
		return null;
		
	}
	
	public List<ProductPackage> compareProducts(List<ProductPackage> newSet, List<ProductPackage> oldSet){
		
		List<ProductPackage> deactivatedSet = new ArrayList<ProductPackage>();
		
		for(int outer = 0; outer < oldSet.size(); outer++){
			
			Product oldProduct = oldSet.get(outer).getProduct();
			
			for(int inner = 0; inner < newSet.size(); inner++ ){
				
				Product newProduct = newSet.get(inner).getProduct();
				
				if(oldSet.get(outer).getIntProductPackageID() == newSet.get(inner).getIntProductPackageID() && oldProduct.getIntProductID() == newProduct.getIntProductID()){
					continue;
				}
				else if(inner == (newSet.size() - 1)){
					deactivatedSet.add(oldSet.get(outer));
				}
			}
		}
		
		return deactivatedSet;
	}
	
	public List<Product> searchList(String[] strProductID, List<Product> productList){
		
		List<Product> selectedProducts = new ArrayList<Product>();
		
		for(int intCtr = 0; intCtr < strProductID.length; intCtr++){
			
			for(int intCtr2 = 0; intCtr2 < productList.size(); intCtr2++){
				Product prod = productList.get(intCtr2);
				
				if(Integer.parseInt(strProductID[intCtr]) == prod.getIntProductID())
					selectedProducts.add(prod);
			}
		}
		
		return selectedProducts;
		
	}
}
