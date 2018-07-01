package com.lizhou.tools;

import java.util.List;

/**
 * 导航工具类
 * @author Administrator
 *
 */
public class NavTool {

	/**
	 * 生成一级导航
	 * @param subName
	 * @return
	 */
	public static String genNavCode(String subName){
		StringBuffer navCode=new StringBuffer();
		navCode.append("您现在的位置：");
		navCode.append("<a href='index.jsp'>首页</a>&nbsp;");
		navCode.append("&gt; ");
		navCode.append(subName);
		return navCode.toString();
	}
	
	/**
	 * 生成多级导航
	 * @param hrefList 导航标签链接的集合
	 * @return
	 */
	public static String genNavCode(List<String> hrefList){
		StringBuffer navCode=new StringBuffer();
		navCode.append("您现在的位置：");
		navCode.append("<a href='index.jsp'>首页</a>&nbsp;");
		navCode.append("&gt; ");
		for(String href : hrefList){
			navCode.append(href);
			navCode.append("&gt; ");
		}
		String nav = navCode.substring(0, navCode.lastIndexOf("&gt; "));
		return nav;
	}
}
