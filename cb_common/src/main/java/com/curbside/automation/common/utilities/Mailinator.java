package com.curbside.automation.common.utilities;

import com.curbside.automation.uifactory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by hitesh.grover on 21/08/17.
 */
public class Mailinator {

    static WebDriver driver;
    static By mailCheckboxes = By.xpath(".//*[@id='inboxpane']//div[@class = 'all_message-min-check-container']");
    static By btnDelete = By.xpath(".//*[@id='status_bar']/div[5]/span[3]/i[2]");
    static By lblMailsSubject = By.xpath(".//*[@id='inboxpane']//div[@class = 'all_message-min_text all_message-min_text-3']");

    public static void setChromeDriver(String userID) throws Throwable {
        DriverFactory.getDriver().getPageSource();
        String baseURL = "https://www.mailinator.com/v2/inbox.jsp?zone=public&query=" + userID + "#/#inboxpane";
        System.setProperty("webdriver.chrome.driver", "../chromedriver");
        try {
            driver = new ChromeDriver();
        } catch (Exception e) {
            DriverFactory.getDriver().getPageSource();
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        DriverFactory.getDriver().getPageSource();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        DriverFactory.getDriver().getPageSource();
        driver.get(baseURL);
        DriverFactory.getDriver().getPageSource();
    }

    public static boolean isMailReceived(String userID, String subjectLine) throws Throwable {
        boolean isMailPresent = false;
        setChromeDriver(userID);

        List<WebElement> allMailsSubject = driver.findElements(lblMailsSubject);
        for (WebElement mailSubject : allMailsSubject) {
            if (mailSubject.getText().equalsIgnoreCase(subjectLine))
                isMailPresent = true;
        }
        DriverFactory.getDriver().getPageSource();
        deleteMails();
        try {
            driver.close();
        } catch (Exception e) {
            driver.quit();
        }
        return isMailPresent;
    }

    public static void deleteMails() throws Throwable {

        int noOfCheckboxes = driver.findElements(mailCheckboxes).size();
        if (noOfCheckboxes >= 5)
            noOfCheckboxes = 5;

        if (noOfCheckboxes >= 1) {
            for (int i = 1; i <= noOfCheckboxes; i++) {
                String checkbox =
                        "(.//*[@id='inboxpane']//div[@class = 'all_message-min-check-container'])" + '[' + i + ']';
                driver.findElement(By.xpath(checkbox)).click();
                Thread.sleep(1000);
            }
            DriverFactory.getDriver().getPageSource();
            driver.findElement(btnDelete).click();
            Thread.sleep(1000);
        }
    }

    public static void deleteMails(String userID) throws Throwable {

        setChromeDriver(userID);
        int noOfCheckboxes = driver.findElements(mailCheckboxes).size();
        if (noOfCheckboxes >= 5)
            noOfCheckboxes = 5;

        if (noOfCheckboxes >= 1) {
            for (int i = 1; i <= noOfCheckboxes; i++) {
                String checkbox =
                        "(.//*[@id='inboxpane']//div[@class = 'all_message-min-check-container'])" + '[' + i + ']';
                driver.findElement(By.xpath(checkbox)).click();
                Thread.sleep(1000);
            }
            DriverFactory.getDriver().getPageSource();
            driver.findElement(btnDelete).click();
            Thread.sleep(1000);
            driver.navigate().refresh();
            noOfCheckboxes = driver.findElements(mailCheckboxes).size();
            DriverFactory.getDriver().getPageSource();
        }
        driver.quit();
        Assert.assertEquals(noOfCheckboxes, 0, "Not able to clear the Inbox");
    }

}