package com.gss.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.dao.ExtraChargeJDBCRepository;
import com.gss.dao.ExtraChargeRepository;
import com.gss.model.ExtraCharge;

public class ExtraChargeServiceImpl implements ExtraChargeService{
	
	@Override
	public boolean createExtraCharge(ExtraCharge extra) {
		
		ExtraChargeRepository repo = new ExtraChargeJDBCRepository();
		
		return repo.createExtraCharge(extra);
	}

	@Override
	public List<ExtraCharge> getAllExtraCharges() {

		ExtraChargeRepository repo = new ExtraChargeJDBCRepository();
		
		return repo.getAllExtraCharges();
	}

	@Override
	public boolean updateExtraCharge(ExtraCharge extra) {
		
		ExtraChargeRepository repo = new ExtraChargeJDBCRepository();
		
		return repo.updateExtraCharge(extra);
	}

	@Override
	public boolean deactivateExtraCharge(int intExtraChargeID) {

		ExtraChargeRepository repo = new ExtraChargeJDBCRepository();
		
		return repo.deactivateExtraCharge(intExtraChargeID);	
	}

}
