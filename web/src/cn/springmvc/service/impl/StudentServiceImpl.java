package cn.springmvc.service.impl;

import java.util.List;

import cn.springmvc.business.StudentBusiness;
import cn.springmvc.model.ClassLists;
import cn.springmvc.service.StudentService;

/**
 */
public class StudentServiceImpl implements StudentService{

	@Override
	public List<ClassLists> getClassList(String id) {
		// TODO Auto-generated method stub
		return StudentBusiness.getClassList(id);
	}
	
}
