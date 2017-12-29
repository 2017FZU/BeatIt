package cn.springmvc.action;


import java.util.ArrayList;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;

import cn.springmvc.json.VcodeStatus;

public class SendVcode {
    public static VcodeStatus Send(String phone, String vcode) {
    	VcodeStatus status = new VcodeStatus();
    	status.setSuccess(false);
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
	    	if(singleSenderResult.result == 0) {
	    		status.setSuccess(true);
	    	} else {
	    		status.setError(singleSenderResult.errMsg);
	    	}
    	} catch (Exception e) {
    		status.setError("服务器异常");
			e.printStackTrace();
		}
    	return status;
    }
}

