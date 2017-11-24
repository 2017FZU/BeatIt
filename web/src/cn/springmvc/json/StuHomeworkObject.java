package cn.springmvc.json;

import java.util.List;

public class StuHomeworkObject {
	private int sid;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getIsview() {
		return isview;
	}
	public void setIsview(int isview) {
		this.isview = isview;
	}
	public List<?> getWorkimg() {
		return workimg;
	}
	public void setWorkimg(List<?> workimg) {
		this.workimg = workimg;
	}
	private String sno;
	private String sname;
	private int score;
	private int isview;
	private List<?>workimg;

}
