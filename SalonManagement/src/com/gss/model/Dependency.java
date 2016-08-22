package com.gss.model;

import java.sql.SQLException;
import java.util.List;

import com.gss.service.BusinessDependencies.BusinessDependencyService;
import com.gss.service.BusinessDependencies.BusinessDependencyServiceImpl;

public class Dependency {
	
	private int intID;
	private String strName;
	private String strValue;
	
	public Dependency(int intID, String strName, String strValue) {
		super();
		this.intID = intID;
		this.strName = strName;
		this.strValue = strValue;
	}
	public int getIntID() {
		return intID;
	}
	public void setIntID(int intID) {
		this.intID = intID;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	public String getStrValue() {
		return strValue;
	}
	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}
	public static boolean updateDependency(List<Dependency> dependencies) throws SQLException{
		BusinessDependencyService repo = new BusinessDependencyServiceImpl();
		
		return repo.updateDependencies(dependencies);
	}
	public static List<Dependency> getAllDependencies() throws SQLException{
		BusinessDependencyService repo = new BusinessDependencyServiceImpl();
		
		return repo.getAllDependencies();
	}
}
