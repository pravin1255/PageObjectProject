package com.crm.qa.testcases;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class HomePageTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;

    public HomePageTest(){
        super();
    }

    //Test cases should be separated- independent with each other
    //Before each test case-launch the browser and login
    //@Test-execute the test case
    //after each test case-close the browser
    @BeforeMethod
    public void setUp(){
        initialization();
        loginPage=new LoginPage();
        homePage=loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
    }

    @Test(priority = 1)
    public void verifyHomeTitleTest(){
        String homepageTitle=homePage.verifyHomePageTitle();
        //The message will be printed only if test cases fail.
        Assert.assertEquals(homepageTitle,"Cogmento CRM","The title is not valid");
    }

    @Test(priority = 2)
    public void verifyUserNameTest(){
        String username=homePage.verifyCorrectUsername();
        Assert.assertEquals(username,prop.getProperty("usernameLabel"),"Proper username label is not displayed");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}