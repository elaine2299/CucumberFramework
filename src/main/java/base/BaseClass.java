package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseClass {
    WebDriver driver;
    WebDriverWait wait;

    public BaseClass(WebDriver driver) {
        this.driver = driver;
    }

    public void getURL(WebDriver driver) {
        driver.get("https://rahulshettyacademy.com/client/");
    }

    public void waitForElementToAppear(By findBy){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForElementToDisappear(WebElement element1){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element1));
    }
}
