package com.prestashop.test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PrestaPositiveScenerios {
	Faker faker = new Faker();
	Random random = new Random();
	String firstName = faker.name().firstName();
	String addres = faker.address().streetName();
	String city = faker.address().cityName();
	String lastName = faker.name().lastName();
	String eMail = faker.internet().emailAddress();
	String password = faker.internet().password();
	Select Day;
	Select Month;
	Select Year;
	Select State;

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
	public void loginTest() {
		driver.get("http://automationpractice.com");
		driver.findElement(By.className("login")).click();

		driver.findElement(By.cssSelector("#email_create")).sendKeys(eMail);
		driver.findElement(By.cssSelector("#SubmitCreate > span")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#customer_firstname")).sendKeys(firstName);
		driver.findElement(By.cssSelector("#customer_lastname")).sendKeys(lastName);
		driver.findElement(By.cssSelector("#passwd")).sendKeys(password);

		Day = new Select(driver.findElement(By.cssSelector("#days")));
		Day.selectByIndex(random.nextInt(27) + 1);

		Month = new Select(driver.findElement(By.cssSelector("#months")));
		Month.selectByIndex(random.nextInt(12) + 1);

		Year = new Select(driver.findElement(By.cssSelector("#years")));
		Year.selectByIndex(random.nextInt(116) + 1);

		driver.findElement(By.cssSelector("#firstname")).sendKeys(firstName);
		driver.findElement(By.cssSelector("#lastname")).sendKeys(lastName);
		driver.findElement(By.cssSelector("#address1")).sendKeys(addres);
		driver.findElement(By.cssSelector("#city")).sendKeys(city);

		State = new Select(driver.findElement(By.cssSelector("#id_state")));
		State.selectByIndex(random.nextInt(50) + 1);

		driver.findElement(By.cssSelector("#address1")).sendKeys(addres);
		driver.findElement(By.cssSelector("#postcode")).sendKeys(faker.address().zipCode().substring(0, 5));
		driver.findElement(By.cssSelector("#other")).sendKeys(faker.chuckNorris().fact());
		driver.findElement(By.cssSelector("#phone_mobile")).sendKeys(faker.phoneNumber().cellPhone());
		driver.findElement(By.cssSelector("#alias")).clear();
		driver.findElement(By.cssSelector("#alias")).sendKeys(eMail);
		driver.findElement(By.cssSelector("#submitAccount > span")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		driver.findElement(By.cssSelector("#header > div.nav > div > div > nav > div:nth-child(2) > a")).click();
		
		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("email")).sendKeys(eMail);
		driver.findElement(By.id("passwd")).sendKeys(password + Keys.ENTER);
		
		
		Assert.assertTrue(driver.findElement(By.cssSelector("#my-account")).getText().contains(firstName+" "+lastName));
		

		
		
		

	}

}
