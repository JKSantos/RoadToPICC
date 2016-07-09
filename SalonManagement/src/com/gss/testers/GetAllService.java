package com.gss.testers;

import java.util.List;

import com.gss.model.Service;
import com.gss.service.ServiceServiceImpl;

public class GetAllService {

	public static void main(String[] args){
		
		ServiceServiceImpl service = new ServiceServiceImpl();
		
		List<Service> serviceList = service.getAllService();
		
		if(serviceList != null){
			System.out.println("success");
			
			for(int i = 0; i < serviceList.size(); i++){
				Service service1 = serviceList.get(i);
				System.out.println(service1.getIntServiceID() + " " + service1.getStrServiceName() + "\t P " + service1.getDblServicePrice());
			}
		}
	}
}
