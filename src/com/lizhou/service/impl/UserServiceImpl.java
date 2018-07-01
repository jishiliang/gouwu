package com.lizhou.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lizhou.dao.inter.BaseDao;
import com.lizhou.entity.PageBean;
import com.lizhou.entity.User;
import com.lizhou.service.inter.UserServiceInter;

@Service("userService")
public class UserServiceImpl implements UserServiceInter {

	@Resource(name="baseDao")
	private BaseDao<User> baseDao;
	
	public void saveUser(User user) {
		baseDao.merge(user);
	}

	public boolean existUserWithUserName(String userName) {
		long count = baseDao.count("SELECT COUNT(*) FROM User WHERE username=?", new Object[]{userName});
		return count != 0;
	}

	public User login(User user) {
		return baseDao.get("FROM User WHERE userName=? AND password=? AND status=?", 
				new Object[]{user.getUserName(), user.getPassword(), user.getStatus()});
	}

	public List<User> findUserList(User s_user, PageBean pageBean) {
		StringBuffer sb = new StringBuffer("FROM User ");
		List<Object> param = new ArrayList<>();
		sb.append(" AND status=1");
		if(s_user != null){
			sb.append(" AND userName like ?");
			param.add("%"+s_user.getUserName()+"%");
		}
		sb.append(" ORDER BY id DESC");
		String hql = sb.toString().replaceFirst("AND", "WHERE");
		if(pageBean != null){
			return baseDao.find(hql, param, pageBean);
		} else{
			return baseDao.find(hql, param);
		}
	}
	
	public Long getUserCount(User s_user) {
		StringBuffer sb = new StringBuffer("SELECT COUNT(*) FROM User ");
		List<Object> param = new ArrayList<>();
		sb.append(" AND status=1");
		if(s_user != null){
			sb.append(" AND userName like ?");
			param.add("%"+s_user.getUserName()+"%");
		}
		String hql = sb.toString().replaceFirst("AND", "WHERE");
		return baseDao.count(hql, param);
	}

	public void delete(User user) {
		baseDao.delete(user);
	}

	public User getUserById(int id) {
		return baseDao.get(User.class, id);
	}

}
