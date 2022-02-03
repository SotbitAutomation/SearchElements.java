package RegistrationAndAuthorization;

import BaseActions.BaseActions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.Arrays;

public class MethodsForRegistrationAndAuthorization extends BaseActions {

    //arrange
    public By registerButtonOnRegistrationTabLocator = By.cssSelector(".js_person_type_block:not([style='display: none;']) [type='submit']");
    By inputINNLocator = By.cssSelector(".js_person_type_block:not([style='display: none;']) [placeholder='ИНН']");
    By checkboxOfAgreementLocator = By.cssSelector(".js_person_type_block:not([style='display: none;']) .main-user-consent-input-fake");
    By buttonOfDisagreeLocator = By.cssSelector(".main-user-consent-request-popup-button-rej");
    By popupAgreementWindowLocator = By.cssSelector(".main-user-consent-request-popup-cont");
    By confirmPasswordInputLocator = By.cssSelector(".js_person_type_block:not([style='display: none;']) [placeholder='Повторите пароль']");
    By authorizationButtonOnRegistrationTabLocator = By.cssSelector(".js_person_type_block:not([style='display: none;']) .btnBlock__authToLink");
    By windowOfRegisterLocator = By.cssSelector(".bx-authform");
    public String tempLocator;
    public boolean  locationFieldIsPresent = false;
    public String fileNameForIP = "arrayWithLocatorsForIP";
    public String[] arrayWithExistingLocatorsForIP;
    public String fileNameForLegalPerson = "arrayWithLocatorsForLegalPerson";
    public String[] arrayWithExistingLocatorsForLegalPerson;
    public String fileNameForPerson = "arrayWithLocatorsForPerson";
    public String[] arrayWithExistingLocatorsForPerson;
    public int counterOfUserType;
    public int radioButtonForPerson;
    public int radioButtonForIP;
    public int radioButtonForLegalPerson;
    public boolean DoNeedToConfirmRegistration;
    long tempIntForInn;

    String[] arrayWithAllPossibleLocators={
            "E-Mail", email,
            "Название", nameCompany,
            "Название компании", nameCompany,
            "Адрес доставки", address,
            "Контактное лицо", lastName,
            "Город", "Сызрань",
            "Индекс", index,
            "Факс", index,
            "Телефон", mobilePhone,
            "Ф.И.О.", nameCompany,
            "БИК", name + lastName,
            "ОГРНИП", iNNManual,
            "ОГРН", iNNManual,
            "КПП", iNNManual,
            "Фактический адрес", address,
            "Юридический адрес", address,
            "Название компании", nameCompany,
            "Должность", appeal,
            "Контактное лицо", name + lastName,
            "Корреспондентский счет", iNNManual,
            "Расчетный счет", iNNManual,
            "Наименование банка", name + lastName,
            "E-Mail (логин)", email,
            "Мобильный", mobilePhone,
            "БСН", iNNManual,
            "Наименование КРП", index,
            "Код КРП (сучетомфилиалов)", index,
            "КАТО", mobilePhone,
            "Фамилия имя отчество руководителя", lastName + name,
            "Наименование компании", nameCompany,
            "Отчество", "Батькович",
            "ФИО", name + lastName,
            "Юридический адрес", address,
            "Название компании", nameCompany,
            "Название организации", nameCompany,
            "Профессия", "Тестировщик",
            "УНП", nameCompany,
            "Сокращенное название компании", nameCompany,
            "Область", "Сызраньская",
            "Адрес", nameCompany,
            "Город", nameCompany,
            "УНП", nameCompany,
            "Расчётный счёт", randomNumber(12),
            "Корреспондентский счёт", randomNumber(12),
            "ОКВЭД", randomNumber(12),
            "Login", email,
            "Телефон организации", mobilePhone,
    };
//    String[] arrayWithAllPossibleLocators={
//            "//*[@placeholder='Ф.И.О.'][not(ancestor-or-self::*[@style = 'display: none;'])]", name + lastName,
//            "//*[@placeholder='ФИО'][not(ancestor-or-self::*[@style = 'display: none;'])]", name + lastName,
//            "//*[@placeholder='Юридический адрес'][not(ancestor-or-self::*[@style = 'display: none;'])]", address,
//            "//*[@placeholder='Контактное лицо'][not(ancestor-or-self::*[@style = 'display: none;'])]", lastName,
//            "//*[@placeholder='ОГРНИП'][not(ancestor-or-self::*[@style = 'display: none;'])]", iNNManual + "000",
//            "//*[@placeholder='ОГРН'][not(ancestor-or-self::*[@style = 'display: none;'])]", iNNManual + "000",
//            "//*[@placeholder='КПП'][not(ancestor-or-self::*[@style = 'display: none;'])]", iNNManual + "000",
//            "//*[@placeholder='Должность'][not(ancestor-or-self::*[@style = 'display: none;'])]", appeal,
//            "//*[@placeholder='Телефон'][not(ancestor-or-self::*[@style = 'display: none;'])]", mobilePhone,
//            "//*[@placeholder='Индекс'][not(ancestor-or-self::*[@style = 'display: none;'])]", index,
//            "//*[@placeholder='Адрес доставки'][not(ancestor-or-self::*[@style = 'display: none;'])]", address,
//            "//*[contains(text(), 'Должность:')][not(ancestor-or-self::*[@style = 'display: none;'])]/following::input[1]", appeal,
//            "//*[contains(text(), 'Мобильный:')][not(ancestor-or-self::*[@style = 'display: none;'])]/following::input[1]", mobilePhone,
//            "//*[@placeholder='E-Mail'][not(ancestor-or-self::*[@style = 'display: none;'])]", email,
//            "//*[@placeholder='Должность'][not(ancestor-or-self::*[@style = 'display: none;'])]", email,
//            "//*[@placeholder='Название компании'][not(ancestor-or-self::*[@style = 'display: none;'])]", nameCompany,
//            "//*[@placeholder='Название организации'][not(ancestor-or-self::*[@style = 'display: none;'])]", nameCompany,
//            "//*[@placeholder='Местоположение'][not(ancestor-or-self::*[@style = 'display: none;'])]", "Россия",
//            "//*[@placeholder='Выберите местоположение ...'][not(ancestor-or-self::*[@style = 'display: none;'])]", "Россия",
//            "//*[@placeholder='Город'][not(ancestor-or-self::*[@style = 'display: none;'])]", "Сызрань",
//            "//*[contains(text(), 'Отчество: ')][not(ancestor-or-self::*[@style = 'display: none;'])]/following::input[1]", "Батькович",
//            "//*[@placeholder='Факс'][not(ancestor-or-self::*[@style = 'display: none;'])]", phone,
//            "//*[contains(text(), 'Профессия: ')][not(ancestor-or-self::*[@style = 'display: none;'])]/following::input[1]", "ТЕСТИРОВЩИК",
//            "//*[@placeholder='УНП'][not(ancestor-or-self::*[@style = 'display: none;'])]", name + lastName,
//            "//*[contains(text(), 'Телефон:')][not(ancestor-or-self::*[@style = 'display: none;'])]/following::input[1]", mobilePhone,
//            "//*[contains(text(), 'Город')][not(ancestor-or-self::*[@style = 'display: none;'])]/following::input[1]", "Батькович",
//            "//*[contains(text(), 'Наименование компании')][not(ancestor-or-self::*[@style = 'display: none;'])]/following::input[1]", "Рога И Копыта",
//
//    };
    public void entranceToB2BFromRegistrationTabWithoutConfirm(){
        driver.findElement(registerButtonOnRegistrationTabLocator).click();
        if (doNeedToConfirmRegistration == true){
            try {
                driver.findElement(By.cssSelector(".btn.bg-success")).click();
            }catch (Exception e){}
        }
    }
  
