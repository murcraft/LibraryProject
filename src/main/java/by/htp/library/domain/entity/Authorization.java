package by.htp.library.domain.entity;

import by.htp.library.console.ReadingConsole;
import by.htp.library.controller.IAuthorizationController;
import by.htp.library.dao.impl.ReaderDaoImpl;

public class Authorization {
	IAuthorizationController aic;
	Librarian librarian = new Librarian();
	Reader reader = new Reader();
	ReaderDaoImpl readerD = new ReaderDaoImpl();
	ReadingConsole read = new ReadingConsole();
	
	public boolean isAuthorizedLibrarian() {
		if(librarian.getLogin().equals(authorizLogin()) && librarian.getPassword().equals(authorizPassword())){
			return true;
		}
		return false;	
	}
	
	
	public String authorizLogin() {
		System.out.println("Enter login");
		String login = read.readLine();
		return login;
	}
	
	public String authorizPassword() {
		System.out.println("Enter password:");
		String password = read.readLine();
		return password;
	}
	
	public boolean checkTrueLogin() {
		String login = authorizPassword();
		if(login == null) {
			return false;
		}
		return true;
	}
	
	public boolean checkTruePass() {
		String pass = authorizPassword();
		if(pass == null || pass.length() < 7) {
			return false;
		}
        long countDig = pass.codePoints().filter(Character::isDigit).count();
        long countLet = pass.codePoints().filter(Character::isLetter).count();
        if((countLet < 6) || (countDig < 1)) {
        	System.out.println("Wrong password. Enter password with no less 1 digit and 6 letters, please");
        	return false;
        }
		return true;
	}
	
	
	
	public boolean isAuthorizedReader() {
		if(checkTruePass() && checkTrueLogin()) {
			return true;
		}
		return false;
	}

}
