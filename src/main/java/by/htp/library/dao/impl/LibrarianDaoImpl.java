package by.htp.library.dao.impl;

import static by.htp.library.dao.util.MySqlPropertyManager.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import by.htp.library.console.ReadingConsole;
import by.htp.library.controller.factory.ReaderController;
import by.htp.library.controller.impl.LibrarianControllerImpl;
import by.htp.library.controller.impl.ReaderControllerImpl;
import by.htp.library.dao.DaoParam;
import by.htp.library.dao.LibrarianDao;
import by.htp.library.dao.ReaderDao;
import by.htp.library.domain.entity.Librarian;
import by.htp.library.domain.entity.Reader;

public class LibrarianDaoImpl implements LibrarianDao {
	
	private static final String SELECT_EMPLOYEE_BY_ID = "SELECT Num_ticket, Name, Surname, Reg_date, Phone FROM library.readers r WHERE r.Num_ticket = ?";
	private static final String SELECT_LIST_EMPLOYEE = "SELECT * FROM library.readers r WHERE r.Num_ticket = ?";
	private static final String INSERT_EMPLOYEE = "INSERT INTO library.readers(Name, Surname, Reg_date, Phone) VALUES(?,?,?,?);";
	private static final String DELETE_ID_EMPLOYEE = "DELETE from library.reader where Num_ticket = ?";
	private static final String UPDATE_ID_EMPLOYEE = "UPDATE library.readers SET id_employee = ?, Name = ?, Surname = ?, Reg_date = ?, Phone = ? where Num_ticket = ?";
	private static final String SELECT_LOGIN = "SELECT Num_ticket, Password FROM library.readers r WHERE r.Num_ticket = ? and r.Password = ?";
	private static final String SELECT_PASS = "SELECT Password FROM library.readers r WHERE r.Num_ticket = ? and r.Password = ?";
	
	private DaoParam<Librarian> librarianDao;
	private ReadingConsole readingConsole;

	public LibrarianDaoImpl() {
		librarianDao =  new LibrarianDaoImpl();
		readingConsole = new ReadingConsole();
	}
	
	@Override
	public Librarian read(int id) {
		Librarian libr = null;
		
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				libr = buildLibr(rs);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return libr;
	}

	@Override
	public List<Librarian> list() {
		List<Librarian> listLibr = new ArrayList<Librarian>();
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = connection.prepareStatement(SELECT_LIST_EMPLOYEE);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listLibr.add(buildLibr(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return listLibr;
	}
	
	@Override
	public boolean insert(Librarian libr) {
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = connection.prepareStatement(INSERT_EMPLOYEE);
			ps.setString(1, libr.getName());
			ps.setString(2, libr.getSurname());
			if (ps.executeUpdate() == 1) {
				return true;
			}			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}

	private Librarian buildLibr(ResultSet rs) throws SQLException {
		Librarian libr = new Librarian();
		libr.setId(rs.getInt("id"));
		libr.setName(rs.getString("Name"));
		libr.setSurname(rs.getString("Surname"));
		return libr;
	}

	@Override
	public boolean authorization(String login, String password) {
			try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
				PreparedStatement ps = connection.prepareStatement(SELECT_LOGIN);
				ps.setString(1, login);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					buildLibr(rs);
				}
				if (ps.executeUpdate() == 1) {
					return true;
				}
			} catch (SQLException e) {			
				e.printStackTrace();
			}
			return false;

	}
	
	public Boolean checkReader(String login, String pass) {
		try (Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			PreparedStatement ps = connection.prepareStatement(SELECT_LOGIN);
			ps.setString(1, login);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			rs.next();
			GregorianCalendar takeDate = new GregorianCalendar();
			GregorianCalendar currentDate = new GregorianCalendar();
			takeDate.setTime(rs.getDate("take_date"));
			takeDate.add(Calendar.DAY_OF_MONTH, 30);
			if (takeDate.after(currentDate))
				System.out.println("You have a book, which have to return untill  "
						+ new SimpleDateFormat("yyyy-MM-dd").format(takeDate.getTime()));
			return true;
		} catch (SQLException e) {
			System.err.println("You have not read any books");
			return false;
		}
	}

	@Override
	public Librarian librarian(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Librarian librarian) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Librarian librarian) {
		// TODO Auto-generated method stub
		return false;
	}

}
