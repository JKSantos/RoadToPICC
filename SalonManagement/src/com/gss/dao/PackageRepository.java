package com.gss.dao;

import java.util.List;

import com.gss.model.Package;

public interface PackageRepository {

	public boolean createPackage(Package pack);
	
	public boolean updatePackage(Package pack);
	
	public List<Package> getAllPackage();
	
	public boolean deactivatePackage(int packageID);
	
	public List<Package> getAllPackageNoImage();
	
	public Package getPackageByID(int intPackageID);
	
	public List<Package> getAllPackageNoDetails();

	public List<Package> queryAllPackage();
	
	public List<Package> getPackageByType(String type);
	
}
