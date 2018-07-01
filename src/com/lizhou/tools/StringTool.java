package com.lizhou.tools;

import java.io.UnsupportedEncodingException;

/**
 * �ַ���������
 * @author bojiangzhou
 *
 */
public class StringTool {
	
	/**
	 * �ж��ַ����Ƿ�Ϊ��
	 * @param str
	 * @return true: �� | false: ��Ϊ��
	 */
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str.trim())){
			return true;
		} else{
			return false;
		}
	}
	
	/**
	 * ��get��ʽ����������תΪUTF-8��ʽ
	 * @param str
	 * @return
	 */
	public static String messyCode(String str){
		String code = null;
		try {
			byte[] by = str.getBytes("ISO-8859-1");
			code = new String(by, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return code;
	}
	
}
