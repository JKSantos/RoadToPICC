package com.gss.service;

import java.sql.SQLException;
import java.util.List;

import com.gss.model.ProductTag;
import com.gss.model.ProductTagReport;
import com.gss.model.TagSum;

public interface ProductTags {
	
	public boolean defectiveTag(ProductTag productTag);
	
	public boolean addStock(ProductTag productTag);
	
	public boolean subtractStock(ProductTag productTag);

	public List<ProductTag> getAllTag() throws SQLException;
	
	public boolean restoreTag(ProductTag productTag) throws SQLException;
	
	public List<ProductTagReport> getAllProductTag() throws SQLException;

	public List<ProductTagReport> getFilteredProductTagReport();
	
	public List<TagSum> getTagSum();
}
