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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import by.htp.library.dao.BookDao;
import by.htp.library.dao.ReaderDao;
import by.htp.library.dao.RegistReadersDao;
import by.htp.library.dao.ReportDao;
import by.htp.library.domain.entity.Book;
import by.htp.library.domain.entity.Reader;
import by.htp.library.domain.entity.RegistReaders;

public class ReportDaoImpl implements ReportDao {
	
	private static final String SELECT_READER_BY_MONTH = "Select Reader*, COUNT(library.taking_book.id_readreE) as readers_b from library.taking_book tb join readers r"
			+ "on tb.id_reader = readers.id_reader wher tb.Return_date <> '1111-11-11' and tb.id_reader in (SELECT distinct read.id_reader FROM library.readers read join library.taking_book tbook"
			+ "on read.id_reader = tbook.id_reader  where (Take_date BETWEEN ? AND ?) and Return_date = '1111-11-11' group by read.id_reader HAVING Take_date between ? and ?";
	private static final String SELECT_READ_BOOKS = "Select b.id_book, b.Title, b.Pages, b.Prod_date, count(*) as returnedbooks from library.taking_book tb join library.book b ON b.id_book = tb.id_book where tb.id_book"
			+ "in(select select distinct id_taking_book from library.taking_book tbook where return_date <> '1111-11-11' GROUP BY tbook.id_book ORDER BY tbook.Take_date DESC, book.Title ASC";

	@Override
	public Map<Reader, Integer> booksForMonth(int min, int max) {
		Calendar currentDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.DAY_OF_MONTH, -30);
		SimpleDateFormat formatCurrentDate = new SimpleDateFormat("yyyy-MM-dd");
		Map<Reader, Integer> readerMap = new HashMap<>();
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_READER_BY_MONTH);
			ps.setString(1, formatCurrentDate.format(endDate.getTime()));
			ps.setString(2, formatCurrentDate.format(currentDate.getTime()));
			ps.setInt(3, min);
			ps.setInt(4, max);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				readerMap.put(buildreader(rs), rs.getInt("READED_BOOKS"));
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return readerMap;

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
			PreparedStatement ps = conn.prepareStatement(SELECT_READ_BOOKS);
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
