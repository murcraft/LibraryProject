package by.htp.kyzniatsova.console.menu;

import by.htp.kyzniatsova.console.ParentMenu;
import by.htp.kyzniatsova.console.ReaderConsole;
import by.htp.kyzniatsova.controller.BookController;

public class BookMenu  extends ParentMenu {
	public static final String BOOK_MENU;
	
	static {
		BOOK_MENU = "1 - to look at all books in library " + "\n" + "2 - to add book "
		+ "\n" + "3 - to change data about book " + "\n" + "4 - to delete book " + "\n"
		+ "\n" + "5 - Exit \n";
	}
	
	public BookMenu(){
		controller = new BookController().factoryMethod(); 
	}
	
	@Override
	public void menu() {
		ReaderConsole read = new ReaderConsole();
		
		while (true) {
			System.out.println(BOOK_MENU);
			int choice2 = read.readNumber();
			switch (choice2) {
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
				super.exitMenu();
				break;

			}
			break;
		}

	
	}


}
