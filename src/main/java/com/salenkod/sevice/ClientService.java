package com.salenkod.sevice;

import java.util.List;

import com.salenkod.model.ClientTable;

public interface ClientService {

	void update(ClientTable row);

	ClientTable insert(ClientTable row);
	
	List<ClientTable> findAll();

	void delete(Long id);

	ClientTable findById(Long id);
	
	List<ClientTable> sortedFind(int startPosition, int maxResults,
			String sortFields, String sortDirections);

	Long getCount();
	
}
