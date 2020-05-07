package com.testcases;

import com.utils.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Verify_Download_Files {
    FileUtils fileUtils = new FileUtils();

    @Test(description = "Read CSV file in local.")
    public void TestCase4(){
        System.out.println("------------Read CSV file in local------------");
        String filePath = "/downloads/car.csv";
        String splitBy = ",";
        List<List<String>> records = fileUtils.readCSV(filePath, splitBy);
        for (List<String> record: records) {
            System.out.println(record);
        }
    }

    @Test(description = "Read JSON file in local.")
    public void TestCase5(){
        System.out.println("------------Read JSON file in local------------");
        String filePath = "/downloads/employees.json";
        JSONObject json = fileUtils.readJSON(filePath);
        fileUtils.parseEmployeeData(json);
    }
}
