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
public class PaymentTest{
	WebDriver webDriver;

	@Value("${local.server.port}")
	int randomServerPort;

	private String getBaseURL() {
		return "http://localhost:" + randomServerPort;
	}
	
	@Given("^I have opened Google1$")
	public void openChrome() throws Throwable {
		Class<?extends WebDriver> driverClass = ChromeDriver.class;
        WebDriverManager.getInstance(driverClass).setup();
        webDriver = driverClass.newInstance();
	}

	@When("^the client calls /home1$")
	public void callHome1() throws Throwable {
		webDriver.get(getBaseURL() + "/home");
	}
	
	@When("^the client calls /detail1$")
	public void callDetail1() throws Throwable {
		webDriver.findElement(By.className("property-image__img")).click();
	}
	
	@When("^the Users input number1$")
	public void inputNumber1() throws Throwable {
		webDriver.findElement(By.id("topup")).click();
		webDriver.findElement(By.id("amount")).sendKeys(Keys.BACK_SPACE,"50000");
		webDriver.findElement(By.className("layui-layer-btn0")).click();
	}
	
	@When("^the Users input letter1$")
	public void inputLetter1() throws Throwable {
		webDriver.findElement(By.id("topup")).click();
		webDriver.findElement(By.id("amount")).sendKeys(Keys.BACK_SPACE,"testnbgian");
		webDriver.findElement(By.className("layui-layer-btn0")).click();
	}

	@Then("^the title is index1$")
	public void getTitle() throws Throwable {
		System.out.println(webDriver.getTitle());
		Assert.assertEquals("Real Estate & Property for Sale in Lidcombe, NSW 2141 - realestate.com.au",webDriver.getTitle());
	}

	@Then("^the detail page display1$")
	public void getDetail() throws Throwable {
		System.out.println(webDriver.getTitle());
		Assert.assertEquals("802/21-23 James St Lidcombe, NSW 2141",webDriver.getTitle());
	}

	@Then("^return message1$")
	public void returnmsg() throws Throwable {
		System.out.println("Success");
	}
}
