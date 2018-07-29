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
				"1 - ������ � ������� � ������ � ���������� " + "\n" + "2 - ������ � ������� �� ������� � ���������� "
						+ "\n" + "3 - ������ � ������� � ����������� � ����������  " + "\n"
						+ "4 - ������ � ������� � ������������ ������� � ���������� " + "\n"
						+ "5 - ����� � ����������� �������� ����� �� ����� " + "\n" + "6 - ����� �� ��������� \n");
	}

	public void bookMenu() {
		System.out.println("1 - ���������� ������ � ������ � ���������� " + "\n" + "2 - �������� ����� � ���������� "
				+ "\n" + "3 - �������� ������ � ����� � ����������  " + "\n" + "4 - ������� ����� �� ���������� " + "\n"
				+ "5 - ����� � ����������� �������� ����� �� ����� " + "\n" + "6 - ����� �� ��������� \n");
	}

	public void authorMenu() {
		System.out.println("1 - ���������� ������ �� ������� � ���������� " + "\n" + "2 - �������� ������ � ���������� "
				+ "\n" + "3 - �������� ������ �� ������ � ����������  " + "\n" + "4 - ������� ������ �� ���������� "
				+ "\n" + "5 - ����� �� ��������� \n");
	}

	public void employeeMenu() {
		System.out.println("1 - ���������� ������ � ����������� � ���������� " + "\n"
				+ "2 - �������� ���������� � ���������� " + "\n" + "3 - �������� ������ � ���������� � ����������  "
				+ "\n" + "4 - ������� ���������� �� ���������� " + "\n"
				+ "5 - ����� � ����������� �������� ����� �� ����� " + "\n" + "6 - ����� �� ��������� \n");
	}

	public void libraryCardMenu() {
		System.out.println("1 - ���������� ������ � ������������ ������� � ���������� " + "\n"
				+ "2 - ����� ����� �� ���������� " + "\n" + "3 - �������� ������ � ������ ������������� ������ " + "\n"
				+ "4 - ������� ������ �� ������������� ������ " + "\n"
				+ "5 - ����� � ����������� �������� ����� �� ����� " + "\n" + "6 - ����� �� ��������� \n");
	}


}
