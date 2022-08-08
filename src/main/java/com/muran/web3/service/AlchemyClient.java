package com.muran.web3.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.muran.web3.bean.AlchemyResponse;
import com.muran.web3.bean.EthBlock;
import com.muran.web3.bean.EthLog;
import com.muran.web3.utils.HttpUtils;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLHandshakeException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlchemyClient {

    private static final String uri = "https://eth-mainnet.g.alchemy.com/v2/";
    private static final String api_key = "erlAAQ2Anaaz1Z_aWyGJih9IZVreUJHx";

    private static final String url = uri + api_key;

    public EthBlock getEthBlockByNumber(Long number){
        JSONObject requests = new JSONObject();
        List<Object> list = new ArrayList<>();
        list.add("0x"+ Long.toHexString(number));
        list.add(true);
        requests.put("jsonrpc","2.0");
        requests.put("method","eth_getBlockByNumber");
        requests.put("params", list);


        try {
            String post = postRequest(url,requests);

            AlchemyResponse<EthBlock> typeReference = JSONObject.parseObject(post, new TypeReference<AlchemyResponse<EthBlock>>() {
            });
            return typeReference.getResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String postRequest(String url,JSONObject requests){
        String post = "";
        try {

            post = HttpUtils.post(url, requests);
        }catch (Exception e){
            try {
                Thread.sleep(3000);
                post = HttpUtils.post(url, requests);
            }catch (Exception e1){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                post = HttpUtils.post(url, requests);
            }
        }
        return post;
    }


    public List<EthLog> getEthLogByNumberAndTopic(Long number,String address, List<String> topics){
        EthBlock ethBlockByNumber = getEthBlockByNumber(number);
        if (ethBlockByNumber!=null){
            JSONObject requests = new JSONObject();
            requests.put("id",1);
            requests.put("jsonrpc","2.0");
            requests.put("method","eth_getLogs");
            List<Object> params = new ArrayList<>();
            Map<String,Object> stringMap = new HashMap<>();
            stringMap.put("blockHash",ethBlockByNumber.getHash());
            stringMap.put("address",address);
            stringMap.put("topics",topics);
            params.add(stringMap);
            requests.put("params",params);
            String post =  postRequest(url,requests);
            AlchemyResponse<List< EthLog>> typeReference = JSONObject.parseObject(post,
                    new TypeReference<AlchemyResponse<List< EthLog>>>() {
            });

            return typeReference.getResult();
        }
        return null;
    }
}
