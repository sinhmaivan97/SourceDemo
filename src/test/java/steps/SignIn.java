package steps;

import commons.PageGeneratorManager;
import cucumberHooks.Hooks;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageObjects.SignInObject;

public class SignIn {
    WebDriver driver;
    private SignInObject signInObject;

    public SignIn() {
        this.driver = Hooks.openAndQuitBrowser();
        signInObject = PageGeneratorManager.getSignInPage(driver);
    }

    @When("Click Sign in button")
    public void click_sign_in_button() {
        signInObject.clickSignInBtn();
    }

    @When("Enter email account")
    public void enter_email_account() {
        signInObject.inputEmailName("Enteryourgmailaccounttotest@gmail.com");
    }

    @When("Click the next button")
    public void click_the_next_button() {
        signInObject.clickNextBtn();
    }

    @When("Enter password")
    public void enter_password() {
        signInObject.inputPassword("yourpassword");
    }
}
