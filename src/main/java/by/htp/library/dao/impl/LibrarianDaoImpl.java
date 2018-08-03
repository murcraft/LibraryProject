package by.htp.library.dao.impl;

import static by.htp.library.dao.util.MySqlPropertyManager.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import by.htp.library.console.ReadingConsole;
import by.htp.library.dao.DaoParam;
import by.htp.library.dao.LibrarianDao;
import by.htp.library.domain.entity.Librarian;

public class LibrarianDaoImpl implements LibrarianDao {
	
	private static final String SELECT_LIBRARIAN_BY_ID = "SELECT * FROM library.librarian l WHERE l.id_librarian = ?";
	private static final String SELECT_LIST_LIBRARIANS = "SELECT * FROM library.librarian";
	private static final String INSERT_LIBRARIAN = "INSERT INTO library.librarian(id_librarian, Num_ticket, Name, Surname, Password, Reg_date, Phone) VALUES(?,?,?,?,?,?,?)";
	private static final String LIBRARIAN = "DELETE from library.librarian where id_librarian = ?";
	private static final String UPDATE_ID_EMPLOYEE = "UPDATE library.librarian SET id_librarian = ?, Num_ticket = ?, Name = ?, Surname = ?, Password = ?, Reg_date = ?, Phone = ? where id_librarian = ?";
	private static final String SELECT_LOGIN = "SELECT id_librarian, Num_ticket, Name, Surname, Password, Reg_date, Phone FROM library.librarian r WHERE r.Num_ticket = ? and r.Password = ?";
	
	private DaoParam<Librarian> librarianDao;
	private ReadingConsole readingConsole;

	public LibrarianDaoImpl() {
		readingConsole = new ReadingConsole();
	}
	
	@Override
	public Librarian read(int id) {
		Librarian libr = null;
		
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = connection.prepareStatement(SELECT_LIBRARIAN_BY_ID);
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
			PreparedStatement ps = connection.prepareStatement(SELECT_LIST_LIBRARIANS);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listLibr.add(buildLibr(rs));
			}
		} catch (SQLException e) {
			System.out.println("Error database connection");
			e.printStackTrace();
		}
		return listLibr;
	}
	
	public void printLibrarians() {
		List<Librarian> listLibr = new ArrayList<Librarian>();
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = connection.prepareStatement(SELECT_LIST_LIBRARIANS);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listLibr.add(buildLibr(rs));
			}
			for(Librarian lib : listLibr) {
				System.out.println(lib);
			}
		} catch (SQLException e) {
			System.out.println("Error database connection");
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean insert(Librarian libr) {
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = connection.prepareStatement(INSERT_LIBRARIAN);
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
		libr.setId(rs.getInt("id_librarian"));
		libr.setId(rs.getInt("Num_ticket"));
		libr.setName(rs.getString("Name"));
		libr.setSurname(rs.getString("Surname"));
		libr.setPassword(rs.getString("Password"));
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(rs.getDate("Reg_date"));
		libr.setRegDate(calendar);
		libr.setPhone(rs.getString("Phone"));
		return libr;
	}

	@Override
	public Librarian authorization(String login, String password) {
		Librarian librarian = new Librarian();
			try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
				PreparedStatement ps = connection.prepareStatement(SELECT_LOGIN);
				ps.setString(1, login);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					librarian = buildLibr(rs);
					return librarian;
				} else return null;
			} catch (SQLException e) {			
				e.printStackTrace();
			}
			return librarian;

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
