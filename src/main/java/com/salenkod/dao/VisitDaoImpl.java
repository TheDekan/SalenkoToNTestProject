package com.salenkod.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.salenkod.model.VisitTable;
import com.salenkod.pojo.Visit;

@Repository("VisitTableDao")
public class VisitDaoImpl extends AbstractDao implements VisitDao {

	/*@Override
	public void upsert(VisitTable row) {
		getSession().saveOrUpdate(row);
	}*/

	@SuppressWarnings("unchecked")
	@Override
	public List<VisitTable> findAll() {
		Criteria criteria = getSession().createCriteria(VisitTable.class);
		return (List<VisitTable>) criteria.list();
	}

	@Override
	public void delete(Long id) {
		Query query = getSession().createQuery("delete from VisitTable where id = :id");
		query.setLong("id", id);
		query.executeUpdate();
	}

	@Override
	public VisitTable findById(Long id) {
		return (VisitTable) findByField(VisitTable.class, "id", id);
	}

	@Override
	public Long getCount() {
		Query query = getSession().createQuery("select count(*) from VisitTable");
		return (Long) query.uniqueResult();
	}

	@Override
	public List<Visit> sortedFind(int startPosition, int maxResults,
			String sortFields, String sortDirections) {
		Query query = 
				getSession().createQuery("SELECT p.id, p.visitTime, d.phone, d.name, c.address FROM VisitTable p"
						+ " inner join p.places.client d"
						+ " inner join p.places c"
						+ " ORDER BY p." + sortFields + " "+ sortDirections);
		query.setFirstResult(startPosition);
		query.setMaxResults(maxResults);
		return query.list();
	}

	@Override
	public void update(VisitTable row) {
		super.update(row);
	}
	
	

	@Override
	public VisitTable insert(VisitTable row) {
		return (VisitTable) super.insert(row);
	}

}
