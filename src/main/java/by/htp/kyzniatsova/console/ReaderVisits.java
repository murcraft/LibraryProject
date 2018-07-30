package by.htp.kyzniatsova.console;

import java.util.Scanner;

public class ReaderVisits {
	Scanner scanner;

	public ReaderVisits() {
		this.scanner = new Scanner(System.in, "utf-8");
	}
	

	public String readString() {
		return scanner.next();
	}

	public int readNumber() {
		System.out.println("Enter the number:");		
		int number = 0;
		if(scanner.hasNext()) {
			number = Integer.parseInt(scanner.next());
		}
		scanner.nextLine();
		return number;
	}
	
	public String readLine() {
		String string = new String();
		scanner.hasNext();
		string += scanner.nextLine();
		return string;
	}

}
