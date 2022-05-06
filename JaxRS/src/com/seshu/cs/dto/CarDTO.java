package com.seshu.cs.dto;

import java.util.ArrayList;
import java.util.List;



public class CarDTO {

	private List<ComponentDTO> components;

	public List<ComponentDTO> getComponents() {
		return components;
	}

	public void setComponents(List<ComponentDTO> components) {
		this.components = components;
	}

	public CarDTO() {
		components = new ArrayList<ComponentDTO>();
	}
}