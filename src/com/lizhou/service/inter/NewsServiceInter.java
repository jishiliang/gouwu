package com.lizhou.service.inter;

import java.util.List;

import com.lizhou.entity.News;
import com.lizhou.entity.PageBean;

/**
 * @author bojiangzhou
 *
 */
public interface NewsServiceInter {
	

	List<News> getNewsList(PageBean pageBean);

	News getNews(int id);
	
}
