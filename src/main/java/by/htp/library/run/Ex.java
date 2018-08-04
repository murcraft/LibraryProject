package by.htp.library.run;

import by.htp.library.dao.RegistReadersDao;
import by.htp.library.dao.impl.RegistReadersDaoImpl;

public class Ex {

	public static void main(String[] args) {

		RegistReadersDao reg = new RegistReadersDaoImpl();
		boolean b = reg.isBookNotInLibrary(6);
		System.out.println(b);

	}

}
