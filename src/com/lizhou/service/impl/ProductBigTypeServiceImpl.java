package com.lizhou.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lizhou.dao.inter.BaseDao;
import com.lizhou.entity.ProductBigType;
import com.lizhou.service.inter.ProductBigTypeServiceInter;


@Service("bigTypeService")
public class ProductBigTypeServiceImpl implements ProductBigTypeServiceInter {
	
	@Resource(name="baseDao")
	BaseDao<ProductBigType> baseDao = null;
	
	public List<ProductBigType> getBigTypeList() {
		
		return baseDao.find("FROM ProductBigType");
	}

	public ProductBigType getBigType(ProductBigType bigType) {
		String hql = "FROM ProductBigType WHERE id=?";
		List<Object> param = new LinkedList<Object>();
		if(bigType != null){
			param.add(bigType.getId());
			return baseDao.get(hql, param);
		}
		return null;
	}

}
