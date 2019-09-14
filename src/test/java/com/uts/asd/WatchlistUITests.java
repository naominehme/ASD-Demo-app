package com.uts.asd;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = AsdApplication.class, loader = SpringBootContextLoader.class)
public class WatchlistUITests {

    // Use selenium webdriver
    WebDriver driver;

    @Value("${local.server.port}")
    int randomServerPort;

    @Given("^I have opened the browser$")
    public void givenStatement() throws IllegalAccessException, InstantiationException {
        Class<?extends WebDriver> driverClass = ChromeDriver.class;
        WebDriverManager.getInstance(driverClass).setup();
        driver = driverClass.newInstance();
        driver.get("http://localhost:" + randomServerPort);
    }

    @When("^the client calls /watchlist$")
    public void the_client_calls_watchlist() throws Throwable {
        driver.get("http://localhost:" + randomServerPort + "/watchlist");
    }

    @Then("^the title is Watchlist$")
    public void the_client_receives_status_code_of() throws Throwable {
        Assert.assertEquals("Watchlist", driver.getTitle());
    }
}
