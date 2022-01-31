package BaseActions;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CustomizingForYourself {
    //arrange
    //public String b2bUrl = "http://http://b2b-1-10-2.devsotbit.ru/b2bcabinet/";
    //public String b2bUrl = "http://b2bshop-centersuvenir.sotbit.com/b2bcabinet/";
    //public String b2bUrl = "http://b2b-last-1-9-0.devsotbit.ru/b2bcabinet/";
    //public String b2bUrl = "http://b2b-1-10-0.devsotbit.ru/b2bcabinet/";
    //public String b2bUrl = "http://b2b.white-siberia.sotbit.com/";
    //public String b2bUrl = "http://b2b-1-10-1.devsotbit.ru/b2bcabinet/";

    public String b2bUrl = "http://b2b-gospod-10-0.devsotbit.ru/b2bcabinet/";
    public String adminLogin = "admin";
    public String adminPassword = "sotbit123456";


    //public String adminLogin = "support@sotbit.ru";
    //public String adminPassword = "sW}$2od]H!s8gY[h";

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")  + "\\webdriver\\chromedriver.exe"
        );
        driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(-1000, 0));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void tearDown() throws IOException {
        var nameScreen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(nameScreen, new File(System.getProperty("user.dir") + "\\screen\\screenshot.png"));
        //driver.quit();
    }

    @After
    public void exitFromB2BToAuthorizationTab() {
//        try {
//            driver.findElement(exitButtonInb2bCabinetLocator).click();
//        } catch (Exception e) {
//            System.out.println("я не нашел кнопку выйти");
//        }
    }
}
