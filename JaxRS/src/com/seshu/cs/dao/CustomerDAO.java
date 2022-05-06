package com.seshu.cs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.seshu.cs.dto.CustomerDTO;
import com.seshu.cs.dto.CustomersDTO;

public class CustomerDAO {

	public static String message;
	public static CustomerDTO populateCustomer(DataSource ds, String customer, String region) {
		CustomerDTO dto = new CustomerDTO();
		try {
			Connection con = ds.getConnection();
			PreparedStatement st=con.prepareStatement("SELECT CUSTOMER, REGION, COUNTRY, "
						+ "LOGO, INDUSTRY, SUMMARY, WAREHOUSES, WMSVERSION, DEPLOYMENT, "
						+ "STATUS, INTEGRATIONTOOLS, CUSTOMIZATIONVOLUME, INFORTEAM, "
						+ "REPOSITORYLINK, REPOSITORYTYPE, ADDDATE, ADDBY, EDITDATE, "
						+ "EDITBY "
					+ "FROM CUSTOMER "
					+ "WHERE CUSTOMER = ? AND REGION = ? "
					+ "ORDER BY REGION, CUSTOMER");
			st.setString(1,  customer);
			st.setString(2,  region);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				dto.setCustomer(rs.getString(1));
				dto.setRegion(rs.getString(2));
				dto.setCountry(rs.getString(3));
				dto.setLogo(rs.getString(4));
				dto.setIndustry(rs.getString(5));
				dto.setSummary(rs.getString(6));
				dto.setWarehouses(""+rs.getInt(7));
				dto.setWmsversion(rs.getString(8));
				dto.setDeployment(rs.getString(9));
				dto.setStatus(rs.getString(10));
				dto.setIntegrationtools(rs.getString(11));
				dto.setCustomizationvolume(rs.getString(12));
				dto.setInforteam(rs.getString(13));
				dto.setRepositorylink(rs.getString(14));
				dto.setRepositorytype(rs.getString(15));
				dto.setAdddate(rs.getDate(16).toString());
				dto.setAddby(rs.getString(17));
				dto.setEditdate(rs.getDate(18).toString());
				dto.setEditby(rs.getString(19));
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			message = e.getMessage();
		}
		return dto;
	}
	
	public static CustomersDTO populateAllCustomers(DataSource ds) {
		CustomerDTO dto = null;
		CustomersDTO customers = new CustomersDTO();
		ArrayList<CustomerDTO> dtoList = new ArrayList<CustomerDTO>();
		try {
			Connection con = ds.getConnection();
			PreparedStatement st=con.prepareStatement("SELECT CUSTOMER, REGION, COUNTRY, "
						+ "LOGO, INDUSTRY, SUMMARY, WAREHOUSES, WMSVERSION, DEPLOYMENT, "
						+ "STATUS, INTEGRATIONTOOLS, CUSTOMIZATIONVOLUME, INFORTEAM, "
						+ "REPOSITORYLINK, REPOSITORYTYPE, ADDDATE, ADDBY, EDITDATE, "
						+ "EDITBY "
					+ "FROM CUSTOMER "					
					+ "ORDER BY REGION, CUSTOMER");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				dto = new CustomerDTO();
				dto.setCustomer(rs.getString(1));
				dto.setRegion(rs.getString(2));
				dto.setCountry(rs.getString(3));
				dto.setLogo(rs.getString(4));
				dto.setIndustry(rs.getString(5));
				dto.setSummary(rs.getString(6));
				dto.setWarehouses(""+rs.getInt(7));
				dto.setWmsversion(rs.getString(8));
				dto.setDeployment(rs.getString(9));
				dto.setStatus(rs.getString(10));
				dto.setIntegrationtools(rs.getString(11));
				dto.setCustomizationvolume(rs.getString(12));
				dto.setInforteam(rs.getString(13));
				dto.setRepositorylink(rs.getString(14));
				dto.setRepositorytype(rs.getString(15));
				dto.setAdddate(rs.getDate(16).toString());
				dto.setAddby(rs.getString(17));
				dto.setEditdate(rs.getDate(18).toString());
				dto.setEditby(rs.getString(19));
				dtoList.add(dto);
			}
			customers.setCustomers(dtoList);
		}catch(Exception e) {
			//System.out.println(e.getMessage());
			message = e.getMessage();
		}
		return customers;
	}
	
	public static CustomersDTO populateAllCustomers(DataSource ds, String region, String customer) {
		CustomerDTO dto = null;
		CustomersDTO customers = new CustomersDTO();
		ArrayList<CustomerDTO> dtoList = new ArrayList<CustomerDTO>();
		try {
			Connection con = ds.getConnection();
			PreparedStatement st=con.prepareStatement("SELECT CUSTOMER, REGION, COUNTRY, "
						+ "LOGO, INDUSTRY, SUMMARY, WAREHOUSES, WMSVERSION, DEPLOYMENT, "
						+ "STATUS, INTEGRATIONTOOLS, CUSTOMIZATIONVOLUME, INFORTEAM, "
						+ "REPOSITORYLINK, REPOSITORYTYPE, ADDDATE, ADDBY, EDITDATE, "
						+ "EDITBY "
					+ "FROM CUSTOMER WHERE REGION = ? AND CUSTOMER = ? "					
					+ "ORDER BY REGION, CUSTOMER");
			st.setString(1,  region);
			st.setString(2,  customer);
			
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				dto = new CustomerDTO();
				dto.setCustomer(rs.getString(1));
				dto.setRegion(rs.getString(2));
				dto.setCountry(rs.getString(3));
				dto.setLogo(rs.getString(4));
				dto.setIndustry(rs.getString(5));
				dto.setSummary(rs.getString(6));
				dto.setWarehouses(""+rs.getInt(7));
				dto.setWmsversion(rs.getString(8));
				dto.setDeployment(rs.getString(9));
				dto.setStatus(rs.getString(10));
				dto.setIntegrationtools(rs.getString(11));
				dto.setCustomizationvolume(rs.getString(12));
				dto.setInforteam(rs.getString(13));
				dto.setRepositorylink(rs.getString(14));
				dto.setRepositorytype(rs.getString(15));
				dto.setAdddate(rs.getDate(16).toString());
				dto.setAddby(rs.getString(17));
				dto.setEditdate(rs.getDate(18).toString());
				dto.setEditby(rs.getString(19));
				dtoList.add(dto);
			}
			customers.setCustomers(dtoList);
		}catch(Exception e) {
			//System.out.println(e.getMessage());
			message = e.getMessage();
		}
		return customers;
	}
}