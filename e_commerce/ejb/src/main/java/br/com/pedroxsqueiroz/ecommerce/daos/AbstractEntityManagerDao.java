package br.com.pedroxsqueiroz.ecommerce.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.pedroxsqueiroz.ecommerce.models.AbstractModel;

@TransactionManagement(TransactionManagementType.CONTAINER)
public abstract class AbstractEntityManagerDao<T extends AbstractModel> implements Dao<T>{
	
	private Class<T> resourceClass; 
	
	public AbstractEntityManagerDao(Class<T> resourceClass)
	{
		this.resourceClass = resourceClass;
	}
	
	@PersistenceContext(unitName = "ecommerce")
	private EntityManager entityManager;

	@Override
	public List<T> list() {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = criteriaBuilder.createQuery(this.resourceClass);
		CriteriaQuery<T> selectAll = query.select(query.from(this.resourceClass));
		
		TypedQuery<T> allQuery = this.entityManager.createQuery(selectAll);
		
		List<T> resultList = allQuery.getResultList();
		
		return resultList;
	}

	@Override
	public T get(Integer id) {
		return this.entityManager.find(this.resourceClass, id);
	}

	@Override
	public T create(T resource) {
		
		if(resource.getId() == null) 
		{
			this.entityManager.persist(resource);			
		}else 
		{
			this.entityManager.merge(resource);
		}
		
		
		return resource;
	}

	@Override
	public T update(Integer id, T resource) {
		
		resource.setId(id);
		return this.entityManager.merge(resource);
	}

	@Override
	public void delete(Integer id) {
		
		T resource = this.get(id);
		this.entityManager.remove(resource);
		
	}
	
}
