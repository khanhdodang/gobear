package com.testcases;

import com.BasePage;
import com.objects.CarDetails;
import com.pages.DetailsPage;
import com.pages.SearchProductsPage;
import com.profiles.DefaultProfile;
import com.utils.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Verify_Categories_Filter_Sort_Details {
    final static Logger logger = Logger.getLogger(FileUtils.class);
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
            driver = (FirefoxDriver) setup.setupDriver(browser);
        }

        setup.maximizeWindows();
        setup.get(DefaultProfile.urlUAT);

        carDetails = new CarDetails();
        searchProductsPage = new SearchProductsPage(driver);
        detailsPage = new DetailsPage(driver);
    }

    @Test(description = "Verify 3 cards are being displayed.")
    public void TestCase1() {
        System.out.println("-----------Verify 3 cards are being displayed.-----------");
        System.out.println("Step: Search for Car Insurance Plans");
        CarDetails searchCarIns = searchProductsPage.searchCarInsurance();
        System.out.println("Step: Assert default search info for Car Insurance");
        detailsPage.assertDefaultCarSearch(searchCarIns);
        System.out.println("Step: Get Car details in left navigation of Car Insurance detail page");
        CarDetails carInfoOnLeftNav = detailsPage.getCarDetailsOnLeftNav();
        System.out.println("Step: Assert card details on left navigation of Car Insurance detail page");
        Assert.assertEquals(carInfoOnLeftNav, searchCarIns);
        System.out.println("Step: Assert Car insurance plans");
        detailsPage.assertCarInsurancePlans();
    }

    @Test(description = "Verify Sort Functions")
    public void TestCase2() {
        System.out.println("-----------Verify Sort Functions-----------");
        searchProductsPage.searchCarInsurance();

        System.out.println("Select Sort Insurer A to Z");
        detailsPage.assertSortOptions(3);

        System.out.println("Select Sort Insurer Z to A");
        detailsPage.assertSortOptions(4);

        System.out.println("Select Sort Price: High to low");
        detailsPage.assertSortOptions(2);

        System.out.println("Select Sort Price: Low to High");
        detailsPage.assertSortOptions(1);
    }

    @Test(description = "Verify Filter Functions")
    public void TestCase3() {
        System.out.println("-----------Verify Filter Functions-----------");
        searchProductsPage.searchCarInsurance();
        // Filter Insurer
        detailsPage.assertFilterInsurer("MAPFRE Insular", "1");
        detailsPage.assertFilterInsurer("QBE Seaboard", "1");
        // Filter Benefits
        detailsPage.assertFilterBenefit("Roadside Assistance", "3");
        detailsPage.assertFilterBenefit("Personal Accident", "6");
        // Filter Car Usage
        detailsPage.assertFilterCarUsage("CarUsageForHireUberGrab", "0");
        detailsPage.assertFilterCarUsage("PRIVATE", "6");
        // Filter Loan Status
        detailsPage.assertFilterLoanStatus("NO", "6");
        detailsPage.assertFilterLoanStatus("YES", "6");
        // Select Car Details
        detailsPage.assertFilterCarDetails("2017", "SUZUKI", "Alto 800", "I don't know", "6");
    }

    @AfterTest
    public void TearDown() {
        if (driver != null) driver.quit();
    }
}
