package com.trifacta.Tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.trifacta.Pages.HomePage;
import com.trifacta.Pages.LoginPage;
import com.trifacta.core.BaseTest;

public class LoginTest extends BaseTest{
	@Test
	public void LoginTest() {
		WebDriver driver = getDriver();
		try {
		LoginPage login = new LoginPage(driver);
		login.openAndNavigateToPage("https://cloud.trifacta.com/sign-in");
		login.loginToTrifacta("garg.saurabh8@gmail.com", "Test.12345");
		HomePage home = new HomePage(driver);
		home.clickonHelpIcon();
		String version = home.getTrifactaVersion();
		System.out.println(version);
		home.closeAboutTrifactaSection();	
		
		} catch(Exception e) {
			e.printStackTrace();
		}
			
	}

}
