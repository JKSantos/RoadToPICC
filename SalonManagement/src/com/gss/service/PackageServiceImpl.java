package com.gss.service;

import java.util.List;

import com.gss.dao.PackageJDBCRepository;
import com.gss.dao.PackageRepository;
import com.gss.model.Package;

public class PackageServiceImpl implements PackageService{
	
	public boolean createPackage(Package pack){
		
		PackageRepository repo = new PackageJDBCRepository();
		
		return repo.createPackage(pack);
	}
	
	public boolean updatePackage(Package pack){
		
		PackageRepository repo = new PackageJDBCRepository();
		
		return repo.updatePackage(pack);
	}
	
	public List<Package> getAllPackage(){
		
		PackageRepository repo = new PackageJDBCRepository();
		
		return repo.getAllPackage();
	}

	@Override
	public boolean deactivatePackage(int packageID) {
		
		PackageRepository repo = new PackageJDBCRepository();
		
		return repo.deactivatePackage(packageID);
	}

	@Override
	public Package getPackageByID(int intPackageID) {
		
		PackageRepository repo = new PackageJDBCRepository();
		
		return repo.getPackageByID(intPackageID);
	}

	@Override
	public List<Package> getAllPackageNoDetails() {
		
		PackageRepository repo = new PackageJDBCRepository();
		
		return repo.getAllPackageNoDetails();

	}

	@Override
	public List<Package> queryAllPackage() {
		
		PackageRepository repo = new PackageJDBCRepository();
		
		return repo.queryAllPackage();
	}

	@Override
	public List<Package> getPackageByType(String type) {
		
		PackageRepository repo = new PackageJDBCRepository();
		
		return repo.getPackageByType(type);
	}

}
