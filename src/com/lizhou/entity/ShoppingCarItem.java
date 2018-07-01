package com.lizhou.entity;

import java.io.Serializable;

/**
 * 购物车单项
 * @author bojiangzhou
 *
 */
public class ShoppingCarItem implements Serializable {
	
	private int id;
	
	private Product product; //商品
	
	private int count = 1; //数量

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
