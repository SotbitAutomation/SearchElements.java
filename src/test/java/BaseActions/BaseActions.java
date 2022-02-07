package BaseActions;
//***********test

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.security.SecureRandom;
import java.util.Collection;


public class BaseActions extends CustomizingForYourself {

    public JavascriptExecutor jse = (JavascriptExecutor)driver;
    public File dir = new File(System.getProperty("user.home") + "/Downloads/");
    public Actions actions = new Actions(driver);

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_______________";
    static SecureRandom rnd = new SecureRandom();

    public String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    static final String NUMBERS = "0123456789";
    public String randomNumber(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(NUMBERS.charAt(rnd.nextInt(NUMBERS.length())));
        return sb.toString();
    }
    static final String NUMBERSWithoutZero = "123456789";
    public String randomNumberWithoutZero(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(NUMBERSWithoutZero.charAt(rnd.nextInt(NUMBERSWithoutZero.length())));
        return sb.toString();
    }

    public int randomNumberUpToTwelve = 1 + (int) (Math.random() * 12);
    public int randomNumberUpToFife = 1 + (int) (Math.random() * 5);
    public int randomNumberUpToTwo = 1 + (int) (Math.random() * 2);
    public int randomNumberUpToNine = 1 + (int) (Math.random() * 9);
    public int tempRandomNumber;

