package cn.springmvc.service.impl;

import java.util.List;

import cn.springmvc.business.StudentBusiness;
import cn.springmvc.model.Classes;
import cn.springmvc.service.StudentService;

/**
 */
public class StudentServiceImpl implements StudentService{

	@Override
	public List<Classes> getClassList(String sid) {
		// TODO Auto-generated method stub
		return StudentBusiness.getClassList(sid);
	}
	
}
