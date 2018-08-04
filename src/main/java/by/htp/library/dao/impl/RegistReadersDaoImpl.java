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
	private static final String SELECT_LIBCARD_BYID_BOOK = "SELECT id_taking_book, id_reader, id_book, Take_date, Return_date FROM library.taking_book WHERE id_book = ?";
	private static final String SELECT_ALL_LIBCARDS = "SELECT * FROM library.taking_book";
	private static final String INSERT_LIBCARD_BYID = "INSERT INTO library.taking_book (date_start,date_end,id_book,id_employee)VALUES(?,?,?,?)";
	private static final String DELETE_LIBCARD_BYID = "DELETE FROM library.taking_book WHERE id_card = ?";
	private static final String UPDATE_LIBCARD_BYID = "UPDATE library.taking_book SET date_start = ? , date_end = ? , id_book = ? , id_employee = ? WHERE id_card = ?";

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
		
		ReaderDao emplDao = new ReaderDaoImpl();
		Reader reader = emplDao.read(resSet.getInt("id_reader"));
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
	
	public void readThreeBook(String numTicket) {
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
		} catch (SQLException e) {
			System.out.println("Error database connection");
			e.printStackTrace();
		}
	}
	
	public Map<Reader, Map<Book, RegistReaders>> readReadersObligation(List<Reader> readersList) {
		calculateOverdue();
		ReaderDao readerDao = new ReaderDaoImpl();
		Reader reader = new Reader();
		Map<Book, RegistReaders> booksMap = new HashMap<>();
		Map<Reader, Map<Book, RegistReaders>> readersMap = new HashMap<>();
		for (Reader r : readersList) {
			booksMap = readReadersOver(r);
			if ( !booksMap.isEmpty()) {
				reader = readerDao.read(r.getId());
				readersMap.put(reader, booksMap);
			}
		}
		return readersMap;
	}

	private List<RegistReaders> getNotReturnBooks() {
		List<RegistReaders> reg = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_NOTRETURN_LIBCARDS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				reg.add(buildRegistReaders(rs));
			}
		} catch (SQLException e) {
			System.out.println("Error database connection");
			e.printStackTrace();
		}
		return reg;
	}

	@Override
	public Map<Book, RegistReaders> readReadersOver(Reader reader) {
		RegistReaders reg = null;
		Map<Book, RegistReaders> bookMap = new HashMap<>();
		
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_NOTRETURN_LIBCARDS_BYUSER);
			ps.setString(1, reader.getNum_ticket());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {	
				reg = buildRegistReaders(rs);
				bookMap.put(reg.getBook(), reg);
			}
			
		} catch (SQLException e) {
			System.out.println("Error database connection");
			e.printStackTrace();
		}
		return bookMap;
	}
	private boolean updateOverdue(RegistReaders reg) {
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(UPDATE_LIBRARUCARD_OVERDUE);
			ps.setInt(1, reg.getDaysOverdue());
			ps.setInt(2, reg.getId());
			
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Error database connection");
			e.printStackTrace();
		}
		return false;
	}

	public void calculateOverdue() {
		ZonedDateTime zdt1 = ZonedDateTime.now();
		for (RegistReaders reg : getNotReturnBooks()) {
			if (!reg.isReturned()) {
				Period period = Period.between(
						reg.getDateEnd().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
						zdt1.toLocalDate());
				int k = period.getDays();
				if (k > 0) {
					reg.setDaysOverdue(k);
					updateOverdue(reg);
				}
			}
		}
	}

	@Override
	public List<RegistReaders> findByReader(int id) {
		List<RegistReaders> reg = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_LIBCARDS_BYEMPLOYEE);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				reg.add(buildRegistReaders(rs));
			}
		} catch (SQLException e) {
			System.out.println("Error database connection");
			e.printStackTrace();
		}

		return reg;
	}
	
	@Override
	public RegistReaders getRegistReader(int id_reader, int id_book) {
		RegistReaders reg = null;
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_LIBCARDS_BYEMPLOYEE_AND_BOOK);
			ps.setInt(1, id_reader);
			ps.setInt(2, id_book);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				reg = buildRegistReaders(rs);
			}
		} catch (SQLException e) {
			System.out.println("Error database connection");
			e.printStackTrace();
		}

		return reg;
	}


}
