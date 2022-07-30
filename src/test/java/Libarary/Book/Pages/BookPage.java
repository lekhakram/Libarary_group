package Libarary.Book.Pages;

import Libarary.Book.Utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookPage {
    public BookPage() {

        PageFactory.initElements(Driver.getDriver(), this);

    }


    @FindBy (xpath ="//i[@class='fa fa-book']")
    public WebElement book;

    @FindBy(xpath ="//a[@class='btn btn-lg btn-outline btn-primary btn-sm add_book_btn']")
    public WebElement Addbook;


    @FindBy (xpath ="//input[@placeholder=\"Book Name\"]\n")
    public WebElement bookName;

    @FindBy (xpath ="//input[@placeholder=\"ISBN\"]")
    public WebElement ISBN;

    @FindBy (xpath ="//input[@placeholder=\"Year\"]")
    public WebElement Year;

    @FindBy (xpath ="//input[@placeholder=\"Author\"]")
    public WebElement Author;

    @FindBy (xpath ="//select [@id=\"book_group_id\"]")
    public WebElement BookCategory;

    @FindBy (xpath ="//textarea [@id=\"description\"]")
    public WebElement Description;

    @FindBy (xpath ="//button [@type=\"submit\"]")
    public WebElement SaveChanges;

    @FindBy (xpath ="//input [@type=\"search\"]")
    public WebElement Search;

    @FindBy (xpath ="//table//tr//td[3]")
    public WebElement BookTitle;

    @FindBy (xpath ="//h2[@id=\"book_count\"]")
    public WebElement Bookcount;
    @FindBy (xpath ="(//span[@class=\"title\"])[1]")
    public WebElement Dashboard;

    @FindBy (xpath ="//a[@id=\"navbarDropdown\"]")
    public WebElement logout1;

    @FindBy (xpath ="//div[@class=\"dropdown-divider\"]")
    public WebElement logout2;



}
