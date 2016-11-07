package com.salenkod.dao;

import java.util.List;

import com.salenkod.model.VisitTable;
import com.salenkod.pojo.Visit;

public interface VisitDao {

	void update(VisitTable row);
	
	VisitTable insert(VisitTable row);

	List<VisitTable> findAll();

	List<Visit> sortedFind(int startPosition, int maxResults,
			String sortFields, String sortDirections);

	void delete(Long id);

	VisitTable findById(Long id);

	Long getCount();

}
