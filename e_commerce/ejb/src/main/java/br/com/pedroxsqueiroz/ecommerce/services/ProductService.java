package br.com.pedroxsqueiroz.ecommerce.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.pedroxsqueiroz.ecommerce.daos.Dao;
import br.com.pedroxsqueiroz.ecommerce.daos.ProductDao;
import br.com.pedroxsqueiroz.ecommerce.models.ProductModel;

@Stateless
public class ProductService extends AbstractService<ProductModel>{

	@Inject
	private ProductDao dao;
	
	@Override
	protected Dao<ProductModel> getDao() {
		return this.dao;
	}

}
