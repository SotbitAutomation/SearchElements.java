package TechnicalSupport;

import org.junit.Test;

public class TechnicalSupport extends MethodsForTechnicalSupport {
    @Test //1. Обращение в службу поддержки
    public void ContactingTheSupportService() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTechnicalSupport();
        openingPageOfCreateAppeal();
        fillingFieldsRandomValues();
        sendingAppeal();
    }
    @Test //2. Отправка обращения в службу поддержки с загрузкой файла
    public void ContactingTheSupportServiceWithAddedFile() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTechnicalSupport();
        openingPageOfCreateAppeal();
        fileUpload();
        fillingFieldsRandomValues();
        sendingAppeal();
    }
    @Test //3. Проверка что отправленное обращение отображается в админ части
    public void checkingAppealAtAdmin() {
        //arrange
        ContactingTheSupportService();
        exitFromB2B();
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToAdminPart();
        navigationToSupportRequests();
        checkingThatAppealIsDisplayed();
    }
    @Test //4. Проверка что отправленное обращение c файлом отображается в админ части
    public void checkingAppealWithFileAtAdmin() {
        //arrange
        deletingExcelFilesFromDownloads();
        ContactingTheSupportServiceWithAddedFile();
        exitFromB2B();
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToAdminPart();
        navigationToSupportRequests();
        checkingThatAppealIsDisplayed();
        sortingAppealsById();
        openingLastAppealInAdminPart();
        downloadFile();
        checkingThatFileIsDownload();
    }
    @Test //5. Ответ на обращения клиента из админ части
    public void answerToAppealFromAdmin() {
        //arrange
        checkingAppealAtAdmin();
        sortingAppealsById();
        openingLastAppealInAdminPart();
        //act
        scrollDown();
        answeringToAppeal();
        sendingAnsweringToAppeal();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTechnicalSupport();
        sortingUserAppeals();
        checkingThatTheResponseToTheLastAppealIsDisplayed();
    }
}

