package steps;

import commons.PageGeneratorManager;
import cucumberHooks.Hooks;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageObjects.ResearchObject;

public class Research {
    WebDriver driver;
    private ResearchObject researchObject;

    public Research() {
        this.driver = Hooks.openAndQuitBrowser();
        researchObject = PageGeneratorManager.getResearchPage(driver);
    }

    @When("Click research button")
    public void click_research_button() {
        researchObject.clickResearchBtn();
    }

    @When("Enter the keyword you want to search for")
    public void enter_the_keyword_you_want_to_search_for() {
        researchObject.inputKeywordSearch("chatbot");
    }

    @When("Scroll and click the show more results button")
    public void scroll_and_click_the_show_more_results_button() {
        researchObject.showmoreAndClickShowmoreBtn();
    }
}
