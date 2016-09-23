package com.gss.model.Trial;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class CreateStudentsAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	private List<Student> students = new ArrayList<Student>();
	
	public String execute(){
		System.out.print(students.size());
		if(StudentRepository.createStudents(students) == true)
			return SUCCESS;
		else
			return ERROR;
		
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
}
