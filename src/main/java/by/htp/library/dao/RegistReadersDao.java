package by.htp.library.dao;

import java.util.List;
import java.util.Map;

import by.htp.library.domain.entity.Book;
import by.htp.library.domain.entity.Reader;
import by.htp.library.domain.entity.RegistReaders;

public interface RegistReadersDao {
	
	RegistReaders read(int id);

	boolean insert(RegistReaders lCard);

	boolean update(RegistReaders lCard);

	boolean delete(RegistReaders lCard);

	List<RegistReaders> getAll();

	Map<Book, RegistReaders> readReadersOver(Reader reader);

	RegistReaders getRegistReader(int id_reader, int id_book);

	List<RegistReaders> findByReader(int id);

	boolean isBookNotInLibrary(int i);

	int readThreeBook(String numTicket);

}
