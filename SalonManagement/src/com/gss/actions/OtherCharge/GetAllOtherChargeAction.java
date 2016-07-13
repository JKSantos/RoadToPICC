package com.gss.actions.OtherCharge;

import java.util.List;

import com.gss.model.ExtraCharge;
import com.gss.service.ExtraChargeService;
import com.gss.service.ExtraChargeServiceImpl;

public class GetAllOtherChargeAction {
	
	private List<ExtraCharge> extraChargeList;
	
	public String execute(){
		
		ExtraChargeService service = new ExtraChargeServiceImpl();
		this.extraChargeList = service.getAllExtraCharges();
		
		return "success";
	}

	public List<ExtraCharge> getExtraChargeList() {
		return extraChargeList;
	}

}
