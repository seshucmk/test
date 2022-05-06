package com.seshu.cs.dto;

import java.util.ArrayList;
import java.util.List;

public class SVNEntriesListDTO {

	private List<SVNHistoryDTO> svnHistory;

	public List<SVNHistoryDTO> getSvnHistory() {
		return svnHistory;
	}

	public void setSvnHistory(List<SVNHistoryDTO> svnHistory) {
		this.svnHistory = svnHistory;
	}
	
	public SVNEntriesListDTO() {
		svnHistory = new ArrayList<SVNHistoryDTO>();
	}
}