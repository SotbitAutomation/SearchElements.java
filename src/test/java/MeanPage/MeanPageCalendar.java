package MeanPage;

import MakingOrders.MakingOrders;
import org.junit.Test;

public class MeanPageCalendar extends MethodsForMeanPage {

    @Test //1. Проверить корректность отображения Календаря и заказов в нем у админа
    public void checkThatTheOrdersAreDisplayInTheCalendarForAdmin() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheCalendar();
        checkThatTheOrdersAreDisplayInTheCalendarTab();
    }
    @Test //2. Проверить корректность отображения Календаря и заказов в нем у юзера
    public void checkThatTheOrdersAreDisplayInTheCalendarForUser() {
        //arrange
        MakingOrders makingOrders = new MakingOrders();
        makingOrders.madeOrder();
        //act
        navigationToTheCalendar();
        checkThatTheOrdersAreDisplayInTheCalendarTab();
    }
}
