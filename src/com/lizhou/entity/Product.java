package com.lizhou.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * 商品实体
 * @author bojiangzhou
 *
 */
@Entity
@Table(name="t_product")
public class Product implements Serializable {
	
	@Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native", strategy="native")
	private int id; //ID
	
	@Column(length=50)
	private String name; //商品名称
	
	private float price; //商品价格
	
	private int stock; //库存
	
	private String proPic; //商品图片路径
	
	@Column(length=1000)
	private String description; //商品描述
	
	private int hot = 0; //商品热卖[默认为0，不是热卖; 1是热卖商品]
	
	private Date hotTime; //设置热卖的时间
	
	private int specialPrice; //是否特价[默认0，不是特价商品；1是特价商品]
	
	private Date specialPriceTime; //特价时间
	
	@ManyToOne //外键
	@JoinColumn(name="bigTypeId")
	private ProductBigType bigType; //大类
	
	@ManyToOne //外键
	@JoinColumn(name="smallTypeId") 
	private ProductSmallType smallType; //小类
	
	@OneToMany
	@JoinColumn(name="productId")
	private List<OrderProduct> orderProductList = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getProPic() {
		return proPic;
	}

	public void setProPic(String proPic) {
		this.proPic = proPic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getHot() {
		return hot;
	}

	public void setHot(int hot) {
		this.hot = hot;
	}

	public Date getHotTime() {
		return hotTime;
	}

	public void setHotTime(Date hotTime) {
		this.hotTime = hotTime;
	}

	public int getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(int specialPrice) {
		this.specialPrice = specialPrice;
	}

	public Date getSpecialPriceTime() {
		return specialPriceTime;
	}

	public void setSpecialPriceTime(Date specialPriceTime) {
		this.specialPriceTime = specialPriceTime;
	}
	
	public ProductBigType getBigType() {
		return bigType;
	}

	public void setBigType(ProductBigType bigType) {
		this.bigType = bigType;
	}

	public ProductSmallType getSmallType() {
		return smallType;
	}

	public void setSmallType(ProductSmallType smallType) {
		this.smallType = smallType;
	}

	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}

	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}
	
	
}
