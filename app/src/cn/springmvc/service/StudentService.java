package cn.springmvc.service;

import java.util.List;

import cn.springmvc.model.Classes;

/**
 */
public interface StudentService {
	public List<Classes> getClassList(String sid);
}
