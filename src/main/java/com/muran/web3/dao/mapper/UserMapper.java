package com.muran.web3.dao.mapper;

import com.muran.web3.dao.model.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

	public List<UserDO> getUser();

	public int insertUser(UserDO userDO);

	public int updateUser(UserDO userDO);

	public int deleteUser(String name);

}
