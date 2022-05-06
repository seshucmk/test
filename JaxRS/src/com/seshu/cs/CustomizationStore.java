package com.seshu.cs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.seshu.cs.dao.ComponentDAO;
import com.seshu.cs.dao.ContentDAO;
import com.seshu.cs.dao.CustomerDAO;
import com.seshu.cs.dao.SVNHistoryDAO;
import com.seshu.cs.dto.CarDTO;
import com.seshu.cs.dto.ComponentDTO;
import com.seshu.cs.dto.ContentDTO;
import com.seshu.cs.dto.ContentsDTO;
import com.seshu.cs.dto.CustomerDTO;
import com.seshu.cs.dto.CustomersDTO;
import com.seshu.cs.dto.SVNEntriesListDTO;
import com.seshu.cs.dto.SVNHistoryDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api
@Path("/cs")
public class CustomizationStore extends CSRestService {
	
	private String svnUser = "syaddanapudi";
	private String svnPassword = "SVN_3021";
	private String imagesPath = "C:\\Users\\syaddana\\OneDrive - Infor\\GDS\\Team\\CRS\\MetaData\\Logos\\";
	
	/*
	@GET
	@Path("/customerslist")
	@ApiOperation(value = "Get Customers",
		response = CustomerDTO.class,
		nickname = "getCustomers")
	public Response getCustomer() {
			
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		//String tableContent = "<table border=\"1px\"><tr><th>Region</th><th>Customer</th><th>Logo</th></tr>";
		String tableContent = "<table><tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr><tr>";
		try	{
			Context initialContext = new InitialContext();
			DataSource datasource = (DataSource)initialContext.lookup("java:/MSSQLDS");
			con = datasource.getConnection();
			//st=con.prepareStatement("SELECT REGION, CUSTOMER FROM CUSTOMER ORDER BY REGION, CUSTOMER");
			st=con.prepareStatement("SELECT DISTINCT CUSTOMER FROM CUSTOMER WHERE LOGO != 'NO'");
			rs = st.executeQuery();
			int i = 0;
			while(rs.next()) {
				i++;				
				tableContent = tableContent+"<td width=\"200\" height=\"100\" align=\"center\"><img width=\"100%\" height=\"80%\" src= \""+imagesPath+rs.getString(1)+".png\"></img></td>";
				//tableContent = tableContent+"<tr><td>"+rs.getString(1)+"</td>"
				//		+ "<td>"+rs.getString(2)+"</td>"				
				//		+ "<td width=\"100\" height=\"100\"><img width=\"100%\" height=\"80%\" alt=\""+imagesPath+rs.getString(2)+".png\""+"\" src= \""+imagesPath+rs.getString(2)+".png\"></img></td></tr>";
						//+ "<td>"+new File(imagesPath).getAbsolutePath()+"</td></tr>";
				if(i%8 == 0) {
					tableContent = tableContent+"</tr><tr>";
				}
			}
			tableContent = tableContent+"</tr></table>";
			
			rs.close();
			st.close();
			con.close();
		}catch(Exception e)	{
			Response.status(500).entity(e.getMessage()).build();
		}
		
		return Response.status(200).entity(tableContent).build();
	}
	*/
	
	@GET
	@Path("/customer/{region}/{customer}")
	@ApiOperation(value = "Get Customers",
		response = CustomerDTO.class,
		nickname = "getCustomers")
	public Response getCustomer(@ApiParam(value="region", required=true) @PathParam("region") String region,
						@ApiParam(value="customer", required=true) @PathParam("customer") String customer) {
		CustomerDTO dto = null;		
		try {
			Context initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup("java:/MSSQLDS");
			if(region == null || customer == null) {
				
			}else {
				dto = CustomerDAO.populateCustomer(ds, customer, region);
			}
		}catch(Exception e) {
			Response.status(500).entity(e.getMessage()).build();
		}
		
		return Response.status(200).entity(dto).build();
	}
	
