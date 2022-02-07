package OrganizationsWithExtendedVersion;

import BaseActions.BaseActions;
import MakingOrders.MakingOrders;
import RegistrationAndAuthorization.MethodsForRegistrationAndAuthorization;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MethodsForAddingOrganizationsWithExtendedVersion extends BaseActions {
    MethodsForRegistrationAndAuthorization registr = new MethodsForRegistrationAndAuthorization();


    public By saveButtonOnTheOrganizationTabLocator = By.xpath("//*[@name='save']");
    By addOrganizationButtonLocator = By.xpath("//*[@type='button'][contains(text(), 'Добавить организацию')]");
    By joinToOrganizationButtonLocator = By.cssSelector(".btn.index_company-join_organization-button");
    By dropDownListForSelectingTypeOfOrganizationLocator = By.cssSelector("#select2-person-type-container");
    By individualBusinessmanFromDropDownListLocator = By.xpath("//*[contains(@class, 'select2-results__option')][contains(text(),'Индивидуальный предприниматель')]");
    By legalPersonFromDropDownListLocator = By.xpath("//*[contains(@class, 'select2-results__option')][contains(text(),'Юридическое лицо')]");
    By employeesTab = By.xpath("//*[contains(@class, 'icon')]/following::*[contains(text(), 'Сотрудники')]");
    public String stringOfNinetyNineSymbols = "фыв'АПР'ячтьjAb=&G;PwAZ*[U]AC3U%/%hNAV%:9Q#rf&v)uLKpx3W]<G*PhK8f8[]6@J\"kT`%~HD>g`kW*G9{ZJ{E&ZRVYQ)z";

    public String[] arrayWithAllPossibleLocatorsForOrganization={
            "Название", nameCompany,
            "Названиекомпании", nameCompany,
            "Адресдоставки", address,
            "Контактноелицо", lastName,
            "Город", "Сызрань",
            "Индекс", index,
            "Факс", index,
            "Телефон", mobilePhone,
            "E-Mail", email,
            "Ф.И.О.", nameCompany,
            "БИК", name + lastName,
            "ОГРНИ", iNNManual,
            "ОГРН", iNNManual,
            "КПП", iNNManual,
            "Фактическийадрес", address,
            "Юридическийадрес", address,
            "Название", nameCompany,
            "Должность", appeal,
            "Контактноелицо", name + lastName,
            "Корреспондентскийсчет", iNNManual,
            "Расчетныйсчет", iNNManual,
            "Наименованиебанка", name + lastName,
            "E-Mail(логин)", email,
            "Имя", name,
            "Фамилия", lastName,
            "Мобильный", mobilePhone,
            "БСН", iNNManual,
            "НаименованиеКРП", index,
            "КодКРП(сучетомфилиалов)", index,
            "КАТО", mobilePhone,
            "Фамилияимяотчестворуководителя", lastName + name,
            "Город", "Сызрань",
            "Наименование компании", nameCompany,
            "Отчество", "Батькович",
            "Расчётныйсчёт", randomNumber(12),
            "ОКВЭД", randomNumber(12),
            "Телефонорганизации", randomNumber(12),
            "ОГРНИП", randomNumber(12),
            "Корреспондентскийсчёт", randomNumber(12),
    };

    public void fillingFieldsForCreatingOrganization(){
        for (int i = 0; i < driver.findElements(By.xpath("//*[@class='form-control']")).size(); i++) {
            for (int j = 0; j < arrayWithAllPossibleLocatorsForOrganization.length; j=j+2) {
                if(driver.findElement(By.xpath("(//*[@class='form-control']) [" + (i+1) + "] /preceding::label[1]"))
                        .getText().replaceAll("[*, ,:]", "").equals(arrayWithAllPossibleLocatorsForOrganization[j])){
                    driver.findElement(By.xpath("(//*[@class='form-control'])[" + (i+1) + "]")).sendKeys(arrayWithAllPossibleLocatorsForOrganization[j+1]);
                    break;
                }
            }
        }
        try{
            driver.findElement(By.xpath("//*[contains(@placeholder, 'естоположение')][not(ancestor-or-self::*[@style = 'display: none;'])]")).sendKeys("Россия");
            driver.findElement(By.xpath("//*[contains(@class, 'bx-ui')]/*[text()='Россия']")).click();
        }catch (Exception e){
            try {
                driver.findElement(By.xpath("//*[contains(@placeholder, 'азвание')][not(ancestor-or-self::*[@style = 'display: none;'])]")).sendKeys("Россия");
                driver.findElement(By.xpath("//*[contains(@class, 'bx-ui')]/*[text()='Россия']")).click();
            }catch (Exception exception){}
        }

        try {
            driver.findElement(By.xpath("//*[contains(text(), 'ИНН:')]/following::*[1]")).clear();
            registr.enterNumberByDigit(iNNManual, By.xpath("//*[contains(text(), 'ИНН:')]/following::*[1]"));
        }catch (Exception e){}
    }



    public void selectionFromDropDownListIndividualBusinessman() {
        driver.findElement(dropDownListForSelectingTypeOfOrganizationLocator).click();
        try {
            driver.findElement(individualBusinessmanFromDropDownListLocator).click();
        }catch (Exception e){
            driver.findElement(By.xpath("//li[contains(text(), 'ИП')]")).click();
        }
        try {
            Assert.assertTrue(driver.findElement(By.xpath("//*[@title = 'Индивидуальный предприниматель']")).isDisplayed());
        }catch (Exception e){
            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@title, 'ИП')]")).isDisplayed());
        }
    }

    public void navigationToAddOrganizationTab() {
        driver.findElement(addOrganizationButtonLocator).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".row.add-company__wrapper")).isDisplayed());
    }

    public void selectionFromDropDownListLegalPerson() {
        driver.findElement(dropDownListForSelectingTypeOfOrganizationLocator).click();
        driver.findElement(legalPersonFromDropDownListLocator).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@title, 'Юридическое лицо')]")).isDisplayed());
    }



    public void creatingOrganization() {
        driver.findElement(saveButtonOnTheOrganizationTabLocator).click();
        navigationToOrganizationTab();
        try {
           Assert.assertTrue(driver.findElement(By.xpath("//*[text()='" + nameCompany + "']")).isDisplayed());
        }catch (Exception e){
                sortingOrganizationByDecrease();
                Assert.assertTrue(driver.findElement(By.xpath("//*[text()='" + nameCompany + "']")).isDisplayed());

        }
    }

    public void checkingThatTheFieldsAreRequired() {
        selectionFromDropDownListLegalPerson();
        try {
            Assert.assertTrue("Поле  не обзятельное", driver.findElement(By.xpath("(//*[contains(text(),'Название:')]/following::*[2])[@required]")).isDisplayed());
        }catch (Exception e){}
        Assert.assertTrue("Поле  не обязтельное", driver.findElement(By.xpath("(//*[contains(text(),'Название компании')]/following::*[1])[@required]")).isDisplayed());
        Assert.assertTrue("Поле  не обязтельное", driver.findElement(By.xpath("(//*[contains(text(),'E-Mail')]/following::*[1])[@required]")).isDisplayed());
        selectionFromDropDownListIndividualBusinessman();
        fillingFieldsForCreatingOrganization();
        try {
            driver.findElement(By.xpath("(//*[@placeholder='Выберите местоположение ...'])[1]")).clear();
        }catch (Exception e){
            driver.findElement(By.xpath("//*[contains(@placeholder, 'название')][not(ancestor-or-self::*[@style = 'display: none;'])]")).clear();
        }
        driver.findElement(saveButtonOnTheOrganizationTabLocator).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".validation-invalid-label.errortext")).isDisplayed());
    }

    public void checkingAvailableActionsWithOrganization( boolean confirmFromAdmin) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("(//*[@class='main-grid-row main-grid-row-body'])[1] /*[7]"), "Одобрена"));
        explicitWaiting();
        driver.findElement(firstHamburgerMenuOnTheOrganizationTabLocator).click();
        driver.findElement(By.xpath("(//*[@class='menu-popup-item-text'])[1]")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".row.companies-wrapper")).isDisplayed());
        driver.findElement(By.xpath("//a[contains(text(), 'Документы')]")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("#DOCUMENTS_LIST")).isDisplayed());
        driver.findElement(ordersTabInTheOrganization).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".personal_list_order")).isDisplayed());
        driver.findElement(By.xpath("//a[contains(text(), 'Общие')]")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".card-bitrix-cabinet")).isDisplayed());
        navigationToOrganizationTab();
        sortingOrganizationByDecrease();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("(//*[@class='main-grid-row main-grid-row-body'])[1] /*[7]"), "Одобрена"));
        explicitWaiting();
        driver.findElement(By.xpath("(//*[@class='main-grid-row-action-button'])[1]")).click();
        driver.findElement(By.xpath("(//*[@class='menu-popup-item-text'])[2]")).click();
        tempValue = nameCompany + "NewName";
        tempValueForNumbers = iNNManual + "123";
        driver.findElement(By.xpath("//*[contains(text(),'Название:')]/following::*[2]")).clear();
        driver.findElement(By.xpath("//*[contains(text(),'Название:')]/following::*[2]")).sendKeys(tempValue);
        tempValue = nameCompany + "NewName";
        driver.findElement(By.xpath("//*[contains(text(),'Название компании')]/following::*[1]")).clear();
        driver.findElement(By.xpath("//*[contains(text(),'Название компании')]/following::*[1]")).sendKeys(tempValue);
        driver.findElement(By.xpath("//*[contains(text(),'ИНН:')]/following::*[1]")).clear();
        driver.findElement(By.xpath("//*[contains(text(),'ИНН:')]/following::*[1]")).sendKeys(tempValueForNumbers);
        driver.findElement(By.xpath("//*[@value='Сохранить']")).click();
        try {
            standardConfirmationOfTheActionOnThePage();
        }catch (Exception e){}
        try {
            navigationToOrganizationTab();
        }catch (Exception e){
            navigationToOrganizationTab();
        }
        sortingOrganizationByDecrease();
        determineWhetherRegistrationOrganizationNeedsToBeConfirmed();
        if (doNeedToConfirmRegistrationOrganization == true){
            try {
                wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("(//*[@class='main-grid-row main-grid-row-body'])[1] /*[7]"), "На модерации"));
            }catch (Exception e){
                driver.findElement(By.xpath("//*[@class='main-ui-pagination-arrow']")).click();
                sortingOrganizationByDecrease();
                wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("(//*[@class='main-grid-row main-grid-row-body'])[1] /*[7]"), "На модерации"));
            }
        }
        System.out.println(driver.findElement(By.xpath("(//*[@class = 'main-grid-row main-grid-row-body'])[1] /*[3]")).getText());

        if (confirmFromAdmin == true){
            confirmRegistrationOfOrganizationFromAdmin();
        }else {
            tryConfirmRegistrationOfOrganizationInB2bFromTheUser();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
        }

        navigationToOrganizationTab();
        sortingOrganizationByDecrease();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("(//*[@class='main-grid-row main-grid-row-body'])[1] /*[7]"), "Одобрена"));
        explicitWaiting();
        driver.findElement(By.xpath("(//*[@class='main-grid-row-action-button'])[1]")).click();
        driver.findElement(By.xpath("(//*[@class='menu-popup-item-text'])[2]")).click();
        System.out.println(driver.findElement(By.xpath("//*[contains(text(),'ИНН:')]/following::*[1]")).getAttribute("value"));
        try {
            Assert.assertEquals(driver.findElement(By.xpath("//*[contains(text(),'ИНН:')]/following::*[1]")).getAttribute("value"), tempValueForNumbers);
        }catch (Exception e){
            navigationToOrganizationTab();
            driver.findElement(By.xpath("//*[@class='main-ui-pagination-arrow']")).click();
            sortingOrganizationByDecrease();
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("(//*[@class='main-grid-row main-grid-row-body'])[1] /*[7]"), "Одобрена"));
            explicitWaiting();
            driver.findElement(By.xpath("(//*[@class='main-grid-row-action-button'])[1]")).click();
            driver.findElement(By.xpath("(//*[@class='menu-popup-item-text'])[2]")).click();
            Assert.assertEquals(driver.findElement(By.xpath("//*[contains(text(),'ИНН:')]/following::*[1]")).getAttribute("value"), tempValueForNumbers);
        }
    }

    public void checkingAvailableActionsWithOrganizationInStandardVersion(){
        driver.findElement(By.xpath("(//span[(text()='Код')])[1]")).click();
        sortingOrganizationByDecrease();
        explicitWaiting();
        driver.findElement(By.xpath("(//*[@class='main-grid-row-action-button'])[1]")).click();
        Assert.assertTrue(driver.findElements(By.xpath("//*[@class='menu-popup-item-text']")).size() == 1);
        driver.findElement(By.xpath("(//*[@class='menu-popup-item-text'])[1]")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".row.companies-wrapper")).isDisplayed());
        driver.findElement(By.xpath("//a[contains(text(), 'Документы')]")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("#DOCUMENTS_LIST")).isDisplayed());
        driver.findElement(ordersTabInTheOrganization).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".personal_list_order")).isDisplayed());
        driver.findElement(By.xpath("//a[contains(text(), 'Общие')]")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".card-bitrix-cabinet")).isDisplayed());
        driver.findElement(By.xpath("//*[contains(text(),'Название компании')]/following::*[1]")).clear();
        driver.findElement(By.xpath("//*[contains(text(),'Название компании')]/following::*[1]")).sendKeys(nameCompany + "NewName");
        tempValue = nameCompany + "NewName";
        driver.findElement(By.xpath("//*[@value='Сохранить']")).click();
        navigationToOrganizationTab();
        sortingOrganizationByDecrease();
        explicitWaiting();
        System.out.println(driver.findElement(By.xpath("(//*[@class = 'main-grid-row main-grid-row-body'])[1] /*[3]")).getText());
        Assert.assertEquals(tempValue, driver.findElement(By.xpath("(//*[@class = 'main-grid-row main-grid-row-body'])[1] /*[3]")).getText());
    }


    public void navigationToPersonsTab() {
        driver.findElement(employeesTab).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("#STAFF_LIST")).isDisplayed());
    }

    public void confirmationEmployeeRequest() {
        navigationToPersonsTab();
        driver.findElement(By.xpath("//*[contains(text(), 'Заявки')]")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("#STAFF_UNCONFIRMED_LIST")).isDisplayed());
        driver.findElement(By.xpath("//*[@name='form_STAFF_UNCONFIRMED_LIST']//*[@class= 'main-grid-row-action-button']")).click();
        driver.findElement(By.xpath("//*[text()='Принять']")).click();
