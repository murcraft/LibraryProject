package by.htp.library.controller.impl;

<<<<<<< HEAD:src/main/java/by/htp/library/controller/impl/BookControllerImpl.java
import by.htp.library.console.ReadingConsole;
import by.htp.library.controller.MainController;
import by.htp.library.dao.BookDao;
import by.htp.library.dao.impl.BookDaoImpl;
import by.htp.library.domain.entity.Book;
=======
import by.htp.kyzniatsova.console.ReaderConsole;
import by.htp.kyzniatsova.controller.MainController;
import by.htp.kyzniatsova.dao.BookDao;
import by.htp.kyzniatsova.dao.impl.BookDaoImpl;
import by.htp.kyzniatsova.entity.Book;
>>>>>>> c840a0d152b7a62892bd87c6d795e159adc4330f:src/main/java/by/htp/kyzniatsova/controller/impl/BookControllerImpl.java

public class BookControllerImpl implements MainController {
	private BookDao bookDao;
	private Book book;
	private ReadingConsole reader;

	public BookControllerImpl() {
		bookDao = new BookDaoImpl();
		book = new Book();
		reader = new ReadingConsole();
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

	@Override
	public void showBook() {
		System.out.println("Enter the id number of book");
		int id = reader.readNumber();
		bookDao.read(id);
	}

}
