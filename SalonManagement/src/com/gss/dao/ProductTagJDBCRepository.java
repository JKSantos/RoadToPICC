package com.gss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.gss.connection.JDBCConnection;
import com.gss.model.ProductTag;

public class ProductTagJDBCRepository implements ProductTagRepository{

	private JDBCConnection jdbc = new JDBCConnection();
	private String strTag = "CALL createTag(?, ?, ?)";
	private String strAddStock = "UPDATE tblProduct SET intProductQuantity = intProductQuantity + ? WHERE intProductID = ?";
	private String strSubtractStock = "UPDATE tblProduct SET intProductQuantity = intProductQuantity - ? WHERE intProductID = ?";
	
	@Override
	public boolean defectiveTag(ProductTag productTag) {
		
		Connection con = jdbc.getConnection();
		
		try{
			
			PreparedStatement createDefectiveTag = con.prepareStatement(this.strTag);
			createDefectiveTag.setInt(1, productTag.getIntProductID());
			createDefectiveTag.setInt(2, productTag.getIntQuantity());
			createDefectiveTag.setInt(3, productTag.getIntTagType());
			
			createDefectiveTag.execute();
			createDefectiveTag.close();
			con.close();
			
			return true;
		}
		
		catch(Exception e){
			
			e.printStackTrace();
			
			return false;
		}
		
	}

	@Override
	public boolean addStock(ProductTag productTag) {
		
		Connection con = jdbc.getConnection();
		
		try{
			
			PreparedStatement addStock = con.prepareStatement(this.strAddStock);
			addStock.setInt(1, productTag.getIntQuantity());
			addStock.setInt(2, productTag.getIntProductID());
			
			addStock.execute();
			addStock.close();
			con.close();
			
			return true;
		}
		
		catch(Exception e){
			
			e.printStackTrace();
			
			return false;
		}
	}

	@Override
	public boolean subtractStock(ProductTag productTag) {
		
		Connection con = jdbc.getConnection();
		
		try{
			
			PreparedStatement subtractStock = con.prepareStatement(this.strSubtractStock);
			subtractStock.setInt(1, productTag.getIntQuantity());
			subtractStock.setInt(2, productTag.getIntProductID());
			
			subtractStock.execute();
			subtractStock.close();
			con.close();
			
			return true;
		}
		
		catch(Exception e){
			
			e.printStackTrace();
			
			return false;
		}
	}

}
