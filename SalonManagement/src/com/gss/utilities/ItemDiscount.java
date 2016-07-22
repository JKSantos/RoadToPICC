package com.gss.utilities;

import com.gss.model.Product;
import com.gss.model.ProductOrder;
import com.gss.model.Promo;
import com.gss.model.ReservedPackage;
import com.gss.model.ReservedPromo;
import com.gss.model.ReservedService;
import com.gss.model.Service;

public class ItemDiscount {
	
	public static ProductOrder getProductDiscount(ProductOrder order){
		
		Product product = order.getProduct();
		
		if(order.getDiscountType().equals("percent")){
			product.setDblProductPrice((product.getDblProductPrice() - (product.getDblProductPrice() * (order.getDiscountAmount() / 100)))); 
			order.setProduct(product);
			return order;
		}
		else{
			product.setDblProductPrice((product.getDblProductPrice() - order.getDiscountAmount())); 
			order.setProduct(product);
			return order;
		}
		
	}
	
	public static ReservedService getServiceDiscount(ReservedService reserved){
		
		Service service = reserved.getService();
		double price = service.getDblServicePrice();
		double discount = reserved.getDiscountAmount();
		
		if(reserved.getDiscountType().equals("percent")){
			service.setDblServicePrice(price - (price * (discount / 100)));
			reserved.setService(service);
			return reserved;
		}
		else{
			service.setDblServicePrice(price - discount);
			reserved.setService(service);
			return reserved;
		}
	}
	
	public static ReservedPackage getPackageDiscount(ReservedPackage reserved){

		com.gss.model.Package packagee = reserved.getPackages();
		double price = packagee.getDblPackagePrice();
		double discount = reserved.getDiscountAmount();
		
		if(reserved.getDiscountType().equals("percent")){
			packagee.setDblPackagePrice(price - (price * (discount / 100)));
			reserved.setPackages(packagee);
			return reserved;
		}
		else{
			packagee.setDblPackagePrice(price - discount);
			reserved.setPackages(packagee);
			return reserved;
		}
	}
	
	public static ReservedPromo getPromoReservation(ReservedPromo reserved){
		
		Promo promo = reserved.getPromo();
		double price = promo.getDblPromoPrice();
		double discount = reserved.getDiscountAmount();
		
		if(reserved.getDiscountType().equals("percent")){
			promo.setDblPromoPrice(price - (price * (discount / 100)));
			reserved.setPromo(promo);
			return reserved;
		}
		else{
			promo.setDblPromoPrice(price - discount);
			reserved.setPromo(promo);
			return reserved;
		}
	}

}
