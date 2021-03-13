package com.revature.models;

public class Decision {
	private int reimb_id;
	private String decision;
	private String author;

	public int getReimb_id() {
		return reimb_id;
	}

	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Decision() {
		super();
	}

	public Decision(int reimb_id, String decision, String author) {
		super();
		this.reimb_id = reimb_id;
		this.decision = decision;
		this.author = author;
	}

	@Override
	public String toString() {
		return "Decision [reimb_id=" + reimb_id + ", decision=" + decision + ", author=" + author + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((decision == null) ? 0 : decision.hashCode());
		result = prime * result + reimb_id;
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
		Decision other = (Decision) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (decision == null) {
			if (other.decision != null)
				return false;
		} else if (!decision.equals(other.decision))
			return false;
		if (reimb_id != other.reimb_id)
			return false;
		return true;
	}

}
