package com.salenkod.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salenkod.dao.VisitDao;
import com.salenkod.model.VisitTable;
import com.salenkod.pojo.Visit;

@Service("visitService")
@Transactional
public class VisitServiceImpl implements VisitService {

	@Autowired
	private VisitDao dao;

	@Override
	public void update(VisitTable row) {
		dao.update(row);
	}
	
	@Override
	public VisitTable insert(VisitTable row) {
		return dao.insert(row);
	}

	@Override
	public List<VisitTable> findAll() {
		return dao.findAll();
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	@Override
	public VisitTable findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public Long getCount() {
		return dao.getCount();
	}

	@Override
	public List<Visit> sortedFind(int startPosition, int maxResults,
			String sortFields, String sortDirections) {
		return dao.sortedFind(startPosition, maxResults, sortFields, sortDirections);
	}
	
}
