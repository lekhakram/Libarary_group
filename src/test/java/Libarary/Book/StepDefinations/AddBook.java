package Libarary.Book.StepDefinations;

import Libarary.Book.Pages.BookPage;
import Libarary.Book.Pages.LogInPage;
import Libarary.Book.Utilities.ConfigurationReader;
import Libarary.Book.Utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.joda.time.Seconds;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddBook {
    WebDriver driver = Driver.getDriver();
    BookPage bookPage = new BookPage();
    LogInPage loginPage = new LogInPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(),10);

    @Given("Librarian is on the loginPage")
    public void librarian_is_on_the_login_page() {
        driver.get(ConfigurationReader.getProperty("url"));
    }

    @Given("Librarian enters valid email and password and click sign in.")
    public void librarian_enters_valid_email_and_password_and_click_sign_in() {
        loginPage.username.sendKeys(ConfigurationReader.getProperty("username"));
        loginPage.password.sendKeys(ConfigurationReader.getProperty("password"));
        loginPage.submit.click();
    }

    @When("Librarian clicks Books module")
    public void librarian_clicks_books_module() {
        bookPage.book.click();
    }

    @When("Librarian click “+ Add Book\" button")
    public void librarian_click_add_book_button() {
        bookPage.Addbook.click();
    }

    @When("Librarian enter bookName , ISBN , Year, Author and Description")
    public void librarian_enter_book_name_isbn_year_author_and_description() {
        bookPage.bookName.sendKeys(ConfigurationReader.getProperty("bookname"));
        bookPage.ISBN.sendKeys(ConfigurationReader.getProperty("isbn"));
        bookPage.Year.sendKeys(ConfigurationReader.getProperty("year"));
        bookPage.Author.sendKeys(ConfigurationReader.getProperty("author"));
        Select bookCategory = new Select(driver.findElement(By.xpath("//select[@id=\"book_group_id\"]")));
        bookCategory.selectByVisibleText(ConfigurationReader.getProperty("bookcategory"));
        bookPage.Description.sendKeys(ConfigurationReader.getProperty("description"));

    }

    @When("librarian click save changes")
    public void librarian_click_save_changes() {

        bookPage.SaveChanges.click();
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Then("verify a new book is added")
    public void verify_a_new_book_is_added() throws SQLException {
        bookPage.Dashboard.click();
       Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String actualCount= (bookPage.Bookcount).getText();
        System.out.println(actualCount);


        String dbUrl = "jdbc:mysql://34.230.35.214:3306/library2";
        String dbUserName =  "library2_client";
        String dbPassword =  "6s2LQQTjBcGFfDhY";

        Connection connectionDb = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

        // Statement helps us to execute query

        Statement statementDb = connectionDb.createStatement();

        // resultset stores data that we get after query executions

        ResultSet resultSetDb = statementDb.executeQuery("select count(*) FROM books");

        resultSetDb.next();
        String expectedCount = resultSetDb.getString(1);
        System.out.println(expectedCount);

        Assert.assertEquals(expectedCount,actualCount);

        resultSetDb.close();
        statementDb.close();
        connectionDb.close();

        }


        }
