package com.muran.web3;

import com.alibaba.fastjson2.JSON;
import com.muran.web3.service.AlchemyClient;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Web3ApplicationTests {

	@Autowired
	AlchemyClient alchemyClient;
	@Test
	void contextLoads() {
		Object ethBlockByNumber = alchemyClient.getEthBlockByNumber(1l);
		System.out.println(ethBlockByNumber);
	}

	@Test
	void testGetLogs(){
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		Object ethLogByNumberAndTopic = alchemyClient.getEthLogByNumberAndTopic(
				10813096l,"0x5f3b5dfeb7b28cdbd7faba78963ee202a494e2a2",
				Lists.newArrayList("0x4566dfc29f6f11d13a418c26a02bef7c28bae749d4de47e4e6a7cddea6730d59"));
		System.out.println(JSON.toJSONString( ethLogByNumberAndTopic));

	}

}
