package by.htp.kyzniatsova.dao;

import java.util.List;

import by.htp.kyzniatsova.entity.Book;

public interface BookDao {
	
	Book read(int id);
	List<Book> list();
	boolean insert(Book book);
	boolean delete(Book book);
	boolean update(Book book);
	Book readAll();
}
