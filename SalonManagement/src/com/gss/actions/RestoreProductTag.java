package com.gss.actions;

import java.sql.SQLException;

import com.gss.model.ProductTag;
import com.gss.service.ProductTagImpl;
import com.gss.service.ProductTags;

public class RestoreProductTag {
	
	private ProductTag productTag;
	
	public String execute() throws SQLException{
		
		ProductTags tags = new ProductTagImpl();
		boolean recorder = tags.restoreTag(productTag);
		
		if(recorder == true)
			return "success";
		else
			return "failed";
	}

	public void setProductTag(ProductTag productTag) {
		this.productTag = productTag;
	}

}
