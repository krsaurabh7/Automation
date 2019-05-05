package stepDefinition;

import cucumber.TestContext;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import managers.PageObjectManager;
import managers.WebdriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import pageObjects.*;


public class CheckoutStepDefinition {

    WebDriver driver;
    HomePage homePage;
    CheckoutPage checkoutPage;
    SearchPage searchPage;
    MenuPage menuPage;
    RestaurantPage restaurantPage;
    PageObjectManager pageObjectManager;
    WebdriverManager webdriverManager;
    TestContext testContext;

    public CheckoutStepDefinition(TestContext context) {
      testContext = context;
    }

    @Given("^launch the application url in the browser$")
    public void launch_the_application_url_in_the_browser() throws Throwable {
        driver = testContext.getWebDriverManager().getDriver();
        pageObjectManager = testContext.getPageObjectManager();
        homePage = pageObjectManager.getHomePage();
        homePage.launchTheApplication();
    }

    @When("^user enters delivery location as \"([^\"]*)\"$")
    public void user_enters_delivery_location_as(String deliveryLocation) throws Throwable {
        homePage.searchDeliveryLocation(deliveryLocation);
    }

    @When("^selects first result$")
    public void select_first_result() throws Throwable {
        homePage.selectFirstResult();
    }

    @When("^clicks on Search$")
    public void clicks_on_Search() throws Throwable {
        restaurantPage = pageObjectManager.getRestaurantPage();
        restaurantPage.clickOnSearch();
    }

    @When("^search for restaurant called \"([^\"]*)\" and clicks on it$")
    public void search_for_restaurant_called_and_clicks_on_it(String restaurantName) throws Throwable {
        searchPage = pageObjectManager.getSearchPage();
        searchPage.searchForRestaurantAndClickIt(restaurantName);
    }

    @When("^adds items and their quantities to the cart$")
    public void adds_quantity_and_items_to_the_cart(DataTable itemQuantities) throws Throwable {
        menuPage = pageObjectManager.getMenuPage();
        menuPage.addsItemsToCart(itemQuantities);
    }

    @When("^clicks on Checkout$")
    public void clicks_on_Checkout() throws Throwable {
        menuPage.clicksOnCheckout();
    }

    @Then("^verifies items and quantity added in checkout page$")
    public void verifies_items_and_quantity_added_in_checkout_page(DataTable itemOnCheckout) throws Throwable {
        checkoutPage = pageObjectManager.getCheckoutPage();
        Assert.assertTrue(checkoutPage.verifyItemsAddedInCheckoutPage(itemOnCheckout));
    }

    @When("^clicks on New to Swiggy SIGN UP$")
    public void clicks_on_New_to_Swiggy_SIGN_UP() throws Throwable {
        checkoutPage.clicksOnNewToSwiggySIGNUP();
    }


    @When("^enters details like \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\"$")
    public void enters_details_like_and(String name, String phoneNo, String password, String email) throws Throwable {
        checkoutPage.entersUserDetails(phoneNo, name, email, password);
    }

    @When("^clicks on Have a referral code$")
    public void clicks_on_Have_a_referral_code() throws Throwable {
        checkoutPage.clickOnHaveAReferralCode();
    }

    @When("^clicks on CONTINUE$")
    public void clicks_on_CONTINUE() throws Throwable {
        checkoutPage.clicksOnContinue();
    }

    @Then("^verifies Error message at Email field Email id already exists$")
    public void verify_Error_message_at_Email_field_Email_id_already_exists() throws Throwable {
        Assert.assertTrue(checkoutPage.verifyEmailErrorMessage());
    }

    @Then("^changes the quantity of \"([^\"]*)\" to (\\d+)$")
    public void changes_the_quantity_of_to(String item, int quantity) throws Throwable {
        checkoutPage.reduceTheQuantityOfItem(item,quantity);
    }
}



