package com.gss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.Statement;

import com.gss.connection.JDBCConnection;
import com.gss.model.Product;
import com.gss.model.ProductPackage;
import com.gss.model.Promo;
import com.gss.model.Requirement;
import com.gss.model.Service;
import com.gss.model.ServicePackage;
import com.gss.model.PackagePackage;
import com.gss.service.PackageService;
import com.gss.service.PackageServiceImpl;
import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;
import com.gss.service.ServiceService;
import com.gss.service.ServiceServiceImpl;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.gss.model.Package;

public class PromoJDBCRepository implements PromoRepository{

	JDBCConnection jdbc = new JDBCConnection();
	
	@Override
	public String createPromo(Promo promo) {
		
		Connection con = jdbc.getConnection();
		String strQuery1 = "CALL createPromo(?, ?, ?, ?, ?, ?, ?);"; 	
		String strQuery2 = "CALL createProductPromo(?, ?, ?);";
		String strQuery3 = "CALL createServicePromo(?, ?, ?);";
		String strQuery4 = "CALL createPackagePromo(?, ?, ?);";
		ResultSet set1;
		
		int intID = 0;
		
		try{
			
			PreparedStatement pre = con.prepareStatement(strQuery1);
			pre.setString(1, promo.getStrPromoName());
			pre.setString(2, promo.getStrPromoDescription());
			pre.setString(3, promo.getStrPromoGuidelines());
			pre.setInt(4, promo.getIntMaxHeadCount());
			pre.setDouble(5, promo.getDblPromoPrice());
			pre.setString(6, promo.getStrPromoAvailability());
			pre.setInt(7, promo.getPromoType());
			
			set1 = pre.executeQuery();
			
			while(set1.next()){
				intID = set1.getInt(1);
			}
			
			pre.close();
			set1.close();
			
			for(int intCtr = 0; intCtr < promo.getProductList().size(); intCtr++){
				
				PreparedStatement pre2 = con.prepareStatement(strQuery2);
				ProductPackage product = promo.getProductList().get(intCtr);
				pre2.setInt(1, intID);
				pre2.setInt(2, product.getProduct().getIntProductID());
				pre2.setInt(3, product.getIntProductQuantity());
				
				pre2.execute();
				pre2.close();
			}
			
			for(int intCtr = 0; intCtr < promo.getServiceList().size(); intCtr++){
				
				PreparedStatement pre2 = con.prepareStatement(strQuery3);
				ServicePackage service = promo.getServiceList().get(intCtr);
				pre2.setInt(1, intID);
				pre2.setInt(2, service.getService().getIntServiceID());
				pre2.setInt(3, service.getIntQuantity());
				
				pre2.execute();
				pre2.close();
			}
			
			for(int intCtr = 0; intCtr < promo.getPackageList().size(); intCtr++){
				
				PreparedStatement pre2 = con.prepareStatement(strQuery4);
				PackagePackage packages = promo.getPackageList().get(intCtr);
				pre2.setInt(1, intID);
				pre2.setInt(2, packages.getPack().getIntPackageID());
				pre2.setInt(3, packages.getIntPackageQuantity());
				
				pre2.execute();
				pre2.close();
			}
			
			try{
				PreparedStatement requirements = con.prepareStatement("INSERT INTO tblPromoRequirement(intPromoID, intRequirementID) VALUES(?, ?);");
				
				for(int index = 0; index < promo.getRequirements().size(); index++){
					Requirement req = promo.getRequirements().get(index);
					requirements.setInt(1, intID);
					requirements.setInt(2, req.getIntRequirementID());
					requirements.addBatch();
				}
				
				requirements.executeBatch();
				requirements.close();
				
			}catch(NullPointerException ne){
				//do nothing
			}
			
			con.close();
			return "success";
		}
		catch(MySQLIntegrityConstraintViolationException e){
			return "existing";
		} catch (SQLException e) {
			e.printStackTrace();
			return "failed";
		}
	}

