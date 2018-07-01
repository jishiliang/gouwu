package com.lizhou.tools;

import java.util.List;

/**
 * ����������
 * @author Administrator
 *
 */
public class NavTool {

	/**
	 * ����һ������
	 * @param subName
	 * @return
	 */
	public static String genNavCode(String subName){
		StringBuffer navCode=new StringBuffer();
		navCode.append("�����ڵ�λ�ã�");
		navCode.append("<a href='index.jsp'>��ҳ</a>&nbsp;");
		navCode.append("&gt; ");
		navCode.append(subName);
		return navCode.toString();
	}
	
	/**
	 * ���ɶ༶����
	 * @param hrefList ������ǩ���ӵļ���
	 * @return
	 */
	public static String genNavCode(List<String> hrefList){
		StringBuffer navCode=new StringBuffer();
		navCode.append("�����ڵ�λ�ã�");
		navCode.append("<a href='index.jsp'>��ҳ</a>&nbsp;");
		navCode.append("&gt; ");
		for(String href : hrefList){
			navCode.append(href);
			navCode.append("&gt; ");
		}
		String nav = navCode.substring(0, navCode.lastIndexOf("&gt; "));
		return nav;
	}
}
