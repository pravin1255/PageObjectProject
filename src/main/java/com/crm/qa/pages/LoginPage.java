package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {

    //Page Factory or Object Repository
    @FindBy(name="email")
    WebElement username;

    @FindBy(name="password")
    WebElement password;

    @FindBy(xpath="//*[text()='Login']")
    WebElement loginBTN;

    @FindBy(xpath="//*[contains(@src,'logo.png')]")
    WebElement crmLogo;

    //Initializing the page objects
    public LoginPage(){
        //PageFactory.initElements(driver,LoginPage.class);
        PageFactory.initElements(driver,this);//This can be used instead of above line
    }

    //Actions performed in login page
    public String validateLoginPageTitle(){
        return driver.getTitle();
    }

    public boolean validateCrmImage(){
        return crmLogo.isDisplayed();
    }

    public HomePage login(String un, String pwd){
        username.sendKeys(un);
        password.sendKeys(pwd);
        loginBTN.click();
        return new HomePage();
    }

}
