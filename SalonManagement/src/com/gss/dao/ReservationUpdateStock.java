package com.gss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.PackagePackage;
import com.gss.model.ProductOrder;
import com.gss.model.ProductPackage;
import com.gss.model.ProductQuantity;
import com.gss.model.Promo;
import com.gss.model.Reservation;
import com.gss.model.ReservationInclusion;
import com.gss.model.ReservedPackage;
import com.gss.model.ReservedPromo;

public class ReservationUpdateStock {
	
	public static boolean updateStock(List<ProductQuantity> products){
		
		Connection con = new JDBCConnection().getConnection();
		
		String query = "CALL updateStock_decrement(?, ?);";
		
		try{
			PreparedStatement statement = con.prepareStatement(query);
			
			for(int index = 0; index < products.size(); index++){
				ProductQuantity quantity = products.get(index);
				statement.setInt(1, quantity.getIntProductID());
				statement.setInt(2, quantity.getIntQuantity());
				statement.addBatch();
			}
			
			statement.executeBatch();
			statement.close();
			
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean updateStock_increment(List<ProductQuantity> products){
		
		Connection con = new JDBCConnection().getConnection();
		
		String query = "CALL updateStock_increment(?, ?);";
		
		try{
			PreparedStatement statement = con.prepareStatement(query);
			
			for(int index = 0; index < products.size(); index++){
				ProductQuantity quantity = products.get(index);
				statement.setInt(1, quantity.getIntProductID());
				statement.setInt(2, quantity.getIntQuantity());
				statement.addBatch();
			}
			
			statement.executeBatch();
			statement.close();
			
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static List<ProductQuantity> getProducts(Reservation reservation){
		
		List<ProductQuantity> quantities = new ArrayList<ProductQuantity>();
		
		ReservationInclusion items = reservation.getIncludedItems();
		List<ProductOrder> productList = items.getProductList();
		List<ReservedPackage> packageList = items.getPackageList();
		List<ReservedPromo> promoList = items.getPromoList();
		
		//getting products inseide reservation
		for(int index = 0; index < productList.size(); index++){
			ProductOrder order = productList.get(index);
			ProductQuantity quantity = new ProductQuantity(order.getIntID(), order.getProduct().getIntProductID(), order.getIntQuantity());
			quantities.add(quantity);
		}
		
		//getting products inside package 
		for(int index = 0; index < packageList.size(); index++){
			
			ReservedPackage packagee = packageList.get(index);
			com.gss.model.Package packageee = new PackageJDBCRepository().getPackageByID(packagee.getIntReservedPackageID());
			
			for(int innerIndex = 0; innerIndex < packageee.getProductList().size(); innerIndex++){
				int productID = packageee.getProductList().get(innerIndex).getProduct().getIntProductID();
				int quantity = packageee.getProductList().get(innerIndex).getIntProductQuantity();
				quantities.add(new ProductQuantity(1, productID, quantity));
			}
		}
		
		for(int indexOuter = 0; indexOuter < promoList.size(); indexOuter++){
			
			ReservedPromo resPromo = promoList.get(indexOuter);
			PromoJDBCRepository promoJdbc = new PromoJDBCRepository();
			Promo promo = promoJdbc.getPromoByID(resPromo.getIntReservedPromoID());
		
			for(int index = 0; index < promo.getPackageList().size(); index++){
				
				com.gss.model.Package packageee = new PackageJDBCRepository().getPackageByID(promo.getPackageList().get(index).getIntPackageID());;
				
				for(int innerIndex = 0; innerIndex < packageee.getProductList().size(); innerIndex++){
					int productID = packageee.getProductList().get(innerIndex).getProduct().getIntProductID();
					int quantity = packageee.getProductList().get(innerIndex).getIntProductQuantity();
					quantities.add(new ProductQuantity(1, productID, quantity));
				}
			}
			
			for(int index = 0; index < promo.getProductList().size(); index++){
				ProductPackage order = promo.getProductList().get(index);
				ProductQuantity quantity = new ProductQuantity(1, order.getProduct().getIntProductID(), order.getIntProductQuantity());
				quantities.add(quantity);
			}
			
		}
		
		return quantities;
	}
	
}
