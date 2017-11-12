package cn.springmvc.json;

import java.util.List;

public class ListObject extends AbstractJsonObject {

	// 列表对象
	private List<?> data;

	public List<?> getItems() {
		return data;
	}

	public void setItems(List<?> items) {
		this.data = items;
	}

}
