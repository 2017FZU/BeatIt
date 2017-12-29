package cn.springmvc.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.springmvc.ImgCompress;
import cn.springmvc.MySessionContext;
import cn.springmvc.business.StudentBusiness;
import cn.springmvc.json.ClassDetailObject;
import cn.springmvc.json.ClassListObject;
import cn.springmvc.json.CourseFileObject;
import cn.springmvc.json.ExFileObject;
import cn.springmvc.json.HistoryObject;
import cn.springmvc.json.HomeWorkListObject;
import cn.springmvc.json.JackJsonUtils;
import cn.springmvc.json.ListObject;
import cn.springmvc.json.NoticeListObject;
import cn.springmvc.json.ResponseUtils;
import cn.springmvc.json.SelfFileObject;
import cn.springmvc.json.ShowListObject;
import cn.springmvc.json.StatusCode;
import cn.springmvc.json.StatusObject;
import cn.springmvc.json.VcodeStatus;
import cn.springmvc.model.Classes;
import cn.springmvc.model.CourseFile;
import cn.springmvc.model.ExplainFile;
import cn.springmvc.model.HomeWork;
import cn.springmvc.model.Notice;
import cn.springmvc.model.SelfFile;
import cn.springmvc.model.Show;
import cn.springmvc.model.User;
import cn.springmvc.model.VcodeModify;
import net.coobird.thumbnailator.Thumbnails;


@Controller
public class StudentServlet {
	public MySessionContext myc= MySessionContext.getInstance();
	public HashMap<String,String>   ModifyVcode     =     new HashMap();     
	@RequestMapping("/getAdvice") // 获取建议
	
	public void getAdvice(HttpServletRequest request, HttpServletResponse response) {
		String sid = request.getParameter("sid");
		String adv = request.getParameter("advice");
		StatusObject status = StudentBusiness.SaveAdvice(sid, adv);
		ListObject listObject = new ListObject();
	    listObject.setData(status);
	    listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("success");
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	@RequestMapping("/getVcode") // 获取手机验证码
	public void getVcode(HttpServletRequest request, HttpServletResponse response) {
		String phone = request.getParameter("phone");
		VcodeModify data  = new VcodeModify();
		data.setSuccess(false);
		data.setError("");
		boolean isexist = StudentBusiness.ModifyQuery(phone);
		if(isexist == false) {
			data.setError("该手机号码已被使用!");
		} else {
			Random random = new Random();
			String vcode = "";
			for (int i=0;i<6;i++)  {  
			    vcode+=random.nextInt(10);  
			}  
			System.out.println("验证码:"+vcode);
			VcodeStatus res = SendVcode.Send(phone, vcode);
			data.setSuccess(res.isSuccess());
			if(res.isSuccess()) {
				ModifyVcode.put(phone, vcode);
				System.out.println("add into map\n");
			}
			data.setError(res.getError());
		}
		ListObject listObject = new ListObject();
	    listObject.setData(data);
	    listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("success");
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	@RequestMapping("/userLogin") // 获取课堂列表
	public void userLogin(HttpServletRequest request, HttpServletResponse response) {
		String phone = request.getParameter("phone");
		String psw = request.getParameter("psw");
		User person = StudentBusiness.CheckLogin(phone, psw);
		if(person.isSuccess() == true) {
			HttpSession session = request.getSession();
			person.setSession(session.getId());
		}		
	    ListObject listObject = new ListObject();
	    listObject.setData(person);
	    listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("success");
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	@RequestMapping("/register") // 获取课堂列表
	public void register(HttpServletRequest request, HttpServletResponse response) {
		String phone = request.getParameter("phone");
		String psw = request.getParameter("psw");
		String vcode = request.getParameter("vcode");
		String sname = request.getParameter("sname");
		String stuno = request.getParameter("stuno");
		User person = new User();
		person.setSuccess(false);
		System.out.println(ModifyVcode.get(phone)+" "+vcode);
		String check = ModifyVcode.get(phone);
		if(check!=null&&check.equals(vcode)) {
			person = StudentBusiness.CheckRegister(phone, psw, sname, stuno);
			if(person.isSuccess() == true) {
				HttpSession session = request.getSession();
				person.setSession(session.getId());
			}
		} else {
			person.setError("验证码错误");
		}
		HttpSession session = request.getSession();
		String sessionid =session.getId();
		HttpSession sess = myc.getSession(sessionid);  
		ListObject listObject = new ListObject();
	    listObject.setData(person);
	    listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("success");
		ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
	}
	@RequestMapping("/getClassList") // 获取课堂列表
	public void getClassList(HttpServletRequest request, HttpServletResponse response) {
	    String sid = request.getParameter("sid");		
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
	    String cid = request.getParameter("cid");

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
		 if(StudentBusiness.CheckWork(wid)) {
			 s.setStatus(0);
		 } else if(len > 0) {
			 s.setStatus(StudentBusiness.submitHomework(sid, wid)); 
			 if(s.getStatus() == 1) {
				 for(int i=0; i<len; ++i) {
					 	CommonsMultipartFile  item = file[i];
			            //上传文件路径
			        	System.out.println("fileName："+item.getOriginalFilename());
			        	String path="C:/source/workimg/"+sid+"/";
			        	String oname = item.getOriginalFilename();
			        	String name = new Date().getTime()+"_"+item.getOriginalFilename();
			        	Pattern p = Pattern.compile("(.*)(.)(.*)");
			    		Matcher m = p.matcher(name);
			    		String saveName="";
			    		String suffix="";
			    		if(m.matches()) {
			    			saveName = m.group(1);
			    			suffix = m.group(3);
			    		}
			        	String url = "http://www.winforbest.cn/download/workimg/"+sid+"/"+name;
			            File filepath = new File(path);
			            while(!filepath.exists()) {
			            	filepath.mkdirs();
			            }
			            //将上传文件保存到一个目标文件当中
			            File fi = new File(path+name);
			            if(StudentBusiness.SaveWorkImg(wid, sid, url, oname) == 0) {
			            	s.setStatus(0);
			            	break;
			            }
			            item.transferTo(fi);
			    		System.out.print("start\n");
			    		System.out.print("size: "+fi.length()/1024 );
			    		if(fi.length()/1024 > 1000) {
				    		System.out.print("size: >1000");
			    			Thumbnails.of(fi)
				    		.scale(0.4f)
				            .toFile(fi);
				    		System.out.print("end\n");	
			    		} else if(fi.length()/1024 > 400){
			    			System.out.print("size: >400");
			    			Thumbnails.of(fi)
				    		.scale(0.5f)
				            .toFile(fi);
				    		System.out.print("end\n");
			    		} else if(fi.length()/1024 > 300){
			    			System.out.print("size: >300");
			    			Thumbnails.of(fi)
				    		.scale(0.7f)
				            .toFile(fi);
				    		System.out.print("end\n");
			    		} else if(fi.length()/1024 > 200){
			    			System.out.print("size: >200");
			    			Thumbnails.of(fi)
				    		.scale(0.9f)
				            .toFile(fi);
				    		System.out.print("end\n");
			    		}
			    		
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
		        	String url = "http://www.winforbest.cn/download/student/"+sid+"/"+name;
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
