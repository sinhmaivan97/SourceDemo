package pageObjects;

import commons.BaseTest;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import pageUIs.ResearchUI;

public class ResearchObject extends BaseTest {
    WebDriver driver;

    public ResearchObject(WebDriver driver) {
        this.driver = driver;
    }


    @SneakyThrows
    public void clickResearchBtn() {
        waitForElementClickable(driver, ResearchUI.ResearchBtn);
        clickToElementByJS(driver, ResearchUI.ResearchBtn);
        Thread.sleep(1500);
    }

    @SneakyThrows
    public void inputKeywordSearch(String text) {
        waitForElementVisible(driver, ResearchUI.ResearchEntry);
        sendkeyToElementAndEnter(driver, ResearchUI.ResearchEntry, text);
        Thread.sleep(1500);
    }

    @SneakyThrows
    public void showmoreAndClickShowmoreBtn() {
        executeJsScrollToBottom(driver);
        waitForElementClickable(driver, ResearchUI.ShowmoreBtn);
        clickToElementByJS(driver, ResearchUI.ShowmoreBtn);
        Thread.sleep(1500);
    }
}
