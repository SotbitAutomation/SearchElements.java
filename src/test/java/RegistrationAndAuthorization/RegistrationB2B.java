package RegistrationAndAuthorization;


import BaseActions.Retry;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationB2B extends MethodsForRegistrationAndAuthorization {

    @Test (retryAnalyzer = Retry.class) //1. Регистрация индивидуального предпринимателся с ручным вводом ИНН
    public void registrationIPWithManualEntryINN() {
        //arrange
        navigationToRegistrationTab();
        //act
        choiceIP();
        enterINNManually();
        fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
        tryConfirmRegistration();
        entranceToB2BFromRegistrationTab();
    }

    @Test(retryAnalyzer = Retry.class) //2. Регистрация индивидуального предпринимателся с выбором ИНН из появляющегося списка
    public void registrationIPWithSelectINNFromList() {
        //arrange
        navigationToRegistrationTab();
        //act
        choiceIP();
        choiceINNFromTheListOnTheRegistrationTab();
        fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
        tryConfirmRegistration();
        entranceToB2BFromRegistrationTab();
    }

    @Test(retryAnalyzer = Retry.class) //3. Регистрация юридического лица с ручным вводом ИНН
    public void registrationLegalPersonWithManualEntryINN() {
        //arrange
        navigationToRegistrationTab();
        //act
        choiceLegalPerson();
        enterINNManually();
        fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForLegalPerson);
        tryConfirmRegistration();
        entranceToB2BFromRegistrationTab();
    }

    @Test(retryAnalyzer = Retry.class) //4. Регистрация юридического лица с выбором ИНН из появляющегося списка
    public void registrationLegalPersonWithSelectINNFromList() {
        //arrange
        navigationToRegistrationTab();
        //act
        choiceLegalPerson();
        choiceINNFromTheListOnTheRegistrationTab();
        fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForLegalPerson);
        tryConfirmRegistration();
        entranceToB2BFromRegistrationTab();
    }

    @Test(retryAnalyzer = Retry.class) //5. Регистрация пользователя без принятия условий соглашения
    public void registrationUserWithoutTermsOfAgreement() {
        //arrange
        navigationToRegistrationTab();
        //act
        choiceIP();
        enterINNManually();
        fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
        removingCheckboxFromThePrivacyPolicy();
        entranceToB2BFromRegistrationTabWithoutPoliticAgree();
    }

    @Test(retryAnalyzer = Retry.class) //6. Регистрация пользователя без заполнения обязательных полей (вывод предупреждений)
    public void registrationUserWithoutRequiredFields() {
        //arrange
        navigationToRegistrationTab();
        choiceIP();
        enterINNManually();
        fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
        //act
        checkRequiredFields();
    }

    @Test(retryAnalyzer = Retry.class) //7. Регистрация пользователя с ошибкой в подтверждении пароля (вывод предупреждения)
    public void registrationUserWithWrongConfirmPassword() {
        //arrange
        navigationToRegistrationTab();
        //act
        choiceIP();
        enterINNManually();
        fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
        enterWrongConfirmPassword();
    }

    @Test(retryAnalyzer = Retry.class) //8. Регистрация пользователя с уже существующим Емаил
    public void registrationUserWithTheSameEmail() {
        //arrange
        navigationToRegistrationTab();
        choiceIP();
        enterINNManually();
        theSameEmail = null;
        fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
        tryConfirmRegistration();
        entranceToB2BFromRegistrationTab();
        //act
        navigationToRegistrationTab();
        choiceIP();
        enterINNManually();
        fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
        changeEmailOnTheSame();
        checkingMessageAboutExistingEmail();
    }

    @Test(retryAnalyzer = Retry.class) //9. Регистрация пользователя с уже существующим  ИНН
    public void registrationUserWithTheSameINN() {
        determineWhetherVersionsOfWorkingWithOrganization();
        determineWhetherRegistrationUserNeedsToBeConfirmed();
        if(versionsOfWorkingWithOrganizationsExtended){
            tempValueForEmail = null;
            navigationToRegistrationTab();
            choiceLegalPerson();
            enterINNManually();
            fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForLegalPerson);
            tryConfirmRegistration();
            entranceToB2BFromRegistrationTab();
            //exitFromB2B();
            tryConfirmRegistrationOfOrganizationInB2bFromTheUser();
            navigationToRegistrationTab();
            choiceIP();
            fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
            enterTheSameINN();
            logInFromAuthorizationTabUseTheSameInn();
            if (!doNeedToConfirmRegistrationUser){ exitFromB2B();}
            tryConfirmRegistration();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTab(tempValueForEmail, password);
            logInFromAuthorizationTab();
            navigationToEmployeesTab();
            acceptEmployee();
            checkThatEmployeeIsDisplayedFromTheManager();
            exitFromB2B();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTab();
            logInFromAuthorizationTab();
            navigationToEmployeesTab();
            chekThatTheOrganizationIsDisplayedByTheEmployee();
        }else {
            System.out.println("В обычной версии работы с организациями нет проверки по ИНН");
        }
    }

    @Test(retryAnalyzer = Retry.class) //10. Переход к авторизации из вкладки "Регистарция"
    public void chekOfButtonAuthorization() {
        //arrange
        navigationToRegistrationTab();
        //act
        choiceIP();
        fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
        choiceINNFromTheListOnTheRegistrationTab();
        goToAuthorizationTAb();
    }
    @Test(retryAnalyzer = Retry.class) //11. Авторизация без одобрения администратором зарегистрированного пользователя
    public void authorizationWithoutTheApprovalOfTheAdministratorOfTheRegisteredUser() {
        determineWhetherRegistrationUserNeedsToBeConfirmed();
        if (doNeedToConfirmRegistrationUser){
        //arrange
        navigationToRegistrationTab();
        //act
        choiceLegalPerson();
        enterINNManually();
        fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForLegalPerson);
        driver.findElement(registerButtonOnRegistrationTabLocator).click();
        checkingThatYouCantLogInWithoutConfirm();
        }else {
            System.out.println("Пользователю не нужно подтверждать регистарцию для авторизации");
        }
    }

    @Test(retryAnalyzer = Retry.class) //12. Регистрация физического лица
    public void registrationPersonWithManualEntryINN() {
        //arrange
        navigationToRegistrationTab();
        //act
        try {
            choicePerson();
            fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForPerson);
            tryConfirmRegistration();
            entranceToB2BFromRegistrationTab();
        }catch (Exception e){
            System.out.println("НЕТУ типа плательщика Физ. лицо!");
        }
    }
    @Test(retryAnalyzer = Retry.class) //13. Проверка отображается уведомление об отправке на модерацию управляющему компании при выборе ИП
    public void checkMessageAboutTheConfirmRegistrationForIP() {
        determineWhetherRegistrationUserNeedsToBeConfirmed();
        if (doNeedToConfirmRegistrationUser){
            //arrange
            navigationToRegistrationTab();
            //act
            choiceIP();
            enterINNManually();
            fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
            driver.findElement(registerButtonOnRegistrationTabLocator).click();
            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Ожидайте подтверждение модератором.')]")).isDisplayed());
        }else {
            System.out.println("Пользователю не нужно подтверждать регистарцию для авторизации");
        }
    }
    @Test(retryAnalyzer = Retry.class) //14. Отображаение уведомление об отправке на модерацию управляющему компании при выборе Юридического лица
    public void checkMessageAboutTheConfirmRegistrationForLegalPerson() {
        determineWhetherRegistrationUserNeedsToBeConfirmed();
        if (doNeedToConfirmRegistrationUser){
            //arrange
            navigationToRegistrationTab();
            //act
            choiceLegalPerson();
            enterINNManually();
            fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForLegalPerson);
            driver.findElement(registerButtonOnRegistrationTabLocator).click();
            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Ожидайте подтверждение модератором.')]")).isDisplayed());
        }else {
            System.out.println("Пользователю не нужно подтверждать регистарцию для авторизации");
        }
    }


}
