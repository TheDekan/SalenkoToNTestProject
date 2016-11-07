package com.salenkod.dao;

import java.util.List;

import com.salenkod.model.PlaceTable;
import com.salenkod.pojo.Place;

public interface PlaceDao {

	void update(PlaceTable row);
	
	PlaceTable insert(PlaceTable row);

	List<PlaceTable> findAll();

	List<Place> sortedFind(int startPosition, int maxResults,
			String sortFields, String sortDirections);

	void delete(Long id);

	PlaceTable findById(Long id);

	Long getCount();

}