    public void standardConfirmationOfTheActionOnThePage() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        explicitWaiting();
    }

    By registerButtonLocator = By.xpath("//*[text()='Регистрация']");
    public By registrationTabLocator = By.xpath("//*[text()='Создать учетную запись']");
    public By loginInputLocator = By.xpath("//*[@placeholder='Логин']");
    public By passwordInputInAuthorizationTanLocator = By.xpath("//*[@placeholder='Пароль']");
    public By logInButtonOnTheAuthorizationTabLocator = By.xpath("//*[text()='Войти '][contains(@class, 'btn')]");
    By organizationsTabLocator = By.xpath("//*[contains(@class, 'nav')] //*[contains(text(), 'Организации')]");
    By employeesTabLocator = By.cssSelector(".icon-person");
    public By catalogTabLocator = By.xpath("//*[contains(@href, '/orders/blank_zakaza/')][@title='Каталог'] /span");
    By cartIconLocator = By.xpath("//*[contains(@href, 'orders/make/')][@class='nav-link']");
    public By firstHamburgerMenuOnTheOrganizationTabLocator = By.xpath("(//*[@class='main-grid-cell main-grid-cell-action'][not(ancestor-or-self::*[@hidden = 'true'])])[1]");
    public By ordersTabInTheOrganization = By.xpath("//a[contains(text(), 'Заказы')]");
    public By firstNameOfOrganizationOnTheOrganizationTabOrEmployeeTab = By.xpath("((//*[@class='main-grid-row main-grid-row-body'])[1] //*[@class='main-grid-cell-content'])[3]");
    public By sotbitTabLocator = By.cssSelector("#global_menu_sotbit");



    public String name = "Name Имя" + randomString(8);
    public String lastName = "LastName Фамилия" + randomString(10);
    public String phone = "+7" + randomNumber(+10);
    public String mobilePhone = "+7495" + randomNumber(+7);
    public String password = randomString(20);
    public String passwordUser;
    public String passwordEmployee;
    public String nameCompany = "NameCompany ИмяКомпании" + randomString(10);
    public String CompanyNameToJoinEmployees;
    public String iNNManual = randomNumber(14);
    public String theSameInnManual;
    public String theSameEmail;
    public String iNN = randomNumber(4);
    public String appeal = "Sir Обращение" + randomString(4);
    public String address = "г. Сызрань ул. " + randomString(20);
    public String email = "test_EMail" + randomString(12) + "@mail.ru";
    public String emailUser;
    public String emailEmployee;
    public String randomData = "Рамдомные символы" + randomString(20) + randomNumberUpToFife;
    public String index = randomNumber(+6);
    public String tempValue;
    public String tempValue1;
    public String tempValue2;
    public String tempValue2WithSell;
    public String tempValue3;
    public String tempValue4;
    public String tempValue5;
    public String tempValue6;

    public String tempValueForEmail;
    public String tempValueForPassword;
    public int tempInt3 = 0;
    public int tempIntValue;
    public int tempIntValue2;
    public String tempValueForNumbers;
    public boolean flag;
    public static boolean flagForLocation = false;
    public boolean doNeedToConfirmRegistration = false;
    public boolean doNeedToConfirmRegistrationOrganization = false;
    public boolean versionsOfWorkingWithOrganizationsExtended = true;
    public String fileNameForConfirmRegistrationUser = "doNeedToConfirmRegistrationUser";  //Имя создаваемого файла в папке с проектом
    public String fileNameForConfirmRegistrationOrganization = "doNeedToConfirmRegistrationOrganization";  //Имя создаваемого файла в папке с проектом
    public String fileNameForUsersEmailsAndPasswords = "fileNameForUsersEmailsAndPasswords";  //Имя создаваемого файла в папке с проектом
    public String fileNameForVersionOfWorkingWithOrganization = "fileNameForVersionOfWorkingWithOrganization";  //Имя создаваемого файла в папке с проектом
    public ObjectOutputStream outputStream = null;
    public ObjectInputStream inputStream = null;
    public int count;
    public int countIP;
    public int counterOfExistingLocators = 0;
    public int numberOfOffersPerPage = 0;
    public By buttonToGoToAdminPartLocator =By.cssSelector("#bx-panel-admin-tab");
    public By checkboxShowEmployeesOfAllOrganizations = By.xpath("//*[@class='form-check'] //*[@type = 'checkbox']");
    public By buttonAddNewEmployee = By.cssSelector("#staff-list__add-staff");
    public By INNFromListLocator = By.xpath("(//*[@id='list'])[1]");

    public By buttonMakeOrderInTheCartLocator = By.xpath("//button[contains(text(), 'Оформить заказ')]");
    public By buttonToSaveTheComponentSettingsForTheCatalog = By.cssSelector("#bx-comp-params-save-button");
    public By buttonSaveLocator = By.xpath("//*[@name='save']");
    public By buttonForMakeOrderLocatorOnTheCheckoutPage = By.cssSelector("#ORDER_CONFIRM_BUTTON");



    public void navigationToAuthorizationTab() {
        driver.navigate().to(b2bUrl);
        try {
            Assert.assertTrue(driver.findElement(By.cssSelector(".login-form")).isDisplayed());
        }catch (Exception e){
            exitFromB2B();
            driver.navigate().to(b2bUrl);
            Assert.assertTrue(driver.findElement(By.cssSelector(".login-form")).isDisplayed());
        }
    }
    public void doubleClick(String xpath){
        Action doubleClick = actions.doubleClick(driver.findElement(By.xpath(xpath))).build();
        doubleClick.perform();
        waitingMilliSecond();
    }

    public void navigationToRegistrationTab() {
        navigationToAuthorizationTab();
        driver.findElement(registerButtonLocator).click();
        Assert.assertEquals("Вкладка Создать учетную запись не открылась"
                , "Создать учетную запись", driver.findElement(registrationTabLocator).getText());
    }
    public void navigationToAdminPartFromMeanPage(){
        driver.findElement(buttonToGoToAdminPartLocator).click();
        try {
            driver.findElement(By.cssSelector("#global_menu_content")).click();
        }catch (Exception e){
            System.out.println("УБЕРИ всплывающее окно 'Битрикс24'!!  а то я не могу пройти дальше из-за него!");
            driver.findElement(By.cssSelector("//*[contains(@class, 'network-dontshow-checkbox')]")).click();
            driver.findElement(By.cssSelector("#save")).click();
        }
    }

    public void fillingFieldsOnTheLogInTabLikeAdmin() {
        driver.findElement(loginInputLocator).sendKeys(adminLogin);
        Assert.assertEquals("Емаил не отображается"
                , adminLogin, driver.findElement(loginInputLocator).getAttribute("value"));
        driver.findElement(passwordInputInAuthorizationTanLocator).sendKeys(adminPassword);
        Assert.assertEquals("Пароль не отображается", adminPassword,
                driver.findElement(passwordInputInAuthorizationTanLocator).getAttribute("value"));

//        driver.findElement(loginInputLocator).sendKeys("e.osmolovskya@sotbit.ru");
//        Assert.assertEquals("Емаил не отображается"
//                , "e.osmolovskya@sotbit.ru", driver.findElement(loginInputLocator).getAttribute("value"));
//        driver.findElement(passwordInputInAuthorizationTanLocator).sendKeys("25nNnhpC6UzUuen");
//        Assert.assertEquals("Пароль не отображается", "25nNnhpC6UzUuen",
//                driver.findElement(passwordInputInAuthorizationTanLocator).getAttribute("value"));

//                driver.findElement(loginInputLocator).sendKeys("keeper");
//        Assert.assertEquals("Емаил не отображается"
//                , "keeper", driver.findElement(loginInputLocator).getAttribute("value"));
//        driver.findElement(passwordInputInAuthorizationTanLocator).sendKeys("TK2iNeOL%H%*lBWE");
//        Assert.assertEquals("Пароль не отображается", "TK2iNeOL%H%*lBWE",
//                driver.findElement(passwordInputInAuthorizationTanLocator).getAttribute("value"));


    }

    public void fillingFieldsOnTheLogInTabLikeUser() {
        readingUserData();
        driver.findElement(loginInputLocator).sendKeys(emailUser);
        Assert.assertEquals("Емаил не отображается"
                , emailUser, driver.findElement(loginInputLocator).getAttribute("value"));
        driver.findElement(passwordInputInAuthorizationTanLocator).sendKeys(passwordUser);
        Assert.assertEquals("Пароль не отображается", passwordUser,
                driver.findElement(passwordInputInAuthorizationTanLocator).getAttribute("value"));
        System.out.println(emailUser);
        System.out.println(passwordUser);
    }
    public void fillingFieldsOnTheLogInTab(String email, String password) {
        driver.findElement(loginInputLocator).sendKeys(email);
        Assert.assertEquals("Емаил не отображается"
                , email, driver.findElement(loginInputLocator).getAttribute("value"));
        driver.findElement(passwordInputInAuthorizationTanLocator).sendKeys(password);
        Assert.assertEquals("Пароль не отображается", password,
                driver.findElement(passwordInputInAuthorizationTanLocator).getAttribute("value"));
    }


    public void fillingFieldsOnTheLogInTabLikeEmployee() {
        readingUserData();
        driver.findElement(loginInputLocator).sendKeys(emailEmployee);
        Assert.assertEquals("Емаил не отображается"
                , emailEmployee, driver.findElement(loginInputLocator).getAttribute("value"));
        driver.findElement(passwordInputInAuthorizationTanLocator).sendKeys(passwordEmployee);
        Assert.assertEquals("Пароль не отображается", passwordEmployee,
                driver.findElement(passwordInputInAuthorizationTanLocator).getAttribute("value"));
        System.out.println(emailEmployee);
        System.out.println(passwordEmployee);
    }

    public void fillingFieldsOnTheLogInTabLikeEmployeeJustNowRegistered() {
        driver.findElement(loginInputLocator).sendKeys(email);
        Assert.assertEquals("Емаил не отображается"
                , email, driver.findElement(loginInputLocator).getAttribute("value"));
        driver.findElement(passwordInputInAuthorizationTanLocator).sendKeys(password);
        Assert.assertEquals("Пароль не отображается", password,
                driver.findElement(passwordInputInAuthorizationTanLocator).getAttribute("value"));
    }

    public void logInToB2B() {
        driver.findElement(logInButtonOnTheAuthorizationTabLocator).click();
        try {
            Assert.assertTrue(driver.findElement(By.cssSelector(".swiper-wrapper")).isDisplayed());
        }catch (Exception e){
            navigationToMeanPageByUrl();
            Assert.assertTrue(driver.findElement(By.cssSelector(".swiper-wrapper")).isDisplayed());
        }
    }

    public void exitFromB2B() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(exitButtonInb2bCabinetLocator));
        driver.findElement(exitButtonInb2bCabinetLocator).click();
    }

    public void navigationToOrganizationTab() {
        driver.findElement(organizationsTabLocator).click();
        try {
            Assert.assertTrue(driver.findElement(By.cssSelector("#PERSONAL_PROFILE_LIST")).isDisplayed());
        }catch (Exception e){
            Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Список организаций пуст']")).isDisplayed());
        }
    }
    public void navigationToDeliveryWayByUrl(){
        driver.navigate().to(b2bUrl.replaceAll("b2bcabinet/" , "") + "bitrix/admin/sale_delivery_service_list.php");
    }
    public void navigationToEmployeesTab() {
        driver.findElement(employeesTabLocator).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("#STAFF_LIST")).isDisplayed());
    }
    public void navigationToMeanPageByUrl(){
        driver.navigate().to(b2bUrl);
    }
    public void checkingThatCartIconHavePictureOfThePresenceOfOneProductInTheBasket(int numberOfProductsInTheBasket){
        tempValueForNumbers = String.valueOf(numberOfProductsInTheBasket);
        flag = false;
        while (flag == false){
            if (driver.findElements(By.cssSelector(".b2b-notification__content")).size() > 0){
                driver.findElement(By.xpath("//i[@class='icon-cross']")).click();
            }else flag = true;
        }
        Assert.assertTrue("Иконка наличия товаров в корзине не показывает 'number'"
                , driver.findElement(By.cssSelector(".badge")).getText().equals(tempValueForNumbers));
    }
    public void resetCache(){
        explicitWaiting();
        driver.findElement(By.cssSelector(".bx-panel-clear-cache-icon")).click();
        explicitWaiting();explicitWaiting();
    }
    public void navigationToCatalogTab() {
        driver.findElement(catalogTabLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".catalog")));
        Assert.assertTrue(driver.findElement(By.cssSelector(".catalog")).isDisplayed());
        openingAllOffers();
    }
    public void openingAllOffers(){
        if (areThereAnyOffers == true){
            if (driver.findElements(By.cssSelector(".offers-info__label")).size() > 0){
                try{
                    for (int i = 1; i <= driver.findElements(By.xpath("//*[contains(text(), 'Предложения:')]")).size(); i++) {
                        driver.findElement(By.xpath("(//*[contains(text(), 'Предложения:')])[" + i + "]")).click();
                    }
                }catch (Exception e) {
                    System.out.println("Нет офферов");
                }
            }
        }
    }

    public void navigationToCart() {
        scrollToTheElement("//*[contains(@href, 'orders/make/')][@class='nav-link']");
        driver.findElement(cartIconLocator).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".basket-page")).isDisplayed());

