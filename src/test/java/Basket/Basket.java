package Basket;

import BaseActions.Retry;
import Catalog.MethodsForCatalog;
import org.testng.annotations.Test;

public class Basket extends MethodsForCatalog {
    @Test (retryAnalyzer = Retry.class)  //1. Проверка корректности удаления товаров в корзине
    public void checkCorrectnessOfDeletingProductsInTheCart() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        //act
        deletingProductsFromTheCart();
        refreshingThisPage();
        checkingThatThereAreNoProdutsInTheBasket();
    }
    @Test(retryAnalyzer = Retry.class) //2. Удаление и восстановление одного из товаров в корзине
    public void deletingAndRestoringOneOfTheProductsInTheShoppingCart() {
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
        //act
        navigationToCart();
        checkingThatThereAreTwoProductsInTheCart();
        deleteTheFirstProductFromTheCart();
        checkingThatThereAreOneActiveProductInTheCart();
        restoreTheFirstProductInTheCart();
        checkingThatThereAreTwoProductsInTheCart();
        deleteTheFirstProductFromTheCart();
        checkingThatInCartOnlyOneProduct();
    }
    @Test(retryAnalyzer = Retry.class) //3. Выделение всех товаров с помощью чек-бокса "выделить все" (все чекбоксы выделены)
    public void highlightingAllProductsUsingTheHighlightAllCheckbox() {
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
        //act
        navigationToCart();
        highlightsAllProductsInTheCart();
        checkingThatAllCheckboxesAreSelected();
    }
    @Test(retryAnalyzer = Retry.class) //4.  Загрузка товаров из Excel в корзине
    public void uploadCatalogInExcelInTheCart() {
        //arrange
        deletingExcelAndJpgFilesFromDownloads();
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        downloadingCatalogFromExcel("blank.xlsx");
        checkingThatThereAreTwoGefestGasStoveInTheCart();
    }
    @Test(retryAnalyzer = Retry.class) //5.  Добавления 10 рандомных дополнительных товаров
    public void addingTenRandomAdditionalProducts() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCart();
        addingAndCheckThatAdditionalProductIsAddedTenTimes();
    }
    @Test(retryAnalyzer = Retry.class) //6.  Фильтрация товаров в корзине по названию
    public void filteringProductsInTheBasketByName() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        refreshingThisPage();
        addingAndCheckThatAdditionalProductIsAddedTenTimes();
        addingToSearchFieldWordForSearchInTheCart();
        checkingThatAllProductsInTheCarContainsWordForSearch();
    }
    @Test(retryAnalyzer = Retry.class) //7.  Фильтрация дополнительных товаров в корзине по названию
    public void filteringAdditionalProductsInTheBasketByName() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCart();
        addingToSearchFieldWordForSearchInAdditionalProducts();
        checkingThatAllAdditionalProductsContainsWordForSearch();
    }
    @Test(retryAnalyzer = Retry.class) //8.  Выбор раздела для доп. товаров
    public void filteringAdditionalProductsBySections() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCart();
        selectRandomSection();
        checkThatAllProductsHaveThisWord();
    }
    @Test(retryAnalyzer = Retry.class) //9.  Поиск дополнительного товара по названию
    public void searchForAnAdditionalProductByName() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        //act
        navigationToCart();
        addingToSearchFieldWordForSearchAdditionalProductsGefestGasStove();
        checkingThatOnlyGefestGasStoveIsDisplayedInAdditionalProducts();
    }
    @Test(retryAnalyzer = Retry.class) //10. Переход в каталог из пустой корзины с помощью гиперссылки "Нажмите здесь"
    public void goToCatalogFromAnEmptyShoppingCartUsingTheHyperlinkClickHere() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        //act
        deletingProductsFromTheCart();
        navigationToCatalogUsingTheHyperlinkClickHere();
    }

    @Test(retryAnalyzer = Retry.class) //11.  Выбор раздела по части его названия
    public void selectSectionByPartOfItsName() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCart();
        selectingSectionByPartOfItsName();
        checkingThatSectionFieldIsEmpty();
        applyTheOutputOfThisSection();
        checkingThatAllAdditionalProductsBelongThisSection();
    }
}