    public void entranceToB2BFromRegistrationTab () {
        determineWhetherRegistrationNeedsToBeConfirmed();
        if (doNeedToConfirmRegistration == true) {
            fillingFieldsOnTheLogInTab();
            logInFromAuthorizationTab();
        } else {
            try {
                driver.findElement(registerButtonOnRegistrationTabLocator).click();
                try {
                    driver.findElement(By.cssSelector(".btn.bg-success")).click();
                }catch (Exception e){}
                Assert.assertTrue(driver.findElement(By.cssSelector(".navbar")).isDisplayed());
            } catch (Exception e) {
                driver.findElement(By.cssSelector(".btn.bg-success")).click();
                Assert.assertTrue(driver.findElement(By.cssSelector(".navbar")).isDisplayed());
            }
        }
    }

    public void removingCheckboxFromThePrivacyPolicy (){
        driver.findElement(By.xpath("//*[@class='label-confidential'][not(ancestor-or-self::*[@style = 'display: none;'])]")).click();
        explicitWaiting();
        driver.findElement(By.xpath("//span[contains(text(), 'Не принимаю')]")).click();
        explicitWaiting();
    }
    public void entranceToB2BFromRegistrationTabWithoutPoliticAgree (){
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerButtonOnRegistrationTabLocator));
        driver.findElement(registerButtonOnRegistrationTabLocator).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".main-user-consent-request-popup-cont")).isDisplayed());
            }


