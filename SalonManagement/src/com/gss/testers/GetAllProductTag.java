package com.gss.testers;

import java.sql.SQLException;

import com.gss.model.ProductTagReport;

public class GetAllProductTag {
	
	public static void main(String[] args){
		try {
			System.out.print(ProductTagReport.getProductTagReport().size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
