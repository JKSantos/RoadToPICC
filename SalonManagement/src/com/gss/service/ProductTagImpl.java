package com.gss.service;

import com.gss.dao.ProductTagJDBCRepository;
import com.gss.dao.ProductTagRepository;
import com.gss.model.ProductTag;

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

	
	

}
