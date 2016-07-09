package com.gss.testers;

import java.util.ArrayList;
import java.util.List;

import com.gss.model.Product;
import com.gss.model.ProductPackage;
import com.gss.model.Promo;
import com.gss.model.Service;
import com.gss.model.ServicePackage;
import com.gss.service.PromoServiceImpl;

public class CreatePromo {
	
	public static void main(String[] args){
		
		PromoServiceImpl service = new PromoServiceImpl();
		
		Product product = new Product(8, "Grips Wax 150mL", "Hair Wax", "For styling your hair", 0, null, 90.00, "resource/jeff.jpg");
		Service serv = new Service(28, "Under Cut", "Hair Cut", 1, "SAMPLE DESCRIPTION",20, null, "resource/jeff.jpg");
		serv.setDblServicePrice(20.00);
		
		ProductPackage packageProd = new ProductPackage(1, product, 5, 1);
		List<ProductPackage> prodList = new ArrayList<ProductPackage>();
		prodList.add(packageProd);
		
		ServicePackage packageServ = new ServicePackage(1, serv, 1);
		List<ServicePackage> servList = new ArrayList<ServicePackage>();
		servList.add(packageServ);
		
		Promo promo = new Promo(1, "Birthday Promo", "Free Haircut and Wax", 0.00, 1, servList, prodList, "NON EXPIRY");
		
		if(service.createPromo(promo) == true)
			System.out.println("success");
		
	}

}
