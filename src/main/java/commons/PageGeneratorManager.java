package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.ChangeLanguageObject;
import pageObjects.ResearchObject;
import pageObjects.SignInObject;

public class PageGeneratorManager {
    public static SignInObject getSignInPage(WebDriver driver) {
        return new SignInObject(driver);
    }

    public static ResearchObject getResearchPage(WebDriver driver) {
        return new ResearchObject(driver);
    }

    public static ChangeLanguageObject getChangeLanguage(WebDriver driver) {
        return new ChangeLanguageObject(driver);
    }
}
