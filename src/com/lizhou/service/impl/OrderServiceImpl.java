package com.lizhou.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lizhou.dao.inter.BaseDao;
import com.lizhou.entity.Order;
import com.lizhou.entity.PageBean;
import com.lizhou.service.inter.OrderServiceInter;
import com.lizhou.tools.StringTool;

@Service("orderService")
public class OrderServiceImpl implements OrderServiceInter {

	@Resource(name="baseDao")
	private BaseDao<Order> baseDao;
	
	public void saveOrder(Order order) {
		baseDao.save(order);
	}

	public List<Order> findOrder(Order s_Order, PageBean pageBean) {
		List<Object> param = new LinkedList<>();
		StringBuffer sb = new StringBuffer("FROM Order ");
		if(s_Order != null){
			if(s_Order.getUser() != null && s_Order.getUser().getId() != 0){
				sb.append("AND user.id=? ");
				param.add(s_Order.getUser().getId());
			}
			if(!StringTool.isEmpty(s_Order.getOrderNo())){
				sb.append("AND orderNo LIKE ? ");
				param.add("%"+s_Order.getOrderNo()+"%");
			}
		}
		sb.append(" ORDER BY createTime DESC");
		
		String hql = sb.toString().replaceFirst("AND", "WHERE");
		if(pageBean == null){
			return baseDao.find(hql, param);
		} else{
			return baseDao.find(hql, param, pageBean);
		}
	}
	
	public long count(){
		
		
		return 0;
	}

	public void updateOrderStatus(String orderNo, int status) {
		String hql = "UPDATE Order SET status=? WHERE orderNo=?";
		baseDao.executeHql(hql, new Object[]{status, orderNo});
	}
	
	

}
