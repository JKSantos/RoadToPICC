package com.gss.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InputStream fileStream;
    private String path;

    public String execute() throws Exception {
    	
    	System.out.println("file path: "+path);
    	fileStream = new FileInputStream(
            new File(
                ((ServletContext) ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT)) 
                .getRealPath(path)
            )
        );
    	
    
    	
        return SUCCESS;
    }

	public InputStream getFileStream() {
		return fileStream;
	}

	public void setPath(String path) {
		this.path = path;
	}
    
    
}