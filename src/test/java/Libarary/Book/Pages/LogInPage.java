package Libarary.Book.Pages;

import Libarary.Book.Utilities.ConfigurationReader;
import Libarary.Book.Utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LogInPage {



    public LogInPage() {

        PageFactory.initElements(Driver.getDriver(), this);

    }

    @FindBy(xpath ="//input[@id=\"inputEmail\"]")
    public WebElement username;

    @FindBy(xpath ="//input[@id=\"inputPassword\"]")
    public WebElement password;

    @FindBy(xpath ="//button[@type=\"submit\"]")
    public WebElement submit;


    WebDriverWait wait = new WebDriverWait(Driver.getDriver(),20);
    WebDriver driver = Driver.getDriver();

    public void logIn() {

        username.sendKeys(ConfigurationReader.getProperty("username"));
        password.sendKeys(ConfigurationReader.getProperty("password"));
        submit.click();
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    public  void login(String UserName, String Password){
        wait.until(ExpectedConditions.visibilityOf(username));
        username.sendKeys(UserName);
        wait.until(ExpectedConditions.visibilityOf(password));
        password.sendKeys(Password);
        wait.until(ExpectedConditions.visibilityOf(submit));
        submit.click();

    }

    public void tileVerify(){

        String expectedTitle = "Library";
        wait.until(ExpectedConditions.titleIs("Library"));
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertEquals("Title verification Failed", expectedTitle, actualTitle);
    }
}
