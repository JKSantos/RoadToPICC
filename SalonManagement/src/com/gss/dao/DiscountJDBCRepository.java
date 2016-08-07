package com.gss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	private String applicability;
	private String strName;
	private String strDesc;
	private String strGuide;
	private int intType;
	private double dblAmount;
	private int status;
	
	@Override
	public List<Discount> getAllDiscount() {
		
		Connection con 						= jdbc.getConnection();
		List<Discount> 	discountList 		= new ArrayList<Discount>();
		String strQuery 					= "SELECT * FROM tblDiscount WHERE intStatus = 1;";
		String getProducts 					= "SELECT intProductID FROM tblDiscountProduct WHERE intDiscountID = ?;";
		String getServices 					= "SELECT intServiceID FROM tblDiscountService WHERE intDiscountID = ?;";
		String getPromos 					= "SELECT intPromoID FROM tblDiscountPromo WHERE intDiscountID = ?;";
		String getPackages 					= "SELECT intPackageID FROM tblDiscountPackage WHERE intDiscountID = ?;";
		
		List<Product> savedProducts 		= Product.getAllProduct();
		List<Service> savedServices 		= Service.getAllService();
		List<Package> savedPackage 			= Package.getAllPackage();
		List<Promo> savedPromos 			= Promo.getAllPromo();
		
		try{
			
			PreparedStatement preDiscounts 			= con.prepareStatement(strQuery);
			PreparedStatement preProducts			= con.prepareStatement(getProducts);
			PreparedStatement preServices			= con.prepareStatement(getServices);
			PreparedStatement prePromos				= con.prepareStatement(getPromos);
			PreparedStatement prePackages			= con.prepareStatement(getPackages);
			
			ResultSet discountSet = null;
			ResultSet productSet = null;
			ResultSet serviceSet = null;
			ResultSet promoSet = null;
			ResultSet packageSet = null;
			
			discountSet = preDiscounts.executeQuery();
			
			while(discountSet.next()){
				
				List<Product> productList 			= new ArrayList<Product>();
				List<Service> serviceList 			= new ArrayList<Service>();
				List<Package> packageList 			= new ArrayList<Package>();
				List<Promo> promoList 				= new ArrayList<Promo>();
				
				this.intID = discountSet.getInt(1);
				this.applicability = discountSet.getString(2);
				this.strName = discountSet.getString(3);
				this.strDesc = discountSet.getString(4);
				this.strGuide = discountSet.getString(5);
				this.intType = discountSet.getInt(6);
				this.dblAmount = discountSet.getDouble(7);
				this.status = discountSet.getInt(8);
				
				//Products
				preProducts.setInt(1, this.intID);
				productSet = preProducts.executeQuery();
				
				while(productSet.next()){
					
					Product product = new SearchProduct().search(productSet.getInt(1), savedProducts);
					productList.add(product);
				}
				
				preServices.setInt(1, this.intID);
				serviceSet = preServices.executeQuery();
				
				while(serviceSet.next()){
					
					Service service = new SearchService().search(serviceSet.getInt(1), savedServices);
					serviceList.add(service);
				}
				
				prePackages.setInt(1, this.intID);
				packageSet = prePackages.executeQuery();
				
				while(packageSet.next()){
					
					Package packagee = new SearchPackage().search(packageSet.getInt(1), savedPackage);
					packageList.add(packagee);
				}
				
				prePromos.setInt(1, this.intID);
				promoSet = prePromos.executeQuery();
				
				while(promoSet.next()){
					
					Promo promo = new SearchPromo().search(promoSet.getInt(1), savedPromos);
					promoList.add(promo);
				}
				
				
				
				Discount discount = new Discount(this.intID, this.applicability, this.strName, this.strDesc, this.strGuide, this.intType, this.dblAmount, productList, serviceList, packageList, promoList, this.status);
			
				discountList.add(discount);
			}
			
			preProducts.close();
			preServices.close();
			prePromos.close();
			prePackages.close();
			productSet.close();
			serviceSet.close();
			promoSet.close();
			packageSet.close();
	
			preDiscounts.close();
			discountSet.close();
			con.close();
			return discountList;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean createDiscount(Discount discount) throws SQLException {
		
		Connection con 				= jdbc.getConnection();
		String strQuery 			= "CALL createDiscount(?, ? ,?, ?, ?, ?)";
		String createProducts		= "CALL createDiscProd(?, ?);";
		String createServices		= "CALL createDiscServ(?, ?);";
		String createPackage		= "CALL createDiscPack(?, ?);";
		String createPromo			= "CALL createDiscPromo(?, ?)";
		int intDiscountID			= 0;
		
		try{
			con.setAutoCommit(false);
			PreparedStatement pre 			= con.prepareStatement(strQuery);
			PreparedStatement preProduct	= con.prepareStatement(createProducts);
			PreparedStatement preService	= con.prepareStatement(createServices);
			PreparedStatement prePackage	= con.prepareStatement(createPackage);
			PreparedStatement prePromo		= con.prepareStatement(createPromo);
			
			ResultSet setDiscountID;
			
			pre.setString(1, discount.getApplicability());
			pre.setString(2, discount.getStrDiscountName());
			pre.setString(3, discount.getStrDiscountDesc());
			pre.setString(4, discount.getStrDiscountGuidelines());
			pre.setInt(5, discount.getIntDiscountType());
			pre.setDouble(6, discount.getDblDiscountAmount());
			
			setDiscountID = pre.executeQuery();
			
			while(setDiscountID.next()){
				intDiscountID = setDiscountID.getInt(1);
			}
			
			pre.close();
			setDiscountID.close();
			
			for(int i = 0; i < discount.getProductList().size(); i++){
				preProduct.setInt(1, intDiscountID);
				preProduct.setInt(2, discount.getProductList().get(i).getIntProductID());
				preProduct.addBatch();
			}
			
			preProduct.executeBatch();
			preProduct.close();
			
			for(int i = 0; i < discount.getServiceList().size(); i++){
				preService.setInt(1, intDiscountID);
				preService.setInt(2, discount.getServiceList().get(i).getIntServiceID());
				preService.addBatch();
			}
			
			preService.executeBatch();
			preService.close();
			
			for(int i = 0; i < discount.getPackageList().size(); i++){
				prePackage.setInt(1, intDiscountID);
				prePackage.setInt(2, discount.getPackageList().get(i).getIntPackageID());
				prePackage.addBatch();
			}
			
			prePackage.executeBatch();
			prePackage.close();
			
			for(int i = 0; i < discount.getPromoList().size(); i++){
				prePromo.setInt(1, intDiscountID);
				prePromo.setInt(2, discount.getPromoList().get(i).getIntPromoID());
				prePromo.addBatch();
			}
			
			prePromo.executeBatch();
			prePromo.close();
			
			con.commit();
			con.close();
			return true;
			
		}
		catch(Exception e){
			e.printStackTrace();
			con.rollback();
			return false;
		}
	}

	@Override
	public boolean updateDiscount(Discount discount) throws SQLException {
		
		Connection con 				= jdbc.getConnection();
		String query 				= "CALL updateDiscount(?, ?, ?, ?, ?, ?, ?)";
		String createProducts		= "CALL createDiscProd(?, ?);";
		String createServices		= "CALL createDiscServ(?, ?);";
		String createPackage		= "CALL createDiscPack(?, ?);";
		String createPromo			= "CALL createDiscPromo(?, ?)";
		String deleteProducts		= "DELETE FROM tblDiscountProduct WHERE intDiscountID = ?;";
		String deleteServices		= "DELETE FROM tblDiscountService WHERE intDiscountID = ?;";
		String deletePackages		= "DELETE FROM tblDiscountPackage WHERE intDiscountID = ?;";
		String deletePromos			= "DELETE FROM tblDiscountPromo WHERE intDiscountID = ?;";
		int intDiscountID			= discount.getIntDiscountID();
		
		try{
			
			con.setAutoCommit(false);
			PreparedStatement preProduct	= con.prepareStatement(createProducts);
			PreparedStatement preService	= con.prepareStatement(createServices);
			PreparedStatement prePackage	= con.prepareStatement(createPackage);
			PreparedStatement prePromo		= con.prepareStatement(createPromo);
			PreparedStatement preDelProduct	= con.prepareStatement(deleteProducts);
			PreparedStatement preDelService	= con.prepareStatement(deleteServices);
			PreparedStatement preDelPackage	= con.prepareStatement(deletePackages);
			PreparedStatement preDelPromo 	= con.prepareStatement(deletePromos);
			PreparedStatement pre 			= con.prepareStatement(query);
			
			pre.setInt(1, discount.getIntDiscountID());
			pre.setString(2, discount.getApplicability());
			pre.setString(3, discount.getStrDiscountName());
			pre.setString(4, discount.getStrDiscountDesc());
			pre.setString(5, discount.getStrDiscountGuidelines());
			pre.setInt(6, discount.getIntDiscountType());
			pre.setDouble(7, discount.getDblDiscountAmount());
			pre.execute();
			pre.close();
			
			preDelProduct.setInt(1, intDiscountID);
			preDelService.setInt(1, intDiscountID);
			preDelPackage.setInt(1, intDiscountID);
			preDelPromo.setInt(1, intDiscountID);
			
			preDelProduct.execute();
			preDelService.execute();
			preDelPackage.execute();
			preDelPromo.execute();
			
			preDelProduct.close();
			preDelService.close();
			preDelPackage.close();
			preDelPromo.close();
			
			for(int i = 0; i < discount.getProductList().size(); i++){
				preProduct.setInt(1, intDiscountID);
				preProduct.setInt(2, discount.getProductList().get(i).getIntProductID());
				preProduct.addBatch();
			}
			
			preProduct.executeBatch();
			preProduct.close();
			
			for(int i = 0; i < discount.getServiceList().size(); i++){
				preService.setInt(1, intDiscountID);
				preService.setInt(2, discount.getServiceList().get(i).getIntServiceID());
				preService.addBatch();
			}
			
			preService.executeBatch();
			preService.close();
			
			for(int i = 0; i < discount.getPackageList().size(); i++){
				prePackage.setInt(1, intDiscountID);
				prePackage.setInt(2, discount.getPackageList().get(i).getIntPackageID());
				prePackage.addBatch();
			}
			
			prePackage.executeBatch();
			prePackage.close();
			
			for(int i = 0; i < discount.getPromoList().size(); i++){
				prePromo.setInt(1, intDiscountID);
				prePromo.setInt(2, discount.getPromoList().get(i).getIntPromoID());
				prePromo.addBatch();
			}
			
			prePromo.executeBatch();
			prePromo.close();
			
			con.commit();
			con.close();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			con.rollback();
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
	
	public List<Discount> getAllDiscountNoDetails(){
		
		Connection con 						= jdbc.getConnection();
		List<Discount> 	discountList 		= new ArrayList<Discount>();
		String strQuery 					= "SELECT * FROM tblDiscount WHERE intStatus = 1;";
		
		try{
			
			PreparedStatement preDiscounts 			= con.prepareStatement(strQuery);
			
			ResultSet discountSet = null;
			
			discountSet = preDiscounts.executeQuery();
			
			while(discountSet.next()){
				
				List<Product> productList 			= new ArrayList<Product>();
				List<Service> serviceList 			= new ArrayList<Service>();
				List<Package> packageList 			= new ArrayList<Package>();
				List<Promo> promoList 				= new ArrayList<Promo>();
				
				this.intID = discountSet.getInt(1);
				this.applicability = discountSet.getString(2);
				this.strName = discountSet.getString(3);
				this.strDesc = discountSet.getString(4);
				this.strGuide = discountSet.getString(5);
				this.intType = discountSet.getInt(6);
				this.dblAmount = discountSet.getDouble(7);
				this.status = discountSet.getInt(8);
				
				Discount discount = new Discount(this.intID, this.applicability, this.strName, this.strDesc, this.strGuide, this.intType, this.dblAmount, productList, serviceList, packageList, promoList, this.status);
			
				discountList.add(discount);
			}
	
			preDiscounts.close();
			discountSet.close();
			con.close();
			return discountList;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public Discount getDiscountByID(int discountID){
		
		Connection con 						= jdbc.getConnection();
		List<Discount> 	discountList 		= new ArrayList<Discount>();
		String strQuery 					= "SELECT * FROM tblDiscount WHERE intDiscountID = ?;";
		String getProducts 					= "SELECT intProductID FROM tblDiscountProduct WHERE intDiscountID = ?;";
		String getServices 					= "SELECT intServiceID FROM tblDiscountService WHERE intDiscountID = ?;";
		String getPromos 					= "SELECT intPromoID FROM tblDiscountPromo WHERE intDiscountID = ?;";
		String getPackages 					= "SELECT intPackageID FROM tblDiscountPackage WHERE intDiscountID = ?;";
		
		List<Product> savedProducts 		= Product.getAllProduct();
		List<Service> savedServices 		= Service.getAllService();
		List<Package> savedPackage 			= Package.getAllPackage();
		List<Promo> savedPromos 			= Promo.getAllPromo();
		
		try{
			
			PreparedStatement preDiscounts 			= con.prepareStatement(strQuery);
			PreparedStatement preProducts			= con.prepareStatement(getProducts);
			PreparedStatement preServices			= con.prepareStatement(getServices);
			PreparedStatement prePromos				= con.prepareStatement(getPromos);
			PreparedStatement prePackages			= con.prepareStatement(getPackages);
			
			ResultSet discountSet = null;
			ResultSet productSet = null;
			ResultSet serviceSet = null;
			ResultSet promoSet = null;
			ResultSet packageSet = null;
			
			preDiscounts.setInt(1, discountID);
			discountSet = preDiscounts.executeQuery();
			
			while(discountSet.next()){
				
				List<Product> productList 			= new ArrayList<Product>();
				List<Service> serviceList 			= new ArrayList<Service>();
				List<Package> packageList 			= new ArrayList<Package>();
				List<Promo> promoList 				= new ArrayList<Promo>();
				
				this.intID = discountSet.getInt(1);
				this.applicability = discountSet.getString(2);
				this.strName = discountSet.getString(3);
				this.strDesc = discountSet.getString(4);
				this.strGuide = discountSet.getString(5);
				this.intType = discountSet.getInt(6);
				this.dblAmount = discountSet.getDouble(7);
				this.status = discountSet.getInt(8);
				
				//Products
				preProducts.setInt(1, this.intID);
				productSet = preProducts.executeQuery();
				
				while(productSet.next()){
					
					Product product = new SearchProduct().search(productSet.getInt(1), savedProducts);
					productList.add(product);
				}
				
				preServices.setInt(1, this.intID);
				serviceSet = preServices.executeQuery();
				
				while(serviceSet.next()){
					
					Service service = new SearchService().search(serviceSet.getInt(1), savedServices);
					serviceList.add(service);
				}
				
				prePackages.setInt(1, this.intID);
				packageSet = prePackages.executeQuery();
				
				while(packageSet.next()){
					
					Package packagee = new SearchPackage().search(packageSet.getInt(1), savedPackage);
					packageList.add(packagee);
				}
				
				prePromos.setInt(1, this.intID);
				promoSet = prePromos.executeQuery();
				
				while(promoSet.next()){
					
					Promo promo = new SearchPromo().search(promoSet.getInt(1), savedPromos);
					promoList.add(promo);
				}
				
				
				
				Discount discount = new Discount(this.intID, this.applicability, this.strName, this.strDesc, this.strGuide, this.intType, this.dblAmount, productList, serviceList, packageList, promoList, this.status);
			
				discountList.add(discount);
			}
			
			preProducts.close();
			preServices.close();
			prePromos.close();
			prePackages.close();
			productSet.close();
			serviceSet.close();
			promoSet.close();
			packageSet.close();
	
			preDiscounts.close();
			discountSet.close();
			con.close();
			return discountList.get(0);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
}
