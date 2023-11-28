package com.myFacebookAccounts.pages;

import com.myFacebookAccounts.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage{

    @FindBy(id = "add_button")
    public WebElement addButton;

    @FindBy(name = "title")
    public WebElement title;

    @FindBy(name = "facebook_account")
    public WebElement facebookPageName;

    @FindBy(name = "phone")
    public WebElement phone;

    @FindBy(name = "email")
    public WebElement email;

    @FindBy(xpath = "//span[.='Save']")
    public WebElement saveButton;

    public void deleteTheLastAccount(){
        List<WebElement> deleteButtonsOfTheAccounts = Driver.get().findElements(By.xpath("//button[.='Delete']"));
        WebElement deleteButtonOfTheLastAccount = deleteButtonsOfTheAccounts.get(deleteButtonsOfTheAccounts.size()-1);
        deleteButtonOfTheLastAccount.click();
    }

}
