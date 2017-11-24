package cn.springmvc.model;

/**
 */
public class ClassLists {
	
	public int cid;
	public String  cname;
	public String tid;
	public String emg;
	public String getTid() {
		return tid;
	}
	public void setTid(String tname) {
		this.tid = tname;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getEmg() {
		return emg;
	}
	public void setEmg(String emg) {
		this.emg = emg;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	@Override
	public String toString() {
		return "ClassLists [cid=" + cid + ", cname=" + cname + ", tid=" + tid + "]";
	}
	

}
