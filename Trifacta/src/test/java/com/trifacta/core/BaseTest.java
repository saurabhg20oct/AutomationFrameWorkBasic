package com.trifacta.core;


import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
	private WebDriver driver;
	@BeforeSuite
	public void beforeSuite() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}


	@AfterSuite
	public void afterSuite() {
		if(null != driver) {
			driver.close();
			driver.quit();
		}
	}

	protected void takeScreenShot(WebElement element, String fileName) throws IOException {
		JavascriptExecutor jse = null;
		if(element!=null) {
			jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].style.border='2px solid red'", element);
		}
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + fileName;
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File(screenshotPath);
		Files.copy(SrcFile.toPath(), DestFile.toPath(),StandardCopyOption.REPLACE_EXISTING);
		jse.executeScript("arguments[0].style.border=''", element);
		
	}

	public WebDriver getDriver() {
		return driver;
	}
}


