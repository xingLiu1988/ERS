package com.revature.models;

public class UpdateInfoTemplate {
	private int userId;
	private String type;
	private String updatedValue;

	public UpdateInfoTemplate() {
		super();
	}

	public UpdateInfoTemplate(int userId, String type, String updatedValue) {
		super();
		this.userId = userId;
		this.type = type;
		this.updatedValue = updatedValue;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getupdatedValue() {
		return updatedValue;
	}

	public void setupdatedValue(String updatedValue) {
		this.updatedValue = updatedValue;
	}

	@Override
	public String toString() {
		return "UpdateInfoTemplate [userId=" + userId + ", type=" + type + ", updatedValue=" + updatedValue + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((updatedValue == null) ? 0 : updatedValue.hashCode());
		result = prime * result + userId;
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
		UpdateInfoTemplate other = (UpdateInfoTemplate) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (updatedValue == null) {
			if (other.updatedValue != null)
				return false;
		} else if (!updatedValue.equals(other.updatedValue))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

}
