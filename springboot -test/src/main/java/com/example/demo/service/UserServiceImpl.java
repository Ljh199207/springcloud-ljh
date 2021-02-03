package com.example.demo.service;

import com.example.demo.domain.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Repository("userService")
public class UserServiceImpl extends BaseService<User> implements UserService {

	@Override
	public User findByName(String userName) {
		Example example = new Example(User.class);
		example.createCriteria().andCondition("username=", userName);
		List<User> userList = this.selectByExample(example);
		if (userList.size() != 0)
			return userList.get(0);
		else
			return null;
	}

	@Override
	public void saveUser(User user) {
		user.setId(this.getSequence("seq_user"));
		user.setCreateTime(new Date());
		this.save(user);
	}

	@Override
	public User getById(Integer id) {
		return this.getById(id);
	}

	@Override
	public List<User> getList() {
		return this.selectAll();
	}

}
