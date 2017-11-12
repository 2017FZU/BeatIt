package cn.springmvc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.springmvc.business.StudentBusiness;
import cn.springmvc.json.JackJsonUtils;
import cn.springmvc.json.ListObject;
import cn.springmvc.json.ResponseUtils;
import cn.springmvc.json.StatusCode;
import cn.springmvc.model.Students;

@Controller
public class StudentServlet {


	@RequestMapping("/getAllStudent")
	public void getAllStudent(HttpServletRequest request, HttpServletResponse response) {
		
	    String username = request.getParameter("username");
		List<Students> list = StudentBusiness.getAllStudents();
		
		ListObject listObject = new ListObject();
		
		listObject.setItems(list);
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg(username);
		
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	
	@RequestMapping("/getAllStudent.dbo")
	public ModelAndView getAllStudent(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap) {
		List<Students> list = StudentBusiness.getAllStudents();
		modelMap.addAttribute("list", list);
		return new ModelAndView("success",modelMap);
	}

}
