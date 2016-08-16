package com.gss.service;

import java.util.List;

import com.gss.dao.ServiceJDBCRepository;
import com.gss.dao.ServiceRepository;
import com.gss.model.Service;

public class ServiceServiceImpl implements ServiceService{
		
	public List<Service> getAllService() {
		
		ServiceRepository repo = new ServiceJDBCRepository();
		List<Service> serviceList = repo.getAllService();
		
		return serviceList;
	}

	
	public boolean createService(Service service) {
		
		ServiceRepository repo = new ServiceJDBCRepository();
		repo.createService(service);
		
		return false;
	}
	
	public boolean updateService(Service service){
		ServiceRepository repo = new ServiceJDBCRepository();
		
		return repo.updateService(service);
	}


	@Override
	public List<String> getAllCategory() {
		
		ServiceRepository repo = new ServiceJDBCRepository();
		
		return repo.getAllCategory();
	}


	@Override
	public boolean deactivateService(int intServiceID) {

		ServiceRepository repo = new ServiceJDBCRepository();
		
		return repo.deactivateService(intServiceID);
	}


	@Override
	public List<Service> getAllServiceNoImage() {

		ServiceRepository repo = new ServiceJDBCRepository();
		
		return repo.getAllServiceNoImage();
	}


	@Override
	public List<Service> queryAllService() {

		ServiceRepository repo = new ServiceJDBCRepository();
		
		return repo.queryAllService();
	}

}
