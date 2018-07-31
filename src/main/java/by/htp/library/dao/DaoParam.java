package by.htp.library.dao;

import java.util.List;

public interface DaoParam<T> {

	T read(int id);
	boolean insert(T t);
	boolean update(T t);
	boolean delete(T t);
	List<T> list();
}
