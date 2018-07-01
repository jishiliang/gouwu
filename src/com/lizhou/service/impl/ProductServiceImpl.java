package com.lizhou.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lizhou.dao.inter.BaseDao;
import com.lizhou.entity.PageBean;
import com.lizhou.entity.Product;
import com.lizhou.service.inter.ProductServiceInter;
import com.lizhou.tools.StringTool;

/**
 * 
 * @author bojiangzhou
 *
 */
@Service("productService")
public class ProductServiceImpl implements ProductServiceInter {
	
	@Resource(name="baseDao")
	private BaseDao<Product> baseDao;

	public List<Product> getProductList(Product product, PageBean pageBean) {
		StringBuffer sbHQL = new StringBuffer("FROM Product ");
		
		List<Object> param = new LinkedList<Object>();
		if(product != null){
		
			if(product.getSpecialPrice() == 1){ 
				sbHQL.append("AND specialPrice=1 ORDER BY specialPriceTime DESC ");
			}
		
			if(product.getHot() == 1){
				sbHQL.append("AND hot=1  ORDER BY hotTime DESC ");
			}
			
			if(product.getBigType() != null){
				
				param.add(product.getBigType().getId());
				sbHQL.append("AND bigType.id=? ");
			}
			
			if(product.getSmallType() != null){
				
				param.add(product.getSmallType().getId());
				sbHQL.append("AND smallType.id=? ");
			}
			
			if(!StringTool.isEmpty(product.getName())){
			
				param.add("%"+product.getName()+"%");
				sbHQL.append("AND name like ?");
			}
		}
		String hql = sbHQL.toString().replaceFirst("AND", "WHERE");
		
		if(pageBean != null){
			return baseDao.find(hql, param, pageBean);
		} else{
			return baseDao.find(hql, param);
		}
	}
	
	public Long getProductCount(Product product) {
		StringBuffer sbHQL = new StringBuffer("SELECT COUNT(*) FROM Product ");
		
		List<Object> param = new LinkedList<Object>();
		if(product != null){
			
			if(product.getBigType() != null){
				param.add(product.getBigType().getId());
				sbHQL.append("AND bigType.id=? ");
			}
			
			if(product.getSmallType() != null){
				param.add(product.getSmallType().getId());
				sbHQL.append("AND smallType.id=? ");
			}
		
			if(!StringTool.isEmpty(product.getName())){
				
				param.add("%"+product.getName()+"%");
				sbHQL.append("AND name like ?");
			}
		}
		String hql = sbHQL.toString().replaceFirst("AND", "WHERE");
		return baseDao.count(hql.toString(), param);
	}

	public Product getProduct(int id) {
		return baseDao.get(Product.class, id);
	}
	
	
	
}
