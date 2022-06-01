package organizations_with_extended_version;

import base_actions.Retry;
import my_orders_history.MethodsForMyOrders;
import org.openqa.selenium.By;
import org.testng.annotations.Test;


public class AddingOrganizationsWithExtendedVersion extends MethodsForAddingOrganizationsWithExtendedVersion {
    @Test(retryAnalyzer = Retry.class) //1. Добавление организация (ИП) у админа
    public void addOrganizationIPFromTheAdmin() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
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
    }

    @Test(retryAnalyzer = Retry.class) //2. Добавление организация (ИП) у юзера
    public void addOrganizationIPFromTheUser() {
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
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            checkingThatOrganizationIsConfirmed();
        }
    }

    @Test(retryAnalyzer = Retry.class) //3. Добавление организация (ЮР) у админа
    public void addOrganizationLegalPersonFromTheAdmin() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
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
    }

    @Test(retryAnalyzer = Retry.class) //4. Добавление организация (ЮР) у юзера
    public void addOrganizationLegalPersonFromTheUser() {
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
            checkingThatOrganizationIsConfirmed();
        }
    }

    @Test(retryAnalyzer = Retry.class) //5. Проверка полей (обязательные / не обязательные)
    public void checkFieldsRequiredOrOptionalFromTheUser() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToOrganizationTab();
            navigationToAddOrganizationTab();
            //act
            checkingThatTheFieldsAreRequired();
        }
    }

    @Test(retryAnalyzer = Retry.class)//6. Изменение данных созданной организации у админа
    public void checkAvailableActionsWithOrganizationsFromTheAdmin() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            try {
                navigationToAuthorizationTab();
                fillingFieldsOnTheLogInTabLikeAdmin();
                logInToB2B();
                navigationToOrganizationTab();
                checkingThatOrganizationsIsDisplayed();
                checkingThatOrganizationIsConfirmed();
            } catch (Exception e) { //Если нет одобренной организации для проверки > создать ее
                addOrganizationIPFromTheAdmin();
                navigationToOrganizationTab();
                checkingThatOrganizationIsConfirmed();
            }
            //act
            addOrganizationIPFromTheAdmin();
            checkingAvailableActionsWithOrganization(true);
        }
    }

    @Test(retryAnalyzer = Retry.class)//7. Изменение данных созданной организациии у юзера
    public void checkAvailableActionsWithOrganizationsFromTheUser() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            try {
                navigationToAuthorizationTab();
                fillingFieldsOnTheLogInTabLikeUser();
                logInToB2B();
                navigationToOrganizationTab();
                checkingThatOrganizationIsConfirmed();
            } catch (Exception e) { //Если нет одобренной организации для проверки > создать ее
                nameCompany = "NameCompany ИмяКомпании" + randomString(10);
                iNNManual = randomNumber(14);
                addOrganizationIPFromTheUser();
                navigationToOrganizationTab();
                checkingThatOrganizationIsConfirmed();
            }
            //act
            sortingOrganizationByDecrease();
            checkingAvailableActionsWithOrganization(false);
        }
    }

    @Test(retryAnalyzer = Retry.class)//8. Присоединиться к организации (ИП) которую создал другой пользователь
    public void checkOfJoiningAnOrganizationIP() {
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
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
            selectionFromTheHeaderOrganization(nameCompany);
            checkingThatTheEmployeeOfCompanyIsDisplayed();
        }
    }

    @Test(retryAnalyzer = Retry.class)//9. Присоединиться к организации (ЮР) которую создал другой пользователь
    public void checkOfJoiningAnOrganizationLP() {
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
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
            navigationToOrganizationTab();
            requestToJoinTheCompany();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            confirmEmployeeRequest();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
            selectionFromTheHeaderOrganization(nameCompany);
            checkingThatTheEmployeeOfCompanyIsDisplayed();
        }
    }

    @Test(retryAnalyzer = Retry.class)//10. Добавление существующего  сотрудника к организации
    public void addingAnEmployeeToAnOrganization() {
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
            fillingFieldForCreatingEmployeeUsingReferralLink();
            addingAnEmployeeToAnOrganizationUsingByReferralLink();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
            checkingTheCompanyNameInHeader();
            checkingThatCompanyIsDisplayed();
        }
    }

    @Test(retryAnalyzer = Retry.class)
    //11. Добавление  существующего  сотрудника к организации который уже является сотрудником компании
    public void addingReExistingEmployeeToAnOrganization() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            addingAnEmployeeToAnOrganization();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            //arrange
            addingTheSameEmployeeToAnOrganizationFromAnotherUser();
        }
    }

    @Test(retryAnalyzer = Retry.class)//12. Регистрация сотрудника компании из раздела сотрудники
    public void registrationOfEmployeeFromTheEmployeesSection() {
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
            fillingFieldsOnTheLogInTabLikeEmployeeJustNowRegistered();
            logInToB2B();
            checkingTheCompanyNameInHeader();
        }
    }

    @Test(retryAnalyzer = Retry.class)//13. Регистрация руководителя компании из раздела сотрудники
    public void registrationOfBossFromTheEmployeesSection() {
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
            choosingBossRole();
            registrationUserFromTheEmployeesTab();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeEmployeeJustNowRegistered();
            logInToB2B();
            checkingTheCompanyNameInHeader();
        }
    }

    @Test(retryAnalyzer = Retry.class)//14. Регистрация сотрудника компании из раздела сотрудники с существующим email
    public void registrationEmployeeFromTheEmployeesSectionWithExistingEmail() {
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
            registrationTheSameUserFromTheEmployeesTab();
        }
    }

    @Test(retryAnalyzer = Retry.class)//15. Добавление существующего Руководителя к организации
    public void addingAnBossToAnOrganizationUsingReferralLink() {
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
            fillingFieldForCreatingEmployeeUsingReferralLink();
            choosingBossRole();
            addingAnEmployeeToAnOrganizationUsingByReferralLink();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
            checkingTheCompanyNameInHeader();
            checkingThatCompanyIsDisplayed();
            navigationToPersonsTab();
            checkingThatThereAreButtonForAddEmployee();
        }
    }

    @Test(retryAnalyzer = Retry.class)//16. Регистрация пользователя через реферальную ссылку
    public void registeringUserUsingReferralLink() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            //arrange
            navigationToPersonsTab();
            try {   //Если у пользователя еще нет организации то создать ему организацию
                driver.findElement(By.xpath("//*[contains(text(), 'Добавить нового сотрудника')]")).click();
            } catch (Exception e) {
                addingAnEmployeeToAnOrganization();
                navigationToAuthorizationTab();
                fillingFieldsOnTheLogInTabLikeUser();
                logInToB2B();
                navigationToPersonsTab();
                driver.findElement(By.xpath("//*[contains(text(), 'Добавить нового сотрудника')]")).click();
            }
            registrationNewUserUsingReferralLink();
            checkingSuccessfulRegistrationMessage();
        }
    }

    @Test(retryAnalyzer = Retry.class)//17 Проверка почтового события после добавления пользователя управляющим компании
    public void checkingTheEventAfterTheUserIsAddedByTheCompanyManager() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
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
            //act
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            navigationToTheEventTableFromAdminMeanPage();
            checkingThatEventAboutInviteBossIsDisplayed();
        }
    }

    @Test(retryAnalyzer = Retry.class)
    //18. Проверка того что сотрудник не может выбрать оргнаизацию для оформления заказа из которой он был удален
    public void checkingThatEmployeeCannotSelectOrganizationToPlaceOrderFromWhichHeWasRemoved() {
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
            selectionFromTheHeaderOrganization(nameCompany);
            confirmEmployeeRequest();
            navigationToPersonsTab();
            deletingEmployee();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
            checkingThatEmployeeCantChoiceCompanyFromWhichHeWasDeleted();
        }
    }

    @Test(retryAnalyzer = Retry.class)//19. Отображение уведомления о добавлении уже существующей компании
    public void registrationTheSameOrganization() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
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
            //act
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
            navigationToOrganizationTab();
            navigationToAddOrganizationTab();
            selectionFromDropDownListLegalPerson();
            fillingFieldsWithTheSameINNAndNameOrganizationForCreatingOrganization();
            checkingMessageAboutExistOrganization();
        }
    }

    @Test(retryAnalyzer = Retry.class)//20. Закрытие поп-ап окна "Присоедениться к организации", после нажатия "Отмена"
    public void closingPopUpWindowJoinOrganizationAfterClickingCancel() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
            navigationToOrganizationTab();
            checkingThatPopUpWindowJoinToOrganizationIsClosedAfterClickCancel();
            checkingThatDataWasDeletedAfterClickCancel();
        }
    }

    @Test(retryAnalyzer = Retry.class)
    //21. Отображение введённых данных, после ввода данных и повторного открытия поп-ап окна "Присоедениться к организации"
    public void displayingEnteredDataAfterEnteringTheDataAndReOpeningThePopUpWindowJoinOrganization() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
            navigationToOrganizationTab();
            checkingThatEnteredDataIsDisplayedAfterReopeningPopUpWindowJoinToOrganization();
        }
    }

    @Test(retryAnalyzer = Retry.class)//22. Вывод списка заказов в табе "Заказы" (на детальной странице организации)
    public void checkThatListOfOrdersIsDisplayedInTheOrdersTab() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            addOrganizationIPFromTheUser();
            selectionFromTheHeaderOrganization(nameCompany);
            addingTwoOrder();
            //act
            navigationToOrganizationTab();
            sortingOrganizationByDecrease();
            checkingThatOrdersIsDisplayedInOrganization();
        }
    }

    @Test(retryAnalyzer = Retry.class)
    //23. Переход к детальной информации совершенного заказа из таба "Заказы" организации совершившей этот заказ
    public void goToTheCompletedOrderFromTheOrdersTabOfTheOrganizationSDetailedPage() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            addOrganizationIPFromTheUser();
            selectionFromTheHeaderOrganization(nameCompany);
            addingTwoOrder();
            //act
            navigationToOrganizationTab();
            sortingOrganizationByDecrease();
            checkingThatOrdersIsDisplayedInOrganization();
            goToDetailPageAboutOrder();
        }
    }
    @Test(retryAnalyzer = Retry.class)
    //24. Вывод количества элементов в счетчике "Всего"
    public void outputOfTheNumberOfItemsInTheTotalCounter() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToOrganizationTab();
            checkingTheNumberOfOrganizationsInTheTotalCounter();
        }
    }
    @Test(retryAnalyzer = Retry.class)
    //25. Поиск организации в разделе "Мои организации"
    public void searchForAnOrganizationInTheMyOrganizationsSection() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            navigationToOrganizationTab();
            searchRandomOrganizationByItSNameFromTheUserSExistingOrganizations();
            deletingNumberForSearchOrganizationUsingCloseIcon();
            searchRandomOrganizationByItSNameFromTheUserSExistingOrganizations();
            deletingNameForSearchOrganizationUsingFieldForSearch();
            searchRandomOrganizationByItSNameFromTheUserSExistingOrganizations();
            openDetailPageFirstOrganization();
            navigationToOrganizationTab();
            deletingNameForSearchOrganizationUsingFieldForSearch();
        }
    }

    @Test (retryAnalyzer = Retry.class)//26 Поиск оформленного заказа по его номеру из детальной страницы организации
    public void searchForCompletedOrderByItsNumberFromDetailOrganizationsPage() {
        MethodsForMyOrders order = new MethodsForMyOrders();
        //arrange
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        order.changeTheQuantityOfRandomProduct();
        navigationToCart();
        order.navigationToMakingOrderFromCart();
        order.trySelectCompany();
        determineWhetherVersionsOfWorkingWithOrganization();
        rememberingNameOfOrganizationWhichMakingOrder();
        order.makingOrder();
        tempValueForNumbers = order.numberOfOrder;
        navigationToOrganizationTab();
        //act
        openingOrganizationWhichMadeAnOrder();
        searchJustNowCompletedOrderByItSNumberOnTheDetailPageOrganization();
        checkingThatThatOrderIsFound();
        order.openingLastOrder();
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
//            driver.findElement(By.xpath("(//*[contains(@href,'company_edit')])[2]")).click();
//            driver.findElement(By.xpath("//*[@id='btn_delete']")).click();
//            standardConfirmationOfTheActionOnThePage();
//        }
//    }
}
