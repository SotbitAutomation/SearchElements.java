package DangerousTests.MultiRegions;

import BaseActions.Retry;
import org.testng.annotations.Test;

public class MultiRegions extends MethodsForMultiRegions {
    @Test(retryAnalyzer = Retry.class) //1. Первый выбор города из списка мультирегиональности
    public void firstChoiceOfTheCityFromTheMultiRegionList() {
        //arrange
        isThereMultiRegions = false;
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        //act
        clickButtonNo();
        choiceRandomCityFromList();
        checkingThatChosenCityIsDisplayed();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProduct();
        checkingThatChosenCityIsDisplayed();
        navigationToCart();
        checkingThatChosenCityIsDisplayed();
        make.navigationToMakingOrderFromCart();
        checkingThatChosenCityIsDisplayed();
    }

    @Test(retryAnalyzer = Retry.class) //2. Смена города из списка мультирегиональности
    public void changingCityFromTheMultiRegionList() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        //act
        refreshingThisPage();
        clickOnTheMultiRegion();
        choiceRandomCityFromList();
        checkingThatChosenCityIsDisplayed();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProduct();
        checkingThatChosenCityIsDisplayed();
        navigationToCart();
        checkingThatChosenCityIsDisplayed();
        make.navigationToMakingOrderFromCart();
        checkingThatChosenCityIsDisplayed();
    }

    @Test(retryAnalyzer = Retry.class) //3. Первый выбор города из поисковой подсказки
    public void firstChoiceOfTheCityFromTheSearchHint() {
        //arrange
        isThereMultiRegions = false;
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        //act
        clickButtonNo();
        choiceNameRandomCityFromList();
        choiceRandomCityFromSearchHint(nameRandomCity);
        checkingThatChosenCityIsDisplayed();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProduct();
        checkingThatChosenCityIsDisplayed();
        navigationToCart();
        checkingThatChosenCityIsDisplayed();
        make.navigationToMakingOrderFromCart();
        checkingThatChosenCityIsDisplayed();
    }


    @Test(retryAnalyzer = Retry.class) //4. Смена города из поисковой подсказки
    public void changingCityFromTheSearchHint() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        //act
        refreshingThisPage();
        clickOnTheMultiRegion();
        choiceNameRandomCityFromList();
        choiceRandomCityFromSearchHint(nameRandomCity);
        checkingThatChosenCityIsDisplayed();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProduct();
        checkingThatChosenCityIsDisplayed();
        navigationToCart();
        checkingThatChosenCityIsDisplayed();
        make.navigationToMakingOrderFromCart();
        checkingThatChosenCityIsDisplayed();
    }

    @Test(retryAnalyzer = Retry.class) //5. Вывод остатков на складх в зависимости от выбранного региона
    public void displayOfStockBalancesDependingOnTheSelectedRegion() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        ternOnEditMode();
        navigationToComponentOfCatalogSetting();
        configureTheOutputOfAllStoragesToTheCatalog();
        showTheQuantityOfProductsInStorageIfItIsNotShowed();
        openRandomRegionInAdminPart();
        unselectAllStorages();
        selectShowingOnlyOneTESTStorage();
        navigationToGasStoveSetting();
        returningQuantitativeAccountingAtTheGefestGasStoveByDefault();
        addingSecondStorageIfItNeed();
        navigationToGasStoveSetting();
        addingProductsToTheStorages();
        navigationToMeanPageByUrl();
        ternOffEditMode();
        selectTheSectionWithGasStoves();
        enterTheMaximumAvailableQuantityOfThisProduct();
        checkingThatTheTotalNumberOfOutputProductsAndQuantityStoragesIsEqualToThePreviouslyEnteredData();
        clickOnTheMultiRegion();
        choiceRandomCityFromSearchHint(nameRandomCity);
        checkingThatTheTotalNumberOfOutputProductsIsEqualToThePreviouslyEnteredDataInTestStorage();
        attemptToAddMoreItemsThanIsAtTheStorage();
        navigationToComponentOfCatalogSetting();
        showingTheQuantityOfProductsInStorage();
    }

}
