package com.gss.dao;

import java.util.List;

import com.gss.model.ExtraCharge;

public interface ExtraChargeRepository {
	
	public boolean createExtraCharge(ExtraCharge extra);
	
	public List<ExtraCharge> getAllExtraCharges();
	
	public boolean updateExtraCharge(ExtraCharge extra);
	
	public boolean deactivateExtraCharge(int intExtraChargeID);

	public List<ExtraCharge> queryAllOtherCharge();

}
