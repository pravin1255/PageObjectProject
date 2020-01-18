package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage extends TestBase {

    @FindBy(xpath="//*[contains(text(),'Contacts')]")
    //CacheLookUp stores the user label name in memory or cache.
    // But if page gets refereshed than it will be reset to blank
    @CacheLookup
    WebElement contactsLabel;

    @FindBy(name="first_name")
    WebElement firstName;

    @FindBy(name="last_name")
    WebElement lastName;

    @FindBy(xpath="//*[@placeholder='Email address']")
    WebElement emailAdd;

    @FindBy(xpath="//*[@placeholder='Street Address']")
    WebElement streetAdd;

    @FindBy(xpath="//*[text()='Save']")
    WebElement saveBTN;

    @FindBy(xpath="//*[text()='New']")
    WebElement newBtn;

    public ContactsPage(){
        PageFactory.initElements(driver,this);
    }

    public boolean verifyContactsLabel(){
        return contactsLabel.isDisplayed();
    }

    public void selectContactsByName(String name){
        clickXpath(driver.findElement(By.xpath("//td[text()='"+name+"']//parent::tr//input[@type='checkbox']")));
    }

    public void createNewContact(String ftName, String ltName, String eAdd, String sAdd){
        newBtn.click();
        firstName.sendKeys(ftName);
        lastName.sendKeys(ltName);
        emailAdd.sendKeys(eAdd);
        streetAdd.sendKeys(sAdd);
        saveBTN.click();
        TestUtil.sleepMethod(5000);
    }
}