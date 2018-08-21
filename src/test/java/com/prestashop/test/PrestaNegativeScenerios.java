package com.prestashop.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PrestaNegativeScenerios {

	WebDriver driver;

	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	@Test
	public void wrongCredentialsTest() {

		driver.get("http://automationpractice.com");
		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("email")).sendKeys("me@me.com");
		driver.findElement(By.id("passwd")).sendKeys("hello" + Keys.ENTER);
		String authentication = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]")).getText();
		Assert.assertTrue(authentication.contains("Authentication failed"));

	}
	
	@Test
	public void InvalidEMailTest() {
		driver.get("http://automationpractice.com");
		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("email")).sendKeys("meme.com");
		driver.findElement(By.id("passwd")).sendKeys("123.8" + Keys.ENTER);
		String authentication = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();
		Assert.assertTrue(authentication.contains("Invad email address."));
	}
	@Test
	public void BlankEMailTest() {
		driver.get("http://automationpractice.com");
		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("passwd")).sendKeys("123.8" + Keys.ENTER);
		String authentication = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();
		Assert.assertTrue(authentication.contains("An email address required."));
	}
	@Test
	public void BlankPasswordTest() {
		driver.get("http://automationpractice.com");
		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("email")).sendKeys("me@me.com");
		driver.findElement(By.id("passwd")).sendKeys("" + Keys.ENTER);
		String authentication = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();
		Assert.assertTrue(authentication.contains("Password is required."));
	}

}
