package com.gss.testers;

import com.gss.model.Service;
import com.gss.service.ServiceServiceImpl;

public class CreateService {
	
	public static void main(String[] args){
		
		ServiceServiceImpl service = new ServiceServiceImpl();
		
		Service serv = new Service(1, "Under Cut", "Hair Cut", 1, "SAMPLE DESCRIPTION",10,  null, "resource/jeff.jpg");
		serv.setDblServicePrice(20.00);
		
		if(service.createService(serv) != true)
			System.out.println("Success");
		
	}

}
