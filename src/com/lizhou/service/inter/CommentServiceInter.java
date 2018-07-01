package com.lizhou.service.inter;

import java.util.List;

import com.lizhou.entity.Comment;
import com.lizhou.entity.PageBean;


public interface CommentServiceInter {
	

	List<Comment> findCommentList(Comment s_Comment, PageBean pageBean);
	 

	Long getCommentCount(Comment s_Comment);

	void saveComment(Comment comment);
	
}
