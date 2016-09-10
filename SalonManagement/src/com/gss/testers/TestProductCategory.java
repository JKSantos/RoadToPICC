package com.gss.testers;

import com.gss.actions.Product.ProductCategory;
import com.gss.actions.Service.ServiceCategory;
import com.gss.dao.ProductJDBCRepository;

public class TestProductCategory {
	
	public static void main(String[] args){
		
		
		ServiceCategory category = new ServiceCategory();
		category.setCategoryName("asdfadfasdf");
		category.setType(1);
		System.out.println(category.add());
	}

}
