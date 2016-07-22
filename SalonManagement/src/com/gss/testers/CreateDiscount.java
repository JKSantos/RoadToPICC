package com.gss.testers;

import java.util.ArrayList;
import java.util.List;

import com.gss.model.Discount;
import com.gss.model.Product;
import com.gss.model.Promo;
import com.gss.model.Package;
import com.gss.model.Service;
import com.gss.service.DiscountService;
import com.gss.service.DiscountServiceImpl;
import com.gss.service.ServiceService;
import com.gss.service.ServiceServiceImpl;
import com.gss.utilities.SearchProduct;
import com.gss.utilities.SearchService;

public class CreateDiscount {
	
	public static void main(String[] args){
		
		DiscountService service = new DiscountServiceImpl();
		service.getAllDiscount();
	}
	
	public static void sss(){

		DiscountService service = new DiscountServiceImpl();
		ServiceService serviceS = new ServiceServiceImpl();
		List<Service> serviceList = serviceS.getAllServiceNoImage();
		
		List<Service> kasama = new ArrayList<Service>();
		kasama.add(new SearchService().search(34, serviceList));
		
		List<Product> producs = new ArrayList<Product>();
		producs.add(new SearchProduct().search(33, Product.getAllProduct()));
		
		List<Package> pack = new ArrayList<Package>();
		List<Promo> promo = new ArrayList<Promo>();
		
		Discount discount = new Discount(1, "ALL CUSTOMER", "GATSBY DISCOUNT", "DISCOUNT ON GATSBY WAX 250ML", "", 2, 25, producs, kasama, pack, promo, 0);
		
		System.out.println(service.createDiscount(discount));
	}

}
