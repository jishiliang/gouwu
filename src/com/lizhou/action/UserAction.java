package com.lizhou.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lizhou.entity.PageBean;
import com.lizhou.entity.User;
import com.lizhou.service.inter.UserServiceInter;
import com.lizhou.tools.DateJsonValueProcessor;
import com.lizhou.tools.NavTool;
import com.lizhou.tools.ResponseTool;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * �û�
 * @author bojiangzhou
 *
 */
@Controller("userAction")
@Scope(value="prototype")
public class UserAction extends ActionSupport implements ServletRequestAware {
	
	@Resource(name="userService")
	private UserServiceInter userService;
	
	private String userName;
	
	private User user;
	
	private String error;
	
	private String imageCode;
	
	private HttpServletRequest request;
	
	//��ҳ
	private String mainPage;
	//С��������
	private String navCode;
	
	//��ѯ
	private User s_user;
	//��ҳ
	private String page;
	private String rows;
	
	private String ids;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}
	
	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public String getNavCode() {
		return navCode;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	public User getS_user() {
		return s_user;
	}

	public void setS_user(User s_user) {
		this.s_user = s_user;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	/*public String existUserWithUserName() throws Exception{
		boolean exist = userService.existUserWithUserName(userName);
		JSONObject result = new JSONObject();
		if(exist){
			result.put("exist", true);
		} else{
			result.put("exist", false);
		}
		ResponseTool.write(ServletActionContext.getResponse(), result);
		return null;
	}
	*/
	/**
	 * ע��
	 */
	public String register(){
		userService.saveUser(user);
		return "register_success";
	}
	
	public String toRegisterView(){
		return "toRegisterView";
	}

	public String toLoginView(){
		return "toLoginView";
	}
	
	//��¼
	public String login(){
		HttpSession session = request.getSession();
		User currentUser = userService.login(user);
		if(!session.getAttribute("sRand").equals(imageCode)){
			error = "��֤�����";
			if(user.getStatus() == 2){
				return "adminError";
			} else{
				return "error";
			}
		} else if(currentUser == null){
			error = "�û������������";
			if(user.getStatus() == 2){
				return "adminError";
			} else{
				return "error";
			}
		} else {
			session.setAttribute("currentUser", currentUser);
		}
		if(user.getStatus() == 2){
			return "adminLogin";
		} else{
			return "login";
		}
	}
	
	/**
	 * ע��
	 * @return
	 */
	public String logout(){
//		request.getSession().removeAttribute("currentUser");
		request.getSession().invalidate(); //���session
		return "logout";
	}
	
	/**
	 * �û�����
	 * @return
	 */
	public String userCenter(){
		navCode = NavTool.genNavCode("��������");
		mainPage = "ucDefault.jsp";
		return "userCenter";
	}
	
	/**
	 * ������Ϣ
	 * @return
	 */
	public String getUserInfo(){
		navCode = NavTool.genNavCode("��������");
		mainPage = "uInfo.jsp";
		return "userCenter";
	}
	
	
	
	/**
	 * �޸ĸ�����Ϣ
	 * @return
	 */
	public String preSave(){
		navCode = NavTool.genNavCode("��������");
		mainPage = "uSav.jsp";
		user = (User) request.getSession().getAttribute("currentUser");
		return "userCenter";
	}
	
	/**
	 * �޸��û���Ϣ
	 * @return
	 */
	public String save(){
		userService.saveUser(user);
		
		request.getSession().setAttribute("currentUser", user);
		
		navCode = NavTool.genNavCode("��������");
		mainPage = "uInfo.jsp";
		
		return "userCenter";
	}
	
	public String toUManageView(){
		return "toUManageView";
	}
	
	/**
	 * �û�����
	 * @return
	 * @throws Exception 
	 */
	public String list() throws Exception{
		//��ҳ����
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		List<User> userList = userService.findUserList(s_user, pageBean);
		long total = userService.getUserCount(s_user);
		
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"orderList"});
		config.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray rows = JSONArray.fromObject(userList, config);
		JSONObject result = new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		
		ResponseTool.write(ServletActionContext.getResponse(), result);
		
		return null;
	}
	
	
	public String deleteUser() throws Exception{
		String[] ids_ = ids.split(",");
		for(int i = 0;i < ids_.length;i++){
			User u = userService.getUserById(Integer.parseInt(ids_[i]));
			userService.delete(u);
		}
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseTool.write(ServletActionContext.getResponse(), result);
		
		return null;
	}
	
	/**
	 * ����û�
	 * @return
	 * @throws Exception 
	 */
	public String addUser() throws Exception{
		userService.saveUser(user);
		
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseTool.write(ServletActionContext.getResponse(), result);
		
		return null;
	}
	
	/**
	 * �༭�û���Ϣ
	 * @return
	 * @throws Exception
	 */
	public String editUser() throws Exception{
		userService.saveUser(user);
		
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseTool.write(ServletActionContext.getResponse(), result);
		
		return null;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
