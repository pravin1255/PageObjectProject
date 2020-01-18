package com.crm.qa.base;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop;
    public static EventFiringWebDriver e_driver;
    public static WebEventListener eventListener;

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
}