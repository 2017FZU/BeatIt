import java.util.HashMap;
import java.util.Map;

/**  
 * @Title: http://www.smschinese.cn/api.shtml
 * @date 2011-3-22
 * @version V1.2  
 */
public class test {
	
	//用户名
	private static String Uid = "Kaloneme";
	
	//接口安全秘钥
	private static String Key = "216e12d9c398105622cf";
	
	//手机号码，多个号码如13800000000,13800000001,13800000002
	private static String smsMob = "15005933756,17720806830";
	
	//短信内容
	private static String smsText = "验证码：8888";
	
	public static void main(String[] args) {
		
		HttpClientUtil client = HttpClientUtil.getInstance();
		
		//GBK发送
		int resultGbk = client.sendMsgGbk(Uid, Key, smsText, smsMob );
		if(resultGbk>0){
			System.out.println("GBK成功发送条数=="+resultGbk);
		}else{
			System.out.println(client.getErrorMsg(resultGbk));
		}
	}
}
