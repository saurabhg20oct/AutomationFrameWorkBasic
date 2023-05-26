package com.trifacta.Pages;

import java.io.IOException;
import java.sql.Time;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.trifacta.core.BasePage;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	private final By Email_box = By.xpath("//*[contains(@class,'sign-in-view ')]//div[@data-label='Email']/input");
	private final By Password_box = By.xpath("//*[contains(@class,'sign-in-view ')]//div[@data-label='Password']/input");
	private final By LoginButton = By.xpath("//*[contains(@class,'sign-in-view ')]//div[@class='m-button-primary']");
	private final By WelcomeMsg = By.xpath("//*[@class='m-text'][contains(text(),'Welcome back')]");
	
	
	
	public void openAndNavigateToPage(String url) throws IOException{
		
		driver.navigate().to(url);
		driver.manage().window().maximize();
		waitForLoad();
		//takeScreenShot(null, "LoginPage.png");
		System.out.println(driver.getTitle());
		assert driver.getTitle().contains("Trifacta Wrangler");
	}
	
	public void loginToTrifacta(String username,String Password) throws IOException {
		
		WebElement email_box = waitForElementToBeClickable(Email_box);
		email_box.sendKeys(username);
		takeScreenShot(email_box, "emailbox.jpg");	
		
		WebElement password_box = waitForElementToBeClickable(Password_box);
		password_box.click();
		password_box.sendKeys(Password);
		takeScreenShot( password_box, "Password_box.jpg");
		
		WebElement loginButton = waitForElementToBeClickable(LoginButton);
		loginButton.click();
		takeScreenShot( loginButton, "loginButton.jpg");
		waitForElementToDisappear(Email_box);
		waitForLoad();
		assert waitForElementToAppear(WelcomeMsg, "Saurabh Garg") == true;
		WebElement element =  waitForElementToBeClickable(WelcomeMsg);
		takeScreenShot( element , "HomePage.jpg");
	}
	
}
