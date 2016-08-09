package com.gss.dao;

import java.sql.SQLException;
import java.util.List;

import com.gss.model.ProductTag;

public interface ProductTagRepository {
	
	public boolean defectiveTag(ProductTag productTag);
	
	public boolean addStock(ProductTag productTag);
	
	public boolean subtractStock(ProductTag productTag);
	
	public List<ProductTag> getAllTag() throws SQLException;
	
	public boolean restoreTag(ProductTag productTag) throws SQLException;

}
