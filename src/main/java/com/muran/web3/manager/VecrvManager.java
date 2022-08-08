package com.muran.web3.manager;

import com.alibaba.fastjson2.JSON;
import com.muran.web3.bean.EthLog;
import com.muran.web3.common.DateFormatter;
import com.muran.web3.common.DecodeUtils;
import com.muran.web3.dao.mapper.VecrvDepositMapper;
import com.muran.web3.dao.mapper.VecrvWithdrawMapper;
import com.muran.web3.dao.model.VecrvDepositEventDO;
import com.muran.web3.dao.model.VecrvWithdrawEventDO;
import com.muran.web3.service.AlchemyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

@Service
public class VecrvManager {

    private static final String withdrawTopic = "0xf279e6a1f5e320cca91135676d9cb6e44ca8a08c0b88342bcdb1144f6511b568";
    private static final String depositTopic =  "0x4566dfc29f6f11d13a418c26a02bef7c28bae749d4de47e4e6a7cddea6730d59";
    private static final String veCrvContractAddr = "0x5f3b5dfeb7b28cdbd7faba78963ee202a494e2a2";
    @Autowired
    private AlchemyClient alchemyClient;
    @Autowired
    private VecrvDepositMapper vecrvDepositMapper;
    @Autowired
    private VecrvWithdrawMapper vecrvWithdrawMapper;
    public   void saveDeposit(long blockNum){

        List<String> topics = new ArrayList<>();
        topics.add(depositTopic);
        List<EthLog> ethLogByNumberAndTopic = alchemyClient.getEthLogByNumberAndTopic(blockNum, veCrvContractAddr,topics);
        System.out.println(" blockNum ====>"+ blockNum );


        if (!CollectionUtils.isEmpty(ethLogByNumberAndTopic)){
            System.out.println(" ethLogByNumberAndTopic ====>"+ JSON.toJSONString( ethLogByNumberAndTopic ));
            for (EthLog  log : ethLogByNumberAndTopic){
                VecrvDepositEventDO  eventDO = transferFromLogToDepositEvent(log);
                if (eventDO == null){
                    continue;
                }
                vecrvDepositMapper.insert(eventDO);
            }
        }else {
            System.out.println("==empty==");
        }

        System.out.println("x**************************");

    }

    public void  saveWithdraw(long blockNum){
        List<String> topics = new ArrayList<>();
        topics.add(withdrawTopic);
        List<EthLog> ethLogByNumberAndTopic = alchemyClient.getEthLogByNumberAndTopic(blockNum, veCrvContractAddr,topics);
        System.out.println(" blockNum ====>"+ blockNum );

        if (!CollectionUtils.isEmpty(ethLogByNumberAndTopic)){
            System.out.println(" ethLogByNumberAndTopic ====>"+ JSON.toJSONString( ethLogByNumberAndTopic ));
            for (EthLog  log : ethLogByNumberAndTopic){
                VecrvWithdrawEventDO  eventDO = transferFromLogToWithdrawEvent(log);
                if (eventDO == null){
                    continue;
                }
                vecrvWithdrawMapper.insert(eventDO);
            }
        }else {
            System.out.println("==empty==");
        }

        System.out.println("x**************************");
    }

    private VecrvWithdrawEventDO transferFromLogToWithdrawEvent(EthLog log)  {

        if (log == null){
            return null;
        }

        VecrvWithdrawEventDO eventDO = new VecrvWithdrawEventDO();
        eventDO.setTxHash(log.getTransactionHash());
        eventDO.setBlockNumber(DecodeUtils.parseHexToLong ( log.getBlockNumber()));
        eventDO.setContractAddress(log.getAddress());
        eventDO.setIndex( DecodeUtils.parseHexToLong(log.getLogIndex()));
        List<String> topics = log.getTopics();
        eventDO.setProvider(DecodeUtils.hexToAddress( topics.get(1)));
        List<String> strings = DecodeUtils.dataSpliter(log.getData());
        eventDO.setValue(DecodeUtils.parseHexToBigInteger(strings.get(0)));
        Long aLong = DecodeUtils.parseHexToLong(strings.get(1));
        eventDO.setTs(aLong);
        Timestamp t = new Timestamp(aLong*1000);
        Date date = new Date(t.getTime());
        try {
            eventDO.setBlockTime( DateFormatter.chaneDateTimeZoneToGMT(date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return eventDO;
    }


    private VecrvDepositEventDO transferFromLogToDepositEvent(EthLog log)  {

        if (log == null){
            return null;
        }

        VecrvDepositEventDO eventDO = new VecrvDepositEventDO();
        eventDO.setTxHash(log.getTransactionHash());
        eventDO.setBlockNumber(DecodeUtils.parseHexToLong ( log.getBlockNumber()));
        eventDO.setContractAddress(log.getAddress());
        eventDO.setIndex( DecodeUtils.parseHexToLong(log.getLogIndex()));
        List<String> topics = log.getTopics();

        eventDO.setProvider(DecodeUtils.hexToAddress( topics.get(1)));

        eventDO.setLockTime(DecodeUtils.parseHexToLong(topics.get(2)));

        List<String> strings = DecodeUtils.dataSpliter(log.getData());
        eventDO.setValue(DecodeUtils.parseHexToBigInteger(strings.get(0)));
        eventDO.setType(DecodeUtils.parseHexToLong(strings.get(1)));
        Long aLong = DecodeUtils.parseHexToLong(strings.get(2));
        eventDO.setTs(aLong);
        Timestamp t = new Timestamp(aLong*1000);
        Date date = new Date(t.getTime());
        try {
            eventDO.setBlockTime( DateFormatter.chaneDateTimeZoneToGMT(date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return eventDO;
    }


    public static void main(String[] args) throws ParseException {
        long l = System.currentTimeMillis();
        long a =   1659188469;
        System.out.println(l);
        System.out.println(a);
        Timestamp t = new Timestamp(a*1000);
        Date d = new Date(t.getTime());
        System.out.println(d);
    }




}
