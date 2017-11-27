package cn.springmvc.model;

public class Studenthomeworks {
	// {"sid":1,  "sno":123456, "sname":"Mr.H", "ScoreShow":"10/20", "AllStar":50,  "count":4}, 
	private int sid;
	private String sno;
	private String sname;
	private String ScoreShow;
	private int AllStar;
	private int count;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
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
	public String getScoreShow() {
		return ScoreShow;
	}
	public void setScoreShow(String scoreShow) {
		ScoreShow = scoreShow;
	}
	public int getAllStar() {
		return AllStar;
	}
	public void setAllStar(int allStar) {
		AllStar = allStar;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
