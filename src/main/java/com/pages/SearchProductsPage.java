package com.pages;

import com.BasePage;
import com.objects.CarDetails;
import com.profiles.DefaultProfile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchProductsPage extends BasePage {

    private final WebDriver driver;
    DetailsPage detailsPage;

    public SearchProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    final String InsuranceTab = "//a[@aria-controls=\"Insurance\" and @role=\"tab\"]";
    final String CarTab = "//a[@aria-controls=\"Car\" and @role=\"tab\"]";
    final String ShowMyResultsButton = "product-form-submit";
    final String CarInfo = "//div[@id=\"car-form\"]//div[@gb-data-name=\"car-info\"]//div[contains(@class, \"select-component\")]//span[@class=\"filter-option clearfix\"]";
    final String CarVariant = "//div[@id=\"car-form\"]//div[@gb-data-name=\"car-variant\"]//div[contains(@class, \"select-component\")]//span[@class=\"filter-option clearfix\"]";
    final String StillPayingForMyCarLoan = "//input[@name=\"car-form-stillPaying\"]";
    final String CarUsage = "//div[@gb-data-name=\"car-usage\"]//span[@class=\"filter-option clearfix\"]";
    final String InsuredFor = "//div[@gb-data-name=\"sum-insured\"]//span[@class=\"filter-option clearfix\"]";

    public void clickInsuranceTab() {
        click(driver, By.xpath(InsuranceTab));
    }

    public void clickCarTab() {
        click(driver, By.xpath(CarTab));
    }

    public void clickShowMyResultsButton() {
        waitForElementClickable(driver, By.name(ShowMyResultsButton), DefaultProfile.WAIT_PRESENT_TIMEOUT);
        click(driver, By.name(ShowMyResultsButton));
    }

    public void waitForkShowMyResultsButtonClickable() {
        waitForElementClickable(driver, By.name(ShowMyResultsButton), DefaultProfile.WAIT_PRESENT_TIMEOUT);
    }

    public CarDetails getCarDetails() {
        CarDetails carDetails = new CarDetails();
        carDetails.setCarYear(getTextFromElements(driver, By.xpath(CarInfo), 0));
        carDetails.setCarMake(getTextFromElements(driver, By.xpath(CarInfo), 1));
        carDetails.setCarModel(getTextFromElements(driver, By.xpath(CarInfo), 2));
        carDetails.setCarVariant(getTextFromElement(driver, By.xpath(CarVariant)));
        carDetails.setCarUsage(getTextFromElement(driver, By.xpath(CarUsage)));
        carDetails.setStillPayingForCarLoan(isElementSelected(driver, By.xpath(StillPayingForMyCarLoan)));
        carDetails.setInsuredFor(getTextFromElement(driver, By.xpath(InsuredFor)));
        return carDetails;
    }

    public CarDetails searchCarInsurance() {
        System.out.println("Search for Car Insurance");
        clickInsuranceTab();
        clickCarTab();
        waitForkShowMyResultsButtonClickable();
        detailsPage = new DetailsPage(driver);
        CarDetails carDetails = this.getCarDetails();
        clickShowMyResultsButton();
        detailsPage.dismissPopup();
        return carDetails;
    }
}
