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
    public void TestCase4() throws IOException {
        System.out.println("------------Read CSV file in local------------");
    String filePath = "/downloads/car.csv";
        List<List<String>> records = fileUtils.readCSV(filePath);
        for (List<String>record : records){
            System.out.println(record);
        }

    }

    @Test(description = "Read JSON file in local.")
    public void TestCase5() throws IOException {
        System.out.println("------------Read JSON file in local------------");
        String filePath = "/downloads/employees.json";
        JSONObject json = fileUtils.readJSON(filePath);
        JSONObject employees = (JSONObject) json.get("employees");
        JSONArray employee = (JSONArray) employees.get("employee");
        for (Object e : employee)
        {
            JSONObject eJson = (JSONObject) e;
            System.out.println(e +"");
            System.out.println("id..." + eJson.get("id"));
            System.out.println("name..." + eJson.get("firstName") + " " + eJson.get("lastName"));
            
        }
    }
}
