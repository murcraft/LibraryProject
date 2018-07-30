package by.htp.kyzniatsova.dao.impl;

import static by.htp.kyzniatsova.dao.util.MySqlPropertyManager.*;

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

import by.htp.kyzniatsova.dao.ReaderDao;
import by.htp.kyzniatsova.entity.Reader;

public class ReaderDaoImpl implements ReaderDao {
	
	private static final String SELECT_EMPLOYEE_BY_ID = "SELECT Num_ticket, Name, Surname, Reg_date, Phone FROM library.readers r WHERE r.Num_ticket = ?";
	private static final String SELECT_LIST_EMPLOYEE = "SELECT * FROM library.readers r WHERE r.Num_ticket = ?";
	private static final String INSERT_EMPLOYEE = "INSERT INTO library.readers(Name, Surname, Reg_date, Phone) VALUES(?,?,?,?);";
	private static final String DELETE_ID_EMPLOYEE = "DELETE from library.reader where Num_ticket = ?";
	private static final String UPDATE_ID_EMPLOYEE = "UPDATE library.readers SET id_employee = ?, Name = ?, Surname = ?, Reg_date = ?, Phone = ? where Num_ticket = ?";

	@Override
	public Reader read(int id) {
		Reader employee = null;
		
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				employee = buildEmployee(rs);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public List<Reader> list() {
		List<Reader> listEmployee = new ArrayList<Reader>();
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = connection.prepareStatement(SELECT_LIST_EMPLOYEE);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listEmployee.add(buildEmployee(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return listEmployee;
	}
	
	@Override
	public boolean insert(Reader employee) {
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = connection.prepareStatement(INSERT_EMPLOYEE);
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getSurname());
			ps.setDate(3, new Date(employee.getDateOfRegistr().getTimeInMillis()));
			ps.setString(4, employee.getPhone());
			if (ps.executeUpdate() == 1) {
				return true;
			}			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(Reader employee) {
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			PreparedStatement ps = connection.prepareStatement(DELETE_ID_EMPLOYEE);
			ps.setInt(1, employee.getNum_ticket());
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(Reader employee) {
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			PreparedStatement ps = connection.prepareStatement(UPDATE_ID_EMPLOYEE);
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getSurname());
			ps.setDate(3, new Date(employee.getDateOfRegistr().getTimeInMillis()));
			ps.setString(4, employee.getPhone());
			ps.setInt(5, employee.getNum_ticket());
			System.out.println(ps);
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return false;
		
	}
	
	private Reader buildEmployee(ResultSet rs) throws SQLException {
		Reader employee = new Reader();
		employee.setNum_ticket(rs.getInt("Num_ticket"));
		employee.setName(rs.getString("Name"));
		employee.setSurname(rs.getString("Surname"));
		employee.setPhoneNumber(rs.getString("PhoneNumber"));
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(rs.getDate("Regist_date"));
		employee.setDateOfRegistr(calendar);
		return employee;
	}

	public Reader getEmployee(ResultSet rs) throws SQLException {
		return buildEmployee(rs);
	}

}
