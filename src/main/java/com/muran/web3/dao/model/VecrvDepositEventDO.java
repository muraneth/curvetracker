package com.muran.web3.dao.model;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class VecrvDepositEventDO {
    private String contractAddress;
    private String txHash;
    private String provider;
    private BigInteger value;
    private Long lockTime;
    private Long type;
    private Long ts;
    private Long index;
    private Date blockTime;
    private Long blockNumber;
}
