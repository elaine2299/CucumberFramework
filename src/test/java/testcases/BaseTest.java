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


    /*public static ExtentReports extentReport() {
        String path = System.getProperty("user.dir") + "\\utils\\" + "TestReport.html";
        System.out.println("The user dir is: " + System.getProperty("user.dir"));
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation results");
        reporter.config().setDocumentTitle("Test Results");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        return extent;
    }*/

    public static void getExtentReport() throws IOException {
        extentReports = new ExtentReports();
        File file = new File("Extent-Report.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
        extentReports.attachReporter(sparkReporter);
        String base64Code = captureScreenshot(driver);
        String path = captureScreenshot("Google.jpg", driver);

        extentReports
                .createTest("Screenshot 1", " This is the screenshot 1 test")
                .info("This is an info msg")
                .addScreenCaptureFromBase64String(base64Code);


        extentReports
                .createTest("Screenshot 2", " This is the screenshot 2 test")
                .info("This is an info msg")
                .addScreenCaptureFromBase64String(base64Code, "Test 2 title");

        extentReports
                .createTest("Screenshot 3", " This is the screenshot 3 test")
                .info("This is an info msg")
                .addScreenCaptureFromPath(path, "Test 3");
    }

 /*   public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destfile = new File(System.getProperty("user.dir") + "\\utils\\" + testcaseName + ".png");
            String destFilePath = destfile.getAbsolutePath();
            FileUtils.copyFile(source, destfile);
            return destFilePath;
    }*/

    public static String captureScreenshot(String testcaseName, WebDriver driver) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File screenshotDir = new File("./screenshot");

        // Ensure the directory exists
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }

        File destFile = new File(screenshotDir, testcaseName);
        try {
            FileUtils.copyFile(sourceFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Screenshot saved successfully");
        return destFile.getAbsolutePath();
    }

    public static String captureScreenshot( WebDriver driver) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        String base64Code = takesScreenshot.getScreenshotAs(OutputType.BASE64);
        System.out.println("Screenshot saved successfully");
        return base64Code;
    }

    public void loginSuccess(){
        System.out.println("User Logged in Successfully Great!");
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        return new Object[][]{{"testemail1@gmail.com", "Password123!"}};
    }

    @BeforeClass
    public void launchApplication() throws IOException {
        driver = browserSetup(driver);
        baseClass = new BaseClass(driver);
        baseClass.getURL(driver);
        /*listener = new Listener();
        listener.setDriver(driver);*/
    }

    @AfterClass
    public void tearDown() throws IOException {
        //getExtentReport();
        /*extentReports.flush();
        // Open report before quitting WebDriver
        Desktop.getDesktop().browse(new File("Extent-Report.html").toURI());*/
        if (driver != null) {
            driver.quit();
        }
    }
}