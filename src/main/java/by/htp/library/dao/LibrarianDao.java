package by.htp.library.dao;

import java.util.List;

import by.htp.library.domain.entity.Librarian;

public interface LibrarianDao extends DaoParam<Librarian> {
	
	Librarian librarian(int id);
	
	List<Librarian> list();
	
	boolean insert(Librarian librarian);
	
	boolean delete(Librarian librarian);
	
	boolean update(Librarian librarian);

	Librarian authorization(String login, String password);

}
