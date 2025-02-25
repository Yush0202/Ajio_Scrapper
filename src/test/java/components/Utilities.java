package components;

import com.microsoft.playwright.*;


import com.microsoft.playwright.options.LoadState;

import org.junit.Assert;



public class Utilities{
    private final Page page;
    private final Playwright playwright;
    private final Browser browser;

    public Utilities(Page page, Playwright playwright, Browser browser){
        this.page = page;
        this.playwright = playwright;
        this.browser = browser;
    }

    public void openWebsite(String url){
        page.navigate(url);
        page.waitForLoadState(LoadState.LOAD);
    }

    public void userNavigatesToPage(String category, String section){
        page.locator("//li[@data-test='li-MEN']").hover();
        page.locator(String.format("//div[@class='items']/span/a[@href='/%s-%s/c/830216013']", category, section)).click();
        page.waitForTimeout(2000);

    }

    public String fetchCategoryFromListing(){
        return page.locator("//h1/div[@class='header1']").innerText();
    }

    public void applyGenderFilter(String gender){
        System.out.println("Applying gender filter: " + gender);
        try {

            page.locator("//label[@for='Men']//preceding-sibling::input").click();
            page.waitForTimeout(2000);
        }
        catch (TimeoutError e){
            Assert.fail("Could not apply gender filter");
        }
    }

    public void closeBrowser(){
        browser.close();
        playwright.close();
    }
}

