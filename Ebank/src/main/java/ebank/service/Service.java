package ebank.service;

public interface Service<T> {
	T getById(long id);
	void add(T t);
	void update(T t);
	void remove(T t);	
}
