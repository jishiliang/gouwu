package com.lizhou.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lizhou.entity.Product;
import com.lizhou.entity.ShoppingCar;
import com.lizhou.entity.ShoppingCarItem;
import com.lizhou.entity.User;
import com.lizhou.service.inter.ProductServiceInter;
import com.lizhou.tools.NavTool;
import com.lizhou.tools.ResponseTool;

import net.sf.json.JSONObject;

/**
 * 购物车
 * @author bojiangzhou
 *
 */
@Controller("shoppingAction")
@Scope(value="prototype")
public class ShoppingAction implements ServletRequestAware {

	private HttpServletRequest request;
	
	@Resource
	private ProductServiceInter productService;
	
	private int productId;
	
	//主页
	private String mainPage;
	//小导航代码
	private String navCode;
	
	//购物车单项数量
	private int count;
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
	/**
	 * 添加到购物车
	 * @return
	 * @throws Exception
	 */
	public String addShoppingCarItem() throws Exception{
		HttpSession session = request.getSession();
		ShoppingCar shoppingCar = (ShoppingCar) session.getAttribute("shoppingCar");
		if(shoppingCar == null){
			shoppingCar = new ShoppingCar();
			User currentUser = (User) session.getAttribute("currentUser");
			shoppingCar.setUserId(currentUser.getId());
		}
		List<ShoppingCarItem> shoppingCarItems = shoppingCar.getShoppingCarItems();
		boolean flag = true;
		for(ShoppingCarItem item : shoppingCarItems){
			if(item.getProduct().getId() == productId){
				item.setCount(item.getCount()+1);
				flag = false;
				break;
			}
		}
		if(flag){
			ShoppingCarItem item = new ShoppingCarItem();
			Product product = productService.getProduct(productId);
			item.setProduct(product);
			shoppingCarItems.add(item);
		}
		session.setAttribute("shoppingCar", shoppingCar);
		
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseTool.write(ServletActionContext.getResponse(), result);
		
		return null; 
	}
	
	/**
	 * 列出购物车清单
	 * @return
	 */
	public String list(){
		mainPage = "shopping.jsp";
		navCode = NavTool.genNavCode("购物车");	
		return "success";
	}
	
	/**
	 * 直接购买商品
	 * @return
	 */
	public String buy(){
		HttpSession session = request.getSession();
		ShoppingCar shoppingCar = (ShoppingCar) session.getAttribute("shoppingCar");
		if(shoppingCar == null){
			shoppingCar = new ShoppingCar();
			User currentUser = (User) session.getAttribute("currentUser");
			shoppingCar.setUserId(currentUser.getId());
		}
		List<ShoppingCarItem> shoppingCarItems = shoppingCar.getShoppingCarItems();
		boolean flag = true;
		for(ShoppingCarItem item : shoppingCarItems){
			if(item.getProduct().getId() == productId){
				item.setCount(item.getCount()+1);
				flag = false;
				break;
			}
		}
		if(flag){
			ShoppingCarItem item = new ShoppingCarItem();
			Product product = productService.getProduct(productId);
			item.setProduct(product);
			shoppingCarItems.add(item);
		}
		session.setAttribute("shoppingCar", shoppingCar);
		
		mainPage = "shopping.jsp";
		navCode = NavTool.genNavCode("购物车");
		
		return "success";
	}
	
	/**
	 * 更新购物车单项信息:修改数量
	 * @return
	 * @throws Exception 
	 */
	public String updateShoppingCarItem() throws Exception{
		HttpSession session = request.getSession();
		ShoppingCar shoppingCar = (ShoppingCar) session.getAttribute("shoppingCar");
		List<ShoppingCarItem> shoppingCarItems = shoppingCar.getShoppingCarItems();
		
		for(ShoppingCarItem item : shoppingCarItems){
			if(item.getProduct().getId() == productId){
				item.setCount(count);
				break;
			}
		}
		session.setAttribute("shoppingCar", shoppingCar);
		 
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseTool.write(ServletActionContext.getResponse(), result);
		
		return null;
	}
	
	public String deleteShoppingCarItem(){
		HttpSession session = request.getSession();
		ShoppingCar shoppingCar = (ShoppingCar) session.getAttribute("shoppingCar");
		List<ShoppingCarItem> shoppingCarItems = shoppingCar.getShoppingCarItems();
		
		for(ShoppingCarItem item : shoppingCarItems){
			if(item.getProduct().getId() == productId){
				shoppingCarItems.remove(item);
				break;
			}
		}
		session.setAttribute("shoppingCar", shoppingCar);
		
		return "delete";
	}
	
	
}