	@GET
	@Path("/customers")
	@ApiOperation(value = "Get Customers",
		response = CustomersDTO.class,
		nickname = "getCustomers")
	public Response getCustomers() {
			
		CustomersDTO dto = null;		
		try {
			Context initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup("java:/MSSQLDS");
			dto = CustomerDAO.populateAllCustomers(ds);
			
		}catch(Exception e) {
			Response.status(500).entity(e.getMessage()).build();
		}
		
		return Response.status(200).entity(dto).build();
	}
	/*
	@GET
	@Path("/customerstest")
	@Produces({"text/html"})
	@ApiOperation(value = "Get Customers",
		response = CustomersDTO.class,
		nickname = "getCustomers")
	public Response getCustomersTest() {
			
		CustomersDTO dto = null;	
		String tableContent = "<H2>Records fetched...";
		try {
			Context initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup("java:/MSSQLDS");
			dto = CustomerDAO.populateAllCustomers(ds);
			ArrayList<CustomerDTO> rowEntries = (ArrayList<CustomerDTO>)dto.getCustomers();
			if(rowEntries.size() == 0 && CustomerDAO.message != null) {
				tableContent = tableContent+"<tr><td>"+CustomerDAO.message+"</td></tr>";
			}else {
				tableContent = tableContent + rowEntries.size() + "</H2>";
				tableContent = tableContent+"<table border=1px><tr><th>Region</th><th>Customer</th><th>Industry</th><th>Summary</th></tr>";
				for (Iterator<CustomerDTO> line = rowEntries.iterator(); line.hasNext();) {
					CustomerDTO customer = line.next();
	        		tableContent = tableContent+"<tr><td>"+customer.getRegion()+"</td><td>"+customer.getCustomer()+"</td><td>"
	        							+customer.getIndustry()+"</td><td>"+customer.getSummary()+"</td></tr>";
	        	}
			}
			tableContent = tableContent+"</table>";
		}catch(Exception e) {
			tableContent = e.getMessage();
			//Response.status(500).entity(e.getMessage()).build();			
		}
		
		return Response.status(200).entity(tableContent).build();
	}
	
	@GET
	@Path("/customerstest/{region}/{customer}")
	@Produces({"text/html"})
	@ApiOperation(value = "Get Customers",
		response = CustomersDTO.class,
		nickname = "getCustomers")
	public Response getCustomersTest(@ApiParam(value="region", required=true) @PathParam("region") String region,
			@ApiParam(value="customer", required=true) @PathParam("customer") String customer) {
			
		CustomersDTO dto = null;	
		String tableContent = "<H2>Records fetched...";
		try {
			Context initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup("java:/MSSQLDS");
			dto = CustomerDAO.populateAllCustomers(ds, region, customer);
			ArrayList<CustomerDTO> rowEntries = (ArrayList<CustomerDTO>)dto.getCustomers();
			if(rowEntries.size() == 0 && CustomerDAO.message != null) {
				tableContent = tableContent+"<tr><td>"+CustomerDAO.message+"</td></tr>";
			}else {
				tableContent = tableContent + rowEntries.size() + "</H2>";
				tableContent = tableContent+"<table border=1px><tr><th>Region</th><th>Customer</th><th>Industry</th><th>Summary</th></tr>";
				for (Iterator<CustomerDTO> line = rowEntries.iterator(); line.hasNext();) {
					CustomerDTO customerDTO = line.next();
	        		tableContent = tableContent+"<tr><td>"+customerDTO.getRegion()+"</td><td>"+customerDTO.getCustomer()+"</td><td>"
	        							+customerDTO.getIndustry()+"</td><td>"+customerDTO.getSummary()+"</td></tr>";
	        	}
			}
			tableContent = tableContent+"</table>";
		}catch(Exception e) {
			tableContent = e.getMessage();
			//Response.status(500).entity(e.getMessage()).build();			
		}
		
		return Response.status(200).entity(tableContent).build();
	}
	*/
	
