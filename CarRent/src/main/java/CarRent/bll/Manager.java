package CarRent.bll;

import java.util.List;

import CarRent.bo.Voiture;

public interface Manager<T> {
	
	// CRUD	
	T getOne(long id);
	List<T> getAll();
	void add(T entity);
	void edit(T entity);
	void delete(T entity);
	
}