//        try {
//            driver.findElement(cartIconLocator).click();
//            Assert.assertTrue(driver.findElement(By.cssSelector(".basket-page")).isDisplayed());
//        } catch (Exception e) {
//            driver.findElement(By.cssSelector(".icon-cross")).click();
//            driver.findElement(cartIconLocator).click();
//            Assert.assertTrue(driver.findElement(By.cssSelector(".basket-page")).isDisplayed());
//        } catch (Error e) {
//            System.out.println("я не нашел иконку корзины");
//        }
    }
    public void navigationToMyOrdersPage(){
        driver.findElement(By.xpath("//*[@title='Мои заказы']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".order_wrapper")).isDisplayed());
    }
    public void navigationToTechnicalSupportTab(){
        scrollToTheElement("//*[@class='icon-clippy']");
        driver.findElement(By.xpath("//*[@class='icon-clippy']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class,'page-title')]/*[text()='Список обращений']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".support-list__title")).isDisplayed());
    }

    public void explicitWaiting() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void waitingMilliSecond() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void scrollDown(){
        jse.executeScript("window.scrollBy(0,250)", "");
    }
    public void scrollUp(){
        ((JavascriptExecutor)driver).executeScript("scroll(0,400)");
    }
    public void scrollToTheElement(String xpath){
        WebElement element = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        explicitWaiting();
    }

    public void fileDownload(){
        By fileInput = By.cssSelector("input[type=file]");
        String filePath = System.getProperty("user.dir")  + "\\resources\\palms.jpg";
        driver.findElement(fileInput).sendKeys(filePath);
    }
    public void downloadingALargeFile(){
        By fileInput = By.cssSelector("input[type=file]");
        String filePath = System.getProperty("user.dir")  + "\\resources\\largeFile.jpg";
        driver.findElement(fileInput).sendKeys(filePath);
    }



    public void confirmRegistrationOfOrganizationFromAdmin() {
        System.out.println(nameCompany);
        determineWhetherRegistrationOrganizationNeedsToBeConfirmed();
        if (doNeedToConfirmRegistrationOrganization == true){
            navigationToAdminPartFromMeanPage();
            driver.findElement(By.cssSelector("#global_menu_sotbit")).click();
            try {
                driver.findElement(By.xpath("//*[text()='Подтверждение организации (расширенный режим)']")).click();
            }catch (Exception e ){
                driver.findElement(By.xpath("//*[text()='Оптовые покупатели']")).click();
                driver.findElement(By.xpath("//*[contains(text(),'Подтверждение организации')]")).click();
            }

//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(((//*[@class='adm-list-table-row'])[1] //*[@class='adm-list-table-cell'])[3])[contains(text(), '" + nameCompany + "')]")));
//            Assert.assertTrue(driver.findElement
//                    (By.xpath("(((//*[@class='adm-list-table-row'])[1] //*[@class='adm-list-table-cell'])[3])[contains(text(), '" + nameCompany + "')]"))
//                    .isDisplayed());
            explicitWaiting();
            Assert.assertTrue(driver.findElement(By.xpath("((//*[@class='adm-list-table-row'])[1] //*[@class='adm-list-table-cell'])[3]")).getText().contains(nameCompany));
            driver.findElement(By.xpath("//*[@class='adm-designed-checkbox-label adm-checkbox']")).click();
            driver.findElement(By.cssSelector("#digitalwand_admin_helper_sotbit_auth_company_confirm_action")).click();
            driver.findElement(By.xpath("//*[text()='Одобрить']")).click();
            driver.findElement(By.xpath("//*[@value='Применить']")).click();
            try {
                driver.findElement(By.cssSelector(".adm-header-btn-site")).click();
            }catch (Exception e){
                driver.navigate().to(b2bUrl);
            }
        }
    }
    public void confirmRegistrationOfOrganizationInB2bFromTheUser(){
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        confirmRegistrationOfOrganizationFromAdmin();
        exitFromB2B();
    }

    public void tryConfirmRegistrationOfOrganizationInB2bFromTheUser() {
        determineWhetherRegistrationOrganizationNeedsToBeConfirmed();
        if (doNeedToConfirmRegistrationOrganization == true){
            confirmRegistrationOfOrganizationInB2bFromTheUser();
        }
    }

    public void sortingOrganizationByDecrease(){
        driver.findElement(By.xpath("(//span[contains(text(),'Код')])[1]")).click();
        try {
            driver.findElement(By.cssSelector(".main-grid-control-sort.main-grid-control-sort-asc")).click();
        } catch (Exception e) {}
    }
    public void checkingThatOrganizationsIsDisplayed(){
        Assert.assertTrue(driver.findElement(By.cssSelector("#PERSONAL_PROFILE_LIST")).isDisplayed());
    }
    public void checkingThatOrganizationIsConfirmed(){
        navigationToOrganizationTab();
        if (doNeedToConfirmRegistrationOrganization == true){
            sortingOrganizationByDecrease();
            waitingMilliSecond();
            try {
                wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("(//*[@class='main-grid-row main-grid-row-body'])[1] /*[7]"), "Одобрена"));
            }catch (Exception e){
                driver.findElement(By.xpath("//*[@class='main-ui-pagination-arrow']")).click();
                sortingOrganizationByDecrease();
                waitingMilliSecond();
                wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("(//*[@class='main-grid-row main-grid-row-body'])[1] /*[7]"), "Одобрена"));
            }
            Assert.assertEquals("Одобрена", driver.findElement(By.xpath("(//*[@class='main-grid-row main-grid-row-body'])[1] /*[7]")).getText());
        }else {
            //Assert.assertTrue(driver.findElement(By.xpath("//*[text()='" + nameCompany + "']")).isDisplayed());
        }
    }

    public  void deletingExcelAndJpgFilesFromDownloads(){
        String [] str = {"xlsx"};
        Collection<File> files = FileUtils.listFiles(dir,str,true);
        for(File f:files){
            f.delete();
        }
        String [] image = {"jpg"};
        Collection<File> filesImage = FileUtils.listFiles(dir,image,true);
        for(File f:filesImage){
            f.delete();
        }
    }
    public boolean isFileDownloaded_Ext(String dirPath, String ext){
        boolean flag=false;
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            flag = false;
        }
