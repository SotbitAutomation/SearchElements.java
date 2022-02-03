package Basket;

import Catalog.MethodsForCatalog;
import org.junit.Test;

public class Basket extends MethodsForCatalog {
    @Test //1. Проверка корректности удаления товаров в корзине
    public void checkCorrectnessOfDeletingProductsInTheCart() {
        //arrange
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
        driver.navigate().refresh();
        checkingThatThereAreNoProdutsInTheBasket();
    }
    @Test //2. Удаление и восстановление одного из товаров в корзине
    public void deletingAndRestoringOneOfTheProductsInTheShoppingCart() {
        //arrange
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
        navigationToCart();
        checkingThatThereAreTwoProductsInTheCart();
        deleteTheFirstProductFromTheCart();
        checkingThatThereAreOneProductsInTheCart();
        restoreTheFirstProductInTheCart();
        checkingThatThereAreTwoProductsInTheCart();
    }
    @Test //3. Выделение всех товаров с помощью чек-бокса "выделить все" (все чекбоксы выделены)
    public void highlightingAllProductsUsingTheHighlightAllCheckbox() {
        //arrange
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
        navigationToCart();
        driver.findElement(checkboxThatHighlightsAllProductsInTheCartLocator).click();
        checkingThatAllCheckboxesAreSelected();
    }
    @Test //4.  Загрузка товаров из Excel в корзине
    public void uploadCatalogInExcelInTheCart() {
        //arrange
        deletingExcelAndJpgFilesFromDownloads();
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        uploadingExcelCatalog("blank.xlsx");
        checkingThatThereAreTwoGefestGasStoveInTheCart();
    }
    @Test //5.  Добавления 10 рандомных дополнительных товаров
    public void addingTenRandomAdditionalProducts() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCart();
        addingAndCheckThatAdditionalProductIsAddedTenTimes();
    }
    @Test //6.  Фильтрация товаров в корзине по названию
    public void filteringProductsInTheBasketByName() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        driver.navigate().refresh();
        addingAndCheckThatAdditionalProductIsAddedTenTimes();
        addingToSearchFieldWordForSearchInTheCart();
        checkingThatAllProductsInTheCarContainsWordForSearch();
    }
    @Test //7.  Фильтрация дополнительных товаров в корзине по названию
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
    @Test //8.  Выбор раздела для доп. товаров
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
    @Test //9.  Поиск дополнительного товара по названию
    public void searchForAnAdditionalProductByName() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        //act
        navigationToCart();
        addingToSearchFieldWordForSearchInAdditionalProductsGefestGasStove();
        checkingThatOnlyGefestGasStoveIsDisplayedInAdditionalProducts();
    }
    @Test //10. Переход в каталог из пустой корзины с помощью гиперссылки "Нажмите здесь"
    public void goToCatalogFromAnEmptyShoppingCartUsingTheHyperlinkClickHere() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        //act
        deletingProductsFromTheCart();
        navigationToCatalogUsingTheHyperlinkClickHere();
    }

    @Test //11.  Выбор раздела по части его названия
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

