package DangerousTests.MultiRegions;

import BaseActions.Retry;
import org.testng.annotations.Test;

public class MultiRegions extends MethodsForMultiRegions {
    @Test(retryAnalyzer = Retry.class) //1. Первый выбор города из списка мультирегиональности
    public void firstChoiceOfTheCityFromTheMultiRegionList() {
        if (isThereModuleMultiRegions) {
            //arrange
            flagForRegionThisIsTheFirstVisit = true;
            driver.quit();
            setUpSuite();
            isThereModuleMultiRegions = false;
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
    }

    @Test(retryAnalyzer = Retry.class) //2. Смена города из списка мультирегиональности
    public void changingCityFromTheMultiRegionList() {
        if (isThereModuleMultiRegions) {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        clickOnTheMultiRegion();
        choiceRandomCityFromSearchHint("Москва");
        deletingProductsFromTheCart();
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
    }

    @Test(retryAnalyzer = Retry.class) //3. Первый выбор города из поисковой подсказки
    public void firstChoiceOfTheCityFromTheSearchHint() {
        if (isThereModuleMultiRegions) {
            //arrange
            flagForRegionThisIsTheFirstVisit = true;
            driver.quit();
            setUpSuite();
            isThereModuleMultiRegions = false;
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
    }


    @Test(retryAnalyzer = Retry.class) //4. Смена города из поисковой подсказки
    public void changingCityFromTheSearchHint() {
        if (isThereModuleMultiRegions) {
            //arrange
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            clickOnTheMultiRegion();
            choiceRandomCityFromSearchHint("Москва");
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
    }

    @Test(retryAnalyzer = Retry.class) //5. Вывод остатков на складх в зависимости от выбранного региона (работа с местоположениями выключена)
    public void displayOfStockBalancesDependingOnTheSelectedRegionWorkingWithLocationsIsDisabled() {
        if (isThereModuleMultiRegions) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            clickOnTheMultiRegion();
            choiceRandomCityFromSearchHint("Москва");
            navigationToRegionSetting();
            turnOffWorkingWithLocations();
            returningSettingsBackIfCatalogBroken();
            deletingProductsFromTheCart();
            navigationToCatalogTab();
            turnOnEditMode();
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
            turnOffEditMode();
            selectTheSectionWithGasStoves();
            enterTheMaximumAvailableQuantityOfThisProduct();
            checkingThatTheTotalNumberOfOutputProductsAndQuantityStoragesIsEqualToThePreviouslyEnteredData();
            clickOnTheMultiRegion();
            choiceRandomCityFromSearchHint(nameRandomCity);
            checkingThatTheTotalNumberOfOutputProductsIsEqualToThePreviouslyEnteredDataInTestStorage();
            attemptToAddMoreItemsThanIsAtTheStorage();
            navigationToComponentOfCatalogSetting();
            showingTheQuantityOfProductsInStorage();
            turnOffEditMode();
        }
    }
    @Test(retryAnalyzer = Retry.class) //6. Вывод остатков на складх в зависимости от выбранного региона (работа с местоположениями включена)
    public void displayOfStockBalancesDependingOnTheSelectedRegionWorkingWithLocationsIsEnabled() {
        if (isThereModuleMultiRegions) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            clickOnTheMultiRegion();
            choiceRandomCityFromSearchHint("Москва");
            navigationToRegionSetting();
            turnOnWorkingWithLocations();
            returningSettingsBackIfCatalogBroken();
            deletingProductsFromTheCart();
            navigationToCatalogTab();
            turnOnEditMode();
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
            turnOffEditMode();
            selectTheSectionWithGasStoves();
            enterTheMaximumAvailableQuantityOfThisProduct();
            checkingThatTheTotalNumberOfOutputProductsAndQuantityStoragesIsEqualToThePreviouslyEnteredData();
            clickOnTheMultiRegion();
            choiceRandomCityFromSearchHint(nameRandomCity);
            checkingThatTheTotalNumberOfOutputProductsIsEqualToThePreviouslyEnteredDataInTestStorage();
            attemptToAddMoreItemsThanIsAtTheStorage();
            navigationToComponentOfCatalogSetting();
            showingTheQuantityOfProductsInStorage();
            turnOffEditMode();
        }
    }

    @Test(retryAnalyzer = Retry.class) //7. Вывод типов цен в зависимости от региона (работа с местоположениями включена)
    public void outputOfPriceTypesDependingOnTheRegionWorkingWithLocationsIsEnabled() {
        if (isThereModuleMultiRegions) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            clickOnTheMultiRegion();
            choiceRandomCityFromSearchHint("Москва");
            deletingProductsFromTheCart();
            returningSettingsBackIfCatalogBroken();
            navigationToRegionSetting();
            turnOnWorkingWithLocations();
            openRandomRegionInAdminPart();
            unselectAllTypesOfPricesAgainstBase();
            choiceSmallOptTypePrice();
            driver.findElement(buttonSaveLocator).click();
            navigationToGasStoveSetting();
            setOptPricesForOurssonGasStove();
            navigationToMeanPageByUrl();
            selectTheSectionWithGasStoves();
            findNumberOfGasStoveInCatalogInCatalog("Плита Oursson");
            checkingThatAllOptPricesAreDisplayed();
            checkingThatAllOptPricesAreDisplayed();
            addingOurssonGasStoveToTheCart();
            checkingThatAffordablePriceIsEqualsOptPrice();
            clickOnTheMultiRegion();
            choiceRandomCityFromSearchHint(nameRandomCity);
            checkingThatOptPriceIsNotDisplayed();
            checkingThatSmallOptPriceIsDisplayed();
            checkingThatAffordablePriceIsEqualsSmallOpt();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            deletingProductsFromTheCart();
            clickOnTheMultiRegion();
            choiceRandomCityFromSearchHint("Москва");
            selectTheSectionWithGasStoves();
            findNumberOfGasStoveInCatalogInCatalog("Плита Oursson");
            checkingThatAllOptPricesAreDisplayed();
            checkingThatAllOptPricesAreDisplayed();
            addingOurssonGasStoveToTheCart();
            checkingThatAffordablePriceIsEqualsOptPrice();
            clickOnTheMultiRegion();
            choiceRandomCityFromSearchHint(nameRandomCity);
            checkingThatOptPriceIsNotDisplayed();
            checkingThatSmallOptPriceIsDisplayed();
            checkingThatAffordablePriceIsEqualsSmallOpt();
        }
    }
    @Test(retryAnalyzer = Retry.class) //8. Вывод типов цен в зависимости от региона (работа с местоположениями выключена)
    public void outputOfPriceTypesDependingOnTheRegionWorkingWithLocationsIsDisabled() {
        if (isThereModuleMultiRegions) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            clickOnTheMultiRegion();
            choiceRandomCityFromSearchHint("Москва");
            deletingProductsFromTheCart();
            returningSettingsBackIfCatalogBroken();
            navigationToRegionSetting();
            turnOffWorkingWithLocations();
            openRandomRegionInAdminPart();
            unselectAllTypesOfPricesAgainstBase();
            choiceSmallOptTypePrice();
            driver.findElement(buttonSaveLocator).click();
            navigationToGasStoveSetting();
            setOptPricesForOurssonGasStove();
            navigationToMeanPageByUrl();
            selectTheSectionWithGasStoves();
            findNumberOfGasStoveInCatalogInCatalog("Плита Oursson");
            checkingThatAllOptPricesAreDisplayed();
            checkingThatAllOptPricesAreDisplayed();
            addingOurssonGasStoveToTheCart();
            checkingThatAffordablePriceIsEqualsOptPrice();
            clickOnTheMultiRegion();
            choiceRandomCityFromSearchHint(nameRandomCity);
            checkingThatOptPriceIsNotDisplayed();
            checkingThatSmallOptPriceIsDisplayed();
            checkingThatAffordablePriceIsEqualsSmallOpt();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            deletingProductsFromTheCart();
            clickOnTheMultiRegion();
            choiceRandomCityFromSearchHint("Москва");
            selectTheSectionWithGasStoves();
            findNumberOfGasStoveInCatalogInCatalog("Плита Oursson");
            checkingThatAllOptPricesAreDisplayed();
            checkingThatAllOptPricesAreDisplayed();
            addingOurssonGasStoveToTheCart();
            checkingThatAffordablePriceIsEqualsOptPrice();
            clickOnTheMultiRegion();
            choiceRandomCityFromSearchHint(nameRandomCity);
            checkingThatOptPriceIsNotDisplayed();
            checkingThatSmallOptPriceIsDisplayed();
            checkingThatAffordablePriceIsEqualsSmallOpt();
        }
    }

}
