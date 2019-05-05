package managers;

import enums.DriverType;
import enums.EnvironmentType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WebdriverManager {

    private WebDriver driver;
    private static DriverType driverType;
    private static EnvironmentType environmentType;

    public WebdriverManager(){
        driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
        environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
    }

    public WebDriver getDriver() throws IOException{
        if(driver == null) driver = createDriver();
        return driver;
    }

    private WebDriver createDriver() throws IOException{
        switch (environmentType) {
            case LOCAL : driver = createLocalDriver();
                break;
            case REMOTE : driver = createRemoteDriver();
                break;
        }
        return driver;
    }

    private WebDriver createRemoteDriver() {
        throw new RuntimeException("not implemented");
    }

    private WebDriver createLocalDriver() throws IOException{
        switch (driverType) {
            case FIREFOX :
                File relativeFile = new File("drivers/geckodriver");
                System.setProperty("webdriver.gecko.driver", relativeFile.getAbsolutePath());
                driver = new FirefoxDriver();
                break;
            case CHROME :
                relativeFile = new File("drivers/chromedriver");
                String exePath = relativeFile.getAbsolutePath();
                System.setProperty("webdriver.chrome.driver", exePath);
                driver = new ChromeDriver();
                break;
        }

        if(FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize()) driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }

    public void closeDriver() {
        //driver.close();
        driver.quit();
    }
}
