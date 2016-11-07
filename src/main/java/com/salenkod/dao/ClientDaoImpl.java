package com.salenkod.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.salenkod.model.ClientTable;

@Repository("ClientTableDao")
public class ClientDaoImpl extends AbstractDao implements ClientDao{

	/*@Override
	public void upsert(ClientTable row) {
		getSession().saveOrUpdate(row);
	}*/

	@SuppressWarnings("unchecked")
	@Override
	public List<ClientTable> findAll() {
		Criteria criteria = getSession().createCriteria(ClientTable.class);
		return (List<ClientTable>) criteria.list();
	}

	@Override
	public void delete(Long id) {
		Query query = getSession().createQuery("delete from ClientTable where id = :id");
		query.setLong("id", id);
		query.executeUpdate();
	}

	@Override
	public ClientTable findById(Long id) {
		return (ClientTable) findByField(ClientTable.class, "id", id);
	}

	@Override
	public Long getCount() {
		Query query = getSession().createQuery("select count(*) from ClientTable");
		return (Long) query.uniqueResult();
	}

	@Override
	public List<ClientTable> sortedFind(int startPosition, int maxResults,
			String sortFields, String sortDirections) {
		Query query = 
				getSession().createQuery("SELECT p FROM ClientTable p ORDER BY p." + sortFields + " "+ sortDirections);
		query.setFirstResult(startPosition);
		query.setMaxResults(maxResults);
		return query.list();
	}

	@Override
	public void update(ClientTable row) {
		super.update(row);
	}
	
	

	@Override
	public ClientTable insert(ClientTable row) {
		return (ClientTable) super.insert(row);
	}
	
}
