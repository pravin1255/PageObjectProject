package com.crm.qa.testcases;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ContactsPageTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;
    ContactsPage contactsPage;
    TestUtil testUtil;
    String sheetName="contacts";

    public ContactsPageTest(){
        super();
    }

    @BeforeMethod
    public void setUp(){
        initialization();
        loginPage=new LoginPage();
        testUtil=new TestUtil();
        homePage=loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
        contactsPage=homePage.clickOnContactsLink();
    }

    @DataProvider
    public Object[][] getCRMTestData(){
        Object[][] data=TestUtil.getTestData(sheetName);
        return data;
    }

    @Test
    public void verifyContactsPageLabel(){
        Assert.assertTrue(contactsPage.verifyContactsLabel(),"Contact label is not present");
    }

    @Test
    public void selectContactsLabel(){
        testUtil.sleepMethod(5000);
        contactsPage.selectContactsByName("Pravin Shetty");
    }

//    @Test(dataProvider = "getCRMTestData")
//    public void createNewContactTest(String frstName, String lstName, String emaAdd, String strAdd){
//        //contactsPage.createNewContact("Vijay","Sharma","vijay@yopmail.com","Mumbai");
//        contactsPage.createNewContact(frstName,lstName,emaAdd,strAdd);
//    }

    @Test(dataProvider = "getCRMTestData")
    public void createNewContactTest(String... str){
        //contactsPage.createNewContact("Vijay","Sharma","vijay@yopmail.com","Mumbai");
        contactsPage.createNewContact(str[0],str[1],str[2],str[3]);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}