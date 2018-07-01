package com.lizhou.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lizhou.entity.Order;
import com.lizhou.entity.OrderProduct;
import com.lizhou.entity.Product;
import com.lizhou.entity.ShoppingCar;
import com.lizhou.entity.ShoppingCarItem;
import com.lizhou.entity.User;
import com.lizhou.service.inter.OrderServiceInter;
import com.lizhou.tools.DateTool;
import com.lizhou.tools.NavTool;
import com.lizhou.tools.ResponseTool;

import net.sf.json.JSONObject;

/**
 * 订单
 * @author bojiangzhou
 *
 */
@Controller("orderAction")
@Scope(value="prototype")
public class OrderAction implements ServletRequestAware {
	
	@Resource
	private OrderServiceInter orderService;
	
	private HttpServletRequest request;
	
	//主页
	private String mainPage;
	//小导航代码
	private String navCode;
	
	private Order s_Order;
	
	private List<Order> orderList;
	
	private String orderNo;
	
	private int status;
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
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

	public Order getS_Order() {
		return s_Order;
	}

	public void setS_Order(Order s_Order) {
		this.s_Order = s_Order;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	/**
	 * 保存订单
	 * @return
	 * @throws Exception 
	 */
	public String save() throws Exception{
		HttpSession session = request.getSession();
		//创建订单
		Order order = new Order();
		
		//获取订单金额 及 订单商品关联列表
		ShoppingCar shoppingCar = (ShoppingCar) session.getAttribute("shoppingCar");
		List<ShoppingCarItem> shoppingCarItems = shoppingCar.getShoppingCarItems();
		float cost = 0;
		List<OrderProduct> orderProductList = new ArrayList<>();
		for(ShoppingCarItem item : shoppingCarItems){
			Product product = item.getProduct();
			
			cost += product.getPrice() * item.getCount();
			
			OrderProduct op = new OrderProduct();
			op.setNum(item.getCount());
			op.setOrder(order);
			op.setProduct(product);
			orderProductList.add(op);
		}
		
		//设置订单
		User currentUser = (User) session.getAttribute("currentUser");
		order.setCreateTime(new Date());
		order.setUser(currentUser);
		order.setOrderNo(DateTool.getCurrentDateStr());//订单编号
		order.setStatus(Order.STATUS_EXAMINE_WAIT);
		order.setCost(cost);
		order.setOrderProductList(orderProductList);
		
		orderService.saveOrder(order);
		
		navCode = NavTool.genNavCode("购物");
		mainPage = "shopping-result.jsp";
		
		session.removeAttribute("shoppingCar");
		
		return "success";
	}
	
	public String findOrder(){
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		if(s_Order == null){
			s_Order = new Order();
		}
		s_Order.setUser(currentUser);
		orderList = orderService.findOrder(s_Order, null);
		
		navCode = NavTool.genNavCode("个人中心");
		mainPage = "/WEB-INF/view/order/orderList.jsp";
		
		return "orderList";
	}
	
	/**
	 * 确认收货
	 * @return
	 * @throws Exception 
	 */
	public String confirmReceive() throws Exception{
		System.out.println(orderNo+"::"+status);
		orderService.updateOrderStatus(orderNo, status);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseTool.write(ServletActionContext.getResponse(), result);
		
		return null;
	}
	
}
