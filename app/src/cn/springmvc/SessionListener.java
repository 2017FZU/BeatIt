package cn.springmvc;

import javax.servlet.http.HttpSessionListener;  
import javax.servlet.http.HttpSessionEvent;  
import javax.servlet.http.HttpSession;  
import java.util.HashMap;  
import java.util.Map;  
  
public class SessionListener implements HttpSessionListener {  
	public static Map userMap = new HashMap();  
	private   MySessionContext myc=MySessionContext.getInstance();  
  
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {  
		myc.AddSession(httpSessionEvent.getSession());  
	}  
  
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {  
		HttpSession session = httpSessionEvent.getSession();  
		myc.DelSession(session);  
	}  
}  