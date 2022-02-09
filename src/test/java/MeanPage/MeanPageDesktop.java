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
        resettingTheCurrentWidgetSettings();
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
        resettingTheCurrentWidgetSettings();
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

    @Test //6. Редактирование набора виджетов по умолчанию для админа
    public void settingTheDefaultWidgetSetForAdmin() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        deletionRandomWidgetFromDesktop();
        storingTheLocationOfWidgetsOnTheDesktop();
        saveTheSettingForWidgetsOnTheDesktopLikeDefault();
        deletionRandomWidgetFromDesktop();
        addingRandomWidgetToTheDesktop();
        resettingTheCurrentWidgetSettings();
        checkingTgeReturnOfSettingsToDefault();
    }

    @Test //7. Редактирование набора виджетов по умолчанию для юзера
    public void settingTheDefaultWidgetSetForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        deletionRandomWidgetFromDesktop();
        storingTheLocationOfWidgetsOnTheDesktop();
        saveTheSettingForWidgetsOnTheDesktopLikeDefault();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletionRandomWidgetFromDesktop();
        addingRandomWidgetToTheDesktop();
        resettingTheCurrentWidgetSettings();
        checkingTgeReturnOfSettingsToDefault();
    }

    @Test //8 Проверка выводимых данных виджета  "Мои заказы" у админа
    public void checkingTheDataInWidgetOfMyOrdersForAdmin() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetToDesktop(myOrdersWidgetLocator);
        makeOrderForWidget();
        rememberingDataOfMyOrdersInTheWidget();
        navigationToMyOrdersPage();
        checkingThatThereIsAOrderWhichDisplayedInWidget();
        navigationToTheDesktop();
        goToTheDetailedOrderPageFromTheWidget();
    }
    @Test //9 Проверка выводимых данных виджета  "Мои заказы" у юзера
    public void checkingTheDataInWidgetOfMyOrdersForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetToDesktop(myOrdersWidgetLocator);
        makeOrderForWidget();
        rememberingDataOfMyOrdersInTheWidget();
        navigationToMyOrdersPage();
        checkingThatThereIsAOrderWhichDisplayedInWidget();
        navigationToTheDesktop();
        goToTheDetailedOrderPageFromTheWidget();
    }

    @Test //10. Проверка выводимых данных виджета "Персональные данные" у админа
    public void checkingTheDataInWidgetOfPersonalDataForAdmin() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetToDesktop(personalDataWidgetLocator);
        storingDataFromTheWidgetOfPersonalData();
        navigationToTheSetting();
        storingDataFromTheSettingTabOfPersonalData();
        checkingThatDataInWidgetOfPersonalIsEqualsSettingInMeanPage();
        navigationToTheDesktop();
    }

    @Test //11. Проверка выводимых данных виджета "Персональные данные" у юзера
    public void checkingTheDataInWidgetOfPersonalDataForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetToDesktop(personalDataWidgetLocator);
        storingDataFromTheWidgetOfPersonalData();
        navigationToTheSetting();
        storingDataFromTheSettingTabOfPersonalData();
        checkingThatDataInWidgetOfPersonalIsEqualsSettingInMeanPage();
        navigationToTheDesktop();
    }

    @Test //12. Проверка выводимых данных виджета "Моя корзина" у админа
    public void checkingTheDataInWidgetOfMyCartForAdmin() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetToDesktop(myCartWidgetLocator);
        storingDataFromTheWidgetOfMyCart();
        navigationToTheCart();
        storingTheQuantityOfProductsInTheCart();
        checkingThatTheNumberOfProductsInTheWidgetAndInTheCartAreEqual();
    }

    @Test //13. Проверка выводимых данных виджета "Моя корзина" у юзера
    public void checkingTheDataInWidgetOfMyCartForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetToDesktop(myCartWidgetLocator);
        storingDataFromTheWidgetOfMyCart();
        navigationToTheCart();
        storingTheQuantityOfProductsInTheCart();
        checkingThatTheNumberOfProductsInTheWidgetAndInTheCartAreEqual();
    }

    @Test //14. Проверка выводимых данных виджета "Организации" у админа
    public void checkingTheDataInWidgetOfOrganizationsForAdmin() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetToDesktop(organizationWidgetLocator);
        checkingThatTheWidgetOfOrganizationsHaveContent();
    }

    @Test //15. Проверка выводимых данных виджета "Организации" у юзера
    public void checkingTheDataInWidgetOfOrganizationsForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetToDesktop(organizationWidgetLocator);
        checkingThatTheWidgetOfOrganizationsHaveContent();
    }
    @Test //16. У пользователя нет кнопки "сохранить как настройки по умолчанию"
    public void userDoesNotHaveSaveDefaultSettingsButton() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        addingRandomWidgetToTheDesktop();
        checkingThatThereIsNoButtonToSaveTheDefaultSettings();
    }
    @Test //17. Проверка выводимых данных виджета "Заметки" у админа и пользователя
    public void checkingTheOutputDataOfTheNotesWidgetFromTheAdminAndTheUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetToDesktop(notesWidgetLocator);
        fillingTheDataForTheNotesWidget();
        checkingThatEnteredDataIsDisplayedInTheNotesWidget();
        saveTheSettingForWidgetsOnTheDesktopLikeDefault();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        checkingThatEnteredDataIsDisplayedInTheNotesWidget();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        deletionJustAddedWidget();
        saveTheSettingForWidgetsOnTheDesktopLikeDefault();
    }
    @Test //18. Проверка выводимых данных виджета Каталог у админа
    public void checkingTheOutputDataOfTheCatalogWidgetFromTheAdmin() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetToDesktop(catalogWidgetLocator);
        makeOrder.uploadingExcelCatalog("blank.xlsx");
        navigationToCart();
        makeOrder.checkingThatThereAreTwoGefestGasStoveInTheCart();
        navigationToTheDesktop();
        downloadingCatalogToYourComputerFromMeanPage();
        makeOrder.checkingThatCatalogIsDownloaded();
    }
    @Test //19. Проверка выводимых данных виджета Каталог у юзера
    public void checkingTheOutputDataOfTheCatalogWidgetFromTheUser() {
        //arrange
        deletingExcelAndJpgFilesFromDownloads();
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetToDesktop(catalogWidgetLocator);
        makeOrder.uploadingExcelCatalog("blank.xlsx");
        navigationToCart();
        makeOrder.checkingThatThereAreTwoGefestGasStoveInTheCart();
        navigationToTheDesktop();
        downloadingCatalogToYourComputerFromMeanPage();
        makeOrder.checkingThatCatalogIsDownloaded();
    }
    @Test //20. Проверка выводимых данных виджета пробки
    public void checkingTheOutputDataOfTheTrafficJamsWidget() {
        //arrange
        deletingExcelAndJpgFilesFromDownloads();
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetToDesktop(trafficJamsWidgetLocator);
        checkingThatTheWidgetOfTrafficJamsHaveContent();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetToDesktop(trafficJamsWidgetLocator);
        checkingThatTheWidgetOfTrafficJamsHaveContent();
    }
    @Test //21. Добавление избранной ссылки от администратора, отображение этой ссылки для пользователей
    public void addingFavoriteLinkFromTheAdminDisplayingThisLinkForUsers() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        addingTheWidgetToDesktop(favoritesLinks);
        fillingTheDataForTheFavoriteLinksWidget();
        saveTheSettingForWidgetsOnTheDesktopLikeDefault();
        checkingThatTheLinkInTheFavoriteLinksWidgetWorks();
        navigationToMeanPageByUrl();
        exitFromB2B();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        checkingThatTheLinkInTheFavoriteLinksWidgetWorks();
        navigationToMeanPageByUrl();
        exitFromB2B();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        deletionJustAddedWidget();
        saveTheSettingForWidgetsOnTheDesktopLikeDefault();
    }
    @Test //22. Проверка добавления виджета "Погода" у админа
    public void checkingAddingWidgetOfWeatherForAdmin() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetToDesktop(weatherWidgetLocator);
        checkingThatWeatherWidgetIsDisplayed();
    }
    @Test //23. Проверка добавления виджета "Погода" у юзера
    public void checkingAddingWidgetOfWeatherForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetToDesktop(weatherWidgetLocator);
        checkingThatWeatherWidgetIsDisplayed();
    }
    @Test //24. Проверка выводимых данных виджета "RSS лента" у админа
    public void checkingAddingWidgetOfRssLentForAdmin() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetToDesktop(rssLEntWidgetLocator);
        addingRssLentToTheWidget();
        checkingThatThisLentIsAdded();
    }
    @Test //25. Проверка выводимых данных виджета "RSS лента" у пользователя
    public void checkingAddingWidgetOfRssLentForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetToDesktop(rssLEntWidgetLocator);
        addingRssLentToTheWidget();
        checkingThatThisLentIsAdded();
    }
    @Test //26. Заказ пополнение личного счета с помощью виджета "Личный кабинет"
    public void orderReplenishmentOfThePersonalAccountUsingThePersonalAccountWidget() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetToDesktop(personalAccountWidgetLocator);
        requestReplenishmentOfYourPersonalAccount();
        checkingThatThePersonalAccountReplenishmentRequestHasBeenCreated();
    }
}
