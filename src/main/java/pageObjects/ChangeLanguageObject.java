package pageObjects;

import commons.BaseTest;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import pageUIs.ChangeLanguageUI;

public class ChangeLanguageObject extends BaseTest {
    WebDriver driver;

    public ChangeLanguageObject(WebDriver driver) {
        this.driver = driver;
    }

    @SneakyThrows
    public void clickChooseTheFirstLanguageBtn() {
        waitForElementClickable(driver, ChangeLanguageUI.ChangeLanguageTheFirstBtn);
        clickToElementByJS(driver, ChangeLanguageUI.ChangeLanguageTheFirstBtn);
        Thread.sleep(1500);
    }

    @SneakyThrows
    public void clickChooseFranceItem() {
        waitForElementClickable(driver, ChangeLanguageUI.ChooseFrance);
        clickToElementByJS(driver, ChangeLanguageUI.ChooseFrance);
        Thread.sleep(2000);
    }

    @SneakyThrows
    public void clickChooseTheSecondLanguageBtn() {
        waitForElementClickable(driver, ChangeLanguageUI.ChangeLanguageTheSecondBtn);
        clickToElementByJS(driver, ChangeLanguageUI.ChangeLanguageTheSecondBtn);
        Thread.sleep(1500);
    }

    @SneakyThrows
    public void clickChooseEspanolItem() {
        waitForElementClickable(driver, ChangeLanguageUI.ChooseEspanol);
        clickToElementByJS(driver, ChangeLanguageUI.ChooseEspanol);
        Thread.sleep(2000);
    }
}
