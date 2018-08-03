package by.htp.library.controller.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import by.htp.library.console.ReadingConsole;
import by.htp.library.controller.MainController;
import by.htp.library.dao.BookDao;
import by.htp.library.dao.DaoParam;
import by.htp.library.dao.ReaderDao;
import by.htp.library.dao.RegistReadersDao;
import by.htp.library.dao.impl.BookDaoImpl;
import by.htp.library.dao.impl.ReaderDaoImpl;
import by.htp.library.dao.impl.RegistReadersDaoImpl;
import by.htp.library.domain.entity.Book;
import by.htp.library.domain.entity.Reader;
import by.htp.library.domain.entity.RegistReaders;

public class RegistReadersControllerImpl implements MainController {
	
//	private DaoParam<RegistReaders> registReaders;
	private BookDao bookDao;
	private ReaderDao readersDao;
	private RegistReadersDao registReadersDao;
	private ReadingConsole readingConsole;

	public RegistReadersControllerImpl() {
//		registReaders = new LibraryCardDaoCreator().factoryMethod();
		bookDao = new BookDaoImpl();
		readersDao = new ReaderDaoImpl();
		registReadersDao = new RegistReadersDaoImpl();
		readingConsole = new ReadingConsole();
	}

	@Override
	public void showAll() {
		for (RegistReaders rr : registReadersDao.getAll()) {
			System.out.println(rr);
		}
	}

	@Override
	public boolean insert() {
		RegistReaders regReaders = new RegistReaders();
		Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.DAY_OF_MONTH, 30);
		regReaders.setDateStart(Calendar.getInstance());
		regReaders.setDateEnd(endDate);
		for (Book b : bookDao.list()) {
			System.out.println(b);
		}
		System.out.println("Choose the book and enter its id number:");
		int id_book = readingConsole.readNumber();
		for (Reader e : readersDao.list()) {
			System.out.println(e);
		}
		System.out.println("Choose the reader and enter his id number:");
		int id_reader = readingConsole.readNumber();
		regReaders.setBook(bookDao.read(id_book));
		regReaders.setReader(readersDao.read(id_reader));
		SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
		if (registReadersDao.insert(regReaders)) {
			System.out.println(regReaders.getReader().getName() + " took the book " + regReaders.getBook().getTitle()
					+ " on " + formatForDateNow.format(regReaders.getDateEnd().getTime()));
			return true;
		} else {
			System.out.println("Somthing wrong, try again");
			return false;
		}

	}
	


	@Override
	public void showBook() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

}
