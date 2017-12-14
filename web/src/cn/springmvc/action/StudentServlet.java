package cn.springmvc.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.springmvc.business.StudentBusiness;
import cn.springmvc.json.AllStudentObject;
import cn.springmvc.json.CreateClassList;
import cn.springmvc.json.CreateCourseList;
import cn.springmvc.json.CreateNoticeList;
import cn.springmvc.json.CreateWorkList;
import cn.springmvc.json.JackJsonUtils;
import cn.springmvc.json.ListObject;
import cn.springmvc.json.ResponseUtils;
import cn.springmvc.json.StatusCode;
import cn.springmvc.json.StatusObject;
import cn.springmvc.json.StuHomeworkObject;
import cn.springmvc.json.WorkObject;
import cn.springmvc.model.ClassLists;
import cn.springmvc.model.CourseLists;
import cn.springmvc.model.NoticeLists;
import cn.springmvc.model.Studenthomeworks;
import cn.springmvc.model.Teachers;
import cn.springmvc.model.User;
import cn.springmvc.model.WorkLists;

@Controller
public class StudentServlet {
	@RequestMapping("/userLogin") // 登录
	public void userLogin(HttpServletRequest request, HttpServletResponse response) {
		String phone = request.getParameter("phone");
		String psw = request.getParameter("psw");
		User person = StudentBusiness.CheckLogin(phone, psw);
		if(person.isSuccess() == true) {
			HttpSession session = request.getSession();
			person.setSession(session.getId());
		}		
	    ListObject listObject = new ListObject();
	    listObject.setdata(person);
	    listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("success");
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	
	@RequestMapping("/register") // 注册
	public void register(HttpServletRequest request, HttpServletResponse response) {
		String phone = request.getParameter("phone");
		String psw = request.getParameter("psw");
		String vcode = request.getParameter("vcode");
		String tname = request.getParameter("tname");
		String email = request.getParameter("temail");

		User person = StudentBusiness.CheckRegister(phone, psw, tname, email);
		
//		HttpSession session = request.getSession();
//		String sessionid =session.getId();
//		HttpSession sess = myc.getSession(sessionid);  
		if(person.isSuccess() == true) {
			HttpSession session = request.getSession();
			person.setSession(session.getId());
		}		
		ListObject listObject = new ListObject();
	    listObject.setdata(person);
	    listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("success");
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	
	@RequestMapping("/CommentHomeWork")
	public void CommentHomeWork(HttpServletRequest request, HttpServletResponse response) {
		String sid = request.getParameter("sid");
		String wid = request.getParameter("wid");
		String score = request.getParameter("score");
		String comment = request.getParameter("comment");
		String isshow = request.getParameter("isshow");
		StatusObject s = StudentBusiness.CommentHomeWork(sid, wid, score, comment, isshow);
		ListObject listObject = new ListObject();
		listObject.setdata(s);
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("success");
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	@RequestMapping("/Test")
	public void Test(HttpServletRequest request, HttpServletResponse response) {
		
		String Uid = "Kaloneme1";
		//接口安全秘钥
		String Key = "e385a8c673e18c5e129d";
		String smsMob = "17720806830";
		//短信内容
		String smsText = "验证码：8888";
		System.out.println("start");
		HttpClientUtil client = HttpClientUtil.getInstance();
		System.out.println("it is ok");
		//GBK发送
		int resultGbk = client.sendMsgGbk(Uid, Key, smsText, smsMob );
		if(resultGbk>0){
			System.out.println("GBK成功发送条数=="+resultGbk);
		}else{
			System.out.println(client.getErrorMsg(resultGbk));
		}
		StatusObject s = new StatusObject();
		s.setStatus(1);
		ListObject listObject = new ListObject();
		listObject.setdata(s);
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("success");
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	@RequestMapping("/UpdateNotice")
	public void UpdateNotice(HttpServletRequest request, HttpServletResponse response) {
		String nid = request.getParameter("nid");
//		String tid = request.getParameter("tid");
		int op = Integer.parseInt( request.getParameter("op"));
		StatusObject s = new StatusObject();
		String content  = null;
		String title = null;
		if(op == 1) {
			title = request.getParameter("title");
			content = request.getParameter("content");	
		}
		s = StudentBusiness.UpdateNotice(nid, title, content, op);
		ListObject listObject = new ListObject();
		listObject.setdata(s);
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("success");
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	@RequestMapping("/getAllTeacher")
	public void getAllTeacher(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("tid");
		Teachers data = StudentBusiness.getTeacher(username);
	   // CreateClassList a = new CreateClassList();
		//CreateTeacher data = new CreateTeacher();
		//data.setTeacher(data);
		ListObject listObject = new ListObject();
		listObject.setdata(data);
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("success");
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	
	@RequestMapping("/getClassList")
	public void getClassList(HttpServletRequest request, HttpServletResponse response) {
		
	    String username = request.getParameter("tid");
		List<ClassLists> list = StudentBusiness.getClassList(username);
	   // CreateClassList a = new CreateClassList();
		CreateClassList data = new CreateClassList();
		data.setClassList(list);
		ListObject listObject = new ListObject();
		
		listObject.setdata(data);
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("success");
		
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
		
	}
	
	@RequestMapping("/getNoticeList")
	public void getNoticeList(HttpServletRequest request, HttpServletResponse response) {
		
		String username = request.getParameter("cid");
		List<NoticeLists> list = StudentBusiness.getNoticeList(username);
	   // CreateClassList a = new CreateClassList();
		CreateNoticeList data = new CreateNoticeList();
		data.setNoticeList(list);
		ListObject listObject = new ListObject();
		
		listObject.setdata(data);
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("success");
		
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	
	@RequestMapping("/addNotice")
	public void addNotice(HttpServletRequest request, HttpServletResponse response) {
		
		String cid = request.getParameter("cid");
		String title = request.getParameter("title");
	    String content = request.getParameter("content");
	    StatusObject data =  StudentBusiness.getaddNotice(cid, title, content);
	    ListObject listObject = new ListObject();
	    listObject.setdata(data);
	    listObject.setCode(StatusCode.CODE_SUCCESS);
	    listObject.setMsg("success");
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	
	@RequestMapping("/addHomeWork")
	public void addHomeWork(HttpServletRequest request, HttpServletResponse response) {
		String cid = request.getParameter("cid");
		String title = request.getParameter("title");
	    String content = request.getParameter("content");
	    String deadline = request.getParameter("deadline");
	    String online = request.getParameter("online");
	    StatusObject data =  StudentBusiness.getaddHomeWork(cid, title, content, deadline, online);
	    ListObject listObject = new ListObject();
	    listObject.setdata(data);
	    listObject.setCode(StatusCode.CODE_SUCCESS);
	    listObject.setMsg("success");
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	
	@RequestMapping("/CreateClass")
	public void CreateClass(HttpServletRequest request, HttpServletResponse response) {
		
		String tid = request.getParameter("tid");
	    String cname = request.getParameter("cname");
	    StatusObject data =  StudentBusiness.getCreateClass(tid, cname);
	    ListObject listObject = new ListObject();
	    listObject.setdata(data);
	    listObject.setCode(StatusCode.CODE_SUCCESS);
	    listObject.setMsg("success");
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	
	@RequestMapping("/getHomeWorkList")
	public void getHomeWorkList(HttpServletRequest request, HttpServletResponse response) {
		
		String username = request.getParameter("cid");
		List<WorkLists> list = StudentBusiness.getHomeWorkList(username);
	   // CreateClassList a = new CreateClassList();
		CreateWorkList data = new CreateWorkList();
		data.sethomeWorkList(list);
		ListObject listObject = new ListObject();
		
		listObject.setdata(data);
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("success");
		
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	
	@RequestMapping("/DelCourseFile")
	public void DelCourseFile(HttpServletRequest request, HttpServletResponse response) {
		
		String cfid = request.getParameter("cfid");
		StatusObject data =  StudentBusiness.getDelCourseFile(cfid);
	    ListObject listObject = new ListObject();
	    listObject.setdata(data);
	    listObject.setCode(StatusCode.CODE_SUCCESS);
	    listObject.setMsg("success");
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	
	@RequestMapping("/getCourseList")
	public void getCourseList(HttpServletRequest request, HttpServletResponse response) {
		
		String username = request.getParameter("cid");
		List<CourseLists> list = StudentBusiness.getCourse(username);
	   // CreateClassList a = new CreateClassList();
		CreateCourseList data = new CreateCourseList();
		data.setCourseList(list);
		ListObject listObject = new ListObject();
		
		listObject.setdata(data);
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("success");
		
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	
	@RequestMapping("/getAllStudent")
	public void getAllStudent(HttpServletRequest request, HttpServletResponse response) {
		
		String username = request.getParameter("cid");
		List<Studenthomeworks> list = StudentBusiness.getAllStudent(username);
	   // CreateClassList a = new CreateClassList();
		AllStudentObject data = new AllStudentObject();
		data.setAllStudent(list);
		ListObject listObject = new ListObject();
		
		listObject.setdata(data);
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("success");
		
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	@RequestMapping(value="/UploadCourseFile",method=RequestMethod.POST)
     public void UploadCourseFile(HttpServletRequest request,
            @RequestParam("file") CommonsMultipartFile[] file, HttpServletResponse response) throws Exception {
        //如果文件不为空，写入上传路径
		 int len = file.length;
		 StatusObject s = new StatusObject();
		 String tid = request.getParameter("tid");
		 String cid = request.getParameter("cid");
		 System.out.print(tid + " "+ cid);
		 if(len > 0) {
			 s.setStatus(1);
			 for(int i=0; i<len; ++i) {
				 	CommonsMultipartFile  item = file[i];
		            //上传文件路径
		        	String path="C:/source/teacher/"+tid+"/"+cid+"/";
		        	String oname = item.getOriginalFilename();
		        	String name = new Date().getTime()+"_"+item.getOriginalFilename();
		        	String url = "http://111.231.190.23/download/teacher/"+tid+"/"+cid+"/"+name;
		        	System.out.println(url);
		        	System.out.println(path);
		        	System.out.println("a");
		        	File filepath = new File(path);
		        	System.out.println("b");
		        	while(!filepath.exists()) {
		            	filepath.mkdirs();
		            	System.out.println("c");
			        	
		            }
		        	System.out.println("d");
		        	
		            //将上传文件保存到一个目标文件当中
		            File fi = new File(path+name);
		            if(StudentBusiness.SaveCourseFile(cid,  url, oname) == 0) {
		            	s.setStatus(0);
		            	break;
		            }
		            item.transferTo(fi);
			 }
	 	 }
		 ListObject listObject = new ListObject();
		 listObject.setdata(s);
	     listObject.setCode(StatusCode.CODE_SUCCESS);
	     listObject.setMsg("success");
		 ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
     }
	@RequestMapping("/getStudentHomeWork") // 获取学生作业
 	public void getStudentHomeWork(HttpServletRequest request, HttpServletResponse response) {
 		// 获取参数
 	    String wid = request.getParameter("wid");
 	    // 处理
 	    List<StuHomeworkObject> list = StudentBusiness.getStudentHomeWork(wid);
 		   // CreateClassList a = new CreateClassList();
 	    WorkObject data = new WorkObject();
 		data.setWork(list);
 		ListObject listObject = new ListObject();
 		listObject.setdata(data);
 	    listObject.setCode(StatusCode.CODE_SUCCESS);
 		listObject.setMsg("success");
 		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
 	}
	@RequestMapping(value="/upExplainFile",method=RequestMethod.POST)
    public void upExplainFile(HttpServletRequest request,
           @RequestParam("file") CommonsMultipartFile[] file, HttpServletResponse response) throws Exception {
       //如果文件不为空，写入上传路径
		 int len = file.length;
		 StatusObject s = new StatusObject();
		 String wid = request.getParameter("wid");
		 if(len > 0) {
			 s.setStatus(1);
			 for(int i=0; i<len; ++i) {
				 	CommonsMultipartFile  item = file[i];
		            //上传文件路径
		        	String path="C:/source/explainfile/"+wid+"/";
		        	String oname = item.getOriginalFilename();
		        	String name = new Date().getTime()+"_"+item.getOriginalFilename();
		        	String url = "http://111.231.190.23/download/explainfile/"+wid+"/"+name;
		        	File filepath = new File(path);
		        	while(!filepath.exists()) {
		            	filepath.mkdirs();
		            }		        	
		            //将上传文件保存到一个目标文件当中
		            File fi = new File(path+name);
		            if(StudentBusiness.SaveExplainFile(wid,  url, oname) == 0) {
		            	s.setStatus(0);
		            	break;
		            }
		            item.transferTo(fi);
			 }
	 	 }
		 ListObject listObject = new ListObject();
		 listObject.setdata(s);
	     listObject.setCode(StatusCode.CODE_SUCCESS);
	     listObject.setMsg("success");
		 ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }
	
}
