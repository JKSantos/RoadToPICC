package com.gss.model;

import java.util.ArrayList;
import java.util.List;

public class Specialization {
	
	private int id;
	private String name;
	
	public Specialization(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static List<Specialization> convertToObject(List<String> id){
		
		List<Specialization> specialization = new ArrayList<Specialization>();
		
		for(int i = 0; i < id.size(); i++){
			specialization.add(new Specialization(Integer.parseInt(id.get(i)), "DUMMY NAME"));
		}
		
		return specialization;
	}
}
