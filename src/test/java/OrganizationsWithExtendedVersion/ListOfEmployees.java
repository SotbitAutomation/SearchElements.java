package OrganizationsWithExtendedVersion;

import BeforeTest.SettingUpAutotestsForB2BSettings;
import RegistrationAndAuthorization.MethodsForRegistrationAndAuthorization;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ListOfEmployees extends MethodsForAddingOrganizationsWithExtendedVersion {
    @Test //0. Создание руководителей с организациями и сотрудников этих организаций
    public void a_creatingBossesWithOrganizationsAndEmployees () {
        //arrange
        SettingUpAutotestsForB2BSettings createUsers = new SettingUpAutotestsForB2BSettings();
        createUsers.f_creatingUsersForAutomationTests();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        creatingOrganizationForUser();
        CompanyNameToJoinEmployees = driver.findElement(By.cssSelector(".auth-company-change__current")).getText();
        navigationToPersonsTab();
        while (driver.findElements(By.cssSelector(".staff-personal-photo")).size() < 10){
            creatingNewEmployeeForBoss();
        }
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeEmployee();
        logInToB2B();
        while (driver.findElements(By.xpath("//*[@class='auth-company-change__list'] /li")).size() < 4){
            MethodsForRegistrationAndAuthorization registr = new MethodsForRegistrationAndAuthorization();
            exitFromB2B();
            navigationToRegistrationTab();
            //act
            registr.choiceIP();
            registr.enterINNManually();
            registr.fillingFieldsOnTheRegistrationTab(registr.arrayWithExistingLocatorsForIP);
            registr.tryConfirmRegistration();
            registr.entranceToB2BFromRegistrationTab();
            navigationToOrganizationTab();
            CompanyNameToJoinEmployees = driver.findElement(firstNameOfOrganizationOnTheOrganizationTabOrEmployeeTab).getText();
            registr.tryConfirmRegistrationOfOrganizationInB2bFromTheUser();
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
            navigationToOrganizationTab();
            nameCompany = CompanyNameToJoinEmployees;
            requestToJoinTheCompany();
            exitFromB2B();
            navigationToAuthorizationTab();
            registr.fillingFieldsOnTheLogInTab();
            registr.logInFromAuthorizationTab();
            confirmEmployeeRequest();
            exitFromB2B();
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
        }
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        if (driver.findElements(By.cssSelector(".auth-company-change__current")).size() == 0){
            creatingOrganizationForUser();
        }
        CompanyNameToJoinEmployees = driver.findElement(By.cssSelector(".auth-company-change__current")).getText();
        navigationToPersonsTab();
        exitFromB2B();
        fillingFieldsOnTheLogInTabLikeEmployee();
        logInToB2B();
        navigationToOrganizationTab();
        nameCompany = CompanyNameToJoinEmployees;
        requestToJoinTheCompany();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        confirmEmployeeRequest();
    }


    @Test //1. Просмотр списка сотрудников
    public void viewingListOfEmployees() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToPersonsTab();
        checkingListOfEmployees();
    }

    @Test //2. Просмотр списка организаций, к которым относиться сотрудник
    public void viewingListOfOrganizationOfEmployee() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToPersonsTab();
        checkingQuantityOrganizationOfEmployee();
    }
    @Test //3. При выбранном чекбоксе "Показать сотрудников всех организаций" отображаются все сотрудники всех организаций в которых состоит пользователь
    public void whenShowEmployeesOfAllOrganizationsCheckboxIsSelectedAllEmployeesOfAllOrganizationsAreDisplayed() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeEmployee();
        logInToB2B();
        navigationToPersonsTab();
        creatingOrganizationForEmployee();
        selectionFromTheHeaderOrganization(nameCompany);
        navigationToPersonsTab();
        checkingEmployeesOthersOrganization();
    }
    @Test //4. При выключеном чекбоксе "Показывать всех сотрудников", отображаеются только сотрудники выбранной компании.
    public void whenShowAllEmployeesCheckboxIsDisabledOnlyEmployeesOfTheSelectedCompanyAreDisplayed() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeEmployee();
        logInToB2B();
        navigationToPersonsTab();
        checkingEmployeesOthersOrganizationDisable();
    }
    @Test //5. Сотрудник не может добавить нового сотрудника
    public void checkThatEmployeeCannotAddANewEmployee() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeEmployee();
        logInToB2B();
        navigationToPersonsTab();
        checkingThatThereAreNoButtonForAddEmployee();
    }
    @Test //6. Список сотрудников организации в которой проходишь модерацию не отображается
    public void checkThatListOfEmployeesOfTheOrganizationInWhichEmployeeAreUndergoingModerationIsNotDisplayed() {
        //arrange
        registerBosWithOrganization();
        registerEmployeeForBosSOrganization();
        navigationToOrganizationTab();
        navigationToOrganizationTab();
        nameCompany = CompanyNameToJoinEmployees;
        requestToJoinTheCompany();
        navigationToPersonsTab();
        checkingThatListOfEmployeeIsEmpty();
    }
    @Test //7. После прохождения модерации, в списке сотрудников отображается сотрудники организаций, в которой пользователь прошёл модерацию.
    public void checkThatAfterPassingModerationEmployeesOfOrganizationsInWhichUserHasPassedModerationAreDisplayedInTheListOfEmployees() {
        registerBosWithOrganization();
        registerEmployeeForBosSOrganization();
        navigationToOrganizationTab();
        nameCompany = CompanyNameToJoinEmployees;
        requestToJoinTheCompany();
        navigationToPersonsTab();
        checkingThatListOfEmployeeIsEmpty();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTab(tempValue, tempValueForPassword);
        logInToB2B();
        navigationToPersonsTab();
        confirmEmployeeRequest();
        exitFromB2B();
        fillingFieldsOnTheLogInTab(tempValue2, tempValueForPassword);
        logInToB2B();
        navigationToPersonsTab();
        checkingThatListOfEmployeeIsNotEmpty();
    }
    @Test //8. Сотрудник отображается у управляющего компанией в табе "Заявки"
    public void checkThatEmployeeIsDisplayedByCompanyManagerInApplicationsTab() {
        registerBosWithOrganization();
        registerEmployeeForBosSOrganization();
        navigationToOrganizationTab();
        nameCompany = CompanyNameToJoinEmployees;
        requestToJoinTheCompany();
        navigationToPersonsTab();
        checkingThatListOfEmployeeIsEmpty();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTab(tempValue, tempValueForPassword);
        logInToB2B();
        navigationToPersonsTab();
        checkingThatUserSNameIsDisplayed();
    }
    @Test //9. Поп-ап окно "регистрация нового сторудника" закрылось после нажатия "Отмена"
    public void checkThatPopUpWindowRegistrationOfNewEmployeeIsClosedAfterClickingCancel() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToPersonsTab();
        checkingThatPopUpWindowIsClosedAfterClickCancel();
    }
    @Test //10. Внесённые данные отображаются, после повторного открытия поп-ап окна.
    public void checkThatEnteredDataIsDisplayedAfterReopeningPopUpWindow() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToPersonsTab();
        checkingThatEnteredDataIsDisplayedAfterReopeningPopUpWindow();
    }
    @Test //11. Выбор рандомной организации из шапки
    public void selectingOrganizationFromTheHeader() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeEmployee();
        logInToB2B();
        try {
            driver.findElement(By.cssSelector(".auth-company-change__current")).click();
        }catch (Exception e){}
        if (driver.findElements(By.cssSelector(".auth-company-change__item")).size() < 5){
            exitFromB2B();
            a_creatingBossesWithOrganizationsAndEmployees();
            exitFromB2B();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
        }
        selectRandomOrganizationFromHeader();
    }
    @Test //12. Проверка, что пользователь отображается только те организации, в которых он прошёл модерацию
    public void checkingThatUserSeeOnlyThoseOrganizationsInWhichHeHasPassedModeration() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeEmployee();
        logInToB2B();
        try {
            driver.findElement(By.cssSelector(".auth-company-change__current")).click();
        }catch (Exception e){
            System.out.println("Список организаций в шапке не раскрылся");
        }
        if (driver.findElements(By.cssSelector(".auth-company-change__item")).size() < 2){
            exitFromB2B();
            a_creatingBossesWithOrganizationsAndEmployees();
            exitFromB2B();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
        }
        creatingOrganizationWithoutConfirm();
        checkingThatOrganizationWithoutConfirmIsUnavailableInHeader();
    }

}
