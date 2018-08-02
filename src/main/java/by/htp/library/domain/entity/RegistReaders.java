package by.htp.library.domain.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RegistReaders implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6766300609818985571L;
	private int id;
	private Book book;
	private Reader reader;
	private Calendar dateStart;
	private Calendar dateEnd;
	
	public RegistReaders() {
		this.dateStart = new GregorianCalendar();
		this.dateEnd = new GregorianCalendar();
	}
	
	public RegistReaders(int id, Book book, Reader reader, Calendar dateStart, Calendar dateEnd) {
		this.id = id;
		this.book = book;
		this.reader = reader;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public Reader getReader() {
		return reader;
	}
	
	public void setReader(Reader reader) {
		this.reader = reader;
	}
	
	public Calendar getDateStart() {
		return dateStart;
	}
	
	public void setDateStart(Calendar dateStart) {
		this.dateStart = dateStart;
	}
	
	public Calendar getDateEnd() {
		return dateEnd;
	}
	
	public void setDateEnd(Calendar dateEnd) {
		this.dateEnd = dateEnd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((dateEnd == null) ? 0 : dateEnd.hashCode());
		result = prime * result + ((dateStart == null) ? 0 : dateStart.hashCode());
		result = prime * result + id;
		result = prime * result + ((reader == null) ? 0 : reader.hashCode());
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
		RegistReaders other = (RegistReaders) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (dateEnd == null) {
			if (other.dateEnd != null)
				return false;
		} else if (!dateEnd.equals(other.dateEnd))
			return false;
		if (dateStart == null) {
			if (other.dateStart != null)
				return false;
		} else if (!dateStart.equals(other.dateStart))
			return false;
		if (id != other.id)
			return false;
		if (reader == null) {
			if (other.reader != null)
				return false;
		} else if (!reader.equals(other.reader))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RegistReaders [id=" + id + ", book=" + book + ", reader=" + reader + ", dateStart=" + dateStart
				+ ", dateEnd=" + dateEnd + "]";
	}


}
