package by.htp.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import by.htp.library.domain.entity.Reader;

public interface ReaderDao extends DaoParam<Reader> {
	
	Reader read(int id);
	
	List<Reader> list();
	
	boolean insert(Reader reader);
	
	boolean delete(Reader reader);
	
	boolean update(Reader reader);
	

	Reader authorization(String login, String password);
	
	Reader getReader(ResultSet rs) throws SQLException;

}
