package com.salenkod.pagination;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.salenkod.model.ClientTable;
import com.salenkod.model.PlaceTable;
import com.salenkod.pojo.Visit;

@XmlRootElement
public class VisitPaginator extends Pagin implements Serializable{
	
	@XmlElement
	private List<PlaceTable> placeList;
	
	@XmlElement
	private List<ClientTable> clientList;
	
	@XmlElement
	private List<Visit> visitList;
			
	public List<ClientTable> getClientList() {
		return clientList;
	}

	public void setClientList(List<ClientTable> clientList) {
		this.clientList = clientList;
	}
	
	public List<PlaceTable> getPlaceList() {
		return placeList;
	}

	public void setPlaceList(List<PlaceTable> placeList) {
		this.placeList = placeList;
	}
		
	public List<Visit> getVisitList() {
		return visitList;
	}

	public void setVisitList(List<Visit> visitList) {
		this.visitList = visitList;
	}
	
}