//        if (doNeedToConfirmRegistration == true){
//            fillingFieldsOnTheLogInTab();
//            logInFromAuthorizationTab();
//        }else {
//            driver.findElement(registerButtonOnRegistrationTabLocator).click();
//            if(versionsOfWorkingWithOrganizationsExtended == false){
//                try {
//                    Assert.assertTrue(driver.findElement(By.cssSelector(".navbar")).isDisplayed());
//                }catch (Exception e){}
//            }
//            try {
//                driver.findElement(By.cssSelector(".btn.bg-success")).click();
//                Assert.assertTrue(driver.findElement(By.cssSelector(".navbar")).isDisplayed());
//            }catch (Exception e){}
//        }

    public void checkingThatTheBannerIsDisplayed(){
        driver.navigate().to(b2bUrl);
        Assert.assertTrue(driver.findElement(By.cssSelector(".navbar")).isDisplayed());
    }

    public void fillingFieldsOnTheRegistrationTab (String[] array){
        for (int i = 0; i < array.length; i=i+2) {
                driver.findElement(By.xpath("//*[contains(text(), '" + array[i] + "')][not(ancestor-or-self::*[@style = 'display: none;'])] /following::*[2]")).sendKeys(array[i+1]);
                if (array[i].equals("Название компании")){
                    driver.findElement(By.xpath("//*[contains(text(), '" + array[i] + "')][not(ancestor-or-self::*[@style = 'display: none;'])] /following::*[2]")).clear();
                    nameCompany = "NameCompany ИмяКомпании" + randomString(10);
                    driver.findElement(By.xpath("//*[contains(text(), '" + array[i] + "')][not(ancestor-or-self::*[@style = 'display: none;'])] /following::*[2]")).sendKeys(nameCompany);
                }
        }
        email = "test_EMail" + randomString(12) + "@mail.ru";
        if(tempValueForEmail == null){
            tempValueForEmail = email;
        }
        driver.findElement(By.xpath("//*[@placeholder='Введите e-mail'][not(ancestor-or-self::*[@style = 'display: none;'])]")).sendKeys(email);
        driver.findElement(By.xpath("//*[@placeholder='Введите имя'][not(ancestor-or-self::*[@style = 'display: none;'])]")).sendKeys(name);
        driver.findElement(By.xpath("//*[@placeholder='Введите фамилию'][not(ancestor-or-self::*[@style = 'display: none;'])]")).sendKeys(lastName);
        driver.findElement(By.xpath("//*[@placeholder='Создать пароль'][not(ancestor-or-self::*[@style = 'display: none;'])]")).sendKeys(password);
        driver.findElement(By.xpath("//*[@placeholder='Повторите пароль'][not(ancestor-or-self::*[@style = 'display: none;'])]")).sendKeys(password);

//        try {
//            driver.findElement(By.xpath("//*[contains(@placeholder, 'естоположение ...')][not(ancestor-or-self::*[@style = 'display: none;'])]")).sendKeys("Россия");
//            driver.findElement(By.xpath("//*[contains(@class,'combobox-variant-active')]")).click();
//        }catch (Exception e){}
        determineWhetherLocationWhileRegistration();
        if (flagForLocation == true){
            try {
            driver.findElement(By.xpath("//*[contains(@placeholder, 'естоположение ...')][not(ancestor-or-self::*[@style = 'display: none;'])]")).sendKeys("Россия");
            driver.findElement(By.xpath("//*[contains(@class,'combobox-variant-active')]")).click();
        }catch (Exception e){}
        }

        Assert.assertEquals("Емаил не отображается"
                ,email, driver.findElement(By.cssSelector(".js_person_type_block:not([style='display: none;']) [placeholder='Введите e-mail']")).getAttribute("value"));
        Assert.assertEquals("Имя не отображается"
                ,name, driver.findElement(By.cssSelector(".js_person_type_block:not([style='display: none;']) [placeholder='Введите имя']")).getAttribute("value"));
        Assert.assertEquals("Фамилия не отображается"
                ,lastName, driver.findElement(By.cssSelector(".js_person_type_block:not([style='display: none;']) [placeholder='Введите фамилию']")).getAttribute("value"));
        Assert.assertEquals("Пароль не отображается"
                ,password, driver.findElement(By.cssSelector(".js_person_type_block:not([style='display: none;']) [placeholder='Создать пароль']")).getAttribute("value"));
        Assert.assertEquals("Подтвержденый пароль не отображается"
                ,password, driver.findElement(By.cssSelector(".js_person_type_block:not([style='display: none;']) [placeholder='Повторите пароль']")).getAttribute("value"));
        if(theSameEmail == null){
            theSameEmail = email;
        }
        System.out.println(email);
        System.out.println(password);
    }

    public void enterINNManually(){
        try{
            iNNManual = randomNumber(14);
            if (theSameInnManual == null){
                theSameInnManual = iNNManual;
            }
            enterNumberByDigit(iNNManual, inputINNLocator);
            iNNManual = driver.findElement(By.xpath("//*[@placeholder='ИНН'][not(ancestor-or-self::*[@style = 'display: none;'])]")).getAttribute("value");
        }catch (Exception e){
            System.out.println("Поле для ИНН не найдено!");
        }
    }

    public void choiceINNFromTheListOnTheRegistrationTab (){
        driver.findElement(By.cssSelector(".js_person_type_block:not([style='display: none;']) [placeholder='ИНН']")).clear();
        driver.findElement(By.cssSelector(".js_person_type_block:not([style='display: none;']) [placeholder='ИНН']")).sendKeys(randomNumber(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='checkcompany-list']")));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='checkcompany-list']")).isDisplayed());
        driver.findElement(By.xpath("(//*[@id='checkcompany-list'] /*)[2]")).click();
        explicitWaiting();

        if (driver.findElements(By.xpath("//*[@placeholder='Название компании'][not(ancestor-or-self::*[@style = 'display: none;'])]")).size() > 0){
            Assert.assertTrue("Название компании не автозаполнилось после выбора ИНН из списка ИННов"
                    ,driver.findElement(By.xpath("//*[@placeholder='Название компании'][not(ancestor-or-self::*[@style = 'display: none;'])]")).getAttribute("value").length() > 0);
        }
        if (driver.findElements(By.xpath("//*[@placeholder='Юридический адрес'][not(ancestor-or-self::*[@style = 'display: none;'])]")).size() > 0){
            Assert.assertTrue("Адрес не автозаполнился после выбора ИНН из списка ИННов"
                    ,driver.findElement(By.xpath("//*[@placeholder='Юридический адрес'][not(ancestor-or-self::*[@style = 'display: none;'])]")).getAttribute("value").length() > 0);
        }
    }

