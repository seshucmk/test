package com.seshu.cs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.seshu.cs.dto.ContentDTO;
import com.seshu.cs.dto.ContentsDTO;

public class ContentDAO {

	public static String message;

	public static ContentsDTO populateCustomerContent(DataSource ds, String customer, String type, String process) {
		ContentsDTO contentListDto = new ContentsDTO();
		ArrayList<ContentDTO> dtoList = new ArrayList<ContentDTO>();
		ContentDTO dto = null;
		try {
			Connection con = ds.getConnection();
			String statement = "SELECT DOCKEY, CUSTOMER, " + "TYPE, FILENAME, REFERENCE, DOCDATA, SUMMARY, AUTHOR, "
					+ "PROCESS, SUBPROCESS, REUSABLEPER, REUSABLEREASON, "
					+ "ESTIMATION, LINK, ADDDATE, ADDBY, EDITDATE, EDITBY " + "FROM CONTENT ";
			
			String whereClause = "";			
			if(customer != null && customer.trim().length() !=0) {
				whereClause = " WHERE CUSTOMER like %'?'% ";				
			}
						
			if(type != null && type.trim().length() !=0) {
				if(whereClause.trim().length() == 0) {
					whereClause = " WHERE TYPE like %'?'% ";		
				}else {
					whereClause = whereClause+" AND TYPE like %'?'% ";
				}				
			}		
			
			if(process != null && process.trim().length() !=0) {
				if(whereClause.trim().length() == 0) {
					whereClause = " WHERE PROCESS like %'?'% ";		
				}else {
					whereClause = whereClause+" AND PROCESS like %'?'% ";
				}
			}
			
			PreparedStatement st = con.prepareStatement(statement+whereClause);
			
			st.setString(1, customer);
			st.setString(2, type);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				dto = new ContentDTO();
				dto.setDockey(rs.getString(1));
				dto.setCustomer(rs.getString(2));
				dto.setType(rs.getString(3));
				dto.setFilename(rs.getString(4));
				dto.setReference(rs.getString(5));
				dto.setDocdata(rs.getString(6));
				dto.setSummary(rs.getString(7));
				dto.setAuthor(rs.getString(8));
				dto.setProcess(rs.getString(9));
				dto.setSubprocess(rs.getString(10));
				dto.setReusableper(rs.getString(11));
				dto.setReusablereason(rs.getString(12));
				dto.setEstimation(rs.getString(13));
				dto.setLink(rs.getString(14));
				dto.setAdddate(rs.getDate(15).toString());
				dto.setAddby(rs.getString(16));
				dto.setEditdate(rs.getDate(17).toString());
				dto.setEditby(rs.getString(18));

				dtoList.add(dto);
			}
			contentListDto.setContent(dtoList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			message = e.getMessage();
		}
		return contentListDto;
	}
	
	public static ContentsDTO populateCustomerContent(DataSource ds, String customer, String type) {
		ContentsDTO contentListDto = new ContentsDTO();
		ArrayList<ContentDTO> dtoList = new ArrayList<ContentDTO>();
		ContentDTO dto = null;
		try {
			Connection con = ds.getConnection();
			PreparedStatement st = con.prepareStatement(
					"SELECT DOCKEY, CUSTOMER, " + "TYPE, FILENAME, REFERENCE, DOCDATA, SUMMARY, AUTHOR, "
							+ "PROCESS, SUBPROCESS, REUSABLEPER, REUSABLEREASON, "
							+ "ESTIMATION, LINK, ADDDATE, ADDBY, EDITDATE, EDITBY " + "FROM CONTENT "
							+ "WHERE CUSTOMER = ? AND TYPE = ? ");
			st.setString(1, customer);
			st.setString(2, type);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				dto = new ContentDTO();
				dto.setDockey(rs.getString(1));
				dto.setCustomer(rs.getString(2));
				dto.setType(rs.getString(3));
				dto.setFilename(rs.getString(4));
				dto.setReference(rs.getString(5));
				dto.setDocdata(rs.getString(6));
				dto.setSummary(rs.getString(7));
				dto.setAuthor(rs.getString(8));
				dto.setProcess(rs.getString(9));
				dto.setSubprocess(rs.getString(10));
				dto.setReusableper(rs.getString(11));
				dto.setReusablereason(rs.getString(12));
				dto.setEstimation(rs.getString(13));
				dto.setLink(rs.getString(14));
				dto.setAdddate(rs.getDate(15).toString());
				dto.setAddby(rs.getString(16));
				dto.setEditdate(rs.getDate(17).toString());
				dto.setEditby(rs.getString(18));

				dtoList.add(dto);
			}
			contentListDto.setContent(dtoList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			message = e.getMessage();
		}
		return contentListDto;
	}

