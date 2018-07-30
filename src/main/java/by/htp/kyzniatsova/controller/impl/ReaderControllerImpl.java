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
	private ReaderVisits reader;
	
	public ReaderControllerImpl() {
		readerDao = new ReaderDaoImpl();
		reader = new ReaderVisits();
	}
	
	@Override
	public boolean insert() {
		System.out.println("Enter the first name of reader");
		String name = reader.readLine();
		System.out.println("Enter the surname of reader");
		String surname = reader.readLine();
		System.out.println("Enter the phone number of reader");
		String phone = reader.readLine();
		System.out.println("Enter rhe date of registration in format YYYY-MM-DD");
		try {
			Calendar dateReg = Calendar.getInstance();
			String date = reader.readLine();
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

		return false;
	}

	@Override
	public boolean delete() {

		return false;
	}

	@Override
	public void showAll() {
		
	}

}
