package com.ttnd.springSecurity.services;

import com.ttnd.springSecurity.entities.User;

public interface UserService {
public User getUser(String username, String password);
}
