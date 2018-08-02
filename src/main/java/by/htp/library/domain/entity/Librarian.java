package by.htp.library.domain.entity;

import java.io.Serializable;

public class Librarian implements Serializable {
	
	private static final long serialVersionUID = 8502245257249798664L;
	private int id;
	private String name;
	private String surname;
	private LibrarianEnum login;
	private LibrarianEnum password;
	
	public Librarian() {

	}

	public Librarian(int id, String name, String surname, LibrarianEnum login, LibrarianEnum password) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public LibrarianEnum getLogin() {
		return login;
	}

	public void setLogin(LibrarianEnum login) {
		this.login = login;
	}

	public LibrarianEnum getPassword() {
		return password;
	}

	public void setPassword(LibrarianEnum password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		Librarian other = (Librarian) obj;
		if (id != other.id)
			return false;
		if (login != other.login)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password != other.password)
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Librarian [id=" + id + ", name=" + name + ", surname=" + surname + ", login=" + login + ", password="
				+ password + "]";
	}

}