package com.gss.dao;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.gss.model.Employee;
import com.gss.model.EmployeeCategory;
import com.gss.model.Job;

public class EmployeeMyBatisRepository implements EmployeeRepository{

	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

	public Employee getEmployeeByUserPass(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public Employee getEmployeeByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createEmployee(Employee emp) {
		
		try{
			Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
	    	SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);		
	    	SqlSession session = sqlSessionFactory.openSession();
	    
	    	session.insert("Employee.insertEmployee", emp);
	    	
	    	session.commit();
	    	session.close();
	    	
	    	return true;
		}
		catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean updateEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<EmployeeCategory> getAllCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Job> getEmployeeJob(int empID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deactivateEmployee(int empID) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Employee getEmployeeByID(int intID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAllEmployeeNoImage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
