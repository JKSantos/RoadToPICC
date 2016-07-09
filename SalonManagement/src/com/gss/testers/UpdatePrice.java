package com.gss.testers;

import com.gss.model.Service;
import com.gss.service.ServiceServiceImpl;

public class UpdatePrice {
	
public static void main(String[] args){
		
		ServiceServiceImpl service = new ServiceServiceImpl();
		
		Service serv = new Service(28, "Under Cut", "Hair Cut", 1, "SAMPLE DESCRIPTION",20, null, "resource/jeff.jpg");
		serv.setDblServicePrice(50.00);
		
		if(service.updateService(serv) == true)
			System.out.println("Success");
		else
			System.out.print("failed");
		
	}

}
