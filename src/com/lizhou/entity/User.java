package com.lizhou.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * 用户
 * @author bojiangzhou
 *
 */
@Entity
@Table(name="t_user")
public class User implements Serializable {
	
	@Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native", strategy="native")
	private int id; //ID
	
	@Column(length=20)
	private String trueName; //真实姓名
	
	@Column(length=20)
	private String userName; //用户名
	
	@Column(length=20)
	private String password; //密码
	
	@Column(length=4)
	private String sex; //性别
	
	private Date birthday; //生日
	
	@Column(length=20)
	private String dentityCode; //身份证
	
	@Column(length=20)
	private String email; //邮件
	
	@Column(length=20)
	private String mobile; //电话
	
	@Column(length=200)
	private String address; //收货地址
	
	private int status = 1; //用户类型[普通用户默认为1]
	
	@OneToMany(targetEntity=Order.class, cascade=CascadeType.ALL)
	@JoinColumn(name="userId", updatable=false)
	private List<Order> orderList = new ArrayList<Order>(); //订单集合, 一对多
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getDentityCode() {
		return dentityCode;
	}

	public void setDentityCode(String dentityCode) {
		this.dentityCode = dentityCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	
}
