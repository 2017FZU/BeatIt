package cn.springmvc.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.springmvc.model.Teachers;

/**
 */
public class TeacherBusiness {

	/**
	 * 
	 * @return
	 */
	public static List<Teachers> getAllTeachers() {
		
		List<Teachers> list = new ArrayList<Teachers>();
		
		String sql = null;
		
		DBHelper db1 = null;
		
		sql = "select * from teacher";// SQL
		
		db1 = new DBHelper(sql);//
		
		ResultSet ret = null;
		
		try {
			ret = db1.pst.executeQuery();
			
			while (ret.next()) {
				
				int tid = ret.getInt("tid");
				
				String tname = ret.getString("tname");
				System.out.print("aaa"+tid+"aaa");
				
				Teachers teachers = new Teachers();
				teachers.setTid(tid);
				teachers.setTname(tname);
				
				list.add(teachers);
			} //
			ret.close();
			db1.close();//
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //
		
		return list;
	}

	/**
	 * @param _tid
	 * @return
	 */
	public static Teachers getTeacherInfoById(String _tid) {
		String sql = null;
		DBHelper db1 = null;
		sql = "select * from teacher where id =" + _tid;// 
		db1 = new DBHelper(sql);// 
		ResultSet ret = null;
		Teachers teachers = new Teachers();
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				int tid = ret.getInt(1);
				String tname = ret.getString(2);
				String psw = ret.getString(3);
				int tel = ret.getInt(4);
				String img = ret.getString(5);
				teachers.setTid(tid);
				teachers.setTname(tname);
				teachers.setPsw(psw);
				teachers.setTel(tel);
				teachers.setImg(img);
			} // 
			ret.close();
			db1.close();// 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 
		return teachers;
	}
}

