package mean_page;


import org.testng.annotations.Test;

public class MeanPageCalendar extends MethodsForMeanPage {

    @Test //1. Проверить корректность отображения Календаря и заказов в нем у админа
    public void checkThatTheOrdersAreDisplayInTheCalendarForAdmin() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        makeOrder.deletingProductsFromTheCart();
        navigationToCatalogTab();
        makeOrder.changeTheQuantityOfRandomProduct();
        makeOrder.checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        navigationToCart();
        makeOrder.navigationToMakingOrderFromCart();
        makeOrder.trySelectCompany();
        makeOrder.checkingPriceOfProductsOnTheMakingOrderPage();
        makeOrder.makingOrder();
        navigationToMyOrdersPage();
        rememberingLastOrderForCalendar();
        navigationToTheCalendar();
        checkThatTheOrdersAreDisplayInTheCalendarTab();
    }
    @Test //2. Проверить корректность отображения Календаря и заказов в нем у юзера
    public void checkThatTheOrdersAreDisplayInTheCalendarForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        makeOrder.deletingProductsFromTheCart();
        navigationToCatalogTab();
        makeOrder.changeTheQuantityOfRandomProduct();
        makeOrder.checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        navigationToCart();
        makeOrder.navigationToMakingOrderFromCart();
        makeOrder.trySelectCompany();
        makeOrder.checkingPriceOfProductsOnTheMakingOrderPage();
        makeOrder.makingOrder();
        navigationToMyOrdersPage();
        rememberingLastOrderForCalendar();
        navigationToTheCalendar();
        checkThatTheOrdersAreDisplayInTheCalendarTab();
    }
}
