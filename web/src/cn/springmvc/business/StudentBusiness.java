package cn.springmvc.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.springmvc.MD5Util;
import cn.springmvc.json.*;
import cn.springmvc.model.*;

/**
 */
public class StudentBusiness {

	/**
	 * 
	 * @return
	 */
	
	public static List<ClassLists> getClassList(String id) {
		List<ClassLists> list = new ArrayList<ClassLists>();
		String sql = null;
		DBHelper db = null;
		sql = "select * from class where tid = " + id;
		db = new DBHelper(sql);
		ResultSet res = null;
		try {
			res = db.pst.executeQuery();
			while(res.next()) {
				int cid = res.getInt("cid");
				String cname = res.getString("cname");
				ClassLists classes = new ClassLists();
				classes.setCid(cid);
				classes.setCname(cname);
				classes.setEmg("http://qr.topscan.com/api.php?text="+cid);
				list.add(classes);
			}
			res.close();
			db.close();//
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		return list;
	}
	
	public static Teachers getTeacher(String id) {
		Teachers list = new Teachers();
		String sql = null;
		DBHelper db = null;
		sql = "select * from teacher";
		db = new DBHelper(sql);
		ResultSet res = null;
		try {
			res = db.pst.executeQuery();
			while(res.next()) {
				int tid = res.getInt("tid");
				String tname = res.getString("tname");
				Teachers teachers = new Teachers();
				teachers.setTid(tid);
				teachers.setTname(tname);
				
			}
			res.close();
			db.close();//
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<NoticeLists> getNoticeList(String id) {
		List<NoticeLists> list = new ArrayList<NoticeLists>();
		String sql = null;
		DBHelper db = null;
		sql = "select * from classnotice where cid = "+id;
		db = new DBHelper(sql);
		ResultSet res = null;
		try {
			res = db.pst.executeQuery();
			while(res.next()) {
				int cid = res.getInt("cid");
				int nid = res.getInt("nid");
				String title = res.getString("title");
				String content = res.getString("content");
				String time = res.getString("time");
				NoticeLists notice = new NoticeLists();
				notice.setTitle(title);
				notice.setCid(cid);
				notice.setContent(content);
				notice.setTime(time);
				notice.setNid(nid);
				list.add(notice);
			}
			res.close();
			db.close();//
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		return list;
	}
	
	public static StatusObject  getaddNotice(String cid,String title,String content) {
		List<NoticeLists> list = new ArrayList<NoticeLists>();
		String sql = null;
		DBHelper db = null;
		sql = "insert into classnotice (cid, title, content) values("+cid+","+title+","+content+")";
		db = new DBHelper(sql);
		StatusObject status = new StatusObject(); 		
		status.setStatus(0);
		int res = 0;
		try {
			res = db.pst.executeUpdate();
			if(res > 0) {
				status.setStatus(1);
			}
			db.close();//
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		return status;
	}
	
	public static StatusObject  getaddHomeWork(String cid, String title,String content,String deadline,String online) {
		String sql = null;
		DBHelper db = null;
		sql = "insert into homework (cid, title, content, deadline, online) values("+cid+","+title+","+content+","+deadline+","+online+")";
		db = new DBHelper(sql);
		StatusObject status = new StatusObject(); 		
		status.setStatus(0);
		int res = 0;
		try {
			res = db.pst.executeUpdate();
			if(res > 0) {
				status.setStatus(1);
			}
			db.close();//
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		return status;
	}
	
	public static StatusObject  getCreateClass(String tid,String cname) {
		List<ClassLists> list = new ArrayList<ClassLists>();
		String sql = null;
		DBHelper db = null;
		sql = "insert into class (tid, cname) values("+tid+","+cname+")";
		db = new DBHelper(sql);
		StatusObject status = new StatusObject(); 		
		status.setStatus(0);
		int res = 0;
		try {
			res = db.pst.executeUpdate();
			if(res > 0) {
				status.setStatus(1);
			}
			db.close();//
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		return status;
	}
	public static List<WorkLists> getHomeWorkList(String id) {
		List<WorkLists> list = new ArrayList<WorkLists>();
		String sql = null;
		DBHelper db = null;
		sql = "select * from homework where cid = "+id;
		db = new DBHelper(sql);
		ResultSet res = null;
		try {
			res = db.pst.executeQuery();
			while(res.next()) {
				int cid = res.getInt("cid");
				int wid = res.getInt("wid");
				String title = res.getString("title");
				int online = res.getInt("online");
				String deadline = res.getString("deadline");
				String content = res.getString("content");
				int status = res.getInt("status");
				WorkLists worklists = new WorkLists();
				worklists.setCid(cid);
				worklists.setWid(wid);
				worklists.setTitle(title);
				worklists.setOnline(online);
				worklists.setContent(content);
				worklists.setDeadline(deadline);
				worklists.setStatus(status);
				list.add(worklists);		
			}
			res.close();
			db.close();//
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		return list;
		
	}
	
	public static StatusObject  getDelCourseFile(String cfid) {

		String sql = null;
		DBHelper db = null;
		sql = "delete from  coursefile where cfid = "+cfid;
		db = new DBHelper(sql);
		StatusObject status = new StatusObject(); 		
		status.setStatus(0);
		int res = 0;
		try {
			res = db.pst.executeUpdate();
			if(res > 0) {
				status.setStatus(1);
			}
			db.close();//
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		return status;
	}

	public static List<CourseLists> getCourse(String id) {
		List<CourseLists> list = new ArrayList<CourseLists>();
		String sql = null;
		DBHelper db = null;
		sql = "select * from coursefile where cid = "+id;
		db = new DBHelper(sql);
		ResultSet res = null;
		try {
			res = db.pst.executeQuery();
			while(res.next()) {
				int cfid = res.getInt("cfid");
				String cfname = res.getString("cfname");
				String url = res.getString("file");
				CourseLists course = new CourseLists();
				course.setUrl(url);;
				course.setCfname(cfname);
				course.setCfid(cfid);
				list.add(course);
			}
			res.close();
			db.close();//
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		return list;
	}
	public static List<Studenthomeworks> getAllStudent(String id) {
		List<Studenthomeworks> list = new ArrayList<Studenthomeworks>();
		String sql = null;
		String sql1 = null;
		DBHelper db = null;
		DBHelper db1 = null;
		sql = "select student.sid, student.stuno,  student.sname, sum(score) as score, "+ "sum(isshow)  as count , count(*) as scoreshow from student,"+ " homeworkstudent, homework where student.sid =  homeworkstudent.sid "+ " and homework.wid = homeworkstudent.wid and homework.cid = "+ id+" group by student.sid";
		sql1 = "select count(distinct wid) as count from homework where cid = "+ id ;
		db = new DBHelper(sql);
		db1 = new DBHelper(sql1);
		ResultSet res = null;
		ResultSet res1 = null;
		String c = null;;
		try {
			
			res = db.pst.executeQuery();
			res1 = db1.pst.executeQuery();
            
			while(res1.next()){
			   c = res1.getString("count");
			}
			while(res.next()) {
				System.out.println("wawwaaaaaaaawww");
				int sid = res.getInt("sid");
				String sno = res.getString("stuno");
				String sname = res.getString("sname");
				
				int AllStar =res.getInt("score");
				int count = res.getInt("count");
				String ScoreShow = res.getString("scoreshow");
				
				String scoreshow = ScoreShow + "/" +c;
				System.out.println(sname+" "+sno+" "+scoreshow);
				Studenthomeworks stuhomework = new Studenthomeworks();
				stuhomework.setSid(sid);
				stuhomework.setSname(sname);
				stuhomework.setSno(sno);
				stuhomework.setScoreShow(scoreshow);
				stuhomework.setAllStar(AllStar);
				stuhomework.setCount(count);
				list.add(stuhomework);
				//					
			}			
			
			res.close();
			db.close();//
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		
		return list;
		
	}
	public static int SaveCourseFile(String cid, String url, String oname) {
		String sql = null;
		DBHelper db = null;
		sql = "insert into coursefile (cid, file, cfname) values("+cid+",'"+url+"','"+oname+"')";
		db = new DBHelper(sql);
		int res = 0;
		try {
			res = db.pst.executeUpdate();
			if(res <= 0) {
				return 0;
			}
			db.close();
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		return 1;
	}

	public static StatusObject UpdateNotice(String nid, String title, String content, int op) {
		StatusObject status = new StatusObject();
		String sql = null;
		DBHelper db = null;
		if(op == 1) {
			sql = "update classnotice set title = "+title+", content = "+content+", time=CURRENT_TIMESTAMP where nid = "+nid; 
		} else {
			sql = "delete from classnotice where nid = "+nid;
		}
		db = new DBHelper(sql);
		int res = 0;
		status.setStatus(1);
		try {
			res = db.pst.executeUpdate();
			if(res <= 0) {
				status.setStatus(0);
			}
			db.close();
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		return status;
	}

	public static List<StuHomeworkObject> getStudentHomeWork(String wid) {
		List<StuHomeworkObject> list =new ArrayList<StuHomeworkObject>();
	//	HistoryObject person= new HistoryObject();
		String sql = null;
		DBHelper db = null;
		DBHelper db2 = null;
		sql = "select student.sid,title, stuno, sname, score, isview from student,homeworkstudent,homework where student.sid = homeworkstudent.sid and homework.wid = homeworkstudent.wid and homework.wid = "+wid;
		db = new DBHelper(sql);
		ResultSet res = null;
		ResultSet res2 = null;
		try {
			res = db.pst.executeQuery();
			while(res.next()) {
				StuHomeworkObject person = new StuHomeworkObject();
				String title = res.getString("title");
				String sno = res.getString("stuno");
				String sname = res.getString("sname");
				int sid = res.getInt("sid");
				int score = res.getInt("score");
				int isview = res.getInt("isview");
				person.setTitle(title);
				person.setSno(sno);
				person.setSname(sname);
				person.setScore(score);
				person.setIsview(isview);
				person.setSid(sid);
				List<WorkimgObject>workimg = new ArrayList<WorkimgObject>();
				
				sql = "select  wkid,iname, file from workimg where  wid = "+ wid ;
				db2 = new DBHelper(sql);
				try {
					res2 = db2.pst.executeQuery();
					while(res2.next()) {
						WorkimgObject w = new WorkimgObject();
						int wkid = res2.getInt("wkid");
						String iname = res2.getString("iname");
						String url = res2.getString("file");
						w.setWkid(wkid);
						w.setIname(iname);
						w.setUrl(url);
						workimg.add(w);
					}
				} catch (SQLException e) {
					// 查询失败
					e.printStackTrace();
				} 
				person.setWorkimg(workimg);
				list.add(person);
			}
			res.close();
			db.close();//
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		return list;
	}

	public static int SaveExplainFile(String wid, String url, String oname) {
		String sql = null;
		DBHelper db = null;
		System.out.println(wid+' '+url+' '+oname);
		sql = "insert into homeworkexplain(wid, efname, file) values("+wid+",'"+oname+"','"+url+"')";
		System.out.println(sql);
		db = new DBHelper(sql);
		int res = 0;
		try {
			res = db.pst.executeUpdate();
			if(res > 0) {
				res=1;
			}
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		db.close();
		return res;
	}

	public static StatusObject CommentHomeWork(String sid, String wid, String score, String comment, String isshow) {
		String sql = null;
		DBHelper db = null;
		StatusObject s = new StatusObject();
		sql = "update homeworkstudent set score = "+score+",comment='"+comment+"',isshow="+isshow+",isview=1 where wid="+wid+" and sid="+sid+";";
		System.out.print(sql);
		db = new DBHelper(sql);
		int res = 0;
		s.setStatus(0);
		try {
			res = db.pst.executeUpdate();
			System.out.print("res => "+res);
			
			if(res > 0) {
				s.setStatus(1);
			}
			db.close();
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		return s;
	}

	public static User CheckRegister(String phone, String psw, String tname, String email) {
		User person = new User();
		person.setError("no error");
		String sql = null;
		DBHelper db = null;
		sql = "insert into teacher (temail, tname, psw, tel) values('"+email+"', '"+tname+"','"+MD5Util.getMD5(psw)+"',"+phone+")";
		db = new DBHelper(sql);
		int res = 0;
		try {
			res = db.pst.executeUpdate();
			if(res <= 0) {
				person.setError("网络异常，操作失败");
				person.setSuccess(false);
			} else {
				person = CheckLogin(phone, psw);
			}
			db.close();//
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		return person;
	}

	public static User CheckLogin(String phone, String psw) {
		User person = new User();
		person.setSuccess(false);
		person.setError("");
		psw = MD5Util.getMD5(psw);
		System.out.println("密码: "+psw);
		String sql = null;
		DBHelper db = null;
		sql = "select * from teacher where tel = "+phone+" AND psw = '"+psw+"'";
		db = new DBHelper(sql);
		ResultSet res = null;
		try {
			res = db.pst.executeQuery();
			while(res.next()) {
				String email = res.getString("temail");
				String tname = res.getString("tname");
				String img = res.getString("img");
				String tel = res.getString("tel");
				int tid = res.getInt("tid");
				person.setImg(img);
				person.setTid(tid);
				person.setTname(tname);
				person.setTel(tel);
				person.setEmail(email);
				person.setSuccess(true);
			}
			if(person.isSuccess() == false) {
				person.setError("帐号不存在或密码错误");
			}
			res.close();
			db.close();//
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		return person;
	}

}
