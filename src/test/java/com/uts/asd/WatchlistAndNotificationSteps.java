package com.uts.asd;

import com.uts.asd.controller.WatchlistController;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
public class WatchlistAndNotificationSteps {

    // Use selenium webdriver
    WebDriver driver;

    @Value("${local.server.port}")
    int randomServerPort;

    @Autowired
    WatchlistController watchlistController;

    private String getBaseURL() {
        return "http://localhost:" + randomServerPort;
    }

    @Given("^I have opened Google Chrome")
    public void openedGoogleChrome() throws IllegalAccessException, InstantiationException {
        Class<?extends WebDriver> driverClass = ChromeDriver.class;
        WebDriverManager.getInstance(driverClass).setup();
        driver = driverClass.newInstance();
    }

    @Given("^I am using the Test User")
    public void useTestUser() {
        watchlistController.setDefaultCustomerID("-2");
    }

    @Given("^I am using the Empty Test User")
    public void useEmptyTestUser() {
        watchlistController.setDefaultCustomerID("-999");
    }

    @When("^the client calls /watchlist$")
    public void watchlistPageIsCalled() {
        driver.get(getBaseURL() + "/watchlist");
    }

    @When("^the client calls /notifications")
    public void notificationsPageIsCalled() {
        driver.get(getBaseURL() + "/notifications");
    }

    @When("^the add property form is submitted with valid values$")
    public void theAddPropertyFormIsSubmittedWithValidValues() {
        driver.findElement(By.id("propertyID")).click();
        driver.findElement(By.id("propertyID")).sendKeys(Keys.BACK_SPACE, "0");
        driver.findElement(By.id("addPropertySubmit")).click();
    }

    @When("^the add property form is submitted with invalid values$")
    public void theAddPropertyFormIsSubmittedWithInvalidValues() {
        driver.findElement(By.id("propertyID")).click();
        driver.findElement(By.id("propertyID")).sendKeys(Keys.BACK_SPACE, "-", "1");
        driver.findElement(By.id("addPropertySubmit")).click();
    }

    @When("^the add preference form is submitted with invalid values$")
    public void theAddPreferenceFormIsSubmittedWithInvalidValues() {
        driver.findElement(By.id("addPreferenceSubmit")).click();
    }

    @Then("^there should be errors$")
    public void thereShouldBeErrors() {
        int elementSize = driver.findElements(By.className("error")).size();
        Assert.assertTrue(elementSize > 0);
    }

    @Then("^there should be no errors$")
    public void thereShouldBeNoErrors() {
        int elementSize = driver.findElements(By.className("error")).size();
        Assert.assertTrue(elementSize == 0);
    }

    @Then("^the title is Watchlist$")
    public void confirmTitleIsWatchlist() {
        Assert.assertEquals("Watchlist", driver.getTitle());
    }

    @Then("^the title is Notifications")
    public void confirmTitleIsNotifications() {
        Assert.assertEquals("Notifications", driver.getTitle());
    }

    @Then("^the watchlist properties are populated$")
    public void confirmPropertiesAreRetrieved() {
        int elementSize = driver.findElements(By.className("content-watchlist-item")).size();
        Assert.assertTrue(elementSize > 0);
    }

    @Then("^the watchlist preferences are populated$")
    public void confirmPreferencesAreRetrieved() {
        int elementSize = driver.findElements(By.className("content-watchlist-preference")).size();
        Assert.assertTrue(elementSize > 0);
    }

    @Then("^the watchlist properties are not populated$")
    public void confirmPropertiesAreNotRetrieved() {
        int elementSize = driver.findElements(By.className("content-watchlist-item")).size();
        Assert.assertTrue(elementSize == 0);
    }

    @Then("^the watchlist preferences are not populated$")
    public void confirmPreferencesAreNotRetrieved() {
        int elementSize = driver.findElements(By.className("content-watchlist-preference")).size();
        Assert.assertTrue(elementSize == 0);
    }

    @When("^the client enables Notifications Enabled$")
    public void enableNotificationsEnabled() {
        if (!driver.findElement(By.id("notificationsEnabled")).isSelected()) {
            driver.findElement(By.id("notificationsEnabled")).click();
        }
    }

    @When("^the client disables Notifications Enabled$")
    public void disableNotificationsEnabled() {
        if (driver.findElement(By.id("notificationsEnabled")).isSelected()) {
            driver.findElement(By.id("notificationsEnabled")).click();
        }
    }

    @When("^the preferences are submitted$")
    public void submitNotificationPreferences() {
        driver.findElement(By.id("submitNotifications")).click();
    }

    @Then("^then Notifications Enabled should be checked$")
    public void confirmNotificationsEnabledChecked() {
        int elementSize = driver.findElements(By.className("content-watchlist-preference")).size();
        Assert.assertTrue(driver.findElement(By.id("notificationsEnabled")).isSelected());
    }

    @Then("^then Notifications Enabled should be unchecked")
    public void confirmNotificationsEnabledUnchecked() {
        int elementSize = driver.findElements(By.className("content-watchlist-preference")).size();
        Assert.assertTrue(!driver.findElement(By.id("notificationsEnabled")).isSelected());
    }

}
