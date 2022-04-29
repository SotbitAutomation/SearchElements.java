package Events;


import BaseActions.Retry;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Events extends MethodsForEvents{
    @Test(retryAnalyzer = Retry.class) //1. Проверка почтовых событий и их статусов после регистрации пользователя
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
    @Test(retryAnalyzer = Retry.class) //2. Проверка таблицы со списком компаний в админ части
    public void checkingTheTableInTheAdminPartWithAListOfCompanies() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
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
        }else System.out.println("Нет учета списка компаний в обычной версии");

    }
    @Test(retryAnalyzer = Retry.class) //3. Отображение подтверждения того, что пользователь является администратором (менеджером) в таблице
    public void displayingConfirmationThatTheUserIsAnAdministratorInTheTable() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
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
        }else System.out.println("Нет сотрудников в нерасширенной версии рабы с компаниями");
    }
    @Test(retryAnalyzer = Retry.class) //4. Уведомление сотруднику об отказе в присоединении к компании
    public void notificationToAnEmployeeAboutRefusalToJoinTheCompany() {
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
        registr.rejectEmployee();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheEventTableFromAdminMeanPage();
        checkingEventThatEmployeeWasRejected();
        checkingTheStatusLastEvent(expectedSuccessExec);
        }else System.out.println("Нет сотрудников в нерасширенной версии рабы с компаниями");
    }
    @Test(retryAnalyzer = Retry.class) //5. Создание почтового события, после добавления сотрудника управляющим компании.
    public void creatingAMailEventAfterAddingAnEmployeeToTheCompanySManagement() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
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
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheEventTableFromAdminMeanPage();
        checkingEventThatManagerHasAddedANewEmployeeToHisCompany();
        checkingTheStatusLastEvent(expectedSuccessExec);
        }else System.out.println("Нет сотрудников в нерасширенной версии рабы с компаниями");
    }
    @Test(retryAnalyzer = Retry.class) //6. Создание почтового события, после Удаления сотрудника из текущей компании
    public void creatingAMailEventAfterDeletingAnEmployeeFromTheCurrentCompany() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
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
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            selectionFromTheHeaderOrganization(nameCompany);
            confirmEmployeeRequest();
            navigationToPersonsTab();
            deletingEmployee();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            navigationToTheEventTableFromAdminMeanPage();
            checkingEventThatEmployeeHasBeenDeletedFromCompany();
            checkingTheStatusLastEvent(expectedSuccessExec);
        }else System.out.println("Нет сотрудников в нерасширенной версии рабы с компаниями");
    }
    @Test(retryAnalyzer = Retry.class) //7. Уведомление о прохождении модерации пользователя от администратора
    public void notificationOfTheUserSModerationFromTheAdministrator() {
        //arrange
        navigationToRegistrationTab();
        //act
        determineWhetherRegistrationUserNeedsToBeConfirmed();
        determineWhetherVersionsOfWorkingWithOrganization();
        if (doNeedToConfirmRegistrationUser && versionsOfWorkingWithOrganizationsExtended){
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
            checkingTheStatusEventOfTheUserSRegistrationHasBeenConfirmedByTheAdmin("F"); //expectedSuccessExec
            determineWhetherRegistrationOrganizationNeedsToBeConfirmed();
            checkingEventAboutTheRegistrationOrganization();
            checkingTheStatusEventThatYouNeedToConfirmOfTheRegistrationOfTheOrganization(expectedSuccessExec);
        }else {
            System.out.println("Если подтверждать регистрацию пользователя не нужно, то никакие события о пользователе не создаются");
        }
    }

    @Test(retryAnalyzer = Retry.class) //8. Уведомление подтверждения регистрации компании от админа
    public void eventAboutCompanyRegistrationConfirmationFromTheAdmin() {
        determineWhetherRegistrationOrganizationNeedsToBeConfirmed();
        if (doNeedToConfirmRegistrationOrganization){
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
        } else System.out.println("Подтверждать рег. компании не нужно");
    }
    @Test(retryAnalyzer = Retry.class) //9. Уведомление об отклонении регистрации пользователя
    public void eventAboutUserRegistrationRejection() {
        //arrange
        navigationToRegistrationTab();
        //act
        determineWhetherVersionsOfWorkingWithOrganization();
        determineWhetherRegistrationUserNeedsToBeConfirmed();
        if (doNeedToConfirmRegistrationUser && versionsOfWorkingWithOrganizationsExtended){
            registr.choiceIP();
            registr.enterINNManually();
            registr.fillingFieldsOnTheRegistrationTab(registr.arrayWithExistingLocatorsForIP);
            registr.tryRejectRegistration();
            registr.fillingFieldsOnTheLogInTab();
            registr.logInFromAuthorizationTabWithRejectedStatus();
            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class, 'validation-invalid-label')][contains(text(), 'заблокирован')]")).isDisplayed());
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            navigationToTheEventTableFromAdminMeanPage();
            checkingEventAboutRejectionUsersRegistration();
            checkingTheStatusEventThatAdminRejectedUserSRegistration(expectedSuccessExec);
        }else {
            System.out.println("Если подтверждать регистрацию пользователя не нужно, то нельзя отменить его регистарцию");
        }
    }
    @Test(retryAnalyzer = Retry.class) //10. Уведомление отклонения регистрации компании
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
    @Test(retryAnalyzer = Retry.class) //11. Уведомление управляющему компании о новой заявке в сотрудники
    public void eventAboutRequestToJoinTheCompanyFromAnEmployee() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
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
        }else System.out.println("Нет сотрудников в нерасширенной версии рабы с компаниями");
    }
    @Test(retryAnalyzer = Retry.class) //12. Уведомление сотруднику о присоединении к компании
    public void eventThatTheEmployeeHasBeenConfirmedToJoinTheCompany() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
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
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        confirmEmployeeRequest();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheEventTableFromAdminMeanPage();
        checkingEventAboutConfirmJoiningToTheCompanyFromAnEmployee();
        checkingStatusEventAboutConfirmJoiningToTheCompanyFromAnEmployee(expectedSuccessExec);
        }else System.out.println("Нет сотрудников в нерасширенной версии рабы с компаниями");
    }
    @Test(retryAnalyzer = Retry.class) //13. Уведомление сотруднику об отказе в присоединении к компании
    public void eventAboutRefusalToJoinTheCompanyToAnEmployee () {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
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
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        rejectEmployeeRequest();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        //act
        navigationToTheEventTableFromAdminMeanPage();
        checkingEventThatTheManagerHasRejectedTheJoiningOfThisEmployeeToTheCompany();
        checkingTheStatusEventAboutRejectedJoiningToTheCompanyFromAnEmployee(expectedSuccessExec);
        }else System.out.println("Нет сотрудников в нерасширенной версии рабы с компаниями");
}
    @Test(retryAnalyzer = Retry.class) //14. Изменение данных созданной организациии у юзера
    public void checkAvailableActionsWithOrganizationsFromTheUser() {
        determineWhetherVersionsOfWorkingWithOrganization();
        determineWhetherRegistrationOrganizationNeedsToBeConfirmed();
        if (versionsOfWorkingWithOrganizationsExtended && doNeedToConfirmRegistrationOrganization) {
        //arrange
        try{
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToOrganizationTab();
            checkingThatOrganizationIsConfirmed();
        }catch (Exception e){ //Если нет одобренной организации для проверки > создать ее
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
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheEventTableFromAdminMeanPage();
        checkingEventAboutNeededToConfirmTheCompanyAfterChanging();
        checkingStatusOfEventAboutNeededToConfirmTheCompanyAfterChanging(expectedSuccessExec);
        checkingEventAboutConfirmTheCompanyAfterChanging();
        checkingStatusOfEventAboutConfirmTheCompanyAfterChanging(expectedSuccessExec);
        }else System.out.println("Нет событий о изменении компаний в нерасширенной версии");
    }
    @Test(retryAnalyzer = Retry.class) //15. Уведомление, что администратор не подтвердил изменение данных организации
    public void eventThatTheAdministratorHasNotConfirmedTheChangeOfTheOrganizationSData() {
        determineWhetherVersionsOfWorkingWithOrganization();
        determineWhetherRegistrationOrganizationNeedsToBeConfirmed();
        if (versionsOfWorkingWithOrganizationsExtended && doNeedToConfirmRegistrationOrganization) {
        //arrange
        try{
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToOrganizationTab();
            checkingThatOrganizationIsConfirmed();
        }catch (Exception e){ //Если нет одобренной организации для проверки > создать ее
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
        }else System.out.println("Нет событий о изменении компаний в нерасширенной версии");
    }




}
