/*
package testcases;

import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import java.util.List;

public class PlaceOrderTest extends BaseTest {
    BaseClass baseClass;
    LoginPage loginPage;
    HomePage homePage;
    String prodName = "ZARA COAT 3";


    @Test()
    public void login() throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.login("testemail1@gmail.com", "Password123!");
    }

    @Test(dependsOnMethods = "login")
    public void addToCart() throws InterruptedException {
        homePage = new HomePage(driver);
        List<WebElement> products = homePage.getProductList();
        Thread.sleep(2000);
        homePage.addProdToCart(prodName);

    }

}
*/
