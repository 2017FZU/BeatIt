/**
 * 单独Job类形式
 */
package com.mhy.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author mhy2011@163.com
 * @date 2013年12月21日
 */
public class ExampleJob2 {

	/**
	 * 执行定时统计任务
	 * 自行指定方法
	 */
	public void execute(){
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "执行ExampleJob2");
	}
}
