package TechnicalSupport;

import org.junit.Test;

public class TechnicalSupport extends MethodsForTechnicalSupport {
    @Test //1. Обращение в службу поддержки
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
    @Test //2. Отправка обращения в службу поддержки с загрузкой файла
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
    @Test //3. Проверка что отправленное обращение отображается в админ части
    public void checkingRequestAtAdmin() {
        //arrange
        contactingTheSupportService();
        exitFromB2B();
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToAdminPart();
        navigationToSupportRequests();
        checkingThatRequestIsDisplayed();
    }
    @Test //4. Проверка что отправленное обращение c файлом отображается в админ части
    public void checkingRequestWithFileAtAdmin() {
        //arrange
        deletingExcelAndJpgFilesFromDownloads();
        contactingTheSupportServiceWithAddedFile();
        exitFromB2B();
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
    @Test //5. Ответ на обращения клиента из админ части
    public void answerToRequestFromAdmin() {
        //arrange
        checkingRequestAtAdmin();
        sortingRequestsById();
        openingLastRequestInAdminPart();
        //act
        scrollDown();
        answeringToRequest();
        sendingAnsweringToRequest();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTechnicalSupportTab();
        sortingUserRequests();
        checkingThatTheResponseToTheLastRequestIsDisplayed();
    }
    @Test //6. Отправка сообщения в службу поддержки из детальной страницы оформленного заказа
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
        System.out.println(tempValue);
        checkingThatTheLastRequestIsDisplayedOnTheTechnicalSupportPage();
        openingLastRequestOnTheTechnicalSupportPage();
        checkingDisplayingRequestByMessage();
    }
    @Test //7. Обращения пользователя в ТП выводятся только у пользователя отправившего эти обращения
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
        exitFromB2B();
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
    @Test //8. Переход к детальной странице обращения
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
    @Test //9. Вывод сообщений об ошибке при отправке обращения без заполнения обязытельных полей
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

