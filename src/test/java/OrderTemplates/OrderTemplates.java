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
    @Test(retryAnalyzer = Retry.class) //3.  Просмотр шаблона > оформление заказа с редактирование товаров в корзине
    public void viewingTemplateThenMakingOrderWithEditingItemsInTheCart() {
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

}
