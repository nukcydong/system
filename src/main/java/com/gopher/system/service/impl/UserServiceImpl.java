package com.gopher.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gopher.system.dao.mysql.UserDAO;
import com.gopher.system.model.User;
import com.gopher.system.service.UserService;
import com.gopher.system.util.ThreadLocalUtils;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
    private UserDAO userDAO;
	@Override
	public Integer insert(User t) {
		return userDAO.insert(t);
	}
	@Override
	public User findOne(String account) {
		User query = new User();
		query.setAccount(account);
		return userDAO.findOne(query);
	}

	@Override
	public User getCurrentUser() {
		Object obj = ThreadLocalUtils.getObject(ThreadLocalUtils.USER_KEY);
		int user_id = 0;
		if(null !=obj) {
			user_id = Integer.valueOf(obj.toString());
		}
		User query = new User();
		query.setId(user_id);
		return userDAO.findOne(query);
	}

}
