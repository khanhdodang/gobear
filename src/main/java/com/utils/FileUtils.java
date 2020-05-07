package com.utils;

import java.util.ArrayList;
import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

    public List<List<String>> readCSV(String filePath, String splitBy) {
        InputStream is = getClass().getResourceAsStream(filePath);
        BufferedReader csvReader = new BufferedReader(new InputStreamReader(is));
        List<List<String>> records = new ArrayList<>();
        String row;
        try {
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.trim().split(splitBy);
                records.add(Arrays.asList(data));
            }
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public JSONObject readJSON(String filePath) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try (InputStream is = getClass().getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            jsonObject = (JSONObject) jsonParser.parse(reader);
            System.out.println(jsonObject);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public void parseEmployeeData(JSONObject json){
        JSONObject employees = (JSONObject) json.get("employees");
        JSONArray employee = (JSONArray) employees.get("employee");
        System.out.println("Employee...");
        for (Object e : employee) {
            JSONObject eJson = (JSONObject) e;
            System.out.println("id: " + eJson.get("id"));
            System.out.println("name: " + eJson.get("firstName") + " " + eJson.get("lastName"));
        }
    }
}
