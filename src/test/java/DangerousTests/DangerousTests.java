package DangerousTests;

import Catalog.MethodsForCatalog;
import MyOrdersHistory.MethodsForMyOrders;
import OrganizationsWithExtendedVersion.MethodsForAddingOrganizationsWithExtendedVersion;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DangerousTests extends MethodsForCatalog {
    @Test //1. Вывод свойст товаров в каталог с помощью "параметра свойств в компонентах и формах"
    public void checkingDisplaySettingsOfProductPropertiesInTheCatalog() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        //returningSettingsBackIfCatalogBroken();
        navigationToSystemSettings();
        enableOutputOfPropertiesInTheDirectory();
        navigationToGasStoveSetting();
        addRegionPropertyToThisProduct();
        configureOutputOfThisPropertyToTheCatalog();
        navigationToMeanPageByUrl();
        resetCache();
        navigationToCatalogTab();
        selectTheSectionWithGasStoves();
        checkingThatTheColumnWithThisPropertyHasAppearedInTheCatalog();
        checkingThatTheProductHasAPropertyWithThePreviouslyEnteredValue();
    }
    @Test //2. Проверка настройки отображения свойств товаров в каталоге (колонках)
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
        enableEditMode();
        navigationToCatalogTab();
        navigationToComponentOfCatalogSetting();
        removeAllColumnsWithPropertiesFromTheCatalog();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        navigationToComponentOfCatalogSetting();
        selectFewPropertiesToOutputToTheCatalog();
        checkingThatSelectedPropertiesAreDisplayedInTheCatalog();
    }
    @Test //3. Проверка вывода цен small Opt в каталог, возможность добавления товара по этой цене
    public void checkingTheDisplaySettingsForThePricesOfProductsInTheCatalog() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
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
    @Test //4. Добавление в корзину товаров на один больше чем есть в наличии, c включенными кол-ыми учетоми
    public void addingTheMaxNumberPlusOneOfProductsToCartUsingIconPlusExpectedFail() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        navigationToSettingOfQuantitativeAccountingForTheProduct();
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
    @Test //5. Добавление в корзину на один больше товара чем есть в наличии (с включенным кол-вым учетом у товара), но выключенным кол-ым учетом у каталога
    public void addingTheMaxNumberPlusOneOfProductsToCartUsingIconPlusWithQuantitativeAccountingEnabledNutQuantitativeAccountingEnabledForTheProduct() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        navigationToSettingOfQuantitativeAccountingForTheProduct();
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
    @Test //6. Добавление в корзину на один больше товара чем есть в наличии (с включенным кол-вым учетом у товара), но выключенным кол-ым учетом у каталога
          //, добавление рандомного товара (со значением по умолчанию кол-го учета (включенным))
    public void addingOneMoreProductToTheCartThanIsAvailableWithQuantitativeAccountingEnabledForTheProductButWthQuantitativeAccountingEnabledForTheCatalogAndAddingRandomProductWithTheDefaultValueOfQuantitativeAccountingExpectedEnabled() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        navigationToSettingOfQuantitativeAccountingForTheProduct();
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
        navigationToCatalogTab();
        addingTheMaxNumberOfProductsToTheCartUsingIconPlus();
        addingThisProductOneMoreTime();
        checkingThatPriceAndQuantityHaveIncreased();
    }


    @Test //7. Добавление в корзину на один больше товара чем есть в наличии (с выключенным кол-вым учетом у товара), и выключенным кол-ым учетом у каталога
    public void addingTheMaxNumberPlusOneOfProductsToCartUsingIconPlusExpectedSuccess() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        navigationToSettingOfQuantitativeAccountingForTheProduct();
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
    }

    @Test //8. Добавление в корзину на один больше товара чем есть в наличии (с включенным кол-вым учетом у каталога), но выключенным кол-ым учетом у каталога
    public void addingOneMoreProductToTheCartThanIsAvailableWithTheQuantityOfTheProductTurnedOffButWithTheCatalogAccountingTurnedOn() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        navigationToSettingOfQuantitativeAccountingForTheProduct();
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
    @Test //9. Проверка корректности работы блока добавления / удаления позиций в каталоге [ - | 0 | + ] c дробными значениями
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

    @Test //10. Отображение стандартной заглушки у товаров без изображений
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
    @Test //11. Выводимая скидка равняется введенной в админ части
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
        explicitWaiting();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        selectTheSectionWithGasStoves();
        enterTheMaximumAvailableQuantityOfThisProduct();
        memorizingDiscountedAndNonDiscountedPricesForGefest();
        checkingThatTheDiscountEnteredAndDisplayedInTheCatalogAreTheSame();
    }
    @Test //12. Товар добавляется по цене со скидкой в корзину
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
        explicitWaiting();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        selectTheSectionWithGasStoves();
        resetCache();
        enterTheMaximumAvailableQuantityOfThisProduct();
        memorizingDiscountedAndNonDiscountedPricesForGefest();
        checkingThatTheDiscountEnteredAndDisplayedInTheCatalogAreTheSame();
        addingAnItemToTheCartWithPriceChecking();
    }
    @Test //13. Добавление ТП в корзину
    public void addingTPToTheCart() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        enableEditMode();
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
    }
    @Test //14. Проверить вывод информации о наличии на складах
    public void checkTheOutputOfInformationAboutAvailabilityInStorage() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        navigationToCatalogTab();
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
        navigationToCatalogTab();
        selectTheSectionWithGasStoves();
        enterTheMaximumAvailableQuantityOfThisProduct();
        checkingThatTheTotalNumberOfOutputProductsAndQuantityByIsEqualToThePreviouslyEnteredData();
        explicitWaiting();explicitWaiting();
        navigationToComponentOfCatalogSetting();
        turnOffShowingTheQuantityOfProductsInStorage();
    }

    @Test //15. Проверка корректности увеличения количество товаров в корзине
          // (с включенным кол-вым учетом у каталога, но выключенным кол-ым учетом у одного из товаров, с дробной кратностью товара, ТП)
    public void checkingCorrectnessOfIncreasingTheNumberOfProductsInTheBasket() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        navigationToSettingOfQuantitativeAccountingForTheProduct();
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
        //act
        addingMaxQuantityOfProductInTheCartUsingPlusIconOneMoreThanAvailable(quantityFieldOfGefestLocator, iconPlusOfGefestLocator, numberOfAvailableGefestGasStove);
        checkingThatQuantityOfGefestGasStoveIsOneMoreThanAvailable();
        attemptToSelectNegativeQuantityOfProductsInTheCartUsingMinusIcon(quantityFieldOfGefestLocator, iconMinusOfGefestLocator);
        checkingThatQuantityThisProductIsEqualsOne(quantityFieldOfGefestLocator);
        attemptToEnterNegativeQuantityOfProductsInTheCart(quantityFieldOfGefestLocator);
        checkingThatQuantityThisProductIsEqualsOne(quantityFieldOfGefestLocator);
        addingMaxQuantityOfProductInTheCartUsingInputField(quantityFieldOfGefestLocator, numberOfAvailableGefestGasStove);
        checkingThatQuantityOfGefestGasStoveIsOneMoreThanAvailable();

        addingMaxQuantityOfProductInTheCartUsingPlusIconOneMoreThanAvailable(quantityFieldOfKaiserLocator, iconPlusOfKaiserLocator, numberOfAvailableKaiserGasStove);
        checkingThatQuantityThisProductIsEqualsAvailable(quantityFieldOfKaiserLocator, numberOfAvailableKaiserGasStove);
        attemptToSelectNegativeQuantityOfProductsInTheCartUsingMinusIcon(quantityFieldOfKaiserLocator, iconMinusOfKaiserLocator);
        checkingThatQuantityOfKaiserGasStoveEqualsOneTenth();
        attemptToEnterNegativeQuantityOfProductsInTheCart(quantityFieldOfKaiserLocator);
        checkingThatQuantityOfKaiserGasStoveEqualsOneTenth();
        addingMaxQuantityOfProductInTheCartUsingInputField(quantityFieldOfKaiserLocator, numberOfAvailableKaiserGasStove);
        checkingThatQuantityThisProductIsEqualsAvailable(quantityFieldOfKaiserLocator, numberOfAvailableKaiserGasStove);
        checkingThatTotalPriceAreCalculatedRight();
        navigationToAdminPartFromMeanPage();
        navigationToGasStoveSetting();
        returningQuantitativeAccountingAtTheGefestGasStoveByDefault();
    }
    @Test //16. Изменение кол-ва ТП в корзине
    public void changingNumberOfTPInTheCart() {
        //arrange
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
        navigationToCatalogTab();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        navigationToCart();
        //act
        addingMaxQuantityOfProductInTheCartUsingPlusIconOneMoreThanAvailable(quantityFieldOfRandomTPLocator, iconPlusOfRandomTPLocator, quantityOfProductsInStock);
        checkingThatQuantityThisProductIsEqualsAvailable(quantityFieldOfRandomTPLocator, quantityOfProductsInStock);
        attemptToSelectNegativeQuantityOfProductsInTheCartUsingMinusIcon(quantityFieldOfRandomTPLocator, iconMinusOfRandomTPLocator);
        checkingThatQuantityThisProductIsEqualsOne(quantityFieldOfRandomTPLocator);
        attemptToEnterNegativeQuantityOfProductsInTheCart(quantityFieldOfRandomTPLocator);
        checkingThatQuantityThisProductIsEqualsOne(quantityFieldOfRandomTPLocator);
        addingMaxQuantityOfProductInTheCartUsingInputField(quantityFieldOfRandomTPLocator, quantityOfProductsInStock);
        checkingThatQuantityThisProductIsEqualsAvailable(quantityFieldOfRandomTPLocator, quantityOfProductsInStock);
        navigationToCatalogTab();
        navigationToComponentOfCatalogSetting();
        choiceStandardCatalog();
    }
    @Test //17. Изменение кол-ва ТП c дробным коэффициентом, и ТП с отключенным кол-ым учетом в корзине
    public void changingNumberOfTPInTheCartWithAFractionalCoefficientAndWithQuantitativeAccountingDisabled() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        deletingProductsFromTheCart();
        enableEditMode();
        navigationToCatalogTab();
        navigationToComponentOfCatalogSetting();
        choiceCatalogWithOnlyOffers();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        configureTheFirstTwoTP();
        addingFirstTwoTPToTheCart();
        //act
        addingMaxQuantityOfProductInTheCartUsingPlusIconOneMoreThanAvailable(quantityFieldOfTPWithQuantitativeAccountingDisabledLocator, iconPlusOfTPWithQuantitativeAccountingDisabledLocator, quantityOfProductsInStock);
        checkingThatQuantityOfThisProductIsOneMoreThanAvailable(quantityFieldOfTPWithQuantitativeAccountingDisabledLocator, quantityOfProductsInStock);
        attemptToSelectNegativeQuantityOfProductsInTheCartUsingMinusIcon(quantityFieldOfTPWithQuantitativeAccountingDisabledLocator, iconMinusOfTPWithQuantitativeAccountingDisabledLocator);
        checkingThatQuantityThisProductIsEqualsOne(quantityFieldOfTPWithQuantitativeAccountingDisabledLocator);
        attemptToEnterNegativeQuantityOfProductsInTheCart(quantityFieldOfTPWithQuantitativeAccountingDisabledLocator);
        checkingThatQuantityThisProductIsEqualsOne(quantityFieldOfTPWithQuantitativeAccountingDisabledLocator);
        addingMaxQuantityOfProductInTheCartUsingInputField(quantityFieldOfTPWithQuantitativeAccountingDisabledLocator, quantityOfProductsInStock);
        checkingThatQuantityOfThisProductIsOneMoreThanAvailable(quantityFieldOfTPWithQuantitativeAccountingDisabledLocator, quantityOfProductsInStock);

        addingMaxQuantityOfProductInTheCartUsingPlusIconOneMoreThanAvailable(quantityFieldOfTPWithAFractionalCoefficientLocator, iconPlusOfTPWithWithAFractionalCoefficientLocator, quantityOfSecondProductsInStock);
        checkingThatQuantityThisProductIsEqualsAvailable(quantityFieldOfTPWithAFractionalCoefficientLocator, quantityOfSecondProductsInStock);
        attemptToSelectNegativeQuantityOfProductsInTheCartUsingMinusIcon(quantityFieldOfTPWithAFractionalCoefficientLocator, iconMinusOfTPWithWithAFractionalCoefficientLocator);
        checkingThatQuantityThisProductEqualsOneTenth(quantityFieldOfTPWithAFractionalCoefficientLocator);
        attemptToEnterNegativeQuantityOfProductsInTheCart(quantityFieldOfTPWithAFractionalCoefficientLocator);
        checkingThatQuantityThisProductEqualsOneTenth(quantityFieldOfTPWithAFractionalCoefficientLocator);
        addingMaxQuantityOfProductInTheCartUsingInputField(quantityFieldOfTPWithAFractionalCoefficientLocator, quantityOfSecondProductsInStock);
        checkingThatQuantityThisProductIsEqualsAvailable(quantityFieldOfTPWithAFractionalCoefficientLocator, quantityOfSecondProductsInStock);
        checkingThatTotalPriceOfTheseProductsAreCalculatedRight(priceForFirstProductInCart, priceForSecondProductInCart);
        navigationToAdminPartFromMeanPage();
        navigationToGasStoveSetting();
        returningQuantitativeAccountingAtTheGefestGasStoveByDefault();
    }

    @Test //18. Организации  ИП не отображаются у пользователя после снятия ИП в доступных типах плательщика
    public void organizationsIPAreNotDisplayedInTheUserAfterRemovingTheIPInTheAvailablePayerTypes() {
        determineWhetherVersionsOfWorkingWithOrganization();
        //!!!!!!!!!! Такая логика, клиенты не жалуются на это (админ не может запретить уже созданным типам организаций покупать товары), по этому не проверять на это у расширенной версии
        if (versionsOfWorkingWithOrganizationsExtended == false){
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            returningSettingsBackIfCatalogBroken();
            navigationToOrganizationTab();
            MethodsForAddingOrganizationsWithExtendedVersion org = new MethodsForAddingOrganizationsWithExtendedVersion();
            if (versionsOfWorkingWithOrganizationsExtended == true){
                org.creatingThreeOrganizationForExtendedVersion();
            }else {
                org.creatingThreeOrganizationForStandardVersion();
            }
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
    @Test //19. Проверка вывода цен small Opt в корзине в доп.товарах, возможность добавления товара по этой цене
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
        navigationToCart();
        addingToSearchFieldWordForSearchInAdditionalProductsGefestGasStove();
        checkingThatThePricesInTheCartForAdditionalProductsIsDisplayedAsForSmallOptGroup();
    }
    @Test //20. Отображение размера скидки и минимальной доступной цены без скидки в корзине
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
        explicitWaiting();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        selectTheSectionWithGasStoves();
        enterTheMaximumAvailableQuantityOfThisProduct();
        memorizingDiscountedAndNonDiscountedPricesForGefest();
        checkingThatTheDiscountEnteredAndDisplayedInTheCatalogAreTheSame();
        addingAnItemToTheCartWithPriceChecking();
        checkingTheDiscountAmountAndTheAvailablePriceWithoutDiscount();
    }
    @Test //21. Проверка корректности состава товаров (ТП) на детальной странице оформленного заказа
    public void checkingTheCorrectnessOfTheProductsOfTheGoodsOnTheDetailedPageOfTheCompletedOrderForTP() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        returningSettingsBackIfCatalogBroken();
        deletingProductsFromTheCart();
        enableEditMode();
        navigationToCatalogTab();
        navigationToComponentOfCatalogSetting();
        choiceCatalogWithOnlyOffers();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        changeTheQuantityOfRandomProduct();
        checkingThatThePriceOfTheAddedProductHasBeenCalculated();
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
    }










    @Test //0. Тоже самое что и 15 тест, но запускается перед автотестами
    public void a_returningSettingsBack() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToSystemSettings();
        navigationToSettingOfQuantitativeAccountingForTheProduct();
        enableQuantitativeAccountingForTheProductsInCatalog();
        navigationToGasStoveSetting();
        returningQuantitativeAccountingAtTheGefestGasStoveByDefault();
        returningTheKaiserGasStoveSettingByDefault();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        tryTurnOffShowTheQuantityOfProductsInStorage();
        navigationToComponentOfCatalogSetting();
        choiceStandardCatalog();
    }

    @Test //last Возвращение настроек назад (количественный учет в каталоге, а в товаре - по умолчанию; откл вывод складов)
    public void w_returningSettingsBack() {
        a_returningSettingsBack();
    }

}
