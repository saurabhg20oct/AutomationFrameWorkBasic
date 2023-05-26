package naukri;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.HashMap;


public class NaukriProfileUpdater {
    public static final By login = By.xpath("//a[@title='Jobseeker Login']");
    public static final By userName = By.xpath("//label[text()='Email ID / Username']/following-sibling::input");
    public static final By password = By.xpath("//label[text()='Password']/following-sibling::input");
    public static final By loginBtn = By.xpath("//button[contains(@class,'loginButton')]");
    public static final By pageLoadedToProfile = By.xpath("//div/*[text()='Saurabh Garg']");
    public static final By viewProfile = By.xpath("//div[@class='view-profile-wrapper']/a[contains(@href,'/mnjuser/profile')]");
    public static final By profileLoaded = By.xpath("//div/span[text()='Saurabh Garg']");
    public static final By uploadCV = By.xpath("//input[@id = 'attachCV']");
    public static final String resumePath = System.getProperty("user.dir") + "/Saurabh_Garg_Resume_Naukri.docx";
    public static final String resumePathPDF = System.getProperty("user.dir") + "/Saurabh_Garg_Resume_Naukri.pdf";
    public  static final By topSideProfile = By.xpath("//div[@class='nI-gNb-drawer']");
    public  static final By modalTitle = By.xpath("//div[@title='Saurabh Garg']");
    public  static final By logout = By.xpath("//a[@title='Logout']");

    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver(disableImages());
        driver.get("https://www.naukri.com/");
        driver.manage().window().maximize();
        waitForPageToLoad(driver,60,login);
        driver.findElement(login).click();
        waitForPageToLoad(driver,60,userName);
        driver.findElement(userName)
                .sendKeys("<username@domin.com>");
        driver.findElement(password)
                .sendKeys("P@$$w0rd");
        driver.findElement(loginBtn).click();
        waitForPageToLoad(driver,30,pageLoadedToProfile);
        driver.findElement(viewProfile).click();
        waitForPageToLoad(driver,30,profileLoaded);
        double num = Math.random();
        int myRandInt = (int)(num*100+1);
        if(myRandInt%2==0)
            driver.findElement(uploadCV).sendKeys(resumePathPDF);
        else
            driver.findElement(uploadCV).sendKeys(resumePath);
        driver.navigate().refresh();
        waitForPageToLoad(driver,30,profileLoaded);
        driver.findElement(topSideProfile).click();
        waitForPageToLoad(driver,60,modalTitle);
        driver.findElement(logout).click();
        waitForPageToLoad(driver,60,login);
        driver.quit();
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
        //options.setHeadless(true);
        HashMap<String, Object> imagesMap = new HashMap<>();
        imagesMap.put("images",2);
        HashMap<String, Object> prefmap = new HashMap<>();
        prefmap.put("profile.default_content_setting_values",imagesMap);
        options.setExperimentalOption("prefs",prefmap);
        return options;
    }

}
