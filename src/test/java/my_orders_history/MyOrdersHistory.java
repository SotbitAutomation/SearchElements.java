package my_orders_history;

import base_actions.Retry;
import org.testng.annotations.Test;

public class MyOrdersHistory extends MethodsForMyOrders {

    @Test (retryAnalyzer = Retry.class) //1 Оформленный заказ отображается
    public void doneOrderIsDisplayedInMyOrdersPage() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        navigationToCart();
        navigationToMakingOrderFromCart();
        trySelectCompany();
        checkingPriceOfProductsOnTheMakingOrderPage();
        choiceRandomDeliveryWay();
        checkingPriceOfProductsOnTheMakingOrderPage();
        choiceRandomPaymentMethod();
        makingOrder();
        checkingThatTheSelectedPaymentMethodIsDisplayed();
        //act
        navigationToMyOrdersPage();
        checkingThatLastOrderIsDisplayed();
    }
    @Test (retryAnalyzer = Retry.class)//2 Данные оформленного заказа соответствуют данным вводимым при оформлении заказа
    public void informationOrderEqualsTheOneSpecifiedWhenMadeOrder() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        navigationToCart();
        navigationToMakingOrderFromCart();
        trySelectCompany();
        rememberingINNOfOrganizationOnThePageOfMakingOrder();
        checkingPriceOfProductsOnTheMakingOrderPage();
        choiceRandomDeliveryWay();
        checkingPriceOfProductsOnTheMakingOrderPage();
        choiceRandomPaymentMethod();
        makingOrder();
        checkingThatTheSelectedPaymentMethodIsDisplayed();
        //act
        navigationToMyOrdersPage();
        checkingThatLastOrderIsDisplayed();
        openingLastOrder();
        checkingThatDeliveryWayIsEqualTheOneSpecifiedWhenMadeOrder();
        checkingThatPaymentMethodIsEqualTheOneSpecifiedWhenMadeOrder();
        checkingThatINNIsEqualTheOneSpecifiedWhenMadeOrder();
    }
    @Test (retryAnalyzer = Retry.class)//3 Проверка вкладок с детальной информацией на странице заказа
    public void checkDetailInformationOnOrderPage() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        navigationToCart();
        navigationToMakingOrderFromCart();
        trySelectCompany();
        rememberingINNOfOrganizationOnThePageOfMakingOrder();
        checkingPriceOfProductsOnTheMakingOrderPage();
        choiceRandomDeliveryWay();
        checkingPriceOfProductsOnTheMakingOrderPage();
        choiceRandomPaymentMethod();
        makingOrder();
        checkingThatTheSelectedPaymentMethodIsDisplayed();
        //act
        navigationToMyOrdersPage();
        checkingThatLastOrderIsDisplayed();
        openingLastOrder();
        checkingThatDeliveryWayIsEqualTheOneSpecifiedWhenMadeOrder();
        checkingThatPaymentMethodIsEqualTheOneSpecifiedWhenMadeOrder();
        checkingThatINNIsEqualTheOneSpecifiedWhenMadeOrder();
        navigationToProductTabInOrderPage();
        navigationToDocumentsTabInOrderPage();
        navigationToPaymentsTabInOrderPage();
        navigationToDeliveryTabInOrderPage();
        navigationToSupportServiceTabInOrderPage();
    }
    @Test (retryAnalyzer = Retry.class)//4 Отмена заказа
    public void CancelMadeOrder() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        navigationToCart();
        navigationToMakingOrderFromCart();
        trySelectCompany();
        checkingPriceOfProductsOnTheMakingOrderPage();
        choiceRandomDeliveryWay();
        checkingPriceOfProductsOnTheMakingOrderPage();
        choiceRandomPaymentMethod();
        makingOrder();
        checkingThatTheSelectedPaymentMethodIsDisplayed();
        //act
        navigationToMyOrdersPage();
        checkingThatLastOrderIsDisplayed();
        openingLastOrder();
        cancelingLastOrder();
    }
    @Test (retryAnalyzer = Retry.class)//5 Повтор рандомного заказа
    public void reOrderingRandomOrder() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        navigationToCart();
        navigationToMakingOrderFromCart();
        trySelectCompany();
        checkingPriceOfProductsOnTheMakingOrderPage();
        choiceRandomDeliveryWay();
        checkingPriceOfProductsOnTheMakingOrderPage();
        choiceRandomPaymentMethod();
        makingOrder();
        checkingThatTheSelectedPaymentMethodIsDisplayed();
        //act
        navigationToMyOrdersPage();
        checkingThatLastOrderIsDisplayed();
        navigationToMyOrdersPage();
        openingRandomOrder();
        ReOrderingOrder();
        navigationToMakingOrderFromCart();
        choiceRandomDeliveryWay();
        checkingPriceOfProductsOnTheMakingOrderPage();
        choiceRandomPaymentMethod();
        makingOrder();
        navigationToMyOrdersPage();
        checkingPriceOfLastedOrder();
    }
    @Test (retryAnalyzer = Retry.class)//6 Смена способа оплаты совершенного заказа
    public void ChangingPaymentMethodForCompletedOrder() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        navigationToCart();
        navigationToMakingOrderFromCart();
        trySelectCompany();
        checkingPriceOfProductsOnTheMakingOrderPage();
        choiceRandomDeliveryWay();
        checkingPriceOfProductsOnTheMakingOrderPage();
        choiceRandomPaymentMethod();
        makingOrder();
        checkingThatTheSelectedPaymentMethodIsDisplayed();
        //act
        navigationToMyOrdersPage();
        checkingThatLastOrderIsDisplayed();
        openingLastOrder();
        changingWayOfPaymentInOrder();
        navigationToMyOrdersPage();
        checkingThatLastOrderIsDisplayed();
        openingLastOrder();
        checkingThatPaymentMethodIsChanged();
        navigationToMyOrdersPage();
        openingLastOrder();
        navigationToPaymentsTabInOrderPage();
        checkingThatPaymentMethodIsChangedInTabOfPayment();
    }

    @Test (retryAnalyzer = Retry.class)//7. Настройка кол-ва заказов на странице и проверка пагинации
    public void settingUpTheNumberOfOrdersOnThePageThenCheckingPagination() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToMyOrdersPage();
        navigationToComponentOfUserOrders();
        selectTheNumberOfDisplayedOrdersOnThePage();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToMyOrdersPage();
        creatingOrdersUntilTheSecondPageOfOrdersIsVisible();
        checkingThatPagesAreSwitching();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToMyOrdersPage();
        navigationToComponentOfUserOrders();
        selectTheNumberOfDisplayedOrdersOnThePageIsEquallyThirty();
        turnOffEditMode();
        hideAdminPanel();
    }

    @Test (retryAnalyzer = Retry.class)//8 Проверка корректности состава товаров на детальной странице оформленного заказа
    public void checkingTheCorrectnessOfTheProductsOfTheGoodsOnTheDetailedPageOfTheCompletedOrder() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        navigationToCart();
        rememberingNamesAndQuantityAddedProducts();
        navigationToMakingOrderFromCart();
        trySelectCompany();
        rememberingTotalPrice();
        makingOrder();
        navigationToMyOrdersPage();
        openingLastOrder();
        checkingThatAddedProductsAreDisplayed();
    }
    @Test (retryAnalyzer = Retry.class)//9 Поиск по имени добавленного товара на детальной станицы оформленного заказа
    public void searchForTheAddedProductOnTheDetailedPageOfTheCompletedOrder() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        navigationToCart();
        rememberingNamesAndQuantityAddedProducts();
        navigationToMakingOrderFromCart();
        trySelectCompany();
        makingOrder();
        navigationToMyOrdersPage();
        openingLastOrder();
        enteringWordForSearch();
        checkingThatOnlyOneProductIsDisplayed();
    }
    @Test (retryAnalyzer = Retry.class)//10 Добавление второй оплаты за заказ проверка ее отображения на детальной странице заказа
    public void addingSecondPaymentForAnOrderCheckingItsDisplayOnTheDetailPage() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProduct();
        navigationToCart();
        navigationToMakingOrderFromCart();
        trySelectCompany();
        makingOrder();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToOrdersPageInAdminPart();
        openLastOrder();
        addingSecondPaymentForOrder();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToMyOrdersPage();
        openingLastOrder();
        checkingThatThereAreTwoPaymentsThere();
    }
    @Test (retryAnalyzer = Retry.class)//11 Добавление второй отгрузки заказа, проверка ее отображения на детальной странице заказа
    public void addingSecondShipmentOfTheOrderCheckingItsDisplayOnTheDetailedOrderPage() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProduct();
        implicitWaiting();
        navigationToCart();
        navigationToMakingOrderFromCart();
        trySelectCompany();
        makingOrder();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToOrdersPageInAdminPart();
        openLastOrder();
        addingSecondDeliveryForOrder();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToMyOrdersPage();
        openingLastOrder();
        checkingThatThereAreTwoDelivery();
    }
    @Test (retryAnalyzer = Retry.class)//12 Поиск оформленного заказа по его номеру
    public void searchForCompletedOrderByItsNumber() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        navigationToCart();
        rememberingNamesAndQuantityAddedProducts();
        navigationToMakingOrderFromCart();
        trySelectCompany();
        makingOrder();
        navigationToMyOrdersPage();
        //act
        searchForAnOrderByItSNumber();
        openingLastOrder();
        navigationToMyOrdersPage();
        deletingNumberForSearchUsingCloseIcon();
        checkingThatAllOrdersAreDisplayedAgain();
        searchForAnOrderByItSNumber();
        deletingNumberForSearchUsingFieldForSearch();
        checkingThatAllOrdersAreDisplayedAgain();
    }

}
