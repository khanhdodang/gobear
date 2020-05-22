package com.testcases;

import com.objects.CarDetails;
import com.objects.EmployeeDetails;
import com.utils.FileUtils;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.List;

public class Verify_Download_Files {
    FileUtils fileUtils = new FileUtils();

    @Test(description = "Read CSV file in local.")
    public void TestCase4(){
        System.out.println("------------Read CSV file in local------------");
        String filePath = "/downloads/car.csv";
        List<List<String>> records = FileUtils.readCSV(filePath);
        for (List<String> record: records) {
            System.out.println(record);
        }
    }

    @Test(description = "Read JSON file in local.")
    public void TestCase5(){
        System.out.println("------------Read JSON file in local------------");
        String filePath = "/downloads/employees.json";
        JSONObject json = FileUtils.readJSON(filePath);
        List<EmployeeDetails> employeeDetailsList = FileUtils.parseEmployeeDataTest(json);
        for(EmployeeDetails employee : employeeDetailsList){
            System.out.println("id: " + employee.getId());
            System.out.println("name: " + employee.getFirstName() + " " + employee.getLastName());
            System.out.println("photo: " + employee.getPhoto());
        }
    }

    @Test(description = "Read CSV file in local.")
    public void TestCase6() {
        System.out.println("------------Read CSV file in local------------");
        List<CarDetails> carDetailsList = FileUtils.readCSVByScanner();
        for (int i = 1; i < carDetailsList.size(); i++) {
            System.out.println("year.." + carDetailsList.get(i).getCarYear());
            System.out.println("make.." + carDetailsList.get(i).getCarMake());
            System.out.println("model.." + carDetailsList.get(i).getCarModel());
        }
    }

        @Test(description = "Download file from URL.")
    public void TestCase7(){
        String url = "https://khanhdobucket.s3-ap-southeast-1.amazonaws.com/downloads/employees.json";
        String fileName = "downloaded_employees.json";
        System.out.println("------------Download file from URL.------------\n" + "URL: " + url);
                try {
                    FileUtils.downloadFromUrl(url, fileName);
                } catch (Exception e) {
                    e.printStackTrace();
                }

        }
}
