package com.lizhou.action;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lizhou.entity.PageBean;
import com.lizhou.entity.Product;
import com.lizhou.entity.ProductBigType;
import com.lizhou.entity.ProductSmallType;
import com.lizhou.entity.User;
import com.lizhou.service.inter.ProductBigTypeServiceInter;
import com.lizhou.service.inter.ProductServiceInter;
import com.lizhou.service.inter.ProductSmallTypeServiceInter;
import com.lizhou.tools.DateJsonValueProcessor;
import com.lizhou.tools.NavTool;
import com.lizhou.tools.ObjectJsonValueProcessor;
import com.lizhou.tools.PageTool;
import com.lizhou.tools.ResponseTool;
import com.lizhou.tools.StringTool;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 产品类Action
 * @author bojiangzhou
 *
 */
@Controller("productAction")
@Scope(value="prototype")
public class ProductAction implements ServletRequestAware {
	
	@Resource(name="productService")
	private ProductServiceInter productService;
	
	@Resource(name="bigTypeService")
	private ProductBigTypeServiceInter bigTypeService;
	
	@Resource(name="smallTypeService")
	private ProductSmallTypeServiceInter smallTypeService;
	
	private HttpServletRequest request;
	
	private List<Product> productList;
	//封装查询参数(前台要查询的参数封装到这个对象里来)
	private Product searchProduct;
	
	//当前页
	private int page = 1;
	private String rows;
	//总记录数
	private Long total;
	//分页代码
	private String pageCode;
	//主页
	private String mainPage;
	//小导航代码
	private String navCode;
	
	//商品ID
	private int productId;
	//详细商品
	private Product product;
	
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public Product getSearchProduct() {
		return searchProduct;
	}
	public void setSearchProduct(Product searchProduct) {
		this.searchProduct = searchProduct;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public String getPageCode() {
		return pageCode;
	}
	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
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
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public String execute() throws Exception {
		//分页属性
		PageBean pageBean = new PageBean(page, 8);
		//转换编码,前台以get方式传值
		if(!StringTool.isEmpty(searchProduct.getName())){
			searchProduct.setName(StringTool.messyCode(searchProduct.getName()));
		}
		//分页查询
		productList = productService.getProductList(searchProduct, pageBean);
		//获取总的记录数
		total = productService.getProductCount(searchProduct);
		//设置页码所带参数
		StringBuffer param = new StringBuffer();
		//小导航的名字
		List<String> hrefList = new LinkedList<>();
		if(searchProduct != null){
			if(searchProduct.getBigType() != null){
				param.append("searchProduct.bigType.id="+searchProduct.getBigType().getId());
				//添加导航
				ProductBigType bigType = bigTypeService.getBigType(searchProduct.getBigType());
				String href = "<a href='product.action?searchProduct.bigType.id="+bigType.getId()+"'>"+bigType.getName()+"</a>&nbsp;";
				hrefList.add(href);
			}
			if(searchProduct.getSmallType() != null){
				param.append("&searchProduct.smallType.id="+searchProduct.getSmallType().getId());
				//添加导航
				ProductSmallType smallType = smallTypeService.getSmallType(searchProduct.getSmallType().getId());
				String href = "<a href='product.action?searchProduct.smallType.id="+smallType.getId()+"'>"+smallType.getName()+"</a>&nbsp;";
				hrefList.add(href);
			}
			//模糊搜索
			if(!StringTool.isEmpty(searchProduct.getName())){
				param.append("&searchProduct.name="+searchProduct.getName());
				
				String href = "商品列表&nbsp;";
				hrefList.add(href);
			}
		}
		//导航
		navCode = NavTool.genNavCode(hrefList);
		//分页
		pageCode = PageTool.genPagination("product.action", total, page, 8, param.toString());
		//主页
		mainPage = "productList.jsp";
		
		return "success";
	}
	
	public String detail(){
		//获取详细商品
		product = productService.getProduct(productId);
		//生成导航代码
		List<String> hrefList = new LinkedList<>();
		int bigTypeId = product.getBigType().getId();
		int smallTypeId = product.getSmallType().getId();
		String href1 = "<a href='product.action?searchProduct.bigType.id="+bigTypeId+"'>"+product.getBigType().getName()+"</a>&nbsp;";
		String href2 = "<a href='product.action?searchProduct.bigType.id="+bigTypeId+"&searchProduct.smallType.id="+smallTypeId+"'>"+product.getSmallType().getName()+"</a>&nbsp;";
		hrefList.add(href1);
		hrefList.add(href2);
		navCode = NavTool.genNavCode(hrefList);
		//设置主页
		mainPage = "productDetails.jsp";
		//保存最近浏览商品
		saveLatelyBrowse();
		
		return "success";
	}
	
	/**
	 * 保存最近浏览商品
	 */
	private void saveLatelyBrowse(){
		LinkedList<Product> lately = (LinkedList<Product>) request.getSession().getAttribute("lately");
		if(lately == null){
			lately = new LinkedList<>();
			request.getSession().setAttribute("lately", lately);
		}
		for(int i=0; i < lately.size();i++){
			if(lately.get(i).getId() == product.getId()){
				lately.remove(i);
				break;
			}
		}
		if(lately.size() == 6){
			lately.removeLast();
		}
		lately.addFirst(product);
	}
	//		config.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));

	/**
	 * 查询产品集合
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception{
		//分页对象
		PageBean pageBean = new PageBean(page, Integer.parseInt(rows));
		//根据查询条件和分页对象查询产品集合
		List<Product> productList = productService.getProductList(product, pageBean);
		//产品总记录数
		long total = productService.getProductCount(searchProduct);
		
		//JSON配置
		JsonConfig config = new JsonConfig();
		//过滤掉的属性
		config.setExcludes(new String[]{"orderProductList"});
		//注册日期处理器
		config.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		config.registerJsonValueProcessor(ProductBigType.class, new ObjectJsonValueProcessor(new String[]{"name", "id"}, ProductBigType.class));
		config.registerJsonValueProcessor(ProductSmallType.class, new ObjectJsonValueProcessor(new String[]{"name", "id"}, ProductSmallType.class));
		JSONArray rows = JSONArray.fromObject(productList, config);
		JSONObject result = new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		//返回json字符串
		ResponseTool.write(ServletActionContext.getResponse(), result);
		System.out.println(result.toString());
		return null;
	}
	
	public String toProductManageView(){
		return "toProductManageView";
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
	
}
