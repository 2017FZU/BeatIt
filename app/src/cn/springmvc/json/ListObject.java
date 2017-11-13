package cn.springmvc.json;

import java.util.List;

public class ListObject extends AbstractJsonObject {

	// 列表对象
	private List<?> data;

	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
}
