package com.gss.service;

import java.util.List;

import com.gss.dao.CatalogueJDBCRepository;
import com.gss.dao.CatalogueRepository;
import com.gss.model.Catalogue;

public class CatalogueServiceImpl implements CatalogueService{

	@Override
	public List<Catalogue> getAllCatalogue() {
		
		CatalogueRepository repo = new CatalogueJDBCRepository();
		
		return repo.getAllCatalogue();
	}

	@Override
	public boolean createCatalogue(Catalogue catalogue) {

		CatalogueRepository repo = new CatalogueJDBCRepository();
		
		return repo.createCatalogue(catalogue);
	}

	@Override
	public boolean updateCatalogue(Catalogue catalogue) {

		CatalogueRepository repo = new CatalogueJDBCRepository();
		
		return repo.updateCatalogue(catalogue);
	}

	@Override
	public boolean deactivateCatalogue(int intCatalogueID) {

		CatalogueRepository repo = new CatalogueJDBCRepository();
		
		return repo.deactivateCatalogue(intCatalogueID);
	}

}
