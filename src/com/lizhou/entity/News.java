package com.lizhou.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="t_news")
public class News implements Serializable {
	
	@Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native", strategy="native")
	private int id; //ID
	
	@Column(length=50)
	private String title; 
	
	@Lob
	@Column(columnDefinition="TEXT")
	private String content; 
	
	private Date createTime; 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
