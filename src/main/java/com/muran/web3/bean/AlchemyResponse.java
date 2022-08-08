package com.muran.web3.bean;

import lombok.Data;

@Data
public class AlchemyResponse<T> {
    private String id;
    private String jsonrpc;
    private T result;
}