	@GET
	//@Produces({"text/html"})
	@Path("/svn")
	@ApiOperation(value = "Get SVN Info",
		response = SVNEntriesListDTO.class,
		nickname = "SVN")
	public Response getSVNInfo(@ApiParam(value="customer", required=true) @PathParam("customer") String customer,
								@ApiParam(value="reference", required=true) @PathParam("reference") String reference,
								@ApiParam(value="component", required=true) @PathParam("component") String component,
								@ApiParam(value="svnuser", required=true) @PathParam("svnuser") String svnuser,
								@ApiParam(value="svnpswd", required=true) @PathParam("svnpswd") String svnpswd) {
			
		SVNEntriesListDTO dto = null;		
		try {
			Context initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup("java:/MSSQLDS");
			dto = SVNHistoryDAO.populateSVNData(ds, customer, reference, component, svnuser, svnpswd);
			
		}catch(Exception e) {
			Response.status(500).entity(e.getMessage()).build();
		}
		
		return Response.status(200).entity(dto).build();
	}
	/*
	@GET
	@Produces({"text/html"})
	@Path("/svntest/{customer}/{reference}/{component}")
	@ApiOperation(value = "Get SVN Info Test",
		response = SVNEntriesListDTO.class,
		nickname = "SVN Test")
	public Response getSVNInfoTest(@ApiParam(value="customer", required=true) @PathParam("customer") String customer,
								@ApiParam(value="reference", required=true) @PathParam("reference") String reference,
								@ApiParam(value="component", required=true) @PathParam("component") String component) {
			
		SVNEntriesListDTO dto = null;
		String tableContent = "<H2>Records fetched...";
		try {
			Context initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup("java:/MSSQLDS");
			dto = SVNHistoryDAO.populateSVNData(ds, customer, reference, component, svnUser, svnPassword);			
			ArrayList<SVNHistoryDTO> rowEntries = (ArrayList<SVNHistoryDTO>)dto.getSvnHistory();
			if(rowEntries.size() == 0 && SVNHistoryDAO.message != null) {
				tableContent = tableContent+"0</H2><table border=1px><tr><td>"+SVNHistoryDAO.message+"</td></tr>";
			}else {
				tableContent = tableContent + rowEntries.size() + "</H2>";
				tableContent = tableContent+"<table border=1px><tr><th>Revision</th><th>Author</th><th>Modified Date</th><th>Comments</th></tr>";
				for (Iterator<SVNHistoryDTO> line = rowEntries.iterator(); line.hasNext();) {
					SVNHistoryDTO svndto = line.next();
	        		tableContent = tableContent+"<tr><td>"+svndto.getRevision()+"</td><td>"+svndto.getAuthor()+"</td><td>"
	        							+svndto.getEditdate()+"</td><td>"+svndto.getComments()+"</td></tr>";
	        	}
			}
			tableContent = tableContent+"</table>";
		}catch(Exception e) {
			tableContent = e.getMessage();
			//Response.status(500).entity(e.getMessage()).build();			
		}
		
		return Response.status(200).entity(tableContent).build();
	}
	*/
	@GET
	@Path("/content/{customer}/{type}")
	@ApiOperation(value = "Get Content",
		response = ContentsDTO.class,
		nickname = "getContent")
	public Response getContent(@ApiParam(value="customer", required=true) @PathParam("customer") String customer,
			@ApiParam(value="type", required=true) @PathParam("type") String type) {
			
		ContentsDTO dto = null;		
		try {
			Context initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup("java:/MSSQLDS");
			dto = ContentDAO.populateContent(ds, customer, type);
			
		}catch(Exception e) {
			Response.status(500).entity(e.getMessage()).build();
		}
		
		return Response.status(200).entity(dto).build();
	}
	/*
	@GET
	@Produces({"text/html"})
	@Path("/customercontenttest/{customer}/{type}")
	@ApiOperation(value = "Get Content",
		response = ContentsDTO.class,
		nickname = "getContent")
	public Response getCustomerContentTest(@ApiParam(value="customer", required=true) @PathParam("customer") String customer,
			@ApiParam(value="type", required=true) @PathParam("type") String type) {
			
		ContentsDTO dto = null;
		String tableContent = "<H2>Records fetched...";
		try {
			Context initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup("java:/MSSQLDS");
			dto = ContentDAO.populateCustomerContent(ds, customer, type);
			ArrayList<ContentDTO> rowEntries = (ArrayList<ContentDTO>)dto.getContent();
			if(rowEntries.size() == 0 && ContentDAO.message != null) {
				tableContent = tableContent+"0</H2><table border=1px><tr><td>"+ContentDAO.message+"</td></tr>";
			}else {
				tableContent = tableContent + rowEntries.size() + "</H2>";
				tableContent = tableContent+"<table border=1px><tr><th>Key</th><th>Document</th><th>Process</th><th>Reference</th><th>Document Data</th></tr>";
				for (Iterator<ContentDTO> line = rowEntries.iterator(); line.hasNext();) {
					ContentDTO contentDTO = line.next();
					int len = contentDTO.getDocdata().length();
					String content = "";
					if(len < 200)
						content = contentDTO.getDocdata();
					else
						content = contentDTO.getDocdata().substring(0, 200);
					
	        		tableContent = tableContent+"<tr><td>"+contentDTO.getDockey()+"</td><td>"+contentDTO.getFilename()+"</td><td>"
	        							+contentDTO.getProcess()+"</td><td>"+contentDTO.getReference()+"</td><td>"+content+"....</td></tr>";
	        	}
			}
			tableContent = tableContent+"</table>";
		}catch(Exception e) {
			tableContent = e.getMessage();
			//Response.status(500).entity(e.getMessage()).build();			
		}
		
		return Response.status(200).entity(tableContent).build();
	}
	*/
	
