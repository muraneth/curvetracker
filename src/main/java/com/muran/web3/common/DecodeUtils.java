package com.muran.web3.common;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class DecodeUtils {

    public static String hexToAddress(String hex){
        return hex.substring(0,2)+hex.substring(26);
    }

    public static Long parseHexToLong(String hex){
        if (hex.startsWith("0x")){
            hex = hex.replace("0x","");
        }
        return Long.parseLong(hex,16);
    }

    public static BigInteger parseHexToBigInteger(String hex){
        hex = hex.replace("0x","");
        return  new BigInteger(hex,16);
    }

    public static List<String> dataSpliter(String data){
        List<String> list = new ArrayList<>();
        data = data.replace("0x","");
        int count= data.length()/64;
        for (int i = 0; i < count; i++) {
            list.add( data.substring(64*i,64*(i+1)));
        }
        return list;
    }

    public static void main(String[] args) {
        String data = "0x000000000000000000000000000000000000000000000050296de6a24284d9b300000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000062d94280";
        List<String> strings = dataSpliter(data);

        for (String s : strings){

            System.out.println(parseHexToBigInteger(s));
        }
        System.out.println(parseHexToLong("0000000000000000000000000000000000000000000000000000000000000001"));

    }






}
