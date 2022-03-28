package BeforeTest;

import OrganizationsWithExtendedVersion.AddingOrganizationsWithExtendedVersion;
import OrganizationsWithExtendedVersion.MethodsForAddingOrganizationsWithExtendedVersion;
import RegistrationAndAuthorization.MethodsForRegistrationAndAuthorization;
import RegistrationAndAuthorization.RegistrationB2B;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class SettingUpAutotestsForB2BSettings extends MethodsForRegistrationAndAuthorization {
    @Test //1. Определение типа шапки
    public void a_determiningTheTypeOfHeader () {
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        driver.findElement(logInButtonOnTheAuthorizationTabLocator).click();
        if (driver.findElements(dropdownUserIcon).size()==0){
            themeColorBlack = true;
        }else {
            themeColorBlack = false;
        }

        //Запись в файл
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(fileNameForB2BThemeColor));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.writeObject(this.themeColorBlack);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Темная ли версия шапки " + themeColorBlack);
    }
    @Test //2. Создание локаторов для регистарции
    public void b_creatingLocatorsForRegistration() {
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

    @Test //3. Определяет есть ли поле "Местоположение" при регистарции (что бы быстрее тесты проходили)
    public void c_determinesWhetherThereIsLocationField() {
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
            outputStream = new ObjectOutputStream(new FileOutputStream("doNeedToSpecifyLocation"));
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

    @Test //4. Определение нужно ли подтверждать регистрацию пользователя
    public void d_determiningOfNeedToConfirmOfRegistrationUser () {
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
            implicitWaiting(); implicitWaiting();
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

    @Test //5. Определение версии работы с компаниями
    public void e_determiningVersionsOfWorkingWithOrganizations () {
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

    @Test //6. Определение нужно ли подтверждать регистрацию организации
    public void f_determiningOfNeedToConfirmOfRegistrationOrganization () {
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended){
            //arrange
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeAdmin();
            logInToB2B();
            navigationToOrganizationTab();
            MethodsForAddingOrganizationsWithExtendedVersion org = new MethodsForAddingOrganizationsWithExtendedVersion();
            org.selectingAllColumnsToDisplay();
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
                Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'На модерации')][contains(@class, 'content')]")).isDisplayed());
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

    @Test //7. Создание пользователей для автотестов
    public void g_creatingUsersForAutomationTests() {
        //arrange
        RegistrationB2B registr = new RegistrationB2B();
        registr.registrationIPWithManualEntryINN();
        emailUser = registr.email;
        passwordUser = registr.password;
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