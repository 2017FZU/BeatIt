package cn.springmvc.model;

/**
 */
public class ClassLists {
	public int cid;	
	public String  cname;
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "ClassLists [cid=" + cid + ", cname=" + cname + "]";
	}
	
	
	
	
	
	
	

}
