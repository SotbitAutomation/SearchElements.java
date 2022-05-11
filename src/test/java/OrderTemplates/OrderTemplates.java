package OrderTemplates;

import BaseActions.Retry;
import org.testng.annotations.Test;

public class OrderTemplates extends MethodsForOrderTemplates{

    @Test(retryAnalyzer = Retry.class) //1.  Создание шаблона заказа
    public void creatingAnOrderTemplate() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        //act
        navigationToOrderTemplates();
        uploadingExcelCatalogForOrderTemplates("blank.xlsx");
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
    @Test(retryAnalyzer = Retry.class) //3.  Просмотр шаблона > оформление заказа с удалением одного из товаров в корзине
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
    @Test(retryAnalyzer = Retry.class) //5.  У сотрудника отображается шаблон заказа который создал другой пользовтаель и привязал к их общей организации
    public void employeeSeesOrderTemplateThatWasCreatedByAnotherUserAndLinkedToTheirCommonOrganization() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended){
            //arrange
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            //act
            navigationToOrganizationTab();
            determineThemeColor();
            determineCurrentOrganizationName();
            navigationToOrderTemplates();
            uploadingExcelCatalogForOrderTemplates("blank.xlsx");
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
        uploadingExcelCatalogForOrderTemplates("blank.xlsx");
        setNameForOrderTemplate();
        createOrderTemplate();
        navigationToOrderTemplates();
        checkingThatCreatedOrderTemplateDisplayed();
        deletingFirstOrderTemplate();
        checkingThatCreatedOrderTemplateIsNotDisplayed();
    }

    @Test(retryAnalyzer = Retry.class)//13. Регистрация руководителя компании из раздела сотрудники
    public void asdasd() {
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
            uploadingExcelCatalogForOrderTemplates("blank.xlsx");
            setNameForOrderTemplate();
            setOrganizationForOrderTemplate();
            createOrderTemplate();

            //act
            readingUserData();
            org.emailEmployee = emailEmployee;
            org.fillingFieldForCreatingEmployeeUsingReferralLink();
            org.choosingBossRole();
            org.addingAnEmployeeToAnOrganizationUsingByReferralLink();


            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
            navigationToOrderTemplates();
            checkingThatCreatedOrderTemplateDisplayed();
            deletingFirstOrderTemplate();
            checkingThatCreatedOrderTemplateIsNotDisplayed();

        }
    }

}
