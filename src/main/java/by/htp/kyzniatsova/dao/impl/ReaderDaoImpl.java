package by.htp.kyzniatsova.dao.impl;

import static by.htp.kyzniatsova.dao.util.MySqlPropertyManager.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.kyzniatsova.dao.ReaderDao;
import by.htp.kyzniatsova.domain.entity.Employee;

public class ReaderDaoImpl implements ReaderDao {
	
	private static final String SELECT_EMPLOYEE_BY_ID = "SELECT Name, Surname, ReadTicket, PhoneNumber, REgist_date FROM library.employee e WHERE e.id_employee = ?";
	private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM library.employee;";
	private static final String INSERT_EMPLOYEE = "INSERT INTO library.employee(id_employee, Name, Surname, ReadTicket, PhoneNumber, Regist_date) VALUES(?,?,?,?,?);";
	private static final String SELECT_ID_EMPLOYEE = "SELECT * from library.employee";
	private static final String DELETE_ID_EMPLOYEE = "DELETE from library.employee where id_employee = ?";
	private static final String UPDATE_ID_EMPLOYEE = "UPDATE library.employee SET id_employee = ?, Name = ?, Surname = ?, ReadTicket = ?, PhoneNumber = ?, Regist_date = ? where id_employee = ?";

	private List <Employee> books = new ArrayList<Employee>();

	public Employee read(int id) {
		Employee book = null;
		
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
	
			if(rs.next()) {
				book = buildEmployer(rs);
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	public List<Employee> list() {
		List<Employee> books = new ArrayList<Employee>();
		return books;
	}
	
	@Override
	public int insert(Employee book) {
		int a = -1;
		boolean isExistId = false;
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			PreparedStatement psSel = connection.prepareStatement(SELECT_ID_EMPLOYEE);
			
			ResultSet rsAll = psSel.executeQuery();
			int id = book.getId();
			while(rsAll.next()) {
				if(rsAll.getInt("id_book") == id) {
					System.out.println("The id is existing in database");
					isExistId = true;
					break;
				}
			}
			
			if(isExistId == false) {
				PreparedStatement ps = connection.prepareStatement(INSERT_EMPLOYEE);
			
				ps.setInt(1, book.getId());
				ps.setString(2, book.getTitle());
				ps.setDate(3, book.getDate());
		
				ps.executeUpdate();
				System.out.println(book + " was inserted");
		
				return a;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	public void delete(Employee book) {
	
	}

	public void update(Employee book) {
		
	}

	public Employee readAll() {
		Employee book = null;
		
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			
			PreparedStatement psAll = connection.prepareStatement(SELECT_ALL_EMPLOYEES);
			ResultSet rsAll = psAll.executeQuery();
			
			while(rsAll.next()) {
				book = buildEmployer(rsAll);
				books.add(book);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	public List<Employee> getBooks() {
		return null;
	}
	
	private Employee buildEmployer(ResultSet rs) throws SQLException {
		Employee book = new Employee();
		book.setId(rs.getInt("id_book"));
		book.setTitle(rs.getString("Title"));
		book.setDate(rs.getDate("Prod_year"));
		return book;
	}

	

}
