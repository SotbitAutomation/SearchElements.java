package base_actions;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

    /*
       Пишет логи для аллюра, в случае падения прикрепляет аттчменты (скриншот и ошибку)
     */
public class LogsAndScreenshotsForAllure implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
//        saveScreenshotOnFailure(CustomizingForYourself.getDriver());
//        saveLogs(result.getMethod().getConstructorOrMethod().getName());

        Object testClass = result.getInstance();
        //WebDriver driver = ((Config) testClass).getDriver();
        Config config = new Config();
        if (config.driver instanceof  WebDriver){
            saveScreenshotOnFailure(config.driver);
        }
        saveLogs(result.getTestName() + result.getEndMillis());
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
