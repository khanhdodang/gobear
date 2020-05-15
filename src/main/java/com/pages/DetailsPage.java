package com.pages;

import com.BasePage;
import com.data.DetailsPageData;
import com.objects.CarDetails;
import com.objects.CardDetails;
import com.profiles.DefaultProfile;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class DetailsPage extends BasePage {

    // Elements on Card Details
    String QBESeaboardHeader = "//h4[text()='QBE Seaboard']";
    String QBESeaboardLogo = "//img[contains(@src, \"https://gobear-images-cdn.azureedge.net/ph/insuerlogos/81adbc97-b0cf-4984-aae6-26266700be29.png\") and @alt=\"QBE Seaboard\"]";
    String MAPFREInsularHeader = "//h4[text()='MAPFRE Insular']";
    String MAPFREInsularLogo = "//img[contains(@src, \"https://gobear-images-cdn.azureedge.net/sg-acc/insuerlogos/f31aed50-38f6-4067-b1e3-b9d62fcc4899.png\") and @alt=\"MAPFRE Insular\"]";
    String PNBGeneralInsurersHeader = "//h4[text()='PNB General Insurers']";
    String PNBGeneralInsurersLogo = "//img[contains(@src, \"https://gobear-images-cdn.azureedge.net/sg-acc/insuerlogos/2910cdd1-7661-4b40-bd08-2135f4d049e4.png\") and @alt=\"PNB General Insurers\"]";
    String AddOnButton = "//a[@role=\"button\" and contains(text(), \"Add on\")]";
    String CompareButton = "//a[@data-gb-name=\"card-compare-button\" and contains(text(), \"Compare\")]";
    String ReadMoreButton = "//a[contains(@class, \"btn-read-more\") and contains(text(), \"Read more\")]";
    String ContactProviderButton = "//a[contains(@class, \"btn-checkout\") and contains(text(), \"CONTACT PROVIDER\")]";
    String YearButton = "//div[@data-gb-name=\"car-year\"]/button";
    String MakeButton = "//div[@data-gb-name=\"car-make\"]/button";
    String ModelButton = "//div[@data-gb-name=\"car-model\"]/button";
    String VariantButton = "//div[@data-gb-name=\"car-variant\"]/button";

    // Elements on Content Details
    String TopCoveragesContainer = "//div[@data-gb-name=\"top-coverages\"]";
    String PolicyPrice = "//div[@class=\"policy-price\"]";
    String PlanName = "//div[@data-gb-name=\"car-plan\"]//h4[contains(@class,\"name\")]";
    String OwnDamageAndTheftLabel = "//div[@data-gb-name=\"top-coverages\"]//div/p[contains(@class, \"detail-key\") and text()=\"Own Damage & Theft\"]";
    String VTPLBodilyInjuryLabel = "//div[@data-gb-name=\"top-coverages\"]//div/p[contains(@class, \"detail-key\") and text()=\"VTPL-Bodily Injury\"]";
    String VTPLPropertyDamageLabel = "//div[@data-gb-name=\"top-coverages\"]//div/p[contains(@class, \"detail-key\") and text()=\"VTPL-Property Damage\"]";
    String ActOfNatureLabel = "//div[@data-gb-name=\"top-coverages\"]//div/p[contains(@class, \"detail-key\") and text()=\"Act of Nature\"]";
    String WhenSomeoneInYourCarGetsHurtLabel = "//div[@data-gb-name=\"top-coverages\"]//div/p[contains(@class, \"detail-key\") and text()=\"When someone in your car gets hurt\"]";

    // Elements on Left sidebar
    String CarYearSelectedOption_CarDetails = "//div[@data-gb-name=\"car-details\"]//div[@data-gb-name=\"car-year\"]//span[@class=\"filter-option clearfix\"]";
    String CarMakeSelectedOption_CarDetails = "//div[@data-gb-name=\"car-details\"]//div[@data-gb-name=\"car-make\"]//span[@class=\"filter-option clearfix\"]";
    String CarModelSelectedOption_CarDetails = "//div[@data-gb-name=\"car-details\"]//div[@data-gb-name=\"car-model\"]//span[@class=\"filter-option clearfix\"]";
    String CarVariantSelectedOption_CarDetails = "//div[@data-gb-name=\"car-details\"]//div[@data-gb-name=\"car-variant\"]//span[@class=\"filter-option clearfix\"]";
    String StillPayingForCarLoanRadio_PlanDetails = "//div[@data-gb-name=\"plan-details\"]//div[@data-gb-name=\"loan-status\"]//label[contains(text(), \"Yes\")]";
    String CarUsageRadio_PlanDetails = "//div[@data-gb-name=\"plan-details\"]//div[@data-gb-name=\"car-usage\"]//label[contains(text(), \"PRIVATE\")]";
    String CarInsuredForSelectedOption_PlanDetails = "//div[@data-gb-name=\"plan-details\"]//div[@data-gb-name=\"select-insured\"]//span[@class=\"filter-option clearfix\"]";

    // Filter Car Usage
    String CarUsagePrivate = "//div[@data-gb-name=\"car-usage\"]//input/following-sibling::label[contains(text(), \"PRIVATE\")]";
    String CarUsageForHireUberGrab = "//div[@data-gb-name=\"car-usage\"]//input/following-sibling::label[contains(text(), \"ForHire/Uber/Grab\")]";

    // Filter Load Status
    String LoanStatusYes = "//div[@data-gb-name=\"loan-status\"]//input/following-sibling::label[contains(text(), \"Yes\")]";
    String LoanStatusNo = "//div[@data-gb-name=\"loan-status\"]//input/following-sibling::label[contains(text(), \"No\")]";

    // Insured
    String InsuredDropdown = "//div[@data-gb-name=\"select-insured\"]//select";
    // Filters Elements
    String cancelButton = "//div[@class=\"popover-navigation\"]/button[text()=\"cancel\"]";
    String ClearAllButton = "//a[@data-gb-name=\"filter-reset\" and text()=\"CLEAR ALL\"]";

    // Sort Elements
    String PriceLowToHigh = "//div[@class=\"sort-detail sidebar-item\"]//input[@value=\"premium-Asc\"]/following-sibling::label";
    String PriceHighToLow = "//div[@class=\"sort-detail sidebar-item\"]//input[@value=\"premium-Desc\"]/following-sibling::label";
    String InsurerAZ = "//div[@class=\"sort-detail sidebar-item\"]//input[@value=\"insurerName-Asc\"]/following-sibling::label";
    String InsurerZA = "//div[@class=\"sort-detail sidebar-item\"]//input[@value=\"insurerName-Desc\"]/following-sibling::label";

    String NoPlansMessage = "//p[text()=\"No plans match your filter criteria…\"]";

    private WebDriver driver;

    public DetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean doesQBESeaboardHeaderExist() {
        return doesElementExist(driver, By.xpath(QBESeaboardHeader));
    }

    public boolean doesQBESeaboardLogoExist() {
        return doesElementExist(driver, By.xpath(QBESeaboardLogo));
    }

    public boolean doesMAPFREInsularHeaderExist() {
        return doesElementExist(driver, By.xpath(MAPFREInsularHeader));
    }

    public boolean doesMAPFREInsularLogoExist() {
        return doesElementExist(driver, By.xpath(MAPFREInsularLogo));
    }

    public boolean doesPNBGeneralInsurersHeaderExist() {
        return doesElementExist(driver, By.xpath(PNBGeneralInsurersHeader));
    }

    public boolean doesPNBGeneralInsurersLogoExist() {
        return doesElementExist(driver, By.xpath(PNBGeneralInsurersLogo));
    }

    public boolean doesNoPlansExist() {
        return doesElementExist(driver, By.xpath(NoPlansMessage));
    }

    public boolean doesResults1Exist(String total) {
        return doesElementExist(driver, By.xpath(searchResultsText1(total)));
    }

    public boolean doesResults2Exist(String result) {
        return doesElementExist(driver, By.xpath(searchResultsText2(result)));
    }

    public int getTotalAddonButton() {
        return getTotalElements(driver, By.xpath(AddOnButton));
    }

    public int getTotalCompareButton() {
        return getTotalElements(driver, By.xpath(CompareButton));
    }

    public int getTotalReadMoreButton() {
        return getTotalElements(driver, By.xpath(ReadMoreButton));
    }

    public int getTotalContactProviderButton() {
        return getTotalElements(driver, By.xpath(ContactProviderButton));
    }

    public int getTotalOwnDamageAndTheftLabel() {
        return getTotalElements(driver, By.xpath(OwnDamageAndTheftLabel));
    }

    public int getTotalVTPLBodilyInjuryLabel() {
        return getTotalElements(driver, By.xpath(VTPLBodilyInjuryLabel));
    }

    public int getTotalVTPLPropertyDamageLabel() {
        return getTotalElements(driver, By.xpath(VTPLPropertyDamageLabel));
    }

    public int getTotalActOfNatureLabel() {
        return getTotalElements(driver, By.xpath(ActOfNatureLabel));
    }

    public int getTotalWhenSomeoneInYourCarGetsHurtLabel() {
        return getTotalElements(driver, By.xpath(WhenSomeoneInYourCarGetsHurtLabel));
    }

    public CarDetails getDefaultCarDetails() {
        CarDetails carDetails = new CarDetails();
        carDetails.setCarYear(DetailsPageData.CAR_YEAR);
        carDetails.setCarMake(DetailsPageData.CAR_MAKE);
        carDetails.setCarModel(DetailsPageData.CAR_MODEL);
        carDetails.setCarVariant(DetailsPageData.CAR_VARIANT);
        carDetails.setCarUsage(DetailsPageData.CAR_USAGE);
        carDetails.setStillPayingForCarLoan(DetailsPageData.STILL_PAYING_FOR_CAR_LOAN);
        carDetails.setInsuredFor(DetailsPageData.INSURED_FOR);
        return carDetails;
    }

    public CarDetails getCarDetails() {
        CarDetails carDetails = new CarDetails();
        carDetails.setCarYear(getTextFromElement(driver, By.xpath(CarYearSelectedOption_CarDetails)));
        carDetails.setCarMake(getTextFromElement(driver, By.xpath(CarMakeSelectedOption_CarDetails)));
        carDetails.setCarModel(getTextFromElement(driver, By.xpath(CarModelSelectedOption_CarDetails)));
        carDetails.setCarVariant(getTextFromElement(driver, By.xpath(CarVariantSelectedOption_CarDetails)));
        carDetails.setCarUsage(getTextFromElement(driver, By.xpath(CarUsageRadio_PlanDetails)));
        carDetails.setStillPayingForCarLoan(isElementSelected(driver, By.xpath(StillPayingForCarLoanRadio_PlanDetails)));
        carDetails.setInsuredFor(getTextFromElement(driver, By.xpath(CarInsuredForSelectedOption_PlanDetails)));
        return carDetails;
    }

    public CardDetails getDefaultCardDetails() {
        CardDetails cardDetails = new CardDetails();
        cardDetails.setOwnDamageAndTheft(DetailsPageData.OWN_DAMAGE_AND_THEFT);
        cardDetails.setVTPLBodilyInjury(DetailsPageData.VTPL_BODILY_INJURY);
        cardDetails.setVTPLPropertyDamage(DetailsPageData.VTPL_PROPERTY_DAMAGE);
        cardDetails.setActOfNature(DetailsPageData.ACT_OF_NATURE);
        cardDetails.setWhenSomeoneInYourCarGetsHurt(DetailsPageData.WHEN_SOMEONE_IN_YOUR_CAR_GETS_HURT);
        return cardDetails;
    }

    public List<CardDetails> getCardDetails() {
        List<CardDetails> cardDetailsList = new ArrayList<CardDetails>();
        int total = getTotalElements(driver, By.xpath(TopCoveragesContainer));
        System.out.println("Number of car insurance plans found " + total);
        for (int i = 1; i < total + 1; i++) {
            CardDetails cardDetails = new CardDetails();
            cardDetails.setOwnDamageAndTheft(getTextFromElements(driver, By.xpath(cardFullSelector(i)), 0));
            cardDetails.setVTPLBodilyInjury(getTextFromElements(driver, By.xpath(cardFullSelector(i)), 1));
            cardDetails.setVTPLPropertyDamage(getTextFromElements(driver, By.xpath(cardFullSelector(i)), 2));
            cardDetails.setActOfNature(getTextFromElements(driver, By.xpath(cardFullSelector(i)), 3));
            cardDetails.setWhenSomeoneInYourCarGetsHurt(getTextFromElements(driver, By.xpath(cardFullSelector(i)), 4));
            cardDetailsList.add(cardDetails);
        }
        return cardDetailsList;
    }

    public ArrayList<String> getPrices() {
        ArrayList<String> arrayList = new ArrayList<String>();
        int total = getTotalElements(driver, By.xpath(TopCoveragesContainer));

        for (int i = 0; i < total; i++) {
            arrayList.add(getTextFromElements(driver, By.xpath(PolicyPrice), i));
        }
        return arrayList;
    }

    public ArrayList<String> getPlanNames() {
        ArrayList<String> arrayList = new ArrayList<String>();
        int total = getTotalElements(driver, By.xpath(TopCoveragesContainer));

        for (int i = 0; i < total; i++) {
            arrayList.add(getTextFromElements(driver, By.xpath(PlanName), i));
        }
        return arrayList;
    }

    public void waitContainerDisplay() {
        waitForElementVisible(driver, By.xpath(TopCoveragesContainer), DefaultProfile.WAIT_PRESENT_TIMEOUT);
        waitForElementClickable(driver, By.xpath(TopCoveragesContainer), DefaultProfile.WAIT_PRESENT_TIMEOUT);
        scrollToTop(driver);
        sleep(1);
    }

    public void verifyUIs() {
//        System.out.println("We should integrate with http://applitools.com/ to compare images instead of selecting each element to check.");

        // 3 Logos & Headers; Note: We can do the same thing for 3 others
        Assert.assertTrue(doesQBESeaboardHeaderExist());
        Assert.assertTrue(doesQBESeaboardLogoExist());
        Assert.assertTrue(doesMAPFREInsularHeaderExist());
        Assert.assertTrue(doesMAPFREInsularLogoExist());
        Assert.assertTrue(doesPNBGeneralInsurersHeaderExist());
        Assert.assertTrue(doesPNBGeneralInsurersLogoExist());

        // Buttons in Card
        Assert.assertEquals(getTotalAddonButton(), 6);
        Assert.assertEquals(getTotalCompareButton(), 6);
        Assert.assertEquals(getTotalReadMoreButton(), 6);
        Assert.assertEquals(getTotalContactProviderButton(), 6);

        // content-detail
        Assert.assertEquals(getTotalOwnDamageAndTheftLabel(), 6);
        Assert.assertEquals(getTotalVTPLBodilyInjuryLabel(), 6);
        Assert.assertEquals(getTotalVTPLPropertyDamageLabel(), 6);
        Assert.assertEquals(getTotalAddonButton(), 6);
        Assert.assertEquals(getTotalActOfNatureLabel(), 6);
        Assert.assertEquals(getTotalWhenSomeoneInYourCarGetsHurtLabel(), 6);
    }

    /**
     * Select an option to sort
     *
     * @param option PRICE_LOW_HIGH, PRICE_HIGH_LOW, INSURER_AZ, INSURER_ZA
     */
    public void sortDetail(String option) {
        option = option.toUpperCase();
        if (option.equals("PRICE_LOW_HIGH")) {
            sortDetail(1);
        } else if (option.equals("PRICE_HIGH_LOW")) {
            sortDetail(2);
        } else if (option.equals("INSURER_AZ")) {
            sortDetail(3);
        } else if (option.equals("INSURER_ZA")) {
            sortDetail(4);
        } else {
            System.out.println("The option is incorrect. Please check again.");
        }
    }

    /**
     * Select an option to sort
     *
     * @param option 1:PRICE_LOW_HIGH, 2:PRICE_HIGH_LOW, 3:INSURER_AZ, 4:INSURER_ZA
     */
    public void sortDetail(int option) {
        scrollToY(driver, 200);
        switch (option) {
            case 1:
                click(driver, By.xpath(PriceLowToHigh));
                break;
            case 2:
                click(driver, By.xpath(PriceHighToLow));
                break;
            case 3:
                click(driver, By.xpath(InsurerAZ));
                break;
            case 4:
                click(driver, By.xpath(InsurerZA));
                break;
            default:
                break;
        }
        waitContainerDisplay();
    }

    /**
     * Filter Insurers
     *
     * @param filterName Alpha Insurance, MAPFRE Insular, Pacific Union Insurance,
     *                   PhilsFirst Insurance, PNB General Insurers, QBE Seaboard
     */
    public void filterInsurers(String filterName) {
        WebElement filter = driver.findElement(By.xpath(filterElementInsurerId(filterName)));
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(filter));
        filter.click();
        waitContainerDisplay();
    }

    /**
     * Filter Benefits
     *
     * @param filterName Act of Nature, Personal Accident, Roadside Assistance
     */
    public void filterBenefits(String filterName) {
        click(driver, By.xpath(filterElementBenefits(filterName)));
        waitContainerDisplay();
    }

    /**
     * Filter Car Usage
     *
     * @param filterName PRIVATE, CarUsageForHireUberGrab
     */
    public void filterCarUsage(String filterName) {
        if (filterName.toUpperCase().equals("PRIVATE")) {
            click(driver, By.xpath(CarUsagePrivate));
            waitContainerDisplay();
        } else {
            click(driver, By.xpath(CarUsageForHireUberGrab));
        }
    }

    public void editYear(String year) {
        System.out.println("Edit Year value to: " + year);
        click(driver, By.xpath(YearButton));
        click(driver, By.xpath(YearDropdown(year)));
    }


    public void editMake(String make) {
        System.out.println("Edit Make value to: " + make);
        click(driver, By.xpath(MakeButton));
        click(driver, By.xpath(MakeDropdown(make)));
    }

    public void editModel(String model) {
        System.out.println("Edit Model value to: " + model);
        click(driver, By.xpath(ModelButton));
        click(driver, By.xpath(ModelDropdown(model)));
    }

    public void editVariant(String variant) {
        System.out.println("Edit Variant value to: " + variant);
        click(driver, By.xpath(VariantButton));
        click(driver, By.xpath(VariantDropdown(variant)));
    }

    /**
     * Filter LoanStatus
     *
     * @param filterName Yes, No
     */
    public void filterLoanStatus(String filterName) {
        if (filterName.toUpperCase().equals("YES")) {
            click(driver, By.xpath(LoanStatusYes));
        } else {
            click(driver, By.xpath(LoanStatusNo));
        }
        waitContainerDisplay();
        sleep(3);
    }

    /**
     * Clear Filter Insurer
     */
    public void clearFilterInsurer() {
        scrollToY(driver, 400);
        System.out.println("Resetting filters..");
        click(driver, By.xpath(ClearAllButton));
        waitContainerDisplay();
    }

    public void dismissPopup() {
        if (doesElementExist(driver, By.xpath(cancelButton))) {
            click(driver, By.xpath(cancelButton));
            sleep(2);
        }
    }

    @Override
    public void sleep(int seconds) {
        super.sleep(seconds);
    }

    String cardFullSelector(int index) {
        return "(//div[@data-gb-name=\"top-coverages\"])[" + index + "]//div/p[contains(@class, \"detail-value\")]";
    }

    String filterElementInsurerId(String dataFilterName) {
        return "//div[@data-filter-by=\"insurerId\"]//div[@data-filter-name=\"" + dataFilterName + "\"]";
    }

    String filterElementBenefits(String dataFilterName) {
        return "//div[@data-filter-by=\"benefits\"]//div[@data-filter-name=\"" + dataFilterName + "\"]";
    }

    String searchResultsText1(String result) {
        return "//div[contains(@class,  \"results-text\")]//*[text()=\"" + result + " car insurance plans found\"]";
    }

    String searchResultsText2(String result) {
        return "//div[contains(@class,  \"results-text\")]//*[contains(text(),\"" + result + "\")]";
    }

    String YearDropdown(String value) {
        return "//div[@data-gb-name=\"car-year\"]/div[@class=\"dropdown-menu open\"]/ul/li//span[text()=\"" + value + "\"]/parent::a//parent::li";
    }

    String MakeDropdown(String value) {
        return "//div[@data-gb-name=\"car-make\"]/div[@class=\"dropdown-menu open\"]/ul/li//span[text()=\"" + value + "\"]/parent::a//parent::li";
    }

    String ModelDropdown(String value) {
        return "//div[@data-gb-name=\"car-model\"]/div[@class=\"dropdown-menu open\"]/ul/li/a/span[text()=\"" + value + "\"]/parent::a//parent::li";
    }

    String VariantDropdown(String value) {
        return "//div[@data-gb-name=\"car-variant\"]/div[@class=\"dropdown-menu open\"]//span[text()=\"" + value + "\"]/parent::a//parent::li";
    }

    public CarDetails getCarDetailsOnLeftNav() {
        sleep(3);
        waitContainerDisplay();
        System.out.println("Verify Car Insurance Details page is displayed properly");
        verifyUIs();
        CarDetails carDetailsAfterSearch = this.getCarDetails();
        return carDetailsAfterSearch;
    }

    public void assertDefaultCarSearch(CarDetails carDetails) {
        Assert.assertTrue("Default Car search info does not match",carDetails.equals(getDefaultCarDetails()));
    }

    public void assertCarInsurancePlans() {
        List<CardDetails> cardDetailsList = this.getCardDetails();
        System.out.println("Verify price of each plan");
        List<String> prices = this.getPrices();
        Assert.assertEquals(prices, DetailsPageData.PRICES());

        System.out.println("Verify details of each plan");
        for (CardDetails c : cardDetailsList) {
            Assert.assertTrue(c.equals(getDefaultCardDetails()));
        }
    }

    public void assertSortOptions(int opt) {
        ArrayList<String> headers = new ArrayList<>();
        ArrayList<String> prices = new ArrayList<>();

        switch (opt) {
            case 1:
                headers = DetailsPageData.HEADERS();
                prices = DetailsPageData.PRICES();
                break;
            case 2:
                headers = DetailsPageData.HEADERS_HighLow();
                prices = DetailsPageData.PRICES_HighLow();
                break;
            case 3:
                headers = DetailsPageData.HEADERS_InsurerAZ();
                prices = DetailsPageData.PRICES_InsurerAZ();
                break;
            case 4:
                headers = DetailsPageData.HEADERS_InsurerZA();
                prices = DetailsPageData.PRICES_InsurerZA();
                break;
            default:
                break;
        }

        sortDetail(opt);

        for (int i = 0; i < this.getPlanNames().size(); i++) {
            Assert.assertEquals("Plan's name does not match", this.getPlanNames().get(i), headers.get(i));
        }

        for (int i = 0; i < this.getPrices().size(); i++) {
            Assert.assertEquals("Plan's price does not match",this.getPrices().get(i), prices.get(i));
        }
    }

    public void assertFilterInsurer(String insurer, String expectedTotalPlanFound) {
        clearFilterInsurer();
        System.out.println("Filter Insurer: " + insurer);
        filterInsurers(insurer);
        Assert.assertEquals("Not found " + insurer, getPlanNames().get(0), insurer);
        Assert.assertTrue("Number not match with expected: " + expectedTotalPlanFound, doesResults1Exist(expectedTotalPlanFound));
    }

    public void assertFilterBenefit(String benefit, String expectedTotalPlanFound) {
        clearFilterInsurer();
        System.out.println("Filter Benefits: " + benefit);
        filterBenefits(benefit);
        Assert.assertTrue("Number not match with expected: " + expectedTotalPlanFound, doesResults1Exist(expectedTotalPlanFound));
    }

    public void assertFilterCarUsage(String usage, String expectedTotalPlanFound) {
        System.out.println("Filter Car Usage: " + usage);
        filterCarUsage(usage);
        Assert.assertTrue("Number not match with expected: " + expectedTotalPlanFound, doesResults1Exist(expectedTotalPlanFound));
        filterCarUsage("PRIVATE");
    }

    public void assertFilterLoanStatus(String status, String expectedTotalPlanFound) {
        filterLoanStatus(status);
        if (status.equalsIgnoreCase("NO")) {
            for (int i = 0; i < this.getPlanNames().size(); i++) {
                Assert.assertEquals("Plans does not match", this.getPlanNames().get(i), DetailsPageData.HEADERS_LoanStatusNo().get(i));
            }
        } else {
            for (int i = 0; i < this.getPlanNames().size(); i++) {
                Assert.assertEquals("Plans does not match", this.getPlanNames().get(i), DetailsPageData.HEADERS().get(i));
            }
        }
        Assert.assertTrue("Number not match with expected: " + expectedTotalPlanFound, doesResults1Exist(expectedTotalPlanFound));
    }

    public void assertFilterCarDetails(String year, String make, String model, String variant, String expectedTotalPlanFound) {
        clearFilterInsurer();
        this.scrollToTop(driver);
        // Select Car Details
        editYear(year);
        editMake(make);
        editModel(model);
        editVariant(variant);
        Assert.assertTrue("Number not match with expected: " + expectedTotalPlanFound, this.doesResults1Exist(expectedTotalPlanFound));
        Assert.assertTrue("Car details not match", this.doesResults2Exist(year + " | " + make + " | " + model));
    }
}
