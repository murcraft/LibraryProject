package by.htp.kyzniatsova.run;

import by.htp.kyzniatsova.console.BookMenu;
import by.htp.kyzniatsova.console.ConsoleMenu;
import by.htp.kyzniatsova.console.ParentMenu;
import by.htp.kyzniatsova.console.ReaderConsole;
import by.htp.kyzniatsova.console.ReaderMenu;

public class MainApp {

	public static void main(String[] args) {

		ConsoleMenu cMenu = new ConsoleMenu();
		ReaderConsole readConsole = new ReaderConsole();
		ParentMenu pMenu = new ParentMenu();
		BookMenu bookMenu = new BookMenu();

		do {
			pMenu.menu();			
			int choice = readConsole.readNumber();
			switch (choice) {

			case 1:
				bookMenu.menu();
				cMenu.toContinue();
				break;
			case 3:
				ReaderMenu eMenu = new ReaderMenu();
				eMenu.menu();
				pMenu.continueMenu();
				break;
			case 4:
				pMenu.continueMenu();
				break;
			case 5:
				pMenu.continueMenu();
			case 6:
				pMenu.exitMenu();
			}
			
		} while (true);
	}
}
