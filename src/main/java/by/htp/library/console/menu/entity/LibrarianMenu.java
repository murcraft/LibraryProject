package by.htp.library.console.menu.entity;

import by.htp.library.console.ReadingConsole;
import by.htp.library.console.menu.ParentMenu;
import by.htp.library.controller.factory.ReaderController;

public class LibrarianMenu  extends ParentMenu {
	
	public static final String LIBRARIAN_MENU;
	static {
		LIBRARIAN_MENU = "1 - to view all readers in library " + "\n"
				+ "2 - to add a reader in library " + "\n" 
				+ "3 - to add a new book in library "+ "\n"
				+ "4 - to get a book back " + "\n" 
				+ "5 - to delete reader in library " + "\n"
				+ "6 - to make reports " + "\n"
				+ "7 - Exit \n";
	}
	
	public LibrarianMenu() {
		controller = new ReaderController().factoryMethod(); 
	}
	
	@Override
	public void menu() {
		ReadingConsole read = new ReadingConsole();
		
		while (true) {
			System.out.println(LIBRARIAN_MENU);
			int choice = read.readNumber();
			switch (choice) {
			case 1:
				controller.showAll();
				break;
			case 2:
				controller.insert();
				break;
			case 3:
				controller.update();
				break;
			case 4:
				controller.delete();
				break;
			case 5:
				controller.delete();
				break;
			case 6:
				controller.delete();
				break;
			case 7:
				super.exitMenu();
				break;
			}
			break;
		}
		
	}

}
