package DangerousTests;

import BaseActions.Retry;
import BeforeTest.SettingUpAutotestsForB2BSettings;
import MyOrdersHistory.MethodsForMyOrders;
import OrganizationsWithExtendedVersion.MethodsForAddingOrganizationsWithExtendedVersion;
import SettingUpCabinetForTesting.SettingUpCabinetForTesting;
import org.testng.annotations.Test;

public class DangerousTests extends MethodsForDangerousTests {
    @Test(retryAnalyzer = Retry.class)
    //0. Возвращение настроек назад (количественный учет в каталоге, а в товаре - по умолчанию; откл вывод складов)
    public void a_returningSettingsBack() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToSystemSettings();
        disableOutputOfPropertiesInTheDirectory();
        navigationToSettingOfQuantitativeAccountingForTheProduct();
        turnOnStoreAccounting();
        enableQuantitativeAccountingForTheProductsInCatalog();
        navigationToGasStoveSetting();
        returningQuantitativeAccountingAtTheGefestGasStoveByDefault();
        returningTheKaiserGasStoveSettingByDefault();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        tryTurnOffShowTheQuantityOfProductsInStorage();
        navigationToComponentOfCatalogSetting();
        choiceStandardCatalog();
        ternOffEditMode();
        SettingUpCabinetForTesting set = new SettingUpCabinetForTesting();
        set.clearAllCacheForTests();
    }

    @Test(retryAnalyzer = Retry.class)
    //1. Вывод свойст товаров в каталог с помощью "параметра свойств в компонентах и формах"
    public void checkingDisplaySettingsOfProductPropertiesInTheCatalog() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        navigationToSystemSettings();
        enableOutputOfPropertiesInTheDirectory();
        navigationToGasStoveSetting();
        addRegionPropertyToThisProduct();
        configureOutputOfThisPropertyToTheCatalog();
        navigationToMeanPageByUrl();
        resetCache();
        navigationToCatalogTab();
        resetCache();
        selectTheSectionWithGasStoves();
        checkingThatTheColumnWithThisPropertyHasAppearedInTheCatalog();
        checkingThatTheProductHasAPropertyWithThePreviouslyEnteredValue();
    }

    @Test(retryAnalyzer = Retry.class) //2. Проверка настройки отображения свойств товаров в каталоге (колонках)
    public void checkingDisplaySettingsOfProductPropertiesInTheList() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        navigationToSystemSettings();
        disableOutputOfPropertiesInTheDirectory();
        navigationToMeanPageByUrl();
        ternOnEditMode();
        navigationToCatalogTab();
        navigationToComponentOfCatalogSetting();
        removeAllColumnsWithPropertiesFromTheCatalog();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        navigationToComponentOfCatalogSetting();
        selectFewPropertiesToOutputToTheCatalog();
        checkingThatSelectedPropertiesAreDisplayedInTheCatalog();
    }

    @Test(retryAnalyzer = Retry.class)
    //3. Проверка вывода цен small Opt в каталог, возможность добавления товара по этой цене
    public void checkingTheDisplaySettingsForThePricesOfProductsInTheCatalog() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        chooseToDisplaySmallOptPricesForAllUsers();
        navigationToGasStoveSetting();
        setPriceForSmallOptForGasStoveGefest();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        selectTheSectionWithGasStoves();
        checkingThatTheGefestGasStoveHasTheSmallOptPrice();
        addingGefestGasStoveToTheCart();
        checkingThatThePriceInTheCatalogFooterIsDisplayedAsForSmallOptGroup();
    }

    @Test(retryAnalyzer = Retry.class)
    //4. Добавление в корзину товаров на один больше чем есть в наличии, c включенными кол-ыми учетоми
    public void addingTheMaxNumberPlusOneOfProductsToCartUsingIconPlusExpectedFail() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        navigationToSettingOfQuantitativeAccountingForTheProduct();
        turnOffStoreAccounting();
        enableQuantitativeAccountingForTheProductsInCatalog();
        navigationToGasStoveSetting();
        enableQuantitativeAccountingAtTheGefestGasStove();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        selectTheSectionWithGasStoves();
        enterTheMaximumAvailableQuantityOfThisProduct();
        addThisProductOneMoreTimeUsingPlusIcon();
        checkingThatPriceAndQuantityAreTheSame();
        addThisProductOneMoreTimeManually();
        checkingThatPriceAndQuantityAreTheSame();
    }

    @Test(retryAnalyzer = Retry.class)
    //5. Добавление в корзину на один больше товара чем есть в наличии (с включенным кол-вым учетом у товара), но выключенным кол-ым учетом у каталога
    public void addingTheMaxNumberPlusOneOfProductsToCartUsingIconPlusWithQuantitativeAccountingEnabledNutQuantitativeAccountingEnabledForTheProduct() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        navigationToSettingOfQuantitativeAccountingForTheProduct();
        turnOffStoreAccounting();
        turnOffQuantitativeAccountingForTheProductsInCatalog();
        navigationToGasStoveSetting();
        enableQuantitativeAccountingAtTheGefestGasStove();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        selectTheSectionWithGasStoves();
        enterTheMaximumAvailableQuantityOfThisProduct();
        addThisProductOneMoreTimeUsingPlusIcon();
        checkingThatPriceAndQuantityAreTheSame();
        addThisProductOneMoreTimeManually();
        checkingThatPriceAndQuantityAreTheSame();
    }

    @Test(retryAnalyzer = Retry.class)
    //6. Добавление в корзину на один больше товара чем есть в наличии (с включенным кол-вым учетом у товара), но выключенным кол-ым учетом у каталога
    //, добавление рандомного товара (со значением по умолчанию кол-го учета (включенным))
    public void addingOneMoreProductToTheCartThanIsAvailableWithQuantitativeAccountingEnabledForTheProductButWthQuantitativeAccountingEnabledForTheCatalogAndAddingRandomProductWithTheDefaultValueOfQuantitativeAccountingExpectedEnabled() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        deletingProductsFromTheCart();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        navigationToSettingOfQuantitativeAccountingForTheProduct();
        turnOffStoreAccounting();
        turnOffQuantitativeAccountingForTheProductsInCatalog();
        navigationToGasStoveSetting();
        enableQuantitativeAccountingAtTheGefestGasStove();
        navigationToMeanPageByUrl();
        ternOffEditMode();
        navigationToCatalogTab();
        selectTheSectionWithGasStoves();
        enterTheMaximumAvailableQuantityOfThisProduct();
        addThisProductOneMoreTimeUsingPlusIcon();
        checkingThatPriceAndQuantityAreTheSame();
        addThisProductOneMoreTimeManually();
        checkingThatPriceAndQuantityAreTheSame();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        addingTheMaxNumberOfProductsToTheCartUsingIconPlus();
        addingThisProductOneMoreTime();
        checkingThatPriceAndQuantityHaveIncreased();
    }


    @Test(retryAnalyzer = Retry.class)
    //7. Добавление в корзину на один больше товара чем есть в наличии (с выключенным кол-вым учетом у товара), и выключенным кол-ым учетом у каталога
    public void addingTheMaxNumberPlusOneOfProductsToCartUsingIconPlusExpectedSuccess() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        navigationToSettingOfQuantitativeAccountingForTheProduct();
        turnOffStoreAccounting();
        turnOffQuantitativeAccountingForTheProductsInCatalog();
        navigationToGasStoveSetting();
        turnOffQuantitativeAccountingAtTheGefestGasStove();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        selectTheSectionWithGasStoves();
        enterTheMaximumAvailableQuantityOfThisProduct();
        addThisProductOneMoreTimeUsingPlusIcon();
        checkingThatPriceAndQuantityHaveIncreasedForGefestGasStove();
        addThisProductOneMoreTimeManually();
        checkingThatPriceAndQuantityHaveIncreasedForGefestGasStove();
        returningSettingsBack();
    }

    @Test(retryAnalyzer = Retry.class)
    //8. Добавление в корзину на один больше товара чем есть в наличии (с включенным кол-вым учетом у каталога), но выключенным кол-ым учетом у каталога
    public void addingOneMoreProductToTheCartThanIsAvailableWithTheQuantityOfTheProductTurnedOffButWithTheCatalogAccountingTurnedOn() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        navigationToSettingOfQuantitativeAccountingForTheProduct();
        turnOffStoreAccounting();
        enableQuantitativeAccountingForTheProductsInCatalog();
        navigationToGasStoveSetting();
        turnOffQuantitativeAccountingAtTheGefestGasStove();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        selectTheSectionWithGasStoves();
        enterTheMaximumAvailableQuantityOfThisProduct();
        addThisProductOneMoreTimeUsingPlusIcon();
        checkingThatPriceAndQuantityHaveIncreasedForGefestGasStove();
        addThisProductOneMoreTimeManually();
        checkingThatPriceAndQuantityHaveIncreasedForGefestGasStove();
    }

    @Test(retryAnalyzer = Retry.class)
    //9. Проверка корректности работы блока добавления / удаления позиций в каталоге [ - | 0 | + ] c дробными значениями
    public void checkingTheCorrectnessOfTheOperationOfTheAddRemoveProductsInTheCatalogWithFractionalValues() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        navigationToAdminPartFromMeanPage();
        navigationToGasStoveSetting();
        settingTheMultiplicityForTheGefestGasStove();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        selectTheSectionWithGasStoves();
        checkingTheMultiplicityOfAddingProductsAndCalculatingThePrice();
        navigationToAdminPartFromMeanPage();
        navigationToGasStoveSetting();
        settingBackTheMultiplicityForTheGefestGasStove();
    }

    @Test(retryAnalyzer = Retry.class) //10. Отображение стандартной заглушки у товаров без изображений
    public void displayingStandardStubForProductsWithoutImages() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        navigationToAdminPartFromMeanPage();
        navigationToGasStoveSetting();
        removingImagesFromTheGefestGasStove();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        selectTheSectionWithGasStoves();
        checkingThatThisProductHasStubInsteadOfPicture();
        navigationToCart();
        checkingThatThisProductHasStubInsteadOfPictureInTheCart();
        navigationToAdminPartFromMeanPage();
        navigationToGasStoveSetting();
        returningImagesToTheGefestGasStoveBack();
    }

    @Test(retryAnalyzer = Retry.class) //11. Выводимая скидка равняется введенной в админ части
    public void displayedDiscountInCatalogIsEqualToTheEnteredInTheAdminPart() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        navigationToAdminPartFromMeanPage();
        deletingALLDiscounts();
        settingUpRandomDiscountForGefestGasStove();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        selectTheSectionWithGasStoves();
        enterTheMaximumAvailableQuantityOfThisProduct();
        memorizingDiscountedAndNonDiscountedPricesForGefest();
        checkingThatTheDiscountEnteredAndDisplayedInTheCatalogAreTheSame();
    }

    @Test(retryAnalyzer = Retry.class) //12. Товар добавляется по цене со скидкой в корзину
    public void productIsAddingAtDiscountedPriceToTheCart() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        navigationToAdminPartFromMeanPage();
        deletingALLDiscounts();
        settingUpRandomDiscountForGefestGasStove();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        selectTheSectionWithGasStoves();
        resetCache();
        enterTheMaximumAvailableQuantityOfThisProduct();
        memorizingDiscountedAndNonDiscountedPricesForGefest();
        checkingThatTheDiscountEnteredAndDisplayedInTheCatalogAreTheSame();
        addingAnItemToTheCartWithPriceChecking();
    }

    @Test(retryAnalyzer = Retry.class) //13. Добавление ТП в корзину
    public void addingTPToTheCart() {
        //arrange
        areThereAnyOffers = true;
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        ternOnEditMode();
        navigationToCatalogTab();
        navigationToComponentOfCatalogSetting();
        choiceCatalogWithOnlyOffers();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        navigationToCatalogTab();
        navigationToComponentOfCatalogSetting();
        choiceStandardCatalog();
        ternOffEditMode();
    }

    @Test(retryAnalyzer = Retry.class) //14. Проверить вывод информации о наличии на складах
    public void checkTheOutputOfInformationAboutAvailabilityInStorage() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
        ternOnEditMode();
        showTheQuantityOfProductsInStorage();
        navigationToAdminPartFromMeanPage();
        navigationToGasStoveSetting();
        returningQuantitativeAccountingAtTheGefestGasStoveByDefault();
        addingSecondStorageIfItNeed();
        navigationToGasStoveSetting();
        addingProductsToTheStorages();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        navigationToComponentOfCatalogSetting();
        configureTheOutputOfAllStoragesToTheCatalog();
        navigationToMeanPageByUrl();
        ternOffEditMode();
        navigationToCatalogTab();
        selectTheSectionWithGasStoves();
        enterTheMaximumAvailableQuantityOfThisProduct();
        checkingThatTheTotalNumberOfOutputProductsAndQuantityByIsEqualToThePreviouslyEnteredData();
        navigationToComponentOfCatalogSetting();
        turnOffShowingTheQuantityOfProductsInStorage();
    }

    @Test(retryAnalyzer = Retry.class)
    //15. Проверка корректности увеличения количество товаров в корзине
    // (с включенным кол-вым учетом у каталога, но выключенным кол-ым учетом у одного из товаров, с дробной кратностью товара, ТП)
    public void checkingCorrectnessOfIncreasingTheNumberOfProductsInTheBasket() {
        //arrange
        areThereAnyOffers = true;
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        navigationToSettingOfQuantitativeAccountingForTheProduct();
        turnOffStoreAccounting();
        enableQuantitativeAccountingForTheProductsInCatalog();
        navigationToGasStoveSetting();
        turnOffQuantitativeAccountingAtTheGefestGasStove();
        settingTheMultiplicityForTheKaiserGasStove();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        selectTheSectionWithGasStoves();
        addingGefestGasStoveToCartUsingPlusIcon();
        addingKaiserGasStoveToCartUsingPlusIcon();
        navigationToCart();
        ternOffEditMode();
        //act
        addingMaxQuantityOfProductInTheCartUsingPlusIconOneMoreThanAvailable(quantityFieldOfGefestLocator, iconPlusOfGefestLocator, numberOfAvailableGefestGasStove);
        checkingThatQuantityOfGefestGasStoveIsOneMoreThanAvailable();
        attemptToSelectNegativeQuantityOfProductsInTheCartUsingMinusIcon(quantityFieldOfGefestLocator, iconMinusOfGefestLocator);
        checkingThatQuantityThisProductIsNotNegative(quantityFieldOfGefestLocator);
        attemptToEnterNegativeQuantityOfProductsInTheCart(quantityFieldOfGefestLocator);
        checkingThatQuantityThisProductIsNotNegative(quantityFieldOfGefestLocator);
        addingMaxQuantityOfProductInTheCartUsingInputField(quantityFieldOfGefestLocator, numberOfAvailableGefestGasStove);
        checkingThatQuantityOfGefestGasStoveIsOneMoreThanAvailable();
        addingMaxQuantityOfProductInTheCartUsingPlusIconOneMoreThanAvailable(quantityFieldOfKaiserLocator, iconPlusOfKaiserLocator, numberOfAvailableKaiserGasStove);
        checkingThatQuantityThisProductIsEqualsAvailable(quantityFieldOfKaiserLocator, numberOfAvailableKaiserGasStove);
        attemptToSelectNegativeQuantityOfProductsInTheCartUsingMinusIcon(quantityFieldOfKaiserLocator, iconMinusOfKaiserLocator);
        checkingThatQuantityOfKaiserGasStoveEqualsOneTenth();
        attemptToEnterNegativeQuantityOfProductsInTheCart(quantityFieldOfKaiserLocator);
        checkingThatQuantityThisProductIsNotNegative(quantityFieldOfKaiserLocator);
        addingMaxQuantityOfProductInTheCartUsingInputField(quantityFieldOfKaiserLocator, numberOfAvailableKaiserGasStove);
        checkingThatQuantityThisProductIsEqualsAvailable(quantityFieldOfKaiserLocator, numberOfAvailableKaiserGasStove);
        checkingThatTotalPriceAreCalculatedRight();
        navigationToAdminPartFromMeanPage();
        navigationToGasStoveSetting();
        returningQuantitativeAccountingAtTheGefestGasStoveByDefault();
    }

    @Test(retryAnalyzer = Retry.class) //16. Изменение кол-ва ТП в корзине
    public void changingNumberOfTPInTheCart() {
        //arrange
        areThereAnyOffers = true;
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        enableEditMode();
        navigationToCatalogTab();
        navigationToComponentOfCatalogSetting();
        choiceCatalogWithOnlyOffers();
        navigationToMeanPageByUrl();
        ternOffEditMode();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        navigationToCart();
        //act
        ternOffEditMode();
        addingMaxQuantityOfProductInTheCartUsingPlusIconOneMoreThanAvailable(quantityFieldOfRandomTPLocator, iconPlusOfRandomTPLocator, quantityOfProductsInStock);
        checkingThatQuantityThisProductIsEqualsAvailable(quantityFieldOfRandomTPLocator, quantityOfProductsInStock);
        attemptToSelectNegativeQuantityOfProductsInTheCartUsingMinusIcon(quantityFieldOfRandomTPLocator, iconMinusOfRandomTPLocator);
        checkingThatQuantityThisProductIsEqualsOne(quantityFieldOfRandomTPLocator);
        attemptToEnterNegativeQuantityOfProductsInTheCart(quantityFieldOfRandomTPLocator);
        checkingThatQuantityThisProductIsNotNegative(quantityFieldOfRandomTPLocator);
        addingMaxQuantityOfProductInTheCartUsingInputField(quantityFieldOfRandomTPLocator, quantityOfProductsInStock);
        checkingThatQuantityThisProductIsEqualsAvailable(quantityFieldOfRandomTPLocator, quantityOfProductsInStock);
        navigationToCatalogTab();
        ternOnEditMode();
        navigationToComponentOfCatalogSetting();
        choiceStandardCatalog();
        ternOffEditMode();
    }

    @Test(retryAnalyzer = Retry.class)
    //17. Изменение кол-ва ТП c дробным коэффициентом, и ТП с отключенным кол-ым учетом в корзине
    public void changingNumberOfTPInTheCartWithAFractionalCoefficientAndWithQuantitativeAccountingDisabled() {
        //arrange
        areThereAnyOffers = true;
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        deletingProductsFromTheCart();
        ternOnEditMode();
        navigationToCatalogTab();
        navigationToComponentOfCatalogSetting();
        choiceCatalogWithOnlyOffers();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        configureTheFirstTwoTP("N");
        addingFirstTwoTPToTheCart();
        //act
        ternOffEditMode();
        addingMaxQuantityOfProductInTheCartUsingPlusIconOneMoreThanAvailable(quantityFieldOfTPWithQuantitativeAccountingDisabledLocator, iconPlusOfTPWithQuantitativeAccountingDisabledLocator, quantityOfSecondProductsInStock);
        checkingThatQuantityOfThisProductIsOneMoreThanAvailable(quantityFieldOfTPWithQuantitativeAccountingDisabledLocator, quantityOfSecondProductsInStock);
        attemptToSelectNegativeQuantityOfProductsInTheCartUsingMinusIcon(quantityFieldOfTPWithQuantitativeAccountingDisabledLocator, iconMinusOfTPWithQuantitativeAccountingDisabledLocator);
        checkingThatQuantityThisProductIsEqualsOne(quantityFieldOfTPWithQuantitativeAccountingDisabledLocator);
        attemptToEnterNegativeQuantityOfProductsInTheCart(quantityFieldOfTPWithQuantitativeAccountingDisabledLocator);
        checkingThatQuantityThisProductIsNotNegative(quantityFieldOfTPWithQuantitativeAccountingDisabledLocator);
        addingMaxQuantityOfProductInTheCartUsingInputField(quantityFieldOfTPWithQuantitativeAccountingDisabledLocator, quantityOfSecondProductsInStock);
        checkingThatQuantityOfThisProductIsOneMoreThanAvailable(quantityFieldOfTPWithQuantitativeAccountingDisabledLocator, quantityOfSecondProductsInStock);
        addingMaxQuantityOfProductInTheCartUsingPlusIconOneMoreThanAvailable(quantityFieldOfTPWithAFractionalCoefficientLocator, iconPlusOfTPWithAFractionalCoefficientLocator, quantityOfProductsInStock);
        checkingThatQuantityThisProductIsEqualsAvailable(quantityFieldOfTPWithAFractionalCoefficientLocator, quantityOfProductsInStock);
        attemptToSelectNegativeQuantityOfProductsInTheCartUsingMinusIcon(quantityFieldOfTPWithAFractionalCoefficientLocator, iconMinusOfTPWithWithAFractionalCoefficientLocator);
        checkingThatQuantityThisProductIsNotNegative(quantityFieldOfTPWithAFractionalCoefficientLocator);
        attemptToEnterNegativeQuantityOfProductsInTheCart(quantityFieldOfTPWithAFractionalCoefficientLocator);
        checkingThatQuantityThisProductIsNotNegative(quantityFieldOfTPWithAFractionalCoefficientLocator);
        addingMaxQuantityOfProductInTheCartUsingInputField(quantityFieldOfTPWithAFractionalCoefficientLocator, quantityOfProductsInStock);
        checkingThatQuantityThisProductIsEqualsAvailable(quantityFieldOfTPWithAFractionalCoefficientLocator, quantityOfProductsInStock);
        checkingThatTotalPriceOfTheseProductsAreCalculatedRight(priceForFirstProductInCart, priceForSecondProductInCart);
        ternOnEditMode();
        navigationToCatalogTab();
        configureTheFirstTwoTP("D");
        navigationToCatalogTab();
        navigationToComponentOfCatalogSetting();
        choiceStandardCatalog();
        ternOffEditMode();
    }

    @Test(retryAnalyzer = Retry.class)
    //18. Организации  ИП не отображаются у пользователя после снятия ИП в доступных типах плательщика
    public void organizationsIPAreNotDisplayedInTheUserAfterRemovingTheIPInTheAvailablePayerTypes() {
        determineWhetherVersionsOfWorkingWithOrganization();
        //!!!!!!!!!! Такая логика, клиенты не жалуются на это (админ не может запретить уже созданным типам организаций покупать товары), по этому не проверять на это у расширенной версии
        if (!versionsOfWorkingWithOrganizationsExtended) {
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            returningSettingsBackIfCatalogBroken();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToOrganizationTab();
            MethodsForAddingOrganizationsWithExtendedVersion org = new MethodsForAddingOrganizationsWithExtendedVersion();
            org.creatingThreeOrganizations();
            exitFromB2B();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            org.navigationToProfilesOfBuyers();
            org.removingIPFromAvailablePayerTypes();
            exitFromB2B();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            deletingProductsFromTheCart();
            navigationToCatalogTab();
            changeTheQuantityOfRandomProduct();
            checkingThatThePriceOfTheAddedProductHasBeenCalculated();
            navigationToCart();
            driver.findElement(buttonMakeOrderInTheCartLocator).click();
            org.checkingThatTheAddedLPOrganizationsAreAvailableForSelectionAndTheAddedIPOrganizationIsNotAvailable();
            exitFromB2B();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            org.navigationToProfilesOfBuyers();
            org.addingBackIPToAvailablePayerTypes();
        }
    }

    @Test(retryAnalyzer = Retry.class)
    //19. Проверка вывода цен small Opt в корзине в доп.товарах, возможность добавления товара по этой цене
    public void checkingOutputOfSmallOptPricesInTheAddProductsTheAbilityToAddProductAtThisPrice() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        chooseToDisplaySmallOptPricesForAllUsers();
        navigationToGasStoveSetting();
        setPriceForSmallOptForGasStoveGefest();
        navigationToMeanPageByUrl();
        ternOffEditMode();
        navigationToCart();
        addingToSearchFieldWordForSearchAdditionalProductsGefestGasStove();
        checkingThatThePricesInTheCartForAdditionalProductsIsDisplayedAsForSmallOptGroup();
    }

    @Test(retryAnalyzer = Retry.class)
    //20. Отображение размера скидки и минимальной доступной цены без скидки в корзине
    public void displayingTheDiscountAmountAndTheMinimumAvailableWithoutDiscountInTheCart() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        navigationToAdminPartFromMeanPage();
        deletingALLDiscounts();
        settingUpRandomDiscountForGefestGasStove();
        navigationToMeanPageByUrl();
        resetCache();
        navigationToCatalogTab();
        resetCache();
        selectTheSectionWithGasStoves();
        enterTheMaximumAvailableQuantityOfThisProduct();
        memorizingDiscountedAndNonDiscountedPricesForGefest();
        checkingThatTheDiscountEnteredAndDisplayedInTheCatalogAreTheSame();
        addingAnItemToTheCartWithPriceChecking();
        checkingTheDiscountAmountAndTheAvailablePriceWithoutDiscount();
    }

    @Test(retryAnalyzer = Retry.class)
    //21. Проверка корректности состава товаров (ТП) на детальной странице оформленного заказа
    public void checkingTheCorrectnessOfTheProductsOfTheGoodsOnTheDetailedPageOfTheCompletedOrderForTP() {
        //arrange
        areThereAnyOffers = true;
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        ternOnEditMode();
        navigationToCatalogTab();
        navigationToComponentOfCatalogSetting();
        choiceCatalogWithOnlyOffers();
        navigationToMeanPageByUrl();
        ternOffEditMode();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        ternOffEditMode();
        navigationToCart();
        MethodsForMyOrders makeOrder = new MethodsForMyOrders();
        makeOrder.rememberingNamesAndQuantityAddedProducts();
        makeOrder.navigationToMakingOrderFromCart();
        makeOrder.trySelectCompany();
        makeOrder.rememberingTotalPrice();
        makeOrder.makingOrder();
        navigationToMyOrdersPage();
        makeOrder.openingLastOrder();
        makeOrder.checkingThatAddedProductsAreDisplayed();
        navigationToCatalogTab();
        navigationToComponentOfCatalogSetting();
        choiceStandardCatalog();
        ternOffEditMode();
    }

    @Test(retryAnalyzer = Retry.class) //22. Настройока вывода полей в компоненте
    public void settingTheOutputOfFieldsInTheComponent() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheSetting();
        ternOnEditMode();
        navigationToComponentOfUserParameters();
        addUserParameterInTheSettingsOnTheMainPage();
        checkingThatNewUserParameterInTheSettingsOnTheMainPageISDisplayed();
        enteredDataInAddedUserParameter();
        saveBasicData(3);
        resetCache();
        checkingThatEnteredDataIsDisplayed();
        navigationToComponentOfUserParameters();
        deletingAddedUserParameterInTheSettingsOnTheMainPage();
    }

    @Test(retryAnalyzer = Retry.class)
    //23. Модерация после внесения изменений (данные предпринимателя обязательны  для "IP")
    public void v_moderationAfterMakingChangesEntrepreneurSInformationIsRequiredForIP() {
        determineWhetherRegistrationOrganizationNeedsToBeConfirmed();
        determineWhetherVersionsOfWorkingWithOrganization();
        if (doNeedToConfirmRegistrationOrganization && versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            navigationToTheSiteSettings();
            selectTapForIP();
            selectGroupsOfOrderPropertiesWhenChangingWhichTheOrganizationGetsToModeration("Данные предпринимателя");
            unselectGroupsOfOrderPropertiesWhenChangingWhichTheOrganizationGetsToModeration("Контактная информация");
            clickElement(buttonSaveLocator);
            navigationToMeanPageByUrl();
            resetCache();
            navigationToOrganizationTab();
            creatingIPOrganization();
            changingDataIPOrganizationWhichNeededConfirmAndNot();
        } else {
            System.out.println("Если подтверждать создание орг не нужно или версия работы с орг. не расширенная > то изменения орг подтв. не нужно");
        }
    }

    @Test(retryAnalyzer = Retry.class)
    //24. Модерация после внесения изменений (контактная информация обязательна  для "LP")
    public void v_moderationAfterMakingChangesContactInformationIsRequiredForLP() {
        determineWhetherRegistrationOrganizationNeedsToBeConfirmed();
        determineWhetherVersionsOfWorkingWithOrganization();
        if (doNeedToConfirmRegistrationOrganization && versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            navigationToTheSiteSettings();
            selectTapForLegalPerson();
            selectGroupsOfOrderPropertiesWhenChangingWhichTheOrganizationGetsToModeration("Контактная информация");
            unselectGroupsOfOrderPropertiesWhenChangingWhichTheOrganizationGetsToModeration("Данные компании");
            driver.findElement(buttonSaveLocator).click();
            navigationToMeanPageByUrl();
            navigationToOrganizationTab();
            creatingLegalPersonOrganization();
            changingDataLegalPersonOrganizationWhichNeededConfirmAndNot();
        } else {
            System.out.println("Если подтверждать создание орг не нужно или версия работы с орг. не расширенная > то изменения орг подтв. не нужно");
        }
    }

    @Test(retryAnalyzer = Retry.class) //25. Вывод обязательных полей в административных настройках
    public void v_outputOfRequiredFieldsInAdministrativeSettings() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheSiteSettings();
        selectTapForIP();
        deselectAllUserDataForRegisteringAnOrganization();
        selectAllUserDataForRegisteringAnOrganizationOneAtATimeCheckingThatTheyAppearedInTheRequiredFieldsField();
        navigationToRegistrationTab();
        checkingThatAllUserDataForIPSelectedInTheAdminPanelIsDisplayedWhenRegisteringTheOrganization();
    }
    @Test(retryAnalyzer = Retry.class) //26. Вывод обязательных полей в форме регистрации
    public void v_outputOfRequiredFieldsInTheRegistrationForm() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheSiteSettings();
        selectTapForIP();
        deselectAllUserDataForRegisteringAnOrganization();
        selectTheFirstTwoProperties();
        selectARequiredProperty();
        navigationToRegistrationTab();
        checkingThatAllUserDataForIPSelectedInTheAdminPanelIsDisplayedWhenRegisteringTheOrganization();
        checkingThatTheFieldSelectedInTheAdminPanelAsRequiredIsReallyRequired();
    }

    @Test(retryAnalyzer = Retry.class) //27. Вывод разделов в поисковой подсказке по части названия
    public void outputOfSectionsInTheSearchHintByPartOfTheName() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        enableTheIndexSectionsForTheSearchModuleSetting();
        navigationToMeanPageByUrl();
        ternOffEditMode();
        navigationToCatalogTab();
        choiceTheSecondLevelCategoryInABlackHatOrTheFirstLevelInAWhiteHat();
        navigationToCatalogTab();
        enterPartOfNameRandomCategoryInTheSearchFieldOnTheCatalogTabAndDeleteOne(fieldForSearchInCatalogLocator, tempString);
        choiceCategoryFromPopApWindow();
        checkingThatBreadCrumbsContainTheNameOfTheSection();
        checkingThatURLContainsChosenCategory();
        checkingThatAllProductsHaveSimilarIdToTheSectionId();
    }
    @Test(retryAnalyzer = Retry.class) //28. Вывод разделов в поисковой подсказке по целому названию
    public void outputOfSectionsInTheSearchHintByWholeOfTheWord() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        enableTheIndexSectionsForTheSearchModuleSetting();
        navigationToMeanPageByUrl();
        ternOffEditMode();
        navigationToCatalogTab();
        choiceTheSecondLevelCategoryInABlackHatOrTheFirstLevelInAWhiteHat();
        navigationToCatalogTab();
        enterNameInTheSearchFieldOnTheCatalogTab(tempString);
        choiceCategoryFromPopApWindow();
        checkingThatBreadCrumbsContainTheNameOfTheSection();
        checkingThatURLContainsChosenCategory();
        checkingThatAllProductsHaveSimilarIdToTheSectionId();
    }
    @Test(retryAnalyzer = Retry.class)
    //29. Вывод минимальной цены в поисковой подсказке
    public void outputOfTheMinimumPriceInTheSearchHint() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        chooseToDisplaySmallOptPricesForAllUsers();
        navigationToGasStoveSetting();
        setPriceForSmallOptForGasStoveGefest();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        enterNameInTheSearchFieldOnTheCatalogTab("Плита GEFEST");
        checkingTheSmallOptPriceInPopUpSearchWindow();
    }

    @Test(retryAnalyzer = Retry.class) //30. Вывод цен в поисковой подсказке для ТП
    public void outputOfPricesInTheSearchHintForTheTP() {
        //arrange
        areThereAnyOffers = true;
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        ternOnEditMode();
        navigationToCatalogTab();
        navigationToComponentOfCatalogSetting();
        choiceCatalogWithOnlyOffers();
        navigationToMeanPageByUrl();
        ternOffEditMode();
        navigationToCatalogTab();
        enterNameInTheSearchFieldOnTheCatalogTab("любим");
        checkingThatThereAreNoCartIconInPupOpWindow();
        checkingThatThereIsAWordFrom();
        returningSettingsBack();
    }

    @Test(retryAnalyzer = Retry.class)
    //31. Добавление товара в корзину из поисковой подсказки с доступным количеством 0 (кол-ый учет у каталога - выкл)
    public void addingAnItemToTheCartFromASearchHintWithAnAvailableQuantityOfZeroAndTheNumberOfCatalogAccountsIsOff() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        navigationToSettingOfQuantitativeAccountingForTheProduct();
        turnOffStoreAccounting();
        turnOffQuantitativeAccountingForTheProductsInCatalog();
        navigationToGasStoveSetting();
        setTheNumberOfGasStoveGefestEqualToZero();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        //act
        wordForSearch = "Плита GEFEST";
        enterNameInTheSearchFieldOnTheCatalogTab(wordForSearch);
        addingThisProductFromPopUpWindowToTheCart();
        navigationToCart();
        checkingThatThisProductWasAddedToTheCart();
        returningSettingsBack();
    }

    @Test(retryAnalyzer = Retry.class) //32. Отображение скидки на детальной странице товара
    public void displayedDiscountOnTheDetailPage() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        navigationToAdminPartFromMeanPage();
        deletingALLDiscounts();
        settingUpRandomDiscountForGefestGasStove();
        navigationToGasStoveSetting();
        setPriceForSmallOptForGasStoveGefest();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        selectTheSectionWithGasStoves();
        findNumberOfGefestInCatalogInCatalog();
        //act
        openDetailPageOfRandomProduct(count);
        checkingThatThereArePricesWithAndWithoutDiscount();
    }
