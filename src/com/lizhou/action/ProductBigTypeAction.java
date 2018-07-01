package com.lizhou.action;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.lizhou.entity.ProductBigType;
import com.lizhou.service.inter.ProductBigTypeServiceInter;
import com.lizhou.tools.ResponseTool;
import com.opensymphony.xwork2.ActionSupport;

@Controller("bigTypeAction")
public class ProductBigTypeAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private ProductBigTypeServiceInter bigTypeService;
	
	public String comboList()throws Exception{
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("id", "");
		jsonObject.put("name", "ÇëÑ¡Ôñ...");
		jsonObject.put("name2", "¹þ¹þ...");
		jsonArray.add(jsonObject);
		List<ProductBigType> productBigTypeList=bigTypeService.getBigTypeList();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"productList","smallTypeList"});
		JSONArray rows=JSONArray.fromObject(productBigTypeList, jsonConfig);
		jsonArray.addAll(rows);
		
		ResponseTool.write(ServletActionContext.getResponse(), jsonArray);
		
		return null;
	}

}
