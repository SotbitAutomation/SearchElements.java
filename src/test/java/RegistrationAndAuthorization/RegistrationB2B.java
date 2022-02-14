package RegistrationAndAuthorization;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class RegistrationB2B extends MethodsForRegistrationAndAuthorization {

        @Test //1. Регистрация индивидуального предпринимателся с ручным вводом ИНН
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

        @Test //2. Регистрация индивидуального предпринимателся с выбором ИНН из появляющегося списка
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

        @Test //3. Регистрация юридического лица с ручным вводом ИНН
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

        @Test //4. Регистрация юридического лица с выбором ИНН из появляющегося списка
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

        @Test //5. Регистрация пользователя без принятия условий соглашения
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

        @Test //6. Регистрация пользователя без заполнения обязательных полей (вывод предупреждений)
        public void registrationUserWithoutRequiredFields() {
            //arrange
            navigationToRegistrationTab();
            choiceIP();
            enterINNManually();
            fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
            //act
            checkRequiredFields();
        }

        @Test //7. Регистрация пользователя с ошибкой в подтверждении пароля (вывод предупреждения)
        public void registrationUserWithWrongConfirmPassword() {
            //arrange
            navigationToRegistrationTab();
            //act
            choiceIP();
            enterINNManually();
            fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
            enterWrongConfirmPassword();
        }

        @Test //8. Регистрация пользователя с уже существующим Емаил
        public void registrationUserWithTheSameEmail() {
            //arrange
            registrationIPWithManualEntryINN();
            exitFromB2B();
            //act
            navigationToRegistrationTab();
            choiceIP();
            enterINNManually();
            fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
            changeEmailOnTheSame();
            checkingMessageAboutExistingEmail();
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

        @Test //10. Переход к авторизации из вкладки "Регистарция"
        public void chekOfButtonAuthorization() {
            //arrange
            navigationToRegistrationTab();
            //act
            choiceIP();
            fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
            choiceINNFromTheListOnTheRegistrationTab();
            goToAuthorizationTAb();
        }

        @Test //11. Регистрация физического лица
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
                System.out.println("НЕТУ типа организации Физ. лицо!");
            }
        }
        @Test //12. Проверка отображается уведомление об отправке на модерацию управляющему компании при выборе ИП
        public void checkMessageAboutTheConfirmRegistrationForIP() {
            //arrange
            navigationToRegistrationTab();
            //act
            choiceIP();
            enterINNManually();
            fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
            determineWhetherRegistrationUserNeedsToBeConfirmed();
            if (doNeedToConfirmRegistrationUser == true){
                driver.findElement(registerButtonOnRegistrationTabLocator).click();
                Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Ожидайте подтверждение модератором.')]")).isDisplayed());
            }
        }
    @Test //13. Проверка сообщения "Ожидайте подтверждение модератором"
    public void checkMessageAboutTheConfirmRegistrationForIPLegalPerson() {
        //arrange
        navigationToRegistrationTab();
        //act
        choiceLegalPerson();
        enterINNManually();
        fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForLegalPerson);
        determineWhetherRegistrationUserNeedsToBeConfirmed();
        if (doNeedToConfirmRegistrationUser == true){
            driver.findElement(registerButtonOnRegistrationTabLocator).click();
            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Ожидайте подтверждение модератором.')]")).isDisplayed());
        }
    }
}
