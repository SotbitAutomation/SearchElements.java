package setting_up_cabinet_for_testing;

import base_actions.Retry;
import org.testng.annotations.Test;

public class SettingUpCabinetForTesting extends MethodsForSettingUpCabinetForTesting {

    @Test(retryAnalyzer = Retry.class) //1. Создание дополнительных типов плательщиков и их свойств
    public void creatingAdditionalBuyerTypesAndTheirProperties() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        driver.findElement(logInButtonOnTheAuthorizationTabLocator).click();
        navigationToBuyerTypes();
        creatingBuyerTypes();
        navigationToListOfProperties();
        creatingPropertiesForTheseBuyerTypes();
    }
    @Test(retryAnalyzer = Retry.class) //2. Настроить в кабинете темную шапку
    public void configureTheBlackHeaderInTheCabinet() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        driver.findElement(logInButtonOnTheAuthorizationTabLocator).click();
        navigationToBasicB2BSettings();
        choiceBlackHat();
    }
    @Test(retryAnalyzer = Retry.class) //3. Настроить в кабинете светлую шапку
    public void configureTheWhiteHeaderInTheCabinet() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        driver.findElement(logInButtonOnTheAuthorizationTabLocator).click();
        navigationToBasicB2BSettings();
        choiceWhiteHat();

    }
    @Test(retryAnalyzer = Retry.class) //5. Включить расширенный режим и подтверждения регистраций
    public void enableExtendedModeAndConfirmRegistrations() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        driver.findElement(logInButtonOnTheAuthorizationTabLocator).click();
        navigationToGeneralB2BSettings();
        enableTheExtendedVersionOfWorkingWithCompanies();
        navigationToMeanPageByUrl();
        navigationToTheSiteSettings();
        enableUserRegistrationConfirmation();
        navigationToTheSiteSettings();
        enableCompanyRegistrationConfirmation();
    }
    @Test(retryAnalyzer = Retry.class) //6. Включить обычный режим и подтверждение регистрации пользователя
    public void enableStandardModeAndConfirmRegistrationUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        driver.findElement(logInButtonOnTheAuthorizationTabLocator).click();
        navigationToGeneralB2BSettings();
        disableTheExtendedVersionOfWorkingWithCompanies();
        navigationToTheSiteSettings();
        enableUserRegistrationConfirmation();
        navigationToTheSiteSettings();
        disableCompanyRegistrationConfirmation();
    }
    @Test(retryAnalyzer = Retry.class) //7. Включить обычный режим и без подтверждений
    public void enableStandardModeAndWithoutConfirmRegistrations() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        driver.findElement(logInButtonOnTheAuthorizationTabLocator).click();
        navigationToGeneralB2BSettings();
        disableTheExtendedVersionOfWorkingWithCompanies();
        navigationToTheSiteSettings();
        disableUserRegistrationConfirmation();
        navigationToTheSiteSettings();
        disableCompanyRegistrationConfirmation();
    }
    @Test //8. Удалить весь кеш
    public void clearAllCacheForTests() {
        //act
        clearAllCache();
        driver.quit();
        setUpSuite();
        flagForCloseWarningWindowThisIsTheFirstVisit = true;
        flagForRegionThisIsTheFirstVisit = true;
    }

}
