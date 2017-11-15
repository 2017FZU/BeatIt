package cn.springmvc.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.springmvc.json.*;
import cn.springmvc.model.*;
/**
 */
public class StudentBusiness {

	// 获取课堂列表
	public static List<Classes> getClassList(String sid) {
		List<Classes> list = new ArrayList<Classes>();
		String sql = null;
		DBHelper db = null;
		sql = "select class.cid, class.cname from classstudentlist , class where classstudentlist.cid = class.cid and sid =  "+ sid;
		db = new DBHelper(sql);
		ResultSet res = null;
		try {
			res = db.pst.executeQuery();
			while(res.next()) {
				int cid = res.getInt("cid");
				String cname = res.getString("cname");
				Classes classes = new Classes();
				classes.setCid(cid);
				classes.setCname(cname);
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
	
	public static Classes getClassInfo(String cid) {
		Classes list = new Classes();
		String sql = null;
		DBHelper db = null;
		sql = "select class.cid, class.cname, teacher.tname from teacher,class where class.tid = teacher.tid and cid =  "+ cid;
		db = new DBHelper(sql);
		ResultSet res = null;
		try {
			res = db.pst.executeQuery();
			while(res.next()) {
				String cname = res.getString("cname");
				String tname = res.getString("tname");
				list.setCname(cname);
				list.setTname(tname);
				list.setCid(Integer.parseInt(cid));
			}
			res.close();
			db.close();
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		return list;
	}
	public static StatusObject addIntoClass(String cid, String sid) {
		String sql = null;
		DBHelper db = null;
		sql = "insert into classstudentlist (cid, sid) values("+cid+","+sid+")";
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
	
	public static  List<Notice> getNoticeList(String cid) {
		List<Notice> list = new  ArrayList<Notice>();
		String sql = null;
		DBHelper db = null;
		sql = "select * from classnotice where cid = "+ cid;
		db = new DBHelper(sql);
		ResultSet res = null;
		try {
			res = db.pst.executeQuery();
			while(res.next()) {
				String title = res.getString("title");
				String content = res.getString("content");
				String time = res.getString("time");
				Notice Notice = new Notice();
				Notice.setTitle(title);
				Notice.setContent(content);
				Notice.setTime(time);
				list.add(Notice);
			}
			res.close();
			db.close();//
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		return list;
	}
	public static ClassDetailObject getClassDetailInfo(String cid) {
		ClassDetailObject list = new  ClassDetailObject();
		String sql = null;
		DBHelper db = null;
		sql = "select cname, tname, temail from class, teacher where class.tid = teacher.tid and cid = "+ cid;
		db = new DBHelper(sql);
		ResultSet res = null;
		try {
			res = db.pst.executeQuery();
			while(res.next()) {
				String cname = res.getString("cname");
				String tname = res.getString("tname");
				String temail = res.getString("temail");
				list.setCname(cname);
				list.setTemail(temail);
				list.setTname(tname);
			}
			res.close();
			db.close();//
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		sql = "select * from classnotice where cid = "+cid+" order by time Desc limit 1";
		db = new DBHelper(sql);
		Notice ntc = new Notice();
		try {
			res = db.pst.executeQuery();
			while(res.next()) {
				String title = res.getString("title");
				String content = res.getString("content");
				String time = res.getString("time");
				ntc.setTitle(title);
				ntc.setContent(content);
				ntc.setTime(time);
			}
			res.close();
			db.close();//
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		list.setNewnotice(ntc);
		return list;
		
	}
	public static List<HomeWork> getClassHomeWorkList(String cid) {
		// TODO Auto-generated method stub
		List<HomeWork> list= new ArrayList<HomeWork>();
		String sql = null;
		DBHelper db = null;
		sql = "select * from homework where cid = "+ cid;
		db = new DBHelper(sql);
		ResultSet res = null;
		try {
			res = db.pst.executeQuery();
			while(res.next()) {
				int wid = res.getInt("wid");
				String title = res.getString("title");
				String content = res.getString("content");
				String deadline = res.getString("deadline");
				int online = res.getInt("online");
				int status = res.getInt("status");
				HomeWork work = new HomeWork();
				work.setWid(wid);
				work.setTitle(title);
				work.setStatus(status);
				work.setOnline(online);
				work.setDeadline(deadline);
				work.setContent(content);
				list.add(work);
			}
			res.close();
			db.close();//
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Show> getShowList(String wid) {
		// TODO Auto-generated method stub
		List<Show> list= new ArrayList<Show>();
		String sql = null;
		DBHelper db = null;
		DBHelper db2 = null;
		sql = "select homeworkstudent.sid, sname, score, comment from homeworkstudent, student where homeworkstudent.sid=student.sid and  isshow=1 and wid = "+ wid;
		db = new DBHelper(sql);
		ResultSet res = null;
		ResultSet res2 = null;
		//System.out.println(sql);
		try {
			res = db.pst.executeQuery();
			while(res.next()) {
				Show work = new Show();
				String sname = res.getString("sname");
				String comment = res.getString("comment");
				int score = res.getInt("score");
				String sid = res.getString("sid");
				work.setSname(sname);
				work.setScore(score);
				work.setComment(comment);
				
				List<WorkImgObject>workimg = new ArrayList<WorkImgObject>();
				
				sql = "select  iname, file from workimg where  wid = "+ wid + " and sid = "+sid;
				//System.out.println(sql);
				db2 = new DBHelper(sql);
				try {
					res2 = db2.pst.executeQuery();
					while(res2.next()) {
						WorkImgObject w = new WorkImgObject();
						String iname = res2.getString("iname");
						String url = res2.getString("file");
						w.setIname(iname);
						w.setUrl(url);
						workimg.add(w);
					}
				} catch (SQLException e) {
					// 查询失败
					e.printStackTrace();
				} 
				work.setWorkimg(workimg);
				list.add(work);
			}
			res.close();
			db.close();//
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		return list;
	}

	public static List<ExplainFile> getHomeWorkEx(String wid) {
		// TODO Auto-generated method stub
		List<ExplainFile> list= new ArrayList<ExplainFile>();
		String sql = null;
		DBHelper db = null;
		sql = "select * from homeworkexplain where wid = "+ wid;
		db = new DBHelper(sql);
		ResultSet res = null;
		try {
			res = db.pst.executeQuery();
			while(res.next()) {
				String efname = res.getString("efname");
				String url = res.getString("file");
				ExplainFile exf = new ExplainFile();
				exf.setEfname(efname);
				exf.setUrl(url);
				list.add(exf);
			}
			res.close();
			db.close();//
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		return list;
	}

	public static List<CourseFile> getClassFile(String cid) {
		// TODO Auto-generated method stub
		List<CourseFile> list= new ArrayList<CourseFile>();
		String sql = null;
		DBHelper db = null;
		sql = "select * from coursefile where cid = "+ cid;
		db = new DBHelper(sql);
		ResultSet res = null;
		try {
			res = db.pst.executeQuery();
			while(res.next()) {
				String cfname = res.getString("cfname");
				String url = res.getString("file");
				CourseFile cf = new CourseFile();
				cf.setCfname(cfname);
				cf.setUrl(url);
				list.add(cf);
			}
			res.close();
			db.close();//
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<SelfFile> getSelfFile(String sid, String cid) {
		// TODO Auto-generated method stub
		List<SelfFile> list= new ArrayList<SelfFile>();
		String sql = null;
		DBHelper db = null;
		sql = "select * from selfFile where cid = "+ cid + " and sid = "+sid;
		db = new DBHelper(sql);
		ResultSet res = null;
		try {
			res = db.pst.executeQuery();
			while(res.next()) {
				String fname = res.getString("fname");
				String url = res.getString("file");
				SelfFile cf = new SelfFile();
				cf.setFname(fname);
				cf.setUrl(url);
				list.add(cf);
			}
			res.close();
			db.close();//
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		return list;
	}

	public static StatusObject DeleteWorkImg(String wkid) {
		// TODO Auto-generated method stub
		StatusObject s= new StatusObject();
		s.setStatus(0);
		String sql = null;
		DBHelper db = null;
		sql = "delete from workimg where wkid = "+ wkid;
		db = new DBHelper(sql);
		int res = 0;
		try {
			res = db.pst.executeUpdate();
			if(res != 0) {
				s.setStatus(1);
			}
			db.close();//
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		return s;
	}

	public static HistoryObject getHomeWorkHistory(String sid, String wid) {
// TODO Auto-generated method stub
		HistoryObject person= new HistoryObject();
		String sql = null;
		DBHelper db = null;
		DBHelper db2 = null;
		sql = "select score, comment from homeworkstudent where sid = "+sid +" and "+" wid = "+wid;
		db = new DBHelper(sql);
		ResultSet res = null;
		ResultSet res2 = null;
		try {
			res = db.pst.executeQuery();
			while(res.next()) {
				String comment = res.getString("comment");
				int score = res.getInt("score");
				person.setScore(score);
				person.setComment(comment);
				
				List<WorkImgObject>workimg = new ArrayList<WorkImgObject>();
				
				sql = "select  wkid,iname, file from workimg where  wid = "+ wid + " and sid = "+sid;
				db2 = new DBHelper(sql);
				try {
					res2 = db2.pst.executeQuery();
					while(res2.next()) {
						WorkImgObject w = new WorkImgObject();
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
			}
			res.close();
			db.close();//
		} catch (SQLException e) {
			// 查询失败
			e.printStackTrace();
		}
		return person;
	}

	public static int submitHomework(String sid, String wid) {
		// TODO Auto-generated method stub
		String sql = null;
		DBHelper db = null;
		sql = "insert into homeworkstudent (sid, wid) values('"+ sid+"', '"+wid+"')";
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

	public static int SaveWorkImg(String wid, String sid, String url, String originalFilename) {
		// TODO Auto-generated method stub
		String sql = null;
		DBHelper db = null;
		sql = "insert into workimg (sid, wid, file, iname) values("+ sid+", "+wid+",'"+url+"','"+originalFilename+"')";
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

	public static int SaveSelfFile(String cid, String sid, String url, String oname) {
		// TODO Auto-generated method stub
		String sql = null;
		DBHelper db = null;
		sql = "insert into selffile (sid, cid, file, fname) values("+ sid+", "+cid+",'"+url+"','"+oname+"')";
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
}
