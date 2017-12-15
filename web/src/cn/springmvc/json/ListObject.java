package cn.springmvc.json;

import cn.springmvc.model.*;
import cn.springmvc.json.*;

public class ListObject extends AbstractJsonObject {

	// 列表对象
	private Object data;
	public Object getdata() {
		return data;
	}

	public void setdata(Object data) {
		this.data = data;
	}

}
