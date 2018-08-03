package by.htp.library.console.menu.entity;

import by.htp.library.console.ReadingConsole;
import by.htp.library.console.menu.ParentMenu;
import by.htp.library.controller.factory.ReaderController;

public class ReaderMenu extends ParentMenu {
	
	public ReaderMenu() {
		controller = new ReaderController().factoryMethod(); 
	}
	
	@Override
	public void menu() {
		ReadingConsole read = new ReadingConsole();
		
		while (true) {
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
				super.exitMenu();
				break;
			}
			break;
		}
		
	}

}
