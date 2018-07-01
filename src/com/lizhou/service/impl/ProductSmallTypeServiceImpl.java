package com.lizhou.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lizhou.dao.inter.BaseDao;
import com.lizhou.entity.ProductSmallType;
import com.lizhou.service.inter.ProductSmallTypeServiceInter;


@Service("smallTypeService")
public class ProductSmallTypeServiceImpl implements ProductSmallTypeServiceInter {
	
	@Resource(name="baseDao")
	BaseDao<ProductSmallType> baseDao = null;
	

	public ProductSmallType getSmallType(int id) {
		String hql = "FROM ProductSmallType WHERE id=?";
		return baseDao.get(ProductSmallType.class, id);
	}

}
