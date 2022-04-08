package TechnicalSupport;

import BaseActions.Retry;
import org.testng.annotations.Test;

public class TechnicalSupport extends MethodsForTechnicalSupport {
    @Test(retryAnalyzer = Retry.class) //1. Обращение в службу поддержки
    public void contactingTheSupportService() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTechnicalSupportTab();
        openingPageOfCreateRequest();
        fillingTheHeadForTechnicalSupport();
        fillingFieldsRandomValuesForTechnicalSupport();
        sendingRequest();
        checkingThatRequestIsDisplayedForTheUser();
    }
    @Test(retryAnalyzer = Retry.class) //2. Отправка обращения в службу поддержки с загрузкой файла
    public void contactingTheSupportServiceWithAddedFile() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTechnicalSupportTab();
        openingPageOfCreateRequest();
        fileDownload();
        fillingTheHeadForTechnicalSupport();
        fillingFieldsRandomValuesForTechnicalSupport();
        sendingRequest();
        checkingThatRequestIsDisplayedForTheUser();
    }
    @Test(retryAnalyzer = Retry.class) //3. Проверка что отправленное обращение отображается в админ части
    public void checkingRequestAtAdmin() {
        //arrange
        contactingTheSupportService();
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToAdminPart();
        navigationToSupportRequests();
        checkingThatRequestIsDisplayed();
    }
    @Test(retryAnalyzer = Retry.class) //4. Проверка что отправленное обращение c файлом отображается в админ части
    public void checkingRequestWithFileAtAdmin() {
        //arrange
        deletingExcelAndJpgFilesFromDownloads();
        contactingTheSupportServiceWithAddedFile();
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToAdminPart();
        navigationToSupportRequests();
        checkingThatRequestIsDisplayed();
        sortingRequestsById();
        openingLastRequestInAdminPart();
        downloadFile();
        checkingThatFileIsDownload();
        deletingExcelAndJpgFilesFromDownloads();
    }
    @Test(retryAnalyzer = Retry.class) //5. Ответ на обращения клиента из админ части
    public void answerToRequestFromAdmin() {
        //arrange
        checkingRequestAtAdmin();
        sortingRequestsById();
        openingLastRequestInAdminPart();
        //act
        answeringToRequest();
        sendingAnsweringToRequest();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTechnicalSupportTab();
        sortingUserRequests();
        checkingThatTheResponseToTheLastRequestIsDisplayed();
    }
    @Test(retryAnalyzer = Retry.class) //6. Отправка сообщения в службу поддержки из детальной страницы оформленного заказа
    public void sendingMessageToTheSupportServiceFromTheDetailedPageOfTheCompletedOrder() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToMyOrdersPage();
        rememberingLastOrder();
        orderHistory.openingLastOrder();
        orderHistory.openSupportServiceTabInOrderPage();
        fillingFieldsRandomValuesForTechnicalSupport();
        sendingRequest();
        checkingThatLastRequestIsDisplayedOnTheDetailOrderPageInSupportTab();
        navigationToMeanPageByUrl();
        navigationToTechnicalSupportTab();
        checkingThatTheLastRequestIsDisplayedOnTheTechnicalSupportPage();
        openingLastRequestOnTheTechnicalSupportPage();
        checkingDisplayingRequestByMessage();
    }
    @Test(retryAnalyzer = Retry.class) //7. Обращения пользователя в ТП выводятся только у пользователя отправившего эти обращения
    public void theUserSRequestsToTheTPAreDisplayedOnlyFromTheUserWhoSentTheseRequests() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTechnicalSupportTab();
        openingPageOfCreateRequest();
        fillingTheHeadForTechnicalSupport();
        fillingFieldsRandomValuesForTechnicalSupport();
        sendingRequest();
        checkingThatRequestIsDisplayedForTheUser();
        memorizingNumberOfLastRequest();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeEmployee();
        logInToB2B();
        navigationToTechnicalSupportTab();
        openingPageOfCreateRequest();
        fillingTheHeadForTechnicalSupport();
        fillingFieldsRandomValuesForTechnicalSupport();
        sendingRequest();
        checkingThatRequestIsDisplayedForTheUser();
        checkThatTheSecondUserDoesNotHaveTheFirstRequest();
    }
    @Test(retryAnalyzer = Retry.class) //8. Переход к детальной странице обращения
    public void goToTheDetailedPageOfTheRequest () {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTechnicalSupportTab();
        openingPageOfCreateRequest();
        fillingTheHeadForTechnicalSupport();
        fillingFieldsRandomValuesForTechnicalSupport();
        sendingRequest();
        checkingThatRequestIsDisplayedForTheUser();
        searchForNumberOfTheColumnResponsibleForTheID();
        doubleClick("((//*[@class='main-grid-row main-grid-row-body'])[1] //*[@class='main-grid-cell main-grid-cell-left'])[" + count + "]");
        checkingDisplayingRequestByMessage();
        checkingThatURLContainsIDOfRequest();
        navigationToTechnicalSupportTab();
        openingLastRequestOnTheTechnicalSupportPage();
        checkingDisplayingRequestByMessage();
        checkingThatURLContainsIDOfRequest();
    }
    @Test(retryAnalyzer = Retry.class) //9. Вывод сообщений об ошибке при отправке обращения без заполнения обязытельных полей
    public void displayingErrorMessagesWhenSendingARequestWithoutFillingTheRequiredFields() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTechnicalSupportTab();
        openingPageOfCreateRequest();
        checkingThatTheEnteringMessagesFieldIsRequired();
        checkingThatAnErrorMessageIsDisplayedIfTheHeaderIsEmpty();
        checkingThatAnErrorMessageIsDisplayedIfTheTheDownloadedFileIsTooLarge();
        fillingInOnlyRequiredFields();
        sendingRequest();
        checkingThatRequestIsDisplayedForTheUser();
    }
}

