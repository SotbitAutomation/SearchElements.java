package OrganizationsWithStandardVersion;

import BaseActions.Retry;
import OrganizationsWithExtendedVersion.MethodsForAddingOrganizationsWithExtendedVersion;
import org.testng.annotations.Test;


public class AddingOrganizationsWithStandardVersion extends MethodsForAddingOrganizationsWithExtendedVersion {

    @Test(retryAnalyzer = Retry.class) //1. Добавление организация (ИП) у админа
    public void addOrganizationIPFromTheAdmin() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (!versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            navigationToOrganizationTab();
            navigationToAddOrganizationTab();
            selectionFromDropDownListIndividualBusinessman();
            fillingFieldsForCreatingOrganization();
            creatingOrganization();
        }
    }

    @Test(retryAnalyzer = Retry.class) //2. Добавление организация (ИП) у юзера
    public void addOrganizationIPFromTheUser() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (!versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToOrganizationTab();
            navigationToAddOrganizationTab();
            selectionFromDropDownListIndividualBusinessman();
            fillingFieldsForCreatingOrganization();
            creatingOrganization();
        }
    }

    @Test(retryAnalyzer = Retry.class) //3. Добавление организация (ЮР) у админа
    public void addOrganizationLegalPersonFromTheAdmin() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (!versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            navigationToOrganizationTab();
            navigationToAddOrganizationTab();
            selectionFromDropDownListLegalPerson();
            fillingFieldsForCreatingOrganization();
            creatingOrganization();
        }
    }

    @Test(retryAnalyzer = Retry.class) //4. Добавление организация (ЮР) у юзера
    public void addOrganizationLegalPersonFromTheUser() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (!versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToOrganizationTab();
            navigationToAddOrganizationTab();
            selectionFromDropDownListLegalPerson();
            fillingFieldsForCreatingOrganization();
            creatingOrganization();
        }
    }

    @Test(retryAnalyzer = Retry.class) //5. Проверка полей (обязательные / не обязательные)
    public void checkFieldsRequiredOrOptionalFromTheUser() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (!versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToOrganizationTab();
            navigationToAddOrganizationTab();
            checkingThatTheFieldsAreRequired();
        }
    }

    @Test(retryAnalyzer = Retry.class) //6. Проверка доступных действий над организациями у админа
    public void checkAvailableActionsWithOrganizationsFromTheAdmin() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (!versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            navigationToOrganizationTab();
            checkingAvailableActionsWithOrganizationInStandardVersion();
        }
    }

    @Test(retryAnalyzer = Retry.class) //7. Проверка доступных действий над организациями у юзера
    public void checkAvailableActionsWithOrganizationsFromTheUser() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (!versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToOrganizationTab();
            addingOrganizationIfThereIsNoOrganizationsInStandardVersion();
            checkingAvailableActionsWithOrganizationInStandardVersion();
        }
    }

    @Test(retryAnalyzer = Retry.class) //8. Проверка корректности вывода профилей покупателя (организаций)
    public void checkingCorrectnessOfTheOutputOfCustomerProfiles() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (!versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            navigationToProfilesOfBuyers();
            checkingThatNeededProfilesOfBuyersIsSelected();
        }
    }

    @Test(retryAnalyzer = Retry.class)
    //9. Проверить валидацию полей формы заданных в настройках свойств (настроить максимальную длину символов)
    public void checkValidationOfFormFieldsSpecifiedInThePropertiesSettings() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (!versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            navigationToOrganizationTab();
            navigationToAddOrganizationTab();
            selectionFromDropDownListIndividualBusinessman();
            checkingThatCapacityFieldIsFortySymbols();
            navigationToTableOfPropertiesSetting();
            changeAllPropertyOfNameCompanyToOneHundredSymbols();
            navigationToMeanPageByUrl();
            navigationToOrganizationTab();
            navigationToAddOrganizationTab();
            selectionFromDropDownListIndividualBusinessman();
            checkingThatCapacityFieldIsOneHundredSymbols();
            checkingMinimumAndMaximumStringLength();
        }
    }

    @Test(retryAnalyzer = Retry.class) //10. Проверить соответствия набора полей заявленным в настройках системы
    public void checkThatCreatedPropertiesIsOutputIn2b2() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (!versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            navigationToTableOfPropertiesSetting();
            rememberingActivePropertiesForLegalPerson();
            rememberingActivePropertiesForIndividualBusinessman();
            navigationToMeanPageByUrl();
            navigationToOrganizationTab();
            navigationToAddOrganizationTab();
            checkingThatAllFieldsForLegalPersonIsOutPut();
            checkingThatAllFieldsForIndividualBusinessman();
        }
    }

    @Test(retryAnalyzer = Retry.class) //11. Проверить корректность автоподстановки данных при заполнении ИНН
    public void checkCorrectnessOfAutomaticDataSubstitutionWhenFillingInTheTIN() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (!versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToOrganizationTab();
            navigationToAddOrganizationTab();
            selectionFromDropDownListIndividualBusinessman();
            fillingFieldsForCreatingOrganization();
            enteringINNUsingListOfCompanies();
            checkingThatINNIsChosen();
            creatingOrganization();
        }
    }

    @Test //12. Проверить корректность сохранения данных существующей организации
    public void checkCorrectnessOfDataSavingExistingOrganization() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (!versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToOrganizationTab();
            addingOrganizationIfThereIsNoOrganizationsInStandardVersion();
            sortingOrganizationByDecrease();
            changeDataInOrganization();
            navigationToCatalogTab();
            increaseQuantityOfFirstProduct();
            navigationToCart();
            driver.findElement(buttonMakeOrderInTheCartLocator).click();
            checkingThatDataHasBeenChanged(); //ошибка b2b, название радиобаттана не меняется после изменения названия комании (как и название орг. в списке орг-й)
        }
    }

    @Test(retryAnalyzer = Retry.class)
    //12.1. !!!Пока ошибка в 12 (клиенты не жалуются, поэтому пока исправляться не будет), выполнять этот!
    // Проверить корректность сохранения данных существующей организации
    public void checkCorrectnessOfDataSavingExistingOrganization2() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (!versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToOrganizationTab();
            addingOrganizationIfThereIsNoOrganizationsInStandardVersion();
            sortingOrganizationByDecrease();
            changeDataInOrganization();
            navigationToCatalogTab();
            increaseQuantityOfFirstProduct();
            navigationToCart();
            scrollToTheElement(buttonMakeOrderInTheCartLocator);
            driver.findElement(buttonMakeOrderInTheCartLocator).click();
            checkingThatDataHasBeenChanged2();
        }
    }

    @Test(retryAnalyzer = Retry.class) //13. Проверка работы настройки списка организаций (для всех)
    public void checkingOperationOfTheOrganizationListSettingsForAll() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (!versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            navigationToOrganizationTab();
            removeTheDisplayOfAllColumnsExceptCode();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToOrganizationTab();
            checkingThatOnlyColumnWithCodeIsDisplayed();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            navigationToOrganizationTab();
            selectingAllColumnsToDisplay();
        }
    }

    @Test(retryAnalyzer = Retry.class) //14. Проверка работы настройки списка организаций (по умолчанию)
    public void checkingOperationOfTheOrganizationListSettingsByDefault() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (!versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            navigationToOrganizationTab();
            removeTheDisplayOfAllColumnsExceptCode();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToOrganizationTab();
            checkingThatOnlyColumnWithCodeIsDisplayed();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            navigationToOrganizationTab();
            //act
            selectingByDefaultSetting();
        }
    }

    @Test(retryAnalyzer = Retry.class)
    //15. Вывод количества элементов в счетчике "Всего"
    public void outputOfTheNumberOfItemsInTheTotalCounter() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToOrganizationTab();
        checkingTheNumberOfOrganizationsInTheTotalCounter();
    }

    @Test(retryAnalyzer = Retry.class)
    //16. Поиск организации в разделе "Мои организации"
    public void searchForAnOrganizationInTheMyOrganizationsSection() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToOrganizationTab();
        searchForRandomOneFromTheUserSExistingOrganizations();
        deletingNumberForSearchOrganizationUsingCloseIcon();
        openDetailPageFirstOrganization();
        navigationToOrganizationTab();
        //searchForRandomOneFromTheUserSExistingOrganizations();
        deletingNameForSearchOrganizationUsingFieldForSearch();
    }


//    @Test //Удаление организаций))
//    public void Deleting() {
//        //arrange
//        navigationToAuthorizationTab();
//        //act
//        fillingFieldsOnTheLogInTabLikeAdmin();
//        logInToB2B();
//        driver.navigate().to("http://b2b.floorstock.sotbit.com/bitrix/admin/sotbit.auth_company_list.php?lang=ru");
//        explicitWaiting();
//
//        for (int i = 0; i < 30; i++) {
//            Assert.assertNotEquals("Компания №2", driver.findElement(By.xpath("(//*[@class='adm-list-table-cell'])[1] /*")).getText());
//            driver.findElement(By.xpath("(//*[@class='adm-list-table-cell'])[1] /*")).click();
//            driver.findElement(By.xpath("//*[@id='btn_delete']")).click();
//            standardConfirmationOfTheActionOnThePage();
//        }
//    }

}
