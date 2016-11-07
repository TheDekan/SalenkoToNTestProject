package com.salenkod.pojo;

import java.sql.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Visit {

	@XmlElement(name="id")
	private Long id;	
	@XmlElement(name="visitTime")
	private Date visitTime;
	@XmlElement(name="address")
	private String address;
	@XmlElement(name="phone")
	private String phone;
	@XmlElement(name="name")
	private String name;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
