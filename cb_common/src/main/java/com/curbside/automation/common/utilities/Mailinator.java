package com.curbside.automation.common.utilities;

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

    public static void setChromeDriver(String userID) throws InterruptedException {
        String baseURL = "https://www.mailinator.com/v2/inbox.jsp?zone=public&query=" + userID + "#/#inboxpane";
        System.setProperty("webdriver.chrome.driver", "../chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(baseURL);
        Thread.sleep(1000);
    }

    public static boolean isMailReceived(String userID, String subjectLine) throws InterruptedException {
        boolean isMailPresent = false;
        boolean inboxCleared ;
        setChromeDriver(userID);

        List<WebElement> allMailsSubject = driver.findElements(lblMailsSubject);
        for (WebElement mailSubject : allMailsSubject) {
            if (mailSubject.getText().equalsIgnoreCase(subjectLine))
                isMailPresent = true;
        }
        inboxCleared = deleteMails();
        driver.quit();
        Assert.assertTrue(inboxCleared, "Not able to clear the Inbox after email received");

        return isMailPresent;
    }

    public static boolean deleteMails() throws InterruptedException {

        boolean inboxCleared = false;
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
            driver.findElement(btnDelete).click();
            Thread.sleep(1000);
        }
        driver.navigate().refresh();
        noOfCheckboxes = driver.findElements(mailCheckboxes).size();

        if(noOfCheckboxes == 0)
            inboxCleared = true;

        return inboxCleared;
    }

    public static void deleteMails(String userID) throws InterruptedException {

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
            driver.findElement(btnDelete).click();
            Thread.sleep(1000);
        }
        driver.navigate().refresh();
        noOfCheckboxes = driver.findElements(mailCheckboxes).size();
        driver.quit();
        Assert.assertEquals(noOfCheckboxes, 0, "Not able to clear the Inbox");
    }

}