package BaseActions;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
//        saveScreenshotOnFailure(CustomizingForYourself.getDriver());
//        saveLogs(result.getMethod().getConstructorOrMethod().getName());

        Object testClass = result.getInstance();
        WebDriver driver = ((CustomizingForYourself) testClass).getDriver();
        if (driver instanceof  WebDriver){
            saveScreenshotOnFailure(driver);
        }
        saveLogs(result.getTestName());
    }

    @Attachment (value="Screennshot", type = "image/png")
    public byte[] saveScreenshotOnFailure(WebDriver driver){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
    @Attachment (value="Stacktrac", type = "text/plain")
    public static String saveLogs(String message){
        return message;
    }
}
