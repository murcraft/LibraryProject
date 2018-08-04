package by.htp.library.dao;

import java.util.Map;

import by.htp.library.domain.entity.Book;
import by.htp.library.domain.entity.Reader;
import by.htp.library.domain.entity.RegistReaders;

public interface ReportDao {
	
	public Map<Book,Integer> booksPop();
	
	public Map<Reader, Map<Book, RegistReaders>> readersObligation();
	
	public Map<Reader, Integer> booksForMonth(int min, int max);


}
