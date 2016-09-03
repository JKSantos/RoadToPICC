package com.gss.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.Discount;
import com.gss.model.Product;
import com.gss.utilities.ItemDiscountHelper;

public class ProductJDBCRepository implements ProductRepository{

	@Override
	public boolean createProduct(Product product) {
		
		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getConnection();
		String strQuery1 = "CALL createProduct(?, ?, ?, ?)";
		String strQuery2 = "CALL createProductPrice(?, ?)";
		ResultSet set1;
		
		int intProdID = 0;
		
		try{
			
			File imageFile = new File(product.getStrPhotoPath());
			FileInputStream fis = new FileInputStream(imageFile);
			
			PreparedStatement pre1 = con.prepareStatement(strQuery1);
			pre1.setString(1, product.getStrProductCategory());
			pre1.setString(2, product.getStrProductName());
			pre1.setBinaryStream(3, (InputStream)fis, (int)imageFile.length());
			pre1.setString(4, product.getStrProductDesc());
			
			set1 = pre1.executeQuery();
			
			while(set1.next()){
				intProdID = set1.getInt(1);
			}
			
			pre1.close();
			
			PreparedStatement pre2 = con.prepareStatement(strQuery2);
			pre2.setInt(1, intProdID);
			pre2.setDouble(2, product.getDblProductPrice());
			
			pre2.execute();
			pre2.close();
			con.close();
			
			return true;
			
		}
		catch(Exception e){
			System.out.println(e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public boolean updateProduct(Product product) {
		
		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getConnection();
		String strQuery1 = "CALL updateProduct(?, ?, ?, ?, ?);";
		String strQuery2 = "CALL createProductPrice(?, ?);";
		double dblPrice = 0.00;
		ResultSet set;
		
		
		try{
			PreparedStatement pre1 = con.prepareStatement(strQuery1);
			
			if(!product.getStrPhotoPath().equalsIgnoreCase("image")){
				File imageFile = new File(product.getStrPhotoPath());
				FileInputStream fis = new FileInputStream(imageFile);
			
				pre1.setInt(1, product.getIntProductID());
				pre1.setString(2, product.getStrProductCategory());
				pre1.setString(3, product.getStrProductName());
				pre1.setString(4, product.getStrProductDesc());
				pre1.setBinaryStream(5, (InputStream)fis, (int)imageFile.length());	
			}
			else{
				pre1.setInt(1, product.getIntProductID());
				pre1.setString(2, product.getStrProductCategory());
				pre1.setString(3, product.getStrProductName());
				pre1.setString(4, product.getStrProductDesc());
				pre1.setInt(5, 101);
			}
			set = pre1.executeQuery();
			
			while(set.next()){
				dblPrice = set.getDouble(1);
			}
			
			if(dblPrice != product.getDblProductPrice()){
				PreparedStatement pre2 = con.prepareStatement(strQuery2);
				pre2.setInt(1, product.getIntProductID());
				pre2.setDouble(2, product.getDblProductPrice());
				
				pre2.execute();
				pre1.close();
				pre2.close();
				con.close();
				return true;
			}
			else{
				pre1.close();
				con.close();
				return true;
			}
				
		}
		catch(Exception e){
			
			System.out.println(e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public List<Product> getAllProducts() {
		
		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getConnection();
		String strQuery1 = "CALL fetchProducts()";
		String strQuery2 = "CALL fetchProductPrice(?)";
		List<Product> productList = new ArrayList<Product>();
		
		
		try{
			
			PreparedStatement pre = con.prepareStatement(strQuery1);
			ResultSet set = pre.executeQuery();
			ResultSet set2;
			
			while(set.next()){
				
				Product product;
				int intProductID = set.getInt(1);
				String strProductCate = set.getString(2);
				String strProductName = set.getString(3);
				String strProductDesc = set.getString(4);
				int intProductQuan = set.getInt(5);
				Blob blob = set.getBlob(6);
				int blobLength = (int)blob.length();
				byte[] actualPhoto = blob.getBytes(1, blobLength);
				int intStatus = set.getInt(7);
				String strPhotoPath = "localhost:8080/SalonManagement/getImage?ImageID=" + intProductID + "&type=product";
				
				PreparedStatement pre2 = con.prepareStatement(strQuery2);
				pre2.setInt(1, intProductID);
				
				set2 = pre2.executeQuery();
				
				while(set2.next()){
					double price = set2.getDouble(1);
					product = new Product(intProductID, strProductName, strProductCate, strProductDesc, intProductQuan, actualPhoto, price, strPhotoPath, intStatus);
				
					productList.add(product);
				}
				pre2.close();
				set2.close();
			}
			
			set.close();
			
			pre.close();
			con.close();
			
//			return ItemDiscountHelper.getProducts(productList);
			
			return productList;
		}
		catch(Exception e){
			
			System.out.println(e.fillInStackTrace());
			return null;
		}
	}

	@Override
	public List<String> getAllCategory() {
	
		List<String> categoryList = new ArrayList<String>();
		Connection con = new JDBCConnection().getConnection();
		String query = "SELECT strProdCategory FROM tblProductCategory;";
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			ResultSet set = pre.executeQuery();
			
			while(set.next()){
				categoryList.add(set.getString(1));
			}
			
			pre.close();
			set.close();
			con.close();
			
			return categoryList;
		}
		catch(Exception e)
		{
			System.out.println(e.fillInStackTrace());
			return null;
		}
		
	}

	@Override
	public boolean deactivateProduct(int intProductID) {
		
		Connection con = new JDBCConnection().getConnection();
		String query = "UPDATE tblProduct SET intProdStatus = 0 WHERE intProductID = ?;";
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			pre.setInt(1, intProductID);
	
			pre.execute();
			pre.close();
			con.close();
			
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public List<Product> getAllProductsNoImage() {
		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getConnection();
		String strQuery1 = "CALL fetchProducts()";
		String strQuery2 = "CALL fetchProductPrice(?)";
		List<Product> productList = new ArrayList<Product>();
		
		
		try{
			
			PreparedStatement pre = con.prepareStatement(strQuery1);
			ResultSet set = pre.executeQuery();
			ResultSet set2;
			
			while(set.next()){
				
				Product product;
				int intProductID = set.getInt(1);
				String strProductCate = set.getString(2);
				String strProductName = set.getString(3);
				String strProductDesc = set.getString(4);
				int intProductQuan = set.getInt(5);
				Blob blob = set.getBlob(6);
				int blobLength = (int)blob.length();
				byte[] actualPhoto = null;
				int intStatus = set.getInt(7);
				String strPhotoPath = "localhost:8080/SalonManagement/getImage?ImageID=" + intProductID + "&type=product";
				
				PreparedStatement pre2 = con.prepareStatement(strQuery2);
				pre2.setInt(1, intProductID);
				
				set2 = pre2.executeQuery();
				
				while(set2.next()){
					double price = set2.getDouble(1);
					product = new Product(intProductID, strProductName, strProductCate, strProductDesc, intProductQuan, actualPhoto, price, strPhotoPath, intStatus);
				
					productList.add(product);
				}
				pre2.close();
				set2.close();
			}
			
			pre.close();
			con.close();
			set.close();
			
//			return ItemDiscountHelper.getProducts(productList);
			return productList;
		}
		catch(Exception e){
			
			System.out.println(e.fillInStackTrace());
			return null;
		}
	}

	@Override
	public List<Product> queryAllProduct() {
		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getConnection();
		String strQuery1 = "CALL queryAllProducts()";
		String strQuery2 = "CALL fetchProductPrice(?)";
		List<Product> productList = new ArrayList<Product>();
		
		
		try{
			
			PreparedStatement pre = con.prepareStatement(strQuery1);
			ResultSet set = pre.executeQuery();
			ResultSet set2;
			
			while(set.next()){
				
				Product product;
				int intProductID = set.getInt(1);
				String strProductCate = set.getString(2);
				String strProductName = set.getString(3);
				String strProductDesc = set.getString(4);
				int intProductQuan = set.getInt(5);
				Blob blob = set.getBlob(6);
				int blobLength = (int)blob.length();
				byte[] actualPhoto = null;
				int intStatus = set.getInt(7);
				String strPhotoPath = ":8080/SalonManagement/getImage?ImageID=" + intProductID + "&type=product";
				
				PreparedStatement pre2 = con.prepareStatement(strQuery2);
				pre2.setInt(1, intProductID);
				
				set2 = pre2.executeQuery();
				
				while(set2.next()){
					double price = set2.getDouble(1);
					product = new Product(intProductID, strProductName, strProductCate, strProductDesc, intProductQuan, actualPhoto, price, strPhotoPath, intStatus);
				
					productList.add(product);
				}
				pre2.close();
				set2.close();
			}
			
			pre.close();
			con.close();
			set.close();
			
			return productList;
		}
		catch(Exception e){
			
			System.out.println(e.fillInStackTrace());
			return null;
		}
	}

}
