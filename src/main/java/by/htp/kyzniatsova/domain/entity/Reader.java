package by.htp.kyzniatsova.domain.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Reader implements Serializable {

	private static final long serialVersionUID = 3684975853056735122L;
	
	private int num_ticket;
	private String name;
	private String surname;
	private String phone;
	private Calendar dateOfRegistr;
	
	public Reader() {
		this.dateOfRegistr = new GregorianCalendar();
	}

	public Reader(int num_ticket, String name, String surname, String phone, Calendar dateOfRegistr) {
		this.num_ticket = num_ticket;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.dateOfRegistr = dateOfRegistr;
	}

	public int getNum_ticket() {
		return num_ticket;
	}

	public void setNum_ticket(int num_ticket) {
		this.num_ticket = num_ticket;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfRegistr == null) ? 0 : dateOfRegistr.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + num_ticket;
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (num_ticket != other.num_ticket)
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
