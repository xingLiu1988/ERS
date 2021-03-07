package com.revature.models;

public class LoginTemplate {
	private String username;
	private String password;
	private String typeOfUser;

	public LoginTemplate() {
		super();
	}

	public LoginTemplate(String username, String password, String typeOfUser) {
		super();
		this.username = username;
		this.password = password;
		this.typeOfUser = typeOfUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTypeOfUser() {
		return typeOfUser;
	}

	public void setTypeOfUser(String typeOfUser) {
		this.typeOfUser = typeOfUser;
	}

	@Override
	public String toString() {
		return "LoginTemplate [username=" + username + ", password=" + password + ", typeOfUser=" + typeOfUser + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((typeOfUser == null) ? 0 : typeOfUser.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		LoginTemplate other = (LoginTemplate) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (typeOfUser == null) {
			if (other.typeOfUser != null)
				return false;
		} else if (!typeOfUser.equals(other.typeOfUser))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
