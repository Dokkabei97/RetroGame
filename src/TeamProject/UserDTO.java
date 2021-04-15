package TeamProject;

import java.sql.Date;

public class UserDTO {
	private int idx;
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String tel;
	private int rank;
	private int score;
	private java.sql.Date date;
	private int admin;
	
	public UserDTO() {
		
	}
	
	public UserDTO(int idx, String id, String name, String email, String tel, int rank, int score,
			Date date, int admin) {
		super();
		this.idx = idx;
		this.id = id;
		this.name = name;
		this.email = email;
		this.tel = tel;
		this.rank = rank;
		this.score = score;
		this.date = date;
		this.admin = admin;
	}


	public UserDTO(int idx, String id, String pwd, String name, String email, String tel, int rank, int score,
			Date date, int admin) {
		super();
		this.idx = idx;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.tel = tel;
		this.rank = rank;
		this.score = score;
		this.date = date;
		this.admin = admin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

}
