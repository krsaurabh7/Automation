package stepDefinition;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.vimalselvam.cucumber.listener.Reporter;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.google.common.io.Files;
import cucumber.TestContext;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.openqa.selenium.WebDriver;

public class Hooks {

   TestContext testContext;

    public Hooks(TestContext context) {
        testContext = context;
    }

    @After(order = 1 )
    public void afterScenario(Scenario scenario) throws Exception {
            String finalDestination =CaptureScreen(testContext.getWebDriverManager().getDriver(),scenario);
            if (scenario.isFailed()) {
            //To add it in the extent report
            Reporter.addScreenCaptureFromPath(finalDestination);
        }
    }

    @After(order = 0)
    public void AfterSteps(){
        testContext.getWebDriverManager().closeDriver();
    }

    private String CaptureScreen(WebDriver driver, Scenario scenario)
    {
        String screenshotName = scenario.getName().replaceAll(" ", "_");
        File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String encodedBase64 = null;
        FileInputStream fileInputStreamReader = null;
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        try {
            fileInputStreamReader = new FileInputStream(sourceFile);
            byte[] bytes = new byte[(int)sourceFile.length()];
            fileInputStreamReader.read(bytes);
            encodedBase64 = new String(Base64.encodeBase64(bytes));

            String screenShotDestination = System.getProperty("user.dir") + "/screenshots/" +
                    screenshotName+ dateName + ".png";

            File destination = new File(screenShotDestination);
            Files.copy(sourceFile, destination);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "data:image/png;base64,"+encodedBase64;
    }

}