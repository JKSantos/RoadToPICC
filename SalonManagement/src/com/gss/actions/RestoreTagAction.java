package com.gss.actions;

import java.sql.SQLException;

import com.gss.model.ProductTag;

public class RestoreTagAction {
	
	private int intTagID;
	
	public String execute() throws SQLException{
		
		ProductTag tag = new ProductTag(intTagID, null, null, intTagID, null, intTagID);
		
		if(ProductTag.restoreTag(tag) == false)
			return "failed";
		else
			return "success";
	}

	public void setIntTagID(int intTagID) {
		this.intTagID = intTagID;
	}

}
