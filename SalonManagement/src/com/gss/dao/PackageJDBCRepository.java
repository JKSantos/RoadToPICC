package com.gss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.Package;
import com.gss.model.Product;
import com.gss.model.ProductPackage;
import com.gss.model.Service;
import com.gss.model.ServicePackage;
import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;
import com.gss.service.ServiceService;
import com.gss.service.ServiceServiceImpl;
import com.gss.utilities.ProductPackageComparison;
import com.gss.utilities.SearchProduct;
import com.gss.utilities.SearchService;
import com.gss.utilities.ServicePackageComparison;

public class PackageJDBCRepository implements PackageRepository{

	private JDBCConnection jdbc = new JDBCConnection();
	private int intServicQuantity;
	
	@Override
	public boolean createPackage(Package pack) {
		
		Connection con = jdbc.getConnection();
		ResultSet set1;
		String strQuery1 = "CALL createPackage(?, ?, ?, ?, ?, ?, ?)"; 
		String strQuery2 = "CALL createProductPackage(?, ?, ?)";
		String strQuery3 = "CALL createServicePackage(?, ?, ?)";

		int intPackageID = 0;
		
		try{
			
			PreparedStatement pre1 = con.prepareStatement(strQuery1);
			pre1.setString(1, pack.getStrPackageName());
			pre1.setString(2, pack.getStrPackageDesc());
			pre1.setInt(3, pack.getIntPackageType());
			pre1.setInt(4, pack.getIntMaxHeadCount());
			pre1.setString(5, pack.getStrPackageAvailability());
			pre1.setDouble(6, pack.getDblPackagePrice());
			pre1.setInt(7, pack.getIntPackageStatus());
			
			set1 = pre1.executeQuery();
			
			while(set1.next()){
				intPackageID = set1.getInt(1);
			}
			
			pre1.close();
			
			for(int intCtr = 0; intCtr < pack.getServiceList().size(); intCtr++){
				ServicePackage servicePack = pack.getServiceList().get(intCtr);
				Service service = servicePack.getService();
				System.out.println(service.getIntServiceID());
				PreparedStatement pre2 = con.prepareStatement(strQuery3);
				pre2.setInt(1, intPackageID);
				pre2.setInt(2, service.getIntServiceID());
				pre2.setInt(3, servicePack.getIntQuantity());
				pre2.execute();
				pre2.close();
			}
			
			for(int intCtr = 0; intCtr < pack.getProductList().size(); intCtr++){
				ProductPackage productPack = pack.getProductList().get(intCtr);
				Product product = productPack.getProduct();
				
				PreparedStatement pre2 = con.prepareStatement(strQuery2);
				pre2.setInt(1, intPackageID);
				pre2.setInt(2, product.getIntProductID());
				pre2.setInt(3, productPack.getIntProductQuantity());
				pre2.execute();
				pre2.close();
			}

			con.close();
			return true;
			
		}
		catch(Exception e){
			System.out.println(e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public boolean updatePackage(Package pack) throws NullPointerException{
		
		Connection con = jdbc.getConnection();
		String strQuery1 = "CALL updatePackage(?, ?, ?, ?, ?, ?, ?)";
		String strQuery2 = "CALL createProductPackage(?, ?, ?)";
		String strQuery3 = "CALL createServicePackage(?, ?, ?)";
		String prohibitedQuery = "CALL deletePackageDetails(?);";
		
		try{
			PreparedStatement pre1 = con.prepareStatement(strQuery1);
			pre1.setInt(1, pack.getIntPackageID());
			pre1.setString(2, pack.getStrPackageName());
			pre1.setString(3, pack.getStrPackageDesc());
			pre1.setInt(4, pack.getIntPackageType());
			pre1.setInt(5, pack.getIntMaxHeadCount());
			pre1.setString(6, pack.getStrPackageAvailability());
			pre1.setDouble(7, pack.getDblPackagePrice());
			
			pre1.execute();
			pre1.close();
			
			PreparedStatement delete = con.prepareStatement(prohibitedQuery);
			delete.setInt(1, pack.getIntPackageID());
			delete.execute();
			delete.close();
			
			for(int intCtr = 0; intCtr < pack.getServiceList().size(); intCtr++){
				ServicePackage servicePack = pack.getServiceList().get(intCtr);
				Service service = servicePack.getService();
				System.out.println(service.getIntServiceID());
				PreparedStatement pre2 = con.prepareStatement(strQuery3);
				pre2.setInt(1, pack.getIntPackageID());
				pre2.setInt(2, service.getIntServiceID());
				pre2.setInt(3, servicePack.getIntQuantity());
				pre2.execute();
				pre2.close();
			}
			
			for(int intCtr = 0; intCtr < pack.getProductList().size(); intCtr++){
				ProductPackage productPack = pack.getProductList().get(intCtr);
				Product product = productPack.getProduct();
				
				PreparedStatement pre2 = con.prepareStatement(strQuery2);
				pre2.setInt(1, pack.getIntPackageID());
				pre2.setInt(2, product.getIntProductID());
				pre2.setInt(3, productPack.getIntProductQuantity());
				pre2.execute();
				pre2.close();
			}
			
			
			con.close();
			return true;
		}
		catch(Exception e){
			System.out.println(e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public List<Package> getAllPackage() {
		
		Connection con = jdbc.getConnection();
		String query = "SELECT * FROM tblPackage WHERE intPackageStatus = 1;";
		List<Package> packageList = new ArrayList<Package>();
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			ResultSet set = pre.executeQuery();
			
			while(set.next()){
				
				ServiceService serv = new ServiceServiceImpl();
				ProductService prod = new ProductServiceImpl();
				List<ServicePackage> servPack = new ArrayList<ServicePackage>();
				List<ProductPackage> prodPack = new ArrayList<ProductPackage>();
				
				List<Product> productList = prod.getAllProductsNoImage();
				List<Service> serviceList = serv.getAllServiceNoImage();
				
				int intID = set.getInt(1);
				String strName = set.getString(2);
				String strDesc = set.getString(3);
				int intType = set.getInt(4);
				int max = set.getInt(5);
				String strAvailability = set.getString(6);
				int intStatus = set.getInt(7);
				double price = 0;
				
				PreparedStatement pre2 = con.prepareStatement("CALL getPackagePrice(?);");
				pre2.setInt(1, intID);
				ResultSet set2 = pre2.executeQuery();
				
				PreparedStatement pre5 = con.prepareStatement("SELECT * FROM tblServicePackage WHERE intPackageID = ? AND intPackageServiceStatus = 1");
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
				
				
				
				PreparedStatement pre6 = con.prepareStatement("SELECT * FROM tblProductPackage WHERE intProductPackageStatus = 1 AND intPackageID = ?");
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
				
				while(set2.next()){
					price = set2.getDouble(1);
				}
				
				
				
				Package packagee = new Package(intID, strName, strDesc, intType, max, strAvailability, price, servPack, prodPack, intStatus);
				packageList.add(packagee);
				pre2.close();
			}
			
			pre.close();
			con.close();
			
			return packageList;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deactivatePackage(int packageID) {
		
		Connection con = jdbc.getConnection();
		String query = "UPDATE tblPackage SET intPackageStatus = 0 WHERE intPackageID = ?;";
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			pre.setInt(1, packageID);
			
			pre.execute();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Package> getAllPackageNoImage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Package getPackageByID(int intPackageID){
		Connection con = jdbc.getConnection();
		String query = "SELECT * FROM tblPackage WHERE intPackageID = ?;";
		Package packageList = null;
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			pre.setInt(1, intPackageID);
			ResultSet set = pre.executeQuery();
			
			while(set.next()){
				
				ServiceService serv = new ServiceServiceImpl();
				ProductService prod = new ProductServiceImpl();
				List<ServicePackage> servPack = new ArrayList<ServicePackage>();
				List<ProductPackage> prodPack = new ArrayList<ProductPackage>();
				
				List<Product> productList = prod.getAllProductsNoImage();
				List<Service> serviceList = serv.getAllServiceNoImage();
				
				int intID = set.getInt(1);
				String strName = set.getString(2);
				String strDesc = set.getString(3);
				int intType = set.getInt(4);
				int max = set.getInt(5);
				String strAvailability = set.getString(6);
				int intStatus = set.getInt(7);
				double price = 0;
				
				PreparedStatement pre2 = con.prepareStatement("CALL getPackagePrice(?);");
				pre2.setInt(1, intID);
				ResultSet set2 = pre2.executeQuery();
				
				PreparedStatement pre5 = con.prepareStatement("SELECT * FROM tblServicePackage WHERE intPackageID = ? AND intPackageServiceStatus = 1");
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
				
				
				
				PreparedStatement pre6 = con.prepareStatement("SELECT * FROM tblProductPackage WHERE intProductPackageStatus = 1 AND intPackageID = ?");
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
				
				while(set2.next()){
					price = set2.getDouble(1);
				}
				
				
				
				packageList = new Package(intID, strName, strDesc, intType, max, strAvailability, price, servPack, prodPack, intStatus);

				pre2.close();
			}
			
			pre.close();
			con.close();
			
			return packageList;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

	public List<Package> getAllPackageNoDetails(){
		
		Connection con = jdbc.getConnection();
		String query = "SELECT * FROM tblPackage WHERE intPackageStatus = 1;";
		List<Package> packageList = new ArrayList<Package>();
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			ResultSet set = pre.executeQuery();
			
			while(set.next()){
				
				ServiceService serv = new ServiceServiceImpl();
				ProductService prod = new ProductServiceImpl();
				List<ServicePackage> servPack = new ArrayList<ServicePackage>();
				List<ProductPackage> prodPack = new ArrayList<ProductPackage>();
				
				List<Product> productList = prod.getAllProductsNoImage();
				List<Service> serviceList = serv.getAllServiceNoImage();
				
				int intID = set.getInt(1);
				String strName = set.getString(2);
				String strDesc = set.getString(3);
				int intType = set.getInt(4);
				int max = set.getInt(5);
				String strAvailability = set.getString(6);
				int intStatus = set.getInt(7);
				double price = 0;
				
				PreparedStatement pre2 = con.prepareStatement("CALL getPackagePrice(?);");
				pre2.setInt(1, intID);
				ResultSet set2 = pre2.executeQuery();				
				
				while(set2.next()){
					price = set2.getDouble(1);
				}
				
				Package packagee = new Package(intID, strName, strDesc, intType, max, strAvailability, price, servPack, prodPack, intStatus);
				packageList.add(packagee);
				pre2.close();
			}
			
			pre.close();
			con.close();
			
			return packageList;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	
	}

	@Override
	public List<Package> queryAllPackage() {
		Connection con = jdbc.getConnection();
		String query = "SELECT * FROM tblPackage ORDER BY strPackageName ASC;";
		List<Package> packageList = new ArrayList<Package>();
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			ResultSet set = pre.executeQuery();
			
			while(set.next()){
				
				ServiceService serv = new ServiceServiceImpl();
				ProductService prod = new ProductServiceImpl();
				List<ServicePackage> servPack = new ArrayList<ServicePackage>();
				List<ProductPackage> prodPack = new ArrayList<ProductPackage>();
				
				List<Product> productList = prod.getAllProductsNoImage();
				List<Service> serviceList = serv.getAllServiceNoImage();
				
				int intID = set.getInt(1);
				String strName = set.getString(2);
				String strDesc = set.getString(3);
				int intType = set.getInt(4);
				int max = set.getInt(5);
				String strAvailability = set.getString(6);
				int intStatus = set.getInt(7);
				double price = 0;
				
				PreparedStatement pre2 = con.prepareStatement("CALL getPackagePrice(?);");
				pre2.setInt(1, intID);
				ResultSet set2 = pre2.executeQuery();				
				
				while(set2.next()){
					price = set2.getDouble(1);
				}
				
				Package packagee = new Package(intID, strName, strDesc, intType, max, strAvailability, price, servPack, prodPack, intStatus);
				packageList.add(packagee);
				pre2.close();
			}
			
			pre.close();
			con.close();
			
			return packageList;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Package> getPackageByType(String type){
		
		Connection con = jdbc.getConnection();
		String query 						= "SELECT * FROM tblPackage WHERE intPackageStatus = 1";
		
		if(type.equalsIgnoreCase("event"))
			query += " AND intPackageType = 1 OR intPackageStatus = 1 AND intPackageType = 4  OR intPackageStatus = 1 AND intPackageType = 5 OR intPackageStatus = 1 AND intPackageType = 7";
		if(type.equalsIgnoreCase("home service"))
			query += " AND intPackageType = 2  OR intPackageStatus = 1 AND intPackageType = 4  OR intPackageStatus = 1 AND intPackageType = 6  OR intPackageStatus = 1 AND intPackageType = 7";
		if(type.equalsIgnoreCase("walkin"))
			query += " AND intPackageType = 3  OR intPackageStatus = 1 AND intPackageType = 5  OR intPackageStatus = 1 AND intPackageType = 6 OR intPackageStatus = 1 AND intPackageType = 7";
		
		List<Package> packageList = new ArrayList<Package>();
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			ResultSet set = pre.executeQuery();
			
			while(set.next()){
				
				ServiceService serv = new ServiceServiceImpl();
				ProductService prod = new ProductServiceImpl();
				List<ServicePackage> servPack = new ArrayList<ServicePackage>();
				List<ProductPackage> prodPack = new ArrayList<ProductPackage>();
				
				List<Product> productList = prod.getAllProductsNoImage();
				List<Service> serviceList = serv.getAllServiceNoImage();
				
				int intID = set.getInt(1);
				String strName = set.getString(2);
				String strDesc = set.getString(3);
				int intType = set.getInt(4);
				int max = set.getInt(5);
				String strAvailability = set.getString(6);
				int intStatus = set.getInt(7);
				double price = 0;
				
				PreparedStatement pre2 = con.prepareStatement("CALL getPackagePrice(?);");
				pre2.setInt(1, intID);
				ResultSet set2 = pre2.executeQuery();
				
				PreparedStatement pre5 = con.prepareStatement("SELECT * FROM tblServicePackage WHERE intPackageID = ? AND intPackageServiceStatus = 1");
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
				
				
				
				PreparedStatement pre6 = con.prepareStatement("SELECT * FROM tblProductPackage WHERE intProductPackageStatus = 1 AND intPackageID = ?");
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
				
				while(set2.next()){
					price = set2.getDouble(1);
				}
				
				
				
				Package packagee = new Package(intID, strName, strDesc, intType, max, strAvailability, price, servPack, prodPack, intStatus);
				packageList.add(packagee);
				pre2.close();
			}
			
			pre.close();
			con.close();
			
			return packageList;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
