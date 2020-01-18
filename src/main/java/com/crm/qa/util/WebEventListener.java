package com.crm.qa.util;

import com.crm.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.io.IOException;

public class WebEventListener extends TestBase implements WebDriverEventListener {
    public void beforeAlertAccept(WebDriver webDriver) {

    }

    public void afterAlertAccept(WebDriver webDriver) {

    }

    public void afterAlertDismiss(WebDriver webDriver) {

    }

    public void beforeAlertDismiss(WebDriver webDriver) {

    }

    public void beforeNavigateTo(String url, WebDriver webDriver) {
        System.out.println("Before Navigating to: "+url+" ");
    }

    public void afterNavigateTo(String url, WebDriver webDriver) {
        System.out.println("Navigated to "+url);
    }

    public void beforeNavigateBack(WebDriver webDriver) {
        System.out.println("beforeNavigateBack");
    }

    public void afterNavigateBack(WebDriver webDriver) {
        System.out.println("afterNavigateBack");
    }

    public void beforeNavigateForward(WebDriver webDriver) {

    }

    public void afterNavigateForward(WebDriver webDriver) {

    }

    public void beforeNavigateRefresh(WebDriver webDriver) {

    }

    public void afterNavigateRefresh(WebDriver webDriver) {

    }

    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        System.out.println("Trying to find Element By: "+by.toString());
    }

    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        System.out.println("Found Element By: "+by.toString());
    }

    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        System.out.println("Trying to click on "+webElement.toString());
    }

    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        System.out.println("Clicked on "+webElement.toString());
    }

    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        System.out.println("Value of the "+webElement.toString()+" before any changes made");
    }

    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        System.out.println("Element value changed to "+webElement.toString());
    }

    public void beforeScript(String s, WebDriver webDriver) {

    }

    public void afterScript(String s, WebDriver webDriver) {

    }

    public void onException(Throwable throwable, WebDriver webDriver) {
        System.out.println("EXCEPTION OCCURED "+throwable.toString());
        try{
            TestUtil.takeScreenshotAtEndOfTest();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
