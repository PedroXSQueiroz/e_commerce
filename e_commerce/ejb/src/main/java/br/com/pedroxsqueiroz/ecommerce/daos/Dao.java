package br.com.pedroxsqueiroz.ecommerce.daos;

import java.util.List;

public interface Dao<T> {
	
	List<T> list();
	
	T get(Integer id);
	
	T create(T resource);
	
	T update(Integer id, T resource);
	
	void delete(Integer id);
}
