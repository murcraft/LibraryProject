package by.htp.library.console.menu;

import java.util.Scanner;

import by.htp.library.controller.MainController;

public class ParentMenu {
	
	protected MainController controller;
	
	public void continueMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nDo you want to continue? Y(Yes)/N(No)" + "\n");
		String str = sc.nextLine();
		switch (str) {
		case "N":
		case "n":
			exitMenu();
			break;
		}
	}

	public void exitMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Do you want to exit? Y(Yes)/N(No)" + "\n");
		String str = sc.nextLine();
		switch (str) {
		case "Y":
		case "y":
			System.out.println("Thanks for choise, come to us!");
			System.exit(0);
			break;
		}
	}

	public void menu() {
		System.out.println("Choose your role, please:\n" + 
						"\"1\" - Reader\n"  + "\"2\" - Librarian\n" + "\"3\" - Exit\n");
	}


}
