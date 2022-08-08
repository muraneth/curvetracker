package com.muran.web3.common;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 25/09/17.
 */
public class CSVReaderWithHeaderAutoDetection {


    public static List<String> readCSV(String path,String column) throws IOException  {
        List<String> list = new ArrayList<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(path));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing values by Header names
                list.add( csvRecord.get(column));
            }
        }
        return list;
    }
    private static final String SAMPLE_CSV_FILE_PATH =
            "/Users/muran/Downloads/export-0x5f3b5dfeb7b28cdbd7faba78963ee202a494e2a2-1.csv";

    public static void main(String[] args) throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing values by Header names

                String name = csvRecord.get("Blockno");
                String DateTime = csvRecord.get("DateTime");

                System.out.println("Record No - " + csvRecord.getRecordNumber());
                System.out.println("---------------");
                System.out.println("Blockno : " + name);
                System.out.println("DateTime : " + DateTime);

                System.out.println("---------------\n\n");
            }
        }
    }

}