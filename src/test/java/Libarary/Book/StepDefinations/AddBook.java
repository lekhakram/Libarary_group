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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String actualTitle = Driver.getDriver().getTitle();
        String expectedTitle = "Library";
        wait.until(ExpectedConditions.titleIs("Library"));
        Assert.assertEquals("Title verification Failed", expectedTitle, actualTitle);
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
        wait.until(ExpectedConditions.visibilityOf(bookPage.bookName));
        bookPage.bookName.sendKeys(ConfigurationReader.getProperty("bookname"));
        wait.until(ExpectedConditions.visibilityOf(bookPage.ISBN));
        bookPage.ISBN.sendKeys(ConfigurationReader.getProperty("isbn"));
        wait.until(ExpectedConditions.visibilityOf(bookPage.Year));
        bookPage.Year.sendKeys(ConfigurationReader.getProperty("year"));
        wait.until(ExpectedConditions.visibilityOf(bookPage.Author));
        bookPage.Author.sendKeys(ConfigurationReader.getProperty("author"));
        Select bookCategory = new Select(driver.findElement(By.xpath("//select[@id=\"book_group_id\"]")));
        bookCategory.selectByVisibleText(ConfigurationReader.getProperty("bookcategory"));
        wait.until(ExpectedConditions.visibilityOf(bookPage.Description));
        bookPage.Description.sendKeys(ConfigurationReader.getProperty("description"));

    }

    @When("librarian click save changes")
    public void librarian_click_save_changes() {
        wait.until(ExpectedConditions.visibilityOf(bookPage.SaveChanges));
        bookPage.SaveChanges.click();

    }

    @Then("verify a new book is added")
    public void verify_a_new_book_is_added() throws SQLException, InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(bookPage.Dashboard));
        bookPage.Dashboard.click();
        wait.until(ExpectedConditions.visibilityOf(bookPage.Bookcount));
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

        resultSetDb= statementDb.executeQuery("select * from books where year=2019");
        resultSetDb.next();
        String expectedCount1 = resultSetDb.getString(1);
        System.out.println(expectedCount);
        ResultSetMetaData rsmd = resultSetDb.getMetaData();

        List<Map<String,Object>> dataList=new ArrayList<>();
        while(resultSetDb.next()){
            Map<String, Object> rowmap = new HashMap<>();
            for(int i = rsmd.getColumnCount(); i >1; i--){
                rowmap.put(rsmd.getColumnName(i), resultSetDb.getString(i));
            }
            dataList.add(rowmap);
        }


        System.out.println("===== PRINT MAPS FROM THE LIST =====");
        for (Map<String, Object> eachRowMap : dataList) {
            System.out.println(eachRowMap);
        }

        resultSetDb.close();
        statementDb.close();
        connectionDb.close();


        Driver.closeDriver();
        }


    @When("Librarian enters valid {string} and {string} and click sign in")
    public void librarianEntersValidAndAndClickSignIn(String username, String password) {
        wait.until(ExpectedConditions.visibilityOf(loginPage.username));
        loginPage.username.sendKeys(username);
        wait.until(ExpectedConditions.visibilityOf(loginPage.password));
        loginPage.password.sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(loginPage.submit));
        loginPage.submit.click();
        wait.until(ExpectedConditions.titleIs("Library"));
        String actualTitle = Driver.getDriver().getTitle();
        String expectedTitle = "Library";
        Assert.assertEquals("Title verification Failed", expectedTitle, actualTitle);



    }
}
