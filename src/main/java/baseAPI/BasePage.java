package baseAPI;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.*;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Optional;
import org.testng.asserts.SoftAssert;
import reporting.ExtentManager;
import reporting.ExtentTestManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

public class BasePage {

    public static WebDriver driver;
    public static WebDriverWait webDriverWait;
    //public static ExtentReports extent;
    public static DataReader dataReader;
    public static String API_URL;
    public SQLConnector database;
    public RestAssuredClient restAssured;

    private Properties properties;
    public final String ABSOLUTE_PATH = System.getProperty("user.dir");
    private final String PROPERTIES_RELATIVE_PATH = "/src/main/resources/secret.properties";
    private final String PROP_FILE_PATH = ABSOLUTE_PATH + PROPERTIES_RELATIVE_PATH;

 /*   @BeforeSuite(alwaysRun = true)
    public void beforeSuiteExtentSetup(ITestContext context) {
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance();
    }*/

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream(PROP_FILE_PATH);
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            dataReader = new DataReader();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            database = new SQLConnector();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            restAssured = new RestAssuredClient();
            API_URL = properties.getProperty("METAPIWebsite");

        }catch(Exception e){
            e.printStackTrace();
        }
    }


  /*  @BeforeMethod(alwaysRun = true)
    public static void beforeEachMethodExtentInit(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName();

        ExtentTestManager.startTest(methodName);
        ExtentTestManager.getTest().assignCategory(className);

        System.out.println("\n\t***" + methodName + "***\n");
    }*/

    //for testNG things
    //@Parameters({"browser", "url"})
    @BeforeMethod(alwaysRun = true)
    public void driverSetup(@Optional("chrome") String browser, String url) {
        driver = initDriver(browser);
        webDriverWait = new WebDriverWait(driver, 20);
        url = properties.getProperty("METwebsiteURL");
        driver.get(url);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

/*
    @AfterMethod
    public void extentFlush(ITestResult result) {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));

        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }

        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, "TEST CASE FAILED: " + result.getName());
            ExtentTestManager.getTest().log(LogStatus.FAIL, result.getThrowable());
            captureScreenshot(driver, result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "TEST CASE SKIPPED: " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "TEST CASE PASSED: " + result.getName());
        }

        ExtentTestManager.endTest();
        extent.flush();
    }
*/

    @AfterMethod
    public void driverClose() {
        driver.close();
        driver.quit();

    }

    @AfterSuite(alwaysRun = true)
    private void afterSuiteTearDown() {
       // extent.close();
    }

    public WebDriver initDriver(String browser) {
        switch (browser.toLowerCase().trim()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
        }
        return driver;
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    private static void captureScreenshot(WebDriver driver, String testName) {
        String fileName = testName + ".png";
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File newScreenshotFile = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator +
                "main" + File.separator + "java" + File.separator + "reporting" + File.separator + fileName);

        try {
            FileHandler.copy(screenshot, newScreenshotFile);
            System.out.println("SCREENSHOT TAKEN");
        } catch (Exception e) {
            System.out.println("ERROR TAKING SCREENSHOT: " + e.getMessage());
        }
    }

    /*
    Selenium Helper Methods
     */

    public WebElement safeFindElement(By by) {
        WebElement element = driver.findElement(by);

        return element;
    }

    public void sendKeysToInput(WebElement element, String keys) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));

        element.sendKeys(keys);
    }


    public void clearInputText(WebElement element) {
        element.sendKeys(Keys.COMMAND + "a");
        element.sendKeys(Keys.DELETE);
    }

    public void usingReturnButtonForSearch(WebElement element,String value){
        addingKeyboardInput(element,value);
        element.sendKeys(Keys.RETURN);
    }

    public void dropdownSelectByVisibleText(WebElement element, String visibleText) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));

        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }

    public void dropdownSelectByIndex(WebElement element, int number) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));

        Select select = new Select(element);
        select.selectByIndex(number);
    }

    public void dropdownSelectByValue(WebElement element, String value) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));

        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void buttonSelect(WebElement element, WebElement selected) {
        clickOnElement(element);
        clickOnElement(selected);
    }

    public void addingKeyboardInput(WebElement element, String value) {
        clickOnElement(element);
        clearInputText(element);
        sendKeysToInput(element, value);
    }

    public void clickOnElement(WebElement element) {
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (StaleElementReferenceException e1) {
            clickJScript(element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void foundIframe(WebElement element) {
        try {
            driver.switchTo().frame(element);
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
    }

    public void clickJScript(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }


    public void createJSAlert(String alertText) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("alert('" + alertText + "');");
    }

    public void scrollJS(int numOfPixelsToScroll) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + numOfPixelsToScroll + ")");
    }

    public void changeHiddenJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String s = element.getAttribute("aria-hidden");
        String f = "false";
        js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2]);", element, s, f);
    }

    public static WebElement getShadowRoot(WebElement shadowHost) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (WebElement) js.executeScript("return arguments[0].shadowRoot", shadowHost);
    }

    public static WebElement getShadowElement(WebElement shadowHost, String cssOfShadowElement) {
        WebElement shardowRoot = getShadowRoot(shadowHost);
        return shardowRoot.findElement(By.cssSelector(cssOfShadowElement));
    }


    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public void confirmAlert() {
        driver.switchTo().alert().accept();
    }

    public String getTextFromAlert() {
        return driver.switchTo().alert().getText();
    }

    public void getListOfElements(List<WebElement> elements, List<String> elementCopied) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            for (WebElement element : elements) {
                elementCopied.add(element.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<String> oneDList(List<WebElement> elements1) {
        List<String> elementCopied1 = new ArrayList<>();
        getListOfElements(elements1, elementCopied1);
        return elementCopied1;
    }

    public List<String> printOutListOfElements(List<WebElement> elementsCopied1) {
        List<String> printOut = oneDList(elementsCopied1);
        for (String s : printOut) {
            System.out.println(s);
        }
        return printOut;
    }

    public List<String> convertToString(List<WebElement> elementsCopied1) {
        List<String> printOut = oneDList(elementsCopied1);
        return printOut;
    }

    public void getListOfhref(List<WebElement> elements, List<String> elementCopied) {
        try {
            waitForElementsToBeVisible(elements);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            for (WebElement element : elements) {
                elementCopied.add(element.getAttribute("href"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> oneDhref(List<WebElement> elements1) {
        List<String> elementCopied1 = new ArrayList<>();
        getListOfhref(elements1, elementCopied1);
        return elementCopied1;
    }

    public List<String> printOutHrefListOfElements(List<WebElement> elementsCopied1) {
        List<String> printOut = oneDhref(elementsCopied1);
        for (String s : printOut) {
            System.out.println(s);
        }
        return printOut;
    }

    public List<String> getRangeFromList(List<WebElement> elements1, int start, int end) {
        List<String> elementCopied1 = new ArrayList<>();
        List<String> elementCopied2 = new ArrayList<>();
        getListOfElements(elements1, elementCopied1);
        if (end < elementCopied1.size()) {
            for (int i = start; i <= end; i++) {
                elementCopied2.add(elementCopied1.get(i));
            }
        }
        return elementCopied2;
    }

    public void getListOfArributes(List<WebElement> elements, List<String> elementCopied) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            for (WebElement element : elements) {
                elementCopied.add(element.getAttribute("aria-label"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> oneDAttributes(List<WebElement> elements1) {
        List<String> elementCopied1 = new ArrayList<>();
        getListOfArributes(elements1, elementCopied1);
        return elementCopied1;
    }

    public List<String> printOutArributesListOfElements(List<WebElement> elementsCopied1) {
        List<String> printOut = oneDAttributes(elementsCopied1);
        for (String s : printOut) {
            System.out.println(s);
        }
        return printOut;
    }

    public List<WebElement> getListOfUnhiddenArributes(List<WebElement> elements) {
        List<WebElement> elementVisible = new ArrayList<WebElement>();
        /*
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (Exception e) {
            e.printStackTrace();
        }
         */
        try {
            for (WebElement element : elements) {
                String s = element.getAttribute("aria-hidden");
                if (s.equals("false")) {
                    elementVisible.add(element);
                }
                /*
                else{
                    changeHiddenJS(element);
                    elementVisible.add(element);
                }
                 */
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementVisible;
    }


    public void clearElement(WebElement element) {
        element.clear();
    }

    public void hoverAction(WebElement element) {
        Actions a = new Actions(driver);
        try {
            waitForElementToBeVisible(element);
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
        a.moveToElement(element).build().perform();
    }

    public void slideAction(WebElement element, int x, int y) {
        Actions action = new Actions(driver);
        try {
            waitForElementToBeVisible(element);
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
        action.dragAndDropBy(element, x, y).build().perform();
    }



    /*
    SYNC Methods
     */
    public void waitForElementToBeVisible(WebElement element) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
    }

    public void waitForElementsToBeVisible(List<WebElement> element) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfAllElements(element));
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
    }


    public void waitForElementToContainText(WebElement element, String text) {
        try {
            webDriverWait.until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void canClick(WebElement element){
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fluentWaitMethod(WebElement element) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(1)).ignoring(StaleElementReferenceException.class);
        // Wait<WebDriver> fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(1)).ignoring(StaleElementReferenceException.class);
        fluentWait.until(ExpectedConditions.visibilityOf(element));
    }

}