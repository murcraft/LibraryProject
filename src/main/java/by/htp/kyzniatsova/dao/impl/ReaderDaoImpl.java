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

	private List <Employee> employees = new ArrayList<Employee>();

	public Employee read(int id) {
		Employee employee = null;
		
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
	
			if(rs.next()) {
				employee = buildEmployer(rs);
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	public List<Employee> list() {
		List<Employee> employees = new ArrayList<Employee>();
		return employees;
	}
	
	@Override
	public int insert(Employee employee) {
		int a = -1;
		boolean isExistId = false;
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			PreparedStatement psSel = connection.prepareStatement(SELECT_ID_EMPLOYEE);
			
			ResultSet rsAll = psSel.executeQuery();
			int id = employee.getId();
			while(rsAll.next()) {
				if(rsAll.getInt("id_employee") == id) {
					System.out.println("This id is existing in database");
					isExistId = true;
					break;
				}
			}
			
			if(isExistId == false) {
				PreparedStatement ps = connection.prepareStatement(INSERT_EMPLOYEE);
			
				ps.setInt(1, employee.getId());
				ps.setString(2, employee.getName());
				ps.setString(3, employee.getSurname());
				ps.setString(4, employee.getNumOfReadTicket());
				ps.setString(5, employee.getPhoneNumber());
				ps.setDate(6, employee.getDateOfRegistration());
		
				ps.executeUpdate();
				System.out.println(employee + " was inserted");
		
				return a;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	public void delete(Employee employee) {
	
	}

	public void update(Employee employee) {
		
	}

	public Employee readAll() {
		Employee employee = null;
		
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			
			PreparedStatement psAll = connection.prepareStatement(SELECT_ALL_EMPLOYEES);
			ResultSet rsAll = psAll.executeQuery();
			
			while(rsAll.next()) {
				employee = buildEmployer(rsAll);
				employees.add(employee);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	public List<Employee> getBooks() {
		return null;
	}
	
	private Employee buildEmployer(ResultSet rs) throws SQLException {//id_employee, Name, Surname, ReadTicket, PhoneNumber, Regist_date
		Employee employee = new Employee();
		employee.setId(rs.getInt("id_employee"));
		employee.setName(rs.getString("Name"));
		employee.setSurname(rs.getString("Surname"));
		employee.setNumOfReadTicket(rs.getString("ReadTicket"));
		employee.setPhoneNumber(rs.getString("PhoneNumber"));
		employee.setDateOfRegistration(rs.getDate("Regist_date"));
		return employee;
	}

	

}
