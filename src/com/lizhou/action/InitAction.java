package com.lizhou.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.lizhou.entity.News;
import com.lizhou.entity.Notice;
import com.lizhou.entity.PageBean;
import com.lizhou.entity.Product;
import com.lizhou.entity.ProductBigType;
import com.lizhou.entity.Tag;
import com.lizhou.service.inter.NewsServiceInter;
import com.lizhou.service.inter.NoticeServiceInter;
import com.lizhou.service.inter.ProductBigTypeServiceInter;
import com.lizhou.service.inter.ProductServiceInter;
import com.lizhou.service.inter.TagServiceInter;

/**
 * 初始化:实现application缓存数据功能
 * 在服务启动时，将数据库中的数据加载进内存，并将其赋值给一个属性名，其它的 Servlet 就可以通过 getAttribute 进行属性值的访问
 * @author bojiangzhou
 *
 */
@Component
public class InitAction implements ServletContextListener, ApplicationContextAware {
	
	private static ApplicationContext ac;
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		//获取application
		ServletContext application = event.getServletContext();
		
		//创建一个Product对象用于传参
		Product product = new Product();
		
		//***********商品大类信息(也包括小类了)加载到application缓存中***********
		//获取服务层
		ProductBigTypeServiceInter bigTypeService = (ProductBigTypeServiceInter) InitAction.ac.getBean("bigTypeService");
		//获取商品大类集合
		List<ProductBigType> bigTypeList = bigTypeService.getBigTypeList();
		//保存到application缓存中
		application.setAttribute("bigTypeList", bigTypeList);
		
		//***********Tag标签加载到application缓存中***********
		TagServiceInter tagService = (TagServiceInter) ac.getBean("tagService");
		List<Tag> tagList = tagService.getTagList();
		application.setAttribute("tagList", tagList);
		
		//***********最新公告加载到application缓存中***********
		NoticeServiceInter noticeService = (NoticeServiceInter) ac.getBean("noticeService");
		//查询前七条公告
		List<Notice> noticeList = noticeService.getNoticeList(new PageBean(1, 7));
		application.setAttribute("noticeList", noticeList);
		
		//***********最新新闻加载到application缓存中***********
		NewsServiceInter newsService = (NewsServiceInter) ac.getBean("newsService");
		List<News> newsList = newsService.getNewsList(new PageBean(1, 7));
		application.setAttribute("newsList", newsList);
		
		//***********今日特价加载到application缓存中***********
		ProductServiceInter productService = (ProductServiceInter) ac.getBean("productService");
		product.setSpecialPrice(1);
		List<Product> productSpecialList = productService.getProductList(product, new PageBean(1, 8));
		application.setAttribute("productSpecialList", productSpecialList);
		
		//***********热卖推荐加载到application缓存中***********
		product = new Product();
		product.setHot(1);
		List<Product> productHotList = productService.getProductList(product, new PageBean(1, 6));
		application.setAttribute("productHotList", productHotList);
		
	}

	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		InitAction.ac = ac;
	}

}
