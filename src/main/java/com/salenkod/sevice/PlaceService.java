package com.salenkod.sevice;

import java.util.List;

import com.salenkod.model.PlaceTable;
import com.salenkod.pojo.Place;

public interface PlaceService {

	void update(PlaceTable row);

	PlaceTable insert(PlaceTable row);
	
	List<PlaceTable> findAll();

	void delete(Long id);

	PlaceTable findById(Long id);
	
	List<Place> sortedFind(int startPosition, int maxResults,
			String sortFields, String sortDirections);

	Long getCount();
	
}
