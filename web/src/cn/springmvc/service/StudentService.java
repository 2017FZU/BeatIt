package cn.springmvc.service;

import java.util.List;

import cn.springmvc.model.ClassLists;


/**
 */
public interface StudentService {
	public List<ClassLists> getClassList(String id);
}
