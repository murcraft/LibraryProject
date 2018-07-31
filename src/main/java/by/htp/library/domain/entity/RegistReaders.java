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
	private int idBook;
	private int idReader;
	private Calendar dateStart;
	private Calendar dateEnd;
	
	public RegistReaders() {
		this.dateStart = new GregorianCalendar();
		this.dateEnd = new GregorianCalendar();
	}
	
	public RegistReaders(int id, int id_book, int id_reader, Calendar dateStart, Calendar dateEnd) {
		this.id = id;
		this.idBook = id_book;
		this.idReader = id_reader;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId_book() {
		return idBook;
	}
	
	public void setId_book(int id_book) {
		this.idBook = id_book;
	}
	
	public int getId_reader() {
		return idReader;
	}
	
	public void setId_reader(int id_reader) {
		this.idReader = id_reader;
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
		result = prime * result + ((dateEnd == null) ? 0 : dateEnd.hashCode());
		result = prime * result + ((dateStart == null) ? 0 : dateStart.hashCode());
		result = prime * result + id;
		result = prime * result + idBook;
		result = prime * result + idReader;
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
		if (idBook != other.idBook)
			return false;
		if (idReader != other.idReader)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RegReaders [id=" + id + ", idBook=" + idBook + ", idReader=" + idReader + ", dateStart=" + dateStart
				+ ", dateEnd=" + dateEnd + "]";
	}
	
		

}
