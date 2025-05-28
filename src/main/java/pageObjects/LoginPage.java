package pageObjects;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseClass {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    @FindBy(id = "userEmail")
    WebElement username;
    @FindBy(id = "userPassword")
    WebElement password;
    @FindBy(id = "login")
    WebElement loginButton;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;
    By errorMessageBy = By.cssSelector("[class*='flyInOut']");

    public void login(String userName, String passWord){
        username.sendKeys(userName);
        password.sendKeys(passWord);
        loginButton.click();
    }

    public String getErrorMessage() {
        waitForElementToAppear(errorMessageBy);
        return errorMessage.getText();
    }

}
