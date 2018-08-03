package by.htp.collections.stores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.htp.library.domain.entity.Book;
import by.htp.library.domain.entity.RegistReaders;

public class BooksStore implements Serializable {

	private static final long serialVersionUID = -4536632574241601183L;
	private List<Book> books = new ArrayList<Book>();
	private Map <Book, RegistReaders> registReaders = new HashMap<Book, RegistReaders>();
	
	public BooksStore() {
		
	}
	
	public BooksStore(List<Book> books, Map<Book, RegistReaders> registReaders) {
		super();
		this.books = books;
		this.registReaders = registReaders;
	}

	public List<Book> getBooks() {
		return books;
	}
	
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	public Map<Book, RegistReaders> getIssuedBooks() {
		return registReaders;
	}
	
	public void setIssuedBooks(Map<Book, RegistReaders> registReaders) {
		this.registReaders = registReaders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((books == null) ? 0 : books.hashCode());
		result = prime * result + ((registReaders == null) ? 0 : registReaders.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BooksStore other = (BooksStore) obj;
		if (books == null) {
			if (other.books != null)
				return false;
		} else if (!books.equals(other.books))
			return false;
		if (registReaders == null) {
			if (other.registReaders != null)
				return false;
		} else if (!registReaders.equals(other.registReaders))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BooksStore [books=" + books + ", registReaders=" + registReaders + "]";
	}
	
	
	
}