// ОШИБКА B2B !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//    @Test(retryAnalyzer = Retry.class)
//    //33. Добавление доп. товара которого нет в наличии в корзину (кол-ый учет у каталога - выкл)
//    public void addingAnAdditionalItemToTheCartWithAnAvailableQuantityOfZeroAndTheNumberOfCatalogAccountsIsOff() {
//        //arrange
//        navigationToAuthorizationTab();
//        fillingFieldsOnTheLogInTabLikeAdmin();
//        logInToB2B();
//        returningSettingsBackIfCatalogBroken();
//        deletingProductsFromTheCart();
//        navigationToSettingOfQuantitativeAccountingForTheProduct();
//        turnOffStoreAccounting();
//        turnOffQuantitativeAccountingForTheProductsInCatalog();
//        navigationToGasStoveSetting();
//        setTheNumberOfGasStoveGefestEqualToZero();
//        navigationToMeanPageByUrl();
//        ternOffEditMode();
//        //act
//        navigationToCart();
//        addingToSearchFieldWordForSearchAdditionalProductsGefestGasStove();
//        addingProductToCartFromTabAddAnAdditionalProduct(1);
//        checkingThatThereAreThisQuantityItemsInTheBasket(1);
//        returningSettingsBack();
//    }













    @Test(retryAnalyzer = Retry.class) // Тест №0  + создает заново локаторы для регистрации, + настраивает поля которые должны отправляться на модерацию после изменения
    public void w_returningSettingsBack() {
        a_returningSettingsBack();
        SettingUpAutotestsForB2BSettings set = new SettingUpAutotestsForB2BSettings();
        set.b_creatingLocatorsForRegistration();
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended){
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            navigationToTheSiteSettings();
            selectTapForLegalPerson();
            selectGroupsOfOrderPropertiesWhenChangingWhichTheOrganizationGetsToModeration("Данные компании");
            unselectGroupsOfOrderPropertiesWhenChangingWhichTheOrganizationGetsToModeration("Контактная информация");
            clickStandardButtonForSaveSettings();
        }
    }


}
