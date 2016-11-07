package com.salenkod.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salenkod.dao.ClientDao;
import com.salenkod.model.ClientTable;

@Service("clientService")
@Transactional
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientDao dao;

	@Override
	public void update(ClientTable row) {
		dao.update(row);
	}
	
	@Override
	public ClientTable insert(ClientTable row) {
		return dao.insert(row);
	}

	@Override
	public List<ClientTable> findAll() {
		return dao.findAll();
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	@Override
	public ClientTable findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public Long getCount() {
		return dao.getCount();
	}

	@Override
	public List<ClientTable> sortedFind(int startPosition, int maxResults,
			String sortFields, String sortDirections) {
		return dao.sortedFind(startPosition, maxResults, sortFields, sortDirections);
	}
	
}