	public static ContentsDTO populateContentByType(DataSource ds, String type) {
		ContentsDTO contentListDto = new ContentsDTO();
		ArrayList<ContentDTO> dtoList = new ArrayList<ContentDTO>();
		ContentDTO dto = null;
		try {
			Connection con = ds.getConnection();
			PreparedStatement st = con.prepareStatement(
					"SELECT DOCKEY, CUSTOMER, " + "TYPE, FILENAME, REFERENCE, DOCDATA, SUMMARY, AUTHOR, "
							+ "PROCESS, SUBPROCESS, REUSABLEPER, REUSABLEREASON, "
							+ "ESTIMATION, LINK, ADDDATE, ADDBY, EDITDATE, EDITBY " + "FROM CONTENT "
							+ "WHERE TYPE = ? ");			
			st.setString(1, type);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				dto = new ContentDTO();
				dto.setDockey(rs.getString(1));
				dto.setCustomer(rs.getString(2));
				dto.setType(rs.getString(3));
				dto.setFilename(rs.getString(4));
				dto.setReference(rs.getString(5));
				dto.setDocdata(rs.getString(6));
				dto.setSummary(rs.getString(7));
				dto.setAuthor(rs.getString(8));
				dto.setProcess(rs.getString(9));
				dto.setSubprocess(rs.getString(10));
				dto.setReusableper(rs.getString(11));
				dto.setReusablereason(rs.getString(12));
				dto.setEstimation(rs.getString(13));
				dto.setLink(rs.getString(14));
				dto.setAdddate(rs.getDate(15).toString());
				dto.setAddby(rs.getString(16));
				dto.setEditdate(rs.getDate(17).toString());
				dto.setEditby(rs.getString(18));

				dtoList.add(dto);
			}
			contentListDto.setContent(dtoList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			message = e.getMessage();
		}
		return contentListDto;
	}
	
	public static ContentsDTO populateContent(DataSource ds, String type, String process) {
		ContentsDTO contentListDto = new ContentsDTO();
		ArrayList<ContentDTO> dtoList = new ArrayList<ContentDTO>();
		ContentDTO dto = null;
		try {
			Connection con = ds.getConnection();
			PreparedStatement st = con.prepareStatement(
					"SELECT DOCKEY, CUSTOMER, " + "TYPE, FILENAME, REFERENCE, DOCDATA, SUMMARY, AUTHOR, "
							+ "PROCESS, SUBPROCESS, REUSABLEPER, REUSABLEREASON, "
							+ "ESTIMATION, LINK, ADDDATE, ADDBY, EDITDATE, EDITBY " + "FROM CONTENT "
							+ "WHERE TYPE = ? AND PROCESS = ? ");			
			st.setString(1, type);
			st.setString(2, process);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				dto = new ContentDTO();
				dto.setDockey(rs.getString(1));
				dto.setCustomer(rs.getString(2));
				dto.setType(rs.getString(3));
				dto.setFilename(rs.getString(4));
				dto.setReference(rs.getString(5));
				dto.setDocdata(rs.getString(6));
				dto.setSummary(rs.getString(7));
				dto.setAuthor(rs.getString(8));
				dto.setProcess(rs.getString(9));
				dto.setSubprocess(rs.getString(10));
				dto.setReusableper(rs.getString(11));
				dto.setReusablereason(rs.getString(12));
				dto.setEstimation(rs.getString(13));
				dto.setLink(rs.getString(14));
				dto.setAdddate(rs.getDate(15).toString());
				dto.setAddby(rs.getString(16));
				dto.setEditdate(rs.getDate(17).toString());
				dto.setEditby(rs.getString(18));

				dtoList.add(dto);
			}
			contentListDto.setContent(dtoList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			message = e.getMessage();
		}
		return contentListDto;
	}

