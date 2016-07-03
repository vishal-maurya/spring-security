package com.ttnd.springSecurity.services.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttnd.springSecurity.Daos.UserDao;
import com.ttnd.springSecurity.entities.User;
import com.ttnd.springSecurity.services.UserService;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	public User getUser(String username, String password) {
		return userDao.getUser(username, password);
	}

}
