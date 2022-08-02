package Libarary.Book.StepDefinations;

import Libarary.Book.Pages.BookPage;
import Libarary.Book.Pages.LogInPage;
import Libarary.Book.Utilities.ConfigurationReader;
import Libarary.Book.Utilities.DB_Util;
import Libarary.Book.Utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Addbook1 {

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
        loginPage.logIn();
        loginPage.tileVerify();
    }

    @When("Librarian clicks Books module")
    public void librarian_clicks_books_module() {
        //        w.until(ExpectedConditions.presenceOfElementLocated (By.cssSelector("h1")));
        wait.until(ExpectedConditions.visibilityOf(bookPage.book));
        bookPage.book.click();
    }

    @When("Librarian click “+ Add Book\" button")
    public void librarian_click_add_book_button() {
        wait.until(ExpectedConditions.visibilityOf(bookPage.Addbook));
        bookPage.Addbook.click();
    }

    @When("Librarian enter bookName , ISBN , Year, Author and Description")
    public void librarian_enter_book_name_isbn_year_author_and_description() {
        bookPage.addBookInfo();

    }

    @When("librarian click save changes")
    public void librarian_click_save_changes() {
        wait.until(ExpectedConditions.visibilityOf(bookPage.SaveChanges));
        bookPage.SaveChanges.click();

    }

    @Then("verify a new book is added")
    public void verify_a_new_book_is_added() throws SQLException, InterruptedException {

        String actualBookName = ConfigurationReader.getProperty("bookname");
        String actualAuthorName = ConfigurationReader.getProperty("author");
        String actualISBN = ConfigurationReader.getProperty("isbn");
        String actualYear = ConfigurationReader.getProperty("year");
        String actualDesc =ConfigurationReader.getProperty("description");



        String query = "select * from books where name ='java' order by id desc";

        DB_Util.runQuery(query);
        Map<String, String> rowMap = DB_Util.getRowMap(1);
        String expectedBookName = rowMap.get("name");
        String expectedAuthorName = rowMap.get("author");
        String expectedISBN = rowMap.get("isbn");
        String expectedDesc = rowMap.get("description");
        String expectedYear = rowMap.get("year");

        // assertions

        Assert.assertEquals(expectedBookName,actualBookName);
        Assert.assertEquals(expectedAuthorName,actualAuthorName);
        Assert.assertEquals(expectedISBN,actualISBN);
        Assert.assertEquals(expectedDesc,actualDesc);
        Assert.assertEquals(expectedYear,actualYear);

    }


    @When("Librarian enters valid {string} and {string} and click sign in")
    public void librarianEntersValidAndAndClickSignIn(String username, String password) {
        loginPage.login(username, password);
        loginPage.tileVerify();
    }
}