//        System.out.println(files.length-1);
//        for (int i = 1; i < files.length-1; i++) {
        if(files[0].getName().contains(ext)) {
            flag=true;
            //}
        }
        return flag;
    }


    public void determineWhetherRegistrationNeedsToBeConfirmed(){
        try {
            inputStream = new ObjectInputStream(new FileInputStream(fileNameForConfirmRegistrationUser)); //Имя файла с массивом
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            doNeedToConfirmRegistration = (boolean) inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Нужно подтверждать регистрацию? - " + doNeedToConfirmRegistration);
    }
    public void determineWhetherVersionsOfWorkingWithOrganization(){
        try {
            inputStream = new ObjectInputStream(new FileInputStream(fileNameForVersionOfWorkingWithOrganization)); //Имя файла с массивом
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            versionsOfWorkingWithOrganizationsExtended = (boolean) inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Версия работы с компаниями расширенная - " + versionsOfWorkingWithOrganizationsExtended);
    }
    public void determineWhetherRegistrationOrganizationNeedsToBeConfirmed(){
        try {
            inputStream = new ObjectInputStream(new FileInputStream(fileNameForConfirmRegistrationOrganization)); //Имя файла с массивом
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            doNeedToConfirmRegistrationOrganization = (boolean) inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Нужно подтверждать регистрацию организации? - " + doNeedToConfirmRegistrationOrganization);
    }
    public void readingUserData(){
        try {
            inputStream = new ObjectInputStream(new FileInputStream(fileNameForUsersEmailsAndPasswords)); //Имя файла с массивом
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            emailUser = (String) inputStream.readObject();
            passwordUser = (String) inputStream.readObject();
            emailEmployee = (String) inputStream.readObject();
            passwordEmployee = (String) inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String replacingSomeSymbols (String stringForReplace){
        stringForReplace = stringForReplace.replaceAll("руб.", "");
        return stringForReplace = stringForReplace.replaceAll("[\\$, ,₽,' ',№]", "");
    }
    public String replacingBracketsAndNumbers (String stringForReplace){
        stringForReplace = stringForReplace.replaceAll("[1-9]", "");
        return stringForReplace = stringForReplace.replaceAll("\\p{P}","");
    }
    public void navigationToTheEventTableFromAdminMeanPage(){
        driver.findElement(buttonToGoToAdminPartLocator).click();
        driver.findElement(By.xpath("//*[contains(@class,'adm-main-menu')][text()='Настройки']")).click();
        try {
            driver.findElement(By.xpath("//*[contains(@class,'adm-submenu')][text()='Таблицы']")).click();
        }catch (Exception e){
            driver.findElement(By.xpath("//*[contains(@class,'adm-submenu')][text()='Производительность']")).click();
            driver.findElement(By.xpath("//*[contains(@class,'adm-submenu')][text()='Таблицы']")).click();
        }
        driver.findElement(By.xpath("//*[text()='b_event']")).click();
        sortingEventsByDecrease();
    }
    public void sortingEventsByDecrease(){
        try {
            driver.findElement(By.cssSelector(".adm-list-table-cell-sort-up")).click(); //Сортировка по убыванию событий
        }catch (Exception e){}
    }
    public void determineWhetherLocationWhileRegistration(){
        try {
            inputStream = new ObjectInputStream(new FileInputStream("Location")); //Имя файла с массивом
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            flagForLocation = (boolean) inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Есть ли выбор местоположения - " + flagForLocation);
    }
    public void enableEditMode(){
        driver.findElement(By.cssSelector("#bx-panel-toggle-indicator")).click();
        explicitWaiting();
    }
    public void rememberingLastOrder(){
        tempValue = driver.findElement(By.xpath("//*[@class='main-grid-row main-grid-row-body'] //*[contains(@class, 'main-grid-cell-left')]")).getText();
    }
}
