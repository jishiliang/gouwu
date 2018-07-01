package com.lizhou.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * 商品大类
 * @author bojiangzhou
 *
 */
@Entity
@Table(name="t_bigType")
public class ProductBigType implements Serializable {
	
	@Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native", strategy="native")
	private int id; //ID
	
	@Column(length=50)
	private String name; //大类名称
	
	private String remarks; //备注
	
	@OneToMany(mappedBy="bigType") //把维护的权利交给对方
	private List<Product> productList = new ArrayList<>(); //商品集合
	
	@OneToMany(mappedBy="bigType", fetch=FetchType.EAGER) //设置'急加载'，加载大类的时候把小类也加载出来
	private List<ProductSmallType> smallTypeList = new ArrayList<>(); //大类下小类集合

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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	public List<ProductSmallType> getSmallTypeList() {
		return smallTypeList;
	}

	public void setSmallTypeList(List<ProductSmallType> smallTypeList) {
		this.smallTypeList = smallTypeList;
	}
	
}
