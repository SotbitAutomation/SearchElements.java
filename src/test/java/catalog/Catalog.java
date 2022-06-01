package catalog;

import base_actions.Retry;
import making_orders.MakingOrders;
import setting_up_cabinet_for_testing.SettingUpCabinetForTesting;
import org.testng.annotations.Test;


public class Catalog extends MethodsForCatalog {
    @Test(retryAnalyzer = Retry.class) //1. Добавление товара в корзину
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

    @Test(retryAnalyzer = Retry.class) //2. Добавление товара в корзину использую "+"
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

    @Test(retryAnalyzer = Retry.class) //3. Добавление товаров в корзину
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

    @Test(retryAnalyzer = Retry.class) //4. Добавление товаров в корзину
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

    @Test(retryAnalyzer = Retry.class) //5. Добавление в корзину максимум   (равно наличию) товаров
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

    @Test(retryAnalyzer = Retry.class) //6. Добавление в корзину максимум   (равно наличию) товаров, используя иконки увеличения ("+" "-")
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

    @Test(retryAnalyzer = Retry.class) //7. Добавление в корзину товаров  на один больше чем есть в наличии
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

    @Test(retryAnalyzer = Retry.class) //8. Добавление в корзину товаров на один больше чем есть в наличии, используя иконки увеличения ("+" "-")
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

    @Test(retryAnalyzer = Retry.class) //9. Добавление товаров в корзину с разных страниц
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

    @Test(retryAnalyzer = Retry.class) //10. Добавление товаров в корзину с разных страниц, используя иконки увеличения ("+" "-")
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

    @Test(retryAnalyzer = Retry.class) //11. Удаление товаров с раздела "Каталог"
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

    @Test(retryAnalyzer = Retry.class) //12. Удаление товаров с раздела "Каталог", используя иконки увеличения ("+" "-")
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

    @Test(retryAnalyzer = Retry.class) //13. Удаление товаров с раздела "Каталог" с разных страниц
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

    @Test(retryAnalyzer = Retry.class) //14. Удаление товаров из корзины с раздела "Каталог" с разных страниц, используя иконки увеличения ("+" "-")
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

    @Test(retryAnalyzer = Retry.class) //15. Уменьшение кол-ва товаров  из раздела "Каталог" с разных страниц
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

    @Test(retryAnalyzer = Retry.class) //16. Уменьшение кол-ва товаров из раздела "Каталог" с разных страниц, используя иконки увеличения ("+" "-")
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

    @Test(retryAnalyzer = Retry.class) //17. Добавление товаров которых нет в наличии в корзину
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

    @Test(retryAnalyzer = Retry.class) //18. Добавление товаров которых нет в наличии в корзину, используя иконки увеличения ("+" "-")
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

    @Test(retryAnalyzer = Retry.class) //19.  Выгрузка каталога в Excel
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

    @Test(retryAnalyzer = Retry.class) //20.  Загрузка товаров из Excel
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

    @Test(retryAnalyzer = Retry.class) //21.  Загрузка товаров из Excel без выбора количества товаров
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

