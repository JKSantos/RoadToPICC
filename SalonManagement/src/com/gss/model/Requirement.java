package com.gss.model;

import java.util.ArrayList;
import java.util.List;

public class Requirement {
	
	private int intRequirementID;
	private String strRequirementName;
	private int intStatus;

	public Requirement(int intRequirementID, String strRequirementName, int intStatus) {
		super();
		this.intRequirementID = intRequirementID;
		this.strRequirementName = strRequirementName;
		this.intStatus = intStatus;
	}
	public int getIntRequirementID() {
		return intRequirementID;
	}
	public void setIntRequirementID(int intRequirementID) {
		this.intRequirementID = intRequirementID;
	}
	public String getStrRequirementName() {
		return strRequirementName;
	}
	public void setStrRequirementName(String strRequirementName) {
		this.strRequirementName = strRequirementName;
	}
	public int getIntStatus() {
		return intStatus;
	}
	public void setIntStatus(int intStatus) {
		this.intStatus = intStatus;
	}
	public static List<Requirement> toOjbect(String[] id){
		
		List<Requirement> list = new ArrayList<Requirement>();
		
		try{
		
			for(int i = 0; i < id.length; i++){
				list.add(new Requirement(Integer.parseInt(id[i]), "DUMMY NAME", 1));
			}
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		
		return list;
	}

}
