package Libarary.Book.Pages;

import Libarary.Book.Utilities.ConfigurationReader;
import Libarary.Book.Utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    WebDriverWait wait = new WebDriverWait(Driver.getDriver(),10);
    WebDriver driver = Driver.getDriver();

    public void addBookInfo(){
        wait.until(ExpectedConditions.visibilityOf(bookName));
        bookName.sendKeys(ConfigurationReader.getProperty("bookname"));
        wait.until(ExpectedConditions.visibilityOf(ISBN));
        ISBN.sendKeys(ConfigurationReader.getProperty("isbn"));
        wait.until(ExpectedConditions.visibilityOf(Year));
        Year.sendKeys(ConfigurationReader.getProperty("year"));
        wait.until(ExpectedConditions.visibilityOf(Author));
        Author.sendKeys(ConfigurationReader.getProperty("author"));
        Select bookCategory = new Select(driver.findElement(By.xpath("//select[@id=\"book_group_id\"]")));
        bookCategory.selectByVisibleText(ConfigurationReader.getProperty("bookcategory"));
        wait.until(ExpectedConditions.visibilityOf(Description));
        Description.sendKeys(ConfigurationReader.getProperty("description"));
    }


}
