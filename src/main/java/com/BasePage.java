package com;

import com.profiles.DefaultProfile;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class BasePage {
    final static Logger logger = Logger.getLogger(BasePage.class);
    private RemoteWebDriver driver = null;

    /**
     * setup Chrome/ Firefox Driver on Mac
     */
    public WebDriver setupDriver(String browser) {
        if (browser.toLowerCase().equals("chrome")) {
            if (System.getProperty("os.name").contains("Mac")) {
                File cDriver = new File(BasePage.class.getResource(DefaultProfile.MAC_CHROME_DRIVER).getFile());
//                System.setProperty("webdriver.chrome.driver", BasePage.class.getResource(DefaultProfile.MAC_CHROME_DRIVER).getFile());
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/resources/drivers/chromedriver");

                // Now checking for existence of Chrome executable.
                if (!new File("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome").exists()) {
                    throw new RuntimeException("Cannot find chromedriver file. Please download and copy to drivers folder in current project");
                }
            }

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--ignore-certificate-errors");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().pageLoadTimeout(DefaultProfile.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(DefaultProfile.IMPLICIT_TIMEOUT, TimeUnit.SECONDS);

        } else if (browser.toLowerCase().equals("firefox")) {
            if (System.getProperty("os.name").contains("Mac")) {
//                System.setProperty("webdriver.gecko.driver", BasePage.class.getResource(DefaultProfile.MAC_FIREFOX_DRIVER).getFile());
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/src/main/resources/drivers/geckodriver");

                // Now checking for existence of Firefox executable.
                if (!new File("/Applications/Firefox.app/Contents/MacOS/firefox").exists()) {
                    //noinspection SpellCheckingInspection
                    throw new RuntimeException("Cannot find geckodriver file. Please download and copy to drivers folder in current project");
                }
            }
            driver = new FirefoxDriver();
        }
        return driver;
    }

    /**
     * Click on an element
     * @param driver receive the driver
     * @param by receive the element
     */
    public void click(WebDriver driver, By by) {
        try {
            WebElement element = driver.findElement(by);
            element.click();
        } catch (Exception ex) {
            logger.error("Unable to find element "+ by, ex);
        }
    }

    /**
     * Check the element displayed ot not
     * @param driver receive the driver
     * @param by receive the element
     */
    public boolean isDisplayed(WebDriver driver, By by) {
        try {
            WebElement element = driver.findElement(by);
            return element.isDisplayed();
        } catch (Exception ex) {
            logger.error("Unable to find element "+ by, ex);
        }
        return false;
    }

    /**
     * Wait for the element visible with the specific timeout
     * @param driver receive the driver
     * @param by receive the element
     * @param timeout in seconds
     */
    public void waitForElementVisible(WebDriver driver, By by, int timeout) {
        try {
            WebElement element = driver.findElement(by);
            WebDriverWait wait = new WebDriverWait(driver,timeout);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception ex) {
            logger.error("Unable to find element "+ by, ex);
        }
    }

    /**
     * Wait for the element clickable with the specific timeout
     * @param driver receive the driver
     * @param by receive the element
     * @param timeout in seconds
     */
    public void waitForElementClickable(WebDriver driver, By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception ex) {
            logger.error("Unable to find element "+ by, ex);
        }
    }

    /**
     * Return the element is existed or not
     * @param driver receive the driver
     * @param by receive the element
     */
    public boolean doesElementExist(WebDriver driver, By by) {
        boolean flag = false;
        try {
            driver.findElement(by);
            flag = true;
        }  catch (NoSuchElementException ex) {
            logger.error("No such element "+ by, ex);
        } catch (Exception ex) {
            logger.error("Unable to find element "+ by, ex);
        }
        return flag;
    }

    /**
     * Return the element is selected or not
     * @param driver receive the driver
     * @param by receive the element
     */
    public boolean isElementSelected(WebDriver driver, By by) {
        boolean flag = false;
        try {
            driver.findElement(by).isSelected();
            flag = true;
        }  catch (NoSuchElementException ex) {
            logger.error("No such element "+ by, ex);
        } catch (Exception ex) {
            logger.error("Unable to find element "+ by, ex);
        }
        return flag;
    }

    /**
     * Return the text of element
     * @param driver receive the driver
     * @param by receive the element
     */
    public String getTextFromElement(WebDriver driver, By by) {
        String text = "";
        try {
            text = driver.findElement(by).getText();
        }  catch (NoSuchElementException ex) {
            logger.error("No such element "+ by, ex);
        } catch (Exception ex) {
            logger.error("Unable to find element "+ by, ex);
        }
        return text;
    }

    /**
     * Return the text of element
     * @param driver receive the driver
     * @param by receive the element
     */
    public String getTextFromElements(WebDriver driver, By by, int index) {
        String text = "";
        try {
            List<WebElement> listOfElements = driver.findElements(by);
            text = listOfElements.get(index).getText();
        }  catch (NoSuchElementException ex) {
            logger.error("No such element "+ by, ex);
        } catch (Exception ex) {
            logger.error("Unable to find element "+ by, ex);
        }
        return text;
    }

    /**
     * Return the total elements
     * @param driver receive the driver
     * @param by receive the element
     */
    public int getTotalElements(WebDriver driver, By by) {
        int total = 0;
        try {
            List<WebElement> listOfElements = driver.findElements(by);
            return listOfElements.size();
        } catch (Exception ex) {
            logger.error("Unable to find element "+ by, ex);
        }
        return total;
    }

    /**
     * Scroll to the top of the page
     * @param driver receive the driver
     */
    public void scrollToTop(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
    }

    /**
     * Scroll to y coordinate
     * @param driver receive the driver
     * @param y y coordinate
     */
    public void scrollToY(WebDriver driver, int y) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, " + y + ")");
    }

    /**
     * Maximize windows
     */
    public void maximizeWindows() {
        try {
            driver.manage().window().maximize();
        } catch (Exception ex) {
            logger.error("Error maximizing window", ex);
        }
    }

    /**
     * Go to the URL
     * @param url
     */
    public void get(String url) {
        try {
            driver.get(url);
        } catch (Exception ex) {
            logger.error("Error getting url " + url, ex);
        }
    }

    /**
     * sleep for x seconds
     * @param seconds
     */
    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception ex) {
            logger.error("Unexpected error", ex);
        }
    }
}
