package MeanPage;

import BaseActions.Retry;
import org.testng.annotations.Test;

public class MeanPageDesktop extends MethodsForMeanPage {
    @Test (retryAnalyzer = Retry.class)//0. Удаление всех и добавление всех виджетов
    public void a_deletingAllAndAddingAllWidgets() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        deletingAllWidget();
        addingAllWidget();
        distributeWidgetsByColumns();
        navigationToTheDesktop();
        saveTheSettingForWidgetsOnTheDesktopLikeDefault();
    }
    @Test (retryAnalyzer = Retry.class)//1. Проверка наличия баннера
    public void checkThatTheBannerIsVisible() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();

    }

    @Test (retryAnalyzer = Retry.class)//2. Редактирование расположения виджетов у админа
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


    @Test (retryAnalyzer = Retry.class)//3. Редактирование расположения виджетов у пользователя
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

    @Test (retryAnalyzer = Retry.class)//4. Редактирование набора виджетов у админа
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

    @Test (retryAnalyzer = Retry.class)//5. Редактирование набора виджетов у пользователя
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

    @Test (retryAnalyzer = Retry.class)//6. Редактирование набора виджетов по умолчанию для админа
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

    @Test (retryAnalyzer = Retry.class)//7. Редактирование набора виджетов по умолчанию для юзера
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
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        deletionRandomWidgetFromDesktop();
        addingRandomWidgetToTheDesktop();
        resettingTheCurrentWidgetSettings();
        checkingTgeReturnOfSettingsToDefault();
    }

    @Test (retryAnalyzer = Retry.class)//8 Проверка выводимых данных виджета  "Мои заказы" у админа
    public void checkingTheDataInWidgetOfMyOrdersForAdmin() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        addingTheWidgetToDesktop(myOrdersWidgetLocator);
        makeOrderForWidget();
        rememberingDataOfMyOrdersInTheWidget();
        navigationToMyOrdersPage();
        checkingThatThereIsAOrderWhichDisplayedInWidget();
        navigationToTheDesktop();
        goToTheDetailedOrderPageFromTheWidget();
    }
    @Test (retryAnalyzer = Retry.class)//9 Проверка выводимых данных виджета  "Мои заказы" у юзера
    public void checkingTheDataInWidgetOfMyOrdersForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        addingTheWidgetToDesktop(myOrdersWidgetLocator);
        makeOrderForWidget();
        rememberingDataOfMyOrdersInTheWidget();
        navigationToMyOrdersPage();
        checkingThatThereIsAOrderWhichDisplayedInWidget();
        navigationToTheDesktop();
        goToTheDetailedOrderPageFromTheWidget();
    }

    @Test (retryAnalyzer = Retry.class)//10. Проверка выводимых данных виджета "Персональные данные" у админа
    public void checkingTheDataInWidgetOfPersonalDataForAdmin() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        addingTheWidgetToDesktop(personalDataWidgetLocator);
        storingDataFromTheWidgetOfPersonalData();
        navigationToTheSetting();
        storingDataFromTheSettingTabOfPersonalData();
        checkingThatDataInWidgetOfPersonalIsEqualsSettingInMeanPage();
        navigationToTheDesktop();
    }

    @Test (retryAnalyzer = Retry.class)//11. Проверка выводимых данных виджета "Персональные данные" у юзера
    public void checkingTheDataInWidgetOfPersonalDataForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        addingTheWidgetToDesktop(personalDataWidgetLocator);
        storingDataFromTheWidgetOfPersonalData();
        navigationToTheSetting();
        storingDataFromTheSettingTabOfPersonalData();
        checkingThatDataInWidgetOfPersonalIsEqualsSettingInMeanPage();
        navigationToTheDesktop();
    }

    @Test (retryAnalyzer = Retry.class)//12. Проверка выводимых данных виджета "Моя корзина" у админа
    public void checkingTheDataInWidgetOfMyCartForAdmin() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        addingTheWidgetToDesktop(myCartWidgetLocator);
        storingDataFromTheWidgetOfMyCart();
        navigationToTheCart();
        storingTheQuantityOfProductsInTheCart();
        checkingThatTheNumberOfProductsInTheWidgetAndInTheCartAreEqual();
    }

    @Test (retryAnalyzer = Retry.class)//13. Проверка выводимых данных виджета "Моя корзина" у юзера
    public void checkingTheDataInWidgetOfMyCartForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        addingTheWidgetToDesktop(myCartWidgetLocator);
        storingDataFromTheWidgetOfMyCart();
        navigationToTheCart();
        storingTheQuantityOfProductsInTheCart();
        checkingThatTheNumberOfProductsInTheWidgetAndInTheCartAreEqual();
    }

    @Test (retryAnalyzer = Retry.class)//14. Проверка выводимых данных виджета "Организации" у админа
    public void checkingTheDataInWidgetOfOrganizationsForAdmin() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        addingTheWidgetToDesktop(organizationWidgetLocator);
        checkingThatTheWidgetOfOrganizationsHaveContent();
        goToTheDetailedOrganizationPageFromTheWidget();
    }

    @Test (retryAnalyzer = Retry.class)//15. Проверка выводимых данных виджета "Организации" у юзера
    public void checkingTheDataInWidgetOfOrganizationsForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        addingTheWidgetToDesktop(organizationWidgetLocator);
        checkingThatTheWidgetOfOrganizationsHaveContent();
        goToTheDetailedOrganizationPageFromTheWidget();
    }
    @Test (retryAnalyzer = Retry.class)//16. У пользователя нет кнопки "сохранить как настройки по умолчанию"
    public void userDoesNotHaveSaveDefaultSettingsButton() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        checkingThatThereIsNoButtonToSaveTheDefaultSettings();
    }
    @Test (retryAnalyzer = Retry.class)//17. Проверка выводимых данных виджета "Заметки" у админа и пользователя
    public void checkingTheOutputDataOfTheNotesWidgetFromTheAdminAndTheUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        addingTheWidgetToDesktop(notesWidgetLocator);
        fillingTheDataForTheNotesWidget();
        checkingThatEnteredDataIsDisplayedInTheNotesWidget();
        saveTheSettingForWidgetsOnTheDesktopLikeDefault();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        checkingThatEnteredDataIsDisplayedInTheNotesWidget();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        deletionJustAddedWidget();
        saveTheSettingForWidgetsOnTheDesktopLikeDefault();
    }
    @Test (retryAnalyzer = Retry.class)//18. Проверка выводимых данных виджета Каталог у админа
    public void checkingTheOutputDataOfTheCatalogWidgetFromTheAdmin() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        addingTheWidgetToDesktop(catalogWidgetLocator);
        makeOrder.uploadingExcelCatalog("blank.xlsx");
        navigationToCart();
        makeOrder.checkingThatThereAreTwoGefestGasStoveInTheCart();
        navigationToTheDesktop();
        downloadingCatalogToYourComputerFromMeanPage();
        makeOrder.checkingThatCatalogIsDownloaded(".xlsx");
    }
    @Test (retryAnalyzer = Retry.class)//19. Проверка выводимых данных виджета Каталог у юзера
    public void checkingTheOutputDataOfTheCatalogWidgetFromTheUser() {
        //arrange
        deletingExcelAndJpgFilesFromDownloads();
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        addingTheWidgetToDesktop(catalogWidgetLocator);
        makeOrder.uploadingExcelCatalog("blank.xlsx");
        navigationToCart();
        makeOrder.checkingThatThereAreTwoGefestGasStoveInTheCart();
        navigationToTheDesktop();
        downloadingCatalogToYourComputerFromMeanPage();
        makeOrder.checkingThatCatalogIsDownloaded(".xlsx");
    }
    @Test (retryAnalyzer = Retry.class)//20. Проверка выводимых данных виджета пробки
    public void checkingTheOutputDataOfTheTrafficJamsWidget() {
        //arrange
        deletingExcelAndJpgFilesFromDownloads();
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        addingTheWidgetToDesktop(trafficJamsWidgetLocator);
        checkingThatTheWidgetOfTrafficJamsHaveContent();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        addingTheWidgetToDesktop(trafficJamsWidgetLocator);
        checkingThatTheWidgetOfTrafficJamsHaveContent();
    }
    @Test (retryAnalyzer = Retry.class)//21. Добавление избранной ссылки от администратора, отображение этой ссылки для пользователей
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
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        checkingThatTheLinkInTheFavoriteLinksWidgetWorks();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        deletionJustAddedWidget();
        saveTheSettingForWidgetsOnTheDesktopLikeDefault();
    }
    @Test (retryAnalyzer = Retry.class)//22. Проверка добавления виджета "Погода" у админа
    public void checkingAddingWidgetOfWeatherForAdmin() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        addingTheWidgetToDesktop(weatherWidgetLocator);
        checkingThatWeatherWidgetIsDisplayed();
        checkingThatWeatherWidgetHaveContent();
        checkingThatInTheWeatherWidgetTheDefaultCityIsMoscow();
        checkingTheTransitionToTheWeatherPageUsingTheLearnMoreButton();
        checkingThatTheSettingsAreBeingOpened();
    }
    @Test (retryAnalyzer = Retry.class)//23. Проверка добавления виджета "Погода" у юзера
    public void checkingAddingWidgetOfWeatherForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        addingTheWidgetToDesktop(weatherWidgetLocator);
        checkingThatWeatherWidgetIsDisplayed();
        checkingThatWeatherWidgetHaveContent();
        checkingThatInTheWeatherWidgetTheDefaultCityIsMoscow();
        checkingTheTransitionToTheWeatherPageUsingTheLearnMoreButton();
        checkingThatTheSettingsAreBeingOpened();
    }
    @Test (retryAnalyzer = Retry.class)//24. Проверка выводимых данных виджета "RSS лента" у админа
    public void checkingAddingWidgetOfRssLentForAdmin() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        addingTheWidgetToDesktop(rssLEntWidgetLocator);
        addingRssLentToTheWidget();
        checkingThatThisLentIsAdded();
    }
    @Test (retryAnalyzer = Retry.class)//25. Проверка выводимых данных виджета "RSS лента" у пользователя
    public void checkingAddingWidgetOfRssLentForUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        addingTheWidgetToDesktop(rssLEntWidgetLocator);
        addingRssLentToTheWidget();
        checkingThatThisLentIsAdded();
    }
    @Test (retryAnalyzer = Retry.class)//26. Заказ пополнение личного счета с помощью виджета "Личный кабинет"
    public void orderReplenishmentOfThePersonalAccountUsingThePersonalAccountWidget() {
        //arrange
        registr.registrationIPWithManualEntryINN();
        //act
        navigationToTheDesktop();
        resettingTheCurrentWidgetSettings();
        addingTheWidgetToDesktop(personalAccountWidgetLocator);
        requestReplenishmentOfYourPersonalAccount();
        checkingThatThePersonalAccountReplenishmentRequestHasBeenCreated();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToOrdersPageInAdminPart();
        makeOrder.checkingThatTheRequestForReplenishmentOfThePersonalAccountIsDisplayedByTheAdmin(tempValue);
        navigationToPageForAddingPersonalAccountInAdminPart();
        makeOrder.addMoneyToTheUserSPersonalAccount(registr.theSameEmail);
        navigationToAuthorizationTab();
        registr.fillingFieldsOnTheLogInTab();
        logInToB2B();
        navigationToTheDesktop();
        checkingThatTheUserHasBeenAddedMoneyToHisPersonalAccountInWidget();
    }
}
