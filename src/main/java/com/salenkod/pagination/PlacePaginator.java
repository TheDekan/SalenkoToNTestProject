package com.salenkod.pagination;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.salenkod.model.ClientTable;
import com.salenkod.pojo.Place;

@XmlRootElement
public class PlacePaginator extends Pagin implements Serializable{

	@XmlElement
	private List<Place> placeList;
	
	@XmlElement
	private List<ClientTable> clientList;
			
	public List<ClientTable> getClientList() {
		return clientList;
	}

	public void setClientList(List<ClientTable> clientList) {
		this.clientList = clientList;
	}
	
	public List<Place> getPlaceList() {
		return placeList;
	}

	public void setPlaceList(List<Place> placeList) {
		this.placeList = placeList;
	}
	
}
