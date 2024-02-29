package pageObjects;

import commons.BaseTest;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import pageUIs.SignInUI;

public class SignInObject extends BaseTest {
    WebDriver driver;

    public SignInObject(WebDriver driver) {
        this.driver = driver;
    }

    @SneakyThrows
    public void clickSignInBtn() {
        waitForElementClickable(driver, SignInUI.SignInBtn);
        clickToElementByJS(driver, SignInUI.SignInBtn);
        Thread.sleep(1500);
    }

    @SneakyThrows
    public void inputEmailName(String text) {
        waitForElementVisible(driver, SignInUI.EmailEntry);
        sendkeyToElement(driver, SignInUI.EmailEntry, text);
        Thread.sleep(1500);
    }

    @SneakyThrows
    public void clickNextBtn() {
        waitForElementClickable(driver, SignInUI.NextBtn);
        clickToElementByJS(driver, SignInUI.NextBtn);
        Thread.sleep(1500);
    }

    @SneakyThrows
    public void inputPassword(String text) {
        waitForElementVisible(driver, SignInUI.PasswordEntry);
        sendkeyToElement(driver, SignInUI.PasswordEntry, text);
        Thread.sleep(1500);
    }
}
