package cn.springmvc.json;

import cn.springmvc.model.Classes;

public class ClassDetailObject extends Classes {
	private String cname;
	private String tname;
	private String temail;
	private Object newnotice;
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTemail() {
		return temail;
	}
	public void setTemail(String temail) {
		this.temail = temail;
	}
	public Object getNewnotice() {
		return newnotice;
	}
	public void setNewnotice(Object newnotice) {
		this.newnotice = newnotice;
	}
}
