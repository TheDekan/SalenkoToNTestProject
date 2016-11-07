package com.salenkod.dao;

import java.util.List;

import com.salenkod.model.ClientTable;

public interface ClientDao {

void update(ClientTable row);
	
	ClientTable insert(ClientTable row);

	List<ClientTable> findAll();

	List<ClientTable> sortedFind(int startPosition, int maxResults,
			String sortFields, String sortDirections);

	void delete(Long id);

	ClientTable findById(Long id);

	Long getCount();
	
}