	@GET
	@Produces({"text/html"})
	@Path("/content/{type}")
	@ApiOperation(value = "Get Content",
		response = ContentsDTO.class,
		nickname = "getContent")
	public Response getContentByTypeTest(@ApiParam(value="type", required=true) @PathParam("type") String type) {
			
		ContentsDTO dto = null;
		String tableContent = "<H2>Records fetched...";
		try {
			Context initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup("java:/MSSQLDS");
			dto = ContentDAO.populateContentByType(ds, type);
			ArrayList<ContentDTO> rowEntries = (ArrayList<ContentDTO>)dto.getContent();
			if(rowEntries.size() == 0 && ContentDAO.message != null) {
				tableContent = tableContent+"0</H2><table border=1px><tr><td>"+ContentDAO.message+"</td></tr>";
			}else {
				tableContent = tableContent + rowEntries.size() + "</H2>";
				tableContent = tableContent+"<table border=1px><tr><th>Key</th><th>Customer</th><th>Document</th><th>Process</th><th>Reference</th><th>Document Data</th></tr>";
				for (Iterator<ContentDTO> line = rowEntries.iterator(); line.hasNext();) {
					ContentDTO contentDTO = line.next();
					int len = contentDTO.getDocdata().length();
					String content = "";
					if(len < 200)
						content = contentDTO.getDocdata();
					else
						content = contentDTO.getDocdata().substring(0, 200);
					
	        		tableContent = tableContent+"<tr><td>"+contentDTO.getDockey()+"</td><td>"+contentDTO.getCustomer()+"</td><td>"+contentDTO.getFilename()+"</td><td>"
	        							+contentDTO.getProcess()+"</td><td>"+contentDTO.getReference()+"</td><td>"+content+"....</td></tr>";
	        	}
			}
			tableContent = tableContent+"</table>";
		}catch(Exception e) {
			tableContent = e.getMessage();
			//Response.status(500).entity(e.getMessage()).build();			
		}
		
		return Response.status(200).entity(tableContent).build();
	}
	
