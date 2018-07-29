package by.htp.kyzniatsova.logic;

import java.util.Scanner;

public class MenuConsole {

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
	
	public void showStartingMenu() {
		System.out.println(
				"1 - работа с данными о книгах в библиотеке " + "\n" + "2 - работа с данными об авторах в библиотеке "
						+ "\n" + "3 - работа с данными о сотрудниках в библиотеке  " + "\n"
						+ "4 - работа с данными о читательских билетах в библиотеке " + "\n"
						+ "5 - отчет о сотрудниках читавших книги за месяц " + "\n" + "6 - Выход из программы \n");
	}

	public void bookMenu() {
		System.out.println("1 - посмотреть данные о книгах в библиотеке " + "\n" + "2 - добавить книгу в библиотеку "
				+ "\n" + "3 - изменить данные о книге в библиотеку  " + "\n" + "4 - удалить книгу из библиотеки " + "\n"
				+ "5 - отчет о сотрудниках читавших книги за месяц " + "\n" + "6 - Выход из программы \n");
	}

	public void authorMenu() {
		System.out.println("1 - посмотреть данные об авторах в библиотеке " + "\n" + "2 - добавить автора в библиотеку "
				+ "\n" + "3 - изменить данные об авторе в библиотеку  " + "\n" + "4 - удалить автора из библиотеки "
				+ "\n" + "5 - Выход из программы \n");
	}

	public void employeeMenu() {
		System.out.println("1 - посмотреть данные о сотрудниках в библиотеке " + "\n"
				+ "2 - добавить сотрудника в библиотеку " + "\n" + "3 - изменить данные о сотруднике в библиотеку  "
				+ "\n" + "4 - удалить сотрудника из библиотеки " + "\n"
				+ "5 - отчет о сотрудниках читавших книги за месяц " + "\n" + "6 - Выход из программы \n");
	}

	public void libraryCardMenu() {
		System.out.println("1 - посмотреть данные о читательских билетах в библиотеке " + "\n"
				+ "2 - взять книгу из библиотеки " + "\n" + "3 - изменить данные в записи читательского билета " + "\n"
				+ "4 - удалить запись из читательского билета " + "\n"
				+ "5 - отчет о сотрудниках читавших книги за месяц " + "\n" + "6 - Выход из программы \n");
	}


}
