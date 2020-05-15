package com.utils;

import com.objects.CarDetails;
import com.objects.EmployeeDetails;
import com.profiles.DefaultProfile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileUtils {
    private static String COMMA_DELIMITER = ",";

    public static List<List<String>> readCSV(String filePath) {
        InputStream is = FileUtils.class.getResourceAsStream(filePath);
        BufferedReader csvReader = new BufferedReader(new InputStreamReader(is));
        List<List<String>> records = new ArrayList<>();
        String row;
        try {
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.trim().split(COMMA_DELIMITER);
                records.add(Arrays.asList(data));
            }
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public static List<CarDetails> readCSVByBuffered() {
        BufferedReader csvReader = null;
        try {
            csvReader = new BufferedReader(new FileReader(FileUtils.class.getResource(DefaultProfile.PATH_CSV).getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<CarDetails> records = new ArrayList<CarDetails>();
        String row;
        try {
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.trim().split(COMMA_DELIMITER);
                CarDetails carDetails = new CarDetails();
                carDetails.setCarYear(data[0]);
                carDetails.setCarMake(data[1]);
                carDetails.setCarModel(data[2]);
                records.add(carDetails);
            }
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public static List<CarDetails> readCSVByScanner() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(FileUtils.class.getResource(DefaultProfile.PATH_CSV).getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int index = 0;
        List<CarDetails> records = new ArrayList<CarDetails>();
        Scanner rowScanner = null;
        while (scanner.hasNextLine()) {
            rowScanner = new Scanner(scanner.nextLine());
            rowScanner.useDelimiter(COMMA_DELIMITER);
            CarDetails carDetails = new CarDetails();
            while (rowScanner.hasNext()) {
                String data = rowScanner.next();
                if (index == 0)
                    carDetails.setCarYear(data);
                else if (index == 1)
                    carDetails.setCarMake(data);
                else if (index == 2)
                    carDetails.setCarModel(data);
                else
                    System.out.println("invalid data::" + data);
                index++;
            }
            index = 0;
            records.add(carDetails);
        }
        return records;
    }

    public static JSONObject readJSON(String filePath) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try (InputStream is = FileUtils.class.getResource(filePath).openStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            try {
                jsonObject = (JSONObject) jsonParser.parse(reader);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static void parseEmployeeData(JSONObject json) {
        JSONArray employees = (JSONArray) json.get("employees");
        System.out.println("Employee list...");
        for (Object e : employees) {
            JSONObject eJson = (JSONObject) e;
            System.out.println("id: " + eJson.get("id"));
            System.out.println("name: " + eJson.get("firstName") + " " + eJson.get("lastName"));
        }
    }

    public static List<EmployeeDetails> parseEmployeeDataTest(JSONObject json) {
        JSONArray employees = (JSONArray) json.get("employees");
        List<EmployeeDetails> employeeDetailsList = new ArrayList<EmployeeDetails>();
        for (Object e : employees) {
            JSONObject eJson = (JSONObject) e;
            EmployeeDetails record = EmployeeDetails.jsonFormat(eJson);
            employeeDetailsList.add(record);
        }
        return employeeDetailsList;
    }

    private static void displayTextInputStream(InputStream input) throws IOException {
        // Read the text input stream one line at a time and display each line.
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    public static boolean downloadFromUrl(String url, String fileName) throws Exception {
        File targetFile = new File(FileUtils.class.getResource(DefaultProfile.DOWNLOAD_PATH).getPath() + File.separator + fileName);
        Path targetPath = targetFile.toPath();
        System.out.println("Target folder: " + targetPath);
        //delete previous downloaded file if exist in target folder
        Files.deleteIfExists(targetPath);
        long copy;
        try (InputStream in = URI.create(url).toURL().openStream()) {
            copy = Files.copy(in, targetPath, StandardCopyOption.REPLACE_EXISTING);
        }
        boolean isDownloaded = Files.exists(targetPath);
        System.out.println("Downloading... " + Files.exists(targetPath));
        if (isDownloaded == true) {
            System.out.println("Download successfully. File exists in target folder");
            System.out.println("File size: " + copy);
        } else {
            System.out.println("Failed to download. File doesn't exist in target folder");
        }
        System.out.println("Downloaded file content: ");
        InputStream targetStream = new FileInputStream(targetFile);
        displayTextInputStream(targetStream);
        return isDownloaded;
    }
}
