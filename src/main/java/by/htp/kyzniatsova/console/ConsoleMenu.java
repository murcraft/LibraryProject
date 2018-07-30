package by.htp.kyzniatsova.console;

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
				"1 - to work with books in library " + "\n" 
				+ "\n" + "2 - to work with readers in library  " + "\n"
				+ "3 - to work with books in library " + "\n"
				+ "4 - to show reports about readers " + "\n" + "6 - Exit \n");
	}

	public void bookMenu() {
		System.out.println("1 - to look at all books in library " + "\n" + "2 - to add book "
				+ "\n" + "3 - to change data about book " + "\n" + "4 - to delete book " + "\n"
				+ "5 - report with readers for month " + "\n" + "6 - Exit \n");
	}

	public void readerMenu() {
		System.out.println("1 - to look at all readers in library " + "\n"
				+ "2 - to add reader " + "\n" + "3 - to change data about reader  "
				+ "\n" + "4 - to delete reader in library " + "\n"
				+ "5 - report with readers for month  " + "\n" + "6 - Exit \n");
	}

	public void librarianMenu() {
		System.out.println("1 - to look at reader's tickets " + "\n"
				+ "2 - to get a book " + "\n" + "3 - to change data about ticket " + "\n"
				+ "4 - to delete reader's ticket " + "\n"
				+ "5 - report with readers for month " + "\n" + "6 - Exit \n");
	}


}
