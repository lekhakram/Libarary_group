package Libarary.Book.StepDefinations;

import Libarary.Book.Utilities.DB_Util;
import Libarary.Book.Utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class hooks {

    // @before is coming from cucumber and it is running before each scenarios.
    @Before
    public void setUpScenario(){
        System.out.println("-->  It is coming from @Before in Hooks");

    }
    // @After is coming from cucumber and it is running after each scenarios.
    @After
    public void tearDownScenario(Scenario scenario){

        System.out.println("-->  It is coming from @After in Hooks");


        if(scenario.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","screenshot");
        }

        Driver.closeDriver();
    }

    @Before("@db")
    public void setupDB(){

        DB_Util.createConnection();
        System.out.println("CONNECTION IS SUCCESSFUL");

    }

    @After("@db")
    public void destroyDB(){

        DB_Util.destroy();
        System.out.println("CONNECTION IS CLOSED");

    }
}
