package com.muran.web3.common;

public enum TopicEnums {

    VECRV_DEPOSIT("0x4566dfc29f6f11d13a418c26a02bef7c28bae749d4de47e4e6a7cddea6730d59","VECRV_DEPOSIT");
    private String topic;
    private String method;

    TopicEnums(String topic,String method){
        this.topic= topic;
        this.method = method;
    }

    public String getTopic(){
        return this.topic;
    }

    public String getMethod(){
        return this.method;
    }
    public TopicEnums getEnumByTopic(String topic){
        for(TopicEnums v : values()){
            if (topic.equals(v.getTopic())){
                return v;
            }
        }
        return null;
    }
}
