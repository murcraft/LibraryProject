package by.htp.kyzniatsova.controller.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import by.htp.kyzniatsova.console.ReaderVisits;
import by.htp.kyzniatsova.controller.MainController;
import by.htp.kyzniatsova.dao.ReaderDao;
import by.htp.kyzniatsova.dao.impl.ReaderDaoImpl;
import by.htp.kyzniatsova.domain.entity.Reader;

public class ReaderControllerImpl  implements MainController {
	
	ReaderDao readerDao;
	private ReaderVisits readerConsole;
	
	public ReaderControllerImpl() {
		readerDao = new ReaderDaoImpl();
		readerConsole = new ReaderVisits();
	}
	
	@Override
	public boolean insert() {
		System.out.println("Enter the first name of reader");
		String name = readerConsole.readLine();
		System.out.println("Enter the surname of reader");
		String surname = readerConsole.readLine();
		System.out.println("Enter the phone number of reader in format 80XXXXXXXXX");
		String phone = readerConsole.readLine();
		System.out.println("Enter rhe date of registration in format YYYY-MM-DD");
		try {
			Calendar dateReg = Calendar.getInstance();
			String date = readerConsole.readLine();
			SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
			dateReg.setTime(dateFormat.parse(date));
			Reader reader = new Reader();
			reader.setName(name);
			reader.setSurname(surname);
			reader.setPhoneNumber(phone);
			reader.setDateOfRegistr(dateReg);
		if (readerDao.insert(reader)) {
			System.out.println("Reader was added successfully");
			return true;
		} else {
			System.out.println("error, try again");
			return false;
		}
	} catch (ParseException e1) {
		e1.printStackTrace();
	}
	return false;
	}

	@Override
	public boolean update() {
		showAll();
		System.out.println("Choose num_ticket, who's you want to change");
		int num_ticket = readerConsole.readNumber();
		System.out.println("Enter the first name of reader");
		String name = readerConsole.readLine();
		System.out.println("Enter the surname of reader");
		String surname = readerConsole.readLine();
		System.out.println("Enter the phone number of reader in format 80XXXXXXXXX");
		String phone = readerConsole.readLine();
		System.out.println("Enter rhe date of registration in format YYYY-MM-DD");
		try {
			Calendar dateReg = Calendar.getInstance();
			String date = readerConsole.readLine();
			SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
			dateReg.setTime(dateFormat.parse(date));
			Reader reader = new Reader();
			reader.setNum_ticket(num_ticket);
			reader.setName(name);
			reader.setSurname(surname);
			reader.setPhoneNumber(phone);
			if (readerDao.insert(reader)) {
				System.out.println("Reader was added successfully");
				return true;
			} else {
				System.out.println("Error, try again, please");
				return false;
			}
		} catch (ParseException e1) {

			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete() {
		showAll();
		System.out.println("Choose num_ticket, who's you want to change");
		int num_ticket = readerConsole.readNumber();
		Reader reader = new Reader();
		reader.setNum_ticket(num_ticket);		
		if (readerDao.delete(readerDao.read(num_ticket))) {
			System.out.println("Reader was added successfully");
			return true;
		} else {
			System.out.println("Error, try again, please");
			return false;
		}
	}

	@Override
	public void showAll() {
		for (Reader reader : readerDao.list()) {
			System.out.println(reader);
		}
	}

}
