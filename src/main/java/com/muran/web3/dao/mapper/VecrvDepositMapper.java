package com.muran.web3.dao.mapper;

import com.muran.web3.dao.model.VecrvDepositEventDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VecrvDepositMapper {
    Long insert(VecrvDepositEventDO event);

    List<VecrvDepositEventDO> queryAll();
}
