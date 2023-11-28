Net Affinity QA Task
---

---
The framework based on Page Object Model, Selenium, TestNG using Java. Supports many browsers.

This framework is based in **Page Object Model (POM).**

The framework uses:

1. Java
2. Maven
3. Selenium
4. TestNG
5. ExtentReport
6. Maven Surefire Plugin

Steps To Create Test Cases
----

1.Create a java class in **tests** package.  
A class typically should contain all the elements that are present on the page and corresponding action methods.

  ```
public class deleteAccount extends TestBase {

    HomePage homePage = new HomePage();

    @Test
    public void deleteAccount() {

        extentLogger= report.createTest("Delete Account Test");

        extentLogger.info("Navigate to URL");
        Driver.get().get(ConfigurationReader.get("url"));
        
        extentLogger.info("Click on add an account button");
        homePage.addButton.click();
```
2. Find required locations from browser and write them into the relevant POM class

```
public class HomePage extends BasePage{

    @FindBy(id = "add_button")
    public WebElement addButton;
```
3.Add the test class in testng_runner.xml file

```
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Net Affinity Automation Task" verbose="1">
    <test name="Delete Account Functionality">
        <classes>
            <class name="com.myFacebookAccounts.tests.deleteAccount">
```
---

Running Suite
---
1. Execute the test cases by maven command `mvn clean test`
---

Reporting
---
1. A html report - Which is generated using Extent Reports, under the folder `test-output`.

---

Key Points
---

1. `Driver`: The class is responsible for maintaining the same WebDriver instance throughout the test. So whenever you require a webdriver instance which has been using for current test (In current thread) always call `Driver.get()`. (Singleton)
2. `BrowserUtils`: All the necessary browser actions in this class.

---

Improvements & Suggestions
---

1. Parallel Run: As the number of test cases increases, it will take time to run tests in a single browser.
2. Runner.xml: As the number of test cases increases, suites like Regression, Smoke should be created.
3. Reporting: If the management does not like the reports generated, a different reporting tool can be tried.
4. Browser Actions: Methods such as hover over, click with js can be added.
5. Rerun: A file can be created to rerun failed test cases.
6. Branch Strategy: I did not pay attention to commit and branch issues because I was working alone on this project. Branch strategy would be better in terms of traceability.
---