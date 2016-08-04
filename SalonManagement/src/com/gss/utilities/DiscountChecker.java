package com.gss.utilities;

import com.gss.model.Discount;
import com.gss.model.Product;
import com.gss.model.ProductOrder;
import com.gss.model.Promo;
import com.gss.model.Package;
import com.gss.model.ReservedPackage;
import com.gss.model.ReservedPromo;
import com.gss.model.ReservedService;
import com.gss.model.Service;

public class DiscountChecker {
	
	public static ProductOrder checkProductDiscount(Product product, Discount discount, int intQuantity){
		
		int productID = product.getIntProductID();
		
		for(int index = 0; index < discount.getProductList().size(); index++){
			
			System.out.println("Product ID: " + product.getIntProductID());
			
			int discountProductID = discount.getProductList().get(index).getIntProductID();
			System.out.println("Discount ProductID: " + discountProductID);
			
			if(productID == discountProductID){
				ProductOrder order = new ProductOrder(1, product, intQuantity, 1);
				order.setDiscountType(discountTypeConverter(discount.getIntDiscountType()));
				order.setDiscountAmount(discount.getDblDiscountAmount());
				
				return order;
				
			}
		}
		
		ProductOrder order = new ProductOrder(1, product, intQuantity, 1);
		order.setDiscountType("NONE");
		order.setDiscountAmount(0);
		
		return order;
	}

	public static ReservedService checkServiceDiscount(Service service, Discount discount, int intQuantity){
	
		int serviceID = service.getIntServiceID();
		
		for(int index = 0; index < discount.getServiceList().size(); index++){
			
			int discountServiceID = discount.getServiceList().get(index).getIntServiceID();
			
			if(serviceID == discountServiceID){
				ReservedService reserved = new ReservedService(1, 1, service, intQuantity, 1);
				reserved.setDiscountType(discountTypeConverter(discount.getIntDiscountType()));
				reserved.setDiscountAmount(discount.getDblDiscountAmount());
				
				return reserved;
				
			}
		}
		
		ReservedService reserved = new ReservedService(1, 1, service, intQuantity, 1);
		reserved.setDiscountType("NONE");
		reserved.setDiscountAmount(0);
		
		return reserved;
	}

	public static ReservedPackage checkPackageDiscount(Package packagee, Discount discount, int intQuantity){
	
		int packageID = packagee.getIntPackageID();
		
		for(int index = 0; index < discount.getPackageList().size(); index++){
			
			int discountPackageID = discount.getPackageList().get(index).getIntPackageID();
			
			if(packageID == discountPackageID){
				ReservedPackage order = new ReservedPackage(1, 1, packagee, intQuantity, 1);
				order.setDiscountType(discountTypeConverter(discount.getIntDiscountType()));
				order.setDiscountAmount(discount.getDblDiscountAmount());
				
				return order;
				
			}
		}
		
		ReservedPackage order = new ReservedPackage(1, 1, packagee, intQuantity, 1);
		order.setDiscountType("NONE");
		order.setDiscountAmount(0);
		
		return order;
	}
	
	public static ReservedPromo checkPromoDiscount(Promo promo, Discount discount, int intQuantity){
		
		int promoID = promo.getIntPromoID();
		
		for(int index = 0; index < discount.getPromoList().size(); index++){
			
			int discountPromoID = discount.getPromoList().get(index).getIntPromoID();
			
			if(promoID == discountPromoID){
				ReservedPromo order = new ReservedPromo(1, 1, promo, intQuantity, 1);
				order.setDiscountType(discountTypeConverter(discount.getIntDiscountType()));
				order.setDiscountAmount(discount.getDblDiscountAmount());
				
				return order;
				
			}
		}
		
		ReservedPromo order = new ReservedPromo(1, 1, promo, intQuantity, 1);
		order.setDiscountType("NONE");
		order.setDiscountAmount(0);
		
		return order;
	}
	
	
	public static String discountTypeConverter(int discount){
	
		String converted = "";
		
		switch(discount){
			case 1:
				converted = "fixed";
				break;
			case 2:
				converted = "percent";
				break;
		}
		
		return converted;
	}

}