	@GET
	@Produces({"text/html"})
	@Path("/process/{type}/{process}")
	@ApiOperation(value = "Get Content",
		response = ContentsDTO.class,
		nickname = "getContent")
	public Response getContentTest(@ApiParam(value="type", required=true) @PathParam("type") String type,
							@ApiParam(value="process", required=true) @PathParam("process") String process) {
			
		ContentsDTO dto = null;
		String tableContent = "<H2>Records fetched...";
		try {
			Context initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup("java:/MSSQLDS");
			dto = ContentDAO.populateContent(ds, type, process);
			ArrayList<ContentDTO> rowEntries = (ArrayList<ContentDTO>)dto.getContent();
			if(rowEntries.size() == 0 && ContentDAO.message != null) {
				tableContent = tableContent+"0</H2><table border=1px><tr><td>"+ContentDAO.message+"</td></tr>";
			}else {
				tableContent = tableContent + rowEntries.size() + "</H2>";
				tableContent = tableContent + "<table border=1px><tr><th>Key</th><th>Customer</th><th>Document</th><th>Process</th><th>Reference</th><th>Document Data</th></tr>";
				for (Iterator<ContentDTO> line = rowEntries.iterator(); line.hasNext();) {
					ContentDTO contentDTO = line.next();
					int len = contentDTO.getDocdata().length();
					String content = "";
					if(len < 200)
						content = contentDTO.getDocdata();
					else
						content = contentDTO.getDocdata().substring(0, 200);
					
	        		tableContent = tableContent+"<tr><td>"+contentDTO.getDockey()+"</td><td>"+contentDTO.getCustomer()+"</td><td>"+contentDTO.getFilename()+"</td><td>"
	        							+contentDTO.getProcess()+"</td><td>"+contentDTO.getReference()+"</td><td>"+content+"....</td></tr>";
	        	}
			}
			tableContent = tableContent+"</table>";
		}catch(Exception e) {
			tableContent = e.getMessage();
			//Response.status(500).entity(e.getMessage()).build();			
		}
		
		return Response.status(200).entity(tableContent).build();
	}
	
	@GET
	@Produces({"text/html"})
	@Path("/contenttest/{type}/{process}/{docdata}")
	@ApiOperation(value = "Get Content",
		response = ContentsDTO.class,
		nickname = "getContent")
	public Response getContentTest(@ApiParam(value="type", required=true) @PathParam("type") String type,
							@ApiParam(value="process", required=true) @PathParam("process") String process,
							@ApiParam(value="docdata", required=true) @PathParam("docdata") String docdata) {
			
		ContentsDTO dto = null;
		String tableContent = "<H2>Records fetched...";
		try {
			Context initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup("java:/MSSQLDS");
			dto = ContentDAO.populateContent(ds, type, process, docdata);
			ArrayList<ContentDTO> rowEntries = (ArrayList<ContentDTO>)dto.getContent();
			if(rowEntries.size() == 0 && ContentDAO.message != null) {
				tableContent = tableContent+"0</H2><table border=1px><tr><td>"+ContentDAO.message+"</td></tr>";
			}else {
				tableContent = tableContent + rowEntries.size() + "</H2>";
				tableContent = tableContent + "<table border=1px><tr><th>Key</th><th>Customer</th><th>Document</th><th>Process</th><th>Reference</th><th>Document Data</th></tr>";
				for (Iterator<ContentDTO> line = rowEntries.iterator(); line.hasNext();) {
					ContentDTO contentDTO = line.next();
					int len = contentDTO.getDocdata().length();
					String content = "";
					if(len < 200)
						content = contentDTO.getDocdata();
					else
						content = contentDTO.getDocdata().substring(0, 200);
					
	        		tableContent = tableContent+"<tr><td>"+contentDTO.getDockey()+"</td><td>"+contentDTO.getCustomer()+"</td><td>"+contentDTO.getFilename()+"</td><td>"
	        							+contentDTO.getProcess()+"</td><td>"+contentDTO.getReference()+"</td><td>"+content+"....</td></tr>";
	        	}
			}
			tableContent = tableContent+"</table>";
		}catch(Exception e) {
			tableContent = e.getMessage();
			//Response.status(500).entity(e.getMessage()).build();			
		}
		
		return Response.status(200).entity(tableContent).build();
	}
	
