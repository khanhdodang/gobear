package com.testcases;
import com.BasePage;
import com.data.DetailsPageData;
import com.objects.CarDetails;
import com.objects.CardDetails;
import com.pages.DetailsPage;
import com.pages.SearchProductsPage;
import com.profiles.DefaultProfile;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class Verify_Categories_Filter_Sort_Details {

    private RemoteWebDriver driver = null;
    SearchProductsPage searchProductsPage;
    DetailsPage detailsPage;
    CarDetails carDetails;

    @Parameters({"browser"})
    @BeforeClass
    public void SetUp(@Optional("chrome") String browser) {
        BasePage setup = new BasePage();
        if (browser.toLowerCase().equals("chrome")) {
            driver = (ChromeDriver) setup.setupDriver(browser);
        } else {
            driver = (FirefoxDriver)setup.setupDriver(browser);
        }

        setup.maximizeWindows();
        setup.get(DefaultProfile.urlUAT);

        carDetails = new CarDetails();
        searchProductsPage = new SearchProductsPage(driver);
    }

    @Test(description = "Verify 3 cards are being displayed.")
    public void TestCase1() {
        System.out.println("-----------Verify 3 cards are being displayed.-----------");
        searchProductsPage.clickInsuranceTab();
        searchProductsPage.clickCarTab();
        searchProductsPage.waitForkShowMyResultsButtonClickable();

        carDetails = searchProductsPage.getCarDetails();
        Assert.assertTrue(carDetails.equals(getDefaultCarDetails()));

        searchProductsPage.clickShowMyResultsButton();

        detailsPage = new DetailsPage(driver);
        detailsPage.sleep(3);
        detailsPage.waitContainerDisplay();

        verifyUIs();

        CarDetails carDetailsAfterSearch = new CarDetails();
        carDetailsAfterSearch = detailsPage.getCarDetails();
        Assert.assertTrue(carDetails.equals(carDetailsAfterSearch));

        List<CardDetails> cardDetailsList = new ArrayList<CardDetails>();
        cardDetailsList = detailsPage.getCardDetails();
        for (CardDetails c: cardDetailsList) {
            Assert.assertTrue(c.equals(getDefaultCardDetails()));
        }

        List<String> prices = new ArrayList<String>();
        prices = detailsPage.getPrices();
        Assert.assertEquals(prices, DetailsPageData.PRICES());
    }

    @Test(description = "Verify Sort Functions")
    public void TestCase2() {
        System.out.println("-----------Verify Sort Functions-----------");
        System.out.println("Select Sort Insurer A to Z");
        detailsPage.sortDetail(3);

        for (int i = 0; i < detailsPage.getPlanNames().size(); i++) {
            Assert.assertEquals(detailsPage.getPlanNames().get(i), DetailsPageData.HEADERS_InsurerAZ().get(i));
        }

        for (int i = 0; i < detailsPage.getPrices().size(); i++) {
            Assert.assertEquals(detailsPage.getPrices().get(i), DetailsPageData.PRICES_InsurerAZ().get(i));
        }

        System.out.println("Select Sort Insurer Z to A");
        detailsPage.sortDetail(4);

        for (int i = 0; i < detailsPage.getPlanNames().size(); i++) {
            Assert.assertEquals(detailsPage.getPlanNames().get(i), DetailsPageData.HEADERS_InsurerZA().get(i));
        }

        for (int i = 0; i < detailsPage.getPrices().size(); i++) {
            Assert.assertEquals(detailsPage.getPrices().get(i), DetailsPageData.PRICES_InsurerZA().get(i));
        }

        System.out.println("Select Sort Price: High to low");
        detailsPage.sortDetail(2);

        for (int i = 0; i < detailsPage.getPlanNames().size(); i++) {
            Assert.assertEquals(detailsPage.getPlanNames().get(i), DetailsPageData.HEADERS_HighLow().get(i));
        }

        for (int i = 0; i < detailsPage.getPrices().size(); i++) {
            Assert.assertEquals(detailsPage.getPrices().get(i), DetailsPageData.PRICES_HighLow().get(i));
        }
        detailsPage.sortDetail(1);
    }

    @Test(description = "Verify Filter Functions")
    public void TestCase3() {
        System.out.println("-----------Verify Filter Functions-----------");
        // Filter Insurer
        detailsPage.filterInsurers("MAPFRE Insular");
        Assert.assertEquals(detailsPage.getPlanNames().get(0), "MAPFRE Insular");
        Assert.assertTrue(detailsPage.doesResults1Exist("1"));
        detailsPage.filterInsurers("MAPFRE Insular");

        // Filter Insurer
        detailsPage.filterInsurers("QBE Seaboard");
        Assert.assertEquals(detailsPage.getPlanNames().get(0), "QBE Seaboard");
        Assert.assertTrue(detailsPage.doesResults1Exist("1"));
        detailsPage.filterInsurers("QBE Seaboard");

        // Filter Benefits
        detailsPage.filterBenefits("Roadside Assistance");
        Assert.assertEquals(detailsPage.getPlanNames().get(0), "QBE Seaboard");
        Assert.assertTrue(detailsPage.doesResults1Exist("3"));
        detailsPage.filterBenefits("Roadside Assistance");

        // Filter Car Usage
        detailsPage.filterCarUsage("CarUsageForHireUberGrab");
        Assert.assertTrue(detailsPage.doesNoPlansExist());
        Assert.assertTrue(detailsPage.doesResults1Exist("0"));
        detailsPage.filterCarUsage("PRIVATE");

        // Filter Loan Status
        detailsPage.filterLoanStatus("NO");
        for (int i = 0; i < detailsPage.getPlanNames().size(); i++) {
            Assert.assertEquals(detailsPage.getPlanNames().get(i), DetailsPageData.HEADERS_LoanStatusNo().get(i));
        }
        detailsPage.filterLoanStatus("YES");

        // Select Car Details
        detailsPage.editYear("2017");
        detailsPage.editMake("SUZUKI");
        detailsPage.editModel("Alto 800");
        Assert.assertTrue(detailsPage.doesResults1Exist("6"));
        System.out.println("The assertion below is a bug. Nothing changes when we modify Car Details.");
        Assert.assertTrue(detailsPage.doesResults2Exist("2017 | SUZUKI | Alto 800 | ₱500,500"));
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

    public CardDetails getDefaultCardDetails() {
        CardDetails cardDetails = new CardDetails();
        cardDetails.setOwnDamageAndTheft(DetailsPageData.OWN_DAMAGE_AND_THEFT);
        cardDetails.setVTPLBodilyInjury(DetailsPageData.VTPL_BODILY_INJURY);
        cardDetails.setVTPLPropertyDamage(DetailsPageData.VTPL_PROPERTY_DAMAGE);
        cardDetails.setActOfNature(DetailsPageData.ACT_OF_NATURE);
        cardDetails.setWhenSomeoneInYourCarGetsHurt(DetailsPageData.WHEN_SOMEONE_IN_YOUR_CAR_GETS_HURT);
        return cardDetails;
    }

    public void verifyUIs() {
        System.out.println("We should integrate with http://applitools.com/ to compare images instead of selecting each element to check.");

        // 3 Logos & Headers; Note: We can do the same thing for 3 others
        Assert.assertTrue(detailsPage.doesQBESeaboardHeaderExist());
        Assert.assertTrue(detailsPage.doesQBESeaboardLogoExist());
        Assert.assertTrue(detailsPage.doesMAPFREInsularHeaderExist());
        Assert.assertTrue(detailsPage.doesMAPFREInsularLogoExist());
        Assert.assertTrue(detailsPage.doesPNBGeneralInsurersHeaderExist());
        Assert.assertTrue(detailsPage.doesPNBGeneralInsurersLogoExist());

        // Buttons in Card
        Assert.assertEquals(detailsPage.getTotalAddonButton(), 6);
        Assert.assertEquals(detailsPage.getTotalCompareButton(), 6);
        Assert.assertEquals(detailsPage.getTotalReadMoreButton(), 6);
        Assert.assertEquals(detailsPage.getTotalContactProviderButton(), 6);

        // content-detail
        Assert.assertEquals(detailsPage.getTotalOwnDamageAndTheftLabel(), 6);
        Assert.assertEquals(detailsPage.getTotalVTPLBodilyInjuryLabel(), 6);
        Assert.assertEquals(detailsPage.getTotalVTPLPropertyDamageLabel(), 6);
        Assert.assertEquals(detailsPage.getTotalAddonButton(), 6);
        Assert.assertEquals(detailsPage.getTotalActOfNatureLabel(), 6);
        Assert.assertEquals(detailsPage.getTotalWhenSomeoneInYourCarGetsHurtLabel(), 6);
    }

    @AfterTest
    public void TearDown() {
        if (driver != null) driver.quit();
    }
}
