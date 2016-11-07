package com.salenkod.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salenkod.dao.PlaceDao;
import com.salenkod.model.PlaceTable;
import com.salenkod.pojo.Place;

@Service("placeService")
@Transactional
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	private PlaceDao dao;

	@Override
	public void update(PlaceTable row) {
		dao.update(row);
	}
	
	@Override
	public PlaceTable insert(PlaceTable row) {
		return dao.insert(row);
	}

	@Override
	public List<PlaceTable> findAll() {
		return dao.findAll();
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	@Override
	public PlaceTable findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public Long getCount() {
		return dao.getCount();
	}

	@Override
	public List<Place> sortedFind(int startPosition, int maxResults,
			String sortFields, String sortDirections) {
		return dao.sortedFind(startPosition, maxResults, sortFields, sortDirections);
	}
	
}
