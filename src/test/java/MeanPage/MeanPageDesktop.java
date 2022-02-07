package MeanPage;

import org.junit.Test;

public class MeanPageDesktop extends MethodsForMeanPage {


    @Test //1. Проверка наличия баннера
    public void checkThatTheBannerIsVisible() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
    }

    @Test //2. Редактирование расположения виджетов у админа
    public void movingWidgetsWithTheAdmin() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        movingARandomWidget(1);
        movingARandomWidget(2);
        movingARandomWidget(3);
        resettingTheCurrentWidgetSettings();
    }


    @Test //3. Редактирование расположения виджетов у пользователя
    public void movingWidgetsWithTheUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        movingARandomWidget(1);
        movingARandomWidget(2);
        movingARandomWidget(3);
        resettingTheCurrentWidgetSettings();
    }

    @Test //4. Редактирование набора виджетов у админа
    public void addingAndRemovingWidgetsFromTheAdmin() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        addingRandomWidgetToTheDesktop();
        deletionJustAddedWidget();
        resettingTheCurrentWidgetSettings();
    }

    @Test //5. Редактирование набора виджетов у пользователя
    public void addingAndRemovingWidgetsFromTheUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        addingRandomWidgetToTheDesktop();
        deletionJustAddedWidget();
        deletionRandomWidgetFromDesktop();
        resettingTheCurrentWidgetSettings();
    }

    @Test //6 Редактирование набора виджетов по умолчанию для админа
    public void settingTheDefaultWidgetSetForAdmin() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        deletionRandomWidgetFromDesktop();
        addingRandomWidgetToTheDesktop();
        storingTheLocationOfWidgetsOnTheDesktop();
        saveTheLocationOfWidgetsOnTheDesktopLikeDefault();
        deletionRandomWidgetFromDesktop();
        resettingTheCurrentWidgetSettings();
        checkingTgeReturnOfSettingsToDefault();
    }

    @Test //7 Редактирование набора виджетов по умолчанию для юзера
    public void settingTheDefaultWidgetSetForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        addingRandomWidgetToTheDesktop();
        storingTheLocationOfWidgetsOnTheDesktop();
        saveTheLocationOfWidgetsOnTheDesktopLikeDefault();
        exitFromB2B();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        deletionRandomWidgetFromDesktop();
        resettingTheCurrentWidgetSettings();
        checkingTgeReturnOfSettingsToDefault();
    }
    //Пока что пропал такой виджет!

//    @Test //8 Проверка выводимых данных виджета  "Состояние заказов" у админа
//    public void checkingTheDataInWidgetOfOrderStatusForAdmin() {
//        //arrange
//        navigationToAuthorizationTab();
//        //act
//        fillingFieldsOnTheLogInTabLikeAdmin();
//        logInToB2B();
//        navigationToTheDesktop();
//        addingTheWidgetOfOrdersStatusToDesktop();
//        Assert.assertTrue(driver.findElement(By.xpath("(//*[contains(@class, 'sotbit-cabinet-gadget-orders')] //*[contains(@class, 'widget_content')])[1]")).isDisplayed());
//    }
//    @Test //9 Проверка выводимых данных виджета  "Состояние заказов" у юзера
//    public void checkingTheDataInWidgetOfOrderStatusForUser() {
//        //arrange
//        navigationToAuthorizationTab();
//        //act
//        fillingFieldsOnTheLogInTabLikeUser();
//        logInToB2B();
//        navigationToTheDesktop();
//        addingTheWidgetOfOrdersStatusToDesktop();
//        Assert.assertTrue(driver.findElement(By.xpath("(//*[contains(@class, 'sotbit-cabinet-gadget-orders')] //*[contains(@class, 'widget_content')])[1]")).isDisplayed());
//    }

    @Test //10 Проверка выводимых данных виджета "Персональные данные" у админа
    public void checkingTheDataInWidgetOfPersonalDataForAdmin() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetOfPersonalDataToDesktop();
        storingDataFromTheWidgetOfPersonalData();
        navigationToTheSetting();
        storingDataFromTheSettingTabOfPersonalData();
    }

    @Test //11 Проверка выводимых данных виджета "Персональные данные" у юзера
    public void checkingTheDataInWidgetOfPersonalDataForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetOfPersonalDataToDesktop();
        storingDataFromTheWidgetOfPersonalData();
        navigationToTheSetting();
        storingDataFromTheSettingTabOfPersonalData();
    }

    @Test //12 Проверка выводимых данных виджета "Моя корзина" у админа
    public void checkingTheDataInWidgetOfMyCartForAdmin() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetOfMyCartToDesktop();
        storingDataFromTheWidgetOfMyCart();
        navigationToTheCart();
        storingTheQuantityOfProductsInTheCart();
    }

    @Test //13 Проверка выводимых данных виджета "Моя корзина" у юзера
    public void checkingTheDataInWidgetOfMyCartForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetOfMyCartToDesktop();
        storingDataFromTheWidgetOfMyCart();
        navigationToTheCart();
        storingTheQuantityOfProductsInTheCart();
    }

    @Test //14 Проверка выводимых данных виджета "Организации" у админа
    public void checkingTheDataInWidgetOfOrganizationsForAdmin() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetOfOrganizationsToDesktop();
        CheckingThatTheWidgetOfOrganizationsHaveContent();
    }

    @Test //15 Проверка выводимых данных виджета "Организации" у юзера
    public void checkingTheDataInWidgetOfOrganizationsForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetOfOrganizationsToDesktop();
        CheckingThatTheWidgetOfOrganizationsHaveContent();
    }


}
