package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SearchPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchForRestaurantAndClickIt(String restaurantName) {
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("_2BJMh")));
        driver.findElement(By.className("_2BJMh")).sendKeys(restaurantName);
        driver.findElement(By.className("_2BJMh")).sendKeys(Keys.RETURN);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[@class='nA6kb' and text()='"+restaurantName+"']")));
        driver.findElement(By.xpath("//div[@class='nA6kb' and text()='"+restaurantName+"']")).click();
    }
}
