package cn.springmvc.model;

/**
 */
public class Teachers {
	public int tid;
	public String  tname;
	public String psw;
	public int tel;
	public String img;
	public int getTid() {
		return tid;
	}



	public void setTid(int tid) {
		this.tid = tid;
	}



	public String getTname() {
		return tname;
	}



	public void setTname(String tname) {
		this.tname = tname;
	}



	public String getPsw() {
		return psw;
	}



	public void setPsw(String psw) {
		this.psw = psw;
	}



	public int getTel() {
		return tel;
	}



	public void setTel(int tel) {
		this.tel = tel;
	}



	public String getImg() {
		return img;
	}



	public void setImg(String img) {
		this.img = img;
	}

	
	
	
	@Override
	public String toString() {
		return "Teachers [tid=" + tid + ", tname=" + tname + ", psw=" + psw + ", tel=" + tel + ", img=" + img + "]";
	}
	
	
	
}
