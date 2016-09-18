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
    private String fineName;

    public String execute() throws Exception {
    	fileStream = new FileInputStream(
            new File(
                ((ServletContext) ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT)) 
                .getRealPath("WEB-INF/")
                +
                fineName
            )
        );
        return SUCCESS;
    }

	public InputStream getFileStream() {
		return fileStream;
	}

	public void setFineName(String fineName) {
		this.fineName = fineName;
	}
    
    
}