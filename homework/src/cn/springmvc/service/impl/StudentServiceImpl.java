package cn.springmvc.service.impl;

import java.util.List;

import cn.springmvc.business.StudentBusiness;
import cn.springmvc.model.Students;
import cn.springmvc.service.StudentService;

/**
 */
public class StudentServiceImpl implements StudentService{

	@Override
	public List<Students> getAllStudents() {
		// TODO Auto-generated method stub
		return StudentBusiness.getAllStudents();
	}
	
}
