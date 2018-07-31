package by.htp.library.console.menu;

import java.util.Scanner;

public class ConsoleMenu {

	public void toContinue() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Do you want to quit? Y(yes)/N(No)" + "\n");
		String str = scanner.next();
		switch (str) {
		case "N":
			toExit();
			break;
		case "n":
			toExit();
			break;
		}
	}

	public void toExit() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Do you want to quit? Y(yes)/N(No)" + "\n");
		String str = scanner.next();
		switch (str) {
		case "Y":
			System.out.println("Thanks for your choise. Come to us else!");
			System.exit(0);
			break;
		case "y":
			System.out.println("Thanks for your choise. Come to us else!");
			System.exit(0);
			break;
		}
	}
	
	public void showStartMenu() {
		System.out.println(
				"Choose your role: 1 - " + "Reader " + "\n"
				+ "2 - Librarian  " + "\n" + "3 - Exit \n");
	}
	
	public void librarianMenu() {
		System.out.println("1 - to view all readers in library " + "\n"
					+ "2 - to add a reader in library " + "\n" 
					+ "3 - to add a new book in library "+ "\n"
					+ "4 - to get a book back to library" + "\n" 
					+ "5 - to give a book to reader " + "\n"
					+ "6 - to make reports " + "\n" + "7 - Exit \n");
	}

	public void readerMenu() {
		System.out.println("1 - to view all catalog of books in library " + "\n"
							+ "2 - to view information about book " + "\n"
							+ "3 - Exit \n");
	}

	public void bookMenu() {
		System.out.println("1 - to look at reader's tickets " + "\n"
				+ "2 - to get a book " + "\n" + "3 - to change data about ticket " + "\n"
				+ "4 - to delete reader's ticket " + "\n"
				+ "5 - to make reports " + "\n" + "6 - Exit \n");
	}

}
