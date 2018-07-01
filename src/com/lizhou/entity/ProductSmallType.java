package com.lizhou.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * 商品小类
 * @author bojiangzhou
 *
 */
@Entity
@Table(name="t_smallType")
public class ProductSmallType implements Serializable {

	@Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native", strategy="native")
	private int id; //ID
	
	@Column(length=50)
	private String name; //大类名称
	
	private String remarks; //备注
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="bigTypeId")
	private ProductBigType bigType; //小类所属大类，多对一
	
	@OneToMany(mappedBy="smallType")
	private List<Product> productList; //商品集合, 一对多

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

	public ProductBigType getBigType() {
		return bigType;
	}

	public void setBigType(ProductBigType bigType) {
		this.bigType = bigType;
	}
	
	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
}
