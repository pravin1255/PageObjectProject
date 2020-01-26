package com.crm.qa.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;
//import com.qa.ExtentReportListener.ExtentManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop;
    public static EventFiringWebDriver e_driver;
    public static WebEventListener eventListener;
    public static ExtentTest logger;
    public static ExtentReports extent;
    public static ExtentHtmlReporter reporter;

    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "//src//main//" +
                    "java//com//crm//qa//config//config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialization(){
        String browserName=prop.getProperty("browser");
        if(browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//main//resources//chromedriver.exe");
            driver=new ChromeDriver();
        }
        else if(browserName.equals("FF")){
            System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//src//main//resources//geckodriver.exe");
            driver=new FirefoxDriver();
        }

        //Create object of EventFiringWebDriver class
        e_driver=new EventFiringWebDriver(driver);

        //Also create the object for WebEventListener class
        eventListener=new WebEventListener();

        //Now register the object of eventListener class with EventFiringWebDriver class
        e_driver.register(eventListener);

        //Now assign EventFiringWebDriver object to WebDriver reference variable
        driver=e_driver;
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_TIMEOUT,TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));
    }

    public void clickXpath (String xpathName){
        WebElement element = driver.findElement(By.xpath(xpathName));
        Actions action = new Actions(driver);
        action.moveToElement(element).click().perform();
    }

    public void clickXpath (WebElement element){
        WebElement ele = element;
        Actions action = new Actions(driver);
        action.moveToElement(ele).click().perform();
    }

    @BeforeSuite
    public void beforeSuite(){
        reporter=new ExtentHtmlReporter("./Reports1/learn_automation_"+getCurrentDateTime()+".html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if(result.getStatus()==ITestResult.FAILURE){
            logger.info("Reason for Test Failure");
            logger.error(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(driver)).build());
            logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(driver)).build());
        }
        else if(result.getStatus()==ITestResult.SUCCESS){
            logger.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(driver)).build());
        }
        else if(result.getStatus()==ITestResult.SKIP){
            logger.skip("Test Skipped", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(driver)).build());
        }
        driver.quit();
        extent.flush();
    }

    @AfterSuite
    public void aftersuite() {

        //ExtentManager.getReporter().flush();
    }

    public static String getCurrentDateTime(){
        DateFormat customFormat=new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        Date currentDate=new Date();
        return customFormat.format(currentDate);
    }

    public static String captureScreenshot(WebDriver driver){
        File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String screenShotPath=System.getProperty("user.dir")+"/screenshots/FreeCRM_"+getCurrentDateTime()+".jpg";
        try{
            FileHandler.copy(src,new File(screenShotPath));
        }
        catch(IOException e){
            System.out.println("Unable to capture screenshot "+e.getMessage());
        }
        return screenShotPath;
    }

}