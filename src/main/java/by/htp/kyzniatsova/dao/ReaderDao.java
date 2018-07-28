package by.htp.kyzniatsova.dao;

import java.util.List;

import by.htp.kyzniatsova.domain.entity.Employee;

public interface ReaderDao {
	Employee read(int id);
	List<Employee> list();
	int insert(Employee employer);
	void delete(Employee employer);
	void update(Employee employer);
	Employee readAll();
	List<Employee> getBooks();
}
