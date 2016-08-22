package com.gss.service.BusinessDependencies;

import java.sql.SQLException;
import java.util.List;

import com.gss.dao.BusinessDependencies.BusinessDependencyJDBCImpl;
import com.gss.dao.BusinessDependencies.BusinessDependencyRepository;
import com.gss.model.Dependency;

public class BusinessDependencyServiceImpl implements BusinessDependencyService{

	@Override
	public boolean updateDependencies(List<Dependency> dependency) throws SQLException {
		
		BusinessDependencyRepository repo = new BusinessDependencyJDBCImpl();
		
		return repo.updateDependencies(dependency);
	}

	@Override
	public List<Dependency> getAllDependencies() {
		
		BusinessDependencyRepository repo = new BusinessDependencyJDBCImpl();
		
		return repo.getAllDependencies();
	}

}
