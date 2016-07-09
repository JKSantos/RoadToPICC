package com.gss.actions;

import com.opensymphony.xwork2.ActionSupport;

public class TilesSampleAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	public String sideNav(){
		
		return SUCCESS;
	}
	
	public String footer(){
		
		return SUCCESS;
	}
	
	public String body(){
		
		return SUCCESS;
	}
	
	public String newBody(){
		
		return SUCCESS;
	}

}
