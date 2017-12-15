package cn.springmvc.model;

public class User {
	private boolean success;
	private String session;
	private int tid;
	private String email; 
	private String tname;
	private String tel;
	private String img;
	private String error;
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String sname) {
		this.tname = sname;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	
}
