package steps;

import commons.PageGeneratorManager;
import cucumberHooks.Hooks;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageObjects.ChangeLanguageObject;

public class ChangeLanguage {
    WebDriver driver;
    private ChangeLanguageObject changeLanguageObject;

    public ChangeLanguage() {
        this.driver = Hooks.openAndQuitBrowser();
        changeLanguageObject = PageGeneratorManager.getChangeLanguage(driver);
    }

    @When("Click on the button to change the language from English to French")
    public void click_on_the_button_to_change_the_language_from_English_to_French() {
        changeLanguageObject.clickChooseTheFirstLanguageBtn();
    }

    @When("Click on the French section")
    public void click_on_the_french_section() {
        changeLanguageObject.clickChooseFranceItem();
    }

    @When("Click on the Espanol section")
    public void click_on_the_espanol_section() {
        changeLanguageObject.clickChooseEspanolItem();
    }

    @When("Click on the button to change the language from French to Espanol")
    public void click_on_the_button_to_change_the_language_from_French_to_Espanol() {
        changeLanguageObject.clickChooseTheSecondLanguageBtn();
    }
}
