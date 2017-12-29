package cn.springmvc;

import java.io.FileOutputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;

public class Base64Decoder {
	public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
	    if (imgStr == null) return false;
	    BASE64Decoder decoder = new BASE64Decoder();
	    try {
	      // Base64解码
	      byte[] bytes = decoder.decodeBuffer(imgStr);
	      for (int i = 0; i < bytes.length; ++i) {
	        if (bytes[i] < 0) {// 调整异常数据
	          bytes[i] += 256;
	        }
	      }
	      OutputStream out = new FileOutputStream(imgFilePath);
	      out.write(bytes);
	      out.flush();
	      out.close();
	      return true;
	    } catch (Exception e) {
	      return false;
	    }
	  }
}
