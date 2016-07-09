package com.gss.service;

import com.gss.model.ProductTag;

public interface ProductTags {
	
	public boolean defectiveTag(ProductTag productTag);
	
	public boolean addStock(ProductTag productTag);
	
	public boolean subtractStock(ProductTag productTag);

}
