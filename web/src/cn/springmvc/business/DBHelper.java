package cn.springmvc.business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 */

public class DBHelper {
	
	 	public static final String url = "jdbc:mysql://localhost:3306/homework";  
	 	
	    public static final String name = "com.mysql.jdbc.Driver";  
	    
	    public static final String user = "root";  
	    
	    public static final String password = "root";  
	  
	    public Connection conn = null;  
	    public PreparedStatement pst = null;  
	  
	    public DBHelper(String sql) {  
	        try {  
	            Class.forName(name);//
	            
	            conn = DriverManager.getConnection(url, user, password);//
	            
	            pst = conn.prepareStatement(sql);//
	            
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
	  
	    public void close() {  
	        try {  
	            this.conn.close();  
	            this.pst.close();  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  
	    }  
}
