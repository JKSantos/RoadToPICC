package com.gss.actions.OtherCharge;

import java.util.List;

import com.gss.model.ExtraCharge;

public class QueryAllOtherCharge {
	
	private List<ExtraCharge> otherChargeList;
	
	public String execute(){
		
		this.otherChargeList = ExtraCharge.queryAllOtherCharge();
		
		return "success";
	}

	public List<ExtraCharge> getOtherChargeList() {
		return otherChargeList;
	}
}
