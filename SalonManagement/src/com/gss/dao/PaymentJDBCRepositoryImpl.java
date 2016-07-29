package com.gss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.Payment;
import com.gss.model.Product;
import com.gss.model.ProductOrder;
import com.gss.model.ProductSales;
import com.gss.model.Reservation;
import com.gss.model.WalkIn;

public class PaymentJDBCRepositoryImpl implements PaymentRepository{
	
	private JDBCConnection jdbc = new JDBCConnection();
	
	@Override
	public boolean createProductSalesPayment(Payment payment) throws SQLException{
		
		Connection con 							= jdbc.getConnection();
		con.setAutoCommit(false);
		
		String insertPayment 					= "CALL createOrderPayment(?, ?, ?)";
		String updateStock						= "CALL updateStock(?, ?);";
				
		ProductSales sales = ProductSales.search(payment.getIntInvoiceID(), ProductSales.getAllProductSales());
		
		try{
			PreparedStatement createPayment		= con.prepareStatement(insertPayment);
			PreparedStatement updateProducts	= con.prepareStatement(updateStock);
			
			createPayment.setInt(1, payment.getIntInvoiceID());
			createPayment.setString(2, payment.getStrPaymentType());
			createPayment.setDouble(3, payment.getDblPaymentAmount());
			
			createPayment.execute();
			
			for(int index = 0; index < sales.getProductList().size(); index++){
				
				ProductOrder product = sales.getProductList().get(index);
				
				updateProducts.setInt(1, product.getProduct().getIntProductID());
				updateProducts.setInt(2, product.getIntQuantity());
				updateProducts.addBatch();
			}
			
			updateProducts.executeBatch();
			updateProducts.close();
			
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
	public boolean refundPayment() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Reservation> getAllUnpaidReservation() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WalkIn> getAllUnpaidWalkIn() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductSales> getAllUnpaidOrder() throws SQLException {
		
		Connection con 									= jdbc.getConnection();
		String getAllUnpaidOrder 						= "CALL getAllUnpaidTransaction(?);";
		
		List<ProductSales> productSalesList 			= new ArrayList<ProductSales>();

		try{
			PreparedStatement getAllProductSales = con.prepareStatement(getAllUnpaidOrder);
			ResultSet orders;
			
			getAllProductSales.setInt(1, 3);
			orders = getAllProductSales.executeQuery();

			while(orders.next()){
				
				List<ProductOrder> orderDetails = new ArrayList<ProductOrder>();
				ProductSales salesList;
				
				int intSalesID = orders.getInt(1);
				Date datCreated = orders.getDate(2);
				Date deliveryDate = orders.getDate(3);
				int intType = orders.getInt(4);
				String strName = orders.getString(5);
				String strAddress = orders.getString(6);
				int intLocationID = orders.getInt(7);
				String strContactNo = orders.getString(8);
				String strStatus = orders.getString(9);
				int intInvoiceID = orders.getInt(10);
				
				salesList = new ProductSales(intSalesID, datCreated, deliveryDate, intType, strName, strAddress, intLocationID, strContactNo, orderDetails, intInvoiceID, strStatus);
				productSalesList.add(salesList);
			}
			
			getAllProductSales.close();
			orders.close();
			con.close();
			
			return productSalesList;
		}
		catch(Exception e){
			e.printStackTrace();
			con.close();
			return null;
		}
	}

	@Override
	public boolean createReservationPayment(Payment payment) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createWalkInPayment(Payment payment) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
}
