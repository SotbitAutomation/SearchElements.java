package BeforeTest;

import RegistrationAndAuthorization.MethodsForRegistrationAndAuthorization;
import RegistrationAndAuthorization.RegistrationB2B;
import SettingUpCabinetForTesting.MethodsForSettingUpCabinetForTesting;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class SettingUpAutotestsForB2BSettings extends MethodsForRegistrationAndAuthorization {
    @Test //1. Определение типа шапки
    public void a_determiningTheTypeOfHeader() {
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        driver.findElement(logInButtonOnTheAuthorizationTabLocator).click();
        implicitWaiting();
        if (driver.findElements(dropdownUserIcon).size() == 0) {
            themeColorBlack = true;
        } else {
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
        try {
            enterINNManually();
        } catch (Exception e) {
            System.out.println("Поле для ИНН отсутсвует");
        }
        creatingArrayWithExistingLocatorsForIP();
        writingArrayWithLocatorsForIPOnComputer();
        choiceLegalPersonForCreatingLocators();
        try {
            enterINNManually();
        } catch (Exception e) {
            System.out.println("Поле для ИНН отсутсвует");
        }
        creatingArrayWithExistingLocatorsForLegalPerson();
        writingArrayWithLocatorsForLegalPersonOnComputer();
        try {
            choicePersonForCreatingLocators();
            creatingArrayWithExistingLocatorsForPerson();
            writingArrayWithLocatorsForPersonOnComputer();
        } catch (Exception e) {
            System.out.println("Тип плательщака 'Физ. лицо' отсутсвует");
        }
    }

    @Test //3. Определяет есть ли поле "Местоположение" при регистарции (что бы быстрее тесты проходили)
    public void c_determinesWhetherThereIsLocationField() {
        navigationToRegistrationTab();
        choiceIP();
        try {
            driver.findElement(By.xpath("//*[contains(@placeholder, 'естоположение ...')][not(ancestor-or-self::*[@style = 'display: none;'])]")).sendKeys("Россия");
            driver.findElement(By.xpath("//*[contains(@class,'combobox-variant-active')]")).click();
            flagForLocation = true;
        } catch (Exception e) {
            System.out.println("Поле 'Местоположение' отсутствует");
        }
        choiceLegalPerson();
        if (!flagForLocation) {
            try {
                driver.findElement(By.xpath("//*[contains(@placeholder, 'естоположение ...')][not(ancestor-or-self::*[@style = 'display: none;'])]")).sendKeys("Россия");
                driver.findElement(By.xpath("//*[contains(@class,'combobox-variant-active')]")).click();
                flagForLocation = true;
            } catch (Exception e) {
                System.out.println("Поле 'Местоположение' отсутствует");
            }
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
        System.out.println("Есть ли поле 'Местоположение' - " + flagForLocation);
    }

    @Test //4. Определение нужно ли подтверждать регистрацию пользователя и оргнаизации, какая версия работы
    public void d_determiningOfNeedToConfirmOfRegistrationsAndWorkVersion() {
        //arrange
        MethodsForSettingUpCabinetForTesting set = new MethodsForSettingUpCabinetForTesting();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        driver.findElement(logInButtonOnTheAuthorizationTabLocator).click();
        navigationToTheSiteSettings();
        //act
        set.checkingWhetherTheUserRegistrationNeedsToBeConfirmed();
        doNeedToConfirmRegistrationUser = set.userRegistrationNeedsToBeConfirmed;
        set.checkingWhetherTheCompanyRegistrationNeedsToBeConfirmed();
        doNeedToConfirmRegistrationOrganization = set.companyRegistrationNeedsToBeConfirmed;


        //Запись в файл (нужно ли подтв пользвоателя)
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
        System.out.println("Нужно подтверждать регистрацию пользователя - " + doNeedToConfirmRegistrationUser);

        //Запись в файл (нужно ли подтв пользвоателя компанию)
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
        System.out.println("Нужно подтверждать регистрацию компании - " + doNeedToConfirmRegistrationOrganization);

        navigationToGeneralB2BSettings();
        set.checkingWhetherAdvancedModeIsEnabled();
        versionsOfWorkingWithOrganizationsExtended = set.whetherExtendedModeIsEnabled;

        navigationToMeanPageByUrl();
        resetCache();
        navigationToOrganizationTab();
        driver.findElement(By.xpath("//*[@id='PERSONAL_PROFILE_LIST_table'] //*[@class='main-grid-interface-settings-icon']")).click();
        for (int i = 1; i <=driver.findElements(By.xpath("//*[contains(@id, 'checkbox')]")).size(); i++) {
            if (!driver.findElement(By.xpath("(//*[contains(@id, 'checkbox')])[" + i + "]")).isSelected()){
                driver.findElement(By.xpath("(//*[contains(@id, 'checkbox')])[" + i + "]")).click();
            }
        }
        driver.findElement(By.cssSelector("#PERSONAL_PROFILE_LIST-grid-settings-apply-button")).click();

        //Запись в файл (версия работы расширенная ли)
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
        System.out.println("Расширенная ли версия работы с компаниями - " + versionsOfWorkingWithOrganizationsExtended);



    }

//    @Test //5. Определение версии работы с компаниями
//    public void e_determiningVersionsOfWorkingWithOrganizations() {
//        //arrange
//        MethodsForSettingUpCabinetForTesting set = new MethodsForSettingUpCabinetForTesting();
//        navigationToAuthorizationTab();
//        fillingFieldsOnTheLogInTabLikeAdmin();
//        driver.findElement(logInButtonOnTheAuthorizationTabLocator).click();
//        //act
//        navigationToGeneralB2BSettings();
//        set.checkingWhetherAdvancedModeIsEnabled();
//        versionsOfWorkingWithOrganizationsExtended = set.whetherExtendedModeIsEnabled;
//
//
//
////        AddingOrganizationsWithExtendedVersion addingOrganization = new AddingOrganizationsWithExtendedVersion();
////        //arrange
////        navigationToRegistrationTab();
////        //act
////        choiceIP();
////        try {
////            enterINNManually();
////        } catch (Exception e) {
////        }
////        fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
////        tryConfirmRegistration();
////        entranceToB2BFromRegistrationTab();
////        try {
////            addingOrganization.navigationToEmployeesTab();
////            versionsOfWorkingWithOrganizationsExtended = true;
////        } catch (Exception e) {
////            versionsOfWorkingWithOrganizationsExtended = false;
////        }  //старое, удалить
//
//        //Запись в файл
//        try {
//            outputStream = new ObjectOutputStream(new FileOutputStream(fileNameForVersionOfWorkingWithOrganization));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            outputStream.writeObject(this.versionsOfWorkingWithOrganizationsExtended);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Расширенная ли версия работы с компаниями " + versionsOfWorkingWithOrganizationsExtended);
//    }

//    @Test //6. Определение нужно ли подтверждать регистрацию организации
//    public void f_determiningOfNeedToConfirmOfRegistrationOrganization() {
//        determineWhetherVersionsOfWorkingWithOrganization();
//        if (versionsOfWorkingWithOrganizationsExtended) {
//            //arrange
//            navigationToAuthorizationTab();
//            fillingFieldsOnTheLogInTabLikeAdmin();
//            logInToB2B();
//            navigationToOrganizationTab();
//            MethodsForAddingOrganizationsWithExtendedVersion org = new MethodsForAddingOrganizationsWithExtendedVersion();
//            org.selectingAllColumnsToDisplay();
//            navigationToRegistrationTab();
//            //act
//            choiceIP();
//            try {
//                enterINNManually();
//            } catch (Exception e) {
//            }
//            fillingFieldsOnTheRegistrationTab(arrayWithExistingLocatorsForIP);
//            tryConfirmRegistration();
//            entranceToB2BFromRegistrationTab();
//            navigationToOrganizationTab();
//
//            try {
//                Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'На модерации')][contains(@class, 'content')]")).isDisplayed());
//                doNeedToConfirmRegistrationOrganization = true;
//            } catch (Exception e) {
//                try {
//                    Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(), 'Одобрена')]")).isDisplayed());
//                    doNeedToConfirmRegistrationOrganization = false;
//                } catch (Exception exception) {
//                    AddingOrganizationsWithExtendedVersion addOrganization = new AddingOrganizationsWithExtendedVersion();
//                    addOrganization.navigationToAddOrganizationTab();
//                    addOrganization.selectionFromDropDownListIndividualBusinessman();
//                    addOrganization.fillingFieldsForCreatingOrganization();
//                    addOrganization.creatingOrganization();
//                    navigationToOrganizationTab();
//                    try {
//                        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'На модерации')]")).isDisplayed());
//                        doNeedToConfirmRegistrationOrganization = true;
//                    } catch (Exception exception1) {
//                    }
//
//                    try {
//                        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Одобрена')]")).isDisplayed());
//                        doNeedToConfirmRegistrationOrganization = false;
//                    } catch (Exception exception2) {
//                        MethodsForAddingOrganizationsWithExtendedVersion addOrg = new MethodsForAddingOrganizationsWithExtendedVersion();
//                        exitFromB2B();
//                        navigationToAuthorizationTab();
//                        fillingFieldsOnTheLogInTabLikeAdmin();
//                        logInToB2B();
//                        navigationToOrganizationTab();
//                        addOrg.selectingAllColumnsToDisplay();
//                        exitFromB2B();
//                        navigationToAuthorizationTab();
//                        fillingFieldsOnTheLogInTab(email, password);
//                        logInToB2B();
//                        navigationToOrganizationTab();
//                    }
//                    try {
//                        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'На модерации')]")).isDisplayed());
//                        doNeedToConfirmRegistrationOrganization = true;
//                    } catch (Exception exception3) {
//                        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Одобрена')]")).isDisplayed());
//                        doNeedToConfirmRegistrationOrganization = false;
//                    }
//                }
//            }
//        }
//        //Запись в файл
//        try {
//            outputStream = new ObjectOutputStream(new FileOutputStream(fileNameForConfirmRegistrationOrganization));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            outputStream.writeObject(this.doNeedToConfirmRegistrationOrganization);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Нужно подтверждать регистрацию - " + doNeedToConfirmRegistrationOrganization);
//    }

    @Test //5. Создание пользователей для автотестов
    public void e_creatingUsersForAutomationTests() {
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