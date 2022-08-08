package com.muran.web3.manger;

import com.muran.web3.common.CSVReaderWithHeaderAutoDetection;
import com.muran.web3.manager.VecrvManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io. * ;
import java.util.List;

@SpringBootTest
public class MangerTests   {
    @Autowired
    private VecrvManager vecrvManager;

    @Test
    public void  testSaveDeposit() throws IOException{

        String path = "/Users/muran/Downloads/export-0x5f3b5dfeb7b28cdbd7faba78963ee202a494e2a2-";
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        for (int i = 1; i <8; i++) {
            String filepath = path+i+".csv";
            System.out.println(filepath);
            List<String> blockno = CSVReaderWithHeaderAutoDetection.readCSV(filepath, "Blockno");
            for (String num : blockno){

                try {
                    vecrvManager.saveWithdraw(Long.valueOf(num));

                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }


    }




}
