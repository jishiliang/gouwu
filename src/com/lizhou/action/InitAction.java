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
 * ��ʼ��:ʵ��application�������ݹ���
 * �ڷ�������ʱ�������ݿ��е����ݼ��ؽ��ڴ棬�����丳ֵ��һ���������������� Servlet �Ϳ���ͨ�� getAttribute ��������ֵ�ķ���
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
		//��ȡapplication
		ServletContext application = event.getServletContext();
		
		//����һ��Product�������ڴ���
		Product product = new Product();
		
		//***********��Ʒ������Ϣ(Ҳ����С����)���ص�application������***********
		//��ȡ�����
		ProductBigTypeServiceInter bigTypeService = (ProductBigTypeServiceInter) InitAction.ac.getBean("bigTypeService");
		//��ȡ��Ʒ���༯��
		List<ProductBigType> bigTypeList = bigTypeService.getBigTypeList();
		//���浽application������
		application.setAttribute("bigTypeList", bigTypeList);
		
		//***********Tag��ǩ���ص�application������***********
		TagServiceInter tagService = (TagServiceInter) ac.getBean("tagService");
		List<Tag> tagList = tagService.getTagList();
		application.setAttribute("tagList", tagList);
		
		//***********���¹�����ص�application������***********
		NoticeServiceInter noticeService = (NoticeServiceInter) ac.getBean("noticeService");
		//��ѯǰ��������
		List<Notice> noticeList = noticeService.getNoticeList(new PageBean(1, 7));
		application.setAttribute("noticeList", noticeList);
		
		//***********�������ż��ص�application������***********
		NewsServiceInter newsService = (NewsServiceInter) ac.getBean("newsService");
		List<News> newsList = newsService.getNewsList(new PageBean(1, 7));
		application.setAttribute("newsList", newsList);
		
		//***********�����ؼۼ��ص�application������***********
		ProductServiceInter productService = (ProductServiceInter) ac.getBean("productService");
		product.setSpecialPrice(1);
		List<Product> productSpecialList = productService.getProductList(product, new PageBean(1, 8));
		application.setAttribute("productSpecialList", productSpecialList);
		
		//***********�����Ƽ����ص�application������***********
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
