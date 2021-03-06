package com.gss.dao.Reports;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.Reports.ProductDetail;
import com.gss.model.Reports.ProductPurchase;
import com.gss.model.Reports.ProductPurchaseDetail;
import com.gss.model.Reports.ProductPurchases;
import com.gss.utilities.DateHelper;
import com.gss.utilities.ReportDate;

public class ProductPurchasesRepository {

	private static JDBCConnection jdbc = new JDBCConnection();
	
	public static ProductPurchase getProductPurchases(List<ReportDate> dateList, String type){
		Connection con = jdbc.getConnection();
		String query = "CALL getProductPurchases(?, ?, ?)";
		String products = "SELECT intProductID FROM tblProduct WHERE intProdStatus = 1;";
		
		ProductPurchase purchase = null;
		List<ProductPurchaseDetail> details = new ArrayList<ProductPurchaseDetail>();
		List<Integer> productIDs = new ArrayList<Integer>();
		
		try{
			
			PreparedStatement get = con.prepareStatement(query);
			PreparedStatement getProduct = con.prepareStatement(products);
			
			ResultSet set = null;
			ResultSet productIDResult = getProduct.executeQuery();
			
			while(productIDResult.next()){
				productIDs.add(productIDResult.getInt(1));
			}
			
			for(int index = 0; index < dateList.size(); index++){
				
				ReportDate date = dateList.get(index);
				ProductPurchaseDetail purchaseDetail = null;
				List<ProductDetail> detailList = new ArrayList<ProductDetail>();
				String classification = "";
				
				for(int index1 = 0; index1 < productIDs.size(); index1++){
					
					get.setInt(1, productIDs.get(index1));
					get.setString(2, date.getDateFrom());
					get.setString(3, date.getDateTo());
					
					set = get.executeQuery();
					
					if(type.equals("annual")){
						String[] dateString = date.getDateFrom().split("-");
						classification = dateString[0];
					}else if(type.equalsIgnoreCase("quarterly")){
						classification = "Quarter " + (index + 1);
					}else{
						classification = DateHelper.intMonthToString(index + 1);
					}
					
					while(set.next()){
		
						String name 			= set.getString(1);
						double reservation 		= set.getDouble(2);
						double walkin 			= set.getDouble(3);
						double productOrder 	= set.getDouble(4);
						
						ProductDetail productDetail = new ProductDetail(name, (reservation + walkin + productOrder));
						
						detailList.add(productDetail);
					}
				}
				
				
				purchaseDetail = new ProductPurchaseDetail(classification, detailList);
				details.add(purchaseDetail);
			}
				
			purchase = new ProductPurchase(type, details);
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return purchase;
	}

	public static List<ProductPurchases> getProductPurchases(ReportDate date){
		
		Connection con = jdbc.getConnection();
		String query1 = "CALL getProductPurchases_tabular_walkin(?, ?);";
		String query2 = "CALL getProductPurchases_tabular_reservation(?, ?);";
		String query3 = "CALL getProductPurchases_tabular_order(?, ?);";
		
		String[] queries = {query1, query2, query3};
		
		List<ProductPurchases> purchases = new ArrayList<ProductPurchases>();
		
		try{
			
			for(int i = 0; i < queries.length; i++){
				PreparedStatement get = con.prepareStatement(queries[i]);
				get.setString(1, date.getDateFrom());
				get.setString(2, date.getDateTo());
				
				ResultSet purchaseResult = get.executeQuery();
				
				while(purchaseResult.next()){
					
					int id = purchaseResult.getInt(1);
					String type = purchaseResult.getString(2);
					String name = purchaseResult.getString(3);
					Date datee = purchaseResult.getDate(4);
					String product = purchaseResult.getString(5);
					int quantity = purchaseResult.getInt(6);
					
					ProductPurchases purchase = new ProductPurchases(id, type, datee, name, product, quantity);
					purchases.add(purchase);
				}
				
				get.close();
				purchaseResult.close();
			}
			
			con.close();
			return purchases;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
