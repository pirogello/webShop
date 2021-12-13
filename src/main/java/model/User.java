package model;

import java.util.Objects;

public class User {

	private int id;
	private String firstName;
	private String secondName;
	private String password;
	private Role role;

	public User() {
	}

	public User(int id, String firstName, String secondName, String password, Role role) {
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
		this.password = password;
		this.role = role;
	}

	public User( String firstName, String secondName, String password, Role role) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", secondName='" + secondName + '\'' +
				", password='" + password + '\'' +
				", role=" + role +
				'}';
	}

	@Override
	public boolean equals(Object o) {

		if(o instanceof User){
			if ((this.firstName.equals(((User) o).firstName))
					&& (this.secondName.equals(((User) o).secondName))
					&& (this.password.equals(((User) o).password)))
				return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, secondName, password, role);
	}
}
