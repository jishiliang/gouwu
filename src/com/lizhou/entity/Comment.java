package com.lizhou.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * 评论/留言
 * @author bojiangzhou
 *
 */
@Entity
@Table(name="t_comment")
public class Comment implements Serializable {

	@Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native", strategy="native")
	private int id; //ID
	
	@Column(length=500)
	private String content; //评论内容
	
	@Column(length=30)
	private String nickName; //评论人昵称
	
	private Date createTime; //评论日期
	
	@Column(length=500)
	private String 	replyContent; //回复内容
	
	private Date replyTime; //回复时间
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	
	
}
