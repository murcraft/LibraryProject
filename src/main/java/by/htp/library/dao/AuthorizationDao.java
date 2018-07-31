package by.htp.library.dao;

import java.util.List;

import by.htp.library.domain.entity.Book;

public interface AuthorizationDao {
	
	Book read(int id);

	boolean insert(Book book);

	boolean delete(Book book);

	boolean update(Book book);
	

	List<Book> list();
	

}
