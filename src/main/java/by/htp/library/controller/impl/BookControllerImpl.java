package by.htp.library.controller.impl;

import java.util.List;

import by.htp.library.console.ReadingConsole;
import by.htp.library.controller.MainController;
import by.htp.library.dao.BookDao;
import by.htp.library.dao.impl.BookDaoImpl;
import by.htp.library.domain.entity.Book;

public class BookControllerImpl implements MainController {
	private BookDao bookDao;
	private Book book;
	private ReadingConsole readerConsole;

	public BookControllerImpl() {
		bookDao = new BookDaoImpl();
		book = new Book();
		readerConsole = new ReadingConsole();
	}

	@Override
	public boolean insert() {
		System.out.println("Enter the book's title:");
		String title = readerConsole.readLine();
		book.setTitle(title);
		System.out.println("Enter number of pages in the book:");
		int pages = readerConsole.readNumber();
		book.setPages(pages);
		System.out.println("Enter the production year in format YYYY:");
		String year = readerConsole.readLine();
		if(year.length() != 4) {
			return false;
		}
		book.setProductYear(year);
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
		int id_book = readerConsole.readNumber();
		System.out.println("Enter the name of book");
		String title = readerConsole.readLine();
		System.out.println("Enter the year of book");
		String productYear = readerConsole.readLine();
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
		int id = readerConsole.readNumber();
		book.setId(id);
		if(bookDao.update(book)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void showAll() {
		List<Book> books = bookDao.list();
		for(Book book : books) {
			System.out.println(book);
		}
	}

	@Override
	public void showBook() {
		System.out.println("Enter the id number of book");
		int id = readerConsole.readNumber();
		Book book = new Book();
		book = bookDao.read(id);
		System.out.println(book);
	}

}
