package by.htp.library.dao;

import java.util.List;

import by.htp.library.domain.entity.RegistReaders;

public interface RegistReadersDao {
	
	RegistReaders read(int id);

	boolean insert(RegistReaders lCard);

	boolean update(RegistReaders lCard);

	boolean delete(RegistReaders lCard);

	List<RegistReaders> getAll();

}
