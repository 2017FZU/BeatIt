package cn.springmvc.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.springmvc.model.Classes;

/**
 */
public class StudentBusiness {

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
}
