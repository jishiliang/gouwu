package com.lizhou.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lizhou.dao.inter.BaseDao;
import com.lizhou.entity.News;
import com.lizhou.entity.PageBean;
import com.lizhou.service.inter.NewsServiceInter;

/**
 * @author bojiangzhou
 *
 */
@Service("newsService")
public class NewsServiceImpl implements NewsServiceInter {
	
	@Resource(name="baseDao")
	private BaseDao<News> baseDao;
	
	public List<News> getNewsList(PageBean pageBean) {
		String hql = "FROM News";
		if(pageBean != null){ 
			
			return baseDao.find(hql, pageBean);
		} else{ 
			
			return baseDao.find(hql);
		}
	}

	public News getNews(int id) {
		return baseDao.get(News.class, id);
	}

}
