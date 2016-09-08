package com.gss.testers;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.Product;

public class GetProducts {

	public static void main(String[] args){
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
				String strPhotoPath = ":8080/SalonManagement/getImage?ImageID=" + intProductID + "&type=product";
				
				PreparedStatement pre2 = con.prepareStatement(strQuery2);
				pre2.setInt(1, intProductID);
				
				set2 = pre2.executeQuery();
				
				while(set2.next()){
					System.out.println("1111");
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
			System.out.println(productList.size());
		}
		catch(Exception e){
			
			System.out.println(e.fillInStackTrace());
			
		}
	}
}
