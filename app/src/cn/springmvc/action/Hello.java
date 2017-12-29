package cn.springmvc.action;
import java.util.ArrayList;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
public class Hello {
    public static void main(String[] args) {
    	String vcode = "1234";
    	String phone = "177208068";
    	try {
    		int appid = 1400033462;
    		String appkey = "23ab4251f73c4a8648c4ffab6797178c";
    		int tmplId = 25119;
	    	SmsSingleSender singleSender = new SmsSingleSender(appid, appkey);
	    	SmsSingleSenderResult singleSenderResult;
	    	ArrayList<String> params = new ArrayList<String>();
	    	params.add(vcode);
	    	params.add("1");
	    	singleSenderResult = singleSender.sendWithParam("86", phone, tmplId, params, "", "", "");
	    	System.out.println(singleSenderResult);

    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
