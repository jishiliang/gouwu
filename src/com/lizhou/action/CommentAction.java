package com.lizhou.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lizhou.entity.Comment;
import com.lizhou.entity.PageBean;
import com.lizhou.service.inter.CommentServiceInter;
import com.lizhou.tools.PageTool;
import com.lizhou.tools.StringTool;

@Controller("commentAction")
@Scope(value="prototype")
public class CommentAction implements ServletRequestAware {

	@Resource(name="commentService")
	private CommentServiceInter commentService;
	
	HttpServletRequest request;
	
	private List<Comment> commentList;
	
	//封装查询条件
	private Comment s_Comment;
	//评论/留言内容
	private Comment comment;
	
	private String page;
	private Long total;
	private String pageCode;
	
	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
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
	
	public Comment getS_Comment() {
		return s_Comment;
	}

	public void setS_Comment(Comment s_Comment) {
		this.s_Comment = s_Comment;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String list(){
		if(StringTool.isEmpty(page)){
			page = "1"; //默认第一页
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 3);
		//查询
		commentList = commentService.findCommentList(s_Comment, pageBean);
		total = commentService.getCommentCount(s_Comment);
		//生成页面
		pageCode = PageTool.genPagination("comment_list.action", total, Integer.parseInt(page), 3);
		return "success";
	}
	
	public String save(){
		if(comment.getCreateTime() == null){
			comment.setCreateTime(new Date());
		}
		commentService.saveComment(comment);
		return "save";
	}
	
}
