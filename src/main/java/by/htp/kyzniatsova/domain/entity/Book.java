package by.htp.kyzniatsova.domain.entity;

import java.io.Serializable;
import java.sql.Date;

public class Book implements Serializable {

	private static final long serialVersionUID = 8040211567201788411L;
	private int id;
	private String title;
	private String pages;
	private String productYear;
	
	public Book() {

	}
	
	public Book(int id, String title, String pages, String date) {
		this.id = id;
		this.title = title;
		this.pages = pages;
		this.productYear = date;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPages() {
		return pages;
	}
	
	public void setPages(String pages) {
		this.pages = pages;
	}
	
	public String getProductYear() {
		return productYear;
	}
	
	public void setProductYear(String productYear) {
		this.productYear = productYear;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productYear == null) ? 0 : productYear.hashCode());
		result = prime * result + id;
		result = prime * result + ((pages == null) ? 0 : pages.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Book other = (Book) obj;
		if (productYear == null) {
			if (other.productYear != null)
				return false;
		} else if (!productYear.equals(other.productYear))
			return false;
		if (id != other.id)
			return false;
		if (pages == null) {
			if (other.pages != null)
				return false;
		} else if (!pages.equals(other.pages))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", pages=" + pages + ", date=" + productYear + "]";
	}

	
	
}
