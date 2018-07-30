package by.htp.kyzniatsova.dao;

import java.util.List;

import by.htp.kyzniatsova.entity.Reader;

public interface ReaderDao {
	
	Reader read(int id);
	List<Reader> list();
	boolean insert(Reader employer);
	boolean delete(Reader employer);
	boolean update(Reader employer);

}
