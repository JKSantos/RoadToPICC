package com.gss.dao;

import com.gss.model.ProductTag;

public interface ProductTagRepository {
	
	public boolean defectiveTag(ProductTag productTag);
	
	public boolean addStock(ProductTag productTag);
	
	public boolean subtractStock(ProductTag productTag);

}
