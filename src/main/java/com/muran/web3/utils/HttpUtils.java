package com.muran.web3.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.HttpPost;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

/**
 * Description: httpClient工具类
 *
 * @author JourWon
 * @date Created on 2018年4月19日
 */
public class HttpUtils {

    public static String post(String URL,JSONObject json) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // 可以add 参数
        CloseableHttpClient client = httpClientBuilder.build();
        HttpPost post = new HttpPost(URL);
        post.setHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Basic YWRtaW46");
        String result = "";

        try {

            StringEntity s = new StringEntity(json.toString(), "utf-8");
            s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
                    "application/json"));
            post.setEntity(s);

            // 发送请求
            HttpResponse httpResponse = client.execute(post);

            // 获取响应输入流
            InputStream inStream = httpResponse.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    inStream, "utf-8"));
            StringBuilder strber = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
                strber.append(line + "\n");
            inStream.close();
            result = strber.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

}