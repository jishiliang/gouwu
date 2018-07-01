package com.lizhou.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 * 璁㈠崟
 * @author bojiangzhou
 *
 */
@Entity
@Table(name="t_order")
public class Order implements Serializable {
	
	@Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native", strategy="native")
	private int id; //ID
	
	private String orderNo; //订单号	
	private Date createTime;
	
	private float cost; 
	
	private int status; 
	

	public static final int STATUS_EXAMINE_WAIT = 1;

	public static final int STATUS_EXAMINE_PASS = 2;

	public static final int STATUS_GOODS_SEND = 3;

	public static final int STATUS_GOODS_TAKE = 4;
	
	@ManyToOne
	@JoinColumn(name="userId", updatable=false)
	private User user; 
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="order")
	@Cascade(value={CascadeType.ALL})

	private List<OrderProduct> orderProductList = new ArrayList<>();

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}

	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}
}
