package com.seshu.cs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.seshu.cs.dto.CarDTO;
import com.seshu.cs.dto.ComponentDTO;

public class ComponentDAO {

	public static String message;
	public static ComponentDTO populateComponent(DataSource ds, String customer, String reference, String component) {
		ComponentDTO dto = new ComponentDTO();
		try {
			Connection con = ds.getConnection();
			PreparedStatement st=con.prepareStatement("SELECT COMPONENT, FILENAME, DOCKEY, "
					+ "DESCRIPTION, REFERENCE, TDSCOMPLIANCE, AUTHOR, LINK, CUSTOMER, "
					+ "ADDDATE, ADDBY, EDITDATE, EDITBY "
					+ "FROM CAR "
					+ "WHERE CUSTOMER = ? AND REFERENCE = ? AND COMPONENT = ? ");
			st.setString(1,  customer);
			st.setString(2,  reference);
			st.setString(3,  component);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				dto.setComponent(rs.getString(1));
				dto.setFilename(rs.getString(2));
				dto.setDockey(rs.getString(3));
				dto.setDescription(rs.getString(4));
				dto.setReference(rs.getString(5));
				dto.setTdscompliance(rs.getString(6));
				dto.setAuthor(rs.getString(7));
				dto.setLink(rs.getString(8));
				dto.setCustomer(rs.getString(9));				
				dto.setAdddate(rs.getDate(10).toString());
				dto.setAddby(rs.getString(11));
				dto.setEditdate(rs.getDate(12).toString());
				dto.setEditby(rs.getString(13));
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			message = e.getMessage();
		}
		return dto;
	}
	
	public static CarDTO populateAllComponents(DataSource ds, String customer, String reference) {
		ComponentDTO dto = null;
		CarDTO components = new CarDTO();
		ArrayList<ComponentDTO> dtoList = new ArrayList<ComponentDTO>();
		try {
			Connection con = ds.getConnection();
			PreparedStatement st=con.prepareStatement("SELECT COMPONENT, FILENAME, DOCKEY, "
					+ "DESCRIPTION, REFERENCE, TDSCOMPLIANCE, AUTHOR, LINK, CUSTOMER, "
					+ "ADDDATE, ADDBY, EDITDATE, EDITBY "
					+ "FROM CAR "
					+ "WHERE CUSTOMER = ? AND REFERENCE = ? ");
			st.setString(1,  customer);
			st.setString(2,  reference);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				dto = new ComponentDTO();
				dto.setComponent(rs.getString(1));
				dto.setFilename(rs.getString(2));
				dto.setDockey(rs.getString(3));
				dto.setDescription(rs.getString(4));
				dto.setReference(rs.getString(5));
				dto.setTdscompliance(rs.getString(6));
				dto.setAuthor(rs.getString(7));
				dto.setLink(rs.getString(8));
				dto.setCustomer(rs.getString(9));				
				dto.setAdddate(rs.getDate(10).toString());
				dto.setAddby(rs.getString(11));
				dto.setEditdate(rs.getDate(12).toString());
				dto.setEditby(rs.getString(13));
				dtoList.add(dto);
			}
			components.setComponents(dtoList);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			message = e.getMessage();
		}
		return components;
	}
	
	public static CarDTO populateAllComponents(DataSource ds, String customer) {
		ComponentDTO dto = null;
		CarDTO components = new CarDTO();
		ArrayList<ComponentDTO> dtoList = new ArrayList<ComponentDTO>();
		try {
			Connection con = ds.getConnection();
			PreparedStatement st=con.prepareStatement("SELECT COMPONENT, FILENAME, DOCKEY, "
					+ "DESCRIPTION, REFERENCE, TDSCOMPLIANCE, AUTHOR, LINK, CUSTOMER, "
					+ "ADDDATE, ADDBY, EDITDATE, EDITBY "
					+ "FROM CAR "
					+ "WHERE CUSTOMER = ? ");
			st.setString(1,  customer);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				dto = new ComponentDTO();
				dto.setComponent(rs.getString(1));
				dto.setFilename(rs.getString(2));
				dto.setDockey(rs.getString(3));
				dto.setDescription(rs.getString(4));
				dto.setReference(rs.getString(5));
				dto.setTdscompliance(rs.getString(6));
				dto.setAuthor(rs.getString(7));
				dto.setLink(rs.getString(8));
				dto.setCustomer(rs.getString(9));				
				dto.setAdddate(rs.getDate(10).toString());
				dto.setAddby(rs.getString(11));
				dto.setEditdate(rs.getDate(12).toString());
				dto.setEditby(rs.getString(13));
				dtoList.add(dto);
			}
			components.setComponents(dtoList);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			message = e.getMessage();
		}
		return components;
	}
}