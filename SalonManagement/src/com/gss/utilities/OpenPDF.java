package com.gss.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;

public class OpenPDF {
	
	private static final long serialVersionUID = 1L;
	private static InputStream fileStream;
    private static String fineName;

    public static InputStream execute(String path) throws Exception {
    	fileStream = new FileInputStream(
            new File(
                ((ServletContext) ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT)) 
                .getRealPath(path)
                +
                fineName
            )
        );
    	
    
    	
        return fileStream;
    }

}
