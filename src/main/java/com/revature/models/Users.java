package com.revature.models;

public class Users {

	private int user_id;
	private String username;
	private String userpassword;
	private String first_name;
	private String last_name;
	private String email;
	private UserRole user_role;

	public Users() {
		super();
	}

	public Users(int user_id, String username, String userpassword, String first_name, String last_name, String email,
			UserRole user_role) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.userpassword = userpassword;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.user_role = user_role;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getUser_role() {
		return user_role;
	}

	public void setUser_role(UserRole user_role) {
		this.user_role = user_role;
	}

	@Override
	public String toString() {
		return "Users [user_id=" + user_id + ", username=" + username + ", userpassword=" + userpassword
				+ ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email + ", user_role="
				+ user_role + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + ((last_name == null) ? 0 : last_name.hashCode());
		result = prime * result + user_id;
		result = prime * result + ((user_role == null) ? 0 : user_role.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((userpassword == null) ? 0 : userpassword.hashCode());
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
		Users other = (Users) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		if (user_id != other.user_id)
			return false;
		if (user_role == null) {
			if (other.user_role != null)
				return false;
		} else if (!user_role.equals(other.user_role))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (userpassword == null) {
			if (other.userpassword != null)
				return false;
		} else if (!userpassword.equals(other.userpassword))
			return false;
		return true;
	}

}
