package com.lizhou.service.inter;

import java.util.List;

import com.lizhou.entity.ProductBigType;

public interface ProductBigTypeServiceInter {

	List<ProductBigType> getBigTypeList();
	

	ProductBigType getBigType(ProductBigType bigType);
	
}
