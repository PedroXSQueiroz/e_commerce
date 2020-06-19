package br.com.pedroxsqueiroz.ecommerce.daos;

import javax.ejb.Stateless;

import br.com.pedroxsqueiroz.ecommerce.models.ProductModel;

@Stateless
public class ProductDao extends AbstractEntityManagerDao<ProductModel> {

	public ProductDao() {
		super(ProductModel.class);
	}

}
