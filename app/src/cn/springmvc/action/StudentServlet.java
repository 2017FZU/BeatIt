package cn.springmvc.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.springmvc.business.StudentBusiness;
import cn.springmvc.json.*;
import cn.springmvc.model.*;

@Controller
public class StudentServlet {
	@RequestMapping("/getClassList") // 获取课堂列表
	public void getClassList(HttpServletRequest request, HttpServletResponse response) {
		
		// 获取参数
	    String sid = request.getParameter("sid");
		
	    // 处理
	    List<Classes> ClassList =  StudentBusiness.getClassList(sid);
	    
	    ClassListObject data = new ClassListObject();
	    
	    data.setClassList(ClassList);
	    
	    ListObject listObject = new ListObject();
		
	    listObject.setData(data);
		
	    listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("success");
		
		
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	@RequestMapping("/getHomeWorkHistory") // 获取课堂列表
	public void getHomeWorkHistory(HttpServletRequest request, HttpServletResponse response) {
		
		// 获取参数
	    String sid = request.getParameter("sid");
	    String wid = request.getParameter("wid");
		
	    // 处理
	    HistoryObject data =  StudentBusiness.getHomeWorkHistory(sid, wid);
	   
	    
	    ListObject listObject = new ListObject();
		
	    listObject.setData(data);
		
	    listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("success");
		
		
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	@RequestMapping("/DeleteWorkImg") // 获取课堂列表
	public void DeleteWorkImg(HttpServletRequest request, HttpServletResponse response) {
		
		// 获取参数
	    String wkid = request.getParameter("wkid");
		
	    // 处理
	    StatusObject s =  StudentBusiness.DeleteWorkImg(wkid);
	   
	    ListObject listObject = new ListObject();		
	    listObject.setData(s);
		
	    listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("success");
		
		
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	@RequestMapping("/getSelfFile") // 获取课堂列表
	public void getSelfFile(HttpServletRequest request, HttpServletResponse response) {
		
		// 获取参数
	    String sid = request.getParameter("sid");
	    String cid = request.getParameter("cid");
	    
		
	    // 处理
	    List<SelfFile> selfFile =  StudentBusiness.getSelfFile(sid, cid);
	    
	    SelfFileObject data = new SelfFileObject();
	    
	    data.setSelfFile(selfFile);
	    
	    ListObject listObject = new ListObject();
		
	    listObject.setData(data);
		
	    listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("success");
		
		
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	@RequestMapping("/getHomeWorkEx") // 获取课堂列表
	public void getHomeWorkEx(HttpServletRequest request, HttpServletResponse response) {
		
		// 获取参数
	    String wid = request.getParameter("wid");
		
	    // 处理
	    List<ExplainFile> ClassList =  StudentBusiness.getHomeWorkEx(wid);
	    
	    ExFileObject data = new ExFileObject();
	    
	    data.setExFile(ClassList);
	    
	    ListObject listObject = new ListObject();
		
	    listObject.setData(data);
		
	    listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("success");
		
		
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	@RequestMapping("/getClassFile") // 获取课堂列表
	public void getClassFile(HttpServletRequest request, HttpServletResponse response) {
		
		// 获取参数
	    String cid = request.getParameter("cid");
		
	    // 处理
	    List<CourseFile> CourseFile =  StudentBusiness.getClassFile(cid);
	    
	    CourseFileObject data = new CourseFileObject();
	    
	    data.setCourseFile(CourseFile);
	    
	    ListObject listObject = new ListObject();
		
	    listObject.setData(data);
		
	    listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("success");
		
		
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	@RequestMapping("/getClassInfo") // 获取课堂简要信息
	public void getClassInfo(HttpServletRequest request, HttpServletResponse response) {
		// 获取参数
	    String cid = request.getParameter("cid");
	    // 处理
	    Classes data =  StudentBusiness.getClassInfo(cid);
	    ListObject listObject = new ListObject();
	    listObject.setData(data);
	    listObject.setCode(StatusCode.CODE_SUCCESS);
	    listObject.setMsg("success");
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	
	@RequestMapping("/addIntoClass") // 加入课堂
	public void addIntoClass(HttpServletRequest request, HttpServletResponse response) {
		
		// 获取参数
	    String cid = request.getParameter("cid");
	    String sid = request.getParameter("sid");
	    // 处理
	    StatusObject data =  StudentBusiness.addIntoClass(cid, sid);
	    ListObject listObject = new ListObject();
	    listObject.setData(data);
	    listObject.setCode(StatusCode.CODE_SUCCESS);
	    listObject.setMsg("success");
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	

	@RequestMapping("/getNoticeList") // 加入课堂
	public void getNoticeList(HttpServletRequest request, HttpServletResponse response) {
		
		// 获取参数
	    String cid = request.getParameter("cid");
	    // 处理
	    List<Notice> list =  StudentBusiness.getNoticeList(cid);
	    NoticeListObject data = new NoticeListObject();
	    data.setNoticeList(list);
	    
	    ListObject listObject = new ListObject();
	    listObject.setData(data);
	    listObject.setCode(StatusCode.CODE_SUCCESS);
	    listObject.setMsg("success");
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	
	@RequestMapping("/getClassDetailInfo") // 加入课堂
	public void getClassDetailInfo(HttpServletRequest request, HttpServletResponse response) {
		
		// 获取参数
	    String cid = request.getParameter("cid");
	    // 处理
	    ClassDetailObject data =  StudentBusiness.getClassDetailInfo(cid);
	    ListObject listObject = new ListObject();
	    listObject.setData(data);
	    listObject.setCode(StatusCode.CODE_SUCCESS);
	    listObject.setMsg("success");
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	@RequestMapping("/getClassHomeWorkList") // 加入课堂
	public void getClassHomeWorkList(HttpServletRequest request, HttpServletResponse response) {
		
		// 获取参数
	    String cid = request.getParameter("cid");
	    // 处理
	    List<HomeWork> res =  StudentBusiness.getClassHomeWorkList(cid);
	    HomeWorkListObject data = new HomeWorkListObject();
	    data.setHomeWork(res);
	    ListObject listObject = new ListObject();
	    listObject.setData(data);
	    listObject.setCode(StatusCode.CODE_SUCCESS);
	    listObject.setMsg("success");
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	
	@RequestMapping("/getShowList") // 加入课堂
	public void getShowList(HttpServletRequest request, HttpServletResponse response) {
		
		// 获取参数
	    String wid = request.getParameter("wid");
	    // 处理
	    System.out.println(wid);
	    List<Show> res =  StudentBusiness.getShowList(wid);
	    ShowListObject data = new ShowListObject();
	    data.setShowList(res);
	    
	    
	    
	    ListObject listObject = new ListObject();
	    listObject.setData(data);
	    listObject.setCode(StatusCode.CODE_SUCCESS);
	    listObject.setMsg("success");
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}

	 @RequestMapping(value="/UploadHomeWork",method=RequestMethod.POST)
     public void UploadHomeWork(HttpServletRequest request,
            @RequestParam("file") CommonsMultipartFile[] file, HttpServletResponse response) throws Exception {
        //如果文件不为空，写入上传路径
		 int len = file.length;
		 StatusObject s = new StatusObject();
		 s.setStatus(0);
		 String sid = request.getParameter("sid");
		 String wid = request.getParameter("wid");
		 if(len > 0) {
			 s.setStatus(StudentBusiness.submitHomework(sid, wid)); 
			 if(s.getStatus() == 1) {
				 for(int i=0; i<len; ++i) {
					 	CommonsMultipartFile  item = file[i];
			            //上传文件路径
			        	System.out.println("fileName："+item.getOriginalFilename());
			        	String path="C:/source/workimg/"+sid+"/";
			        	String oname = item.getOriginalFilename();
			        	String name = new Date().getTime()+"_"+item.getOriginalFilename();
			        	String url = "http://111.231.190.23/download/workimg/"+sid+"/"+name;
			            File filepath = new File(path);
			            while(!filepath.exists()) {
			            	filepath.mkdir();  
			            }
			            //将上传文件保存到一个目标文件当中
			            File fi = new File(path+name);
			            if(StudentBusiness.SaveWorkImg(wid, sid, url, oname) == 0) {
			            	s.setStatus(0);
			            	break;
			            }
			            item.transferTo(fi);
			         
			            //System.out.println("方法二的运行时间：" + path+new Date().getTime()+"_"+item.getOriginalFilename());
				 }
			 }
	 	 }
		 ListObject listObject = new ListObject();
		 listObject.setData(s);
	     listObject.setCode(StatusCode.CODE_SUCCESS);
	     listObject.setMsg("success");
		 ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
     }
	 @RequestMapping(value="/UploadSelfFile",method=RequestMethod.POST)
     public void UploadSelfFile(HttpServletRequest request,
            @RequestParam("file") CommonsMultipartFile[] file, HttpServletResponse response) throws Exception {
        //如果文件不为空，写入上传路径
		 int len = file.length;
		 StatusObject s = new StatusObject();
		 String sid = request.getParameter("sid");
		 String cid = request.getParameter("cid");
		 if(len > 0) {
			 s.setStatus(1);
			 for(int i=0; i<len; ++i) {
				 	CommonsMultipartFile  item = file[i];
		            //上传文件路径
		        	String path="C:/source/student/"+sid+"/";
		        	String oname = item.getOriginalFilename();
		        	String name = new Date().getTime()+"_"+item.getOriginalFilename();
		        	String url = "http://111.231.190.23/download/student/"+sid+"/"+name;
		        	System.out.println("a");
		            File filepath = new File(path);
		            System.out.println("b");
		            while(!filepath.exists()) {
		            	filepath.mkdir();  
		            }
		            
		            //将上传文件保存到一个目标文件当中
		            File fi = new File(path+name);
		            System.out.println("start");
		            if(StudentBusiness.SaveSelfFile(cid, sid, url, oname) == 0) {
		            	s.setStatus(0);
		            	break;
		            }
		            System.out.println("Over");
		            
		            item.transferTo(fi);
			 }
	 	 }
		 ListObject listObject = new ListObject();
		 listObject.setData(s);
	     listObject.setCode(StatusCode.CODE_SUCCESS);
	     listObject.setMsg("success");
		 ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
     }
}
