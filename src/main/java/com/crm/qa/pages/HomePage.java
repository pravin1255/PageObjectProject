package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ReporterType;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class HomePage extends TestBase {

    @FindBy(xpath="//*[@class='user-display']")
    WebElement usernameLabel;

    @FindBy(xpath="//*[contains(text(),'Contacts')]")
    WebElement contactLink;

    @FindBy(xpath="//*[contains(text(),'Deals')]")
    WebElement dealsLink;

    @FindBy(xpath="//*[contains(text(),'Tasks')]")
    WebElement tasksLink;

    //Initializing the page objects
    HomePage(){
        PageFactory.initElements(driver,this);
    }

    public String verifyHomePageTitle(){
        return driver.getTitle();
    }

    public ContactsPage clickOnContactsLink(){
        try{
            Thread.sleep(5000);
        }catch(Exception e){e.printStackTrace();}
        contactLink.click();
        return new ContactsPage();
    }

    public DealsPage clickOnDealsLink(){
        dealsLink.click();
        return new DealsPage();
    }

    public TasksPage clickOnTasksLink(){
        tasksLink.click();
        return new TasksPage();
    }

    public String verifyCorrectUsername(){
        return usernameLabel.getText();
    }
}