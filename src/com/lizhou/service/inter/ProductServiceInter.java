package com.lizhou.service.inter;

import java.util.List;

import com.lizhou.entity.PageBean;
import com.lizhou.entity.Product;


public interface ProductServiceInter {
	

	List<Product> getProductList(Product product, PageBean pageBean);
	

	Long getProductCount(Product product);

	Product getProduct(int id);
	
	
}
