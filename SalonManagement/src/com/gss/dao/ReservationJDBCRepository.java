package com.gss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.sql.Time;

import com.gss.connection.JDBCConnection;
import com.gss.model.EmployeeAssigned;
import com.gss.model.Invoice;
import com.gss.model.Package;
import com.gss.model.Payment;
import com.gss.model.ProductOrder;
import com.gss.model.Promo;
import com.gss.model.Reservation;
import com.gss.model.ReservedPackage;
import com.gss.model.ReservedPromo;
import com.gss.model.ReservedService;
import com.gss.model.Service;

public class ReservationJDBCRepository implements ReservationRepository{

	private JDBCConnection jdbc = new JDBCConnection();
	
	private int intReservationID;
	private Date datFrom;
	private Date datTo;
	private String timFrom;
	private String timTo;
	private String strName;
	private String strAddress;
	private String strContactNo;
	private String strEmail;
	private int headCount;
	private List<EmployeeAssigned> employeeAssigned;
	private List<ProductOrder> productList;
	private List<Service> serviceList;
	private List<Package> packageList;
	private List<Promo> promoList;
	private Invoice invoice;
	private List<Payment> paymentList;
	private String strStatus;
	
	private Date date = new Date();
	
	@Override
	public List<Reservation> getAllReservation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createReservation(Reservation reservation) throws SQLException {
		
		Connection con = jdbc.getConnection();
		con.setAutoCommit(false);
		
		String createReservation				= "CALL createReservation(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String createProduct					= "CALL createProductReservation(?, ?, ?);";
		String createService					= "CALL createServiceReservation(?, ?, ?);";
		String createPackage					= "CALL createPackageReservation(?, ?, ?);";
		String createPromo						= "CALL createPromoReservation(?, ?, ?);";
		
		
		try{
			PreparedStatement preReservation = con.prepareStatement(createReservation);
			PreparedStatement preProduct = con.prepareStatement(createProduct);
			PreparedStatement preService = con.prepareStatement(createService);
			PreparedStatement prePackage = con.prepareStatement(createPackage);
			PreparedStatement prePromo = con.prepareStatement(createPromo);
			
			ResultSet reservationResult;
			
			//reservation insertion
			
			preReservation.setInt(1, reservation.getIntReservationType());
			preReservation.setDate(2, new java.sql.Date(reservation.getDatFrom().getTime()));
			preReservation.setDate(3, new java.sql.Date(reservation.getDatTo().getTime()));
			preReservation.setTime(4, reservation.getTimFrom());
			preReservation.setTime(5, reservation.getTimTo());
			preReservation.setString(6, reservation.getStrName());
			preReservation.setString(7, reservation.getStrAddress());
			preReservation.setString(8, reservation.getStrContactNo());
			preReservation.setString(9, reservation.getStrEmail());
			preReservation.setInt(10, reservation.getHeadCount());
			preReservation.setString(11, reservation.getStrStatus());
			
			
			//products
			
			//services
			
			//packages
			
			//promos
			
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
	public boolean updateReservation(Reservation reservation) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cancelReservation(Reservation reservation) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<EmployeeAssigned> getAllAssignedEmployee(int ReservationID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductOrder> getAllProductOrder(int ReservationID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReservedService> getAllReservedService(int ReservationID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReservedPackage> getAllReservedPackage(int ReservationID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReservedPromo> getAllReservedPromo(int ReservationID) {
		// TODO Auto-generated method stub
		return null;
	}
}
