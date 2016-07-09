package com.gss.service;

import java.sql.SQLException;
import java.util.List;

import com.gss.dao.WalkInJDBCRepository;
import com.gss.dao.WalkInRepository;
import com.gss.model.WalkIn;

public class WalkInServiceImpl implements WalkInService{

	@Override
	public boolean createWalkIn(WalkIn walkin) throws SQLException {

		WalkInRepository repo = new WalkInJDBCRepository();
		
		return repo.createWalkIn(walkin);
	}

	@Override
	public boolean updateWalkIn(WalkIn walkin) {
		
		WalkInRepository repo = new WalkInJDBCRepository();
		
		return repo.updateWalkIn(walkin);
	}

	@Override
	public boolean cancelWalkIn(int intWalkInID) {

		WalkInRepository repo = new WalkInJDBCRepository();
		
		return repo.cancelWalkIn(intWalkInID);
	}

	@Override
	public boolean payWalkIn(int intWalkInID, double dblPaymentAmount) {

		WalkInRepository repo = new WalkInJDBCRepository();
		
		return repo.payWalkIn(intWalkInID, dblPaymentAmount);
	}

	@Override
	public List<WalkIn> getAllWalkIn() {

		WalkInRepository repo = new WalkInJDBCRepository();
		
		return repo.getAllWalkIn();
	}

}
