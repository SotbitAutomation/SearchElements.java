package Documents;


import org.junit.Test;


public class Documents extends MethodsForDocuments {
    @Test //1. Добавление существующего документа пользователю
    public void addExistingDocumentToUser() {
        //arrange
        deletingExcelAndJpgFilesFromDownloads();
        registr.registrationIPWithManualEntryINN();
        registr.tryConfirmRegistration();
        //act
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        addingExistingDocumentToJustCreatedUser();
        exitFromB2B();
        navigationToAuthorizationTab();
        registr.fillingFieldsOnTheLogInTab();
        logInToB2B();
        navigationToActsOfDocuments();
        checkingThatDocumentIsDisplayed();
        downloadDocument();
    }

    @Test //2. Добавление существующего  документа пользователю c привязкой организации
    public void addExistingDocumentWithOrganizationToUser() {
        //arrange
        deletingExcelAndJpgFilesFromDownloads();
        registr.registrationIPWithManualEntryINN();
        registr.tryConfirmRegistration();
        //act
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        addingExistingDocumentWithOrganizationToJustCreatedUser();
        exitFromB2B();
        navigationToAuthorizationTab();
        registr.fillingFieldsOnTheLogInTab();
        logInToB2B();
        navigationToActsOfDocuments();
        checkingThatDocumentIsDisplayed();
        downloadDocument();
        navigationToDetailInformationOfOrganizationFromDocument();
    }

    @Test //3. Добавление документа пользователю
    public void addDocumentToUser() {
        //arrange
        deletingExcelAndJpgFilesFromDownloads();
        registr.registrationIPWithManualEntryINN();
        registr.tryConfirmRegistration();
        //act
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        addingDocumentToJustCreatedUser();
        exitFromB2B();
        navigationToAuthorizationTab();
        registr.fillingFieldsOnTheLogInTab();
        logInToB2B();
        navigationToActsOfDocuments();
        checkingThatDocumentIsDisplayed();
        downloadDocument();
    }

    @Test //4. Добавление документа пользователю c привязкой организации
    public void addDocumentWithOrganizationToUser() {
        //arrange
        deletingExcelAndJpgFilesFromDownloads();
        registr.registrationIPWithManualEntryINN();
        registr.tryConfirmRegistration();
        //act
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        System.out.println(registr.theSameEmail);
        addingDocumentWithOrganizationToJustCreatedUser(registr.theSameInnManual, registr.theSameEmail);
        driver.findElement(buttonForSaveDocumentForUserLocator).click();
        exitFromB2B();
        navigationToAuthorizationTab();
        registr.fillingFieldsOnTheLogInTab();
        logInToB2B();
        navigationToActsOfDocuments();
        checkingThatDocumentIsDisplayed();
        downloadDocument();
        navigationToDetailInformationOfOrganizationFromDocument();
    }
    @Test //5. Отображение документа привязонного к заказу
    public void displayingTheDocumentLinkedToTheOrder() {
        //arrange
        deletingExcelAndJpgFilesFromDownloads();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        ordering.deletingProductsFromTheCart();
        navigationToCatalogTab();
        ordering.changeTheQuantityOfRandomProduct();
        ordering.checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        navigationToCart();
        makingAnOrderAndStoringNumberOfOrderForBindingADocument();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        addingDocumentForUserWithNumberOfOrder(emailUser, makingOrder.numberOfOrder);
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToMyOrdersPage();
        checkingThatLastOrderHaveDocument();
        downloadDocument();
    }
    @Test //6. Переход на детальную страницу заказа из раздела "Документы" с помощью привязанного к заказу документа
    public void goToTheDetailedOrderPageFromTheDocumentsSectionUsingTheDocumentLinkedToTheOrder() {
        //arrange
        deletingExcelAndJpgFilesFromDownloads();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        ordering.deletingProductsFromTheCart();
        navigationToCatalogTab();
        ordering.changeTheQuantityOfRandomProduct();
        ordering.checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        navigationToCart();
        makingAnOrderAndStoringNumberOfOrderForBindingADocument();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        resetCache();
        addingDocumentForUserWithNumberOfOrder(emailUser, makingOrder.numberOfOrder);
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToActsOfDocuments();
        navigationToTheLastOrderFromDocumentsPage();
    }
    @Test //7. Смена порядка вывода инфоблоков раздела "Документы"
    public void changingTheOrderOutputOfInformationBlocksInTheDocumentsSection() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        searchSequenceNumberOfActs();
        openDocumentsTabInAdminPanel();
        openActSettings();
        changeTheSequenceNumberForTheActsSectionToTheOpposite();
        navigationToMeanPageByUrl();
        resetCache();
        checkingThatTheSequenceNumberOfTheActsHasChanged();
    }
}
