package by.htp.kyzniatsova.dao;

import java.util.List;

import by.htp.kyzniatsova.domain.entity.Book;

public interface BookDao {
	
	Book read(int id);
	List<Book> list();
	int insert(Book book);
	void delete(Book book);
	void update(Book book);
	Book readAll();
	List<Book> getBooks();

}
