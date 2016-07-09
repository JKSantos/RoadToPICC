package com.gss.service;

import java.util.List;

import com.gss.model.Catalogue;

public interface CatalogueService {
	
	public List<Catalogue> getAllCatalogue();
	
	public boolean createCatalogue(Catalogue catalogue);
	
	public boolean updateCatalogue(Catalogue catalogue);
	
	public boolean deactivateCatalogue(int intCatalogueID);

}
