package com.lizhou.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车
 * @author bojiangzhou
 *
 */
public class ShoppingCar implements Serializable {
	
	private int userId; //绑定用户ID
	
	private List<ShoppingCarItem> shoppingCarItems = new ArrayList<>();

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<ShoppingCarItem> getShoppingCarItems() {
		return shoppingCarItems;
	}

	public void setShoppingCarItems(List<ShoppingCarItem> shoppingCarItems) {
		this.shoppingCarItems = shoppingCarItems;
	}
	
}
