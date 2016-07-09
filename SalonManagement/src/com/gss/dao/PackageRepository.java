package com.gss.dao;

import java.util.List;

import com.gss.model.Package;

public interface PackageRepository {

	public boolean createPackage(Package pack);
	
	public boolean updatePackage(Package pack);
	
	public List<Package> getAllPackage();
	
	public boolean deactivatePackage(int packageID);
	
	public List<Package> getAllPackageNoImage();
	
}
