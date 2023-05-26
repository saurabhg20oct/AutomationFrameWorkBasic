package com.trifacta.core;

import java.io.File;
import java.io.IOException;
import java.lang.module.FindException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;


public class BasePage {
    private static final int TIMEOUT = 10;
    private static final int POLLING = 1;
    protected WebDriver driver;
    private FluentWait<WebDriver>  wait;
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new FluentWait<WebDriver>(driver)
                .pollingEvery(Duration.ofSeconds(POLLING))
                .ignoring(NoSuchElementException.class)
                .withTimeout(Duration.ofSeconds(TIMEOUT));
    }

    protected boolean waitForElementToAppear(By locator,String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement ele = driver.findElement(locator);
        if(ele.getText().contains(text)) 
        	return true;
        else
        	return false;
        
    }
    protected void waitForElementToAppear(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        
    }
    protected WebElement waitForElementToBeClickable(By locator) {
      return  wait.until(ExpectedConditions.elementToBeClickable(locator));
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
    
    protected void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    
    protected void refreshPage() {
		driver.navigate().refresh();
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
		
	}
    
    protected void waitForTextToDisappear(By locator, String text) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, text)));
    }
    protected void waitForLoad() {
	  wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
	}
	
	
    
}
	
