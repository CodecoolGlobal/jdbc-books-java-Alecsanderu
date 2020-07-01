package com.codecool.books.model;

import java.util.List;

public interface Dao<T> {

	void add(T t);


	void update(T t);

	T get(int id);

	List<T> getAll();
}