    @Test(retryAnalyzer = Retry.class) //22. Добавление товара в корзину из раздела "Добавить дополнительные товары", используя иконки увеличения ("+" "-")
    public void addingProductToCartFromTabAddAnAdditionalProductUsingIconPlus() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        navigationToCart();
        addingProductToCartFromTabAddAnAdditionalProduct(randomNumberUpToFife);
        checkThatProductAddedFromTabAddAnAdditional();
    }

    @Test(retryAnalyzer = Retry.class) //23. Сортировка товаров по наличию (возрастанию)
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

    @Test(retryAnalyzer = Retry.class) //24. Сортировка товаров по наличию (убыванию)
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

    @Test(retryAnalyzer = Retry.class) //25. Сортировка товаров по цене (возрастанию)
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
    @Test(retryAnalyzer = Retry.class) //26. Сортировка товаров по цене (убыванию)
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
    @Test(retryAnalyzer = Retry.class) //27. Поиск названию рандомного товара
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
        enterNameInTheSearchFieldOnTheCatalogTabByWord(fieldForSearchInCatalogLocator, wordForSearch);
        searchByWord();
    }

    @Test(retryAnalyzer = Retry.class) //28. Поиск по части названия рандомного товара
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
        enterPartOfNameInTheSearchFieldOnTheCatalogTab(fieldForSearchInCatalogLocator, wordForSearch);
        searchByWord();
    }

    @Test(retryAnalyzer = Retry.class) //29. При выборе "Категории" выводятся соответсвующие товары
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
//        removeTheSelectionFromTheCatalogCategory();
//        checkingThatAllProductsAreDisplayedAgain();
    }

    @Test(retryAnalyzer = Retry.class) //30. Фильтрация товаров по цене (максимальной)
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
        changingPageInCatalog();
        checkThatMaxPriceHasBeenApplied();
        checkThatProductsAreSortedByDecreasePrice();
    }

    @Test(retryAnalyzer = Retry.class) //31. Фильтрация товаров по цене (минимальной)
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
        changingPageInCatalog();
        checkThatProductsAreSortedByIncreasePrice();
        checkThatMinPriceHasBeenApplied();
    }

    @Test(retryAnalyzer = Retry.class) //32. Открытие детальной информации о товаре
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
        determiningRandomProduct();
        openDetailPageOfRandomProduct(randomProductNumberOnThePage);
        checkThatDetailPageOfProductIsOpened();
        navigationToMeanPageByUrl();
    }

    @Test(retryAnalyzer = Retry.class) //33. Проверка работы "Модели работы" выгрузки каталога
    public void checkExcelUploadSettings() {
        //arrange
        deletingExcelAndJpgFilesFromDownloads();
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        resetCache();
        navigationToCatalogTab();
        turnOnEditMode();
        navigationToComponentOfUnloadingTheCatalog();
        selectTheWorkModelAsUserConfigurable();
        selectRandomSectionToDownloadToTheCatalog();
        clickTheUploadButton();
        checkingThatCatalogIsDownloaded(".xlsx");
        turnOffEditMode();
    }
    @Test(retryAnalyzer = Retry.class) //34.  Проверка корректности добавления в корзину после загрузки из EXCEL всего каталога
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
    @Test(retryAnalyzer = Retry.class) //35. Сортировка товаров по алфавиту (возрастанию)
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
    @Test(retryAnalyzer = Retry.class) //36. Сортировка товаров по алфавиту (убыванию)
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
    @Test(retryAnalyzer = Retry.class) //37. Проверка корректности пагинации
    public void checkingCorrectnessOfPagination() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToClothesSection();
        changePageOnTheSecond();
        checkingThatSelectedSecondPage();
        changePageOnTheFourth();
        checkingThatSelectedFourthPage();
    }
    @Test(retryAnalyzer = Retry.class) //38. Проверка корректности вывода разделов каталога (проверяя кол-во страниц каталога)
    public void checkingCorrectnessOfTheOutputOfSecondLevelDirectorySections() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        goToTheLastPage();
        rememberTheNumberOfPages();
        choiceTheSecondLevelCategoryInABlackHatOrTheFirstLevelInAWhiteHat();
    }
    @Test(retryAnalyzer = Retry.class) //39. Проверка корректности вывода релевантных свойств фильтра
    public void checkingCorrectnessOfOutputOfRelevantFilterProperties() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        choosingRandomCategory();
        rememberTheNumberOfProductsOnFirstPage();
        goToTheLastPage();
        choiceRandomProperty();
        checkingThatQuantityItemsIsDecreased();
        checkingThatQuantityOfPropertiesIsHadDecreased();
    }

    @Test(retryAnalyzer = Retry.class) //40. После удаления всех выбранных подкатегорий выводится весь каталог
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
    @Test(retryAnalyzer = Retry.class) //41. Добавление товаров из карточки товара
    public void addingProductFromTheProductCard() {
        SettingUpCabinetForTesting set = new SettingUpCabinetForTesting();
        set.clearAllCacheForTests();
        flagForRegionThisIsTheFirstVisit = true;
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
        determiningRandomProduct();
        openDetailPageOfRandomProduct(randomProductNumberOnThePage);
        addingThisProductToTheBasket();
        navigationToMeanPageByUrl();
        navigationToCart();
        checkingThatThereIsSomeProductsInTheBasket();
    }
    @Test(retryAnalyzer = Retry.class) //42. Проверка корректности вывода разделов каталога (проверяя имена и кол-во выводимых товаров)
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
    @Test(retryAnalyzer = Retry.class) //43. Добавление товара в корзину из детальной поп-ап страници товара
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
        determiningRandomProduct();
        openDetailPageOfRandomProduct(randomProductNumberOnThePage);
        addingProductFromPopUpDetailPage();
        navigationToMeanPageByUrl();
        checkingThatCartIconHavePictureOfThePresenceOfOneProductInTheBasket(1);
        navigationToCart();
        checkingThatThereIsProductInTheCartThatWasAddedFromTheDetailedProductPage();
    }
    @Test(retryAnalyzer = Retry.class) //44. Добавление денег на личный счет
    public void addingMoneyToAPersonalAccount() {
        //arrange
        registr.registrationIPWithManualEntryINN();
        //act
        navigationToPersonalAccountTab();
        sendRequestToTopUpYourPersonalAccountForOneHundredRubles();
        checkingConfirmInformationThatTheRequestIsSent();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        resetCache();
        navigationToOrdersPageInAdminPart();
        checkingThatTheRequestForReplenishmentOfThePersonalAccountIsDisplayedByTheAdmin(tempValue);
        navigationToPageForAddingPersonalAccountInAdminPart();
        addMoneyToTheUserSPersonalAccount(registr.theSameEmail);
        navigationToAuthorizationTab();
        registr.fillingFieldsOnTheLogInTab();
        logInToB2B();
        navigationToPersonalAccountTab();
        checkingThatTheUserHasBeenAddedMoneyToHisPersonalAccount();
    }

    @Test(retryAnalyzer = Retry.class) //45. Добавление товара в корзину из поисковой подсказки
    public void addingAnItemToTheShoppingCartFromASearchHint() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changingPageInCatalog();
        determineRandomProductOnPge();
        choiceWordForSearch();
        enterNameInTheSearchFieldOnTheCatalogTab(wordForSearch);
        addingThisProductFromPopUpWindowToTheCart();
        navigationToCart();
        checkingThatThisProductWasAddedToTheCart();
    }

    @Test(retryAnalyzer = Retry.class) //46. Добавление товара в корзину из поисковой подсказки с доступным количеством 0 (кол-ый учет у каталога - выкл)
    public void addingAnItemToTheCartFromASearchHintWithAnAvailableQuantityOfZeroAndTheNumberOfCatalogAccountsIsOn() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        enterNameInTheSearchFieldOnTheCatalogTab("Городской велосипед STELS Navigator");
        checkingThatThereAreNoCartIconInPupOpWindow();
    }
    @Test(retryAnalyzer = Retry.class) //47. Добавление товара в корзину при раскрытом каталоге во весь экран
    public void addingAnItemToTheCartWhenTheCatalogIsOpenedInFullScreen() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        openCatalogInFullScreen();
        checkingThatCatalogIsOpenToFullScreen();
        changeTheQuantityOfRandomProduct();
        closeCatalogInFullScreen();
        checkingThatCartIconHavePictureOfThePresenceOfOneProductInTheBasket(numberOfProductsInTheFooter);
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        checkingThatCartIconHavePictureOfThePresenceOfOneProductInTheBasket(numberOfProductsInTheFooter);
    }
    @Test(retryAnalyzer = Retry.class) //48. Добавление товара в корзину при раскрытом каталоге во весь экран из детальной страницы товара
    public void addingAnItemToTheCartWhenTheCatalogIsOpenedInFullScreenFromTheDetailedProductPage() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        openCatalogInFullScreen();
        checkingThatCatalogIsOpenToFullScreen();
        determiningNumberOfProductsOnThePage();
        determiningRandomProduct();
        openDetailPageOfRandomProduct(randomProductNumberOnThePage);
        checkThatDetailPageOfProductIsOpened();
        addingProductFromPopUpDetailPage();
        navigationToMeanPageByUrl();
        checkingThatCartIconHavePictureOfThePresenceOfOneProductInTheBasket(1);
        navigationToCart();
        checkingThatThereIsProductInTheCartThatWasAddedFromTheDetailedProductPage();
    }
    @Test(retryAnalyzer = Retry.class) //49. Добавление первого и последнего товара в корзину при раскрытом каталоге во весь экран из детальной страницы товара
    public void addingFirstAndLastItemToTheCartWhenTheCatalogIsOpenedInFullScreenFromTheDetailedProductPage() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        openCatalogInFullScreen();
        checkingThatCatalogIsOpenToFullScreen();
        determiningNumberOfProductsOnThePage();
        openDetailPageOfRandomProduct(1);
        checkThatDetailPageOfProductIsOpened();
        addingProductFromPopUpDetailPage();
        navigationToMeanPageByUrl();
        checkingThatCartIconHavePictureOfThePresenceOfOneProductInTheBasket(1);
        navigationToCart();
        checkingThatThereIsProductInTheCartThatWasAddedFromTheDetailedProductPage();
        navigationToCatalogTab();
        openCatalogInFullScreen();
        openDetailPageOfRandomProduct(numberOfProductsPerPage);
        checkThatDetailPageOfProductIsOpened();
        addingProductFromPopUpDetailPage();
        navigationToMeanPageByUrl();
        checkingThatCartIconHavePictureOfThePresenceOfOneProductInTheBasket(2);
        navigationToCart();
        checkingThatThereIsProductInTheCartThatWasAddedFromTheDetailedProductPage();
    }
    @Test(retryAnalyzer = Retry.class) //50. Удаление/ восстановление  товаров в разделе "Корзина"
    public void removingRestoringProductsFromTheCartSection() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProduct();
        changeTheQuantityOfRandomProduct();
        changeTheQuantityOfRandomProduct();
        changeTheQuantityOfRandomProduct();
        navigationToCart();
        choiceRandomProductInTheCart();
        checkingThatSuchNumberOfSelectedProductsAppearedInTheBasket(1);
        deletingSelectedProducts();
        checkingThatTheAmountOfPricesHasBeenRecalculatedTakingIntoAccountTheDeletedItem();
        restoreJustDeletedItemInTheCart();
        calculationOfAllPricesOfGoodsInTheBasket();
        checkingThatCurrentSumOfAllPricesInTheCartIsEqualsCalculatedSum(calculatedSumOfAllPricesInTheCart);
        choiceRandomProductInTheCart();
        deletingSelectedProducts();
        checkingThatTheAmountOfPricesHasBeenRecalculatedTakingIntoAccountTheDeletedItem();
        refreshingThisPage();
        checkingThatProductWasDeleted(quantityItemsInTheCart);
        calculationOfAllPricesOfGoodsInTheBasket();
        checkingThatCurrentSumOfAllPricesInTheCartIsEqualsCalculatedSum(calculatedSumOfAllPricesInTheCart);
        refreshingThisPage();
        chooseAllProductInTheCart();
        checkingThatCheckboxThatAllProductsSelectedIsDisplayed();
        choiceRandomProductInTheCart();
        checkingThatCheckboxThatAllProductsSelectedIsNotDisplayed();
        refreshingThisPage();
        selectAllItemsInTheCart();
        checkingThatSuchNumberOfSelectedProductsAppearedInTheBasket(quantityItemsInTheCart);



    }





//    @Test
//    public void circle() {
//        for (int i = 0; i < 5; i++) {
//            CustomizingForYourself cust = new CustomizingForYourself();
//
//            //Сюда тест
//            decreaseProductFromTheCatalogFromTheDifferentPagesUsingIconMinus();
//
//            numberOfProductsInTheFooter = 0;
//            pricesForAllProductsInTheFooter = 0;
//            sumOfPricesOfTheAddedProducts = 0;
//        }
//    }
}





