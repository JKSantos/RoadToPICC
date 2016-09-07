package com.gss.service;

import java.sql.SQLException;
import java.util.List;

import com.gss.model.ProductTag;
import com.gss.model.ProductTagReport;
import com.gss.model.TagSum;
import com.gss.model.Reports.ProductTagSum;

public interface ProductTags {
	
	public boolean defectiveTag(ProductTag productTag);
	
	public boolean addStock(ProductTag productTag);
	
	public boolean subtractStock(ProductTag productTag);

	public List<ProductTag> getAllTag() throws SQLException;
	
	public boolean restoreTag(ProductTag productTag) throws SQLException;
	
	public List<ProductTagReport> getProductTagReport(String dateFrom, String dateTo);

	public List<ProductTagReport> getFilteredProductTagReport();
	
	public List<TagSum> getTagSum(String dateFrom, String dateTo);
	
	public ProductTagSum getProductTagSum(String dateFrom, String dateTo);
}
