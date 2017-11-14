package cn.springmvc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.springmvc.business.StudentBusiness;
import cn.springmvc.json.Createlll;
import cn.springmvc.json.JackJsonUtils;
import cn.springmvc.json.ListObject;
import cn.springmvc.json.ResponseUtils;
import cn.springmvc.json.StatusCode;
import cn.springmvc.model.Students;

@Controller
public class StudentServlet {


	@RequestMapping("/getAllStudent")
	public void getAllStudent(HttpServletRequest request, HttpServletResponse response) {
		
	    String username = request.getParameter("sid");
		//List<Students> list = StudentBusiness.getAllStudents();
		Createlll a = new Createlll();
		ListObject listObject = new ListObject();
		
		listObject.setdata(a);
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg(username);
		
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	
//	@RequestMapping("/getAllTeacher")
//	public void getAllTeacher(HttpServletRequest request, HttpServletResponse response) {
//		
//	    String username = request.getParameter("tid");
//		List<Teachers> list = TeacherBusiness.getAllTeachers();
//		
//		ListObject listObject = new ListObject();
//		
//		listObject.setItemes(list);
//		listObject.setCode(StatusCode.CODE_SUCCESS);
//		listObject.setMsg(username);
//		
//		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
//		
//	}
//	
//	@RequestMapping("/getClassList")
//	public void getClassList(HttpServletRequest request, HttpServletResponse response) {
//		
//	    String username = request.getParameter("cid");
//		List<ClassLists> list = ClassListBusiness.getAllClassLists();
//		
//		ListObject listObject = new ListObject();
//		
//		listObject.setItemes(list);
//		listObject.setCode(StatusCode.CODE_SUCCESS);
//		listObject.setMsg(username);
//		
//		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
//		
//	}
	
//	@RequestMapping("/getAllStudent.dbo")
//	public ModelAndView getAllStudent(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap) {
//		List<Students> list = StudentBusiness.getAllStudents();
//		modelMap.addAttribute("list", list);
//		return new ModelAndView("success",modelMap);
//	}

}
