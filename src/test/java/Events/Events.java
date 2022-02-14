package Events;

import org.junit.Test;

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
        checkingEventAboutTheNeedToConfirmUserRegistration();
        determineWhetherRegistrationOrganizationNeedsToBeConfirmed();
        checkingEventAboutTheRegistrationOrganization();
        searchForAColumnWithEventStatuses();
        checkingTheStatusOfTheUserRegistrationEvent();
        checkingTheStatusOfAnOrganizationRegistrationEvent();
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



}
