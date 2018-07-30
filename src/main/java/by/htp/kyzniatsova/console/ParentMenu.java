package by.htp.kyzniatsova.console;

import java.util.Scanner;

import by.htp.kyzniatsova.controller.MainController;

public class ParentMenu {
	
	public static final String WINDOW = "1 - " + "books " + "\n"
			+ "2 - readers  " + "\n"
			+ "3 - reader's tickets " + "\n"
			+ "4 - reports " + "\n"
			+ "5 - Exit \n";
	
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
		System.out.println(WINDOW);
	}


}
