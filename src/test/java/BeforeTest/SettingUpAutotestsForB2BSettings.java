package BeforeTest;

import OrganizationsWithExtendedVersion.AddingOrganizationsWithExtendedVersion;
import OrganizationsWithExtendedVersion.MethodsForAddingOrganizationsWithExtendedVersion;
import RegistrationAndAuthorization.MethodsForRegistrationAndAuthorization;
import RegistrationAndAuthorization.RegistrationB2B;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SettingUpAutotestsForB2BSettings extends MethodsForRegistrationAndAuthorization {
    @Test //1. Создание локаторов для регистарции
    public void a_creatingLocatorsForRegistration() {
        //arrange
        navigationToRegistrationTab();
        //act
        determineRadioButton();
        choiceIPForCreatingLocators();
        try{
            enterINNManually();
        }catch (Exception e){}
        creatingArrayWithExistingLocatorsForIP();
        writingArrayWithLocatorsForIPOnComputer();
        choiceLegalPersonForCreatingLocators();
        try{
            enterINNManually();
        }catch (Exception e){}
        creatingArrayWithExistingLocatorsForLegalPerson();
        writingArrayWithLocatorsForLegalPersonOnComputer();
        try{
            choicePersonForCreatingLocators();
            creatingArrayWithExistingLocatorsForPerson();
            writingArrayWithLocatorsForPersonOnComputer();
        }catch (Exception e){}
    }

    @Test //2. Определяет есть ли поле "Местоположение" при регистарции (что бы быстрее тесты проходили)
    public void b_determinesWhetherThereIsLocationField() {
        navigationToRegistrationTab();
        choiceIP();
        try {
            driver.findElement(By.xpath("//*[contains(@placeholder, 'естоположение ...')][not(ancestor-or-self::*[@style = 'display: none;'])]")).sendKeys("Россия");
            driver.findElement(By.xpath("//*[contains(@class,'combobox-variant-active')]")).click();
            flagForLocation = true;
        }catch (Exception e){}
        choiceLegalPerson();
        if (!flagForLocation){
            try {
                driver.findElement(By.xpath("//*[contains(@placeholder, 'естоположение ...')][not(ancestor-or-self::*[@style = 'display: none;'])]")).sendKeys("Россия");
                driver.findElement(By.xpath("//*[contains(@class,'combobox-variant-active')]")).click();
                flagForLocation = true;
            }catch (Exception e){}
        }

        //Запись в файл
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("Location"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.writeObject(this.flagForLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(flagForLocation);
    }

    @Test //3. Определение нужно ли подтверждать регистрацию пользователя
    public void c_determiningOfNeedToConfirmOfRegistrationUser () {
        //arrange
        navigationToRegistrationTab();
        //act
        choiceIP();
        try{
            enterINNManually();
        }catch (Exception e){}
        fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
        try {
            driver.findElement(registerButtonOnRegistrationTabLocator).click();
            Assert.assertTrue(driver.findElement(By.cssSelector(".navbar")).isDisplayed());
        }catch (Exception e){
            driver.findElement(By.cssSelector(".btn.bg-success")).click();
        }

        try{
            checkingThatTheBannerIsDisplayed();
            doNeedToConfirmRegistrationUser = false;
        }catch (Exception e){
            doNeedToConfirmRegistrationUser = true;
        }
        //Запись в файл
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(fileNameForConfirmRegistrationUser));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.writeObject(this.doNeedToConfirmRegistrationUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(doNeedToConfirmRegistrationUser);
    }

    @Test //4. Определение версии работы с компаниями
    public void d_determiningVersionsOfWorkingWithOrganizations_3 () {
        AddingOrganizationsWithExtendedVersion addingOrganization = new AddingOrganizationsWithExtendedVersion ();
        //arrange
        navigationToRegistrationTab();
        //act
        choiceIP();
        try{
            enterINNManually();
        }catch (Exception e){}
        fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
        tryConfirmRegistration();
        entranceToB2BFromRegistrationTab();
        try {
            addingOrganization.navigationToEmployeesTab();
            versionsOfWorkingWithOrganizationsExtended = true;
        }catch (Exception e){
            versionsOfWorkingWithOrganizationsExtended = false;
        }

        //Запись в файл
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(fileNameForVersionOfWorkingWithOrganization));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.writeObject(this.versionsOfWorkingWithOrganizationsExtended);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Расширенная ли версия работы с компаниями " + versionsOfWorkingWithOrganizationsExtended);
    }

    @Test //5. Определение нужно ли подтверждать регистрацию организации
    public void e_determiningOfNeedToConfirmOfRegistrationOrganization_4 () {
        determineWhetherVersionsOfWorkingWithOrganization();

        if (versionsOfWorkingWithOrganizationsExtended){
            //arrange
            navigationToRegistrationTab();
            //act
            choiceIP();
            try{
                enterINNManually();
            }catch (Exception e){}
            fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
            tryConfirmRegistration();
            entranceToB2BFromRegistrationTab();
            navigationToOrganizationTab();

            try {
                Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'На модерации')]")).isDisplayed());
                doNeedToConfirmRegistrationOrganization = true;
            }catch (Exception e){
                try {
                    Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Одобрена')]")).isDisplayed());
                    doNeedToConfirmRegistrationOrganization = false;
                }catch (Exception exception){
                    AddingOrganizationsWithExtendedVersion addOrganization = new AddingOrganizationsWithExtendedVersion();
                    addOrganization.navigationToAddOrganizationTab();
                    addOrganization.selectionFromDropDownListIndividualBusinessman();
                    addOrganization.fillingFieldsForCreatingOrganization();
                    addOrganization.creatingOrganization();
                    navigationToOrganizationTab();
                    try {
                        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'На модерации')]")).isDisplayed());
                        doNeedToConfirmRegistrationOrganization = true;
                    }catch (Exception exception1){}

                        try {
                            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Одобрена')]")).isDisplayed());
                            doNeedToConfirmRegistrationOrganization = false;
                        }catch (Exception exception2){
                            MethodsForAddingOrganizationsWithExtendedVersion addOrg = new MethodsForAddingOrganizationsWithExtendedVersion();
                            exitFromB2B();
                            navigationToAuthorizationTab();
                            fillingFieldsOnTheLogInTabLikeAdmin();
                            logInToB2B();
                            navigationToOrganizationTab();
                            addOrg.selectingAllColumnsToDisplay();
                            exitFromB2B();
                            navigationToAuthorizationTab();
                            fillingFieldsOnTheLogInTab(email, password);
                            logInToB2B();
                            navigationToOrganizationTab();
                        }
                        try {
                            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'На модерации')]")).isDisplayed());
                            doNeedToConfirmRegistrationOrganization = true;
                        }catch (Exception exception3){
                            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Одобрена')]")).isDisplayed());
                            doNeedToConfirmRegistrationOrganization = false;
                        }
                }
            }
        }
            //Запись в файл
            try {
                outputStream = new ObjectOutputStream(new FileOutputStream(fileNameForConfirmRegistrationOrganization));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.writeObject(this.doNeedToConfirmRegistrationOrganization);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Нужно подтверждать регистрацию - " + doNeedToConfirmRegistrationOrganization);
    }

    @Test //6. Создание пользователей для автотестов
    public void f_creatingUsersForAutomationTests() {
        //arrange
        RegistrationB2B registr = new RegistrationB2B();
        registr.registrationIPWithManualEntryINN();
        emailUser = registr.email;
        passwordUser = registr.password;
        exitFromB2B();
        registr.registrationLegalPersonWithManualEntryINN();
        emailEmployee = registr.email;
        passwordEmployee = registr.password;

        //Запись в файл
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(fileNameForUsersEmailsAndPasswords));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.writeObject(this.emailUser);
            outputStream.writeObject(this.passwordUser);
            outputStream.writeObject(this.emailEmployee);
            outputStream.writeObject(this.passwordEmployee);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}