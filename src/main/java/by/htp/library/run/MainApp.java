package by.htp.library.run;

import by.htp.library.console.ReadingConsole;
import by.htp.library.console.menu.ConsoleMenu;
import by.htp.library.console.menu.ParentMenu;
import by.htp.library.console.menu.entity.ReaderMenu;
import by.htp.library.dao.impl.ReaderDaoImpl;
import by.htp.library.domain.entity.Authorization;
import by.htp.library.domain.entity.Librarian;
import by.htp.library.domain.entity.Reader;

public class MainApp {

	public static void main(String[] args) {

		ConsoleMenu cMenu = new ConsoleMenu();
		ReadingConsole readConsole = new ReadingConsole();
		ParentMenu pMenu = new ParentMenu();
		ReaderMenu readerMenu = new ReaderMenu();
		Authorization authorization = new Authorization();
		Reader reader = new Reader();
		ReaderDaoImpl readerD = new ReaderDaoImpl();
		Librarian librarian = new Librarian();

		
		while(true) {
			cMenu.showStartMenu();
			int choise =  readConsole.readNumber();
			switch(choise) {
			case 1:
				authorization.authorizLogin();
				authorization.authorizPassword();
				break;
			case 2:
				librarian.getLogin();
				librarian.getPassword();
				break;
			case 3: 
				pMenu.exitMenu();
				default:
				cMenu.showStartMenu();
				break;
					
			}
			 if(authorization.isAuthorizedReader()) {
				do {
					cMenu.readerMenu();;			
					int choice = readConsole.readNumber();
					switch (choice) {
	
					case 1:
						readerMenu = new ReaderMenu();
						readerMenu.menu();
						pMenu.continueMenu();
						break;
					case 2:
						ParentMenu eMenu = new ReaderMenu();
						eMenu.menu();
						pMenu.continueMenu();
	
						break;
					case 3:
						pMenu.exitMenu();
					}
				} while (true);
			} else {
				System.out.println("The user is not ixisted");
				pMenu.continueMenu();
			}
		}
	}
}
