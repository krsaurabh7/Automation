package pageObjects;

import dataProvider.ConfigFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchDeliveryLocation(String deliveryLocation){
        driver.findElement(By.id("location")).sendKeys(deliveryLocation);
    }

    public void selectFirstResult() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//span[@class='icon-location _1HNsg']")));
        driver.findElements(By.xpath(".//span[@class='icon-location _1HNsg']")).get(0).click();
    }

    public void launchTheApplication(){
        ConfigFileReader configFileReader = new ConfigFileReader();
        driver.manage().window().maximize();
        driver.get(configFileReader.getApplicationUrl());
    }
}