//    public void entranceToB2BWithTheSameINN (){
//
//        try {
//            driver.findElement(registerButtonOnRegistrationTabLocator).click();
//            Assert.assertTrue("ИНН уже существует", driver.findElement(
//                    By.xpath("//*[contains(text(), 'Пользователь с таким ИНН')]")).isDisplayed());
//        }catch (Exception e){
//            driver.findElement(By.xpath("//html")).click();
//            driver.findElement(registerButtonOnRegistrationTabLocator).click();
//            Assert.assertTrue("ИНН уже существует", driver.findElement(
//                    By.xpath("//*[contains(text(), 'Пользователь с таким ИНН')]")).isDisplayed());
//        }
//    }
    public void determineRadioButton(){
        count = 0;
        counterOfUserType = driver.findElements(By.xpath("//*[@class='chouse-company'] /* /following::*[1]")).size();
        for (int i = 1; i < counterOfUserType; i++) {
            if (driver.findElement(By.xpath("(//*[@class='chouse-company'] /* /following::*[1])[" + i + "]"))
                    .getText().equals("Физическое лицо")){
                radioButtonForPerson = i;
                count++;
            }else if (driver.findElement(By.xpath("(//*[@class='chouse-company'] /* /following::*[1])[" + i + "]"))
                    .getText().equals("Индивидуальный предприниматель")){
                radioButtonForIP = i;
                count++;
            }else if (driver.findElement(By.xpath("(//*[@class='chouse-company'] /* /following::*[1])[" + i + "]"))
                    .getText().equals("Юридическое лицо")){
                radioButtonForLegalPerson = i;
                count++;
            }
        }
        if(count <2){
            radioButtonForIP = 6;
            radioButtonForLegalPerson = 5;
        }
    }

    public void choicePerson (){
        determineRadioButton();
        driver.findElement(By.xpath("(//*[@class='chouse-company'] /* /following::*[1])["+ radioButtonForPerson + "]")).click();
        readBackLocatorsForPerson();
    }
    public void choiceIP (){
        determineRadioButton();
        driver.findElement(By.xpath("(//*[@class='chouse-company'] /* /following::*[1])["+ radioButtonForIP + "]")).click();
        driver.findElement(By.xpath("(//*[@class='chouse-company'] /* /following::*[1])["+ radioButtonForIP + "]")).isSelected();
        readBackLocatorsForIP();
    }
    public void choiceLegalPerson (){
        determineRadioButton();
        driver.findElement(By.xpath("(//*[@class='chouse-company'] /* /following::*[1])["+ radioButtonForLegalPerson + "]")).click();
        readBackLocatorsForLegalPerson();
    }
    public void choicePersonForCreatingLocators (){
        driver.findElement(By.xpath("(//*[@class='chouse-company'] /* /following::*[1])["+ radioButtonForPerson + "]")).click();
    }
    public void choiceIPForCreatingLocators (){
        driver.findElement(By.xpath("(//*[@class='chouse-company'] /* /following::*[1])["+ radioButtonForIP + "]")).click();
    }
    public void choiceLegalPersonForCreatingLocators (){
        driver.findElement(By.xpath("(//*[@class='chouse-company'] /* /following::*[1])["+ radioButtonForLegalPerson + "]")).click();
    }
    public void checkRequiredFields(){
        driver.findElement(By.xpath("//*[@placeholder='Введите e-mail'][not(ancestor-or-self::*[@style = 'display: none;'])]")).clear();
        Assert.assertTrue("Поле емаил не обязательно", driver.findElement(By.cssSelector
                (".js_person_type_block:not([style='display: none;']) [required][type='email']")).isDisplayed());
        Assert.assertTrue("Поле имя не обязательно", driver.findElement(By.cssSelector
                (".js_person_type_block:not([style='display: none;']) [required][placeholder='Введите имя']")).isDisplayed());
        Assert.assertTrue("Поле фамилия не обязательно", driver.findElement(By.cssSelector
                (".js_person_type_block:not([style='display: none;']) [required][placeholder='Введите фамилию']")).isDisplayed());
        Assert.assertTrue("Поле Создать пароль не обязательно", driver.findElement(By.cssSelector
                (".js_person_type_block:not([style='display: none;']) [required][placeholder='Создать пароль']")).isDisplayed());
        Assert.assertTrue("Поле Повторите пароль не обязательно", driver.findElement(By.cssSelector
                (".js_person_type_block:not([style='display: none;']) [required][placeholder='Повторите пароль']")).isDisplayed());
        driver.findElement(registerButtonOnRegistrationTabLocator).click();
        Assert.assertEquals("Вкладка Создать учетную запись не открылась"
                , "Создать учетную запись", driver.findElement(registrationTabLocator).getText());
    }
    public void enterWrongConfirmPassword(){
        driver.findElement(confirmPasswordInputLocator).sendKeys("password");
        driver.findElement(registerButtonOnRegistrationTabLocator).click();
        Assert.assertTrue("Сообщение Пароли не совпадают не отобразилось", driver.findElement(
                By.xpath("//*[.='Пароли не совпадают. Введите корректный подтверждающий пароль.']")).isDisplayed());
    }
    public void checkingMessageAboutExistingEmail(){
        driver.findElement(registerButtonOnRegistrationTabLocator).click();
        Assert.assertTrue("Email уже существует", driver.findElement(
                By.xpath("//*[contains(text(), 'Пользователь с таким e-mail')]")).isDisplayed());
    }

    public void goToAuthorizationTAb(){
        driver.findElement(authorizationButtonOnRegistrationTabLocator).click();
        Assert.assertTrue("Окно авторизации не открылось"
                ,driver.findElement(windowOfRegisterLocator).isDisplayed());
    }
    public void changeINNonTheSame(){
        driver.findElement(By.cssSelector(".js_person_type_block:not([style='display: none;']) [placeholder='ИНН']")).clear();

        driver.findElement(By.cssSelector(".js_person_type_block:not([style='display: none;']) [placeholder='ИНН']")).sendKeys(iNN);

    }
    public int getCountsOfDigits(long number) {
        int count = (number == 0) ? 1 : 0;
        while (number != 0) {
            count++;
            number /= 10;
        }
        return count;
    }
    public void enterINNToTheSame(){

        driver.findElement(inputINNLocator).clear();
        enterNumberByDigit(theSameInnManual, inputINNLocator);
    }
    public void enterNumberByDigit(String stringNumber, By locator){
        for (int i = 0; i < stringNumber.length() ; i++) {
            driver.findElement(locator).sendKeys(String.valueOf(stringNumber.charAt(i)));
            waitingMilliSecond();
        }
        driver.findElement(locator).sendKeys("a");
        explicitWaiting();
        driver.findElement(locator).sendKeys("\u0008");
    }
    public void changeEmail(){
        driver.findElement(By.cssSelector(".js_person_type_block:not([style='display: none;']) [placeholder='Введите e-mail']")).clear();
        driver.findElement(By.cssSelector(".js_person_type_block:not([style='display: none;']) [placeholder='Введите e-mail']"))
                .sendKeys("differentEmail" + email);
    }
    public void changeEmailOnTheSame(){
        driver.findElement(By.cssSelector(".js_person_type_block:not([style='display: none;']) [placeholder='Введите e-mail']")).clear();
        driver.findElement(By.cssSelector(".js_person_type_block:not([style='display: none;']) [placeholder='Введите e-mail']"))
                .sendKeys(theSameEmail);
    }

    By organizationsForConfirmRegistrationLocator = By.xpath("(//*[text()='Подтверждение регистрации'])[1]");
    By chekBoxForUserSelectionLocator = By.xpath("(//*[@class='adm-designed-checkbox-label adm-checkbox'])[1]");
    By choiceOfActionForApproveLocator = By.xpath("//*[@value = 'approve']");
    By applyApprovalLocator = By.xpath("//*[@value = 'Применить']");

    public void confirmRegistration(){
        try{
            driver.findElement(registerButtonOnRegistrationTabLocator).click();
        }catch (Exception e){}
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInFromAuthorizationTab();
        //navigationToAuthorizationTab();
        navigationToAdminPartFromMeanPage();
        driver.findElement(sotbitTabLocator).click();
        try {
            driver.findElement(organizationsForConfirmRegistrationLocator).click();
        }catch (Exception e){
            driver.findElement(By.xpath("(//*[text()='Оптовые покупатели'])")).click();
            driver.findElement(organizationsForConfirmRegistrationLocator).click();
        }
        driver.findElement(chekBoxForUserSelectionLocator).click();
        driver.findElement(choiceOfActionForApproveLocator).click();
        driver.findElement(applyApprovalLocator).click();
        exitFromB2B();
        navigationToAuthorizationTab();
    }

    public void tryConfirmRegistration(){
        determineWhetherRegistrationNeedsToBeConfirmed();
        if (doNeedToConfirmRegistration == true){
            confirmRegistration();
        }
    }



                                     //FOR AUTHORIZATION


    By loginButtonLocator = By.xpath("//*[contains(@class,'btn')][text()='Войти ']");
    By loginInputLocator = By.cssSelector("[placeholder='Логин']");
    By passwordInputInAuthorizationTanLocator = By.cssSelector("[type='password']");
    By rememberOnAuthorizationPageLocator = By.xpath("//*[@name='USER_REMEMBER']");
    By forgetPasswordButtonLocator = By.xpath("//*[text()='Забыли пароль?']");
    By emailInputForRequestingPassword = By.cssSelector("[type='email']");
    By sendButtonLocator = By.xpath("//*[contains(@class,'btn')][text()='Выслать']");
    By authorizationButtonOnPasswordRecoveryPageLocator = By.xpath("//*[contains(@class,'btn')][text()='Авторизация']");

    public void fillingFieldsOnTheLogInTab (){
        System.out.println(email);
        System.out.println(password);
        try {
            driver.findElement(loginInputLocator).sendKeys(email);
        }catch (Exception e){
            navigationToAuthorizationTab();
            driver.findElement(loginInputLocator).sendKeys(email);
        }
        Assert.assertEquals("Емаил не отображается"
                , email, driver.findElement(loginInputLocator).getAttribute("value"));
        driver.findElement(passwordInputInAuthorizationTanLocator).sendKeys(password);
        Assert.assertEquals("Пароль не отображается", password, driver.findElement(passwordInputInAuthorizationTanLocator).getAttribute("value"));
    }
    public void logInFromAuthorizationTab(){
        driver.findElement(loginButtonLocator).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".navbar")).isDisplayed());
    }
    public void logInExistingUserWithoutConfirm(){
        driver.findElement(loginButtonLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".validation-invalid-label")));
        Assert.assertTrue(driver.findElement(By.cssSelector(".validation-invalid-label")).isDisplayed());
        try {
            Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Ваш логин заблокирован.']")).isDisplayed());
        }catch (Exception e){
            Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Неверный логин или пароль.']")).isDisplayed());
        }
    }
    public void ligInExistingUserWithWrongPassword(){
        driver.findElement(passwordInputInAuthorizationTanLocator).clear();
        driver.findElement(passwordInputInAuthorizationTanLocator).sendKeys("Qwer1234!");
        Assert.assertEquals("Пароль не отображается", "Qwer1234!", driver.findElement(passwordInputInAuthorizationTanLocator).getAttribute("value"));
        driver.findElement(loginButtonLocator).click();
        Assert.assertTrue("Нет сообщения о неверном пароле"
                ,driver.findElement(By.xpath("//*[@class='validation-invalid-label']")).isDisplayed());
    }
    public void passwordRequired(){
        driver.findElement(forgetPasswordButtonLocator).click();
        driver.findElement(emailInputForRequestingPassword).clear();
        driver.findElement(emailInputForRequestingPassword).sendKeys(email);
        Assert.assertEquals("Емаил не отображается"
                , email, driver.findElement(emailInputForRequestingPassword).getAttribute("value"));
        driver.findElement(sendButtonLocator).click();
        Assert.assertTrue("Нет сообщения о отправке ваших данных"
                , driver.findElement(By.xpath("//*[contains(text(), 'Контрольная строка, а также ваши регистрационные данные " +
                        "были высланы на email. Пожалуйста, дождитесь письма, так как контрольная строка изменяется при каждом запросе.')]")).isDisplayed());
        System.out.println(email);
        System.out.println(password);
    }
    public void passwordRequiredForUnregisteredUser(){
        driver.findElement(forgetPasswordButtonLocator).click();
        driver.findElement(emailInputForRequestingPassword).clear();
        driver.findElement(emailInputForRequestingPassword).sendKeys(email);
        Assert.assertEquals("Емаил не отображается"
                , email, driver.findElement(emailInputForRequestingPassword).getAttribute("value"));
        driver.findElement(sendButtonLocator).click();
        Assert.assertTrue("Нет сообщения что пользователь с таким email не зарегестрирван"
                ,driver.findElement(By.xpath("//*[contains(text(), 'Пользователь с таким email не зарегистрирован.')]")).isDisplayed());
    }
    public void goToAuthorizationTAbFromPasswordRequiredWindow(){
        driver.findElement(authorizationButtonOnPasswordRecoveryPageLocator).click();
        Assert.assertTrue("Окно авторизации не открылось"
                ,driver.findElement(windowOfRegisterLocator).isDisplayed());
    }

    public void logInFromAuthorizationTabUseTheSameInn(){
        driver.findElement(registerButtonOnRegistrationTabLocator).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        driver.findElement(By.cssSelector(".btn.bg-success")).click();
    }
    public void acceptEmployee(){
        driver.findElement(By.xpath("//*[contains(text(), 'Заявки' )]")).click();
        driver.findElement(By.xpath("//*[contains(@class, 'active')] //*[@class = 'main-grid-row-action-button']")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Принять')]")).click();
    }
    public void rejectEmployee(){
        driver.findElement(By.xpath("//*[contains(text(), 'Заявки' )]")).click();
        driver.findElement(By.xpath("//*[contains(@class, 'active')] //*[@class = 'main-grid-row-action-button']")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Отклонить')]")).click();
        driver.findElement(By.xpath("//*[contains(@class, 'nav-item')] /*[contains(text(), 'Сотрудники' )]")).click();
        driver.findElement(By.xpath("//*[@name='show-all-users']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".main-grid-empty-text")).isDisplayed());
    }
    public void checkThatEmployeeIsDisplayedFromTheManager(){
        driver.findElement(By.xpath("//*[contains(@class, 'nav-item')] /*[contains(text(), 'Сотрудники' )]")).click();
        driver.findElement(By.xpath("//*[@name='show-all-users']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".staff-list__companies-list")));
        try{
            Assert.assertTrue(driver.findElement(By.cssSelector(".staff-list__companies-list")).isDisplayed());
        }catch (Exception e){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Здесь плавающая ОШИБКА");
        }
    }
    public void changeToTheEmployeeSEmailOnAuthorizationTab(){
        driver.findElement(By.name("USER_LOGIN")).clear();
        driver.findElement(By.name("USER_LOGIN")).sendKeys("differentEmail" + email);
    }
    public void chekThatTheOrganizationIsDisplayedByTheEmployee(){
        Assert.assertTrue(driver.findElement(By.cssSelector(".staff-list__companies-list")).isDisplayed());
        try{
            Assert.assertTrue(driver.findElement(By.cssSelector(".auth-company-change__current")).isDisplayed());
        }catch (Exception e){
            Assert.assertTrue(driver.findElement(By.cssSelector(".staff-personal-photo")).isDisplayed());
        }
    }

    public void creatingArrayWithExistingLocatorsForIP(){
        counterOfExistingLocators = 0;
        arrayWithExistingLocatorsForIP = new String[driver.findElements(By.xpath("//*[@class='form-control'][not(ancestor-or-self::*[@style = 'display: none;'])]")).size() * 2];
        for (int i = 0; i < driver.findElements(By.xpath("//*[@class='form-control'][not(ancestor-or-self::*[@style = 'display: none;'])]")).size(); i++) {
            for (int j = 0; j < arrayWithAllPossibleLocators.length; j=j+2) {

                if(driver.findElement(By.xpath("(//*[@class='form-control'][not(ancestor-or-self::*[@style = 'display: none;'])])[" + (i+1) + "]/preceding::label[1]"))
                        .getText().replaceAll("[*,:]", "").trim().equals(arrayWithAllPossibleLocators[j])){
                    arrayWithExistingLocatorsForIP[counterOfExistingLocators] = arrayWithAllPossibleLocators[j];
                    arrayWithExistingLocatorsForIP[counterOfExistingLocators+1] = arrayWithAllPossibleLocators[j+1];
                    driver.findElement(By.xpath("//*[contains(text(), '" + arrayWithExistingLocatorsForIP[counterOfExistingLocators]
                            + "')][not(ancestor-or-self::*[@style = 'display: none;'])] /following::*[2]")).sendKeys(arrayWithExistingLocatorsForIP[counterOfExistingLocators+1]);
                    System.out.print(arrayWithExistingLocatorsForIP[counterOfExistingLocators] + "_____");
                    System.out.println(arrayWithExistingLocatorsForIP[counterOfExistingLocators+1]);
                    counterOfExistingLocators = counterOfExistingLocators+2;
                    break;
                }

            }
        }
        arrayWithExistingLocatorsForIP = Arrays.stream(arrayWithExistingLocatorsForIP)
                .filter(s -> (s != null && s.length() > 0))
                .toArray(String[]::new);
        explicitWaiting();



//        counterOfExistingLocators =0;
//        arrayWithExistingLocatorsForIP = new String[arrayWithAllPossibleLocators.length];
//
//        for (int i = 0; i < arrayWithAllPossibleLocators.length; i=i+2) {
//            try {
//                driver.findElement(By.xpath(arrayWithAllPossibleLocators[i])).sendKeys(arrayWithAllPossibleLocators[i+1]);
//                arrayWithExistingLocatorsForIP[counterOfExistingLocators] = arrayWithAllPossibleLocators[i];
//                arrayWithExistingLocatorsForIP[counterOfExistingLocators+1]=arrayWithAllPossibleLocators[i+1];
//                System.out.print(arrayWithExistingLocatorsForIP[counterOfExistingLocators] + "_____");
//                System.out.println(arrayWithExistingLocatorsForIP[counterOfExistingLocators+1]);
//                counterOfExistingLocators = counterOfExistingLocators+2;
//            }catch (Exception e){}
//        }
//
//        arrayWithExistingLocatorsForIP = Arrays.stream(arrayWithExistingLocatorsForIP)
//                .filter(s -> (s != null && s.length() > 0))
//                .toArray(String[]::new);
//        explicitWaiting();
    }
    public void creatingArrayWithExistingLocatorsForLegalPerson(){
        counterOfExistingLocators =0;
        arrayWithExistingLocatorsForLegalPerson = new String[driver.findElements(By.xpath("//*[@class='form-control'][not(ancestor-or-self::*[@style = 'display: none;'])]")).size() *2];
        for (int i = 0; i < driver.findElements(By.xpath("//*[@class='form-control'][not(ancestor-or-self::*[@style = 'display: none;'])]")).size(); i++) {
            for (int j = 0; j < arrayWithAllPossibleLocators.length; j=j+2) {

                if(driver.findElement(By.xpath("(//*[@class='form-control'][not(ancestor-or-self::*[@style = 'display: none;'])])[" + (i+1) + "]/preceding::label[1]"))
                        .getText().replaceAll("[*,:]", "").trim().equals(arrayWithAllPossibleLocators[j])) {
                    arrayWithExistingLocatorsForLegalPerson[counterOfExistingLocators] = arrayWithAllPossibleLocators[j];
                    arrayWithExistingLocatorsForLegalPerson[counterOfExistingLocators + 1] = arrayWithAllPossibleLocators[j + 1];
                    driver.findElement(By.xpath("//*[contains(text(), '" + arrayWithExistingLocatorsForLegalPerson[counterOfExistingLocators]
                            + "')][not(ancestor-or-self::*[@style = 'display: none;'])] /following::*[2]")).sendKeys(arrayWithExistingLocatorsForLegalPerson[counterOfExistingLocators + 1]);
                    System.out.print(arrayWithExistingLocatorsForLegalPerson[counterOfExistingLocators] + "_____");
                    System.out.println(arrayWithExistingLocatorsForLegalPerson[counterOfExistingLocators + 1]);
                    counterOfExistingLocators = counterOfExistingLocators + 2;
                    break;
                }
            }
        }
        arrayWithExistingLocatorsForLegalPerson = Arrays.stream(arrayWithExistingLocatorsForLegalPerson)
                .filter(s -> (s != null && s.length() > 0))
                .toArray(String[]::new);
        explicitWaiting();

//        counterOfExistingLocators =0;
//        arrayWithExistingLocatorsForLegalPerson = new String[arrayWithAllPossibleLocators.length];
//        for (int i = 0; i < arrayWithAllPossibleLocators.length; i=i+2) {
//            try {
//                driver.findElement(By.xpath(arrayWithAllPossibleLocators[i])).sendKeys(arrayWithAllPossibleLocators[i+1]);
//                arrayWithExistingLocatorsForLegalPerson[counterOfExistingLocators] = arrayWithAllPossibleLocators[i];
//                arrayWithExistingLocatorsForLegalPerson[counterOfExistingLocators+1]=arrayWithAllPossibleLocators[i+1];
//                System.out.print(arrayWithExistingLocatorsForLegalPerson[counterOfExistingLocators] + "_____");
//                System.out.println(arrayWithExistingLocatorsForLegalPerson[counterOfExistingLocators+1]);
//                counterOfExistingLocators = counterOfExistingLocators+2;
//            }catch (Exception e){}
//        }
//
//        arrayWithExistingLocatorsForLegalPerson = Arrays.stream(arrayWithExistingLocatorsForLegalPerson)
//                .filter(s -> (s != null && s.length() > 0))
//                .toArray(String[]::new);
    }

    public void creatingArrayWithExistingLocatorsForPerson(){
        counterOfExistingLocators =0;
        arrayWithExistingLocatorsForPerson = new String[driver.findElements(By.xpath("//*[@class='form-control'][not(ancestor-or-self::*[@style = 'display: none;'])]")).size() * 2];
        driver.findElement(By.xpath("(//*[@class='form-control'][not(ancestor-or-self::*[@style = 'display: none;'])])[4]/preceding::label[1]"))
                .getText().replaceAll("[*, :]", "").trim();
        for (int i = 0; i < driver.findElements(By.xpath("//*[@class='form-control'][not(ancestor-or-self::*[@style = 'display: none;'])]")).size(); i++) {
            for (int j = 0; j < arrayWithAllPossibleLocators.length; j=j+2) {
                if(driver.findElement(By.xpath("(//*[@class='form-control'][not(ancestor-or-self::*[@style = 'display: none;'])])[" + (i+1) + "]/preceding::label[1]"))
                        .getText().replaceAll("[*,:]", "").trim().equals(arrayWithAllPossibleLocators[j])){
                    arrayWithExistingLocatorsForPerson[counterOfExistingLocators] = arrayWithAllPossibleLocators[j];
                    arrayWithExistingLocatorsForPerson[counterOfExistingLocators+1]=arrayWithAllPossibleLocators[j+1];
                    driver.findElement(By.xpath("//*[contains(text(), '" + arrayWithExistingLocatorsForPerson[counterOfExistingLocators]
                            + "')][not(ancestor-or-self::*[@style = 'display: none;'])] /following::*[2]")).sendKeys(arrayWithExistingLocatorsForPerson[counterOfExistingLocators+1]);
                    System.out.print(arrayWithExistingLocatorsForPerson[counterOfExistingLocators] + "_____");
                    System.out.println(arrayWithExistingLocatorsForPerson[counterOfExistingLocators+1]);
                    counterOfExistingLocators = counterOfExistingLocators+2;
                    break;
                }
            }
        }
        arrayWithExistingLocatorsForPerson = Arrays.stream(arrayWithExistingLocatorsForPerson)
                .filter(s -> (s != null && s.length() > 0))
                .toArray(String[]::new);
        explicitWaiting();
//        counterOfExistingLocators =0;
//        arrayWithExistingLocatorsForPerson = new String[arrayWithAllPossibleLocators.length];
//
//        for (int i = 0; i < arrayWithAllPossibleLocators.length; i=i+2) {
//            try {
//                driver.findElement(By.xpath(arrayWithAllPossibleLocators[i])).sendKeys(arrayWithAllPossibleLocators[i+1]);
//                arrayWithExistingLocatorsForPerson[counterOfExistingLocators] = arrayWithAllPossibleLocators[i];
//                arrayWithExistingLocatorsForPerson[counterOfExistingLocators+1]=arrayWithAllPossibleLocators[i+1];
//                System.out.print(arrayWithExistingLocatorsForPerson[counterOfExistingLocators] + "_____");
//                System.out.println(arrayWithExistingLocatorsForPerson[counterOfExistingLocators+1]);
//                counterOfExistingLocators = counterOfExistingLocators+2;
//            }catch (Exception e){}
//        }
//
//        arrayWithExistingLocatorsForPerson = Arrays.stream(arrayWithExistingLocatorsForPerson)
//                .filter(s -> (s != null && s.length() > 0))
//                .toArray(String[]::new);
    }
    public  void writingArrayWithLocatorsForIPOnComputer() {
        //Запись в файл
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(fileNameForIP));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.writeObject(this.arrayWithExistingLocatorsForIP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readBackLocatorsForIP() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(fileNameForIP));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            arrayWithExistingLocatorsForIP = (String[])inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public  void writingArrayWithLocatorsForLegalPersonOnComputer() {
        //Запись в файл
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(fileNameForLegalPerson));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.writeObject(this.arrayWithExistingLocatorsForLegalPerson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  void writingArrayWithLocatorsForPersonOnComputer() {
        //Запись в файл
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(fileNameForPerson));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.writeObject(this.arrayWithExistingLocatorsForPerson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readBackLocatorsForLegalPerson() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(fileNameForLegalPerson));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            arrayWithExistingLocatorsForLegalPerson = (String[])inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void readBackLocatorsForPerson() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(fileNameForPerson));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            arrayWithExistingLocatorsForPerson = (String[])inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void checkingStatusOfOrganizationWithoutConfirm(){
        determineWhetherRegistrationOrganizationNeedsToBeConfirmed();
        if (doNeedToConfirmRegistrationOrganization == true){
            Assert.assertTrue(driver.findElement(By.xpath("//*[text()='На модерации']")).isDisplayed());
        }else {
            Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Одобрена']")).isDisplayed());
        }
    }
    public void checkingThatEventAboutChangePasswordIsSuccessful(){
        Assert.assertTrue(driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[1] //*[contains(text(), 'SUCCESS_CHANGE_PASSWORD')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[1] //*[contains(text(), '" + email + "')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("((//*[@class='adm-list-table-row'])[1] //* [@class = 'adm-list-table-cell'])[5] [text()= 'Y']")).isDisplayed());
    }




}




