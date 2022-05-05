package AccessToB2BWithoutAuthorization;

import BaseActions.Retry;
import org.testng.annotations.Test;

public class AccessToB2BWithoutAuthorization extends MethodsForAccessToB2BWithoutAuthorization{
    @Test(retryAnalyzer = Retry.class) //1. Просмотр разделов в b2b без авторизации
    public void viewingSectionsInB2BWithoutAuthorization() {
        firsNavigationToB2B();
        navigationToCatalogTab();
        navigationToTheDesktop();
        navigationToSubsectionFromTheAboutUsSection("contacts");
        checkingThatPageWithContactsIsOpened();
        checkingThatBreadcrumbContainsNavigationChain("Контакты");
        navigationToSubsectionFromTheAboutUsSection("payment");
        checkingThatPageWithPaymentWaysIsOpened();
        checkingThatBreadcrumbContainsNavigationChain("Оплата");
        navigationToSubsectionFromTheAboutUsSection("delivery");
        checkingThatPageWithDeliveryWaysIsOpened();
        checkingThatBreadcrumbContainsNavigationChain("Доставка");
        navigationToSubsectionFromTheAboutUsSection("news");
        checkingThatPageWithNewsIsOpened();
        checkingThatBreadcrumbContainsNavigationChain("Новости");
    }
    @Test(retryAnalyzer = Retry.class) //2. Проверка оптовых цен, добавление товаров без авторизации
    public void checkOptRicesThenAddingProductsWithoutAuthorization() {
        firsNavigationToB2B();
        navigationToCatalogTab();
        checkingThatThereAreNoOptPrices();
        catalog.determiningNumberOfProductsOnThePage();
        catalog.determiningRandomProduct();
        tryToAddProductToTheCartWithoutAuthorization();
    }
    @Test(retryAnalyzer = Retry.class) //3. Проверка оптовых цен, добавление товаров без авторизации из детальной страницы товара
    public void checkOptRicesThenAddingProductsWithoutAuthorizationFromDetailProductPage() {
        firsNavigationToB2B();
        navigationToCatalogTab();
        checkingThatThereAreNoOptPrices();
        catalog.determiningNumberOfProductsOnThePage();
        catalog.determiningRandomProduct();
        catalog.openDetailPageOfRandomProduct(catalog.randomProductNumberOnThePage);
        catalog.checkThatDetailPageOfProductIsOpened();
        checkingThatThereAreOnlyRetailPrice();
    }
    @Test(retryAnalyzer = Retry.class) //4. Вывод сообщения для неавторизованного пользователя
    public void outputMessageForUnauthorizedUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        driver.findElement(logInButtonOnTheAuthorizationTabLocator).click();
        navigationToBasicB2BSettings();
        enterMessageForUnauthorizedUsers();
        driver.findElement(buttonSaveLocator).click();
        driver.quit();
        flagForCloseWarningWindowThisIsTheFirstVisit = true;
        setUpSuite();
        navigationToMeanPageByUrl();
        checkingWarningOutputForUnauthorizedUsers();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        driver.findElement(logInButtonOnTheAuthorizationTabLocator).click();
        navigationToBasicB2BSettings();
        clearExistingMessage();
        driver.findElement(buttonSaveLocator).click();
        navigationToMeanPageByUrl();
        exitFromB2B();
    }
    @Test(retryAnalyzer = Retry.class) //5. Вывод заглушки при попытке войти в недоступные разделы  по прямой ссылке
    public void outputOfAStubWhenUserTryingToGoUnavailableSectionsByDirectLink() {
        //act
        navigationToCartByUrl();
        checkingThatThereIsAStub();
    }

}
