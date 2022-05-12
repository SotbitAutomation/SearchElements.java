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
        uploadingExcelCatalogForOrderTemplates("blankForOrderTemplates.xlsx");
        setNameForOrderTemplate();
        createOrderTemplate();
        navigationToOrderTemplates();
        checkingThatCreatedOrderTemplateDisplayed();
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
            uploadingExcelCatalogForOrderTemplates("blankForOrderTemplates.xlsx");
            setNameForOrderTemplate();
            setOrganizationForOrderTemplate();
            createOrderTemplate();
            navigationToOrderTemplates();
            checkingThatCreatedOrderTemplateDisplayed();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
            navigationToOrganizationTab();
            selectingOrganizationForWhichAnotherUserHasCreatedAnOrderTemplate();
            navigationToOrderTemplates();
            checkingThatCreatedOrderTemplateDisplayed();
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
        uploadingExcelCatalogForOrderTemplates("blankForOrderTemplates.xlsx");
        setNameForOrderTemplate();
        createOrderTemplate();
        navigationToOrderTemplates();
        checkingThatCreatedOrderTemplateDisplayed();
        deletingFirstOrderTemplate();
        checkingThatCreatedOrderTemplateIsNotDisplayed();
    }

    @Test(retryAnalyzer = Retry.class)
//7. Удаление шаблона заказа добавленным в организацию сотрудником у которого роль руководителя
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
            uploadingExcelCatalogForOrderTemplates("blankForOrderTemplates.xlsx");
            setNameForOrderTemplate();
            setOrganizationForOrderTemplate();
            createOrderTemplate();
            //act
            addingAnEmployeeWithBossRoleToOrganizationWithOrderTemplate();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
            navigationToOrderTemplates();
            checkingThatCreatedOrderTemplateDisplayed();
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

    @Test(retryAnalyzer = Retry.class) //9.  На детальной странице шаблона отображается список товаров, количество и сумма
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
        checkingPriceOfItemOnTheOrderTemplateDetailPage("1", 345000);
        checkingPriceOfItemOnTheOrderTemplateDetailPage("2", 125600);
        findingNumberOFColumnWithNeededName("Наименование");
        checkingNameOfItemOnTheOrderTemplateDetailPage("1", "Видеокамера Canon EOS C300");
        checkingNameOfItemOnTheOrderTemplateDetailPage("2", "Видеокамера Canon XA11");
    }
    @Test(retryAnalyzer = Retry.class)//10.  Выгрузка шаблона заказа в Excel файл
    public void uploadingAnOrderTemplateToAnExcelFile() {
        //arrange
        deletingExcelAndJpgFilesFromDownloads();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToOrderTemplates();
        uploadingExcelCatalogForOrderTemplates("blankForOrderTemplates.xlsx");
        setNameForOrderTemplate();
        createOrderTemplate();
        navigationToOrderTemplates();
        //act
        expendHamburgerMenuInFirstOrderTemplate();
        clickingUploadExcel();
        checkingThatOrderTemplateIsDownloaded();
    }

}
