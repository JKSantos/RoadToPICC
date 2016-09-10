package com.gss.testers;

import com.gss.dao.EmployeeJDBCRepository;

public class TestJo {
	
	public static void main(String[] args){
		System.out.println(EmployeeJDBCRepository.searchJob("NEW ADMIN"));
	}

}
