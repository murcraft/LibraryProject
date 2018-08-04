package by.htp.library.dao.impl;

import static by.htp.library.dao.util.MySqlPropertyManager.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import by.htp.library.console.ReadingConsole;
import by.htp.library.controller.factory.ReaderController;
import by.htp.library.controller.impl.ReaderControllerImpl;
import by.htp.library.dao.DaoParam;
import by.htp.library.dao.ReaderDao;
import by.htp.library.domain.entity.Reader;

public class ReaderDaoImpl implements ReaderDao {
	
	private static final String SELECT_EMPLOYEE_BY_ID = "SELECT Num_ticket, Name, Surname, Reg_date, Phone FROM library.readers r WHERE r.Num_ticket = ?";
	private static final String SELECT_LIST_EMPLOYEE = "SELECT * FROM library.readers r WHERE r.Num_ticket = ?";
	private static final String INSERT_EMPLOYEE = "INSERT INTO library.readers(Num_ticket, Name, Surname, Phone, Reg_date, Password) VALUES(?,?,?,?,?,?);";
	private static final String DELETE_ID_EMPLOYEE = "DELETE from library.reader where Num_ticket = ?";
	private static final String UPDATE_ID_EMPLOYEE = "UPDATE library.readers SET id_employee = ?, Name = ?, Surname = ?, Reg_date = ?, Phone = ? where Num_ticket = ?";
	private static final String SELECT_LOGIN = "SELECT id_reader, Num_ticket, Name, Surname, Reg_date, Phone, Password FROM library.readers r WHERE r.Num_ticket = ? and r.Password = ?";
	private static final String SELECT_PASS = "SELECT Password FROM library.readers r WHERE r.Num_ticket = ? and r.Password = ?";
	
	private DaoParam<Reader> userDao;
	private ReadingConsole readingConsole;

	public ReaderDaoImpl() {
		readingConsole = new ReadingConsole();
	}
	
	@Override
	public Reader read(int id) {
		Reader employee = null;
		
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				employee = buildReader(rs);
				System.out.println();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public List<Reader> list() {
		List<Reader> listReader = new ArrayList<Reader>();
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = connection.prepareStatement(SELECT_LIST_EMPLOYEE);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listReader.add(buildReader(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return listReader;
	}
	
	@Override
	public boolean insert(Reader reader) {
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = connection.prepareStatement(INSERT_EMPLOYEE);
			ps.setString(1, reader.getNum_ticket());
			ps.setString(2, reader.getName());
			ps.setString(3, reader.getSurname());
			ps.setString(4, reader.getPhone());
			ps.setDate(5, new Date(reader.getDateOfRegistr().getTime().getTime()));
			ps.setString(6, reader.getPassword());
			if (ps.executeUpdate() == 1) {
				System.out.println(reader + " was inserted successfully");
				return true;
			}
		} catch (SQLIntegrityConstraintViolationException ex) {
			System.out.println("Error ticket number, this number exists in datatbase.");
		} catch (SQLException e) {
			e.printStackTrace();
		} 		
		return false;
	}
	
	@Override
	public boolean delete(Reader reader) {
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			PreparedStatement ps = connection.prepareStatement(DELETE_ID_EMPLOYEE);
			ps.setInt(1, reader.getId());
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean update(Reader reader) {
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			PreparedStatement ps = connection.prepareStatement(UPDATE_ID_EMPLOYEE);
			ps.setString(1, reader.getNum_ticket());
			ps.setString(2, reader.getName());
			ps.setString(3, reader.getSurname());
			ps.setDate(4, new Date(reader.getDateOfRegistr().getTimeInMillis()));
			ps.setString(5, reader.getPhone());
			ps.setInt(6, reader.getId());
			System.out.println(ps);
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return false;
		
	}
	
	private Reader buildReader(ResultSet rs) throws SQLException {
		Reader reader = new Reader();
		reader.setId(rs.getInt("id_reader"));
		reader.setNum_ticket(rs.getString("Num_ticket"));
		reader.setName(rs.getString("Name"));
		reader.setSurname(rs.getString("Surname"));
		reader.setPhoneNumber(rs.getString("Phone"));
		reader.setPassword(rs.getString("Password"));
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(rs.getDate("Reg_date"));
		reader.setDateOfRegistr(calendar);
		return reader;
	}
	
	@Override
	public Reader getReader(ResultSet rs) throws SQLException {
		return buildReader(rs);
	}
	
	@Override
	public Reader authorization(String login, String password) {
		Reader reader = new Reader();
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			PreparedStatement ps = connection.prepareStatement(SELECT_LOGIN);
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				reader = buildReader(rs);
				return reader;
			} else return null;
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return null;

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


}
