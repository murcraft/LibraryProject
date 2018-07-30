package by.htp.kyzniatsova.controller.impl;

import by.htp.kyzniatsova.console.ReaderVisits;
import by.htp.kyzniatsova.controller.MainController;
import by.htp.kyzniatsova.dao.BookDao;
import by.htp.kyzniatsova.dao.impl.BookDaoImpl;
import by.htp.kyzniatsova.domain.entity.Book;

public class BookControllerImpl implements MainController {
	private BookDao bookDao;
	private Book book;
	private ReaderVisits reader;

	public BookControllerImpl() {
		bookDao = new BookDaoImpl();
		book = new Book();
		reader = new ReaderVisits();
	}

	@Override
	public boolean insert() {
		System.out.println("Enter the book's name");
		String title = reader.readLine();		
		book.setTitle(title);
		if(bookDao.insert(book) == true) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean update() {
		showAll();
		System.out.println("Choose the book for edition");
		int id_book = reader.readNumber();
		System.out.println("Enter the name of book");
		String title = reader.readLine();
		System.out.println("Enter the year of book");
		String productYear = reader.readLine();
		book.setId(id_book);
		book.setTitle(title);
		book.setProductYear(productYear);
		if(bookDao.update(book)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete() {
		showAll();
		System.out.println("Choose the book for deleting and enter its id number");
		int id = reader.readNumber();
		book.setId(id);
		if(bookDao.update(book)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void showAll() {
		for(Book book : bookDao.list()) {
			System.out.println(book);
		}
	}

}
