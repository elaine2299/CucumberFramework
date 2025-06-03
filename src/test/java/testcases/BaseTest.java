package testcases;

import base.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Properties;

public class BaseTest {
    public static WebDriver driver;
    BaseClass baseClass;
    public static ExtentReports extentReports;
    Properties prop;


    public WebDriver browserSetup(WebDriver driver) {
        prop = new Properties();
        //String browserName = System.getProperty("browser");
        String browserName = "edge";
        if (browserName == null || browserName.isEmpty()) {
            browserName = "chrome"; // Default to Chrome
        }

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        return driver;
    }

    public void loginSuccess(){
        System.out.println("User Logged in Successfully Great!");
        System.out.println("Commit successful");
        System.out.println("Github Webhooks done!");
        System.out.println("Test Success");
        System.out.println("Test Success, Yay!!");
    }

    /*@DataProvider
    public Object[][] getData() throws IOException {
        return new Object[][]{{"testemail1@gmail.com", "Password123!"}};
    }*/

    //@BeforeClass
    public void launchApplication() throws IOException {
        driver = browserSetup(driver);
        baseClass = new BaseClass(driver);
        baseClass.getURL(driver);
    }

    //@AfterClass
    public void tearDown() throws IOException {
        if (driver != null) {
            driver.quit();
        }
    }
}
