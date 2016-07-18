package com.gss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.Discount;
import com.gss.model.Product;
import com.gss.model.Promo;
import com.gss.model.Service;
import com.gss.utilities.SearchPackage;
import com.gss.utilities.SearchProduct;
import com.gss.utilities.SearchPromo;
import com.gss.utilities.SearchService;
import com.gss.model.Package;

public class DiscountJDBCRepository implements DiscountRepository{

	JDBCConnection jdbc = new JDBCConnection();
	private int intID;
	private String strName;
	private String strDesc;
	private String strGuide;
	private int intType;
	private double dblAmount;
	private int status;
	
	private String strItemID = "";
	private int intCtr = 0;
	
	@Override
	public List<Discount> getAllDiscount() {
		
		Connection con 						= jdbc.getConnection();
		List<Discount> 	discountList 		= new ArrayList<Discount>();
		String strQuery 					= "SELECT * FROM tblDiscount WHERE intStatus = 1;";
		String getProducts 					= "SELECT intProductID FROM tblDiscountProduct WHERE intDiscountID = ?;";
		String getServices 					= "SELECT intServiceID FROM tblDiscountService WHERE intDiscountID = ?;";
		String getPromos = 					"SELECT intPromoID FROM tblDiscountPromo WHERE intDiscountID = ?;";
		String getPackages 					= "SELECT intPackageID FROM tblDiscountPackage WHERE intDiscountID = ?;";
		
		try{
			
			PreparedStatement pre 			= con.prepareStatement(strQuery);
			PreparedStatement products 		= con.prepareStatement(getProducts);
			PreparedStatement services 		= con.prepareStatement(getServices);
			PreparedStatement promos 		= con.prepareStatement(getPromos);
			PreparedStatement packages 		= con.prepareStatement(getPackages);
			
			ResultSet productSet;
			ResultSet serviceSet;
			ResultSet promoSet;
			ResultSet packageSet;
			ResultSet set;
			
			set = pre.executeQuery();
			
			while(set.next()){
				
				List<Product> productList = new ArrayList<Product>();
				List<Service> serviceList = new ArrayList<Service>();
				List<Package> packageList = new ArrayList<Package>();
				List<Promo> promoList = new ArrayList<Promo>();
				
				this.intID = set.getInt(1);
				this.strName = set.getString(2);
				this.strDesc = set.getString(3);
				this.strGuide = set.getString(4);
				this.intType = set.getInt(5);
				this.dblAmount = set.getDouble(6);
				this.status = set.getInt(7);
				
				products.setInt(1, this.intID);
				productSet = products.executeQuery();
			
				while(productSet.next()){
					
					if(intCtr != 0){
						strItemID += "," + String.valueOf(productSet.getInt(1)); intCtr++;
					}
					else{
						strItemID += String.valueOf(productSet.getInt(1)); intCtr++;
					}
				}
				
				if(!strItemID.equals(""))
					productList = new SearchProduct().searchList(strItemID.split(","), Product.getAllProduct());
				strItemID = "";
				intCtr = 0;
				
				services.setInt(1, this.intID);
				serviceSet = services.executeQuery();
			
				while(serviceSet.next()){
					
					if(intCtr != 0){
						strItemID += "," + String.valueOf(serviceSet.getInt(1)); intCtr++;
					}
					else{
						strItemID += String.valueOf(serviceSet.getInt(1)); intCtr++;
					}
				}
				
				if(!strItemID.equals(""))
					serviceList = new SearchService().searchList(strItemID.split(","), Service.getAllService());
				strItemID = "";
				intCtr = 0;
				
				packages.setInt(1, this.intID);
				packageSet = packages.executeQuery();
			
				while(serviceSet.next()){
					
					if(intCtr != 0){
						strItemID += "," + String.valueOf(packageSet.getInt(1)); intCtr++;
					}
					else{
						strItemID += String.valueOf(packageSet.getInt(1)); intCtr++;
					}
				}
				
				if(!strItemID.equals(""))
					packageList = new SearchPackage().searchList(strItemID.split(","), Package.getAllPackage());
				strItemID = "";
				intCtr = 0;
				
				promos.setInt(1, this.intID);
				promoSet = promos.executeQuery();
			
				while(serviceSet.next()){
					
					if(intCtr != 0){
						strItemID += "," + String.valueOf(promoSet.getInt(1)); intCtr++;
					}
					else{
						strItemID += String.valueOf(promoSet.getInt(1)); intCtr++;
					}
				}
				
				if(!strItemID.equals(""))
					promoList = new SearchPromo().searchList(strItemID.split(","), Promo.getAllPromo());
				strItemID = "";
				intCtr = 0;
				
				Discount discount = new Discount(intID, strName, strDesc, strGuide, intType, dblAmount, productList, serviceList, packageList, promoList, status);
				
				discountList.add(discount);
			}
			
			return discountList;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean createDiscount(Discount discount) {
		
		Connection con = jdbc.getConnection();
		String strQuery = "CALL createDiscount(?, ? ,?, ?, ?)";
		
		try{
			PreparedStatement pre = con.prepareStatement(strQuery);
			pre.setString(1, discount.getStrDiscountName());
			pre.setString(2, discount.getStrDiscountDesc());
			pre.setString(3, discount.getStrDiscountGuidelines());
			pre.setInt(4, discount.getIntDiscountType());
			pre.setDouble(5, discount.getDblDiscountAmount());
			
			pre.execute();
			pre.close();
			con.close();
			
			return true;
			
		}
		catch(Exception e){
			System.out.println(e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public boolean updateDiscount(Discount discount) {
		
		Connection con = jdbc.getConnection();
		String query = "CALL updateDiscount(?, ?, ?, ?, ?)";
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			pre.setInt(1, discount.getIntDiscountID());
			pre.setString(2, discount.getStrDiscountName());
			pre.setString(3, discount.getStrDiscountDesc());
			pre.setString(4, discount.getStrDiscountGuidelines());
			pre.setInt(5, discount.getIntDiscountType());
			pre.setDouble(6, discount.getDblDiscountAmount());
			pre.execute();
			pre.close();
			con.close();
			
			return true;
		}
		catch(Exception e){
			System.out.println(e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public boolean deactivateDiscount(int intDiscountID) {

		Connection con = jdbc.getConnection();
		String query = "UPDATE tblDiscount SET intStatus = 0 WHERE intDiscountID = ?";
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			pre.setInt(1, intDiscountID);

			pre.execute();
			pre.close();
			con.close();
			
			return true;
		}
		catch(Exception e){
			System.out.println(e.fillInStackTrace());
			return false;
		}
	}
	
}
