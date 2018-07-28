package by.htp.kyzniatsova.domain.entity;

import java.io.Serializable;
import java.sql.Date;

public class Employee implements Serializable {

	private static final long serialVersionUID = 3684975853056735122L;
	
	private int id;
	private String name;
	private String surname;
	private boolean isReader;
	private String phoneNumber;
	private String numOfReadTicket;
	private Date dateOfRegistration;
	
	public Employee() {
	}

	public Employee(int id, String name, String surname, boolean isReader, String phoneNumber, Date dateOfRegistration) {
		this.setId(id);
		this.name = name;
		this.surname = surname;
		this.isReader = isReader;
		this.phoneNumber = phoneNumber;
		this.dateOfRegistration = dateOfRegistration;
	}

	public Employee(String name, String surname, boolean isReader, String phoneNumber, String numOfReadTicket) {
		this.name = name;
		this.surname = surname;
		this.isReader = isReader;
		this.phoneNumber = phoneNumber;
		this.numOfReadTicket = numOfReadTicket;
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

	public boolean isReader() {
		return isReader;
	}

	public void setReader(boolean isReader) {
		this.isReader = isReader;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getNumOfReadTicket() {
		return numOfReadTicket;
	}

	public void setNumOfReadTicket(String numOfReadTicket) {
		this.numOfReadTicket = numOfReadTicket;
	}

	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}

	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfRegistration == null) ? 0 : dateOfRegistration.hashCode());
		result = prime * result + id;
		result = prime * result + (isReader ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((numOfReadTicket == null) ? 0 : numOfReadTicket.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
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
		Employee other = (Employee) obj;
		if (dateOfRegistration == null) {
			if (other.dateOfRegistration != null)
				return false;
		} else if (!dateOfRegistration.equals(other.dateOfRegistration))
			return false;
		if (id != other.id)
			return false;
		if (isReader != other.isReader)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numOfReadTicket == null) {
			if (other.numOfReadTicket != null)
				return false;
		} else if (!numOfReadTicket.equals(other.numOfReadTicket))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
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
		return "Employer [name=" + name + ", surname=" + surname + ", isReader=" + isReader + ", phoneNumber="
				+ phoneNumber + ", numOfReadTicket=" + numOfReadTicket + ", dateOfRegistration=" + dateOfRegistration + "]";
	}

}
