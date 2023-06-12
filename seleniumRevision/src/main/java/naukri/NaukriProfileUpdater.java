package naukri;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class NaukriProfileUpdater {
    public static final By login = By.xpath("//a[@title='Jobseeker Login']");
    public static final By usernameInput = By.xpath("//label[text()='Email ID / Username']/following-sibling::input");
    public static final By passwordInput = By.xpath("//label[text()='Password']/following-sibling::input");
    public static final By loginBtn = By.xpath("//button[contains(@class,'loginButton')]");
    public static final By pageLoadedToProfile = By.xpath("//div/*[text()='Saurabh Garg']");
    public static final By viewProfile = By.xpath("//div[@class='view-profile-wrapper']/a[contains(@href,'/mnjuser/profile')]");
    public static final By profileLoaded = By.xpath("//div/span[text()='Saurabh Garg']");
    public static final By uploadCV = By.xpath("//input[@id = 'attachCV']");
    public static final By editResumeHeadLine = By.xpath("//div[@class='resumeHeadline']//span[text()='Resume headline']/following-sibling::span");
    public static final By resumeHeadLineTextArea = By.xpath("//textarea[contains(@class,'resumeHeadlineTxt')]");
    public static final By saveBtn = By.xpath("//button[@class='btn-dark-ot' and text()='Save']");
    public  static final By topSideProfile = By.xpath("//div[@class='nI-gNb-drawer']");
    public  static final By modalTitle = By.xpath("//div[@title='Saurabh Garg']");
    public  static final By logout = By.xpath("//a[@title='Logout']");

    /*
    Profile Details to be entered here
    */
    public static final String username= "<Enter Your Username>";
    public static final String password = "<Enter Your Password>";
    public static final String resumePathDocx = System.getProperty("user.dir") + "/Saurabh_Garg_Resume_Naukri.docx";
    public static final String resumePathPDF = System.getProperty("user.dir") + "/Saurabh_Garg_Resume_Naukri.pdf";
    public static final String profileSummary1 = "SDET engineer with 8+ years of experience in leading test operations. "
            + "Lead and groomed teams of varying sizes in designing complex test cases & scenarios. Maintained staging "
            + "servers and optimized documentation & reporting preparation procedures.";
    public static final String profileSummary2 = "Proven ability to build, deploy, run, and manage individual applications. Accustomed to " +
            "executing scenarios using Cucumber, working with gradle as a build management tool, " +
            "Git for version control, Jenkins for CI/CD, and JIRA for defect tracking.";
    List<String> profileSummary = Arrays.asList(profileSummary1,profileSummary2);
    List<String> resume = Arrays.asList(resumePathDocx,resumePathPDF);

    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver(disableImages());
        driver.get("https://www.naukri.com/");
        driver.manage().window().maximize();
        waitForPageToLoad(driver,60,login);
        //takeScreenShot(driver.findElement(login),"abc.png");
        driver.findElement(login).click();
        waitForPageToLoad(driver,60, usernameInput);
        driver.findElement(usernameInput)
                .sendKeys(username);
        driver.findElement(passwordInput)
                .sendKeys(password);
        driver.findElement(loginBtn).click();
        waitForPageToLoad(driver,60,pageLoadedToProfile);
        driver.findElement(viewProfile).click();
        waitForPageToLoad(driver,60,profileLoaded);
        double num = Math.random();
        int myRandInt = (int)(num*10+1);
        String resumeHeadline;
        if(myRandInt%2==0) {
            driver.findElement(uploadCV).sendKeys(resumePathPDF);
            resumeHeadline = profileSummary1;
        }
        else{
            driver.findElement(uploadCV).sendKeys(resumePathDocx);
            resumeHeadline = profileSummary2;
        }
        driver.findElement(editResumeHeadLine).click();
        WebElement textArea= driver.findElement(resumeHeadLineTextArea);
        textArea.click();
        textArea.clear();
        textArea.sendKeys(resumeHeadline);
        driver.findElement(saveBtn).click();
        waitForPageToLoad(driver,60,profileLoaded);
        driver.navigate().refresh();
        waitForPageToLoad(driver,60,profileLoaded);
        driver.findElement(topSideProfile).click();
        waitForPageToLoad(driver,60,modalTitle);
        driver.findElement(logout).click();
        waitForElementToBeClickable(driver,60,login);
        afterSuite(driver);
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
}
