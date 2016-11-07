package com.salenkod.pagination;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.salenkod.model.ClientTable;

@XmlRootElement
public class ClientPaginator extends Pagin implements Serializable {

	@XmlElement
	private List<ClientTable> clientList;
			
	public List<ClientTable> getClientList() {
		return clientList;
	}

	public void setClientList(List<ClientTable> clientList) {
		this.clientList = clientList;
	}
	
}
