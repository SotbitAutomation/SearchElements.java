package MeanPage;


import BaseActions.Retry;
import org.testng.annotations.Test;

public class MeanPageSettingPage extends MethodsForMeanPage {
    @Test (retryAnalyzer = Retry.class)//1. Изменения основных данных у юзера
    public void changeTheBasicDataForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheSetting();
        fillingBasicData();
        saveBasicData(1);
        navigationToTheDesktop();
        navigationToTheSetting();
        checkThatBasicDataHasBeenSaved();
    }
    @Test (retryAnalyzer = Retry.class)//2. Смена пароля, авторизация под новым
    public void changeThePasswordAndLogInWithNewOneForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheSetting();
        fillingNewPassword();
        saveBasicData(1);
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUserWithNewPassword();
        logInToB2B();
        navigationToTheSetting();
        returnOldPassword();
        saveBasicData(1);
    }

    @Test (retryAnalyzer = Retry.class)//3. Проверка отображения политики конфиденциальности
    public void checkTheDisplayOfThePrivacyPolicy() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheSetting();
        makeSureThatTheCheckboxForThePrivacyPolicyIsMandatory();
        openPrivacyPolicy();
    }
    @Test (retryAnalyzer = Retry.class)//4. Изменения личных данных у юзера
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
    @Test (retryAnalyzer = Retry.class)//5. Изменения основных данных у юзера без соглашения с политикой конфиденциальности
    public void changeTheBasicDataForUserWithoutAgreement() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheSetting();
        uncheckThePrivacyPolicyCheckbox();
        fillingBasicData();
        saveBasicDataWithOutAgreement();
        navigationToTheDesktop();
        navigationToTheSetting();
        checkThatBasicDataDoesNotSaved();
    }

    @Test (retryAnalyzer = Retry.class)//6. Изменения личных данных и фото у юзера
    public void changeThePersonalDataWithPhotoForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheSetting();
        fileDownload();
        savePersonalData();
        navigationToTheDesktop();
        checkingThatPhotoIsDisplayed();
    }



}
