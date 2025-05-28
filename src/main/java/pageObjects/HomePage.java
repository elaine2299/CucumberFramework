package pageObjects;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class HomePage extends BaseClass {
    WebDriver driver;
    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(xpath = "//*[@routerlink='/dashboard/cart']")
    WebElement cart;

    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.xpath("//*[text()=' Add To Cart']");
    By toastContainer = By.cssSelector("#toast-container");
    By toastContainer2 = By.cssSelector(".ng-animating");
    By cartbutton = By.xpath("//*[@routerlink='/dashboard/cart']");

    public List<WebElement> getProductList() throws InterruptedException {
        waitForElementToAppear(productsBy);
        Thread.sleep(200);
        return products;
    }
    public WebElement searchProduct(String productName) throws InterruptedException {
        WebElement prod = getProductList().stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        return prod;

    }

    public void addProdToCart(String prodName) throws InterruptedException {
        WebElement prod = searchProduct(prodName);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toastContainer);
        waitForElementToAppear(toastContainer2);
        waitForElementToAppear(cartbutton);
        cart.click();
    }


}


