package naukri;
import naukri.NaukriConstants;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.time.Duration;

import java.util.HashMap;


public class NaukriProfileUpdater implements NaukriConstants {
    public static void main(String[] args) {
        String secretUsername = System.getenv("NAUKRI_USERNAME");
        String secretPassword = System.getenv("NAUKRI_PASSWORD");
        int myRandInt  = getRandomNumber01(0,99,5);
        String resumeHeadlineSelected = resumeHeadline.get(myRandInt);
        myRandInt  = getRandomNumber01(199,9999,2);
        String resumePath = resume.get(myRandInt);
        myRandInt  = getRandomNumber01(199,9999,5);
        String profileSummarySelected = resumeHeadline.get(myRandInt);
        while(profileSummarySelected == resumeHeadlineSelected){
            myRandInt  = getRandomNumber01(199,9999,4);
            profileSummarySelected = resumeHeadline.get(myRandInt);
        }

        System.out.println("Resume Headline Selected: " + resumeHeadlineSelected);
        System.out.println("Resume Path: " + resumePath);
        System.out.println("Profile Summary Selected: "  + profileSummarySelected);

        WebDriver driver = new ChromeDriver(disableImages());
        driver.manage().window().maximize();
        driver.get("https://www.naukri.com");
        waitForPageToLoad(driver,60,login);
        takeScreenshot(driver, "screenshots", "JobseekerLogin");
        
        driver.findElement(login).click();
        waitForPageToLoad(driver,60, usernameInput);
        driver.findElement(usernameInput)
                .sendKeys(secretUsername);
        takeScreenshot(driver, "screenshots", "PreLoginUsernameEntered");
        driver.findElement(passwordInput)
                .sendKeys(secretPassword);
        takeScreenshot(driver, "screenshots", "PreLoginPwdEntered");
        driver.findElement(loginBtn).click();
        takeScreenshot(driver, "screenshots", "PostLogin");
        waitForPageToLoad(driver,60,pageLoadedToProfile);
        System.out.println("Logged in to Portal, Profile Page Loaded");
        takeScreenshot(driver, "screenshots", "ProfileLoaded");
        driver.findElement(viewProfile).click();
        waitForPageToLoad(driver,60,profileLoaded);
        driver.findElement(uploadCV).sendKeys(resumePath);
        takeScreenshot(driver, "screenshots", "CvUploaded");
        waitForElementToBeClickable(driver,60,editResumeHeadLine);
        System.out.println("CV Uploaded, Going to update Resume headline");
        driver.findElement(editResumeHeadLine).click();
        WebElement textArea= driver.findElement(resumeHeadLineTextArea);
        textArea.click();
        textArea.clear();

        textArea.sendKeys(resumeHeadlineSelected);
        driver.findElement(saveBtn).click();
        waitForPageToLoad(driver,60,profileLoaded);
        System.out.println("Updated Resume headline, Going to update Profile Summary headline");
        driver.findElement(profileSummary).click();
        WebElement editBtn = driver.findElement(getProfileSummaryEditBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})", editBtn);
        waitForElementToBeClickable(driver,60,getProfileSummaryEditBtn);
        editBtn.click();
        textArea = driver.findElement(profileSummaryEditTextArea);
        textArea.click();
        textArea.clear();
        textArea.sendKeys(profileSummarySelected);
        driver.findElement(saveBtn).click();
        waitForPageToLoad(driver,60,profileLoaded);
        System.out.println("Updated Profile Summary headline, now Logging out");
        driver.navigate().refresh();
        waitForPageToLoad(driver,60,topSideProfile);
        driver.findElement(topSideProfile).click();
        waitForPageToLoad(driver,60,modalTitle);
        driver.findElement(logout).click();
        waitForElementToBeClickable(driver,60,login);
        System.out.println(" Logged out, Good bye for now :) ");
        afterSuite(driver);
    }
    public static Path takeScreenshot(WebDriver driver, String folderPath, String filePrefix) {
        try {
            // Ensure folder exists
            Path folder = Paths.get(folderPath);
            if (!Files.exists(folder)) {
                Files.createDirectories(folder);
            }

            // Timestamp for uniqueness
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

            // File name
            String fileName = (filePrefix != null ? filePrefix + "_" : "") + timestamp + ".png";
            Path screenshotPath = folder.resolve(fileName);

            // Take screenshot
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(srcFile.toPath(), screenshotPath);

            System.out.println("Screenshot saved at: " + screenshotPath.toAbsolutePath());
            return screenshotPath;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void waitForPageToLoad(WebDriver driver, int seconds, By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public static void waitForAlert(WebDriver driver, int seconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static ChromeOptions disableImages(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless=new");
        options.addArguments("--incognito");
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        HashMap<String, Object> imagesMap = new HashMap<>();
        imagesMap.put("images",1);
        HashMap<String, Object> prefmap = new HashMap<>();
        prefmap.put("profile.default_content_setting_values",imagesMap);
        options.setExperimentalOption("prefs",prefmap);
        return options;
    }
    public static void waitForElementToBeClickable(WebDriver driver, int seconds, By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        WebElement element = driver.findElement(by);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }
    public static void afterSuite(WebDriver driver) {
        if(null != driver) {
            driver.close();
            driver.quit();
        }
    }
    public static int getRandomNumber01(int min, int max,int mod) {
        return (int) ((Math.random() * (max - min)) + min)%mod;
    }

}
