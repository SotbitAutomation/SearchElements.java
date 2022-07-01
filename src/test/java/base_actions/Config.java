package base_actions;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import setting_up_cabinet_for_testing.SettingUpCabinetForTesting;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


@Listeners({LogsAndScreenshotsForAllure.class})
public class Config {

    /*
       url тестируемого кабинета логин и пароль
     */
    public static final String b2bUrl = "http://b2b-gospod.devsotbit.ru/";
    //public static final = "http://b2b-11-3.devsotbit.ru/b2bcabinet/";
    public static final String adminLogin = "admin";
    public static final String adminPassword = "sotbit123456";

    /*
       Настройки для более быстрой работы
       1. установлен ли модуль Мультирегиональность, 2-4 есть ли оптовые (индивидуальные) цены, 5 есть ли свой тип цен, 6 Есть ли ТП в каталоге
       эти настройки для более быстрой работы, например если не указать есть ли офферы, то придется в методах реализовывать попытку раскрыть оффер
       который при каждом заходе в каталог (смене страницы) будет пытаться кликнуть "Подробнее" у оффера, не будет ее находить, выбрасывать ошибку...
     */
    public boolean isThereModuleMultiRegions = true;
    public boolean isThereASmallOptPrice = true;
    public boolean isThereAOptPrice = true;
    public boolean isThereAnIndividualPrice = true;
    public boolean isThereATestPrice = false;
    public boolean areThereAnyOffers = false;

    /*
       Объявление объектов для обращения к селениумскому фреймворку (для доступа к стандартным методам управления браузером, ожиданиями)
     */
    public static WebDriver driver;
    public static WebDriverWait wait;
    public By exitButtonInb2bCabinetLocator = By.xpath("(//*[contains(@href, 'logout')])[last()]");

    /*
       для задания ширины и высоты браузера (раскоментить метод в @BeforeSuite, и установить нужное разрешение). Иначе раскрывает во весь экран
     */
    public static int browserWindowWidth = 1920;
    public static int browserWindowHeight = 1080; // съедает место предупреждение что это авто браузер, 900 = 768
    public static void setTheScreenWidth(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
        driver.manage().window().setPosition(new Point(-1920, 0));
    }

    @BeforeSuite
    public static void setUpSuite() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\resources\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(-1000, 0));
        setTheScreenWidth(browserWindowWidth, browserWindowHeight);
        wait = new WebDriverWait(driver, 7);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void clearAllCacheForSuite() throws IOException {
        SettingUpCabinetForTesting set = new SettingUpCabinetForTesting();
        set.clearAllCacheForTests();
    }


}
