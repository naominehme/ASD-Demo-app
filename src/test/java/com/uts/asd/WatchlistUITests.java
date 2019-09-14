package com.uts.asd;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.util.concurrent.TimeUnit;

public class WatchlistUITests {

    // Use selenium webdriver
    WebDriver driver;

    // Declare run-time test variables
    ResultActions returnedPage;

    @Given("^I have opened the browser$")
    public void givenStatement() throws IllegalAccessException, InstantiationException {
        Class<?extends WebDriver> driverClass = ChromeDriver.class;
        WebDriverManager.getInstance(driverClass).setup();
        driver = driverClass.newInstance();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost/");
    }

    @When("^the client calls /watchlist$")
    public void the_client_calls_watchlist() throws Throwable {
        driver.get("http://localhost/watchlist");
    }

    @Then("^the title is Watchlist$")
    public void the_client_receives_status_code_of() throws Throwable {
        Assert.assertEquals("Watchlist", driver.getTitle());
    }
}
