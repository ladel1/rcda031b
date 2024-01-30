package ebank.service;

import java.util.List;

public interface Service<T> {
	T getById(long id);
	List<T> getAll();
	void add(T t);
	void update(T t);
	void remove(T t);	
}
