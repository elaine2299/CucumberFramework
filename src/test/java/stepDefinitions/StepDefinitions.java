package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;
import testcases.BaseTest;

import java.io.IOException;

public class StepDefinitions extends BaseTest {

    LoginPage loginPage;

    @Given("User is on Login Page")
    public void User_is_on_Login_Page() throws IOException {
        launchApplication();
    }

    @When("^User enters username (.+) and password (.+)$")
    public void User_enters_username_and_password(String username, String password){
        loginPage = new LoginPage(driver);
        loginPage.login(username, password);
    }
    @Then("User is successfully Logged in")
    public void User_is_successfully_Logged_in() throws IOException {
        loginSuccess();
        tearDown();


    }
}
