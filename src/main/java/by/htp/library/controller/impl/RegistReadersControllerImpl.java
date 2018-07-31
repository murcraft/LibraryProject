package by.htp.library.controller.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import by.htp.library.console.ReadingConsole;
import by.htp.library.controller.MainController;
import by.htp.library.dao.DaoParam;
import by.htp.library.domain.entity.Book;
import by.htp.library.domain.entity.Reader;
import by.htp.library.domain.entity.RegistReaders;

public class RegistReadersControllerImpl implements MainController {
	
	private DaoParam<RegistReaders> registReaders;
	private DaoParam<Book> books;
	private DaoParam<Reader> readers;
	private ReadingConsole readingConsole;

//	public RegistReadersControllerImpl() {
//		registReaders = new LibraryCardDaoCreator().factoryMethod();
//		books = new BookDaoCreator().factoryMethod();
//		readers = new EmployeeDaoCreator().factoryMethod();
//		readingConsole = new ReadingConsole();
//	}
//
//	@Override
//	public void showAll() {
//		for (LibraryCard lCard : libraryCardDao.getAll()) {
//			System.out.println(lCard);
//		}
//	}
//
//	@Override
//	public boolean insert() {
//		RegistReaders libraryCard = new RegistReaders();
//		Calendar endDate = Calendar.getInstance();
//		endDate.add(Calendar.DAY_OF_MONTH, 30);
//		libraryCard.setDateStart(Calendar.getInstance());
//		libraryCard.setDateEnd(endDate);
//		for (Book b : books.getAll()) {
//			System.out.println(b);
//		}
//		System.out.println("Выберите книгу для редактирования: введите ее ID");
//		int id_book = readingConsole.readNumber();
//		for (Reader e : readers.getAll()) {
//			System.out.println(e);
//		}
//		System.out.println("Выберите сотрудника: введите его ID");
//		int id_employee = readingConsole.readNumber();
//		libraryCard.setId(books.read(id_book));
//		libraryCard.setEmployee(readers.get(id_employee));
//		SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy");
//		if (libraryCardDao.insert(libraryCard)) {
//			System.out.println(libraryCard.getEmployee().getName() + " взял(а) книгу " + libraryCard.getBook().getTitle()
//					+ " до " + formatForDateNow.format(libraryCard.getDateEnd().getTime()));
//			return true;
//		} else {
//			System.out.println("Произошла ошибка при добавлении, попрубуйте снова");
//			return false;
//		}
//
//	}
	

	@Override
	public boolean insert() {
		return false;

	}

	@Override
	public boolean update() {
		return false;

	}

	@Override
	public boolean delete() {
		return false;

	}

	@Override
	public void showAll() {
		
		
	}

	@Override
	public void showBook() {
		// TODO Auto-generated method stub
		
	}

}
