package com.salenkod.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.salenkod.model.PlaceTable;
import com.salenkod.pojo.Place;

@Repository("PlaceTableDao")
public class PlaceDaoImpl extends AbstractDao implements PlaceDao {

	/*@Override
	public void upsert(PlaceTable row) {
		getSession().saveOrUpdate(row);
	}*/

	@SuppressWarnings("unchecked")
	@Override
	public List<PlaceTable> findAll() {
		Criteria criteria = getSession().createCriteria(PlaceTable.class);
		return (List<PlaceTable>) criteria.list();
	}

	@Override
	public void delete(Long id) {
		Query query = getSession().createQuery("delete from PlaceTable where id = :id");
		query.setLong("id", id);
		query.executeUpdate();
	}

	@Override
	public PlaceTable findById(Long id) {
		return (PlaceTable) findByField(PlaceTable.class, "id", id);
	}

	@Override
	public Long getCount() {
		Query query = getSession().createQuery("select count(*) from PlaceTable");
		return (Long) query.uniqueResult();
	}

	@Override
	public List<Place> sortedFind(int startPosition, int maxResults,
			String sortFields, String sortDirections) {
		Query query = 
				getSession().createQuery("SELECT p.id, p.address, c.name, c.phone FROM PlaceTable p"
						+ " inner join p.client c"
						+ " ORDER BY p." + sortFields + " "+ sortDirections);
		query.setFirstResult(startPosition);
		query.setMaxResults(maxResults);
		return query.list();
	}

	@Override
	public void update(PlaceTable row) {
		super.update(row);
	}
	
	

	@Override
	public PlaceTable insert(PlaceTable row) {
		return (PlaceTable) super.insert(row);
	}

}

