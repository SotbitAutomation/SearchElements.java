package Events;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Events extends MethodsForEvents{
    @Test //1. Проверка почтовых событий и их статусов после регистрации пользователя
    public void checkingMailEventsAndTheirStatusesAfterUserRegistration() {
        //arrange
        navigationToRegistrationTab();
        //act
        registr.choiceIP();
        registr.enterINNManually();
        registr.fillingFieldsOnTheRegistrationTab(registr.arrayWithExistingLocatorsForIP);
        driver.findElement(registr.registerButtonOnRegistrationTabLocator).click();
        //act
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheEventTableFromAdminMeanPage();
        determineWhetherRegistrationUserNeedsToBeConfirmed();
        checkingEventAboutNeededToConfirmUserRegistration();
        determineWhetherRegistrationOrganizationNeedsToBeConfirmed();
        checkingEventAboutTheRegistrationOrganization();
        checkingTheStatusEventAboutNeededConfirmUserRegistration();
        checkingTheStatusEventThatYouNeedToConfirmOfTheRegistrationOfTheOrganization(expectedSuccessExec);
    }
    @Test //2. Проверка таблицы со списком компаний в админ части
    public void checkingTheTableInTheAdminPartWithAListOfCompanies() {
        //arrange
        navigationToRegistrationTab();
        //act
        registr.choiceIP();
        registr.enterINNManually();
        registr.fillingFieldsOnTheRegistrationTab(registr.arrayWithExistingLocatorsForIP);
        driver.findElement(registr.registerButtonOnRegistrationTabLocator).click();
        //act
        confirmRegistrations();
        navigationToThtListOfCompanies();
        searchForNeededColumnByUniqueAttribute("ACTIVE");
        checkingThatThisColumnThisCompanyHaveThisData(registr.email);
        searchForNeededColumnByUniqueAttribute("NAME");
        checkingThatThisColumnThisCompanyHaveThisData(registr.nameCompany);
        searchForNeededColumnByUniqueAttribute("INN");
        checkingThatThisColumnThisCompanyHaveThisData(registr.iNNManual);
        searchForNeededColumnByUniqueTitle("Активность");
        checkingThatThisColumnThisCompanyHaveThisData("Да");
        searchForNeededColumnByUniqueAttribute("STATUS");
        checkingThatThisColumnThisCompanyHaveThisData("Подтверждена");
        String timeStamp = new SimpleDateFormat("ddMMyyyy").format(Calendar.getInstance().getTime());
        searchForNeededColumnByUniqueAttribute("DATE_CREATE");
        checkingThatThisColumnThisCompanyHaveThisDate(timeStamp);
        searchForNeededColumnByUniqueAttribute("DATE_UPDATE");
        checkingThatThisColumnThisCompanyHaveThisDate(timeStamp);
    }
    @Test //3. Отображение подтверждения того, что пользователь является администратором (менеджером) в таблице
    public void displayingConfirmationThatTheUserIsAnAdministratorInTheTable() {
        //arrange
        navigationToRegistrationTab();
        //act
        registr.choiceIP();
        registr.enterINNManually();
        registr.fillingFieldsOnTheRegistrationTab(registr.arrayWithExistingLocatorsForIP);
        driver.findElement(registr.registerButtonOnRegistrationTabLocator).click();
        //act
        confirmRegistrations();
        navigationToTablesFromAdmin();
        openTableWithSotbitAuthStaff();
        openLastPageSotbitAuthStaffTable();
        checkingThatLastCreatedUserIsManager();
    }
    @Test //4. Уведомление сотруднику об отказе в присоединении к компании
    public void notificationToAnEmployeeAboutRefusalToJoinTheCompany() {
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
        registr.rejectEmployee();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheEventTableFromAdminMeanPage();
        checkingEventThatEmployeeWasRejected();
        checkingTheStatusLastEvent(expectedSuccessExec);
    }
    @Test //5. Создание почтового события, после добавления сотрудника управляющим компании.
    public void creatingAMailEventAfterAddingAnEmployeeToTheCompanySManagement() {
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
        tryConfirmRegistrationOfOrganizationInB2bFromTheUser();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        selectionFromTheHeaderOrganization(nameCompany);
        fillingAllFieldsOnTheRegisterNewEmployee();
        registrationUserFromTheEmployeesTab();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheEventTableFromAdminMeanPage();
        checkingEventThatManagerHasAddedANewEmployeeToHisCompany();
        checkingTheStatusLastEvent(expectedSuccessExec);
    }
    @Test //6. Создание почтового события, после Удаления сотрудника из текущей компании
    public void creatingAMailEventAfterDeletingAnEmployeeFromTheCurrentCompany() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToOrganizationTab();
        navigationToAddOrganizationTab();
        selectionFromDropDownListIndividualBusinessman();
        fillingFieldsForCreatingOrganization();
        creatingOrganization();
        tryConfirmRegistrationOfOrganizationInB2bFromTheUser();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeEmployee();
        logInToB2B();
        navigationToOrganizationTab();
        requestToJoinTheCompany();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        selectionFromTheHeaderOrganization(nameCompany);
        confirmEmployeeRequest();
        navigationToPersonsTab();
        deletingEmployee();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheEventTableFromAdminMeanPage();
        checkingEventThatEmployeeHasBeenDeletedFromCompany();
        checkingTheStatusLastEvent(expectedSuccessExec);
    }
    @Test //7. Уведомление о прохождении модерации пользователя от администратора
    public void notificationOfTheUserSModerationFromTheAdministrator() {
        //arrange
        navigationToRegistrationTab();
        //act
        determineWhetherRegistrationUserNeedsToBeConfirmed();
        if (doNeedToConfirmRegistrationUser){
            registr.choiceIP();
            registr.enterINNManually();
            registr.fillingFieldsOnTheRegistrationTab(registr.arrayWithExistingLocatorsForIP);
            registr.tryConfirmRegistration();
            registr.entranceToB2BFromRegistrationTab();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            navigationToTheEventTableFromAdminMeanPage();
            checkingEventAboutNeededToConfirmUserRegistration();
            checkingTheStatusEventAboutNeededConfirmUserRegistration();
            checkingEventAboutConfirmUserRegistration();
            checkingTheStatusEventOfTheUserSRegistrationHasBeenConfirmedByTheAdmin(expectedSuccessExec);
            determineWhetherRegistrationOrganizationNeedsToBeConfirmed();
            checkingEventAboutTheRegistrationOrganization();
            checkingTheStatusEventThatYouNeedToConfirmOfTheRegistrationOfTheOrganization(expectedSuccessExec);
        }else {
            System.out.println("Если подтверждать регистрацию пользователя не нужно, то никакие события о пользователе не создаются");
        }
    }

    @Test //8. Уведомление подтверждения регистрации компании от админа
    public void eventAboutCompanyRegistrationConfirmationFromTheAdmin() {
        //arrange
        navigationToRegistrationTab();
        //act
        registr.choiceIP();
        registr.enterINNManually();
        registr.fillingFieldsOnTheRegistrationTab(registr.arrayWithExistingLocatorsForIP);
        driver.findElement(registr.registerButtonOnRegistrationTabLocator).click();
        confirmRegistrations();
        navigationToTheEventTableFromAdmin();
        checkingEventAboutTheRegistrationOrganizationHasBeenApproved();
        checkingTheStatusEventThatYouNeedToConfirmOfTheRegistrationOfTheOrganization(expectedSuccessExec);
        checkingTheStatusEventThatTheOrganizationSRegistrationHasBeenConfirmedByTheAdministrator(expectedSuccessExec);
    }
    @Test //9. Уведомление об отклонении регистрации пользователя
    public void eventAboutUserRegistrationRejection() {
        //arrange
        navigationToRegistrationTab();
        //act
        determineWhetherRegistrationUserNeedsToBeConfirmed();
        if (doNeedToConfirmRegistrationUser){
            registr.choiceIP();
            registr.enterINNManually();
            registr.fillingFieldsOnTheRegistrationTab(registr.arrayWithExistingLocatorsForIP);
            registr.tryRejectRegistration();
            registr.fillingFieldsOnTheLogInTab();
            registr.logInFromAuthorizationTabWithRejectedStatus();
            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class, 'validation-invalid-label')][contains(text(), 'заблокирован')]")).isDisplayed());
            navigationToMeanPageByUrl();
            Assert.assertTrue(driver.findElement(By.cssSelector(".login-form")).isDisplayed());
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            navigationToTheEventTableFromAdminMeanPage();
            checkingEventAboutRejectionUsersRegistration();
            checkingTheStatusEventThatAdminRejectedUserSRegistration(expectedSuccessExec);
        }else {
            System.out.println("Если подтверждать регистрацию пользователя не нужно, то никакие события о пользователе не создаются");
        }
    }
    @Test //10. Уведомление отклонения регистрации компании
    public void eventAboutRejectionOfCompanyRegistration() {
        //arrange
        determineWhetherRegistrationOrganizationNeedsToBeConfirmed();
        if (doNeedToConfirmRegistrationOrganization){
            navigationToRegistrationTab();
            //act
            registr.choiceIP();
            registr.enterINNManually();
            registr.fillingFieldsOnTheRegistrationTab(registr.arrayWithExistingLocatorsForIP);
            driver.findElement(registr.registerButtonOnRegistrationTabLocator).click();
            registr.navigationToPageForConfirmUserRegistration();
            registr.navigationToRegistrationOrganizationsTableFromAdminPart();
            registr.rejectTheRegistrationOfTheLasOrOrganization();
            navigationToTheEventTableFromAdmin();
            checkingEventAboutRejectionCompanyRegistration();
            checkingTheStatusEventThatAdminRejectedCompanyRegistration(expectedSuccessExec);
        }else {
            System.out.println("Если подтверждать регистрацию организации не нужно, то никакие события об организации не создаются");
        }
    }
    @Test //11. Уведомление управляющему компании о новой заявке в сотрудники
    public void eventAboutRequestToJoinTheCompanyFromAnEmployee() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToOrganizationTab();
        navigationToAddOrganizationTab();
        selectionFromDropDownListIndividualBusinessman();
        fillingFieldsForCreatingOrganization();
        creatingOrganization();
        tryConfirmRegistrationOfOrganizationInB2bFromTheUser();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeEmployee();
        logInToB2B();
        navigationToOrganizationTab();
        requestToJoinTheCompany();
        exitFromB2B();
        //act
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheEventTableFromAdminMeanPage();
        checkingEventAboutRequestToJoinTheCompanyFromAnEmployee();
        checkingTheStatusEventAboutRequestToJoinTheCompanyFromAnEmployee(expectedSuccessExec);
    }
    @Test //12. Уведомление сотруднику о присоединении к компании
    public void eventThatTheEmployeeHasBeenConfirmedToJoinTheCompany() {
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
        tryConfirmRegistrationOfOrganizationInB2bFromTheUser();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeEmployee();
        logInToB2B();
        navigationToOrganizationTab();
        requestToJoinTheCompany();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        confirmEmployeeRequest();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheEventTableFromAdminMeanPage();
        checkingEventAboutConfirmJoiningToTheCompanyFromAnEmployee();
        checkingStatusEventAboutConfirmJoiningToTheCompanyFromAnEmployee(expectedSuccessExec);
    }
    @Test //13. Уведомление сотруднику об отказе в присоединении к компании
    public void eventAboutRefusalToJoinTheCompanyToAnEmployee () {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToOrganizationTab();
        navigationToAddOrganizationTab();
        selectionFromDropDownListIndividualBusinessman();
        fillingFieldsForCreatingOrganization();
        creatingOrganization();
        tryConfirmRegistrationOfOrganizationInB2bFromTheUser();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeEmployee();
        logInToB2B();
        navigationToOrganizationTab();
        requestToJoinTheCompany();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        rejectEmployeeRequest();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        //act
        navigationToTheEventTableFromAdminMeanPage();
        checkingEventThatTheManagerHasRejectedTheJoiningOfThisEmployeeToTheCompany();
        checkingTheStatusEventAboutRejectedJoiningToTheCompanyFromAnEmployee(expectedSuccessExec);
    }
    @Test //14. Изменение данных созданной организациии у юзера
    public void checkAvailableActionsWithOrganizationsFromTheUser() {
        //arrange
        try{
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToOrganizationTab();
            checkingThatOrganizationIsConfirmed();
        }catch (Exception e){ //Если нет одобренной организации для проверки > создать ее
            exitFromB2B();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToOrganizationTab();
            navigationToAddOrganizationTab();
            selectionFromDropDownListIndividualBusinessman();
            fillingFieldsForCreatingOrganization();
            creatingOrganization();
            tryConfirmRegistrationOfOrganizationInB2bFromTheUser();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            checkingThatOrganizationIsConfirmed();
            navigationToOrganizationTab();
            checkingThatOrganizationIsConfirmed();
        }
        sortingOrganizationByDecrease();
        checkingAvailableActionsWithOrganization(false);
        //act
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheEventTableFromAdminMeanPage();
        checkingEventAboutNeededToConfirmTheCompanyAfterChanging();
        checkingStatusOfEventAboutNeededToConfirmTheCompanyAfterChanging(expectedSuccessExec);
        checkingEventAboutConfirmTheCompanyAfterChanging();
        checkingStatusOfEventAboutConfirmTheCompanyAfterChanging(expectedSuccessExec);
    }
    @Test //15. Уведомление, что администратор не подтвердил изменение данных организации
    public void eventThatTheAdministratorHasNotConfirmedTheChangeOfTheOrganizationSData() {
        //arrange
        try{
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToOrganizationTab();
            checkingThatOrganizationIsConfirmed();
        }catch (Exception e){ //Если нет одобренной организации для проверки > создать ее
            exitFromB2B();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToOrganizationTab();
            navigationToAddOrganizationTab();
            selectionFromDropDownListIndividualBusinessman();
            fillingFieldsForCreatingOrganization();
            creatingOrganization();
            tryConfirmRegistrationOfOrganizationInB2bFromTheUser();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            checkingThatOrganizationIsConfirmed();
            navigationToOrganizationTab();
            checkingThatOrganizationIsConfirmed();
        }
        sortingOrganizationByDecrease();
        openTabsInTheOrganizationDetailedPage();
        changeSomeDataInTheOrganizationTab();
        registr.navigationToPageForConfirmUserRegistration();
        registr.navigationToRegistrationOrganizationsTableFromAdminPart();
        //act
        registr.rejectTheRegistrationOfTheLasOrOrganization();
        navigationToTheEventTableFromAdmin();
        checkingEventAboutRejectTheCompanyByAdminAfterChanging();
        checkingStatusOfEventAboutRejectTheCompanyByAdminAfterChanging(expectedSuccessExec);
    }




}
