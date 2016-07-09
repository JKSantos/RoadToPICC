package com.gss.testers;

import java.util.ArrayList;
import java.util.List;

import com.gss.model.Package;
import com.gss.model.Product;
import com.gss.model.ProductPackage;
import com.gss.model.Service;
import com.gss.model.ServicePackage;
import com.gss.service.PackageServiceImpl;

public class UpdatePackage {
	
	public static void main(String[] args){
		
		Service serv = new Service(32, "Under Cut", "Hair Cut", 1, "SAMPLE DESCRIPTION",20, null, "resource/jeff.jpg");
		serv.setDblServicePrice(20.00);
		
		Service serv2 = new Service(35, "Under Cut", "Hair Cut", 1, "SAMPLE DESCRIPTION",20,null, "resource/jeff.jpg");
		serv.setDblServicePrice(20.00);
		
		ServicePackage servPack = new ServicePackage(6, serv, 10, 1);
		ServicePackage servPack2 = new ServicePackage(10, serv2, 2, 1);
		
		List<ServicePackage> serviceList = new ArrayList<ServicePackage>();
		serviceList.add(servPack);
		serviceList.add(servPack2);
		
		Product product = new Product(16, "Grips Wax 150mL", "Hair Wax", "For styling your hair", 0, null, 90.00, "resource/jeff.jpg", 1);
		Product product2 = new Product(17, "Grips Wax 150mL", "Hair Wax", "For styling your hair", 0, null, 90.00, "resource/jeff.jpg", 1);
		
		ProductPackage productPack1 = new ProductPackage(2, product, 2, 1);
		ProductPackage productPack2 = new ProductPackage(25, product2, 3, 1);
		
		List<ProductPackage> prodList = new ArrayList<ProductPackage>();
		prodList.add(productPack1);
		prodList.add(productPack2);
		
		Package newPackage = new Package(11, "Graduation Package", "SAMPLE WEDDING PACKAGE", 1, 7, "NON EXPIRY", 2500.00, serviceList, prodList, 1);
			
		PackageServiceImpl packServ = new PackageServiceImpl();
		
		if(packServ.updatePackage(newPackage))
			System.out.println("success");
		else
			System.out.println("failed");
		
	}

}
