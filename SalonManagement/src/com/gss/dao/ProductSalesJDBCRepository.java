package com.gss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.gss.connection.JDBCConnection;
import com.gss.model.Product;
import com.gss.model.ProductOrder;
import com.gss.model.ProductSales;
import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;


public class ProductSalesJDBCRepository implements ProductSalesRepository{
	
	private JDBCConnection jdbc = new JDBCConnection();
	
	private int intSalesID;
	private Date datCreated;
	private Date deliveryDate;
	private int intType;
	private String strName;
	private String strAddress;
	private int intLocationID;
	private String strContactNo;
	private int intInvoiceID;
	private String strStatus;
	private int intID;
	private int intQuantity;
	private int intStatus;

	@Override
	public boolean createProductSales(ProductSales sales) throws SQLException {
		
		Connection con 								= jdbc.getConnection();
		String createProductSales 					= "CALL createProductSales(?, ?, ?, ?, ?, ?)";
		String createDetails 						= "CALL createDetail(?, ?, ?)";
		
		try{
			con.setAutoCommit(false);
			
			PreparedStatement insertProductSales 	= con.prepareStatement(createProductSales);
			PreparedStatement insertDetails 		= con.prepareStatement(createDetails);
			ResultSet salesID;
			int intID = 0;
			
			insertProductSales.setDate(1, (java.sql.Date) sales.getDeliveryDate());
			insertProductSales.setInt(2, sales.getIntType());
			insertProductSales.setString(3, sales.getStrName());
			insertProductSales.setString(4, sales.getStrAddress());
			insertProductSales.setInt(5, sales.getIntLocationID());
			insertProductSales.setString(6, sales.getStrContactNo());
			salesID = insertProductSales.executeQuery();
			
			while(salesID.next()){
				intID = salesID.getInt(1);
			}
			
			for(int intCtr = 0; intCtr < sales.getProductList().size(); intCtr++){
				insertDetails.setInt(1, intID);
				insertDetails.setInt(2, sales.getProductList().get(intCtr).getProduct().getIntProductID());
				insertDetails.setInt(3, sales.getProductList().get(intCtr).getIntQuantity());
				insertDetails.addBatch();
			}
			
			insertDetails.executeBatch();
			insertProductSales.close();
			salesID.close();
			insertDetails.close();
			con.commit();
			con.close();
			return true;
		}
		catch(Exception e){
			
			e.printStackTrace();
			con.rollback();
			con.close();
			
			return false;
		}
	}
//undone
	@Override
	public boolean updateProductSales(ProductSales Sales) throws SQLException {
		Connection con 								= jdbc.getConnection();
		String updateProductSales 					= "CALL updateProductSales(?, ?, ?, ?)";
		String deleteProductDetails					= "CALL deleteDetails(?)";
		String createDetails 						= "CALL createDetail(?, ?, ?)";
		
		try{
			con.setAutoCommit(false);
			
			PreparedStatement updateSales 			= con.prepareStatement(updateProductSales);
			PreparedStatement deleteDetails			= con.prepareStatement(deleteProductDetails);
			PreparedStatement insertDetails 		= con.prepareStatement(createDetails);

			int intID = 0;
			
			updateSales.setInt(1, Sales.getIntSalesID());
			updateSales.setDate(2, (java.sql.Date) Sales.getDeliveryDate());
			updateSales.setString(3, Sales.getStrAddress());
			updateSales.setInt(4, Sales.getIntLocationID());
			updateSales.execute();
			
			deleteDetails.setInt(1, Sales.getIntSalesID());
			deleteDetails.execute();
			
			for(int intCtr = 0; intCtr < Sales.getProductList().size(); intCtr++){
				insertDetails.setInt(1, intID);
				insertDetails.setInt(2, Sales.getProductList().get(intCtr).getProduct().getIntProductID());
				insertDetails.setInt(3, Sales.getProductList().get(intCtr).getIntQuantity());
				insertDetails.addBatch();
			}
			
			insertDetails.executeBatch();
			updateSales.close();
			deleteDetails.close();
			insertDetails.close();
			con.commit();
			con.close();
			return true;
		}
		catch(Exception e){
			
			e.printStackTrace();
			con.rollback();
			con.close();
			
			return false;
		}
	}

	@Override
	public List<ProductSales> getAllProductSales() {
		Connection con = jdbc.getConnection();
		
		String getAllOrder 					= "SELECT * FROM tblOrder;";
		String getAllDet 					= "SELECT * FROM tblOrderDetail WHERE intOrderID = ?";
		
		try{
			ProductService service = new ProductServiceImpl();
			List<ProductSales> salesList 	= new ArrayList<ProductSales>();
			List<Product> productList 		= service.getAllProductsNoImage();
			
			PreparedStatement getAll 		= con.prepareStatement(getAllOrder);
			PreparedStatement getAllDetails	= con.prepareStatement(getAllDet);
			ResultSet orders				= getAll.executeQuery();
			ResultSet details;
			
			while(orders.next()){
				
				List<ProductOrder> orderDetails = new ArrayList<ProductOrder>();
				ProductSales salesList1;
				
				this.intSalesID = orders.getInt(1);
				this.datCreated = orders.getDate(2);
				this.deliveryDate = orders.getDate(3);
				this.intType = orders.getInt(4);
				this.strName = orders.getString(5);
				this.strAddress = orders.getString(6);
				this.intLocationID = orders.getInt(7);
				this.strContactNo = orders.getString(8);
				this.strStatus = orders.getString(9);
				this.intInvoiceID = orders.getInt(10);
				
				getAllDetails.setInt(1, intSalesID);
				details = getAllDetails.executeQuery();
				
				while(details.next()){
					
					this.intID = details.getInt(1);
					int id = details.getInt(3);
					this.intQuantity = details.getInt(4);
					this.intStatus = details.getInt(5);
					
					for(int i = 0; i < productList.size(); i++){
						if(id == productList.get(i).getIntProductID()){
							Product product = productList.get(i);
							ProductOrder order = new ProductOrder(this.intID, product, this.intQuantity, this.intStatus);
							orderDetails.add(order);
						}
					}
				}
				details.close();
				
				salesList1 = new ProductSales(this.intSalesID, this.datCreated, this.deliveryDate, this.intType, this.strName, this.strAddress, this.intLocationID, this.strContactNo, orderDetails, this.intInvoiceID, this.strStatus);
				salesList.add(salesList1);
			}
			
			getAll.close();
			getAllDetails.close();
			orders.close();
			
			con.close();
			return salesList;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deactivateProductSales(int intID) throws SQLException{
		
		Connection con = jdbc.getConnection();
		String deactivateSales = "UPDATE tblOrder SET strOrderStatus = 'CANCELLED' WHERE intOrderID = ?";
		
		try{
			con.setAutoCommit(false);
			PreparedStatement deactivate = con.prepareStatement(deactivateSales);
			deactivate.setInt(1, intID);
			deactivate.execute();
			deactivate.close();
			
			con.close();
			con.commit();
			return true;
		}
		catch(Exception e){
			
			con.rollback();
			con.close();
			return false;
		}
	}

}
