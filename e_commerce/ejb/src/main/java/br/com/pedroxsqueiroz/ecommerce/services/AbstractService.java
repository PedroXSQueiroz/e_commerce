package br.com.pedroxsqueiroz.ecommerce.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.pedroxsqueiroz.ecommerce.daos.AbstractEntityManagerDao;
import br.com.pedroxsqueiroz.ecommerce.daos.Dao;
import br.com.pedroxsqueiroz.ecommerce.models.AbstractModel;


public abstract class AbstractService<T extends AbstractModel> implements Service<T> {
	
	protected abstract Dao<T> getDao();
	
	public AbstractService()
	{
	}
	
	@Override
	public T get(Integer id) {
		return this.getDao().get(id);
	}

	@Override
	public List get() {
		return this.getDao().list();
	}

	@Override
	public T save(T resource) {
		return this.getDao().create(resource);
	}

	@Override
	public T update(T resource, Integer id) {
		return this.getDao().update(id, resource);
	}

	@Override
	public void delete(Integer id) {
		this.getDao().delete(id);
	}

}
