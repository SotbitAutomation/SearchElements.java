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

import java.io.IOException;
import java.util.concurrent.TimeUnit;


@Listeners({LogsAndScreenshotsForAllure.class})
public class Config {

    /*
       url тестируемого кабинета логин и пароль
     */
    //public static final String b2bUrl = "http://b2b-gospod.devsotbit.ru/";
    public static final String b2bUrl = "http://b2b-last-11-3.devsotbit.ru/b2bcabinet/";
    public static final String adminLogin = "admin";
    public static final String adminPassword = "sotbit123456";

    /*
       Настройки для более быстрой работы
       1. установлен ли модуль Мультирегиональность, 2-4 есть ли оптовые (индивидуальные) цены, 5 есть ли свой тип цен, 6 Есть ли ТП в каталоге
       эти настройки для более быстрой работы, например если не указать есть ли офферы, то придется в методах реализовывать попытку раскрыть оффер
       который при каждом заходе в каталог (смене страницы) будет пытаться кликнуть "Подробнее" у оффера, не будет ее находить, выбрасывать ошибку...
     */
    public boolean isThereModuleMultiRegions = false;
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
       для задания ширины и высоты браузера
     */
    public static int browserWindowWidth = 1920;
    public static int browserWindowHeight = 1080; // съедает место предупреждение что это авто браузер, 900 = 768
    public static void setTheScreenWidth(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }
    /*
       действия выполняемые перед всеми запущенными тестами
    */
    @BeforeSuite
    public static void setUpSuite() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\resources\\webdriver\\chromedriver.exe"); //путь до драйвера открывающего браузер
        driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(-1920, 0)); //смещает окно браузера на левый физический экран
        setTheScreenWidth(browserWindowWidth, browserWindowHeight); // разворачивает окно в нужном разрешении (как задано выше)
        wait = new WebDriverWait(driver, 7); // ожидание элемента после загрузки дом дерева (дом дерево перестало изменяться), нужно явно указывать "wait.until(...)"
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // стандартное ожидание элемента после загрузки дом дерева (дом дерево перестало изменяться)
    }

    /*
       действия выполняемые после каждого класса (очищаю кеш сайта, перезапускаю браузер)
    */
    @AfterClass
    public static void clearAllCacheForSuite() throws IOException {
//        SettingUpCabinetForTesting set = new SettingUpCabinetForTesting();
//        set.clearAllCacheForTests();
    }


}
