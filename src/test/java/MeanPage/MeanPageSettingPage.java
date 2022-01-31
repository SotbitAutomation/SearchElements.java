package MeanPage;

import org.junit.Test;
import org.openqa.selenium.By;

public class MeanPageSettingPage extends MethodsForMeanPage {
    @Test //1. Изменения основных данных у юзера
    public void changeTheBasicDataForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheSetting();
        fillingBasicData();
        saveBasicData();
        navigationToTheDesktop();
        navigationToTheSetting();
        checkThatBasicDataHasBeenSaved();
    }
    @Test //2. Смена пароля, авторизация под новым
    public void changeThePasswordAndLogInWithNewOneForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheSetting();
        fillingNewPassword();
        saveBasicData();
        exitFromB2BToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUserWithNewPassword();
        logInToB2B();
        navigationToTheSetting();
        returnOldPassword();
        saveBasicData();
    }

    @Test //3. Проверка отображения политики конфиденциальности
    public void checkTheDisplayOfThePrivacyPolicy() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheSetting();
        openPrivacyPolicy();
    }
    @Test //4. Изменения личных данных у юзера
    public void changeThePersonalDataForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheSetting();
        fillingPersonalData();
        savePersonalData();
        navigationToTheDesktop();
        navigationToTheSetting();
        checkThatPersonalDataHasBeenSaved();
    }
    @Test //5. Изменения основных данных у юзера без соглашения с политикой конфиденциальности
    public void changeTheBasicDataForUserWithoutAgreement() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheSetting();
        driver.findElement(By.xpath("(//*[@class='uniform-checker'])[1]")).click();
        fillingBasicData();
        saveBasicData();
        navigationToTheDesktop();
        navigationToTheSetting();
        checkThatBasicDataDoesNotSaved();
    }

    @Test //6. Изменения личных данных и фотоу юзера
    public void changeThePersonalDataWithPhotoForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheSetting();
        fileUpload();
        savePersonalData();
        navigationToTheDesktop();
        checkingThatPhotoIsDisplayed();
    }



}
