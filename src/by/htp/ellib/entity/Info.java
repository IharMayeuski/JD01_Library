package by.htp.ellib.entity;

public class Info {
	private int id;
	private int book_id;
	private int users_id;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public int getUsers_id() {
		return users_id;
	}
	public void setUsers_id(int users_id) {
		this.users_id = users_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + book_id;
		result = prime * result + id;
		result = prime * result + users_id;
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
		Info other = (Info) obj;
		if (book_id != other.book_id)
			return false;
		if (id != other.id)
			return false;
		if (users_id != other.users_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Info [id=" + id + ", book_id=" + book_id + ", users_id=" + users_id + "]";
	}
}
