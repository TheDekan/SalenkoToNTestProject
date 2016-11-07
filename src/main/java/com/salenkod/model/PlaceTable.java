package com.salenkod.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "places")
public class PlaceTable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
    @Column(name = "client_id")
    private Long clientId;

	@Column(name = "address")
	private String address;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="client_id", referencedColumnName = "id", insertable = false, updatable = false)
	private ClientTable client;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		PlaceTable tb = (PlaceTable) o;

		return id.equals(tb.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
}
