package com.muran.web3.bean;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EthLog {
    private String address;
    private List<String> topics;
    private String data;
    private String blockNumber;
    private String transactionHash;
    private String transactionIndex;
    private String blockHash;
    private String logIndex;
}
