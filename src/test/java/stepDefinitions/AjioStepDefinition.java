package stepDefinitions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;
import components.Utilities;
import io.cucumber.java.en.*;
import org.junit.Assert;


public class AjioStepDefinition {
    private final Playwright playwright = Playwright.create();
    private final Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    private final Page page = browser.newPage();
    private final Utilities utilities = new Utilities(page, playwright, browser);


    @Given("User opens Ajio website")
    public void user_opens_ajio_website() {
        utilities.openWebsite("https://www.ajio.com/");
        page.locator("//div[@class='modal fade in  ']").click();
        page.waitForLoadState(LoadState.LOAD);

        Assert.assertTrue(page.locator("//img[contains(@src, 'Ajio-Logo.svg')]").isVisible());

    }

    @And("close Browser")
    public void close_Browser() {
        utilities.closeBrowser();
    }

    @When("User navigates to {string} category and {string} section")
    public void userNavigatesToCategoryAndSection(String category, String section) {
        utilities.userNavigatesToPage(category, section);
    }

    @Then("validate that user is on {string} page")
    public void validateThatUserIsOnPage(String section) {

        Assert.assertEquals("Invalid Category",section,utilities.fetchCategoryFromListing());
    }

    @When("User apply {string} gender filter")
    public void userApplyGenderFilter(String gender) {
        utilities.applyGenderFilter(gender);
    }

//    @Then("validate that {string} filter is applied")
//    public void validateThatFilterIsApplied(String filter) {
//        Assert.assertTrue(page.locator("//span[@class='pull-left']").innerText().contains(filter));
//    }
}
