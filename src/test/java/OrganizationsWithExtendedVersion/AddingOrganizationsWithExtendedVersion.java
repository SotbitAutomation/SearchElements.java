package OrganizationsWithExtendedVersion;

import org.junit.Test;
import org.openqa.selenium.By;


public class AddingOrganizationsWithExtendedVersion extends MethodsForAddingOrganizationsWithExtendedVersion {
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
        confirmRegistrationOfOrganizationFromAdmin();
        checkingThatOrganizationIsConfirmed();
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
        tryConfirmRegistrationOfOrganizationInB2bFromTheUser();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        checkingThatOrganizationIsConfirmed();
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
        confirmRegistrationOfOrganizationFromAdmin();
        checkingThatOrganizationIsConfirmed();
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
        tryConfirmRegistrationOfOrganizationInB2bFromTheUser();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        checkingThatOrganizationIsConfirmed();
    }

    @Test //5. Проверка полей (обязательные / не обязательные)
    public void checkFieldsRequiredOrOptionalFromTheUser() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToOrganizationTab();
        navigationToAddOrganizationTab();
        //act
        checkingThatTheFieldsAreRequired();
    }

    @Test //6. Проверка доступных действий над организациями у админа
    public void checkAvailableActionsWithOrganizationsFromTheAdmin() {
        //arrange
        try{
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            navigationToOrganizationTab();
            checkingThatOrganizationsIsDisplayed();
            checkingThatOrganizationIsConfirmed();
        }catch (Exception e){ //Если нет одобренной организации для проверки > создать ее
            exitFromB2B();
            addOrganizationIPFromTheAdmin();
            navigationToOrganizationTab();
            checkingThatOrganizationIsConfirmed();
        }
        addOrganizationIPFromTheAdmin();
        //act

        checkingAvailableActionsWithOrganization(true);
    }

    @Test //7. Проверка доступных действий над организациями у юзера
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
            addOrganizationIPFromTheUser();
            navigationToOrganizationTab();
            checkingThatOrganizationIsConfirmed();
        }
        //act
        sortingOrganizationByDecrease();
        checkingAvailableActionsWithOrganization(false);
    }

    @Test //8. Присоединиться к организации (ИП) которую создал другой пользователь
    public void checkOfJoiningAnOrganizationIP() {
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
        confirmationEmployeeRequest();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeEmployee();
        logInToB2B();
        selectionFromTheHeaderOrganization(nameCompany);
        checkingThatTheEmployeeOfCompanyIsDisplayed();
    }

    @Test //9. Присоединиться к организации (ЮР) которую создал другой пользователь
    public void checkOfJoiningAnOrganizationLP() {
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
        fillingFieldsOnTheLogInTabLikeEmployee();
        logInToB2B();
        navigationToOrganizationTab();
        requestToJoinTheCompany();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        confirmationEmployeeRequest();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeEmployee();
        logInToB2B();
        selectionFromTheHeaderOrganization(nameCompany);
        checkingThatTheEmployeeOfCompanyIsDisplayed();
    }

    @Test //10. Добавление существующего  сотрудника к организации
    public void addingAnEmployeeToAnOrganization() {
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
        fillingFieldForCreatingEmployeeUsingReferralLink();
        addingAnEmployeeToAnOrganizationUsingByReferralLink();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeEmployee();
        logInToB2B();
        checkingTheCompanyNameInHeader();
        checkingThatCompanyIsDisplayed();
    }

    @Test //11. Добавление  существующего  сотрудника к организации который уже является сотрудником компании
    public void addingReExistingEmployeeToAnOrganization() {
        //arrange
        addingAnEmployeeToAnOrganization();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        //arrange
        addingTheSameEmployeeToAnOrganizationFromAnotherUser();
    }

    @Test //12. Регистрация сотрудника компании из раздела сотрудники
    public void registrationOfEmployeeFromTheEmployeesSection() {
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
        fillingFieldsOnTheLogInTabLikeEmployeeJustNowRegistered();
        logInToB2B();
        checkingTheCompanyNameInHeader();
    }

    @Test //13. Регистрация руководителя компании из раздела сотрудники
    public void registrationOfBossFromTheEmployeesSection() {
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
        choosingBossRole();
        registrationUserFromTheEmployeesTab();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeEmployeeJustNowRegistered();
        logInToB2B();
        checkingTheCompanyNameInHeader();
    }

    @Test //14. Регистрация сотрудника компании из раздела сотрудники с существующим email
    public void registrationEmployeeFromTheEmployeesSectionWithExistingEmail() {
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
        registrationTheSameUserFromTheEmployeesTab();
    }

    @Test //15. Добавление существующего Руководителя к организации
    public void addingAnBossToAnOrganizationUsingReferralLink() {
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
        fillingFieldForCreatingEmployeeUsingReferralLink();
        choosingBossRole();
        addingAnEmployeeToAnOrganizationUsingByReferralLink();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeEmployee();
        logInToB2B();
        checkingTheCompanyNameInHeader();
        checkingThatCompanyIsDisplayed();
        navigationToPersonsTab();
        checkingThatThereAreButtonForAddEmployee();
    }

    @Test //16. Регистрация пользователя через реферальную ссылку
    public void registeringUserUsingReferralLink() {
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        //arrange
        navigationToPersonsTab();
        try {   //Если у пользователя еще нет организации то создать ему организацию
            driver.findElement(By.xpath("//*[contains(text(), 'Добавить нового сотрудника')]")).click();
        }catch (Exception e){
            exitFromB2B();
            addingAnEmployeeToAnOrganization();
            exitFromB2B();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToPersonsTab();
            driver.findElement(By.xpath("//*[contains(text(), 'Добавить нового сотрудника')]")).click();
        }
        registrationNewUserUsingReferralLink();
        checkingSuccessfulRegistrationMessage();
    }

    @Test //17 Проверка почтового события после добавления пользователя управляющим компании
    public void checkingEmail() {
        //arrange
        navigationToAuthorizationTab();
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
        fillingFieldForCreatingEmployeeUsingReferralLink();
        choosingBossRole();
        addingAnEmployeeToAnOrganizationUsingByReferralLink();
        exitFromB2B();
        //act
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheEventTableFromAdminMeanPage();
        checkingThatEventAboutInviteBossIsDisplayed();
    }

    @Test //18. Проверка того что сотрудник не может выбрать оргнаизацию для оформления заказа из которой он был удален
    public void checkingThatEmployeeCannotSelectOrganizationToPlaceOrderFromWhichHeWasRemoved() {
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
        confirmationEmployeeRequest();
        navigationToPersonsTab();
        deletingEmployee();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeEmployee();
        logInToB2B();
        checkingThatEmployeeCantChoiceCompanyFromWhichHeWasDeleted();
    }

    @Test //19. Отображение уведомления о добавлении уже существующей компании
    public void registrationTheSameOrganization() {
        //arrange
        navigationToAuthorizationTab();
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
        checkingThatOrganizationIsConfirmed();
        exitFromB2B();
        //act
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeEmployee();
        logInToB2B();
        navigationToOrganizationTab();
        navigationToAddOrganizationTab();
        selectionFromDropDownListLegalPerson();
        fillingFieldsForCreatingOrganization();
        checkingMessageAboutExistOrganization();
    }

    @Test //20. Закрытие поп-ап окна "Присоедениться к организации", после нажатия "Отмена"
    public void closingPopUpWindowJoinOrganizationAfterClickingCancel() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeEmployee();
        logInToB2B();
        navigationToOrganizationTab();
        checkingThatPopUpWindowJoinToOrganizationIsClosedAfterClickCancel();
        checkingThatDataWasDeletedAfterClickCancel();
    }
    @Test //21. Отображение введённых данных, после ввода данных и повторного открытия поп-ап окна "Присоедениться к организации"
    public void displayingEnteredDataAfterEnteringTheDataAndReOpeningThePopUpWindowJoinOrganization() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeEmployee();
        logInToB2B();
        navigationToOrganizationTab();
        checkingThatEnteredDataIsDisplayedAfterReopeningPopUpWindowJoinToOrganization();
    }
    @Test //22. Вывод списка заказов в табе "Заказы" (на детальной странице организации)
    public void checkThatListOfOrdersIsDisplayedInTheOrdersTab() {
        //arrange
        addOrganizationIPFromTheUser();
        selectionFromTheHeaderOrganization(nameCompany);
        exitFromB2B();
        addingTwoOrder();
        //act
        navigationToOrganizationTab();
        sortingOrganizationByDecrease();
        checkingThatOrdersIsDisplayedInOrganization();
    }
    @Test //23. Переход к детальной информации совершенного заказа из таба "Заказы" организации совершившей этот заказ
    public void goToTheCompletedOrderFromTheOrdersTabOfTheOrganizationSDetailedPage() {
        //arrange
        addOrganizationIPFromTheUser();
        selectionFromTheHeaderOrganization(nameCompany);
        exitFromB2B();
        addingTwoOrder();
        //act
        navigationToOrganizationTab();
        sortingOrganizationByDecrease();
        checkingThatOrdersIsDisplayedInOrganization();
        goToDetailPageAboutOrder();
    }













//    @Test //Удаление организаций
//    public void Deleting() {
//        //arrange
//        navigationToAuthorizationTab();
//        //act
//        fillingFieldsOnTheLogInTabLikeAdmin();
//        logInToB2B();
//        driver.findElement(By.xpath("//*[text() = 'Администрирование']")).click();
//        driver.findElement(By.id("global_menu_sotbit")).click();
//        driver.findElement(By.xpath("//*[text() = 'Список компаний']")).click();
//
//        for (int i = 0; i <500; i++) {
//            Assert.assertNotEquals("Компания №2", driver.findElement(By.xpath("(//*[@class='adm-list-table-cell'])[1] /*")).getText());
//            driver.findElement(By.xpath("(//*[@class='adm-list-table-cell'])[1] /*")).click();
//            driver.findElement(By.xpath("//*[@id='btn_delete']")).click();
//            standardConfirmationOfTheActionOnThePage();
//        }
//    }
}
