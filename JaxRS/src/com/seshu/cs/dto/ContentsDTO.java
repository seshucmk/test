package com.seshu.cs.dto;

import java.util.ArrayList;
import java.util.List;



public class ContentsDTO {

	private List<ContentDTO> content;

	public List<ContentDTO> getContent() {
		return content;
	}

	public void setContent(List<ContentDTO> content) {
		this.content = content;
	}

	public ContentsDTO() {
		content = new ArrayList<ContentDTO>();
	}
}