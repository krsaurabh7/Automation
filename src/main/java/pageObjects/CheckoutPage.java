package pageObjects;

import cucumber.api.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean verifyItemsAddedInCheckoutPage(DataTable expectedMap) throws Throwable {
        boolean itemsMatch = true;
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("_33KRy")));
        List<WebElement> itemsOnCheckout = driver.findElements(By.className("_33KRy"));
        List<WebElement> QuantityOnCheckout = driver.findElements(By.className("_2zAXs"));
        Map<String,String> data = expectedMap.asMap(String.class, String.class);
        for(int countOfItem = 0 ; countOfItem <itemsOnCheckout.size(); countOfItem++){
            if(!(data.get(itemsOnCheckout.get(countOfItem).getText()).equalsIgnoreCase
                    (QuantityOnCheckout.get(countOfItem).getText()))){
                return false;
            }
        }
        return itemsMatch;
    }

    public void clicksOnNewToSwiggySIGNUP() {
        driver.findElement(By.xpath(".//div[text()='SIGN UP']")).click();
    }

    public void entersUserDetails(String mobileNo, String name, String email, String password) throws Throwable {
        wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mobile")));
        driver.findElement(By.id("mobile")).sendKeys(mobileNo);
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    public void clickOnHaveAReferralCode() throws Throwable {
        driver.findElement(By.xpath(".//div[text()='Have a referral code?']")).click();
    }

    public void clicksOnContinue() throws Throwable {
        driver.findElement(By.xpath(".//a[text()='CONTINUE']")).click();
    }

    public boolean verifyEmailErrorMessage() throws Throwable {
        wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//label[text()='Email id already exists']")));
        if(driver.findElements(By.xpath(".//label[text()='Email id already exists']")).size() != 0){
            return true;
        }
        return false;
    }

    public void reduceTheQuantityOfItem(String itemName, int reducedQuantity) throws Throwable {
        List<WebElement> itemsOnCheckout = driver.findElements(By.className("_33KRy"));
        List<WebElement> quantityOnCheckout = driver.findElements(By.xpath(".//div[@class='_29Y5Z']"));

        for(int countOfItem = 0 ; countOfItem <itemsOnCheckout.size(); countOfItem++){
            if(itemsOnCheckout.get(countOfItem).getText().equalsIgnoreCase(itemName))
            {
                for(int quantityOfItem = 1; quantityOfItem <= reducedQuantity; quantityOfItem++){
                    quantityOnCheckout.get(countOfItem).click();
                }
            }
        }
    }
}
