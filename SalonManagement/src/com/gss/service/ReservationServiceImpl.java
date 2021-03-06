package com.gss.service;

import java.sql.SQLException;
import java.util.List;

import com.gss.dao.ReservationJDBCRepository;
import com.gss.dao.ReservationRepository;
import com.gss.model.EmployeeAssigned;
import com.gss.model.ProductOrder;
import com.gss.model.Reservation;
import com.gss.model.ReservedPackage;
import com.gss.model.ReservedPromo;
import com.gss.model.ReservedService;

public class ReservationServiceImpl implements ReservationService{

	@Override
	public List<Reservation> getAllReservation() {
		
		ReservationRepository repo = new ReservationJDBCRepository();
		
		return repo.getAllReservation();
	}

	@Override
	public int createReservation(Reservation reservation) throws SQLException {
		
		ReservationRepository repo = new ReservationJDBCRepository();
		
		return repo.createReservation(reservation);
	}

	@Override
	public boolean updateReservation(Reservation reservation) throws SQLException {

		ReservationRepository repo = new ReservationJDBCRepository();
		
		return repo.updateReservation(reservation);
	}

	@Override
	public boolean cancelReservation(int id) throws SQLException {
		
		ReservationRepository repo = new ReservationJDBCRepository();
		
		return repo.cancelReservation(id);
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

	@Override
	public List<Reservation> getAllReservationNoDetails() {
		
		ReservationRepository repo = new ReservationJDBCRepository();
		
		return repo.getAllReservationNoDetails();
	}
	
	

}
