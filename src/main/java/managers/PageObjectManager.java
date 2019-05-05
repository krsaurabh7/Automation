package managers;

import org.openqa.selenium.WebDriver;
import pageObjects.*;


public class PageObjectManager {
    private WebDriver driver;
    private HomePage homePage;
    private CheckoutPage checkoutPage;
    private MenuPage menuPage;
    private RestaurantPage restaurantPage;
    private SearchPage searchPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        return (homePage == null) ? homePage = new HomePage(driver) : homePage;
    }

    public CheckoutPage getCheckoutPage() {
        return (checkoutPage == null) ? checkoutPage = new CheckoutPage(driver) : checkoutPage;
    }

    public MenuPage getMenuPage() {
        return (menuPage == null) ? menuPage = new MenuPage(driver) : menuPage;
    }

    public RestaurantPage getRestaurantPage() {
        return (restaurantPage == null) ? restaurantPage = new RestaurantPage(driver) : restaurantPage;
    }

    public SearchPage getSearchPage() {
        return (searchPage == null) ? searchPage = new SearchPage(driver) : searchPage;
    }
}
