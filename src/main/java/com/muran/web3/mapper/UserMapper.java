package com.muran.web3.mapper;

import com.muran.web3.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

	public List<User> getUser();

	public int insertUser(User user);

	public int updateUser(User user);

	public int deleteUser(String name);

}
