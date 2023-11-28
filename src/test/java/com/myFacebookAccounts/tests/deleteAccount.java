package com.myFacebookAccounts.tests;

import com.myFacebookAccounts.pages.HomePage;
import com.myFacebookAccounts.utilities.BrowserUtils;
import com.myFacebookAccounts.utilities.ConfigurationReader;
import com.myFacebookAccounts.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class deleteAccount extends TestBase {

    HomePage homePage = new HomePage();

    @Test
    public void deleteAccount() {

        extentLogger= report.createTest("Delete Account Test");

        extentLogger.info("Navigate to URL");
        Driver.get().get(ConfigurationReader.get("url"));

        extentLogger.info("Click on add an account button");
        homePage.addButton.click();

        extentLogger.info("Fill the fields");
        homePage.title.sendKeys("Test Title - Umit");
        homePage.facebookPageName.sendKeys("Test Name");
        homePage.phone.sendKeys("+905389123452");
        homePage.email.sendKeys("umitolmez455@gmail.com");

        extentLogger.info("Save the form");
        homePage.saveButton.click();

        extentLogger.info("Verify that toast message is displayed");
        BrowserUtils.waitForVisibility(homePage.toastMessage, 5);
        Assert.assertTrue(homePage.toastMessage.isDisplayed());

        extentLogger.info("Verify that toast message is Account inserted");
        Assert.assertEquals(homePage.toastMessage.getText(), "Account inserted");

        BrowserUtils.waitFor(3);

        extentLogger.info("Delete the last account that created");
        homePage.deleteTheLastAccount();

        extentLogger.info("Verify that toast message is displayed");
        BrowserUtils.waitForVisibility(homePage.toastMessage, 5);
        Assert.assertTrue(homePage.toastMessage.isDisplayed());

        extentLogger.info("Verify that toast message is Account deleted");
        Assert.assertEquals(homePage.toastMessage.getText(), "Account deleted");

        extentLogger.pass("PASSED");
    }
}
