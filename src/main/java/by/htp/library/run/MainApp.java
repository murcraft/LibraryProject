package by.htp.library.run;

import by.htp.library.console.menu.ConsoleMenu;
import by.htp.library.console.menu.LogicMenu;

public class MainApp {

	public static void main(String[] args) {

		ConsoleMenu cMenu = new ConsoleMenu();
		LogicMenu logicMenu = new LogicMenu();
		
		while(true) {
			cMenu.showStartMenu();
			logicMenu.authorization();
		}
	}
}
