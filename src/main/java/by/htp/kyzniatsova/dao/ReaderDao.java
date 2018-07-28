package by.htp.kyzniatsova.dao;

import java.util.List;

import by.htp.kyzniatsova.domain.entity.Employer;

public interface ReaderDao {
	Employer read(int id);
	List<Employer> list();
	int insert(Employer employer);
	void delete(Employer employer);
	void update(Employer employer);
	Employer readAll();
	List<Employer> getBooks();
}
