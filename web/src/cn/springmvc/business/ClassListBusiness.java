package cn.springmvc.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.springmvc.model.ClassLists;

/**
 */
public class ClassListBusiness {

	/**
	 * 
	 * @return
	 */
	public static List<ClassLists> getAllClassLists() {
		
		List<ClassLists> list = new ArrayList<ClassLists>();
		
		String sql = null;
		
		DBHelper db1 = null;
		
		sql = "select * from class";// SQL
		
		db1 = new DBHelper(sql);//
		
		ResultSet ret = null;
		
		try {
			ret = db1.pst.executeQuery();
			
			while (ret.next()) {
				
				int cid = ret.getInt("cid");
				
				String cname = ret.getString("cname");
				
				System.out.print("aaa"+cid+"aaa");
				
				ClassLists classes = new ClassLists();
				
				classes.setCid(cid);
				classes.setCname(cname);
				
				list.add(classes);
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
	 * @param _id
	 * @return
	 */
	public static ClassLists getClassListInfoById(String _cid) {
		String sql = null;
		DBHelper db1 = null;
		sql = "select * from student where id =" + _cid;// 
		db1 = new DBHelper(sql);// 
		ResultSet ret = null;
		ClassLists classes = new ClassLists();
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				int cid = ret.getInt(1);
				String cname = ret.getString(2);
				
				classes.setCid(cid);
				classes.setCname(cname);
				
			} // 
			ret.close();
			db1.close();// 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 
		return classes;
	}
}

