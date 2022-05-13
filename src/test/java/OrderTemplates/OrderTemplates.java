package OrderTemplates;

import BaseActions.Retry;
import org.testng.annotations.Test;

public class OrderTemplates extends MethodsForOrderTemplates {

    @Test(retryAnalyzer = Retry.class) //1.  Создание шаблона заказа
    public void creatingAnOrderTemplate() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        //act
        navigationToOrderTemplates();
        uploadingExcelCatalogForOrderTemplatesWithAcceptIt("blankForOrderTemplates.xlsx");
        setNameForOrderTemplate();
        createOrderTemplate();
        navigationToOrderTemplates();
        checkingThatCreatedOrderTemplateIsDisplayed();
    }

    @Test(retryAnalyzer = Retry.class) //2.  Оформление заказа из шаблона
    public void makingOrderFromTemplate() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        //act
        navigationToOrderTemplates();
        addingItemsToTheCartFromOrderTemplate();
        makingOrderWithItemsFromOrderTemplate();
    }

    @Test(retryAnalyzer = Retry.class)
    //3.  Просмотр шаблона > оформление заказа с удалением одного из товаров в корзине
    public void viewingTemplateThenMakingOrderWithDeletingItemInTheCart() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        //act
        navigationToOrderTemplates();
        expendHamburgerMenuInFirstOrderTemplate();
        clickingLookOrderTemplate();
        clickingActionsInOpenedOrderTemplate();
        clickingButtonForCreatingOrderInTheActions();
        confirmTheConfidenceThatTheBasketWillBeReplacedWithProductsFromTheOrderTemplate();
        choiceRandomProductInTheCart();
        deletingSelectedProducts();
        makeOrder.navigationToMakingOrderFromCart();
        makeOrder.trySelectCompany();
        makeOrder.findingNeededColumnAboutOrderOnTheMakingOrderPage("Количество");
        checkingThatItemIsDeletedFromOrder();
        makeOrder.makingOrder();
    }

    @Test(retryAnalyzer = Retry.class) //4.  Оформление заказа из шаблона с редактированием кол-ва товаров в корзине
    public void makingOrderFromTemplateWithEditingTheNumberOfProductsInTheBasket() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        //act
        navigationToOrderTemplates();
        uploadingExcelCatalogForOrderTemplatesWithAcceptIt("blankForOrderTemplates.xlsx");
        setNameForOrderTemplate();
        createOrderTemplate();
        navigationToOrderTemplates();
        addingItemsToTheCartFromOrderTemplate();
        memorizingTheTotalPriceInTheCart();
        clickingIconForChangingQuantityItemsInTheCart(1, "plus");
        waitingUntilTotalSumIsChanged();
        memorizingTheTotalPriceInTheCart();
        clickingIconForChangingQuantityItemsInTheCart(2, "minus");
        waitingUntilTotalSumIsChanged();
        memorizingTheTotalPriceInTheCart();
        makeOrder.navigationToMakingOrderFromCart();
        makeOrder.trySelectCompany();
        makeOrder.findingNeededColumnAboutOrderOnTheMakingOrderPage("Сумма");
        checkingThatPriceForOrderOnTheMakingOrderPageAndInTheCartIsTheSame();
        makeOrder.makingOrder();
    }

    @Test(retryAnalyzer = Retry.class)
    //5.  У сотрудника отображается шаблон заказа который создал другой пользовтаель и привязал к их общей организации
    public void employeeSeesOrderTemplateThatWasCreatedByAnotherUserAndLinkedToTheirCommonOrganization() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            //act
            navigationToOrganizationTab();
            determineThemeColor();
            determineCurrentOrganizationName();
            navigationToOrderTemplates();
            uploadingExcelCatalogForOrderTemplatesWithAcceptIt("blankForOrderTemplates.xlsx");
            setNameForOrderTemplate();
            setOrganizationForOrderTemplate();
            createOrderTemplate();
            navigationToOrderTemplates();
            checkingThatCreatedOrderTemplateIsDisplayed();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
            navigationToOrganizationTab();
            selectingOrganizationForWhichAnotherUserHasCreatedAnOrderTemplate();
            navigationToOrderTemplates();
            checkingThatCreatedOrderTemplateIsDisplayed();
        }
    }

    @Test(retryAnalyzer = Retry.class) //6.  Удаление шаблона пользователем, создавшим этот шаблон
    public void userWhoCreatedTheTemplateCanDeleteTheOrderTemplate() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        //act
        navigationToOrderTemplates();
        uploadingExcelCatalogForOrderTemplatesWithAcceptIt("blankForOrderTemplates.xlsx");
        setNameForOrderTemplate();
        createOrderTemplate();
        navigationToOrderTemplates();
        checkingThatCreatedOrderTemplateIsDisplayed();
        deletingFirstOrderTemplate();
        checkingThatCreatedOrderTemplateIsNotDisplayed();
    }

    @Test(retryAnalyzer = Retry.class) //7. Удаление шаблона заказа добавленным в организацию сотрудником у которого роль руководителя
    public void deletingOrderTemplateByAnEmployeeAddedToTheOrganizationWhoHasTheBossRole() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToOrganizationTab();
            determineThemeColor();
            determineCurrentOrganizationName();
            navigationToOrderTemplates();
            uploadingExcelCatalogForOrderTemplatesWithAcceptIt("blankForOrderTemplates.xlsx");
            setNameForOrderTemplate();
            setOrganizationForOrderTemplate();
            createOrderTemplate();
            //act
            addingAnEmployeeWithBossRoleToOrganizationWithOrderTemplate();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
            navigationToOrderTemplates();
            checkingThatCreatedOrderTemplateIsDisplayed();
            deletingFirstOrderTemplate();
            checkingThatCreatedOrderTemplateIsNotDisplayed();
        }
    }

    @Test(retryAnalyzer = Retry.class) //8.  Названия столбцов таблицы с шаблонами заказов отображаются
    public void columnNamesOfTheTableWithOrderTemplatesAreDisplayed() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        //act
        navigationToOrderTemplates();
        checkingColumnHeadersOrderTemplateTable();
    }

    @Test(retryAnalyzer = Retry.class)
    //9.  На детальной странице шаблона отображается список товаров, количество и сумма
    public void detailedPageOfTheTemplateDisplaysListOfProductsQuantityAndAmount() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToOrderTemplates();
        //act
        expendHamburgerMenuInFirstOrderTemplate();
        clickingLookOrderTemplate();
        checkingColumnHeadersOnTheDetailPageOfOrderTemplate();
        findingNumberOFColumnWithNeededName("Количество");
        checkingNumberOfItemsOnTheOrderTemplateDetailPage("1", "1");
        checkingNumberOfItemsOnTheOrderTemplateDetailPage("2", "2");
        findingNumberOFColumnWithNeededName("Стоимость");
        checkingPriceOfItemOnTheOrderTemplateDetailPage("1", "345000");
        checkingPriceOfItemOnTheOrderTemplateDetailPage("2", "125600");
        findingNumberOFColumnWithNeededName("Наименование");
        checkingNameOfItemOnTheOrderTemplateDetailPage("1", "Видеокамера Canon EOS C300");
        checkingNameOfItemOnTheOrderTemplateDetailPage("2", "Видеокамера Canon XA11");
        checkingTheTotalQuantityAndPrice();
        clickingActionsInOpenedOrderTemplate();
        clickingButtonForCreatingOrderInTheActions();
        confirmTheConfidenceThatTheBasketWillBeReplacedWithProductsFromTheOrderTemplate();
        checkingThatTotalPriceInTheCartIsEqualsOrderTemplate();
        makeOrder.navigationToMakingOrderFromCart();
        makeOrder.trySelectCompany();
        makeOrder.makingOrder();
    }

    @Test(retryAnalyzer = Retry.class)//10.  Выгрузка шаблона заказа в Excel файл
    public void uploadingAnOrderTemplateToAnExcelFile() {
        //arrange
        deletingExcelAndJpgFilesFromDownloads();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToOrderTemplates();
        uploadingExcelCatalogForOrderTemplatesWithAcceptIt("blankForOrderTemplates.xlsx");
        setNameForOrderTemplate();
        createOrderTemplate();
        navigationToOrderTemplates();
        //act
        expendHamburgerMenuInFirstOrderTemplate();
        clickingUploadExcel();
        checkingThatOrderTemplateIsDownloaded();
    }

    @Test(retryAnalyzer = Retry.class) //11.  Загрузить шаблона в систему. Шаблону присваивается название в соответствии с файлом
    public void uploadTheOrderTemplateToTheSystemItHasANameAccordingToTheFile() {
        //arrange
        deletingExcelAndJpgFilesFromDownloads();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToOrderTemplates();
        uploadingExcelCatalogForOrderTemplatesWithAcceptIt("blankForOrderTemplates.xlsx");
        setNameForOrderTemplate();
        createOrderTemplate();
        navigationToOrderTemplates();
        expendHamburgerMenuInFirstOrderTemplate();
        clickingUploadExcel();
        checkingThatOrderTemplateIsDownloaded();
        navigationToOrderTemplates();
        //act
        createOrderTemplateWithJustUnloadedFile();
        checkingThatNameByDefaultIsEqualsNameDownloadedFile();
        checkingColumnHeadersOnTheDetailPageOfOrderTemplate();
        findingNumberOFColumnWithNeededName("Количество");
        checkingNumberOfItemsOnTheOrderTemplateDetailPage("1", "1");
        checkingNumberOfItemsOnTheOrderTemplateDetailPage("2", "2");
        findingNumberOFColumnWithNeededName("Стоимость");
        checkingPriceOfItemOnTheOrderTemplateDetailPage("1", "345000");
        checkingPriceOfItemOnTheOrderTemplateDetailPage("2", "125600");
        findingNumberOFColumnWithNeededName("Наименование");
        checkingNameOfItemOnTheOrderTemplateDetailPage("1", "Видеокамера Canon EOS C300");
        checkingNameOfItemOnTheOrderTemplateDetailPage("2", "Видеокамера Canon XA11");
        checkingTheTotalQuantityAndPrice();
        createOrderTemplate();
    }

    @Test(retryAnalyzer = Retry.class) //12.  Открытие детальной страницы товара из шаблона заказа
    public void openingDetailedProductPageFromAnOrderTemplate() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToOrderTemplates();
        expendHamburgerMenuInFirstOrderTemplate();
        //act
        clickingLookOrderTemplate();
        checkingDataOnTheDetailPage();
        navigationToMeanPageByUrl();
    }

    @Test(retryAnalyzer = Retry.class)  //13.  Загрузка Excel каталога с невалидными товарами (нет в наличии, и больше чем есть в наличии)
    public void loadingAnExcelCatalogWithInvalidProductsOutOfStockAndMoreThanAvailable() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        //act
        navigationToOrderTemplates();
        uploadingExcelCatalogForOrderTemplatesWithAcceptIt("invalidBlank.xlsx");
        checkingThatOneOfItemIsUnavailable();
        checkingThatAtOneOfItemThereIsNoSuchQuantityAvailable();
        setNameForOrderTemplate();
        createOrderTemplate();
        navigationToOrderTemplates();
        checkingThatCreatedOrderTemplateIsDisplayed();
        addingItemsToTheCartFromOrderTemplate();
        checkingThatWasAddedOnlyOneItemWithAvailableQuantity();
    }

    @Test(retryAnalyzer = Retry.class) //14.  Отмена создания шаблона
    public void cancelTemplateCreation() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        //act
        navigationToOrderTemplates();
        uploadingExcelCatalogForOrderTemplatesWithAcceptIt("blankForOrderTemplates.xlsx");
        cancelingTemplateCreation(); //сейчас ошибка, потом дописать, добавить проверок после отмены
    }

    @Test(retryAnalyzer = Retry.class) //15.  Отображение не сохраненного шаблона в списке шаблонов
    public void displayingAnUnsavedTemplateInTheTemplateList() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        //act
        navigationToOrderTemplates();
        uploadingExcelCatalogForOrderTemplatesWithAcceptIt("blankForOrderTemplates.xlsx");
        navigationToMeanPageByUrl();
        navigationToOrderTemplates();
        checkingMessageThatOrderTemplateIsNotSaved();
        expendHamburgerMenuInFirstOrderTemplate();
        clickingChange();
        setNameForOrderTemplate();
        createOrderTemplate();
        navigationToOrderTemplates();
        checkingThatCreatedOrderTemplateIsDisplayed();
        checkingThatFirstOrderTemplateHasNotMarkThatItNotSaved();
    }

    @Test(retryAnalyzer = Retry.class) //16.  Удаление не сохраненного шаблона
    public void deletingAnUnsavedTemplate() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        //act
        navigationToOrderTemplates();
        uploadingExcelCatalogForOrderTemplatesWithAcceptIt("blankForOrderTemplates.xlsx");
        navigationToMeanPageByUrl();
        navigationToOrderTemplates();
        checkingMessageThatOrderTemplateIsNotSaved();
        rememberingNameFirstTemplate();
        deletingFirstOrderTemplate();
        checkingThatCreatedOrderTemplateIsNotDisplayed();
    }
    @Test(retryAnalyzer = Retry.class) //17.  Отмена создания шаблона сразу после его загрузки
    public void cancelTemplateCreationImmediatelyAfterUploadingIt() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        //act
        navigationToOrderTemplates();
        uploadingExcelCatalogForOrderTemplates("blankForOrderTemplates.xlsx");
        closingCatalogDownloadWindowPupOpWindow();
    }


}