	@Override
	public String updatePromo(Promo promo) {
		
		Connection con = jdbc.getConnection();
		String query1 = "CALL updatePromo(?, ?, ?, ?, ?, ?, ?, ?)";
		String delete = "CALL deleteOldDetail(?)";
		String query = "CALL createProductPromo(?, ?, ?);";
		String strQuery2 = "INSERT INTO tblProductPromo(intPromoID, intProductID, intProductQuantity, intPromoStatus) VALUES (?, ?, ?, 1);";
		String strQuery3 = "CALL createServicePromo(?, ?, ?);";
		String strQuery4 = "CALL createPackagePromo(?, ?, ?);";
		int intID = promo.getIntPromoID();
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query1);
			pre.setInt(1, promo.getIntPromoID());
			pre.setString(2, promo.getStrPromoName());
			pre.setString(3, promo.getStrPromoDescription());
			pre.setString(4, promo.getStrPromoGuidelines());
			pre.setInt(5, promo.getIntMaxHeadCount());
			pre.setDouble(6, promo.getDblPromoPrice());
			pre.setString(7, promo.getStrPromoAvailability());
			pre.setInt(8, promo.getPromoType());
			
			pre.execute();
			PreparedStatement deleteStatement = con.prepareStatement(delete);
			deleteStatement.setInt(1, intID);
			deleteStatement.execute();
			deleteStatement.close();
			
			for(int intCtr = 0; intCtr < promo.getProductList().size(); intCtr++){
				
				PreparedStatement pre2 = con.prepareStatement(query);
				ProductPackage product = promo.getProductList().get(intCtr);
				pre2.setInt(1, intID);
				pre2.setInt(2, product.getProduct().getIntProductID());
				pre2.setInt(3, product.getIntProductQuantity());
				
				pre2.execute();
				pre2.close();
			}
			
			for(int intCtr = 0; intCtr < promo.getServiceList().size(); intCtr++){
				
				PreparedStatement pre2 = con.prepareStatement(strQuery3);
				ServicePackage service = promo.getServiceList().get(intCtr);
				pre2.setInt(1, intID);
				pre2.setInt(2, service.getService().getIntServiceID());
				pre2.setInt(3, service.getIntQuantity());
				
				pre2.execute();
				pre2.close();
			}
			
			for(int intCtr = 0; intCtr < promo.getPackageList().size(); intCtr++){
				
				PreparedStatement pre2 = con.prepareStatement(strQuery4);
				PackagePackage packages = promo.getPackageList().get(intCtr);
				pre2.setInt(1, intID);
				pre2.setInt(2, packages.getPack().getIntPackageID());
				pre2.setInt(3, packages.getIntPackageQuantity());
				
				pre2.execute();
				pre2.close();
			}
			
			try{
				PreparedStatement requirements = con.prepareStatement("INSERT INTO tblPromoRequirement(intPromoID, intRequirementID) VALUES(?, ?);");
				
				for(int index = 0; index < promo.getRequirements().size(); index++){
					Requirement req = promo.getRequirements().get(index);
					requirements.setInt(1, intID);
					requirements.setInt(2, req.getIntRequirementID());
					requirements.addBatch();
				}
				
				requirements.executeBatch();
				requirements.close();
				
			}catch(NullPointerException ne){
				//do nothing
			}
			
