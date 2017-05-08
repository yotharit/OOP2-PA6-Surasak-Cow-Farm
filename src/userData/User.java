package userData;

import java.time.LocalDate;

public class User {
	private String name;
	private String surname;
	private LocalDate birthDate;
	private String id;
	private String phoneNumber;
	private String address;
	private String userType;
	private String userName;
	private String password;

	public User(String name, String surname, LocalDate birthDate, 
			String id, String phoneNumber, String address,
			String userType, String userName, String password) 
	{
		super();
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.userType = userType;
		this.userName = userName;
		this.password = password;
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
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