	public static ContentsDTO populateContent(DataSource ds, String type, String process, String searchString) {
		ContentsDTO contentListDto = new ContentsDTO();
		ArrayList<ContentDTO> dtoList = new ArrayList<ContentDTO>();
		ContentDTO dto = null;
		searchString = "%"+searchString.replaceAll(" ", "%")+"%";
		try {
			Connection con = ds.getConnection();
			PreparedStatement st = con.prepareStatement(
					"SELECT DOCKEY, CUSTOMER, " + "TYPE, FILENAME, REFERENCE, DOCDATA, SUMMARY, AUTHOR, "
							+ "PROCESS, SUBPROCESS, REUSABLEPER, REUSABLEREASON, "
							+ "ESTIMATION, LINK, ADDDATE, ADDBY, EDITDATE, EDITBY " + "FROM CONTENT "
							+ "WHERE TYPE = ? AND PROCESS = ? AND DOCDATA LIKE ? ");			
			st.setString(1, type);
			st.setString(2, process);
			st.setString(3, searchString);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				dto = new ContentDTO();
				dto.setDockey(rs.getString(1));
				dto.setCustomer(rs.getString(2));
				dto.setType(rs.getString(3));
				dto.setFilename(rs.getString(4));
				dto.setReference(rs.getString(5));
				dto.setDocdata(rs.getString(6));
				dto.setSummary(rs.getString(7));
				dto.setAuthor(rs.getString(8));
				dto.setProcess(rs.getString(9));
				dto.setSubprocess(rs.getString(10));
				dto.setReusableper(rs.getString(11));
				dto.setReusablereason(rs.getString(12));
				dto.setEstimation(rs.getString(13));
				dto.setLink(rs.getString(14));
				dto.setAdddate(rs.getDate(15).toString());
				dto.setAddby(rs.getString(16));
				dto.setEditdate(rs.getDate(17).toString());
				dto.setEditby(rs.getString(18));

				dtoList.add(dto);
			}
			contentListDto.setContent(dtoList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			message = e.getMessage();
		}
		return contentListDto;
	}
	
	public static ContentsDTO populateContent(DataSource ds, String key) {
		ContentsDTO contentListDto = new ContentsDTO();
		ArrayList<ContentDTO> dtoList = new ArrayList<ContentDTO>();
		ContentDTO dto = null;
		try {
			Connection con = ds.getConnection();
			PreparedStatement st = con.prepareStatement(
					"SELECT DOCKEY, CUSTOMER, " + "TYPE, FILENAME, REFERENCE, DOCDATA, SUMMARY, AUTHOR, "
							+ "PROCESS, SUBPROCESS, REUSABLEPER, REUSABLEREASON, "
							+ "ESTIMATION, LINK, ADDDATE, ADDBY, EDITDATE, EDITBY " 
							+ "FROM CONTENT "
							+ "WHERE DOCKEY = ? ");
			st.setString(1, key);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				dto = new ContentDTO();
				dto.setDockey(rs.getString(1));
				dto.setCustomer(rs.getString(2));
				dto.setType(rs.getString(3));
				dto.setFilename(rs.getString(4));
				dto.setReference(rs.getString(5));
				dto.setDocdata(rs.getString(6));
				dto.setSummary(rs.getString(7));
				dto.setAuthor(rs.getString(8));
				dto.setProcess(rs.getString(9));
				dto.setSubprocess(rs.getString(10));
				dto.setReusableper(rs.getString(11));
				dto.setReusablereason(rs.getString(12));
				dto.setEstimation(rs.getString(13));
				dto.setLink(rs.getString(14));
				dto.setAdddate(rs.getDate(15).toString());
				dto.setAddby(rs.getString(16));
				dto.setEditdate(rs.getDate(17).toString());
				dto.setEditby(rs.getString(18));

				dtoList.add(dto);
			}
			contentListDto.setContent(dtoList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			message = e.getMessage();
		}
		return contentListDto;
	}
}