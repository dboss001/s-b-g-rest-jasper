package net.db.spring.restreports.domain.report;

import java.util.List;

import net.db.spring.restreports.domain.data.Address;

public class UserReportData {
	
	private String name;
	private String username;
	
	private List<Address> addresses;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
}
