package com.gss.utilities;

import java.util.List;

import com.gss.model.ProductPackage;

public class ProductPackageComparison {
	
	public String compare(ProductPackage prodPack, List<ProductPackage> productList){
		
		
		if(productList.size() > 0){
			for(int intCtr = 0; intCtr < productList.size(); intCtr++ ){
			
				ProductPackage newProduct = prodPack;
				ProductPackage oldProduct = productList.get(intCtr);
				int newID = newProduct.getIntProductPackageID();
				int oldID = oldProduct.getIntProductPackageID();
				int newQuantity = newProduct.getIntProductQuantity();
				int oldQuantity = oldProduct.getIntProductQuantity();
				int newStatus = newProduct.getIntStatus();
				int oldStatus = oldProduct.getIntStatus();
			
				if(newID == oldID && newQuantity == oldQuantity){
					System.out.println("--Same: " + newID + " " + oldID);
					return "same";
				}
				else if(newID == oldID && newQuantity != oldQuantity || newID == oldID && newStatus != oldStatus){
					System.out.println("--update: " + newID + " " + oldID);
					System.out.println("--update: " + newQuantity + " " + oldQuantity);
					return "update";
				}
				else if(intCtr == (productList.size() - 1)){
					System.out.println("--new: " + newID + " " + oldID);
					return "new";
				}
			}
			
			return null;
		}
		else{
			return "new";
		}
	}

}
