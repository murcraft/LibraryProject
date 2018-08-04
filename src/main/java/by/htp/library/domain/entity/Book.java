package by.htp.library.domain.entity;

import java.io.Serializable;

public class Book implements Serializable {

	private static final long serialVersionUID = 8040211567201788411L;
	private int id;
	private String title;
	private int pages;
	private String productYear;
	
	public Book() {

	}
	
	public Book(int id, String title, int pages, String productYear) {
		this.id = id;
		this.title = title;
		this.pages = pages;
		this.productYear = productYear;
	}
	
	public Book(int id) {
		this.id = id;
	}
	
	public Book(String title, String productYear) {
		this.title = title;
		this.productYear = productYear;
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
	
	public int getPages() {
		return pages;
	}
	
	public void setPages(int pages) {
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
		result = prime * result + id;
		result = prime * result + pages;
		result = prime * result + ((productYear == null) ? 0 : productYear.hashCode());
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
		if (id != other.id)
			return false;
		if (pages != other.pages)
			return false;
		if (productYear == null) {
			if (other.productYear != null)
				return false;
		} else if (!productYear.equals(other.productYear))
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
		return "Book [id = " + id + ", \"" + title + "\", " + pages + " pages, " + productYear + "]";
	}
	
}
