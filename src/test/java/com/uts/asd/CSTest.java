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
public class CSTest{
	WebDriver webDriver;

	@Value("${local.server.port}")
	int randomServerPort;

	private String getURL() {
		return "http://localhost:" + randomServerPort;
	}
	
	@Given("^I have opened Google2$")
	public void gOOgleOpened() throws Throwable {
		Class<?extends WebDriver> driverClass = ChromeDriver.class;
        WebDriverManager.getInstance(driverClass).setup();
        webDriver = driverClass.newInstance();
	}

	@When("^the client calls csservice$")
	public void csservice() throws Throwable {
		webDriver.get(getURL() + "/replyProblem.html");
	}
	
	@When("^the client submit problem$")
	public void submitp() throws Throwable {
		//webDriver.findElement(By.name("submit")).click();
		System.out.println(webDriver.getTitle());
	}

	@Then("^the return message2$")
	public void page_display() throws Throwable {
		System.out.println(webDriver.getTitle());
	}

}
