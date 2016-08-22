package com.gss.service.BusinessDependencies;

import java.sql.SQLException;
import java.util.List;

import com.gss.model.Dependency;

public interface BusinessDependencyService {
	
	public boolean updateDependencies(List<Dependency> dependency) throws SQLException;

	public List<Dependency> getAllDependencies();
}
