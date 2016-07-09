package com.gss.dao;

import java.util.List;

import com.gss.model.Catalogue;

public interface CatalogueRepository {
	
	public List<Catalogue> getAllCatalogue();
	
	public boolean createCatalogue(Catalogue catalogue);
	
	public boolean updateCatalogue(Catalogue catalogue);
	
	public boolean deactivateCatalogue(int intCatalogueID);

}
