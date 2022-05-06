package com.seshu.cs.dto;

import java.util.ArrayList;
import java.util.List;



public class CustomersDTO {

	private List<CustomerDTO> customers;

	public List<CustomerDTO> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerDTO> customers) {
		this.customers = customers;
	}

	public CustomersDTO() {
		customers = new ArrayList<CustomerDTO>();
	}
}