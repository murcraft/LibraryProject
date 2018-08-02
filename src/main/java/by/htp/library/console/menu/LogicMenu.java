package by.htp.library.console.menu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.htp.library.console.ReadingConsole;
import by.htp.library.dao.impl.BookDaoImpl;
import by.htp.library.dao.impl.LibrarianDaoImpl;
import by.htp.library.dao.impl.ReaderDaoImpl;
import by.htp.library.dao.impl.RegistReadersDaoImpl;
import by.htp.library.domain.entity.Book;
import by.htp.library.domain.entity.Reader;
import by.htp.library.domain.entity.RegistReaders;

public class LogicMenu {
	private  ConsoleMenu consoleMenu = new ConsoleMenu();
	private ReaderDaoImpl readerDao;
	private BookDaoImpl bookDao;
	ReadingConsole readConsole = new ReadingConsole();
	RegistReadersDaoImpl registReadersDaoImpl = new RegistReadersDaoImpl();
	private  LibrarianDaoImpl librarianDao;
	Reader reader = new Reader();
	
	
	public void authorization() {
		sleep(1000);
		output: while (true) {
			switch (readConsole.readLine()) {
			case "1":
				readerDao = new ReaderDaoImpl();
				if (menuReader(readConsole))
				showFunctionReader(readConsole);
				break output;
			case "2":
				librarianDao = new LibrarianDaoImpl();
				menuLibrarian(readConsole);
//				showFunctionLibrarian(readConsole);
				break output;
			case "3":
				break output;
			default:
				System.out.println("Incorrect number, please enter 1, 2, 3");
			}
		}

	}

	private Boolean menuReader(ReadingConsole readConsole) {
		while (true) {
			System.out.println("Enter login: ");
			String login = readConsole.readLine();
			System.out.println("Enter password: ");
			String pass = readConsole.readLine();

			if (readerDao.authorization1(login, pass) != null) {
				Reader reader = readerDao.authorization1(login, pass);
				System.out.println("It's nice to meet you! " + reader.getName() + " " + reader.getSurname() + "!");
				System.out.println(reader);
				RegistReaders registReaders = registReadersDaoImpl.read(reader.getId());
				System.out.println(registReaders);
				
				registReadersDaoImpl.read(reader.getId());
				if(registReaders != null) {
					registReadersDaoImpl.readThreeBook(reader.getId());
				}
				return true;
			} else {
				System.out.println("You are entered incorrect login or password");
				System.out.println("Do you want to exit? enter 3");
				System.out.println("No? Enter any symbol");
				if (readConsole.readLine().equals("0"))
					break;
			}
		}
		return false;

	}

	private void showFunctionReader(ReadingConsole readConsole) {

		output: while (true) {
			consoleMenu.readerMenu();
			sleep(1000);
			switch (readConsole.readLine()) {
			case "1":
				bookDao = new BookDaoImpl();
				bookDao.readAll();
				exitMenu(readConsole);
				break;
			case "2": {
				System.out.println("Enter id book");
				int id = checkIdBoock(readConsole);
				sleep(1000);
				bookDao = new BookDaoImpl();
				Book book = new Book();
				book = bookDao.read(id);
				checkNullBook(book);
				exitMenu(readConsole);
				break;
			}
			case "3":
				break output;
			default:
				System.out.println("You are entered incorrect number");
				System.out.println("Number must be [0-2]");
			}
		}
	}
	
	private void checkNullBook(Book book) {
		if(book != null) {
			System.out.println(book);
		} else {
			System.out.println("incorrect id number");
		}
	}


	private void showLibrarianMenu(ReadingConsole readConsole) {

		label: while (true) {
			consoleMenu.librarianMenu();
			switch (readConsole.readLine()) {
			case "0":
				break label;
			case "1":
				librarianDao.list();
				exitMenu(readConsole);
				break;
			case "2":
				System.out.println("Enter id book");
				int id = checkIdBoock(readConsole);
				sleep(1000);
				readerDao.read(id);
				exitMenu(readConsole);
				break;
			case "3":
				break;
			case "4":
				break;
			case "5":
				readerDao.list();
				exitMenu(readConsole);
				break;

			default:
				System.out.println("You entered incorrect number, please be attentive repeat Enter");
				System.out.println("Number must be [0-5]");
			}
		}

	}

	private Boolean menuLibrarian(ReadingConsole readConsole) {
		while (true) {
			System.out.println("Please, Enter your login");
			String login = readConsole.readLine();
			System.out.println("Please, Enter your password");
			String pass = readConsole.readLine();
			if (librarianDao.authorization(login, pass)) {
				System.out.println(
						"Welcome to Library! ");// + librarian.NAME.getValue() + " " + librarian.SURNAME.getValue());
//				readerDao.checkReader(login, pass);
				return true;
			} else {
				System.out.println("You entered incorrect login or password, please be attentive repeat Enter");
				System.out.println("If you wish exit, enter [0]");
				System.out.println("If you would like continue, enter other symbol");
				if (readConsole.readLine().equals("0"))
					break;
			}
		}
		return false;

	}

	private static void menuReport() {

	}

	private static Reader createReader(ReadingConsole readConsole) {
		Reader reader = new Reader();
		System.out.println("Enter login:");
		reader.setNum_ticket(readConsole.readLine());
		System.out.println("Enter password");
		reader.setPassword(validationPassword(readConsole));
		System.out.println("Enter name");
		reader.setName(readConsole.readLine());
		System.out.println("Enter surname");
		reader.setSurname(readConsole.readLine());
		System.out.println("Enter numberPhone");
		reader.setPhoneNumber(readConsole.readLine());
		return reader;
	}

	private static void exitMenu(ReadingConsole readConsole) {
		while (true) {
			System.out.println("Are you wish to continue(Y/N)?");
			String result = readConsole.readLine();
			if ("Y".equalsIgnoreCase(result)) {
				break;
			} else if ("N".equalsIgnoreCase(result)) {
				System.exit(0);
			} else
				System.out.println("You entered incorrect date, please be attentive repeat Enter");
		}
	}

	private static String validationPassword(ReadingConsole readConsole) {
		while (true) {
			String s = readConsole.readLine();
			Pattern p = Pattern.compile("[0-9]");
			Matcher m = p.matcher(s);
			if (s.length() > 6 && m.find())
				return s;
			System.out.println("The password must contain at least 6 characters and one digit!");
		}

	}

	private static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static int checkIdBoock(ReadingConsole readConsole) {
		String s = new String();
		while (true) {
			s = readConsole.readLine();
			Pattern p = Pattern.compile("\\D");
			Matcher m = p.matcher(s);
			if (m.find())
				System.out.println("You entered wrong id Book!,it must be number!");
			else {
				int id = Integer.parseInt(s.trim());
				return id;
			}

		}
	}

}