	@GET
	@Produces({"text/html"})
	@Path("/contenttest/{key}")
	@ApiOperation(value = "Get Content",
		response = ContentsDTO.class,
		nickname = "getContent")
	public Response getContentTest(@ApiParam(value="key", required=true) @PathParam("key") String key) {
			
		ContentsDTO dto = null;
		String tableContent = "<H2>Records fetched...";
		try {
			Context initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup("java:/MSSQLDS");
			dto = ContentDAO.populateContent(ds, key);
			ArrayList<ContentDTO> rowEntries = (ArrayList<ContentDTO>)dto.getContent();
			if(rowEntries.size() == 0 && ContentDAO.message != null) {
				tableContent = tableContent+"0</H2><table border=1px><tr><td>"+ContentDAO.message+"</td></tr>";
			}else {
				tableContent = tableContent + rowEntries.size() + "</H2>";
				tableContent = tableContent + "<table border=1px><tr><th>Key</th><th>Customer</th><th>Document</th><th>Process</th><th>Reference</th><th>Document Data</th></tr>";
				for (Iterator<ContentDTO> line = rowEntries.iterator(); line.hasNext();) {
					ContentDTO contentDTO = line.next();		
					
	        		tableContent = tableContent+"<tr><td>"+contentDTO.getDockey()+"</td><td>"+contentDTO.getCustomer()+"</td><td>"+contentDTO.getFilename()+"</td><td>"
	        							+contentDTO.getProcess()+"</td><td>"+contentDTO.getReference()+"</td><td>"+contentDTO.getDocdata()+"</td></tr>";
	        	}
			}
			tableContent = tableContent+"</table>";
		}catch(Exception e) {
			tableContent = e.getMessage();
			//Response.status(500).entity(e.getMessage()).build();			
		}
		
		return Response.status(200).entity(tableContent).build();
	}
	
	@GET
	@Produces({"text/html"})
	@Path("/componenttest/{customer}/{reference}/{component}")
	@ApiOperation(value = "Get Component",
		response = ComponentDTO.class,
		nickname = "getComponent")
	public Response getComponentTest(@ApiParam(value="customer", required=true) @PathParam("customer") String customer,
							@ApiParam(value="reference", required=true) @PathParam("reference") String reference,
							@ApiParam(value="component", required=true) @PathParam("component") String component) {
			
		ComponentDTO componentDTO = null;
		String tableComponent = "";
		try {
			Context initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup("java:/MSSQLDS");
			componentDTO = ComponentDAO.populateComponent(ds, customer, reference, component);			
			tableComponent = tableComponent + "<table border=1px><tr><th>Component</th><th>Customer</th><th>CAR</th><th>Description</th><th>Reference</th><th>Link</th></tr>";
			tableComponent = tableComponent+"<tr><td>"+componentDTO.getComponent()+"</td><td>"+componentDTO.getCustomer()+"</td><td>"+componentDTO.getFilename()+"</td><td>"
	        							+componentDTO.getDescription()+"</td><td>"+componentDTO.getReference()+"</td><td>"+componentDTO.getLink()+"</td></tr>";
			tableComponent = tableComponent+"</table>";
			
		}catch(Exception e) {
			tableComponent = e.getMessage();
			//Response.status(500).entity(e.getMessage()).build();			
		}
		
		return Response.status(200).entity(tableComponent).build();
	}
	
