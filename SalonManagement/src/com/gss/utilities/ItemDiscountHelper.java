package com.gss.utilities;

import java.util.ArrayList;
import java.util.List;

import com.gss.model.Discount;
import com.gss.model.Product;
import com.gss.model.Service;
import com.gss.model.Package;
import com.gss.model.Promo;

public class ItemDiscountHelper {
	

	public static List<Product> getProducts(List<Product> products){
		
		 List<Discount> discounts = Discount.getAllDiscount();
		
		for(int indexOuter = 0; indexOuter < products.size(); indexOuter++){
			
			Product product = products.get(indexOuter);
			
			for(int index = 0; index < discounts.size(); index++){
				
				List<Product> discontedProducts = discounts.get(index).getProductList();
				int discountType = discounts.get(index).getIntDiscountType();
				String discountApplicability = discounts.get(index).getApplicability();
				
				
				for(int indexInner = 0; indexInner < discontedProducts.size(); indexInner++){
					
					int id = discontedProducts.get(indexInner).getIntProductID();

					
					if(product.getIntProductID() == id && discountApplicability.equals("ALL CUSTOMER")){
						
						double price = product.getDblProductPrice();
						double discount = discounts.get(index).getDblDiscountAmount();
						
						if(discountType == 1){
							product.setDblProductPrice((price - discount));
							
							products.set(indexOuter, product);
							break;
						}
						else{
							product.setDblProductPrice((price - (price * (discount / 100))));
							
							products.set(indexOuter, product);
							break;
						}
					}
				}
			}
		}
		
		return products;
	}

	
	public static List<Service> getServices(List<Service> services){
		
		
		System.out.println("ssssss");
		 List<Discount> discounts = Discount.getAllDiscount();
		
		for(int indexOuter = 0; indexOuter < services.size(); indexOuter++){
			
			Service service = services.get(indexOuter);
			
			for(int index = 0; index < discounts.size(); index++){
			
				List<Service> discountedService = discounts.get(index).getServiceList();
				int discountType = discounts.get(index).getIntDiscountType();
				String discountApplicability = discounts.get(index).getApplicability();
				
				for(int indexInner = 0; indexInner < discountedService.size(); indexInner++){
					
					if(service.getIntServiceID() == discountedService.get(indexInner).getIntServiceID() && discountApplicability.equals("ALL CUSTOMER")){
						
						double price = service.getDblServicePrice();
						double discount = discounts.get(index).getDblDiscountAmount();
						
						if(discountType == 1){
							service.setDblServicePrice((price - discount));
							
							services.set(indexOuter, service);
							break;
						}
						else{
							service.setDblServicePrice((price - (price * (discount / 100))));
							
							services.set(indexOuter, service);
							break;
						}
					}
				}
				
				break;
			}
		}
		return services;
	}
	
	public static List<Package> getPackages(List<Package> packages){
		
		 List<Discount> discounts = Discount.getAllDiscount();
		
		for(int indexOuter = 0; indexOuter < packages.size(); indexOuter++){
			
			Package packagee = packages.get(indexOuter);
			
			for(int index = 0; index < discounts.size(); index ++){
			
				List<Package> discountedPackage = discounts.get(index).getPackageList();
				int discountType = discounts.get(index).getIntDiscountType();
				String discountApplicability = discounts.get(index).getApplicability();
				
				for(int indexInner = 0; indexInner < discountedPackage.size(); indexInner++){
					
					if(packagee.getIntPackageID() == discountedPackage.get(indexInner).getIntPackageID() && discountApplicability.equals("ALL CUSTOMER")){
						
						double price = packagee.getDblPackagePrice();
						double discount = discounts.get(index).getDblDiscountAmount();
						
						if(discountType == 1){
							packagee.setDblPackagePrice((price - discount));
							
							packages.set(indexOuter, packagee);
							break;
						}
						else{
							packagee.setDblPackagePrice((price - (price * (discount / 100))));
							
							packages.set(indexOuter, packagee);
							break;
						}
					}
				}
				
				break;
			}
		}
		return packages;
	}
	
	public static List<Promo> getPromos(List<Promo> promos){
		
		 List<Discount> discounts = Discount.getAllDiscount();
		
		for(int indexOuter = 0; indexOuter < promos.size(); indexOuter++){
			
			Promo promo = promos.get(indexOuter);
			
			for(int index = 0; index < discounts.size(); index ++){
			
				List<Promo> discountedPromo = discounts.get(index).getPromoList();
				int discountType = discounts.get(index).getIntDiscountType();
				String discountApplicability = discounts.get(index).getApplicability();
				
				for(int indexInner = 0; indexInner < discountedPromo.size(); indexInner++){
					
					if(promo.getIntPromoID() == discountedPromo.get(indexInner).getIntPromoID() && discountApplicability.equals("ALL CUSTOMER")){
						
						double price = promo.getDblPromoPrice();
						double discount = discounts.get(index).getDblDiscountAmount();
						
						if(discountType == 1){
							promo.setDblPromoPrice((price - discount));
							
							promos.set(indexOuter, promo);
							break;
						}
						else{
							promo.setDblPromoPrice((price - (price * (discount / 100))));
							
							promos.set(indexOuter, promo);
							break;
						}
					}
				}
				
				break;
			}
		}
		return promos;
	}
}
