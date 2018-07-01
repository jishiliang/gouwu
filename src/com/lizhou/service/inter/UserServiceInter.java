package com.lizhou.service.inter;

import java.util.List;

import com.lizhou.entity.PageBean;
import com.lizhou.entity.User;


public interface UserServiceInter {

	void saveUser(User user);
	

	boolean existUserWithUserName(String userName);
	

	User login(User user);
	

	List<User> findUserList(User s_user, PageBean pageBean);

	Long getUserCount(User s_user);
	

	void delete(User user);
	

	User getUserById(int id);
}
