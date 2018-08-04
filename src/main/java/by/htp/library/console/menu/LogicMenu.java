package by.htp.library.console.menu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.htp.library.console.ReadingConsole;
import by.htp.library.controller.impl.*;
import by.htp.library.dao.impl.BookDaoImpl;
import by.htp.library.dao.impl.LibrarianDaoImpl;
import by.htp.library.dao.impl.ReaderDaoImpl;
import by.htp.library.dao.impl.RegistReadersDaoImpl;
import by.htp.library.domain.entity.Book;
import by.htp.library.domain.entity.Librarian;
import by.htp.library.domain.entity.Reader;
import by.htp.library.domain.entity.RegistReaders;

public class LogicMenu {
	private ConsoleMenu consoleMenu = new ConsoleMenu();
	private ReaderDaoImpl readerDao;
	private BookDaoImpl bookDao;
	private ReadingConsole readConsole = new ReadingConsole();
	private RegistReadersDaoImpl registReadersDaoImpl = new RegistReadersDaoImpl();
	private LibrarianDaoImpl librarianDao;
	private BookControllerImpl bookController = new BookControllerImpl();
	private ReaderControllerImpl readerController = new ReaderControllerImpl();
	private RegistReadersControllerImpl registReadersContr = new RegistReadersControllerImpl();
	
	public void checkUsers() {
		sleep(1000);
		output: while (true) {
			switch (readConsole.readLine()) {
			case "1":
				readerDao = new ReaderDaoImpl();
				if (checkReaderAuthorization(readConsole))
				showFunctionReader(readConsole);
				break output;
			case "2":
				librarianDao = new LibrarianDaoImpl();
				menuLibrarian(readConsole);
				showLibrarianMenu(readConsole);
				break output;
			case "3":
				System.exit(0);
				break;
			default:
				System.out.println("Incorrect number, please, enter 1, 2, 3");
			}
		}
	}

	private Boolean checkReaderAuthorization(ReadingConsole readConsole) {
		while (true) {
			System.out.println("Enter login: ");
			String login = readConsole.readLine();
			System.out.println("Enter password: ");
			String pass = readConsole.readLine();

			if (readerDao.authorization(login, pass) != null) {
				Reader reader = readerDao.authorization(login, pass);
				System.out.println("It's nice to meet you! " + reader.getName() + " " + reader.getSurname() + "!");
				RegistReaders registReaders = registReadersDaoImpl.read(reader.getId());
				registReadersDaoImpl.readThreeBook(reader.getNum_ticket());
				return true;
			} else {
				System.out.println("You are entered incorrect login or password.");
				System.out.println("If you want to exit enter 3\nif not - enter any symbol:");
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
				bookController.showBook();
				sleep(1000);
				exitMenu(readConsole);
				break;
			}
			case "3":
				break output;
			default:
				System.out.println("You are entered wrong number");
				System.out.println("Please, enter 1, 2 or 3");
			}
		}
	}

	private void showLibrarianMenu(ReadingConsole readConsole) {
		label: while (true) {
			consoleMenu.librarianMenu();
			switch (readConsole.readLine()) {
			case "1":
				librarianDao.printLibrarians();
				exitMenu(readConsole);
				break;
			case "2":
				sleep(1000);
				readerController.insert();
				exitMenu(readConsole);
				break;	
			case "3":
				Boolean flag = bookController.insert();
				if(flag) {
					System.out.println("Success");
				} else {
					System.out.println("Error, repeat, please");
				}
				exitMenu(readConsole);
				break;
			case "4":
				if(registReadersContr.update()) {
					System.out.println("The book was took from library");
				} else {
					System.out.println("The book is be reading or reader has obligations");
				}
				exitMenu(readConsole);
				break;
			case "5":
				if(registReadersContr.insert()) {
					System.out.println("The book was inserted");
				} else {
					System.out.println("Check data and try again");
				};
				exitMenu(readConsole);
				break;
			case "6":
				break label;
			case "7":
				break label;
			default:
				System.out.println("Wrong number, please, try again: enter 0 - 7");
			}
		}

	}

	private Boolean menuLibrarian(ReadingConsole readConsole) {
		while (true) {
			System.out.println("Please, enter your login");
			String login = readConsole.readLine();
			System.out.println("Please, enter your password");
			String pass = readConsole.readLine();
			
			if (librarianDao.authorization(login, pass) != null) {
				Librarian librarian = librarianDao.authorization(login, pass);
				System.out.println("Welcome! " + librarian.getName() + " " + librarian.getSurname() + "!");
				
				return true;
			} else {
				System.out.println("You entered incorrect login or password.");
				System.out.println("Please, enter 0 to continue" + "\n" + "Enter other symbol to EXIT.");
				if (!readConsole.readLine().equals("0"))
					break;
			}
		}
		return false;

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
			System.out.println("Do you want continue(Y/N)?");
			String result = readConsole.readLine();
			if ("Y".equalsIgnoreCase(result)) {
				break;
			} else if ("N".equalsIgnoreCase(result)) {
				System.exit(0);
			} else
				System.out.println("Wrong number, please try again");
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

	private static int checkIdBook(ReadingConsole readConsole) {
		String value = null;
		while (true) {
			value = readConsole.readLine();
			Pattern pattern = Pattern.compile("\\D");
			Matcher matcher = pattern.matcher(value);
			if (matcher.find())
				System.out.println("You entered wrong id! Please, enter the number!");
			else {
				int id = Integer.parseInt(value.trim());
				return id;
			}

		}
	}

}
