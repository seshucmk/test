package com.seshu.cs;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

import io.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("/rest")
@Produces({"application/json"})
@Consumes({"application/json"})
public class CSRestService extends Application {
	
	@Context HttpHeaders headers;
	public CSRestService() {
    	super();

    	BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("Wild");
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:6060");
        beanConfig.setBasePath("/rest");
        beanConfig.setResourcePackage("com.seshu.cs");
        beanConfig.setScan(true);
        beanConfig.setPrettyPrint(true);
        
    }
	
	protected boolean authenticate() throws Exception {
		
		boolean authorized = false;
		MultivaluedMap<String, String> headerMap = headers.getRequestHeaders();
				
		String user = headerMap.getFirst("Username");
		String password = headerMap.getFirst("Password");
		if ((password == null || password.length() == 0)) {
			throw new Exception("Header param 'Password' is required");
		}
		if ((user == null || user.length() == 0)) {
			throw new Exception("Header param 'Username' is required");
		}
		
		if(user.equalsIgnoreCase("Seshu") && password.equalsIgnoreCase("Seshu")) {
			authorized = true;
		}
		return authorized;
	}
}