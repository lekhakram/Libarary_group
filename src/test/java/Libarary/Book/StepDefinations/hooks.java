package Libarary.Book.StepDefinations;

import Libarary.Book.Utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class hooks {

    // @before is coming from cucumber and it is running before each scenarios.
    @Before
    public void setUpScenario(){
        System.out.println("-->  It is coming from @Before in Hooks");

    }
    // @After is coming from cucumber and it is running after each scenarios.
    @After
    public void tearDownScenario(){

        System.out.println("-->  It is coming from @After in Hooks");

//    if(scenario.isFailed()){
//        byte[] screenshot=((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
//        scenario.embed(screenshot, , scenario.getName());
//    }
        Driver.closeDriver();
    }

}
