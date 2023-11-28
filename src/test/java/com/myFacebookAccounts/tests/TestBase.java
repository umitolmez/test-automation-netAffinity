package com.myFacebookAccounts.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.myFacebookAccounts.utilities.BrowserUtils;
import com.myFacebookAccounts.utilities.ConfigurationReader;
import com.myFacebookAccounts.utilities.Driver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.IOException;
import java.time.Duration;

/**
 * Every test class should extend this class.
 *
 */
public class TestBase {

    protected static ExtentReports report;
    //this class is used to create HTML report file
    protected static ExtentHtmlReporter htmlReporter;
    //this will  define a test, enables adding logs, authors, test steps
    protected static ExtentTest extentLogger;

    //env set up
    protected  String url;

    @BeforeTest
    public void setUpTest(){
        //initialize the class
        report = new ExtentReports();

        //create a report path
        String projectPath = System.getProperty("user.dir");
        String path = projectPath + "/test-output/report.html";

        //initialize the html reporter with the report path
        htmlReporter = new ExtentHtmlReporter(path);

        //attach the html report to report object
        report.attachReporter(htmlReporter);

        //title in report
        htmlReporter.config().setReportName("Report Name");

        //set environment information
        report.setSystemInfo("Environment","test01");
        report.setSystemInfo("Browser", ConfigurationReader.get("browser"));
        report.setSystemInfo("OS",System.getProperty("os.name"));
    }

    @BeforeMethod
    public void setUp(){

        Driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Driver.get().manage().window().maximize();
    }

    //ITestResult class describes the result of a test in TestNG
    @AfterMethod
    public void tearDown(ITestResult result) throws InterruptedException, IOException {
        //if test fails
        if(result.getStatus()==ITestResult.FAILURE){
            //record the name of failed test case
            extentLogger.fail(result.getName());

            //take the screenshot and return location of screenshot
            String screenShotPath = BrowserUtils.getScreenshot(result.getName());

            //add your screenshot to your report
            extentLogger.addScreenCaptureFromPath(screenShotPath);

            //capture the exception and put inside the report
            extentLogger.fail(result.getThrowable());

        }

        Driver.closeDriver();
    }
    @AfterTest
    public void tearDownTest(){
        //this is when the report is actually created
        report.flush();

    }
}
