package com.uts.asd;

import cucumber.api.java.en.Given;

import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import cucumber.api.java.en.Then;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = AsdApplication.class, loader = SpringBootContextLoader.class)
public class BidTest{
	WebDriver webDriver;

	@Value("${local.server.port}")
	int randomServerPort;

	private String getBaseURL() {
		return "http://localhost:" + randomServerPort;
	}
	
	@Given("^I have opened Google$")
	public void today_is_Sunday() throws Throwable {
		Class<?extends WebDriver> driverClass = ChromeDriver.class;
        WebDriverManager.getInstance(driverClass).setup();
        webDriver = driverClass.newInstance();
	}

	@When("^the client calls /home$")
	public void callHome() throws Throwable {
		webDriver.get(getBaseURL() + "/home");
	}
	
	@When("^the client calls /detail$")
	public void callDetail() throws Throwable {
		webDriver.findElement(By.className("property-image__img")).click();
	}
	
	@When("^the Users input number$")
	public void inputNumber() throws Throwable {
		webDriver.findElement(By.id("money")).sendKeys(Keys.BACK_SPACE,"4050188");;
		webDriver.findElement(By.id("post")).click();;
	}
	
	@When("^the Users input letter$")
	public void inputLetter() throws Throwable {
		webDriver.findElement(By.id("money")).sendKeys(Keys.BACK_SPACE,"abcc");;
		webDriver.findElement(By.id("post")).click();;
	}

	@Then("^the title is index$")
	public void i_should_be_told() throws Throwable {
		System.out.println(webDriver.getTitle());
		Assert.assertEquals("Real Estate & Property for Sale in Lidcombe, NSW 2141 - realestate.com.au",webDriver.getTitle());
	}

	@Then("^the detail page display$")
	public void the_detail_page_display() throws Throwable {
		System.out.println(webDriver.getTitle());
		Assert.assertEquals("802/21-23 James St Lidcombe, NSW 2141",webDriver.getTitle());
	}

	@Then("^return message$")
	public void return_message() throws Throwable {
		System.out.println("Success");
	}
}
