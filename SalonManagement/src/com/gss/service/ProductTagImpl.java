package com.gss.service;

import java.sql.SQLException;
import java.util.List;

import com.gss.dao.ProductTagJDBCRepository;
import com.gss.dao.ProductTagRepository;
import com.gss.model.ProductTag;
import com.gss.model.ProductTagReport;

public class ProductTagImpl implements ProductTags{

	@Override
	public boolean defectiveTag(ProductTag productTag) {
		
		ProductTagRepository repo = new ProductTagJDBCRepository();
		
		return repo.defectiveTag(productTag);
	}

	@Override
	public boolean addStock(ProductTag productTag) {

		ProductTagRepository repo = new ProductTagJDBCRepository();
		
		return repo.addStock(productTag);
	}

	@Override
	public boolean subtractStock(ProductTag productTag) {
		
		ProductTagRepository repo = new ProductTagJDBCRepository();
		
		return repo.subtractStock(productTag);
	}

	@Override
	public List<ProductTag> getAllTag() throws SQLException {
		
		ProductTagRepository repo = new ProductTagJDBCRepository();
		
		return repo.getAllTag();
	}

	@Override
	public boolean restoreTag(ProductTag productTag) throws SQLException {
		
		ProductTagRepository repo = new ProductTagJDBCRepository();
		
		return repo.restoreTag(productTag);
	}
	
	@Override
	public List<ProductTagReport> getAllProductTag() throws SQLException {
		
		ProductTagRepository repo = new ProductTagJDBCRepository();
		
		return repo.getProductTagReport();
	}

	@Override
	public List<ProductTagReport> getFilteredProductTagReport() {
		
		ProductTagRepository repo = new ProductTagJDBCRepository();
		
		return repo.getFilteredProductTagReport();
	}
}
