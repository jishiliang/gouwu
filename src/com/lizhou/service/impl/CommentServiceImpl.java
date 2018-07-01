package com.lizhou.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lizhou.dao.impl.BaseDaoImpl;
import com.lizhou.entity.Comment;
import com.lizhou.entity.PageBean;
import com.lizhou.service.inter.CommentServiceInter;

@Service("commentService")
public class CommentServiceImpl implements CommentServiceInter {
	
	@Resource(name="baseDao")
	private BaseDaoImpl<Comment> baseDao;
	
	public List<Comment> findCommentList(Comment s_Comment, PageBean pageBean) {
		List<Object> param = new LinkedList<>();
		StringBuffer hql = new StringBuffer("FROM Comment ");
		
		hql.append("ORDER BY createTime DESC");
		if(pageBean == null){
			return baseDao.find(hql.toString(), param);
		} else{
			return baseDao.find(hql.toString(), param, pageBean);
		}
	}

	public Long getCommentCount(Comment s_Comment) {
		List<Object> param = new LinkedList<>();
		StringBuffer hql = new StringBuffer("SELECT COUNT(*) FROM Comment ");
		
		return baseDao.count(hql.toString(), param);
	}

	@Override
	public void saveComment(Comment comment) {
		baseDao.merge(comment);
	}

}
