package by.htp.library.dao.impl;

import static by.htp.library.dao.util.MySqlPropertyManager.getLogin;
import static by.htp.library.dao.util.MySqlPropertyManager.getPass;
import static by.htp.library.dao.util.MySqlPropertyManager.getUrl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.htp.library.dao.BookDao;
import by.htp.library.dao.ReaderDao;
import by.htp.library.dao.RegistReadersDao;
import by.htp.library.dao.ReportDao;
import by.htp.library.domain.entity.Book;
import by.htp.library.domain.entity.Reader;
import by.htp.library.domain.entity.RegistReaders;

public class ReportDaoImpl implements ReportDao {
	
	private static final String SELECT_EMPLOYEE_READ_BOOK_BYMONTH = "SELECT EMPLOYEE.*, COUNT(LIBRARY_CARD.ID_EMPLOYEE) AS READED_BOOKS FROM LIBRARY_CARD JOIN EMPLOYEE ON LIBRARY_CARD.ID_EMPLOYEE = EMPLOYEE.ID_EMPLOYEE WHERE LIBRARY_CARD.isReturned > 0 AND LIBRARY_CARD.id_employee IN 		\r\n" + 
			"		(\r\n" + 
			"			SELECT distinct employee.id_employee FROM employee join library_card\r\n" + 
			"			On employee.id_employee=library_card.id_employee\r\n" + 
			"			WHERE \r\n" + 
			"			(date_end  	BETWEEN ? AND ?)\r\n" + 
			"			and isReturned = 1\r\n" + 
			"		)\r\n" + 
			"		GROUP BY EMPLOYEE.id_employee HAVING READED_BOOKS BETWEEN ? AND ?";
	private static final String SELECT_BOOKS_READ = "SELECT BOOK.ID_BOOK, BOOK.TITLE, BOOK.QUANTITY, BOOK.ID_AUTHOR, COUNT(*) AS BOOK_TAKEDAWAY FROM LIBRARY_CARD\r\n" + 
			"	JOIN BOOK ON LIBRARY_CARD.ID_BOOK = BOOK.ID_BOOK WHERE LIBRARY_CARD.ID_BOOK IN \r\n" + 
			"	(\r\n" + 
			"		SELECT DISTINCT LIBRARY_CARD.ID_BOOK FROM LIBRARY_CARD WHERE ISRETURNED > 0\r\n" + 
			"	)\r\n" + 
			"	GROUP BY LIBRARY_CARD.ID_BOOK ORDER BY BOOK_TAKEDAWAY DESC, BOOK.TITLE ASC";

	@Override
	public Map<Reader, Integer> booksForMonth(int min, int max) {
		Calendar currentDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.DAY_OF_MONTH, -30);
		SimpleDateFormat formatCurrentDate = new SimpleDateFormat("yyyy-MM-dd");
		Map<Reader, Integer> employeeMap = new HashMap<>();
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_EMPLOYEE_READ_BOOK_BYMONTH);
			ps.setString(1, formatCurrentDate.format(endDate.getTime()));
			ps.setString(2, formatCurrentDate.format(currentDate.getTime()));
			ps.setInt(3, min);
			ps.setInt(4, max);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				employeeMap.put(buildreader(rs), rs.getInt("READED_BOOKS"));
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return employeeMap;

	}


	private Reader buildreader(ResultSet rs) throws SQLException {
		ReaderDao readerDao = new ReaderDaoImpl();
		Reader reader = new Reader();
		reader = readerDao.getReader(rs);
		return reader;
	}

	@Override
	public Map<Book,Integer> booksPop() {
		BookDao bookDao = new BookDaoImpl();
		Map<Book,Integer> books = new HashMap<>();
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_BOOKS_READ);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				books.put(((BookDaoImpl)bookDao).buildBook(rs), rs.getInt("BOOK_TAKEDAWAY"));
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return books;

	}

	@Override
	public  Map<Reader, Map<Book, RegistReaders>> readersObligation(){
		RegistReadersDao registReaders = new RegistReadersDaoImpl();
		ReaderDao readerDao = new ReaderDaoImpl();
		return registReaders.readReadersOver(readerDao.list());
	}
	



}
