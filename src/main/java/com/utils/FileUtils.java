package com.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

    public ArrayList<String> readCSV(String filePath) throws IOException {
        File csvFile = new File(filePath);
        ArrayList<String> data = new ArrayList<String>();
        if (csvFile.isFile()) {
            Scanner sc = new Scanner(csvFile);
            sc.useDelimiter(",");   //sets the delimiter pattern
            while (sc.hasNext()) {
                data.add(sc.next());
                System.out.print(sc.next());
            }
            sc.close();  //closes the scanner
        }
        return data;
    }

    public List<List<String>> readCSVTest(String filePath) throws IOException {
        InputStream is = FileUtils.class.getResourceAsStream(filePath);
        BufferedReader csvReader = new BufferedReader(new InputStreamReader(is));
        List<List<String>> records = new ArrayList<>();
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            records.add(Arrays.asList(data));
        }
        csvReader.close();
        return records;
    }

    public JSONObject readJSON(String filePath) throws IOException {
        InputStream is = FileUtils.class.getResourceAsStream(filePath);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is));) {
            jsonObject = (JSONObject) jsonParser.parse(reader);
            System.out.println(jsonObject);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
