package by.htp.library.dao.impl;

import static by.htp.library.dao.util.MySqlPropertyManager.getLogin;
import static by.htp.library.dao.util.MySqlPropertyManager.getPass;
import static by.htp.library.dao.util.MySqlPropertyManager.getUrl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import by.htp.library.dao.BookDao;
import by.htp.library.dao.ReaderDao;
import by.htp.library.dao.RegistReadersDao;
import by.htp.library.domain.entity.Book;
import by.htp.library.domain.entity.Reader;
import by.htp.library.domain.entity.RegistReaders;

public class RegistReadersDaoImpl implements RegistReadersDao {

	private static final String SELECT_LIBCARD_BYID = "SELECT * FROM library.taking_book WHERE id_taking_book = ?";
	private static final String COUNT_LIBCARD_BYID_READER = "SELECT count(tb.id_reader) as count, b.Title\r\n" + 
			"FROM library.taking_book tb\r\n" + 
			"left join library.book b on b.id_book=tb.id_book\r\n" + 
			"WHERE \r\n" + 
			"tb.id_reader = '12346'\r\n" + 
			"and tb.return_date = '1111-11-11'\r\n" + 
			"group by tb.id_book;"; 
			
	private static final String SELECT_ALL_LIBCARDS = "SELECT * FROM library.taking_book";
	private static final String INSERT_LIBCARD_BYID = "INSERT INTO library.taking_book (date_start,date_end,id_book,id_employee)VALUES(?,?,?,?)";
	private static final String DELETE_LIBCARD_BYID = "DELETE FROM library.taking_book WHERE id_card = ?";
	private static final String UPDATE_LIBCARD_BYID = "UPDATE library.taking_book SET date_start = ? , date_end = ? , id_book = ? , id_employee = ? WHERE id_card = ?";

	@Override
	public RegistReaders read(int id) {
		RegistReaders registReaders = null;
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_LIBCARD_BYID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				registReaders = buildRegistReaders(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return registReaders;
	}

	@Override
	public boolean insert(RegistReaders registReaders) {
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(INSERT_LIBCARD_BYID);
			ps.setDate(1, new Date(registReaders.getDateStart().getTimeInMillis()));
			ps.setDate(2, new Date(registReaders.getDateEnd().getTimeInMillis()));
			ps.setInt(3, registReaders.getId_book());
			ps.setInt(4, registReaders.getId_reader());

			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(RegistReaders registReaders) {
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(UPDATE_LIBCARD_BYID);
			ps.setDate(1, new Date(registReaders.getDateStart().getTimeInMillis()));
			ps.setDate(2, new Date(registReaders.getDateEnd().getTimeInMillis()));
			ps.setInt(3, registReaders.getId_book());
			ps.setInt(4, registReaders.getId_reader());
			ps.setInt(5, registReaders.getId());
			System.out.println(ps);
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(RegistReaders registReaders) {
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(DELETE_LIBCARD_BYID);
			ps.setInt(1, registReaders.getId());
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<RegistReaders> getAll() {
		List<RegistReaders> registReaders = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_ALL_LIBCARDS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				registReaders.add(buildRegistReaders(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return registReaders;
	}

	private RegistReaders buildRegistReaders(ResultSet rs) throws SQLException {
		RegistReaders libCard = new RegistReaders();
		libCard.setId(rs.getInt("id_taking_book"));
		Calendar calendarStart = new GregorianCalendar();
		calendarStart.setTime(rs.getDate("date_start"));
		libCard.setDateStart(calendarStart);
		Calendar calendarEnd = new GregorianCalendar();
		calendarEnd.setTime(rs.getDate("date_end"));
		libCard.setDateEnd(calendarEnd);
		BookDao bookDao = new BookDaoImpl();
		Book book = bookDao.read(rs.getInt("id_book"));
		libCard.setId(book.getId());
		ReaderDao emplDao = new ReaderDaoImpl();
		Reader reader = new Reader();
		reader = emplDao.read(rs.getInt("id_reader"));
		libCard.setId_reader(reader.getId());
		return libCard;
	}
	
	public void readThreeBook(int id) {
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(COUNT_LIBCARD_BYID_READER);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;
			}
			if(count > 2) {
				System.out.println();
			} 
		} catch (SQLException e) {
			System.out.println("You have 3 books!!");
			e.printStackTrace();
		}

	}

}
