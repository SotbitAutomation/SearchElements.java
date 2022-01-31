package OrganizationsitsWithStandardVersion;

import OrganizationsWithExtendedVersion.MethodsForAddingOrganizationsWithExtendedVersion;
import RegistrationAndAuthorization.MethodsForRegistrationAndAuthorization;
import org.junit.Test;


public class AddingOrganizationsWithStandardVersion extends MethodsForAddingOrganizationsWithExtendedVersion {
    MethodsForRegistrationAndAuthorization registr = new MethodsForRegistrationAndAuthorization();

    @Test //1. Добавление организация (ИП) у админа
    public void addOrganizationIPFromTheAdmin() {
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

    @Test //2. Добавление организация (ИП) у юзера
    public void addOrganizationIPFromTheUser() {
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

    @Test //3. Добавление организация (ЮР) у админа
    public void addOrganizationLegalPersonFromTheAdmin() {
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

    @Test //4. Добавление организация (ЮР) у юзера
    public void addOrganizationLegalPersonFromTheUser() {
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

    @Test //5. Проверка полей (обязательные / не обязательные)
    public void checkFieldsRequiredOrOptionalFromTheUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToOrganizationTab();
        navigationToAddOrganizationTab();
        checkingThatTheFieldsAreRequired();
    }

    @Test //6. Проверка доступных действий над организациями у админа
    public void checkAvailableActionsWithOrganizationsFromTheAdmin() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToOrganizationTab();
        checkingAvailableActionsWithOrganizationInStandardVersion();
    }

    @Test //7. Проверка доступных действий над организациями у юзера
    public void checkAvailableActionsWithOrganizationsFromTheUser() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToOrganizationTab();
        addingOrganizationIfThereIsNoOrganizationsInStandardVersion();
        checkingAvailableActionsWithOrganizationInStandardVersion();
    }
    @Test //8. Проверка корректности вывода профилей покупателя (организаций)
    public void CheckingCorrectnessOfTheOutputOfCustomerProfiles() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToProfilesOfBuyers();
        checkingThatNeededProfilesOfBuyersIsSelected();
    }
    @Test //9. Проверить валидацию полей формы заданных в настройках свойств
    public void CheckValidationOfFormFieldsSpecifiedInThePropertiesSettings () {
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
    @Test //10. Проверить соответствия набора полей заявленным в настройках системы
    public void CheckThatCreatedPropertiesIsOutputIn2b2 () {
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
    @Test //11. Проверить корректность автоподстановки данных при заполнении ИНН
    public void CheckCorrectnessOfAutomaticDataSubstitutionWhenFillingInTheTIN() {
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
    @Test //12. Проверить корректность сохранения данных существующей организации
    public void CheckCorrectnessOfDataSavingExistingOrganization() {
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
        checkingThatDataHasBeenChanged();
    }
    @Test //12.1. !!!Пока ошибка в 12 (клиенты не жалуются, поэтому пока исправляться не будет), выполнять этот!
          // Проверить корректность сохранения данных существующей организации
    public void CheckCorrectnessOfDataSavingExistingOrganization2() {
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
        checkingThatDataHasBeenChanged2();
    }
    @Test //13. Проверка работы настройки списка организаций (для всех)
    public void CheckingOperationOfTheOrganizationListSettingsForAll() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToOrganizationTab();
        removeTheDisplayOfAllColumnsExceptCode();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToOrganizationTab();
        checkingThatOnlyColumnWithCodeIsDisplayed();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToOrganizationTab();
        selectingAllColumnsToDisplay();
    }
    @Test //14. Проверка работы настройки списка организаций (по умолчанию)
    public void CheckingOperationOfTheOrganizationListSettingsByDefault() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToOrganizationTab();
        removeTheDisplayOfAllColumnsExceptCode();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToOrganizationTab();
        checkingThatOnlyColumnWithCodeIsDisplayed();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToOrganizationTab();
        //act
        selectingByDefaultSetting();




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
