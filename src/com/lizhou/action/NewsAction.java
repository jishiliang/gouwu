package com.lizhou.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lizhou.entity.News;
import com.lizhou.entity.Notice;
import com.lizhou.service.inter.NewsServiceInter;
import com.lizhou.service.inter.NoticeServiceInter;
import com.lizhou.tools.NavTool;

/**
 * 新闻Action
 * @author bojiangzhou
 *
 */
@Controller("newsAction")
@Scope(value="prototype")
public class NewsAction {
	
	@Resource(name="newsService")
	private NewsServiceInter newsService;
	
	private int newsId;
	
	private News news;
	
	private String navCode;
	
	private String mainPage;

	
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public String getNavCode() {
		return navCode;
	}
	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}
	public String getMainPage() {
		return mainPage;
	}
	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}
	
	public String detail(){
		//获取
		news = newsService.getNews(newsId);
		//设置主页
		mainPage = "newsDetails.jsp";
		//导航
		navCode = NavTool.genNavCode("新闻信息");
		return "detail";
	}
	
	
}