	@GET
	@Produces({"text/html"})
	@Path("/componenttest/{customer}/{reference}")
	@ApiOperation(value = "Get Component",
		response = CarDTO.class,
		nickname = "getComponent")
	public Response getComponentTest(@ApiParam(value="customer", required=true) @PathParam("customer") String customer,
			@ApiParam(value="reference", required=true) @PathParam("reference") String reference) {
			
		CarDTO dto = null;
		String tableComponent = "<H2>Records fetched...";
		try {
			Context initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup("java:/MSSQLDS");
			dto = ComponentDAO.populateAllComponents(ds, customer, reference);
			ArrayList<ComponentDTO> rowEntries = (ArrayList<ComponentDTO>)dto.getComponents();
			if(rowEntries.size() == 0 && ComponentDAO.message != null) {
				tableComponent = tableComponent+"0</H2><table border=1px><tr><td>"+ComponentDAO.message+"</td></tr>";
			}else {
				tableComponent = tableComponent + rowEntries.size() + "</H2>";
				tableComponent = tableComponent + "<table border=1px><tr><th>Component</th><th>Customer</th><th>CAR</th><th>Description</th><th>Reference</th><th>Link</th></tr>";
				for (Iterator<ComponentDTO> line = rowEntries.iterator(); line.hasNext();) {
					ComponentDTO componentDTO = line.next();					
					tableComponent = tableComponent+"<tr><td>"+componentDTO.getComponent()+"</td><td>"+componentDTO.getCustomer()+"</td><td>"+componentDTO.getFilename()+"</td><td>"
							+componentDTO.getDescription()+"</td><td>"+componentDTO.getReference()+"</td><td>"+componentDTO.getLink()+"</td></tr>";
	        	}
			}
			tableComponent = tableComponent+"</table>";
		}catch(Exception e) {
			tableComponent = e.getMessage();
			//Response.status(500).entity(e.getMessage()).build();			
		}
		
		return Response.status(200).entity(tableComponent).build();
	}
	
	@GET
	@Produces({"text/html"})
	@Path("/componenttest/{customer}")
	@ApiOperation(value = "Get Component",
		response = CarDTO.class,
		nickname = "getComponent")
	public Response getComponentTest(@ApiParam(value="customer", required=true) @PathParam("customer") String customer) {
			
		CarDTO dto = null;
		String tableComponent = "<H2>Records fetched...";
		try {
			Context initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup("java:/MSSQLDS");
			dto = ComponentDAO.populateAllComponents(ds, customer);
			ArrayList<ComponentDTO> rowEntries = (ArrayList<ComponentDTO>)dto.getComponents();
			if(rowEntries.size() == 0 && ComponentDAO.message != null) {
				tableComponent = tableComponent+"0</H2><table border=1px><tr><td>"+ComponentDAO.message+"</td></tr>";
			}else {
				tableComponent = tableComponent + rowEntries.size() + "</H2>";
				tableComponent = tableComponent + "<table border=1px><tr><th>Component</th><th>Customer</th><th>CAR</th><th>Description</th><th>Reference</th><th>Link</th></tr>";
				for (Iterator<ComponentDTO> line = rowEntries.iterator(); line.hasNext();) {
					ComponentDTO componentDTO = line.next();					
					tableComponent = tableComponent+"<tr><td>"+componentDTO.getComponent()+"</td><td>"+componentDTO.getCustomer()+"</td><td>"+componentDTO.getFilename()+"</td><td>"
							+componentDTO.getDescription()+"</td><td>"+componentDTO.getReference()+"</td><td>"+componentDTO.getLink()+"</td></tr>";
	        	}
			}
			tableComponent = tableComponent+"</table>";
		}catch(Exception e) {
			tableComponent = e.getMessage();
			//Response.status(500).entity(e.getMessage()).build();			
		}
		
		return Response.status(200).entity(tableComponent).build();
	}

}