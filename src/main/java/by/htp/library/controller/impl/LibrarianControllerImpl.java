package by.htp.library.controller.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import by.htp.library.console.ReadingConsole;
import by.htp.library.controller.MainController;
import by.htp.library.dao.LibrarianDao;
import by.htp.library.dao.impl.LibrarianDaoImpl;
import by.htp.library.domain.entity.Librarian;

public class LibrarianControllerImpl implements MainController {
	
	LibrarianDao librarianDao;
	private ReadingConsole readerConsole;
	
	public LibrarianControllerImpl() {
		librarianDao = new LibrarianDaoImpl();
		readerConsole = new ReadingConsole();
	}
	
	@Override
	public boolean insert() {
		System.out.println("Enter the first name of reader");
		String name = readerConsole.readLine();
		System.out.println("Enter the surname of reader");
		String surname = readerConsole.readLine();
		System.out.println("Enter the phone number of reader in format 80XXXXXXXXX");
		String phone = readerConsole.readLine();
		System.out.println("Enter the password for reader(not less 6 symbols and 1 digit)");
		String pass = readerConsole.readLine();
		System.out.println("Enter rhe date of registration in format YYYY-MM-DD");
		try {
			Calendar dateReg = Calendar.getInstance();
			String date = readerConsole.readLine();
			SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
			dateReg.setTime(dateFormat.parse(date));
			Librarian librarian = new Librarian();
			librarian.setName(name);
			librarian.setSurname(surname);
		if (librarianDao.insert(librarian)) {
			System.out.println("Reader was added successfully");
			return true;
		} else {
			System.out.println("error, try again");
			return false;
		}
	} catch (ParseException e1) {
		e1.printStackTrace();
	}
	return false;
	}

	@Override
	public boolean update() {
		showAll();
		System.out.println("Choose num_ticket, who's you want to change");
		String num_ticket = readerConsole.readLine();
		System.out.println("Enter the first name of reader");
		String name = readerConsole.readLine();
		System.out.println("Enter the surname of reader");
		String surname = readerConsole.readLine();
		System.out.println("Enter the phone number of reader in format 80XXXXXXXXX");
		String phone = readerConsole.readLine();
		System.out.println("Enter rhe date of registration in format YYYY-MM-DD");
		try {
			Calendar dateReg = Calendar.getInstance();
			String date = readerConsole.readLine();
			SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
			dateReg.setTime(dateFormat.parse(date));
			Librarian librarian = new Librarian();
			librarian.setName(name);
			librarian.setSurname(surname);
			if (librarianDao.insert(librarian)) {
				System.out.println("Reader was updated successfully");
				return true;
			} else {
				System.out.println("Error, try again, please");
				return false;
			}
		} catch (ParseException e1) {

			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete() {
		showAll();
		System.out.println("Choose id, who's you want to change");
		int id = readerConsole.readNumber();
		Librarian librarian = new Librarian();
//		librarian.setId(id);		
		if (librarianDao.delete(librarianDao.read(id))) {
			System.out.println("Reader was added successfully");
			return true;
		} else {
			System.out.println("Error, try again, please");
			return false;
		}
	}

	@Override
	public void showAll() {
		for (Librarian librarian : librarianDao.list()) {
			System.out.println(librarian);
		}
	}

	@Override
	public void showBook() {
		
		
	}

}
