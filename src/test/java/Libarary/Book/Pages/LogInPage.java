package Libarary.Book.Pages;

import Libarary.Book.Utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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


}
