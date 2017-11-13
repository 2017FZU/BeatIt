package cn.springmvc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.springmvc.business.StudentBusiness;
import cn.springmvc.json.JackJsonUtils;
import cn.springmvc.json.ListObject;
import cn.springmvc.json.ResponseUtils;
import cn.springmvc.json.StatusCode;
import cn.springmvc.model.Classes;


@Controller
public class StudentServlet {
	@RequestMapping("/getClassList")
	public void getClassList(HttpServletRequest request, HttpServletResponse response) {
		
	    String sid = request.getParameter("sid");
		System.out.println(sid);
	    List<Classes> data =  StudentBusiness.getClassList(sid);
		ListObject listObject = new ListObject();
		listObject.setData(data);
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("success");
		
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	@RequestMapping("/getClassDetailInfo")
	public void getClassDetailInfo(HttpServletRequest request, HttpServletResponse response) {
	    String sid = request.getParameter("cid");
	    List<Classes> data =  StudentBusiness.getClassList(sid);
		ListObject listObject = new ListObject();
		listObject.setData(data);
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("success");
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	
}
