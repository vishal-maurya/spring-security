package com.ttnd.springSecurity.Daos;

import com.ttnd.springSecurity.entities.User;

public interface UserDao {

	public User getUser(String userName, String password);
	
	public User findByUserName(String userName);
}
