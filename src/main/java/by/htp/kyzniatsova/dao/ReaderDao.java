package by.htp.kyzniatsova.dao;

import java.util.List;

import by.htp.kyzniatsova.domain.entity.Employee;

public interface ReaderDao {
	
	Employee read(int id);
	List<Employee> list();
	boolean insert(Employee employer);
	boolean delete(Employee employer);
	boolean update(Employee employer);

}
