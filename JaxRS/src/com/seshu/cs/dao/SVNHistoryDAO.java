package com.seshu.cs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.sql.DataSource;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import com.seshu.cs.dto.SVNEntriesListDTO;
import com.seshu.cs.dto.SVNHistoryDTO;

public class SVNHistoryDAO {

	public static String message;
	public static SVNEntriesListDTO populateSVNData(DataSource ds, String customer, 
				String reference, String component, String svnUser, String svnPswd) {
		SVNEntriesListDTO dto = new SVNEntriesListDTO();
		String link = "";
		try {
			Connection con = ds.getConnection();
			PreparedStatement st=con.prepareStatement("SELECT LINK FROM CAR WHERE CUSTOMER = ? AND REFERENCE = ? AND COMPONENT = ?");
			st.setString(1,  customer);
			st.setString(2,  reference);
			st.setString(3,  component);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				link = rs.getString(1);
			}			
			dto = getSVNInfo(link, svnUser, svnPswd);
		}catch(Exception e) {
			message = e.getMessage();
			//System.out.println(e.getMessage());
		}
		return dto;
	}
	
	@SuppressWarnings("unchecked")
	public static SVNEntriesListDTO getSVNInfo(String link, String svnUser, String svnPswd) {
    	    	
    	ArrayList<SVNHistoryDTO> rowEntries = new ArrayList<SVNHistoryDTO>();
        setupLibrary();
        SVNRepository repository = null;      
        Collection<SVNLogEntry> logEntries = null;
        try {
            repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(link));
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(svnUser, svnPswd);
            repository.setAuthenticationManager(authManager);
            long endRevision = repository.getLatestRevision();
            logEntries = repository.log(new String[] {""}, null, 0, endRevision, true, true);
                        
            SVNHistoryDTO dto = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            for (Iterator<SVNLogEntry> entries = logEntries.iterator(); entries.hasNext();) {            	
                SVNLogEntry logEntry = entries.next();
                dto = new SVNHistoryDTO();
                dto.setRevision(logEntry.getRevision()+"");
                dto.setAuthor(logEntry.getAuthor());
                dto.setEditdate(sdf.format(logEntry.getDate()).toString());
                dto.setComments(logEntry.getMessage());             
                rowEntries.add(dto);
            }
        } catch (SVNException svne) {            
            //System.out.println(svne.getMessage());
        	message = svne.getMessage();
        }
        SVNEntriesListDTO listDto = new SVNEntriesListDTO();
        listDto.setSvnHistory(rowEntries);
        return listDto;
    }
    
    private static void setupLibrary() {
        DAVRepositoryFactory.setup();
        SVNRepositoryFactoryImpl.setup();
        FSRepositoryFactory.setup();
    }
}