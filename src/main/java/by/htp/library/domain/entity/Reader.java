package by.htp.library.domain.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Reader implements Serializable {

	private static final long serialVersionUID = 3684975853056735122L;
	private int id;
	private String num_ticket;
	private String name;
	private String surname;
	private String phone;
	private String password;
	private Calendar dateOfRegistr;
	
	public Reader() {
		this.dateOfRegistr = new GregorianCalendar();
	}

	public Reader(String num_ticket, String name, String surname, String phone, Calendar dateOfRegistr) {
		this.num_ticket = num_ticket;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.dateOfRegistr = dateOfRegistr;
	}
	
	public Reader(int id, String num_ticket, String name, String surname, String password, String phone, Calendar dateOfRegistr) {
		this.id = id; 
		this.num_ticket = num_ticket;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.phone = phone;
		this.dateOfRegistr = dateOfRegistr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNum_ticket() {
		return num_ticket;
	}

	public void setNum_ticket(String num_ticket2) {
		this.num_ticket = num_ticket2;
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

	public String getPhone() {
		return phone;
	}

	public void setPhoneNumber(String phone) {
		this.phone = phone;
	}

	public Calendar getDateOfRegistr() {
		return dateOfRegistr;
	}

	public void setDateOfRegistr(Calendar dateOfRegistr) {
		this.dateOfRegistr = dateOfRegistr;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfRegistr == null) ? 0 : dateOfRegistr.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((num_ticket == null) ? 0 : num_ticket.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		Reader other = (Reader) obj;
		if (dateOfRegistr == null) {
			if (other.dateOfRegistr != null)
				return false;
		} else if (!dateOfRegistr.equals(other.dateOfRegistr))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (num_ticket == null) {
			if (other.num_ticket != null)
				return false;
		} else if (!num_ticket.equals(other.num_ticket))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
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
		return "Employee [num_ticket=" + num_ticket + ", name=" + name + ", surname=" + surname + ", phone=" + phone
				+ ", dateOfRegistr=" + dateOfRegistr + "]";
	}


}
