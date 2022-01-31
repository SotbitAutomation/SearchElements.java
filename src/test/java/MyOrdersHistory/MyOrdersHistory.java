package MyOrdersHistory;

import org.junit.Test;

public class MyOrdersHistory extends MethodsForMyOrders {

    @Test //1 Оформленный заказ отображается
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
        changingCompanyInTheCartForExtendedVersion();
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
    @Test //2 Данные оформленного заказа соответствуют данным вводимым при оформлении заказа
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
        changingCompanyInTheCartForExtendedVersion();
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
    @Test //3 Проверка вкладок с детальной информацией на странице заказа
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
        changingCompanyInTheCartForExtendedVersion();
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
    @Test //4 Отмена заказа
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
        changingCompanyInTheCartForExtendedVersion();
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
    @Test //5 Повтор рандомного заказа
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
        changingCompanyInTheCartForExtendedVersion();
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
    @Test //6 Смена способа оплаты совершенного заказа
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
        changingCompanyInTheCartForExtendedVersion();
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

    @Test //7. Настройка кол-ва заказов на странице и проверка пагинации
    public void settingUpTheNumberOfOrdersOnThePageThenCheckingPagination() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToMyOrdersPage();
        enableEditMode();
        navigationToComponentOfUserOrders();
        selectTheNumberOfDisplayedOrdersOnThePage();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToMyOrdersPage();
        creatingOrdersUntilTheSecondPageOfOrdersIsVisible();
        checkingThatPagesAreSwitching();
    }

    @Test //8 Проверка корректности состава товаров на детальной странице оформленного заказа
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
    @Test //9 Поиск по имени добавленного товара на детальной станицы оформленного заказа
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







//
//    @Test
//    public void circle() {
//        for (int i = 0; i < 10; i++) {
//            settingUpTheNumberOfOrdersOnThePageThenCheckingPagination();
//
//
//
//
//
//
//            productCounterInTheCart = 0;
//            pricesForAllProductsInTheFooter = 0;
//            sumOfPricesOfTheAddedProducts = 0;
//            exitFromB2B();
//        }
//    }



}
