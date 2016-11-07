package com.salenkod.sevice;

import java.util.List;

import com.salenkod.model.VisitTable;
import com.salenkod.pojo.Visit;

public interface VisitService {

	void update(VisitTable row);

	VisitTable insert(VisitTable row);
	
	List<VisitTable> findAll();

	void delete(Long id);

	VisitTable findById(Long id);
	
	List<Visit> sortedFind(int startPosition, int maxResults,
			String sortFields, String sortDirections);

	Long getCount();

}
