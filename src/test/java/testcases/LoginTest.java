package testcases;

import base.BaseClass;
import org.testng.annotations.Test;
import pageObjects.LoginPage;

public class LoginTest extends BaseTest{
    LoginPage loginPage;

    @Test(dataProvider = "getData", groups = {"logintests"})
    public void login(String username, String password) throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.login(username, password);
    }
}
