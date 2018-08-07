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
import by.htp.library.domain.entity.Book;
import by.htp.library.domain.entity.Reader;
import by.htp.library.domain.entity.RegistReaders;

public class RegistReadersDaoImpl implements RegistReadersDao {

	private static final String SELECT_LIBCARD_BYID = "SELECT * FROM library.taking_book WHERE id_taking_book = ?";
	private static final String COUNT_LIBCARD_BYID_READER = "SELECT count(tb.id_reader) as count, tb.id_taking_book, r.id_reader, r.Num_ticket, r.Name, r.Surname, r.Phone, r.Reg_date, r.Password, b.id_book, b.Title, b.Pages, b.Prod_year, b.Subject, tb.take_date\r\n" + 
			"FROM library.taking_book tb \r\n" + 
			"left join library.readers r on r.Num_ticket = tb.id_reader \r\n" + 
			"left join library.book b on b.id_book = tb.id_book \r\n" + 
			"WHERE tb.id_reader = ? and tb.return_date = '1111-11-11' and (current_date() - take_date) > 30 group by tb.id_book;"; 
	private static final String SELECT_LIBCARD_BYID_READER = "SELECT id_taking_book, id_reader, id_book, Take_date, Return_date FROM library.taking_book WHERE id_reader = ?";		
	private static final String SELECT_BYID_BOOK = "SELECT id_taking_book, id_reader, id_book, Take_date, Return_date FROM library.taking_book WHERE id_book = ?";
	private static final String SELECT_BYID_BOOK_CHECK = "SELECT id_reader, id_book, Take_date, Return_date FROM library.taking_book WHERE id_book = ? and Return_date = '1111-11-11'";
	private static final String SELECT_BYID_READER_BOOK_CHECK = "SELECT id_reader, id_book, Take_date, Return_date FROM library.taking_book WHERE id_book = ? and id_reader = ?";
	private static final String SELECT_ALL_LIBCARDS = "SELECT * FROM library.taking_book";
	private static final String INSERT_LIBCARD_BYID = "INSERT INTO library.taking_book (date_start,date_end,id_book,id_reader)VALUES(?,?,?,?)";
	private static final String DELETE_LIBCARD_BYID = "DELETE FROM library.taking_book WHERE id_card = ?";
	private static final String UPDATE_LIBCARD_BYID = "UPDATE library.taking_book SET date_start = ? , date_end = ? , id_book = ? , id_reader = ? WHERE id_card = ?";

	@Override
	public RegistReaders read(int id_reader) {
		RegistReaders registReaders = null;
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_LIBCARD_BYID_READER);
			ps.setInt(1, id_reader);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				registReaders = buildRegistReaders(rs);
			}
		} catch (SQLException e) {
			System.out.println("Error database connection");
			e.printStackTrace();
		}
		return registReaders;
	}
	
	public RegistReaders readInBook(int id_book) {
		RegistReaders registReaders = null;
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_LIBCARD_BYID);
			ps.setInt(1, id_book);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				registReaders = buildRegistReaders(rs);
			}
		} catch (SQLException e) {
			System.out.println("Error database connection");
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
			ps.setInt(3, registReaders.getBook().getId());
			ps.setInt(4, registReaders.getReader().getId());

			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Error database connection");
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
			ps.setInt(3, registReaders.getBook().getId());
			ps.setInt(4, registReaders.getReader().getId());
			ps.setInt(5, registReaders.getId());
			System.out.println(ps);
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Error database connection");
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
			System.out.println("Error database connection");
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
			System.out.println("Error database connection");
			e.printStackTrace();
		}
		return registReaders;
	}

	private RegistReaders buildRegistReaders(ResultSet resSet) throws SQLException {
		RegistReaders libCard = new RegistReaders();
		libCard.setId(resSet.getInt("id_taking_book"));
		
		ReaderDao readerDao = new ReaderDaoImpl();
		Reader reader = readerDao.read(resSet.getInt("id_reader"));
		libCard.setReader(reader);
		
		BookDao bookDao = new BookDaoImpl();
		Book book = bookDao.read(resSet.getInt("id_book"));
		libCard.setBook(book);
		
		Calendar calendarStart = new GregorianCalendar();
		calendarStart.setTime(resSet.getDate("Take_date"));
		libCard.setDateStart(calendarStart);
		Calendar calendarEnd = new GregorianCalendar();
		calendarEnd.setTime(resSet.getDate("Return_date"));
		libCard.setDateEnd(calendarEnd);
		
		return libCard;
	}
	
	public int readThreeBook(String numTicket) {
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(COUNT_LIBCARD_BYID_READER);
			ps.setString(1, numTicket);
			ResultSet rs = ps.executeQuery();
			List<Book> books = new ArrayList<Book>();

			int count = 0;
			while (rs.next()) {
				Book book = new Book();
				book.setTitle(rs.getString("b.Title"));
				book.setProductYear(rs.getString("b.Prod_year"));
				books.add(book);
				count++;
			}
			if(count > 2) {
				System.out.println("You have " + count + " books as obligations in our library: ");
				for (Book b : books) {
					int i = 0;
					System.out.println(i + " - " + b.getTitle());
					i++;
				}
			}
			return count;
		} catch (SQLException e) {
			System.out.println("Error database connection");
			e.printStackTrace();
		}
		return 0;
	}
	
	public boolean isBookNotInLibrary(int id_book) {
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_BYID_BOOK_CHECK);
			ps.setInt(1, id_book);
			ResultSet rs = ps.executeQuery();
			boolean flag = false;
			while (rs.next()) {
				Book book = new Book(id_book);
				book.setId(rs.getInt("id_book"));
				flag = true;
				return flag;
			}
			return flag;
		} catch (SQLException e) {
			System.out.println("Error database connection");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Map<Book, RegistReaders> readReadersOver(Reader reader) {
		return null;
	}

	@Override
	public RegistReaders getRegistReader(int id_reader, int id_book) {
		return null;
	}

	@Override
	public List<RegistReaders> findByReader(int id) {
		return null;
	}

	@Override
	public Map<Reader, Map<Book, RegistReaders>> readReadersOver(List<Reader> list) {
		return null;
	}



}
