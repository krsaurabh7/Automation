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

public class MenuPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public MenuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addsItemsToCart(DataTable itemQuantities) throws Throwable {
        wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("_2wg_t")));

        for (Map<String, String> data : itemQuantities.asMaps(String.class, String.class)) {
            driver.findElement(By.xpath(".//input[@class='_5mXmk']")).clear();
            driver.findElement(By.xpath(".//input[@class='_5mXmk']")).sendKeys(data.get("items"));
            Thread.sleep(1000);

            List<WebElement> webElementItems = driver.findElements(By.xpath(".//div[@class='_2wg_t']"));
            webElementItems.get(0).findElement(By.xpath("descendant::div[text()='ADD']")).click();

            Thread.sleep(1000);
            webElementItems = driver.findElements(By.xpath(".//div[@class='_2wg_t']"));
            for(int numberOfItem=1; numberOfItem < Integer.parseInt(data.get("quantity")); numberOfItem++){
                webElementItems.get(0).findElement(By.xpath("descendant::div[text()='+']")).click();
                Thread.sleep(1000);
            }
        }
    }

    public void clicksOnCheckout(){
        driver.findElement(By.xpath(".//div[text()='Checkout']")).click();
    }
}
