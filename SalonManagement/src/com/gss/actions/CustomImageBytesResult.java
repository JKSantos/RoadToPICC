package com.gss.actions;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;

public class CustomImageBytesResult implements Result {

	public void execute(ActionInvocation invocation) throws Exception {
 
		GetImageAction action = (GetImageAction) invocation.getAction();
		HttpServletResponse response = ServletActionContext.getResponse();

		response.setContentType(action.getCustomContentType());
		//response.setContentLength(action.getCustomContentLength());
		response.getOutputStream().write(action.getCustomImageInBytes());
		response.getOutputStream().flush();
		
	}

}