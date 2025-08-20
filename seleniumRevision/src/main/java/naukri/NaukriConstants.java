package naukri;

import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

public interface NaukriConstants {

     By login = By.xpath("//a[contains(text(),'Login') or contains(@title,'Jobseeker Login')]");
     By usernameInput = By.xpath("//label[text()='Email ID / Username']/following-sibling::input");
     By passwordInput = By.xpath("//label[text()='Password']/following-sibling::input");
     By loginBtn = By.xpath("//button[contains(@class,'loginButton')]");
     By pageLoadedToProfile = By.xpath("//div/*[text()='Saurabh Garg']");
     By viewProfile = By.xpath("//div[@class='view-profile-wrapper']/a[contains(@href,'/mnjuser/profile')]");
     By profileLoaded = By.xpath("//div/span[text()='Saurabh Garg']");
     By uploadCV = By.xpath("//input[@id = 'attachCV']");
     By editResumeHeadLine = By.xpath("//div[@class='resumeHeadline']//span[text()='Resume headline']/following-sibling::span");
     By resumeHeadLineTextArea = By.xpath("//textarea[contains(@class,'resumeHeadlineTxt')]");
     By saveBtn = By.xpath("//button[@class='btn-dark-ot' and text()='Save']");
      By topSideProfile = By.xpath("//div[@class='nI-gNb-drawer']");
      By modalTitle = By.xpath("//div[@title='Saurabh Garg']");
      By logout = By.xpath("//a[@title='Logout']");

     By profileSummary = By.xpath("//*[text()='Profile summary']");
     By getProfileSummaryEditBtn = By.xpath("//span[text()='Profile summary']/following-sibling::span[@class='edit icon']");
     By profileSummaryEditTextArea = By.xpath("//textarea[contains(@class,'profileSummaryTxt')]");

     String username= "<UserName>";
     String password = "<UserPassword>";
     String resumePathDocx = System.getProperty("user.dir") + "/Saurabh_Garg_Resume_Naukri.docx";
     String resumePathPDF = System.getProperty("user.dir") + "/Saurabh_Garg_Resume_Naukri.pdf";
     String resumeHeadline1 = "SDET engineer with 8+ years of experience in leading test operations. "
            + "Lead and groomed teams of varying sizes in designing complex test cases & scenarios. Maintained staging "
            + "servers and optimized documentation & reporting preparation procedures.";
     String profileSummaryText1 = "Proven ability to build, deploy, run, and manage individual applications. Accustomed to " +
            "executing scenarios using Cucumber, working with gradle as a build management tool, " +
            "Git for version control, Jenkins for CI/CD, and JIRA for defect tracking.";

     String profileSummaryText2 = "Test Lead with 8+ years of experience in testing software applications in Manual and Automation and SOA Testing. \n"
            + "Experienced in leading testing effort with the Product team, Development team, onshore and offshore QA team. \n"
            + "Managing design, development, and execution of the entire test process, track and report the progress of test execution. \n"
            + "Document and coordinate the detailed execution plan for all cycles to support testing.";

     String resumeHeadline2 = "A highly motivated Test Engineer, with over 8 years of experience in testing and development in multiple domains. \n"
            + "Worked extensively with the various customers and Product Organizations. \n"
            + "Very good exposure to complete life cycle development, design and testing of enterprise softwares.";
     String getResumeHeadline3 = "Having 8+ years of experience in Automation Testing using Selenium + Java in Technology Domain. "
            + "Lead a team of 5 resources. Grooming and helping them during the scripting and execution. Expertise in Core Java and Programming skills. "
            + "Automation of REST APIs with Java and Rest Assured and Karate. Experience testing Cloud Applications. "
            + "Understanding and working knowledge of Automation Testing and the Frameworks Automation of regression scripts which involve "
            + "Web application using Selenium, REST API Database automation using JDBC Driver Expertise in the phases/stages of Automation "
            + "Testing Processes such as Test Automation Feasibility Analysis, Appropriate Tool Selection, Develop Automation Framework, "
            + "Develop Test Script, Execute and Analyze result. Developed various reusable components and utilities to improve the performance and coverage of project. "
            + "Test case preparation and execution, defect logging, monitoring and closure of defects in JIRA.";
     List<String> resumeHeadline = Arrays.asList(resumeHeadline1,resumeHeadline2,profileSummaryText1,profileSummaryText2,getResumeHeadline3);
     List<String> resume = Arrays.asList(resumePathDocx,resumePathPDF,resumePathPDF);
}
