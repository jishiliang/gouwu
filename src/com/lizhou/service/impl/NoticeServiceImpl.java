package com.lizhou.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lizhou.dao.inter.BaseDao;
import com.lizhou.entity.Notice;
import com.lizhou.entity.PageBean;
import com.lizhou.service.inter.NoticeServiceInter;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeServiceInter {
	
	@Resource(name="baseDao")
	private BaseDao<Notice> baseDao;
	
	public List<Notice> getNoticeList(PageBean pageBean) {
		String hql = "FROM Notice";
		if(pageBean != null){ 
			
			return baseDao.find(hql, pageBean);
		} else{ 
			
			return baseDao.find(hql);
		}
	}

	public Notice getNotice(int id) {
		return baseDao.get(Notice.class, id);
	}

}
