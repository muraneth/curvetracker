package com.muran.web3.dao.mapper;

import com.muran.web3.dao.model.VecrvWithdrawEventDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VecrvWithdrawMapper {
    Long insert(VecrvWithdrawEventDO eventDO);

    List<VecrvWithdrawEventDO> queryAll();

}
