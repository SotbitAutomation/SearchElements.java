package RegistrationAndAuthorization;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class AuthorizationB2B extends MethodsForRegistrationAndAuthorization {

    @Test //1. Авторизация зарегистрированного пользователя
    public void authorizationUser() {
        //arrange
        navigationToRegistrationTab();
        choiceIP();
        enterINNManually();
        fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
        tryConfirmRegistration();
        entranceToB2BFromRegistrationTab();
        exitFromB2B();
        //act
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTab();
        logInFromAuthorizationTab();
    }

    @Test //2. Авторизация зарегистрированного пользователя без чекбокса "Запомнить"
    public void authorizationUserWithoutCheckboxRememberMe() {
        //arrange
        navigationToRegistrationTab();
        choiceIP();
        enterINNManually();
        fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
        tryConfirmRegistration();
        entranceToB2BFromRegistrationTab();
        exitFromB2B();
        //act
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTab();
        driver.findElement(By.xpath("//*[@name='USER_REMEMBER']")).click();
        logInFromAuthorizationTab();
    }

    @Test //3. Авторизация зарегистрированного пользователя под неверным паролем
    public void authorizationUserWithWrongPassword() {
        //arrange
        navigationToRegistrationTab();
        choiceIP();
        enterINNManually();
        fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
        tryConfirmRegistration();
        entranceToB2BFromRegistrationTab();
        exitFromB2B();
        //act
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTab();
        ligInExistingUserWithWrongPassword();
    }

    @Test //4. Запрос нового пароля зарегистрированного пользователя
    public void requestingNewPassword() {
        //arrange
        navigationToRegistrationTab();
        choiceIP();
        enterINNManually();
        fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
        tryConfirmRegistration();
        entranceToB2BFromRegistrationTab();
        exitFromB2B();
        //act
        passwordRequired();
    }

    @Test //5. Запрос нового пароля незарегистрированного пользователя
    public void requestingNewPasswordUnregisteredUser() {
        navigationToAuthorizationTab();
        passwordRequiredForUnregisteredUser();
    }

    @Test //6. Возращение к окну авторизации после запроса пароля
    public void goBackToAuthorizationAfterSendingData() {
        //arrange
        requestingNewPassword();
        //act
        goToAuthorizationTAbFromPasswordRequiredWindow();
    }
    @Test //7. Авторизация без одобрения администратором зарегистрированного пользователя
    public void authorizationWithoutConfirmInAdminPart() {
        //arrange
        navigationToRegistrationTab();
        determineWhetherRegistrationUserNeedsToBeConfirmed();
        //act
        if (doNeedToConfirmRegistrationUser == true){
            choiceIP();
            enterINNManually();
            fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
            entranceToB2BFromRegistrationTabWithoutConfirm();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTab();
            logInExistingUserWithoutConfirm();
        }
    }
    @Test //8. Авторнизация пользователя с неподтверждённой компанией
    public void authorizationUserWithoutConfirmCompany() {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended == true){
            //arrange
            navigationToRegistrationTab();
            choiceIP();
            enterINNManually();
            fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
            tryConfirmRegistration();
            entranceToB2BFromRegistrationTab();
            exitFromB2B();
            //act
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTab();
            logInFromAuthorizationTab();
            navigationToOrganizationTab();
            checkingStatusOfOrganizationWithoutConfirm();
        }
    }
    @Test //9. Регистрация пользователя с уже существующим  ИНН
    public void registrationUserWithTheSameINN() {
        determineWhetherVersionsOfWorkingWithOrganization();
        determineWhetherRegistrationUserNeedsToBeConfirmed();
        if(versionsOfWorkingWithOrganizationsExtended == true){
            navigationToRegistrationTab();
            choiceLegalPerson();
            enterINNManually();
            fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForLegalPerson);
            tryConfirmRegistration();
            entranceToB2BFromRegistrationTab();
            //exitFromB2B();
            tryConfirmRegistrationOfOrganizationInB2bFromTheUser();
            //act
            navigationToRegistrationTab();
            choiceIP();
            fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
            enterINNToTheSame();
            logInFromAuthorizationTabUseTheSameInn();
            if (doNeedToConfirmRegistrationUser == false){ exitFromB2B();}
            tryConfirmRegistration();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTab(tempValueForEmail, password);
            logInFromAuthorizationTab();
            navigationToEmployeesTab();
            rejectEmployee();
            exitFromB2B();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTab();
            logInFromAuthorizationTab();
            navigationToEmployeesTab();
            Assert.assertTrue(driver.findElement(By.cssSelector(".main-grid-empty-text")).isDisplayed());
        }else {
            System.out.println("В обычной версии работы с организациями нет проверки по ИНН");
        }
    }
    @Test //10. Проверка, что пользователю отправлено уведомление о смене пароля
    public void checkingEventAboutChangePassword() {
        //arrange
        navigationToRegistrationTab();
        choiceIP();
        enterINNManually();
        fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
        tryConfirmRegistration();
        entranceToB2BFromRegistrationTab();
        exitFromB2B();
        navigationToAuthorizationTab();
        passwordRequired();
        //act
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToTheEventTableFromAdminMeanPage();
        checkingThatEventAboutChangePasswordIsSuccessful();
    }
}
