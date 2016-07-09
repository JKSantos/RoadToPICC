package com.gss.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.gss.model.Employee;
import com.gss.utilities.DateHelper;

public class Trial {
	
	public static void main(String[] args) throws IOException{

		Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
	    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);		
	    SqlSession session = sqlSessionFactory.openSession();
	    
	    Date mydate = DateHelper.parseDate("1997-03-02");
	    
	   /* Employee emp = new Employee(1, "Santos", "Jeffrey", "King", mydate, "M", "Pasig", "09361144842", "A", "jeff", "pass", null);
	
	    session.insert("Employee.insertEmployee", emp);
	    
	    System.out.println("record inserted successfully");
	    session.commit();
	    session.close();*/
	}

}
