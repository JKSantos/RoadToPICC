package com.gss.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.Employee;
import com.gss.model.Product;
import com.gss.model.ProductTag;
import com.gss.model.ProductTagReport;
import com.gss.model.TagSum;

public class ProductTagJDBCRepository implements ProductTagRepository{

	private JDBCConnection jdbc = new JDBCConnection();
	
	private String strTag = "CALL createTag(?, ?, ?, ?)";
	private String strAddStock = "UPDATE tblProduct SET intProductQuantity = intProductQuantity + ? WHERE intProductID = ?";
	private String strSubtractStock = "UPDATE tblProduct SET intProductQuantity = intProductQuantity - ? WHERE intProductID = ?";
	
	@Override
	public boolean defectiveTag(ProductTag productTag) {
		
		Connection con = jdbc.getConnection();
		
		try{
			
			PreparedStatement createDefectiveTag = con.prepareStatement(this.strTag);
			createDefectiveTag.setInt(1, productTag.getProduct().getIntProductID());
			createDefectiveTag.setInt(2, productTag.getIntQuantity());
			createDefectiveTag.setInt(3, productTag.getIntTagType());
			createDefectiveTag.setInt(4, productTag.getTagBy().getIntEmpID());
			
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
			addStock.setInt(2, productTag.getProduct().getIntProductID());
			
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
			subtractStock.setInt(2, productTag.getProduct().getIntProductID());
			
			defectiveTag(productTag);
			
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


	public boolean restoreTag(ProductTag productTag) throws SQLException{
		
		Connection con = jdbc.getConnection();
		String deleteTag = "CALL restoreTag(?);";				
		
		try{
			PreparedStatement delete = con.prepareStatement(deleteTag);
			
			delete.setInt(1, productTag.getIntTagID());
			
			ResultSet result = delete.executeQuery();
			
			delete.close();
			result.close();
			con.close();
			
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			con.close();
			return false;
		}
	}

	public List<ProductTagReport> getProductTagReport(){
		
		Connection 	con 				= jdbc.getConnection();
		String getAllTags 				= "CALL queryProductTag();";
		List<ProductTagReport>	reports = new ArrayList<ProductTagReport>();
		
		try{
			PreparedStatement get		= con.prepareStatement(getAllTags);
			ResultSet tags				= get.executeQuery();
			
			while(tags.next()){
				int intTagID			= tags.getInt(1);
				String strProductName	= tags.getString(2);
				Date datDateTagged		= tags.getDate(3);
				String tagType			= ProductTag.toString(tags.getInt(4));
				int intQuantity			= tags.getInt(5);
				int intEmpID			= tags.getInt(6);
				String strEmployee		= tags.getString(7) + " " + tags.getString(7);
				
				ProductTagReport tag = new ProductTagReport(intTagID, strProductName, datDateTagged, tagType, intQuantity, intEmpID, strEmployee);
				reports.add(tag);
			}
			
			get.close();
			tags.close();
			con.close();
			
			return reports;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ProductTagReport> getFilteredProductTagReport() {
		
		Connection 	con 				= jdbc.getConnection();
		String getAllTags 				= "CALL queryFilteredProductTag(?, ?, ?, ?);";
		
		List<ProductTagReport>	reports = new ArrayList<ProductTagReport>();
		
		try{
			PreparedStatement get		= con.prepareStatement(getAllTags);
			ResultSet tags				= get.executeQuery();
			
			while(tags.next()){
				int intTagID			= tags.getInt(1);
				String strProductName	= tags.getString(2);
				Date datDateTagged		= tags.getDate(3);
				String tagType			= ProductTag.toString(tags.getInt(4));
				int intQuantity			= tags.getInt(5);
				int intEmpID			= tags.getInt(6);
				String strEmployee		= tags.getString(7) + " " + tags.getString(7);
				
				ProductTagReport tag = new ProductTagReport(intTagID, strProductName, datDateTagged, tagType, intQuantity, intEmpID, strEmployee);
				reports.add(tag);
			}	
			
			get.close();
			tags.close();
			con.close();
			
			return reports;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<TagSum> getTagSum() {
		
		Connection 	con 				= jdbc.getConnection();
		String getProductsWithTag 		= "CALL productsWithTags();";
		String sum						= "CALL getProductTagSum(?);";
		
		List<TagSum>	reports = new ArrayList<TagSum>();
		
		try{
			PreparedStatement withTag	= con.prepareStatement(getProductsWithTag);
			PreparedStatement sumSet	= con.prepareStatement(sum);
			ResultSet withTagResult		= withTag.executeQuery();
			ResultSet sumSetResult		= null;
			
			while(withTagResult.next()){
				int id = withTagResult.getInt(1);
				

				sumSet.setInt(1, id);
				sumSetResult = sumSet.executeQuery();
				
				while(sumSetResult.next()){
					int intProductID = sumSetResult.getInt(1);
					String strProductName = sumSetResult.getString(2);
					int intTotal = sumSetResult.getInt(3);
					int def = sumSetResult.getInt(4);
					int lost = sumSetResult.getInt(5);
					int exp = sumSetResult.getInt(6);
					int consumed = sumSetResult.getInt(7);
					
					TagSum tagSum = new TagSum(intProductID, strProductName, intTotal, def, lost, exp, consumed);
					reports.add(tagSum);
				}
			}
			
			
			withTag.close();
			sumSet.close();
			sumSetResult.close();
			withTagResult.close();
			
			return reports;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ProductTag> getAllTag() throws SQLException {
		Connection con = jdbc.getConnection();
		String query 					= "SELECT * FROM tblProductTag;";
		List<ProductTag> tagList 		= new ArrayList<ProductTag>();
		
		List<Product> productList = Product.getAllProduct();
		List<Employee> employeeList  = Employee.getEmployeeList();
		
		try{
			
			PreparedStatement getAllTags = con.prepareStatement(query);
			
			ResultSet tags = getAllTags.executeQuery();
			
			while(tags.next()){
				int intTagID = tags.getInt(1);
				int intProductID = tags.getInt(2);
				Date tagDate = tags.getDate(3);
				int intTagType = tags.getInt(4);
				int intQuantity = tags.getInt(5);
				int intTagBy = tags.getInt(6);
				
				Product product = null;
				Employee employee = null;
				
				for(int i = 0; i < productList.size(); i++){
					if(intProductID == productList.get(i).getIntProductID()){
						product = productList.get(i);
					}
				}
				
				for(int i = 0; i < employeeList.size(); i++){
					if(intTagBy == employeeList.get(i).getIntEmpID()){
						employee = employeeList.get(i);
					}
				}
				
				tagList.add(new ProductTag(intTagID, product, tagDate, intTagType, employee, intQuantity));
			}
			
			getAllTags.close();
			tags.close();
			con.close();
			
			return tagList;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
