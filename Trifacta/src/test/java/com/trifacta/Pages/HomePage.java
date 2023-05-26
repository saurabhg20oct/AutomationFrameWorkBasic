package com.trifacta.Pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.trifacta.core.BasePage;

public class HomePage extends BasePage {
	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	private final By helpIcon = By.xpath("//div[@aria-label='Help']");
	private final By aboutTrifacta = By.xpath("//div[text()='About Trifacta Wrangler']");
	private final By versionLabel = By.xpath("//*[contains(@class,'m-modal-body')]//h4");
	private final By versionPath = By.xpath("//*[contains(@class,'m-modal-body')]//h4/following-sibling::p");
	private final By closeModal = By.xpath("//a[@class='m-modal-close']");
	private final By profileIcon = By.xpath("//*[@class='m-disk']");
	private final By logoutBtn = By.xpath("//*[contains(@class,'menu_m-menu-item')][@data-id='sign-out']//div[@class='m-text']");
	
	public void clickonHelpIcon() throws IOException {
		WebElement element = waitForElementToBeClickable(helpIcon);
		element.click();
		takeScreenShot(element, "helpIcon.jpg");
		waitForElementToAppear(aboutTrifacta, "About Trifacta Wrangler");
		clickonAboutIcon();
	}
	public void clickonAboutIcon() throws IOException {
		WebElement element = waitForElementToBeClickable(aboutTrifacta);
		takeScreenShot(element, "aboutTrifacta.jpg");
		element.click();
		waitForElementToAppear(aboutTrifacta);
		waitForElementToAppear(versionLabel, "Version");
		getTrifactaVersion();
			
	}
	public String getTrifactaVersion() throws IOException {
		String version = "";
		WebElement element = waitForElementToBeClickable(versionPath);
		version = element.getText();
		takeScreenShot(element, version+".jpg");
		return version;
	}
	public void closeAboutTrifactaSection() throws IOException {
		WebElement element = waitForElementToBeClickable(closeModal);
		takeScreenShot(element, "closeModal.jpg");
		element.click();
		waitForElementToDisappear(versionPath);
		clickOnProfileSection();
	}
	public void clickOnProfileSection() throws IOException {
		WebElement element = waitForElementToBeClickable(profileIcon);
		element.click();
		takeScreenShot(element, "profileIcon.jpg");	
		waitForElementToAppear(logoutBtn, "Log out");
		clickOnLogoutBtn();
	}
	public void clickOnLogoutBtn() throws IOException {
		WebElement element = waitForElementToBeClickable(logoutBtn);
		element.click();
		waitForElementToDisappear(helpIcon);
			
	}
	
	
	
}
