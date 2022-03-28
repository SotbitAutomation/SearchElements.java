package OrganizationsWithExtendedVersion;

import BaseActions.Retry;
import BeforeTest.SettingUpAutotestsForB2BSettings;
import RegistrationAndAuthorization.MethodsForRegistrationAndAuthorization;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ListOfEmployees extends MethodsForAddingOrganizationsWithExtendedVersion {
    @Test(retryAnalyzer = Retry.class)//0. Создание руководителей с организациями и сотрудников этих организаций
    public void a_creatingBossesWithOrganizationsAndEmployees() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            SettingUpAutotestsForB2BSettings createUsers = new SettingUpAutotestsForB2BSettings();
//            createUsers.g_creatingUsersForAutomationTests();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            creatingOrganizationForUser();
            if (themeColorBlack){
                CompanyNameToJoinEmployees = driver.findElement(By.cssSelector(".auth-company-change__current")).getText();
            }else {
                CompanyNameToJoinEmployees = driver.findElement(By.cssSelector(".header-elements")).getText();
            }
            navigationToPersonsTab();
            while (driver.findElements(By.cssSelector(".staff-personal-photo")).size() < 10) {
                creatingNewEmployeeForBoss();
            }
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
            if (themeColorBlack){
                quantityOrganizationOfUser = driver.findElements(By.xpath("//*[@class='auth-company-change__list'] /li")).size();
            }else {
                navigationToOrganizationTab();
                quantityOrganizationOfUser = driver.findElements(By.xpath("//*[@class='header-elements'] //*[@class='dropdown-item']")).size();
            }
            while (quantityOrganizationOfUser < 5) {
                MethodsForRegistrationAndAuthorization registr = new MethodsForRegistrationAndAuthorization();
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
                navigationToAuthorizationTab();
                fillingFieldsOnTheLogInTabLikeEmployee();
                logInToB2B();
                navigationToOrganizationTab();
                nameCompany = CompanyNameToJoinEmployees;
                requestToJoinTheCompany();
                navigationToAuthorizationTab();
                registr.fillingFieldsOnTheLogInTab();
                registr.logInFromAuthorizationTab();
                confirmEmployeeRequest();
                navigationToAuthorizationTab();
                fillingFieldsOnTheLogInTabLikeEmployee();
                logInToB2B();
                quantityOrganizationOfUser++;
            }
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToOrganizationTab();
            if (themeColorBlack){
                quantityOrganizationOfUser = driver.findElements(By.xpath("//*[@class='auth-company-change__list'] /li")).size();
            }else {
                quantityOrganizationOfUser = driver.findElements(By.xpath("//*[@class='header-elements'] //*[@class='dropdown-item']")).size();
            }
//            if (quantityOrganizationOfUser == 0) {
//                System.out.println("что-то пошло не так, у юзера не было организаций");
//                creatingOrganizationForUser();
//            }
            if (themeColorBlack){
                CompanyNameToJoinEmployees = driver.findElement(By.cssSelector(".auth-company-change__current")).getText();
            }else {
                CompanyNameToJoinEmployees = driver.findElement(By.cssSelector(".header-elements")).getText();
            }
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
            navigationToOrganizationTab();
            nameCompany = CompanyNameToJoinEmployees;
            requestToJoinTheCompany();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            confirmEmployeeRequest();
        }
    }


    @Test(retryAnalyzer = Retry.class)//1. Просмотр списка сотрудников
    public void viewingListOfEmployees() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToPersonsTab();
            checkingListOfEmployees();
        }
    }

    @Test(retryAnalyzer = Retry.class)//2. Просмотр списка организаций, к которым относиться сотрудник
    public void viewingListOfOrganizationOfEmployee() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToPersonsTab();
            checkingQuantityOrganizationOfEmployee();
        }
    }

    @Test(retryAnalyzer = Retry.class)
    //3. При выбранном чекбоксе "Показать сотрудников всех организаций" отображаются все сотрудники всех организаций в которых состоит пользователь
    public void whenShowEmployeesOfAllOrganizationsCheckboxIsSelectedAllEmployeesOfAllOrganizationsAreDisplayed() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
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
    }

    @Test(retryAnalyzer = Retry.class)
    //4. При выключеном чекбоксе "Показывать всех сотрудников", отображаеются только сотрудники выбранной компании.
    public void whenShowAllEmployeesCheckboxIsDisabledOnlyEmployeesOfTheSelectedCompanyAreDisplayed() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
            navigationToPersonsTab();
            checkingEmployeesOthersOrganizationDisable();
        }
    }

    @Test(retryAnalyzer = Retry.class) //5. Сотрудник не может добавить нового сотрудника
    public void checkThatEmployeeCannotAddANewEmployee() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
            navigationToOrganizationTab();
            sortingOrganizationByDecrease();
            nameCompany = driver.findElement(By.xpath("(//span[contains(text(), 'NameCompany')])[last()-1]")).getText();
            selectionFromTheHeaderOrganization(nameCompany);
            navigationToPersonsTab();
            checkingThatThereAreNoButtonForAddEmployee();
        }
    }

    @Test(retryAnalyzer = Retry.class) //6. Список сотрудников организации в которой проходишь модерацию не отображается
    public void checkThatListOfEmployeesOfTheOrganizationInWhichEmployeeAreUndergoingModerationIsNotDisplayed() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
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
    }

    @Test(retryAnalyzer = Retry.class)
    //7. После прохождения модерации, в списке сотрудников отображается сотрудники организаций, в которой пользователь прошёл модерацию.
    public void checkThatAfterPassingModerationEmployeesOfOrganizationsInWhichUserHasPassedModerationAreDisplayedInTheListOfEmployees() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            registerBosWithOrganization();
            registerEmployeeForBosSOrganization();
            navigationToOrganizationTab();
            nameCompany = CompanyNameToJoinEmployees;
            requestToJoinTheCompany();
            navigationToPersonsTab();
            checkingThatListOfEmployeeIsEmpty();
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
    }

    @Test(retryAnalyzer = Retry.class) //8. Сотрудник отображается у управляющего компанией в табе "Заявки"
    public void checkThatEmployeeIsDisplayedByCompanyManagerInApplicationsTab() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            registerBosWithOrganization();
            registerEmployeeForBosSOrganization();
            navigationToOrganizationTab();
            nameCompany = CompanyNameToJoinEmployees;
            requestToJoinTheCompany();
            navigationToPersonsTab();
            checkingThatListOfEmployeeIsEmpty();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTab(tempValue, tempValueForPassword);
            logInToB2B();
            navigationToPersonsTab();
            checkingThatUserSNameIsDisplayed();
        }
    }

    @Test(retryAnalyzer = Retry.class) //9. Поп-ап окно "регистрация нового сторудника" закрылось после нажатия "Отмена"
    public void checkThatPopUpWindowRegistrationOfNewEmployeeIsClosedAfterClickingCancel() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToPersonsTab();
            checkingThatPopUpWindowIsClosedAfterClickCancel();
        }
    }

    @Test(retryAnalyzer = Retry.class) //10. Внесённые данные отображаются, после повторного открытия поп-ап окна.
    public void checkThatEnteredDataIsDisplayedAfterReopeningPopUpWindow() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToPersonsTab();
            checkingThatEnteredDataIsDisplayedAfterReopeningPopUpWindow();
        }
    }

    @Test (retryAnalyzer = Retry.class)//11. Выбор рандомной организации из шапки
    public void selectingOrganizationFromTheHeader() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
            if (themeColorBlack){
                quantityOrganizationOfUser = driver.findElements(By.xpath("//*[@class='auth-company-change__list'] /li")).size();
            }else {
                navigationToOrganizationTab();
                quantityOrganizationOfUser = driver.findElements(By.xpath("//*[@class='header-elements'] //*[@class='dropdown-item']")).size();
            }
            if (quantityOrganizationOfUser < 3) {
                a_creatingBossesWithOrganizationsAndEmployees();
                navigationToAuthorizationTab();
                fillingFieldsOnTheLogInTabLikeEmployee();
                logInToB2B();
            }
            selectRandomOrganizationFromHeader();
        }
    }

    @Test(retryAnalyzer = Retry.class) //12. Проверка, что пользователь отображается только те организации, в которых он прошёл модерацию
    public void checkingThatUserSeeOnlyThoseOrganizationsInWhichHeHasPassedModeration() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
            if (themeColorBlack){
                quantityOrganizationOfUser = driver.findElements(By.xpath("//*[@class='auth-company-change__list'] /li")).size();
            }else {
                navigationToOrganizationTab();
                quantityOrganizationOfUser = driver.findElements(By.xpath("//*[@class='header-elements'] //*[@class='dropdown-item']")).size();
            }
            if (quantityOrganizationOfUser < 2) {
                a_creatingBossesWithOrganizationsAndEmployees();
                navigationToAuthorizationTab();
                fillingFieldsOnTheLogInTabLikeEmployee();
                logInToB2B();
                quantityOrganizationOfUser++;
            }
            creatingOrganizationWithoutConfirm();
            checkingThatOrganizationWithoutConfirmIsUnavailableInHeader();
        }
    }
}
