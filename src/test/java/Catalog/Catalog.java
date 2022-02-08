package Catalog;

import MakingOrders.MakingOrders;
import org.junit.Test;
import org.openqa.selenium.By;


public class Catalog extends MethodsForCatalog {
    @Test //1. Добавление товара в корзину
    public void addingProductToCart() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProduct();
        checkingThatCartIconHavePictureOfThePresenceOfOneProductInTheBasket(numberOfProductsInTheFooter);
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        checkingThatCartIconHavePictureOfThePresenceOfOneProductInTheBasket(numberOfProductsInTheFooter);
    }

    @Test //2. Добавление товара в корзину использую "+"
    public void addingProductToCartUsingIconPlus() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProductUsingIconPlus();
        checkingThatCartIconHavePictureOfThePresenceOfOneProductInTheBasket(numberOfProductsInTheFooter);
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        checkingThatCartIconHavePictureOfThePresenceOfOneProductInTheBasket(numberOfProductsInTheFooter);
    }

    @Test //3. Добавление товаров в корзину
    public void addingProductsToCart() {
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
        checkingThatCartIconHavePictureOfThePresenceOfOneProductInTheBasket(numberOfProductsInTheFooter);
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        checkingThatCartIconHavePictureOfThePresenceOfOneProductInTheBasket(numberOfProductsInTheFooter);
    }

    @Test //4. Добавление товаров в корзину
    public void addingProductsToCartUsingIconPlus() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProductUsingIconPlus();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        changeTheQuantityOfRandomProductUsingIconPlus();
        checkingThatCartIconHavePictureOfThePresenceOfOneProductInTheBasket(numberOfProductsInTheFooter);
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        checkingThatCartIconHavePictureOfThePresenceOfOneProductInTheBasket(numberOfProductsInTheFooter);
    }

    @Test //5. Добавление в корзину максимум   (равно наличию) товаров
    public void addingTheMaxNumberOfProductsToCart() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        addingTheMaxNumberOfProductsToTheCart();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        addingTheMaxNumberOfProductsToTheCart();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
    }

    @Test //6. Добавление в корзину максимум   (равно наличию) товаров, используя иконки увеличения ("+" "-")
    public void addingTheMaxNumberOfProductsToCartUsingIconPlus() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        addingTheMaxNumberOfProductsToTheCartUsingIconPlus();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        addingTheMaxNumberOfProductsToTheCartUsingIconPlus();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
    }

    @Test //7. Добавление в корзину товаров  на один больше чем есть в наличии
    public void addingTheMaxNumberPlusOneOfProductsToCart() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        addingTheMaxNumberOfProductsToTheCart();
        addingPlusOneToThisProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        addingTheMaxNumberOfProductsToTheCart();
        addingPlusOneToThisProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
    }

    @Test //8. Добавление в корзину товаров на один больше чем есть в наличии, используя иконки увеличения ("+" "-")
    public void addingTheMaxNumberPlusOneOfProductsToCartUsingIconPlus() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        addingTheMaxNumberOfProductsToTheCartUsingIconPlus();
        addingPlusOneToThisProductUsingIconPlus();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        addingTheMaxNumberOfProductsToTheCartUsingIconPlus();
        addingPlusOneToThisProductUsingIconPlus();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
    }

    @Test //9. Добавление товаров в корзину с разных страниц
    public void addingProductsToCartFromDifferentPages() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changingPageInCatalog();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        changingPageInCatalog();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
    }

    @Test //10. Добавление товаров в корзину с разных страниц, используя иконки увеличения ("+" "-")
    public void addingProductsToCartUsingIconPlusFromDifferentPages() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changingPageInCatalog();
        changeTheQuantityOfRandomProductUsingIconPlus();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        changingPageInCatalog();
        changeTheQuantityOfRandomProductUsingIconPlus();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
    }

    @Test //11. Удаление товаров с раздела "Каталог"
    public void deletingProductFromTheCatalog() {
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
        deletingLastAddedProductFromTheCatalog();
    }

    @Test //12. Удаление товаров с раздела "Каталог", используя иконки увеличения ("+" "-")
    public void deletingProductFromTheCatalogUsingIconMinus() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProductUsingIconPlus();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        changeTheQuantityOfRandomProductUsingIconPlus();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        deletingLastAddedProductFromTheCatalogUsingIconMinus();
    }

    @Test //13. Удаление товаров с раздела "Каталог" с разных страниц
    public void deletingProductFromTheCatalogFromTheDifferentPages() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changingPageInCatalog();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        changingPageInCatalog();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        navigationToThePageWithTheLastAddedProduct();
        deletingLastAddedProductFromTheCatalog();
    }

    @Test //14. Удаление товаров из корзины с раздела "Каталог" с разных страниц, используя иконки увеличения ("+" "-")
    public void deletingProductFromTheCatalogFromTheDifferentPagesUsingIconMinus() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changingPageInCatalog();
        changeTheQuantityOfRandomProductUsingIconPlus();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        changingPageInCatalog();
        changeTheQuantityOfRandomProductUsingIconPlus();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        navigationToThePageWithTheLastAddedProduct();
        deletingLastAddedProductFromTheCatalogUsingIconMinus();
    }

    @Test //15. Уменьшение кол-ва товаров  из раздела "Каталог" с разных страниц
    public void decreaseProductFromTheCatalogFromTheDifferentPages() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changingPageInCatalog();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        changingPageInCatalog();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        navigationToThePageWithTheLastAddedProduct();
        decreaseQuantitiesLastAddedProduct();
    }

    @Test //16. Уменьшение кол-ва товаров из раздела "Каталог" с разных страниц, используя иконки увеличения ("+" "-")
    public void decreaseProductFromTheCatalogFromTheDifferentPagesUsingIconMinus() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changingPageInCatalog();
        changeTheQuantityOfRandomProductUsingIconPlus();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        changingPageInCatalog();
        changeTheQuantityOfRandomProductUsingIconPlus();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        navigationToThePageWithTheLastAddedProduct();
        decreaseQuantitiesLastAddedProductUsingIconMinus(); //Не работает ожидание
    }

    @Test //17. Добавление товаров которых нет в наличии в корзину
    public void addingProductsWithoutAvailability() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        addingProductsThatAreOutOfStock();
    }

    @Test //18. Добавление товаров которых нет в наличии в корзину, используя иконки увеличения ("+" "-")
    public void addingProductsWithoutAvailabilityUsingIconPlus() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        addingProductsThatAreOutOfStockUsingIconPlus();
    }

    @Test //19.  Выгрузка каталога в Excel
    public void downloadCatalogToExcel() {
        //arrange
        deletingExcelAndJpgFilesFromDownloads();
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        downloadingCatalogToYourComputer();
    }

    @Test //20.  Загрузка товаров из Excel
    public void uploadCatalogInExcel() {
        //arrange
        deletingExcelAndJpgFilesFromDownloads();
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        downloadingCatalogFromExcel("blank.xlsx");
        navigationToCart();
        checkingThatThereAreTwoGefestGasStoveInTheCart();
    }

    @Test //21.  Загрузка товаров из Excel без выбора количества товаров
    public void uploadCatalogInExcelWithoutSelectingProducts() {
        //arrange
        deletingExcelAndJpgFilesFromDownloads();
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        downloadingCatalogFromExcel("emptyBlank.xlsx");
        checkThatMessageAboutEmptyExcelIsDisplayed();
    }

    @Test //22. Добавление товара в корзину из раздела "Добавить дополнительные товары", используя иконки увеличения ("+" "-")
    public void addingProductToCartFromTabAddAnAdditionalProductUsingIconPlus() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        navigationToCart();
        addingProductToCartFromTabAddAnAdditionalProduct();
        checkThatProductAddedFromTabAddAnAdditional();
    }

    @Test //23. Сортировка товаров по наличию (возрастанию)
    public void sortingProductsByIncreaseAvailability() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        sortsProductsByIncreaseAvailability();
        checkThatProductsAreSortedByIncreaseAvailability();
        changingPageInCatalog();
        checkThatProductsAreSortedByIncreaseAvailability();
        changingPageInCatalog();
        checkThatProductsAreSortedByIncreaseAvailability();
    }

    @Test //24. Сортировка товаров по наличию (убыванию)
    public void sortingProductsByDecreaseAvailability() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        sortsProductsByDecreaseAvailability();
        checkThatProductsAreSortedByDecreaseAvailability();
        changingPageInCatalog();
        checkThatProductsAreSortedByDecreaseAvailability();
        changingPageInCatalog();
        checkThatProductsAreSortedByDecreaseAvailability();
    }

    @Test //25. Сортировка товаров по цене (возрастанию)
    public void sortingProductsByIncreasePrice() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        sortsProductsByIncreasePrice();
        checkThatProductsAreSortedByIncreasePrice();
        changingPageInCatalog();
        checkThatProductsAreSortedByIncreasePrice();
        changingPageInCatalog();
        checkThatProductsAreSortedByIncreasePrice();
    }
    @Test //26. Сортировка товаров по цене (убыванию)
    public void sortingProductsByDecreasePrice() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        sortsProductsByDecreasePrice();
        checkThatProductsAreSortedByDecreasePrice();
        changingPageInCatalog();
        checkThatProductsAreSortedByDecreasePrice();
        changingPageInCatalog();
        checkThatProductsAreSortedByDecreasePrice();
    }
    @Test //27. Поиск названию рандомного товара
    public void searchingByWord() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        changingPageInCatalog();
        determineRandomProductOnPge();
        choiceWordForSearch();
        searchByWord();
    }

    @Test //28. Поиск по части названия рандомного товара
    public void searchingByPartOfWord() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        changingPageInCatalog();
        determineRandomProductOnPge();
        choicePartOfWordForSearch();
        searchByWord();
    }

    @Test //29. При выборе "Категории" выводятся соответсвующие товары
    public void outputOfProductsWhenSelectingACategory() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        rememberTheNumberOfProductsOnFirstPage();
        goToTheLastPage();
        rememberTheNumberOfPages();
        choosingRandomCategory();
        goToTheLastPage();
        checkThatTheQuantityOfPagesIsChanged();
        removeTheSelectionFromTheCatalogCategory();
        checkingThatAllProductsAreDisplayedAgain();
    }

    @Test //30. Фильтрация товаров по цене (максимальной)
    public void filteringProductsByMaxPrice() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        enteringTheMaxPriceIntoTheFilter();
        sortsProductsByDecreasePrice();
        checkThatMaxPriceHasBeenApplied();
        checkThatProductsAreSortedByDecreasePrice();
        changePageOnTheSecond();
        checkThatMaxPriceHasBeenApplied();
        checkThatProductsAreSortedByDecreasePrice();
        try {
            changingPageInCatalog();
        }
        catch (Exception e){
            System.out.println("Всего одна страница");
        }
        checkThatMaxPriceHasBeenApplied();
        checkThatProductsAreSortedByDecreasePrice();
    }

    @Test //31. Фильтрация товаров по цене (минимальной)
    public void filteringProductsByMinPrice() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        enteringTheMinPriceIntoTheFilter();
        sortsProductsByIncreasePrice();
        checkThatProductsAreSortedByIncreasePrice();
        checkThatMinPriceHasBeenApplied();
        changePageOnTheSecond();
        checkThatProductsAreSortedByIncreasePrice();
        checkThatMinPriceHasBeenApplied();
        try {
            changingPageInCatalog();
        }catch (Exception e){}
        checkThatProductsAreSortedByIncreasePrice();
        checkThatMinPriceHasBeenApplied();
    }

    @Test //32. Открытие детальной информации о товаре
    public void openingDetailedProductInformation() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changePageOnTheSecond();
        openingAllOffers();
        determiningNumberOfProductsOnThePage();
        determiningRandomNumberOfProducts();
        openDetailPageOfRandomProduct();
        checkThatDetailPageOfProductIsOpened();
        navigationToMeanPageByUrl();
    }

    @Test //33. Проверка работы "Модели работы" выгрузки каталога
    public void checkExcelUploadSettings() {
        //arrange
        deletingExcelAndJpgFilesFromDownloads();
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToCatalogTab();
        enableEditMode();
        navigationToComponentOfUnloadingTheCatalog();
        selectTheWorkModelAsUserConfigurable();
        selectRandomSectionToDownloadToTheCatalog();
        clickTheUploadButton();
        checkingThatCatalogIsDownloaded();
    }
    @Test //34.  Проверка корректности добавления в корзину после загрузки из EXCEL всего каталога
    public void addingAnItemToTheCartAfterDownloadingItFromExcel() {
        //arrange
        deletingExcelAndJpgFilesFromDownloads();
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        downloadingCatalogFromExcel("fullBlank.xlsx");
        navigationToCart();
        checkThatProductsAreDisplayedInCart();
        MakingOrders make = new MakingOrders();
        make.navigationToMakingOrderFromCart();
        navigationToMeanPageByUrl();
    }
    @Test //35. Сортировка товаров по алфавиту (возрастанию)
    public void sortingOfProductsAlphabeticallyIncrease() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        sortingOfProductsInAlphabeticalIncreasingOrder();
        checkingThatCatalogIsSortedByProductNamesIncrease();
        checkingThatURLContainsSORTAndASC();
        changingPageInCatalog();
        checkingThatCatalogIsSortedByProductNamesIncrease();
        checkingThatURLContainsSORTAndASC();
        changingPageInCatalog();
        checkingThatCatalogIsSortedByProductNamesIncrease();
        checkingThatURLContainsSORTAndASC();
    }
    @Test //36. Сортировка товаров по алфавиту (убыванию)
    public void sortingOfProductsAlphabeticallyDescending() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        sortingOfProductsInAlphabeticalDescendingOrder();
        checkingThatCatalogIsSortedByProductNamesDecrease();
        checkingThatURLContainsSORTAndDESC();
        changingPageInCatalog();
        checkingThatCatalogIsSortedByProductNamesDecrease();
        checkingThatURLContainsSORTAndDESC();
        changingPageInCatalog();
        checkingThatCatalogIsSortedByProductNamesDecrease();
        checkingThatURLContainsSORTAndDESC();
    }
    @Test //37. Проверка корректности пагинации
    public void checkingCorrectnessOfPagination() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        changePageOnTheSecond();
        checkingThatSelectedSecondPage();
        changePageOnTheFifth();
        checkingThatSelectedFifthPage();
    }
    @Test //38. Проверка корректности вывода разделов каталога (проверяя кол-во страниц каталога)
    public void checkingCorrectnessOfTheOutputOfSecondLevelDirectorySections() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        goToTheLastPage();
        rememberTheNumberOfPages();
        choiceTheSecondLevelCategory();
        goToTheLastPage();
        checkThatTheQuantityOfPagesIsChanged();
    }
    @Test //39. Проверка корректности вывода релевантных свойств фильтра
    public void checkingCorrectnessOfOutputOfRelevantFilterProperties() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        choosingRandomCategory();
        checkingThatQuantityOfPropertiesIsHadDecreased();
    }

    @Test //40. После удаления всех выбранных подкатегорий выводится весь каталог
          // (иногда падает, так как у некоторых категорий при удалении всех подкатегорий остаются выбранные подкатегории подкатегорий)
    public void outputOfCatalogCategorySubsections() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        rememberTheNumberOfProductsOnFirstPage();
        goToTheLastPage();
        rememberTheNumberOfPages();
        choosingRandomCategory();
        goToTheLastPage();
        checkThatTheQuantityOfPagesIsChanged();
        removeOutputOfAllSubcategories();
        checkingThatAllProductsAreDisplayedAgain();
    }
    @Test //41. Добавление товаров из карточки товара
    public void addingProductFromTheProductCard() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changePageOnTheSecond();
        openingAllOffers();
        determiningNumberOfProductsOnThePage();
        determiningRandomNumberOfProducts();
        openDetailPageOfRandomProduct();
        addingThisProductToTheBasket();
        navigationToMeanPageByUrl();
        navigationToCart();
        checkingThatThereIsSomeProductsInTheBasket();
    }
    @Test //42. Проверка корректности вывода разделов каталога (проверяя имена и кол-во выводимых товаров)
    public void checkingCorrectnessOfTheOutputDirectorySectionsUsingTheNamesAndNumberOfProducts() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        selectTheSectionWithGasStoves();
        checkingTheNamesOfTheOutputGasStoves();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        selectSectionWithChainsaws();
        checkingTheNamesOfTheOutputGasChainsaw();
        selectTheSectionWithGasStoves();
        checkingTheQuantityOfOutputGoodsWhenSelectingSubcategoriesChainsawsAndGasStoves();
    }
    @Test //43. Добавление товара в корзину из детальной поп-ап страници товара
    public void addingProductToTheCartFromTheDetailedPopUpProductPage() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changePageOnTheSecond();
        openingAllOffers();
        determiningNumberOfProductsOnThePage();
        determiningRandomNumberOfProducts();
        openDetailPageOfRandomProduct();
        addingProductFromPopUpDetailPage();
        navigationToMeanPageByUrl();
        checkingThatCartIconHavePictureOfThePresenceOfOneProductInTheBasket(1);
        navigationToCart();
        checkingThatThereIsProductInTheCartThatWasAddedFromTheDetailedProductPage();
    }
    @Test //43. фыв
    public void asd() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        driver.findElement(By.xpath("//*[contains(@href, 'personal/account')]")).click();
        driver.findElement(By.cssSelector(".btn-pay")).click();
        driver.findElement(By.cssSelector(".blank_invoices-pay_button")).click();

        explicitWaiting();explicitWaiting();explicitWaiting();explicitWaiting();explicitWaiting();explicitWaiting();explicitWaiting();explicitWaiting();explicitWaiting();explicitWaiting();
        System.out.println(driver.findElement(By.cssSelector(".bx-sap.blank_personal")).getText());



//        deletingProductsFromTheCart();
//        navigationToCatalogTab();
//        changePageOnTheSecond();
//        openingAllOffers();
//        determiningNumberOfProductsOnThePage();
//        determiningRandomNumberOfProducts();
//        openDetailPageOfRandomProduct();
//        addingProductFromPopUpDetailPage();
//        navigationToMeanPageByUrl();
//        checkingThatCartIconHavePictureOfThePresenceOfOneProductInTheBasket(1);
//        navigationToCart();
//        checkingThatThereIsProductInTheCartThatWasAddedFromTheDetailedProductPage();
    }





//    @Test
//    public void circle() {
//        for (int i = 0; i < 20; i++) {
//
//
//            checkingCorrectnessOfTheOutputOfSecondLevelDirectorySections();
//            //Сюда тест
//
//            productCounterInTheCart = 0;
//            pricesForAllProductsInTheFooter = 0;
//            sumOfPricesOfTheAddedProducts = 0;
//            exitFromB2B();
//
//        }
//    }
}





