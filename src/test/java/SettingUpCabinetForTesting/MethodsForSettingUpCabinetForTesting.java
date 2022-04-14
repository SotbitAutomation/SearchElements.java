package SettingUpCabinetForTesting;

import BaseActions.BaseActions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MethodsForSettingUpCabinetForTesting extends BaseActions {
    public String[] arrayWithBuyerTypes={
            "Беларусь юр. лицо", "BelarusLegalPerson", "//option[text()='Юридическое лицо']",
            "Беларусь ИП", "BelarusIP", "//option[text()='Физическое лицо']",
            "Украина юр.лицо", "UkraineLegalPerson", "//option[text()='Юридическое лицо']",
            "Казахстан юр. лицо", "KazakhstanLegalPerson", "//option[text()='Юридическое лицо']",
            "Казахстан ИП", "KazakhstanIP", "//option[text()='Физическое лицо']",
    };
    public String[] arrayWithPropertyNames={
            "Название компании", "COMPANY",
            "Юридический адрес", "COMPANY_ADR",
            "Контактное лицо", "CONTACT_PERSON",
            "E-Mail", "EMAIL",
            "Город", "CITY",
            "Местоположение", "LOCATION",
            "ИНН", "INN",
    };

    public void navigationToBuyerTypes(){
        driver.navigate().to(b2bUrl.replaceAll("b2bcabinet/" , "") + "bitrix/admin/sale_person_type.php?lang=ru");
    }
    public void navigationToListOfProperties(){
        driver.navigate().to(b2bUrl.replaceAll("b2bcabinet/" , "") + "bitrix/admin/sale_order_props.php?lang=ru");
    }

    public void creatingBuyerTypes() {
        System.out.println(arrayWithBuyerTypes.length / 3);
        for (int i = 0; i < arrayWithBuyerTypes.length; i=i+3) {

        String nameOfBuyerType = arrayWithBuyerTypes[i];
        String nameOfBuyerTypeByEnglish = arrayWithBuyerTypes[i+1];
        By accordanceWithLocator = By.xpath(arrayWithBuyerTypes[i+2]);

        driver.findElement(By.xpath("//*[@class='ui-btn ui-btn-primary']")).click();
        driver.findElement(By.xpath("(//*[contains(@class, 'adm-designed-checkbox-label')])[1]")).click();
        driver.findElement(By.xpath("(//*[contains(@class, 'adm-designed-checkbox-label')])[2]")).click();
        driver.findElement(By.xpath("//*[@name='NAME']")).sendKeys(nameOfBuyerType);
        driver.findElement(By.xpath("//*[@name='CODE']")).sendKeys(nameOfBuyerTypeByEnglish);
        driver.findElement(By.xpath("//select")).click();
        driver.findElement(accordanceWithLocator).click();
        driver.findElement(By.xpath("//*[@class='adm-btn-save']")).click();
        }
    }
    int sortValue = 101;
    public void creatingPropertiesForTheseBuyerTypes(){
        for (int i = 0; i < arrayWithBuyerTypes.length; i=i+3) {
            driver.findElement(By.xpath("//*[@name='filter_person_type_id']")).click();
            System.out.println(arrayWithBuyerTypes[i]);
            driver.findElement(By.xpath("//*[@name='filter_person_type_id']//*[contains(text(), '"+ arrayWithBuyerTypes[i] + "')]")).click();
            driver.findElement(By.xpath("//*[@name='set_filter']")).click();
            for (int j = 0; j < arrayWithPropertyNames.length ; j=j+2) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".adm-btn-add")));
                driver.findElement(By.cssSelector(".adm-btn-add")).click();
                driver.findElement(By.xpath("//*[contains(@class, 'popup-menu-item-text')][contains(text(), '"+ arrayWithBuyerTypes[i] + "')]")).click();
                driver.findElement(By.xpath("//*[@name='NAME']")).sendKeys(arrayWithPropertyNames[j]);
                driver.findElement(By.xpath("//*[@name='CODE']")).sendKeys(arrayWithPropertyNames[j+1]);
                driver.findElement(By.xpath("//*[@name='SORT']")).clear();
                driver.findElement(By.xpath("//*[@name='SORT']")).sendKeys(String.valueOf(sortValue));
                sortValue++;
                driver.findElement(By.xpath("//*[contains(text(), 'Входит в профиль')] /following::label[1]")).click();
                scrollToTheElement("//*[contains(text(), 'Обязательное')] /following::label[1]");
                driver.findElement(By.xpath("//*[contains(text(), 'Обязательное')] /following::label[1]")).click();
                if (arrayWithPropertyNames[j].equals("Название компании")){
                    scrollUp();
                    driver.findElement(By.xpath("//*[contains(text(), 'Доступно в фильтре по заказам')] /following::label[1]")).click();
                    scrollToTheElement("//*[contains(text(), 'Использовать как название профиля')] /following::label[1]");
                    driver.findElement(By.xpath("//*[contains(text(), 'Использовать как название профиля')] /following::label[1]")).click();
                }
                if (arrayWithPropertyNames[j].equals("Юридический адрес")){
                    scrollToTheElement("//*[contains(text(), 'Является адресом')] /following::label[1]");
                    driver.findElement(By.xpath("//*[contains(text(), 'Является адресом')] /following::label[1]")).click();
                    scrollToTheElement("//*[contains(text(), 'Обязательное')] /following::label[1]");
                    driver.findElement(By.xpath("//*[contains(text(), 'Обязательное')] /following::label[1]")).click();
                    driver.findElement(By.xpath("//*[contains(text(), 'Много строк')] /following::label[1]")).click();
                }
                if (arrayWithPropertyNames[j].equals("Контактное лицо")){
                    scrollToTheElement("//*[contains(text(), 'Использовать как имя плательщика')] /following::label[1]");
                    driver.findElement(By.xpath("//*[contains(text(), 'Использовать как имя плательщика')] /following::label[1]")).click();
                }
                if (arrayWithPropertyNames[j].equals("E-Mail")){
                    scrollToTheElement("//*[contains(text(), 'Использовать как E-Mail')] /following::label[1]");
                    driver.findElement(By.xpath("//*[contains(text(), 'Использовать как E-Mail')] /following::label[1]")).click();
                }
                if (arrayWithPropertyNames[j].equals("Город")){
                    scrollUp();
                    driver.findElement(By.xpath("//*[contains(text(), 'Доступно в фильтре по заказам')] /following::label[1]")).click();
                    scrollToTheElement("//*[contains(text(), 'Обязательное')] /following::label[1]");
                    driver.findElement(By.xpath("//*[contains(text(), 'Обязательное')] /following::label[1]")).click();
                }
                if (arrayWithPropertyNames[j].equals("Местоположение")){
                    scrollToTheElement("//*[@name='TYPE']");
                    driver.findElement(By.xpath("//*[@name='TYPE']")).click();
                    driver.findElement(By.xpath("//option[@value='LOCATION']")).click();
                    scrollToTheElement("//*[contains(text(), 'Использовать как местоположение')] /following::label[1]");
                    driver.findElement(By.xpath("//*[contains(text(), 'Использовать как местоположение')] /following::label[1]")).click();
                    scrollToTheElement("//*[@name='INPUT_FIELD_LOCATION']");
                    driver.findElement(By.xpath("//*[@name='INPUT_FIELD_LOCATION']")).click();
                    driver.findElement(By.xpath("//*[text()='Город']")).click();
                    driver.findElement(By.xpath("//*[contains(text(), 'Использовать как местоположение для налогов')] /following::label[1]")).click();
                }
                if (arrayWithPropertyNames[j].equals("ИНН") && arrayWithBuyerTypes[i].contains("Беларусь")){
                    scrollUp();
                    driver.findElement(By.xpath("//*[@name='NAME']")).clear();
                    driver.findElement(By.xpath("//*[@name='NAME']")).sendKeys("УНП");
                    driver.findElement(By.xpath("//*[@name='CODE']")).clear();
                    driver.findElement(By.xpath("//*[@name='CODE']")).sendKeys("UNP");
                }
                if (arrayWithPropertyNames[j].equals("ИНН") && arrayWithBuyerTypes[i].contains("Украина")){
                    scrollUp();
                    driver.findElement(By.xpath("//*[@name='NAME']")).clear();
                    driver.findElement(By.xpath("//*[@name='NAME']")).sendKeys("ЕГРПОУ");
                    driver.findElement(By.xpath("//*[@name='CODE']")).clear();
                    driver.findElement(By.xpath("//*[@name='CODE']")).sendKeys("EGRPOU");
                }
                if (arrayWithPropertyNames[j].equals("ИНН") && arrayWithBuyerTypes[i].contains("Казахстан")){
                    scrollUp();
                    driver.findElement(By.xpath("//*[@name='NAME']")).clear();
                    driver.findElement(By.xpath("//*[@name='NAME']")).sendKeys("БСН");
                    driver.findElement(By.xpath("//*[@name='CODE']")).clear();
                    driver.findElement(By.xpath("//*[@name='CODE']")).sendKeys("BSN");
                }
                driver.findElement(By.cssSelector(".adm-btn-save")).click();
            }
        }
    }
    public void choiceBlackHat(){
        scrollToTheElement("//*[@id='HEADER_TYPE']");
        driver.findElement(By.xpath("//*[@id='HEADER_TYPE']")).click();
        driver.findElement(By.xpath("//*[@id='HEADER_TYPE'] /*[contains(text(), 'Темная')]")).click();
        driver.findElement(buttonSaveLocator).click();
        themeColorBlack = true;
    }
    public void choiceWhiteHat(){
        scrollToTheElement("//*[@id='HEADER_TYPE']");
        driver.findElement(By.xpath("//*[@id='HEADER_TYPE']")).click();
        driver.findElement(By.xpath("//*[@id='HEADER_TYPE'] /*[contains(text(), 'Светлая')]")).click();
        driver.findElement(buttonSaveLocator).click();
        themeColorBlack = false;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(fileNameForB2BThemeColor));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.writeObject(this.themeColorBlack);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Темная ли версия шапки " + themeColorBlack);
    }
    public void makeMenuInTheDropDownList(){
        driver.findElement(By.xpath("//*[contains(@title, 'Каталог ')]")).click();
        driver.findElement(By.cssSelector("#CATALOG_SHOW_SECTIONS")).click();
        driver.findElement(By.xpath("//*[@value='MENU']")).click();
        driver.findElement(buttonSaveLocator).click();
    }
    public void makeMenuInTheFilter(){
        driver.findElement(By.xpath("//*[contains(@title, 'Каталог ')]")).click();
        driver.findElement(By.cssSelector("#CATALOG_SHOW_SECTIONS")).click();
        driver.findElement(By.xpath("//*[@value='FILTER']")).click();
        driver.findElement(buttonSaveLocator).click();
    }
    public boolean whetherExtendedModeIsEnabled;
    public void checkingWhetherAdvancedModeIsEnabled(){
        whetherExtendedModeIsEnabled = driver.findElements(By.xpath("//*[@id='EXTENDED_VERSION_COMPANIES'][@checked]")).size() > 0;
    }
    public void enableTheExtendedVersionOfWorkingWithCompanies(){
        checkingWhetherAdvancedModeIsEnabled();
        if (!whetherExtendedModeIsEnabled){
            driver.findElement(By.cssSelector(".adm-designed-checkbox-label")).click();
        }
        driver.findElement(buttonSaveLocator).click();
        changeTheWayToDetailPageCompany("buyer", "companies");
    }
    public void disableTheExtendedVersionOfWorkingWithCompanies(){
        checkingWhetherAdvancedModeIsEnabled();
        if (whetherExtendedModeIsEnabled){
            driver.findElement(By.cssSelector(".adm-designed-checkbox-label")).click();
            implicitWaiting();
        }
        driver.findElement(buttonSaveLocator).click();
        changeTheWayToDetailPageCompany("companies", "buyer");
    }

    public void changeTheWayToDetailPageCompany(String oldAddress, String newAddress){
        navigationToBasicB2BSettings();
        tempValue = driver.findElement(By.xpath("//*[@name='ADDRESS_COMPANY']")).getAttribute("value");
        driver.findElement(By.xpath("//*[@name='ADDRESS_COMPANY']")).clear();
        tempValue = tempValue.replaceAll(oldAddress, newAddress);
        driver.findElement(By.xpath("//*[@name='ADDRESS_COMPANY']")).sendKeys(tempValue);
        driver.findElement(buttonSaveLocator).click();
    }

    public boolean userRegistrationNeedsToBeConfirmed;
    public void checkingWhetherTheUserRegistrationNeedsToBeConfirmed(){
        driver.findElement(By.xpath("//*[contains(@title, 'оптовых ')]")).click();
        userRegistrationNeedsToBeConfirmed = driver.findElements(By.xpath("//*[@id='CONFIRM_REGISTER'][@checked]")).size() > 0;
    }
    public void enableUserRegistrationConfirmation(){
        checkingWhetherTheUserRegistrationNeedsToBeConfirmed();
        if (!userRegistrationNeedsToBeConfirmed){
        clickElement("//*[@for='CONFIRM_REGISTER']");
        }
        driver.findElement(buttonSaveLocator).click();
    }
    public void disableUserRegistrationConfirmation(){
        checkingWhetherTheUserRegistrationNeedsToBeConfirmed();
        if (userRegistrationNeedsToBeConfirmed){
            clickElement("//*[@for='CONFIRM_REGISTER']");
        }
        driver.findElement(buttonSaveLocator).click();
    }
    public boolean companyRegistrationNeedsToBeConfirmed;
    public void checkingWhetherTheCompanyRegistrationNeedsToBeConfirmed(){
        driver.findElement(By.xpath("//*[contains(@title, 'оптовых ')]")).click();
        companyRegistrationNeedsToBeConfirmed = driver.findElements(By.xpath("//*[@id='CONFIRM_BUYER'][@checked]")).size() > 0;
    }
    public void enableCompanyRegistrationConfirmation (){
        checkingWhetherTheCompanyRegistrationNeedsToBeConfirmed();
        if (!companyRegistrationNeedsToBeConfirmed){
            clickElement("//*[@for='CONFIRM_BUYER']");
            implicitWaiting();
        }
        driver.findElement(buttonSaveLocator).click();
    }
    public void disableCompanyRegistrationConfirmation (){
        checkingWhetherTheCompanyRegistrationNeedsToBeConfirmed();
        if (companyRegistrationNeedsToBeConfirmed){
            clickElement("//*[@for='CONFIRM_BUYER']");
            implicitWaiting();
        }
        driver.findElement(buttonSaveLocator).click();
    }
    public void navigationToPageForClearCache(){
        driver.navigate().to(b2bUrl.replaceAll("b2bcabinet/" , "") + "bitrix/admin/cache.php?lang=ru");
    }
    public void clearCache(){
        driver.findElement(By.xpath("//*[contains(@title, 'чистка файлов')]")).click();
        driver.findElement(By.xpath("//*[@class='cache-types'][@value='all']")).click();
        driver.findElement(By.cssSelector("#start_button")).click();
        try {
            waitElementInVisible("//*[@id='wait_window_div']");
        }catch (Exception e){
            System.out.println("Не хватило 10 секунд, подожду еще");
            implicitWaiting();implicitWaiting();implicitWaiting();implicitWaiting();implicitWaiting();implicitWaiting();implicitWaiting();
            waitElementInVisible("//*[@id='wait_window_div']");
        }
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#wait_window_div")));
    }
    public void clearAllCache() {
        //arrange
        countForThemeColor = 0;
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        driver.findElement(logInButtonOnTheAuthorizationTabLocator).click();
        resetCache();navigationToPageForClearCache();
        clearCache();
        navigationToMeanPageByUrl();
        resetCache();
        navigationToCatalogTab();
        resetCache();
    }
}
