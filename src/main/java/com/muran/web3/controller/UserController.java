package com.muran.web3.controller;

import com.muran.web3.dao.model.UserDO;

import com.muran.web3.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

	@Autowired
	private UserMapper mapper;

	@RequestMapping("/select")
	public List<UserDO> select() {
		return mapper.getUser();
	}

	@RequestMapping("/insert")
	public int insert(UserDO userDO) {
		return mapper.insertUser(userDO);
	}

	@RequestMapping("/update")
	public int update(UserDO userDO) {
		return mapper.updateUser(userDO);
	}

	@RequestMapping("/delete")
	public int delete(String name) {
		return mapper.deleteUser(name);
	}
}
