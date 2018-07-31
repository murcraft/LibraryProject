package by.htp.library.dao;

import java.util.List;

import by.htp.library.domain.entity.Reader;

public interface ReaderDao extends DaoParam<Reader> {
	
	Reader read(int id);
	List<Reader> list();
	boolean insert(Reader employer);
	boolean delete(Reader employer);
	boolean update(Reader employer);

	boolean authorization(String login, String password);

}