//        if (driver.findElement(By.xpath("(//*[@class= 'main-grid-row-action-button'])[2]")).isDisplayed()) {
//            driver.findElement(By.xpath("(//*[@class= 'main-grid-row-action-button'])[2]")).click();
//            driver.findElement(By.xpath("//*[text()='Принять']")).click();
//        }
    }

    public void requestToJoinTheCompany() {
        driver.findElement(joinToOrganizationButtonLocator).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".popup-window")).isDisplayed());
        driver.findElement(By.xpath("//input[contains(@placeholder,'Введите название')]")).sendKeys(nameCompany);
        waitingMilliSecond();waitingMilliSecond();waitingMilliSecond();waitingMilliSecond();waitingMilliSecond();
        driver.findElement(By.xpath("//*[contains(text(), '" + nameCompany + "')]")).click();
        driver.findElement(By.xpath("//*[@value = 'Присоединиться']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Ваш запрос успешно отправлен!']")));
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Ваш запрос успешно отправлен!']")).isDisplayed());
        driver.findElement(By.xpath("//*[@type='button'][contains(@class, 'btn_ok')]")).click();
    }

    public void checkingThatTheEmployeeOfCompanyIsDisplayed() {
        navigationToEmployeesTab();
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='" + nameCompany + "']")).isDisplayed());
    }
    public void checkingThatCompanyIsDisplayed() {
        navigationToOrganizationTab();
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='" + nameCompany + "']")).isDisplayed());
    }

    public void selectionFromTheHeaderOrganization(String nameCompany) {
        try {
            if (driver.findElement(By.xpath("//*[contains (text(), '" + nameCompany + "')]")).isDisplayed()){
                System.out.println("Организацию уже выбрана");
            }else {
                driver.findElement(By.cssSelector(".auth-company-change__current")).click();
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains (text(), '" + nameCompany + "')]")));
                    driver.findElement(By.xpath("//*[contains (text(), '" + nameCompany + "')]")).click();
                }catch (Exception e){
                    Assert.assertEquals(nameCompany, driver.findElement(By.cssSelector(".auth-company-change__current")).getText().replaceAll("[*]", ""));
                }
                wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".auth-company-change__current"), nameCompany));
                Assert.assertEquals(nameCompany, driver.findElement(By.cssSelector(".auth-company-change__current")).getText().replaceAll("[*]", ""));
            }
        }catch (Exception e){
            Assert.assertEquals(nameCompany, driver.findElement(By.cssSelector(".auth-company-change__current")).getText().replaceAll("[*]", ""));
        }
    }
    public void checkingTheCompanyNameInHeader() {
        Assert.assertEquals(nameCompany, driver.findElement(By.cssSelector(".auth-company-change__current")).getText());
    }


    public void addingAnEmployeeToAnOrganizationUsingByReferralLink() {
        driver.findElement(By.xpath("//*[@value='Отправить ссылку']")).click();
        driver.findElement(By.cssSelector(".btn_confirm")).click();
        driver.findElement(By.cssSelector(".btn_ok")).click();
    }
    public void fillingFieldForCreatingEmployeeUsingReferralLink(){
        navigationToPersonsTab();
        driver.findElement(buttonAddNewEmployee).click();
        driver.findElement(By.cssSelector(".register-referral-link")).click();
        driver.findElement(By.xpath("//*[@placeholder='Введите e-mail']")).sendKeys(emailEmployee);

        tempIntValue = driver.findElements(By.xpath("//*[@style='display: block;']//input[contains(@class,'select')]")).size();
        for (int i = 1; i < tempIntValue+1; i++) {
            driver.findElement(By.xpath("(//*[@style='display: block;']//input[contains(@class,'select')])[" + i + "]")).click();
            tempRandomNumber = 1 + (int) (Math.random() * driver.findElements(By.xpath("//*[contains(@class,'results__option')]")).size());
            if (tempRandomNumber == 1){
                tempRandomNumber++;
            }
            driver.findElement(By.xpath("(//*[contains(@class,'results__option')])[" + (tempRandomNumber) + "]")).click();
        }
    }

    public void addingAnBossToAnOrganizationFromAnotherUser() {
        navigationToPersonsTab();
        driver.findElement(By.xpath("//*[contains(text(), 'Добавить нового сотрудника')]")).click();
        driver.findElement(By.cssSelector(".register-referral-link")).click();
        driver.findElement(By.xpath("//*[@placeholder='Введите e-mail']")).sendKeys("qwert@qa.team");
        driver.findElement(By.xpath("(//*[@class='select2-selection__rendered'])[1]")).click();
        driver.findElement(By.xpath("//li[text()='Управляющий компанией']")).click();
        driver.findElement(By.xpath("(//*[@class='select2-selection__rendered'])[2]")).click();
        //driver.findElement(By.xpath("//*[contains(@class, 'select2-results__option')][text()='Все пользователи (в том числе неавторизованные)']")).click();
        driver.findElement(By.xpath("//*[contains(@class, 'select2-results__option')][text()='Зарегистрированные пользователи']")).click();
        driver.findElement(By.xpath("//*[@value='Отправить ссылку']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Пользователь с таким e-mail уже есть в системе.']")));
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Пользователь с таким e-mail уже есть в системе.']")).isDisplayed());
        driver.findElement(By.cssSelector(".btn_confirm")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'добавлен в список сотрудников.')]")).isDisplayed());
        driver.findElement(By.cssSelector(".btn_ok")).click();
    }

    public void addingTheSameEmployeeToAnOrganizationFromAnotherUser() {
        fillingFieldForCreatingEmployeeUsingReferralLink();
        driver.findElement(By.xpath("//*[@value='Отправить ссылку']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".errortext")).isDisplayed());
        driver.findElement(By.cssSelector(".popup-close")).click();
    }

    public void fillingFieldsOnTheRegisterNewEmployee(){
        navigationToPersonsTab();
        driver.findElement(buttonAddNewEmployee).click();

        for (int i = 0; i < driver.findElements(By.xpath("//input[contains(@placeholder,'')][@class='form-control']")).size(); i++) {
            for (int j = 0; j < arrayWithAllPossibleLocatorsForOrganization.length; j=j+2) {
                if(driver.findElement(By.xpath("(//*[@class='register-field-name'])["+ (i+1) +"]"))
                        .getText().replaceAll("[*, ]", "").equals(arrayWithAllPossibleLocatorsForOrganization[j])){
                    driver.findElement(By.xpath("(//*[@class='form-control'])[" + (i+1) + "]")).sendKeys(arrayWithAllPossibleLocatorsForOrganization[j+1]);
                    break;
                }
            }
        }
        driver.findElement(By.xpath("//*[@placeholder='Создать пароль']")).sendKeys(password);
        driver.findElement(By.xpath("//*[@placeholder='Повторите пароль']")).sendKeys(password);
        System.out.println("емаил созданного поьзователя" + email);
        System.out.println("пароль созданного поьзователя" + password);
        Assert.assertEquals("Емаил не отображается"
                ,email, driver.findElement(By.xpath("//*[contains(@placeholder,'Введите e-mai')][@class='form-control']")).getAttribute("value"));
        Assert.assertEquals("Пароль не отображается"
                ,password, driver.findElement(By.xpath("//*[contains(@placeholder,'Создать пароль')][@class='form-control']")).getAttribute("value"));
        Assert.assertEquals("Подтвержденый пароль не отображается"
                ,password, driver.findElement(By.xpath("//*[contains(@placeholder,'Повторите пароль')][@class='form-control']")).getAttribute("value"));

    }


    public void fillingAllFieldsOnTheRegisterNewEmployee() {
        fillingFieldsOnTheRegisterNewEmployee();
        tempIntValue = driver.findElements(By.xpath("//*[@name='regform']//input[contains(@class,'select')]")).size();
        for (int i = 1; i < tempIntValue+1; i++) {
            driver.findElement(By.xpath("(//*[@name='regform']//input[contains(@class,'select')])[" + i + "]")).click();
            tempRandomNumber = 1 + (int) (Math.random() * driver.findElements(By.xpath("//*[contains(@class,'results__option')]")).size());
            if (tempRandomNumber == 1){
                tempRandomNumber++;
            }
            driver.findElement(By.xpath("(//*[contains(@class,'results__option')])[" + (tempRandomNumber) + "]")).click();
        }
    }
    public void choosingBossRole(){
        try{
            driver.findElement(By.xpath("(//*[contains(@class, 'choice__remove')])[1]")).click();
            driver.findElement(By.xpath("//*[contains(@class, 'results__options')] /*[(text()= 'Управляющий компанией')]")).click();
        }catch (Exception e){}
    }

    public void registrationUserFromTheEmployeesTab(){
        driver.findElement(By.xpath("//*[@value='Регистрация']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'добавлен в список сотрудников.')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'добавлен в список сотрудников.')]")).isDisplayed());
        driver.findElement(By.cssSelector(".btn_ok")).click();
    }

    public void registrationTheSameUserFromTheEmployeesTab() {
        driver.findElement(By.xpath("//*[@placeholder='Введите e-mail (логин)']")).clear();
        driver.findElement(By.xpath("//*[@placeholder='Введите e-mail (логин)']")).sendKeys("qwer@qa.team");
        driver.findElement(By.xpath("//*[@value='Регистрация']")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Присоединить')]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'добавлен в список сотрудников')]")).isDisplayed());
        driver.findElement(By.xpath("//button[contains(@class, 'btn_ok')]")).click();
    }
    public void registrationNewUserUsingReferralLink(){
        driver.findElement(By.cssSelector(".register-referral-link")).click();
        driver.findElement(By.xpath("//*[@class='referral__input-email']")).sendKeys("unregisteredEmail" + randomString(4) + email );
        driver.findElement(By.xpath("//*[@value='Отправить ссылку']")).click();
    }
    public void checkingSuccessfulRegistrationMessage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".success-form-add")));
        Assert.assertTrue(driver.findElement(By.cssSelector(".success-form-add")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Ссылка для регистрации нового сотрудника отправлена на указанный e-mail.']")).isDisplayed());
    }
    public void checkingThatEventAboutInviteBossIsDisplayed() {
        Assert.assertTrue(driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[1] //*[contains(text(), 'STAFF_INVITE')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[1] //*[contains(text(), '" + emailEmployee + "')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("((//*[@class='adm-list-table-row'])[1] //* [@class = 'adm-list-table-cell'])[5] [text()= 'Y']")).isDisplayed());
    }
    public void deletingEmployee(){
        driver.findElement(By.xpath("//*[@class= 'main-grid-row-action-button']")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Удалить')]")).click();
    }
    public void checkingThatEmployeeCantChoiceCompanyFromWhichHeWasDeleted(){
        if (driver.findElements(By.cssSelector(".auth-company-change__current")).size() == 0){
            System.out.println("У пользователя вообще нет организаций");
        }else {
            Assert.assertNotEquals(nameCompany, driver.findElement(By.cssSelector(".auth-company-change__current")).getText().replaceAll("[*]", ""));
            driver.findElement(By.cssSelector(".auth-company-change__current")).click();
            Assert.assertTrue(driver.findElements(By.xpath("//*[contains(text(), '" + nameCompany + "')]")).size() == 0);
        }
    }
    public void creatingNewEmployeeForBoss(){
        exitFromB2B();
        navigationToRegistrationTab();
        registr.choiceIP();
        registr.enterINNManually();
        registr.fillingFieldsOnTheRegistrationTab(registr.arrayWithExistingLocatorsForIP);
        registr.tryConfirmRegistration();
        registr.entranceToB2BFromRegistrationTab();
        navigationToOrganizationTab();
        nameCompany = CompanyNameToJoinEmployees;
        requestToJoinTheCompany();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        confirmationEmployeeRequest();
        navigationToPersonsTab();
    }
    public void creatingOrganizationForUser(){
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
    public void creatingOrganizationForEmployee(){
        navigationToOrganizationTab();
        navigationToAddOrganizationTab();
        selectionFromDropDownListIndividualBusinessman();
        fillingFieldsForCreatingOrganization();
        creatingOrganization();
        tryConfirmRegistrationOfOrganizationInB2bFromTheUser();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeEmployee();
        logInToB2B();
        checkingThatOrganizationIsConfirmed();
    }
    public void checkingListOfEmployees(){
        Assert.assertTrue(driver.findElements(By.xpath("//*[text()='Сотрудник компании']")).size() >= 10);
        Assert.assertTrue(driver.findElements(By.cssSelector(".staff-personal-photo")).size() >= 10);
    }
    public void checkingQuantityOrganizationOfEmployee(){
        Assert.assertTrue(driver.findElements(By.xpath("((//*[@class='main-grid-row main-grid-row-body'])[1] //*[@class='main-grid-cell-content'])[3] //span"))
                .size() > 4);
    }
    public void checkingEmployeesOthersOrganization(){
        if (driver.findElements(By.xpath("//*[@class='checked']/*[@name='show-all-users']")).size() != 0){
            driver.findElement(By.xpath("//*[@name='show-all-users']")).click();
            explicitWaiting();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".main-grid-panel-total")));
        Assert.assertTrue(driver.findElements(By.xpath("//*[@class='main-grid-row main-grid-row-body']")).size() == 0);
        driver.findElement(checkboxShowEmployeesOfAllOrganizations).click();
        Assert.assertTrue(driver.findElements(By.xpath("//*[@class='main-grid-row main-grid-row-body']")).size() > 10);
    }
    public void checkingEmployeesOthersOrganizationDisable(){
        tempIntValue = driver.findElements(By.xpath("//*[@class='main-grid-row main-grid-row-body']")).size();
        driver.findElement(checkboxShowEmployeesOfAllOrganizations).click();
        explicitWaiting();
        Assert.assertTrue(driver.findElements(By.xpath("//*[@class='main-grid-row main-grid-row-body']")).size() > tempIntValue);
    }
    public void checkingThatThereAreNoButtonForAddEmployee(){
        if (driver.findElements(buttonAddNewEmployee).size() == 0){
        }else {
            System.out.println(5 / 0);
        }
    }
    public void checkingThatThereAreButtonForAddEmployee(){
        Assert.assertTrue(driver.findElement(buttonAddNewEmployee).isDisplayed());
    }

    public void registerBosWithOrganization(){
        navigationToRegistrationTab();
        registr.choiceIP();
        registr.enterINNManually();
        registr.fillingFieldsOnTheRegistrationTab(registr.arrayWithExistingLocatorsForIP);
        tempValue = driver.findElement(By.xpath("//*[@type='email'][not(ancestor-or-self::*[@style = 'display: none;'])]")).getAttribute("value");
        tempValueForPassword = driver.findElement(By.xpath("//*[@placeholder='Создать пароль'][not(ancestor-or-self::*[@style = 'display: none;'])]")).getAttribute("value");
        registr.tryConfirmRegistration();
        registr.entranceToB2BFromRegistrationTab();
        navigationToOrganizationTab();
        CompanyNameToJoinEmployees = driver.findElement(firstNameOfOrganizationOnTheOrganizationTabOrEmployeeTab).getText();
        registr.tryConfirmRegistrationOfOrganizationInB2bFromTheUser();
    }

    public void registerEmployeeForBosSOrganization(){
        navigationToRegistrationTab();
        registr.choiceIP();
        registr.enterINNManually();
        registr.fillingFieldsOnTheRegistrationTab(registr.arrayWithExistingLocatorsForIP);
        tempValue2 = driver.findElement(By.xpath("//*[@type='email'][not(ancestor-or-self::*[@style = 'display: none;'])]")).getAttribute("value");
        tempValue3 = driver.findElement(By.xpath("//*[@placeholder='Введите имя'][not(ancestor-or-self::*[@style = 'display: none;'])]")).getAttribute("value");
        registr.tryConfirmRegistration();
        registr.entranceToB2BFromRegistrationTab();
    }

    public void checkingThatListOfEmployeeIsEmpty(){
        Assert.assertTrue(driver.findElements(By.xpath("//*[@class='main-grid-row main-grid-row-body']")).size() == 0);
    }
    public void checkingThatListOfEmployeeIsNotEmpty(){
        Assert.assertTrue(driver.findElements(By.xpath("//*[@class='main-grid-row main-grid-row-body']")).size() != 0);
    }
    public void checkingThatUserSNameIsDisplayed(){
        driver.findElement(By.xpath("//*[contains(text(), 'Заявки')]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), '" + tempValue3 + "')]")).isDisplayed());
    }
    public void checkingThatPopUpWindowIsClosedAfterClickCancel(){
        driver.findElement(buttonAddNewEmployee).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".popup-content")).isDisplayed());
        driver.findElement(By.cssSelector(".popup-close")).click();
        Assert.assertFalse(driver.findElement(By.cssSelector(".popup-content")).isDisplayed());
        driver.findElement(buttonAddNewEmployee).click();
        driver.findElement(By.xpath("//*[@value='Отмена']")).click();
        Assert.assertFalse(driver.findElement(By.cssSelector(".popup-content")).isDisplayed());
    }
    public void checkingThatEnteredDataIsDisplayedAfterReopeningPopUpWindow(){
        driver.findElement(buttonAddNewEmployee).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".popup-content")).isDisplayed());
        driver.findElement(By.xpath("//*[@placeholder='Введите e-mail (логин)']")).sendKeys("TEST@qa.team");
        driver.findElement(By.cssSelector(".popup-close")).click();
        Assert.assertFalse(driver.findElement(By.cssSelector(".popup-content")).isDisplayed());
        driver.findElement(buttonAddNewEmployee).click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@placeholder='Введите e-mail (логин)']")).getAttribute("value"), "TEST@qa.team");
    }
    public void checkingMessageAboutExistOrganization (){
        driver.findElement(saveButtonOnTheOrganizationTabLocator).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".validation-invalid-label.errortext")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Такая организация уже существует']")).isDisplayed());
    }
    public void checkingThatPopUpWindowJoinToOrganizationIsClosedAfterClickCancel(){
        driver.findElement(joinToOrganizationButtonLocator).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".popup-window")).isDisplayed());
        driver.findElement(By.xpath("//input[@value='Отмена']")).click();
        Assert.assertFalse(driver.findElement(By.cssSelector(".popup-window")).isDisplayed());
        driver.findElement(joinToOrganizationButtonLocator).click();
        driver.findElement(By.cssSelector(".popup-close")).click();
        Assert.assertFalse(driver.findElement(By.cssSelector(".popup-window")).isDisplayed());
    }
    public void checkingThatDataWasDeletedAfterClickCancel(){
        driver.findElement(joinToOrganizationButtonLocator).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".popup-window")).isDisplayed());
        driver.findElement(By.xpath("//*[contains(@class,'join__search-company')]")).sendKeys("Name");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='select__company-item']")));
        Assert.assertTrue("Список компаний не отобразился",driver.findElements(By.xpath("//*[@class='select__company-item']")).size() > 0);
        driver.findElement(By.xpath("//*[contains(@class,'join__search-company')]")).sendKeys("\u0008");
        driver.findElement(By.xpath("//*[contains(@class,'join__search-company')]")).sendKeys("\u0008");
        driver.findElement(By.xpath("//*[contains(@class,'join__search-company')]")).sendKeys("\u0008");
        driver.findElement(By.xpath("//*[contains(@class,'join__search-company')]")).sendKeys("\u0008");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class='select__company-item']")));
        Assert.assertTrue("Компании отображаются, хотя название очищено",driver.findElements(By.xpath("//*[@class='select__company-item']")).size() == 0);
        driver.findElement(By.xpath("//*[contains(@class,'join__search-company')]")).sendKeys("Name");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='select__company-item']")));
        driver.findElement(By.xpath("//input[@value='Отмена']")).click();
        driver.findElement(joinToOrganizationButtonLocator).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".popup-window")).isDisplayed());
        System.out.println(driver.findElement(By.xpath("//*[contains(@class,'join__search-company')]")).getText());
        Assert.assertTrue("Название компании для поиска не удалилось после закрытия попап окна с помощью кнопки 'Отмена'"
                , driver.findElement(By.xpath("//*[contains(@class,'join__search-company')]")).getText().isEmpty());
        Assert.assertTrue("Компании отображаются, хотя название очищено",driver.findElements(By.xpath("//*[@class='select__company-item']")).size() == 0);
    }
    public void checkingThatEnteredDataIsDisplayedAfterReopeningPopUpWindowJoinToOrganization(){
        driver.findElement(joinToOrganizationButtonLocator).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".popup-window")).isDisplayed());
        driver.findElement(By.xpath("//input[contains(@placeholder,'Введите название')]")).sendKeys("Name");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='select__company-item']")));
        Assert.assertTrue("Список компаний не отобразился",driver.findElements(By.xpath("//*[@class='select__company-item']")).size() > 0);
        driver.findElement(By.cssSelector(".popup-close")).click();
        driver.findElement(joinToOrganizationButtonLocator).click();
        Assert.assertTrue(driver.findElement(By.xpath("//input[contains(@placeholder,'Введите название')]")).getAttribute("value").equals("Name"));
        Assert.assertTrue("Список компаний не отобразился",driver.findElements(By.xpath("//*[@class='select__company-item']")).size() > 0);
    }
    public void addingTwoOrder(){
        MakingOrders makingOrders = new MakingOrders();
        makingOrders.madeOrder();
        tempValue = replacingSomeSymbols(driver.findElement(By.xpath("//*[contains(text(), 'Ваш заказ')] /*")).getText());
        exitFromB2B();
        makingOrders.priceForNewlyAddedProducts = 0;
        makingOrders.numberOfProductsInTheFooter = 0;
        makingOrders.pricesForAllProductsInTheFooter = 0;
        makingOrders.sumOfPricesOfTheAddedProducts = 0;
        makingOrders.madeOrder();
        tempValue2 = replacingSomeSymbols(driver.findElement(By.xpath("//*[contains(text(), 'Ваш заказ')] /*")).getText());
    }
    public void checkingThatOrdersIsDisplayedInOrganization(){
        explicitWaiting();
        driver.findElement(firstHamburgerMenuOnTheOrganizationTabLocator).click();
        driver.findElement(By.xpath("//*[text()='Просмотр']")).click();
        driver.findElement(ordersTabInTheOrganization).click();
        Assert.assertTrue(tempValue2.equals(driver.findElement(By.xpath("((//*[@class='main-grid-row main-grid-row-body'])[1]//*[@class='main-grid-cell main-grid-cell-left'])[1]")).getText()));
        Assert.assertTrue(tempValue.equals(driver.findElement(By.xpath("((//*[@class='main-grid-row main-grid-row-body'])[2]//*[@class='main-grid-cell main-grid-cell-left'])[1]")).getText()));
    }
    public void goToDetailPageAboutOrder(){
        driver.findElement(firstHamburgerMenuOnTheOrganizationTabLocator).click();
        driver.findElement(By.xpath("//*[text()='Подробнее о заказе']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".blank_detail-total")).isDisplayed());
    }
    public void selectRandomOrganizationFromHeader(){
        tempValue = driver.findElement(By.xpath("(//*[@class='auth-company-change__item'])[" + randomNumberUpToFife + "]")).getText();
        driver.findElement(By.xpath("(//*[@class='auth-company-change__item'])[" + randomNumberUpToFife + "]")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".auth-company-change__current"), tempValue));
        System.out.println(driver.findElement(By.cssSelector(".auth-company-change__current")).getText());
        Assert.assertTrue(driver.findElement(By.cssSelector(".auth-company-change__current")).getText().equals(tempValue));
    }
    public void creatingOrganizationWithoutConfirm(){
        tempIntValue = driver.findElements(By.cssSelector(".auth-company-change__item")).size();
        navigationToOrganizationTab();
        navigationToAddOrganizationTab();
        selectionFromDropDownListIndividualBusinessman();
        fillingFieldsForCreatingOrganization();
        creatingOrganization();
        exitFromB2B();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeEmployee();
        logInToB2B();
    }
    public void checkingThatOrganizationWithoutConfirmIsUnavailableInHeader(){
        determineWhetherRegistrationOrganizationNeedsToBeConfirmed();
        if (doNeedToConfirmRegistrationOrganization == true){
            driver.findElement(By.cssSelector(".auth-company-change__current")).click();
            Assert.assertTrue(driver.findElements(By.cssSelector(".auth-company-change__item")).size() == tempIntValue);
            navigationToOrganizationTab();
            sortingOrganizationByDecrease();
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("(//*[@class='main-grid-row main-grid-row-body'])[1] /*[7]"), "На модерации"));
            Assert.assertEquals("На модерации", driver.findElement(By.xpath("(//*[@class='main-grid-row main-grid-row-body'])[1] /*[7]")).getText());
        }
    }
    public void addingOrganizationIfThereIsNoOrganizationsInStandardVersion() {
        if (driver.findElements(By.xpath("//*[@title='Двойной щелчок - Изменить']")).size() == 0) {
            navigationToAddOrganizationTab();
            selectionFromDropDownListIndividualBusinessman();
            fillingFieldsForCreatingOrganization();
            creatingOrganization();
            navigationToOrganizationTab();
        }
    }
    public void navigationToProfilesOfBuyers(){
        driver.findElement(buttonToGoToAdminPartLocator).click();
        driver.findElement(sotbitTabLocator).click();
        try {
            driver.findElement(By.xpath("//*[@class = 'adm-sub-submenu-block adm-sub-submenu-open']//*[@class='adm-submenu-item-name-link-text'][contains(text(),'[s1] Сотбит: B2B кабинет')]")).click();
        }catch (Exception e){
            driver.findElement(By.xpath("(//*[contains(@class,'adm-submenu-item-name-link-text')][contains(text(), 'Личный кабинет B2B')])[1]")).click();
            driver.findElement(By.xpath("//*[@class = 'adm-sub-submenu-block adm-sub-submenu-open']//*[@class='adm-submenu-item-name-link-text'][contains(text(),'s1')]")).click();
        }
    }
    public void checkingThatNeededProfilesOfBuyersIsSelected(){
        tempIntValue = driver.findElements(By.xpath("//*[contains(@id, 'BUYER_PERSONAL_TYPE')] /option")).size();
        for (int i = 1; i <= tempIntValue ; i++) {
            if (driver.findElement(By.xpath("(//*[contains(@id, 'BUYER_PERSONAL_TYPE')] /option)[" + i + "]")).isSelected()){
                tempValue = replacingBracketsAndNumbers(driver.findElement(By.xpath("(//*[contains(@id, 'BUYER_PERSONAL_TYPE')] /option)[" + i + "]")).getText()).trim();
                System.out.println("Этот тип плательщика выбран в админке  " + tempValue);
                exitFromB2B();
                navigationToMeanPageByUrl();
                navigationToRegistrationTab();
                tempIntValue2 = driver.findElements(By.xpath("//*[@class='chouse-company'] /* /following::*[1]")).size();
                flag = false;
                for (int j = 1; j < tempIntValue2; j++) {
                    if (driver.findElement(By.xpath("(//*[@class='chouse-company'] /* /following::*[1])[" + j + "]")).getText().equals(tempValue)){
                        flag = true;
                        break;
                    }
                }
                Assert.assertTrue("Этого  " + tempValue + " типа плательщика нет на вкладке Регистраниии (хотя в админке он выбран)",flag == true);
                navigationToMeanPageByUrl();
                fillingFieldsOnTheLogInTabLikeAdmin();
                logInToB2B();
                navigationToProfilesOfBuyers();
            }
        }
    }
    public void checkingThatCapacityFieldIsFortySymbols(){
        driver.findElement(By.xpath("//*[contains(text(),'Название компании')]/following::input[1]")).sendKeys(stringOfNinetyNineSymbols);
        if (driver.findElement(By.xpath("//*[contains(text(),'Название компании')]/following::input[1]")).getAttribute("value").equals(stringOfNinetyNineSymbols)) {
        }else {
            System.out.println("В поле 'Название компании'  УЖЕ можно ввести 99 символов");
        }
    }
    public void navigationToTableOfPropertiesSetting(){
        driver.navigate().to(b2bUrl.replaceAll("/b2bcabinet/", "") + "/bitrix/admin/sale_order_props.php?lang=ru#authorize");
        driver.findElement(By.xpath("//*[@class='adm-select']/*[@value='500']")).click();
        explicitWaiting();
    }
    public void changeAllPropertyOfNameCompanyToOneHundredSymbols(){
        for (int i = 1; i <= driver.findElements(By.xpath("(//*[contains(text(), 'Название компании')])/preceding::*[5]")).size(); i++) {
            driver.findElement(By.xpath("((//*[contains(text(), 'Название компании')])/preceding::*[5])[" + i + "]")).click();
            driver.findElement(By.xpath("//*[contains(text(), 'Изменить параметры свойства')]")).click();
            driver.findElement(By.xpath("//*[contains(text(), 'Количество видимых символов')] / following::*[1] /input")).clear();
            driver.findElement(By.xpath("//*[contains(text(), 'Количество видимых символов')] / following::*[1] /input")).sendKeys("100");
            driver.findElement(By.xpath("//*[contains(text(), 'Минимальная длина строки')] / following::*[1] /input")).clear();
            driver.findElement(By.xpath("//*[contains(text(), 'Минимальная длина строки')] / following::*[1] /input")).sendKeys("3");
            driver.findElement(By.xpath("//*[contains(text(), 'Максимальная длина строки')] / following::*[1] /input")).clear();
            driver.findElement(By.xpath("//*[contains(text(), 'Максимальная длина строки')] / following::*[1] /input")).sendKeys("100");
            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Количество видимых символов')] / following::*[1] /input")).getAttribute("value").equals("100"));
            driver.findElement(By.xpath("//*[@title='Сохранить и вернуться']")).click();
        }
    }

    public void checkingThatCapacityFieldIsOneHundredSymbols (){
        driver.findElement(By.xpath("//*[contains(text(),'Название компании')]/following::input[1]")).sendKeys(stringOfNinetyNineSymbols);
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Название компании')]/following::input[1]")).getAttribute("value").equals(stringOfNinetyNineSymbols));
    }
    public void checkingMinimumAndMaximumStringLength(){
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Название компании')]/following::input[1][@minlength = '3'][@maxlength='100']")).isDisplayed());
    }
    public void rememberingActivePropertiesForLegalPerson(){
        count = 0;
        for (int i = 1; i <= driver.findElements(By.xpath("//td[contains(text(), 'ридическое лицо')]")).size(); i++) {
            if (driver.findElement(By.xpath("(//td[contains(text(), 'ридическое лицо')])[" + i + "] /following::*[3]")).getText().equals("Да")
                    && driver.findElement(By.xpath("(//td[contains(text(), 'ридическое лицо')])[" + i + "] /following::*[9]")).getText().equals("Да")){
                count++;
            }
        }
    }
    public void rememberingActivePropertiesForIndividualBusinessman(){
        countIP =0;
        for (int i = 1; i <= driver.findElements(By.xpath("//td[contains(text(), 'ндивидуальный предприниматель')]")).size(); i++) {
            if (driver.findElement(By.xpath("(//td[contains(text(), 'ндивидуальный предприниматель')])[" + i + "] /following::*[3]")).getText().equals("Да")
                    && driver.findElement(By.xpath("(//td[contains(text(), 'ндивидуальный предприниматель')])[" + i + "] /following::*[9]")).getText().equals("Да")){
                countIP++;
            }
        }
    }
    public void checkingThatAllFieldsForLegalPersonIsOutPut(){
        selectionFromDropDownListLegalPerson();
        Assert.assertTrue(driver.findElements(By.cssSelector(".form-control")).size()
                -driver.findElements(By.xpath("//*[contains(@placeholder, 'естоположение')]")).size()
                == count);
    }
    public void checkingThatAllFieldsForIndividualBusinessman(){
        selectionFromDropDownListIndividualBusinessman();
        Assert.assertTrue(driver.findElements(By.cssSelector(".form-control")).size()
                - driver.findElements(By.xpath("//*[contains(@placeholder, 'естоположение')]")).size()
                == countIP);
    }
    public void enteringINNUsingListOfCompanies(){
        driver.findElement(By.xpath("//*[contains(text(), 'ИНН')][not(ancestor-or-self::*[@style = 'display: none;'])] /following::*[1]")).clear();
        driver.findElement(By.xpath("//*[contains(text(), 'ИНН')][not(ancestor-or-self::*[@style = 'display: none;'])] /following::*[1]")).sendKeys(randomNumber(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='checkcompany-list']")));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='checkcompany-list']")).isDisplayed());
        driver.findElement(By.xpath("(//*[@id='checkcompany-list'] /*)[1]")).click();
    }
    public void checkingThatINNIsChosen(){
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'ИНН')][not(ancestor-or-self::*[@style = 'display: none;'])] /following::*[1]")).getAttribute("value").length() > 5);
        nameCompany = driver.findElement(By.xpath("//*[contains(text(), 'Название компании')][not(ancestor-or-self::*[@style = 'display: none;'])] /following::*[1]")).getAttribute("value");
    }
    public void changeDataInOrganization(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='main-grid-row-action-button'])[1]")));
        explicitWaiting();
        driver.findElement(By.xpath("(//*[@class='main-grid-row-action-button'])[1]")).click();
        driver.findElement(By.xpath("(//*[@class='menu-popup-item-text'])[1]")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".row.companies-wrapper")).isDisplayed());
        driver.findElement(By.xpath("//*[contains(text(),'Название компании')]/following::*[1]")).clear();
        nameCompany = "New имя компании" + randomString(12);
        driver.findElement(By.xpath("//*[contains(text(),'Название компании')]/following::*[1]")).sendKeys(nameCompany);
        driver.findElement(By.xpath("//*[contains(text(),'ИНН')]/following::*[1]")).clear();
        iNN = randomNumber(12);
        registr.enterNumberByDigit(iNN, By.xpath("//*[contains(text(),'ИНН')]/following::*[1]"));
        driver.findElement(By.xpath("//*[contains(@value,'mail.ru')]")).clear();
        email = "test_EMail" + randomString(12) + "@mail.ru";
        driver.findElement(By.xpath("//*[contains(@value,'mail.ru')]")).sendKeys(email);
        try {
            driver.findElement(By.xpath("//*[contains(@value,'7495')]")).clear();
            mobilePhone = "+7495" + randomNumber(7);
            driver.findElement(By.xpath("//*[contains(@value,'7495')]")).sendKeys(mobilePhone);
            tempValue = "yes";
        }catch (Exception e){
            System.out.println("Я не нашел поле 'Телефон'");
        }
        driver.findElement(saveButtonOnTheOrganizationTabLocator).click();
    }
    public void increaseQuantityOfFirstProduct(){
        try {
            driver.findElement(By.xpath("(//*[@class='quantity-selector__increment'])[1]")).click();
        }catch (Exception e){
            driver.findElement(By.xpath("(//*[@class='quantity-selector__increment'])[1]")).click();
        }
    }
    public void checkingThatDataHasBeenChanged(){

            for (int i = 1; i <= driver.findElements(By.xpath("//*[contains(@class, 'index_checkout-payer_type')] //*[@class='form-check-label']")).size(); i++) {
                if (driver.findElement(By.xpath("(//*[contains(@class, 'index_checkout-payer_type')] //*[@class='form-check-label'])[" + i + "]")).getText()
                        .equals(nameCompany)){
                    driver.findElement(By.xpath("(//*[contains(@class, 'index_checkout-payer_type')] //*[@class='form-check-label'])[" + i + "]")).click();
                    count++;
                }
            } count = (1/count);
        //!!!!!!!!!ДОПИСАТЬ когда пофикят ошибку с неправельными именами радиобаттона после извенения названия компании
    }
    public void checkingThatDataHasBeenChanged2(){
        try {
            for (int i = 1; i <= driver.findElements(By.xpath("//*[contains(@class, 'index_checkout-payer_type')] //*[@class='form-check-label']")).size(); i++) {
                if (driver.findElement(By.xpath("(//*[contains(@class, 'index_checkout-payer_type')] //*[@class='form-check-label'])[" + i + "]")).getText()
                        .equals(nameCompany)){
                    driver.findElement(By.xpath("(//*[contains(@class, 'index_checkout-payer_type')] //*[@class='form-check-label'])[" + i + "]")).click();
                    count++;
                }
            } count = (1/count);

        }catch (Exception e){
            System.out.println("Имя радиобаттона не соответствует названию компании");
            count=0;
            for (int i = 1; i <= driver.findElements(By.xpath("//*[contains(@class, 'index_checkout-payer_type')] //*[@class='form-check-label']")).size(); i++) {
                driver.findElement(By.xpath("(//*[contains(@class, 'index_checkout-payer_type')] //*[@class='form-check-label'])[" + i + "]")).click();
                explicitWaiting();
                if (driver.findElement(By.xpath("//*[contains(text(), 'ИНН')] /following::*[1] /input")).getAttribute("value").equals(iNN)){
                    Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'ИНН')] /following::*[1] /input")).getAttribute("value").equals(iNN));
                    Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Название компании')] /following::*[1] /input")).getAttribute("value").equals(nameCompany));
                    Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@value,'mail.ru')]")).getAttribute("value").equals(email));
                    if (tempValue.equals("yes")){
                        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@value,'7495')]")).getAttribute("value").replaceAll("[+]", "")
                                .equals(mobilePhone.replaceAll("[+]", "")));
                    }
                    count++;
                    break;
                }
            }count = (1/count);
        }
    }
    public void saveTheListOfSettingDisplayedOnTheOrganizationsTab(){
        driver.findElement(By.cssSelector("#PERSONAL_PROFILE_LIST-grid-settings-apply-button")).click();
        driver.findElement(By.xpath("//*[@class='popup-window-button']")).click();
    }
    public void removeTheDisplayOfAllColumnsExceptCode (){
        driver.findElement(By.xpath("//*[@class='main-grid-interface-settings-icon']")).click();

        for (int i = 1; i <= driver.findElements(By.xpath("//*[contains(@id, 'checkbox')]")).size(); i++) {
            if (driver.findElement(By.xpath("(//*[contains(@id, 'checkbox')])[" + i + "]")).isSelected()) {
                if (driver.findElement(By.xpath("(//*[contains(@id, 'checkbox')])[" + i + "]")).getAttribute("id").equals("ID-checkbox")){

                }else driver.findElement(By.xpath("(//*[contains(@id, 'checkbox')])[" + i + "]")).click();
            }
        }

        if (driver.findElement(By.xpath("//*[@name='grid-settings-window-for-all']")).isSelected()){
            System.out.println("У 'Для всех' галочка уже стоит");
        }else driver.findElement(By.xpath("//*[@name='grid-settings-window-for-all']")).click();
        saveTheListOfSettingDisplayedOnTheOrganizationsTab();

    }
    public void checkingThatOnlyColumnWithCodeIsDisplayed(){
        Assert.assertTrue(driver.findElements(By.cssSelector(".main-grid-head-title")).size()/2 == 1);
        Assert.assertTrue(driver.findElement(By.cssSelector(".main-grid-head-title")).getText().equals("Код"));
    }
    public void selectingAllColumnsToDisplay(){
        driver.findElement(By.xpath("//*[@class='main-grid-interface-settings-icon']")).click();
        for (int i = 1; i <= driver.findElements(By.xpath("//*[contains(@id, 'checkbox')]")).size(); i++) {
            if (driver.findElement(By.xpath("(//*[contains(@id, 'checkbox')])[" + i + "]")).isSelected()){
            }else try {
                driver.findElement(By.xpath("(//*[contains(@id, 'checkbox')])[" + i + "]")).click();
            }catch (Exception e){}
        }
        saveTheListOfSettingDisplayedOnTheOrganizationsTab();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[@data-name='NAME']")));
        Assert.assertTrue(driver.findElements(By.cssSelector(".main-grid-head-title")).size() > 3);
    }
    public void selectingByDefaultSetting(){
        driver.findElement(By.xpath("//*[@class='main-grid-interface-settings-icon']")).click();
        if (driver.findElement(By.xpath("//*[@name='grid-settings-window-for-all']")).isSelected()){
            System.out.println("У 'Для всех' галочка уже стоит");
        }else driver.findElement(By.xpath("//*[@name='grid-settings-window-for-all']")).click();
        driver.findElement(By.cssSelector("#PERSONAL_PROFILE_LIST-grid-settings-reset-button")).click();
        driver.findElement(By.xpath("//*[@class='popup-window-button']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[@data-name='NAME']")));
        Assert.assertTrue(driver.findElements(By.cssSelector(".main-grid-head-title")).size() > 3);
    }
    public void changingSomeFieldsForUniqueness(){
        try {
            driver.findElement(By.xpath("//*[contains(text(), 'ИНН:')]/following::*[1]")).clear();
            iNNManual = randomNumber(14);
            registr.enterNumberByDigit(iNNManual, By.xpath("//*[contains(text(), 'ИНН:')]/following::*[1]"));
        }catch (Exception e){}
        try {
            driver.findElement(By.xpath("//*[contains(text(), 'Название компании')][not(ancestor-or-self::*[@style = 'display: none;'])] /following::*[1]")).clear();
            nameCompany = "NameCompany ИмяКомпании" + randomString(10);
            driver.findElement(By.xpath("//*[contains(text(), 'Название компании')][not(ancestor-or-self::*[@style = 'display: none;'])] /following::*[1]")).sendKeys(nameCompany);
        }catch (Exception e){}
        try {
            driver.findElement(By.xpath("//*[contains(text(), 'Название')][not(ancestor-or-self::*[@style = 'display: none;'])] /following::*[2]")).clear();
            driver.findElement(By.xpath("//*[contains(text(), 'Название')][not(ancestor-or-self::*[@style = 'display: none;'])] /following::*[2]")).sendKeys(nameCompany);
        }catch (Exception e){}
        try {
            driver.findElement(By.xpath("//*[contains(text(), 'доставк')][not(ancestor-or-self::*[@style = 'display: none;'])] /following::*[2]")).clear();
            driver.findElement(By.xpath("//*[contains(text(), 'доставк')][not(ancestor-or-self::*[@style = 'display: none;'])] /following::*[2]")).sendKeys(randomString(30));
        }catch (Exception e){}
        try {
            driver.findElement(By.xpath("//*[contains(text(), 'Индекс')][not(ancestor-or-self::*[@style = 'display: none;'])] /following::*[2]")).clear();
            driver.findElement(By.xpath("//*[contains(text(), 'Индекс')][not(ancestor-or-self::*[@style = 'display: none;'])] /following::*[2]")).sendKeys(randomNumber(8));
        }catch (Exception e){}
        try {
            driver.findElement(By.xpath("//*[contains(text(), 'Телефон')][not(ancestor-or-self::*[@style = 'display: none;'])] /following::*[2]")).clear();
            mobilePhone = "+7495" + randomNumber(+7);
            driver.findElement(By.xpath("//*[contains(text(), 'Телефон')][not(ancestor-or-self::*[@style = 'display: none;'])] /following::*[2]")).sendKeys(mobilePhone);
        }catch (Exception e){}
    }
    public void creatingThreeOrganizationForExtendedVersion(){
        MethodsForAddingOrganizationsWithExtendedVersion org = new MethodsForAddingOrganizationsWithExtendedVersion();
        org.navigationToAddOrganizationTab();
        org.selectionFromDropDownListLegalPerson();
        org.fillingFieldsForCreatingOrganization();
        changingSomeFieldsForUniqueness();
        tempValue1 = driver.findElement(By.xpath("//*[@name='ADD_COMPANY_NAME']")).getAttribute("value");
        creatingOrganization();
        tryConfirmRegistrationOfOrganizationInB2bFromTheUser();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        checkingThatOrganizationIsConfirmed(); //+1 организация (ЮР)

        org.navigationToOrganizationTab();
        org.navigationToAddOrganizationTab();
        org.selectionFromDropDownListIndividualBusinessman();
        org.fillingFieldsForCreatingOrganization();
        changingSomeFieldsForUniqueness();
        tempValue = driver.findElement(By.xpath("//*[@name='ADD_COMPANY_NAME']")).getAttribute("value");
        creatingOrganization();
        tryConfirmRegistrationOfOrganizationInB2bFromTheUser();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        checkingThatOrganizationIsConfirmed(); //+1 организация (ИП)

        navigationToOrganizationTab();
        org.navigationToAddOrganizationTab();
        org.selectionFromDropDownListLegalPerson();
        org.fillingFieldsForCreatingOrganization();
        changingSomeFieldsForUniqueness();
        tempValue2 = driver.findElement(By.xpath("//*[@name='ADD_COMPANY_NAME']")).getAttribute("value");
        creatingOrganization();
        tryConfirmRegistrationOfOrganizationInB2bFromTheUser();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        checkingThatOrganizationIsConfirmed(); //+1 организация (ЮР)
    }

    public void creatingThreeOrganizationForStandardVersion(){
        MethodsForAddingOrganizationsWithExtendedVersion org = new MethodsForAddingOrganizationsWithExtendedVersion();
        org.navigationToAddOrganizationTab();
        org.selectionFromDropDownListLegalPerson();
        org.fillingFieldsForCreatingOrganization();
        changingSomeFieldsForUniqueness();

        tempValue1 = driver.findElement(By.xpath("//*[contains(text(), 'Название компании')][not(ancestor-or-self::*[@style = 'display: none;'])] /following::*[1]"))
                .getAttribute("value");
        System.out.println("111111" + nameCompany);

        creatingOrganization(); //+1 организация (ЮР)
        navigationToOrganizationTab();
        org.navigationToAddOrganizationTab();
        org.selectionFromDropDownListIndividualBusinessman();
        org.fillingFieldsForCreatingOrganization();
        changingSomeFieldsForUniqueness();
        tempValue = driver.findElement(By.xpath("//*[contains(text(), 'Название компании')][not(ancestor-or-self::*[@style = 'display: none;'])] /following::*[1]"))
                .getAttribute("value");
        creatingOrganization(); //+1 организация (ИП)

        navigationToOrganizationTab();
        org.navigationToAddOrganizationTab();
        org.selectionFromDropDownListLegalPerson();
        org.fillingFieldsForCreatingOrganization();
        changingSomeFieldsForUniqueness();
        tempValue2 = driver.findElement(By.xpath("//*[contains(text(), 'Название компании')][not(ancestor-or-self::*[@style = 'display: none;'])] /following::*[1]"))
                .getAttribute("value");
        creatingOrganization(); //+1 организация (ЮР)
    }
    public void removingIPFromAvailablePayerTypes(){
        tempIntValue = driver.findElements(By.xpath("//*[contains(@id, 'BUYER_PERSONAL_TYPE')] /option")).size();
        for (int i = 1; i <= tempIntValue ; i++) {
            if ((driver.findElement(By.xpath("(//*[contains(@id, 'BUYER_PERSONAL_TYPE')] /option)[" + i + "]")).isSelected())
                    && driver.findElement(By.xpath("(//*[contains(@id, 'BUYER_PERSONAL_TYPE')] /option)[" + i + "]")).getText().contains("ндивидуальный предприниматель")) {
                driver.findElement(By.xpath("(//*[contains(@id, 'BUYER_PERSONAL_TYPE')] /option)[" + i + "]")).click();
            }
        }
        driver.findElement(By.xpath("//*[@name='save']")).click();
    }
    public void addingBackIPToAvailablePayerTypes(){
        tempIntValue = driver.findElements(By.xpath("//*[contains(@id, 'BUYER_PERSONAL_TYPE')] /option")).size();
        for (int i = 1; i <= tempIntValue ; i++) {
            if (!(driver.findElement(By.xpath("(//*[contains(@id, 'BUYER_PERSONAL_TYPE')] /option)[" + i + "]")).isSelected())
                    && driver.findElement(By.xpath("(//*[contains(@id, 'BUYER_PERSONAL_TYPE')] /option)[" + i + "]")).getText().contains("ндивидуальный предприниматель")) {
                driver.findElement(By.xpath("(//*[contains(@id, 'BUYER_PERSONAL_TYPE')] /option)[" + i + "]")).click();
            }
        }
        driver.findElement(By.xpath("//*[@name='save']")).click();
    }
    public void checkingThatTheAddedLPOrganizationsAreAvailableForSelectionAndTheAddedIPOrganizationIsNotAvailable (){
        //проверка что добавленные организации LP доступны для выбора, а добавленная организация IP не доступны
        determineWhetherVersionsOfWorkingWithOrganization();
        MethodsForAddingOrganizationsWithExtendedVersion org = new MethodsForAddingOrganizationsWithExtendedVersion();
        if (versionsOfWorkingWithOrganizationsExtended == true){
            flag = false;
            org.selectionFromTheHeaderOrganization(tempValue1);
            org.selectionFromTheHeaderOrganization(tempValue2);
            flag = true;
            try {
                org.selectionFromTheHeaderOrganization(tempValue);
                flag = false;
                Assert.assertTrue("Организация ИП отображается, хотя в админ части убрал ИП из доступных типов плательщиков", false == true);
            }catch (Exception e){}

        }else {
            flag = false;
            for (int i = 1; i <= driver.findElements(By.xpath("//*[contains(@for, 'PPROFILE_ID')]")).size(); i++) {
                if (driver.findElement(By.xpath("(//*[contains(@for, 'PPROFILE_ID')])[" + i + "]")).getText().equals(tempValue1)){
                    driver.findElement(By.xpath("(//*[contains(@for, 'PPROFILE_ID')])[" + i + "]")).click();
                    flag = true;
                }
                if (driver.findElement(By.xpath("(//*[contains(@for, 'PPROFILE_ID')])[" + i + "]")).getText().equals(tempValue2)){
                    driver.findElement(By.xpath("(//*[contains(@for, 'PPROFILE_ID')])[" + i + "]")).click();
                    flag = true;
                }
                if (driver.findElement(By.xpath("(//*[contains(@for, 'PPROFILE_ID')])[" + i + "]")).getText().equals(tempValue)){
                    driver.findElement(By.xpath("(//*[contains(@for, 'PPROFILE_ID')])[" + i + "]")).click();
                    flag = false;
                    Assert.assertTrue("Организация ИП отображается, хотя в админ части убрал ИП из доступных типов плательщиков", flag == true);
                }
            }
        }
        Assert.assertTrue("Организация ИП отображается, хотя в админ части убрал ИП из доступных типов плательщиков", flag == true);
    }


}

