package com.example.demo.service;


import com.example.demo.domain.User;

import java.util.List;

public interface UserService extends IService<User> {

	User findByName(String userName);

	void saveUser(User user);

	User getById(Integer id);

	List<User> getList();
}