			return "success";
		}catch(MySQLIntegrityConstraintViolationException m){
			return "existing";
		}
		catch(SQLException e){
			e.printStackTrace();
			return "failed";
		}
	}

	@Override
	public List<Promo> getAllPromo() {
		
		ServiceService serviceService 		= new ServiceServiceImpl();
		ProductService productService 		= new ProductServiceImpl();
		PackageService packageService 		= new PackageServiceImpl();
		
		List<Promo> promoList 				= new ArrayList<Promo>();
		
		String query 						= "SELECT * FROM tblPromo WHERE intPromoStatus = 1;";
		String getPromoDiscount				= "CALL getPromoDiscount(?)";
		String query2 						= "SELECT * FROM tblServicePromo WHERE intPromoID = ? AND intPromoStatus = 1;";
		String query3 						= "SELECT * FROM tblProductPromo WHERE intPromoID = ? AND intPromoStatus = 1;";
		String query4 						= "SELECT * FROM tblPackagePromo WHERE intPromoID = ? AND intStatus = 1;";

		Connection con = jdbc.getConnection();
		
		try{
			PreparedStatement pre 			= con.prepareStatement(query);
			PreparedStatement discounts		= con.prepareStatement(getPromoDiscount);
			ResultSet set 					= pre.executeQuery();
			ResultSet discountedPrice = null;
			
			while(set.next()){
				
				List<Product> productList = productService.getAllProductsNoImage();
				List<Service> serviceList = serviceService.getAllServiceNoImage();
				List<Package> packageList = packageService.getAllPackage();
				
				List<ProductPackage> prodPack = new ArrayList<ProductPackage>();
				List<ServicePackage> servPack = new ArrayList<ServicePackage>();
				List<PackagePackage> packPack = new ArrayList<PackagePackage>();
				
				int intID = set.getInt(1);
				String name = set.getString(2);
				String desc = set.getString(3);
				String guide = set.getString(4);
				int max = set.getInt(5);
				double price = set.getDouble(6);
				String avail = set.getString(7);
				int status = set.getInt(8);
				int type = set.getInt(9);
				
				
				discounts.setInt(1, intID);
				discountedPrice = discounts.executeQuery();
				
				while(discountedPrice.next()){
				
					price = discountedPrice.getDouble(1);
				}
				
				
				PreparedStatement pre5 = con.prepareStatement(query2);
				pre5.setInt(1, intID);
				ResultSet set4 = pre5.executeQuery();
				
				while(set4.next()){
					
					int intID1 = set4.getInt(1);
					int intPackage = set4.getInt(2);
					int intService = set4.getInt(3);
					int intQuantity1 = set4.getInt(4);
					
					for(int i = 0; i < serviceList.size(); i++){
						Service service1 = serviceList.get(i);
						if(intService == service1.getIntServiceID()){
							ServicePackage service2 = new ServicePackage(intID1, intPackage, service1, intQuantity1, 1);
							servPack.add(service2);
						}
					}
				}
				
				PreparedStatement pre6 = con.prepareStatement(query3);
				pre6.setInt(1, intID);
				ResultSet set10 = pre6.executeQuery();
				
				while(set10.next()){
					
					int intID1 = set10.getInt(1);
					int intPackage = set10.getInt(2);
					int intProduct = set10.getInt(3);
					int intQuantity1 = set10.getInt(4);
					
					for(int i = 0; i < productList.size(); i++){
						Product product1 = productList.get(i);
						if(intProduct == product1.getIntProductID()){
							ProductPackage service2 = new ProductPackage(intID1, intPackage, product1, intQuantity1, 1);
							prodPack.add(service2);
						}
					}
				}
				
				PreparedStatement packageStatement = con.prepareStatement(query4);
				packageStatement.setInt(1, intID);
				ResultSet packageSet = packageStatement.executeQuery();
				
				while(packageSet.next()){
					
					int intID1 = packageSet.getInt(1);
					int intPromoID = packageSet.getInt(2);
					int intPackageID = packageSet.getInt(3);
					int intQuantity = packageSet.getInt(4);
					
					for(int i = 0; i < packageList.size(); i++){
						Package pack = packageList.get(i);
						if(intPackageID == pack.getIntPackageID()){
							PackagePackage packages = new PackagePackage(intID1, intPromoID, pack, intQuantity, 1);
							packPack.add(packages);
						}
					}
				}
				
				PreparedStatement statement = con.prepareStatement("CALL getPromoRequirement(?);");
				statement.setInt(1, intID);
				ResultSet requirementSet = statement.executeQuery();
				
				List<Requirement> requirements = new ArrayList<Requirement>();
				
				while(requirementSet.next()){
					Requirement requirement = new Requirement(requirementSet.getInt(1), requirementSet.getString(2), 1);
					requirements.add(requirement);
				}
				
				statement.close();
				requirementSet.close();
				
				System.out.print(type + "ssd");
				
				Promo promo = new Promo(intID, name, desc, guide, price, max, servPack, prodPack, packPack, avail, status, type);
				promo.setRequirements(requirements);
				promo.setStringPrice(String.format("%.2f", price));
				promoList.add(promo);
			}
			discounts.close();
			discountedPrice.close();
			set.close();
			pre.close();
			
			return promoList;
		}
		catch(Exception e){
			System.out.println(e.fillInStackTrace());
			return promoList;
		}
	}

	@Override
	public boolean deactivatePromo(int promoID) {
		
		Connection con = jdbc.getConnection();
		String query = "UPDATE tblPromo SET intPromoStatus = 0 WHERE intPromoID = ? ";
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			pre.setInt(1, promoID);
			
			pre.execute();
			
			pre.close();
			con.close();
			
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public List<Promo> getAllPromoNoDetails(){
		
		List<Promo> promoList 				= new ArrayList<Promo>();
		
		String query 						= "SELECT * FROM tblPromo WHERE intPromoStatus = 1;";
		String getPromoDiscount				= "CALL getPromoDiscount(?)";

		Connection con = jdbc.getConnection();
		
		try{
			PreparedStatement pre 			= con.prepareStatement(query);
			PreparedStatement discounts		= con.prepareStatement(getPromoDiscount);
			ResultSet set 					= pre.executeQuery();
			ResultSet discountedPrice = null;
			
			while(set.next()){
				
				List<ProductPackage> prodPack = new ArrayList<ProductPackage>();
				List<ServicePackage> servPack = new ArrayList<ServicePackage>();
				List<PackagePackage> packPack = new ArrayList<PackagePackage>();
				
				int intID = set.getInt(1);
				String name = set.getString(2);
				String desc = set.getString(3);
				String guide = set.getString(4);
				int max = set.getInt(5);
				double price = set.getDouble(6);
				String avail = set.getString(7);
				int status = set.getInt(8);
				int type = set.getInt(9);
				
				discounts.setInt(1, intID);
				discountedPrice = discounts.executeQuery();
				
				while(discountedPrice.next()){
				
					price = discountedPrice.getDouble(1);
				}
				
				PreparedStatement statement = con.prepareStatement("CALL getPromoRequirement(?);");
				statement.setInt(1, intID);
				ResultSet requirementSet = statement.executeQuery();
				
				List<Requirement> requirements = new ArrayList<Requirement>();
				
				while(requirementSet.next()){
					Requirement requirement = new Requirement(requirementSet.getInt(1), requirementSet.getString(2), 1);
					requirements.add(requirement);
				}
				
				statement.close();
				requirementSet.close();
				
				Promo promo = new Promo(intID, name, desc, guide, price, max, servPack, prodPack, packPack, avail, status, type);
				promo.setRequirements(requirements);
				promo.setStringPrice(String.format("%.2f", price));
				promoList.add(promo);
			}
			discounts.close();
			try{
				discountedPrice.close();
			}catch(NullPointerException n){
				
			}
			set.close();
			pre.close();
			
			return promoList;
		}
		catch(SQLException e){
			e.printStackTrace();
			return promoList;
		}
	}

	public Promo getPromoByID(int promoID){
		
		ServiceService serviceService 		= new ServiceServiceImpl();
		ProductService productService 		= new ProductServiceImpl();
		PackageService packageService 		= new PackageServiceImpl();
		
		List<Promo> promoList 				= new ArrayList<Promo>();
		
		String query 						= "SELECT * FROM tblPromo WHERE intPromoID = ?;";
		String getPromoDiscount				= "CALL getPromoDiscount(?)";
		String query2 						= "SELECT * FROM tblServicePromo WHERE intPromoID = ? AND intPromoStatus = 1;";
		String query3 						= "SELECT * FROM tblProductPromo WHERE intPromoID = ? AND intPromoStatus = 1;";
		String query4 						= "SELECT * FROM tblPackagePromo WHERE intPromoID = ? AND intStatus = 1;";

		Connection con = jdbc.getConnection();
		
		try{
			PreparedStatement pre 			= con.prepareStatement(query);
			PreparedStatement discounts		= con.prepareStatement(getPromoDiscount);
			pre.setInt(1, promoID);
			ResultSet set 					= pre.executeQuery();
			ResultSet discountedPrice = null;
			
			while(set.next()){
				
				List<Product> productList = productService.getAllProductsNoImage();
				List<Service> serviceList = serviceService.getAllServiceNoImage();
				List<Package> packageList = packageService.getAllPackage();
				
				List<ProductPackage> prodPack = new ArrayList<ProductPackage>();
				List<ServicePackage> servPack = new ArrayList<ServicePackage>();
				List<PackagePackage> packPack = new ArrayList<PackagePackage>();
				
				int intID = set.getInt(1);
				String name = set.getString(2);
				String desc = set.getString(3);
				String guide = set.getString(4);
				int max = set.getInt(5);
				double price = set.getDouble(6);
				String avail = set.getString(7);
				int status = set.getInt(8);
				int type = set.getInt(9);
				
				discounts.setInt(1, intID);
				discountedPrice = discounts.executeQuery();
				
				while(discountedPrice.next()){
				
					price = discountedPrice.getDouble(1);
				}
				
				
				PreparedStatement pre5 = con.prepareStatement(query2);
				pre5.setInt(1, intID);
				ResultSet set4 = pre5.executeQuery();
				
				while(set4.next()){
					
					int intID1 = set4.getInt(1);
					int intPackage = set4.getInt(2);
					int intService = set4.getInt(3);
					int intQuantity1 = set4.getInt(4);
					
					for(int i = 0; i < serviceList.size(); i++){
						Service service1 = serviceList.get(i);
						if(intService == service1.getIntServiceID()){
							ServicePackage service2 = new ServicePackage(intID1, intPackage, service1, intQuantity1, 1);
							servPack.add(service2);
						}
					}
				}
				
				PreparedStatement pre6 = con.prepareStatement(query3);
				pre6.setInt(1, intID);
				ResultSet set10 = pre6.executeQuery();
				
				while(set10.next()){
					
					int intID1 = set10.getInt(1);
					int intPackage = set10.getInt(2);
					int intProduct = set10.getInt(3);
					int intQuantity1 = set10.getInt(4);
					
					for(int i = 0; i < productList.size(); i++){
						Product product1 = productList.get(i);
						if(intProduct == product1.getIntProductID()){
							ProductPackage service2 = new ProductPackage(intID1, intPackage, product1, intQuantity1, 1);
							prodPack.add(service2);
						}
					}
				}
				
				PreparedStatement packageStatement = con.prepareStatement(query4);
				packageStatement.setInt(1, intID);
				ResultSet packageSet = packageStatement.executeQuery();
				
				while(packageSet.next()){
					
					int intID1 = packageSet.getInt(1);
					int intPromoID = packageSet.getInt(2);
					int intPackageID = packageSet.getInt(3);
					int intQuantity = packageSet.getInt(4);
					
					for(int i = 0; i < packageList.size(); i++){
						Package pack = packageList.get(i);
						if(intPackageID == pack.getIntPackageID()){
							PackagePackage packages = new PackagePackage(intID1, intPromoID, pack, intQuantity, 1);
							packPack.add(packages);
						}
					}
				}
				
				PreparedStatement statement = con.prepareStatement("CALL getPromoRequirement(?);");
				statement.setInt(1, intID);
				ResultSet requirementSet = statement.executeQuery();
				
				List<Requirement> requirements = new ArrayList<Requirement>();
				
				while(requirementSet.next()){
					Requirement requirement = new Requirement(requirementSet.getInt(1), requirementSet.getString(2), 1);
					requirements.add(requirement);
				}
				
				statement.close();
				requirementSet.close();
				
				Promo promo = new Promo(intID, name, desc, guide, price, max, servPack, prodPack, packPack, avail, status, type);
				promo.setStringPrice(String.format("%.2f", price));
				promo.setRequirements(requirements);
				promoList.add(promo);
			}
			discounts.close();
			discountedPrice.close();
			set.close();
			pre.close();
			
			return promoList.get(0);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Promo> queryAllPromo() {
		
		List<Promo> promoList 				= new ArrayList<Promo>();
		
		String query 						= "SELECT * FROM tblPromo ORDER BY strPromoName ASC;";
		String getPromoDiscount				= "CALL getPromoDiscount(?)";

		Connection con = jdbc.getConnection();
		
		try{
			PreparedStatement pre 			= con.prepareStatement(query);
			PreparedStatement discounts		= con.prepareStatement(getPromoDiscount);
			ResultSet set 					= pre.executeQuery();
			ResultSet discountedPrice = null;
			
			while(set.next()){
				
				List<ProductPackage> prodPack = new ArrayList<ProductPackage>();
				List<ServicePackage> servPack = new ArrayList<ServicePackage>();
				List<PackagePackage> packPack = new ArrayList<PackagePackage>();
				
				int intID = set.getInt(1);
				String name = set.getString(2);
				String desc = set.getString(3);
				String guide = set.getString(4);
				int max = set.getInt(5);
				double price = set.getDouble(6);
				String avail = set.getString(7);
				int status = set.getInt(8);
				int type = set.getInt(9);
				
				discounts.setInt(1, intID);
				discountedPrice = discounts.executeQuery();
				
				while(discountedPrice.next()){
				
					price = discountedPrice.getDouble(1);
				}
				
				Promo promo = new Promo(intID, name, desc, guide, price, max, servPack, prodPack, packPack, avail, status, type);
				promo.setStringPrice(String.format("%.2f", price));
				promoList.add(promo);
			}
			discounts.close();
			discountedPrice.close();
			set.close();
			pre.close();
			
			return promoList;
		}
		catch(Exception e){
			e.printStackTrace();
			return promoList;
		}
	}
	
	public void checkExpiredPomo() throws SQLException{
		Connection con = jdbc.getConnection();
		
		String query = "CALL checkExpiredPromo();";
		
		try{
			
			PreparedStatement statement = con.prepareStatement(query);
			
			statement.execute();
			
			statement.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			con.close();
		}
	
	}	
	
	public static List<Promo> getPromoByType	(String Type){
		
		List<Promo> promoList 				= new ArrayList<Promo>();
		
		String query 						= "SELECT * FROM tblPromo WHERE intPromoStatus = 1;";
		String getPromoDiscount				= "CALL getPromoDiscount(?)";

		Connection con = new JDBCConnection().getConnection();
		
		try{
			PreparedStatement pre 			= con.prepareStatement(query);
			PreparedStatement discounts		= con.prepareStatement(getPromoDiscount);
			ResultSet set 					= pre.executeQuery();
			ResultSet discountedPrice = null;
			
			while(set.next()){
				
				List<ProductPackage> prodPack = new ArrayList<ProductPackage>();
				List<ServicePackage> servPack = new ArrayList<ServicePackage>();
				List<PackagePackage> packPack = new ArrayList<PackagePackage>();
				
				int intID = set.getInt(1);
				String name = set.getString(2);
				String desc = set.getString(3);
				String guide = set.getString(4);
				int max = set.getInt(5);
				double price = set.getDouble(6);
				String avail = set.getString(7);
				int status = set.getInt(8);
				int type = set.getInt(9);
				
				discounts.setInt(1, intID);
				discountedPrice = discounts.executeQuery();
				
				while(discountedPrice.next()){
				
					price = discountedPrice.getDouble(1);
				}
				
				PreparedStatement statement = con.prepareStatement("CALL getPromoRequirement(?);");
				statement.setInt(1, intID);
				ResultSet requirementSet = statement.executeQuery();
				
				List<Requirement> requirements = new ArrayList<Requirement>();
				
				while(requirementSet.next()){
					Requirement requirement = new Requirement(requirementSet.getInt(1), requirementSet.getString(2), 1);
					requirements.add(requirement);
				}
				
				statement.close();
				requirementSet.close();
				
				Promo promo = new Promo(intID, name, desc, guide, price, max, servPack, prodPack, packPack, avail, status, type);
				promo.setRequirements(requirements);
				promo.setStringPrice(String.format("%.2f", price));
				promoList.add(promo);
			}
			discounts.close();
			discountedPrice.close();
			set.close();
			pre.close();
			
			return promoList;
		}
		catch(Exception e){
			e.printStackTrace();
			return promoList;
		}
	}
}
