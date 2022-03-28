package Events;

import OrganizationsWithExtendedVersion.MethodsForAddingOrganizationsWithExtendedVersion;
import RegistrationAndAuthorization.MethodsForRegistrationAndAuthorization;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class MethodsForEvents extends MethodsForAddingOrganizationsWithExtendedVersion {

    MethodsForAddingOrganizationsWithExtendedVersion org = new MethodsForAddingOrganizationsWithExtendedVersion();
    MethodsForRegistrationAndAuthorization registr = new MethodsForRegistrationAndAuthorization();
    int countColumnWithEventStatus;
    String expectedSuccessExec = "F";

    public void checkingTheStatusThisEvent(String expectedValue, String nameEvent, String uniqueInformation){
        searchForAColumnWithEventStatuses();
        for (int i = 1; i < 10; i++) {
            if (driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[" + i + "]")).getText().contains(uniqueInformation)
                    && driver.findElements(By.xpath("((//*[@class='adm-list-table-row']))[" + i + "]//*[contains(text(), '" + nameEvent + "')]")).size() > 0){
                count = i;
                System.out.println("№ этогот события  -   " + count);
                break;
            }
        }
        Assert.assertTrue(driver.findElement(By.xpath("((//*[@class='adm-list-table-row'])[" + count + "] //*[contains(@class, 'adm-list-table-cell')])[" + countColumnWithEventStatus + "]"))
                .getText().equals(expectedValue));
    }

    public void checkingEventAboutNeededToConfirmUserRegistration(){
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            if (doNeedToConfirmRegistrationUser){
                System.out.println("Проверяю событие о необходимости подтвержить  рег. юзера с таким email   "  + registr.email);
                checkingThatThereIsThisEvent("SOTBIT_AUTH_CONFIRM_REGISTRATION", registr.email);
            }else {
                System.out.println("Если подтверждать регистрацию пользователя не нужно, то никакие события не создаются");
            }
        }else {
            if (doNeedToConfirmRegistrationUser){
                System.out.println("Проверяю событие о необходимости подтвержить  рег. юзера с таким email   "  + registr.email);
                checkingThatThereIsThisEvent("SOTBIT_AUTH_CONFIRM_REGISTRATION", registr.email);
            }else {
                System.out.println("Проверяю событие что пользователь зарегистирровался (без подтверждения)"  + registr.email);
                checkingThatThereIsThisEvent("SOTBIT_AUTH_WHOLESALER_NOTICE", registr.email);
            }
        }
    }
    public void checkingEventAboutConfirmUserRegistration(){
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            if (doNeedToConfirmRegistrationUser){
                System.out.println("Проверяю событие о том что рег. юзера с таким email   "  + registr.email + "   была подтверждена админом");
                checkingThatThereIsThisEvent("SOTBIT_AUTH_ADMIN_CONFIRM", registr.email);
            }else System.out.println("Подтверждать регистрацию пользователя не нужно");
        }else {
            if (doNeedToConfirmRegistrationUser){
                System.out.println("Проверяю событие о том что рег. юзера с таким email   "  + registr.email + "   была подтверждена админом");
                checkingThatThereIsThisEvent("SOTBIT_AUTH_WHOLESALER_NOTICE", registr.email);
            }else System.out.println("Подтверждать регистрацию пользователя не нужно");
            }
        }

    public void checkingEventAboutTheRegistrationOrganization(){
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            if (doNeedToConfirmRegistrationOrganization){
                System.out.println("Проверяю событие о необходимости подтвердить орг. с таким ИНН   "  + registr.iNNManual);
                checkingThatThereIsThisEvent("SOTBIT_AUTH_COMPANY_MODERATION", registr.iNNManual);
            }else {
                System.out.println("Проверяю событие что зарегистрировалась орг. с таким ИНН   "  + registr.iNNManual);
                checkingThatThereIsThisEvent("SOTBIT_AUTH_COMPANY_REGISTER", registr.iNNManual);
            }
        }else {
            System.out.println("СОБЫТИЯ ПРО ОРГАНИЗАЦИЮ НЕ СОЗДАЮТСЯ в нерасширенной версии!!!!!");
        }
    }
    public void checkingEventAboutTheRegistrationOrganizationHasBeenApproved(){
        System.out.println("Проверяю событие что орг. с таким ИНН   "  + registr.iNNManual + " была подтверждена админом");
        checkingThatThereIsThisEvent("SOTBIT_AUTH_COMPANY_CONFIRM", registr.iNNManual);
    }
    public void checkingEventAboutRejectionUsersRegistration(){
        if (doNeedToConfirmRegistrationUser){
            System.out.println("Проверяю событие что регистрация пользователя с таким емаил   "  + registr.email + " была отклонена админом");
            checkingThatThereIsThisEvent("SOTBIT_AUTH_ADMIN_REJECTED", registr.email);
        }else {
            System.out.println("Если подтверждать регистрацию пользователя не нужно, то никакие события о пользователе не создаются");
        }
    }
    public void checkingEventAboutRejectionCompanyRegistration(){
        if (doNeedToConfirmRegistrationOrganization){
            System.out.println("Проверяю событие что регистрация организации с таким ИНН   "  + registr.iNNManual + " была отклонена админом");
            checkingThatThereIsThisEvent("SOTBIT_AUTH_COMPANY_REJECTED", registr.iNNManual);
        }else {
            System.out.println("Если подтверждать регистрацию пользователя не нужно, то никакие события о пользователе не создаются");
        }
    }
    public void checkingThatThereIsThisEvent(String nameEvent, String uniqueInformation){
        try {
            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), '" + nameEvent + "')] /following::*[contains(text(), '" + uniqueInformation + "')]"))
                    .isDisplayed());
        }catch (Exception e){
            System.out.println("!!!!   словил тупую ошибку, сменило само старницу на первую, меняю обратно !!!!! ");
            driver.findElement(By.xpath("//*[@class='adm-nav-page']")).click();
            waitingMilliSecond();
            System.out.println("УДАЛИТЬ " + nameEvent);
            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), '" + nameEvent + "')] /following::*[contains(text(), '" + uniqueInformation + "')]"))
                    .isDisplayed());
        }
    }

    public void searchForAColumnWithEventStatuses(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@title, 'EVENT_NAME')]")));
        waitingMilliSecond();
        for (int i = 1; i < driver.findElements(By.xpath("//*[@class='adm-list-table-header'] //td[contains(@class, 'adm-list-table-cell')]")).size() ; i++) {
            if (driver.findElement(By.xpath("(//*[@class='adm-list-table-header'] //td[contains(@class, 'adm-list-table-cell')])[" + i + "]"))
                    .getAttribute("title").contains("SUCCESS")){
                countColumnWithEventStatus = i;
                break;
            }
        }
    }
    public void checkingTheStatusEventAboutNeededConfirmUserRegistration(){
        if (doNeedToConfirmRegistrationUser){
            System.out.println("Проверяю статус события о рег. юзера (ожидаю '0') с таким email   "  + registr.email);
            checkingTheStatusThisEvent("0", "SOTBIT_AUTH_CONFIRM_REGISTRATION", registr.email);
        }
    }
    public void checkingTheStatusEventOfTheUserSRegistrationHasBeenConfirmedByTheAdmin(String expectedValue){
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended) {
            if (doNeedToConfirmRegistrationUser){
                System.out.println("Проверяю статус события что рег. юзера (ожидаю '" +expectedValue+ "') с таким email   "  + registr.email + " была подтверждена админом");
                checkingTheStatusThisEvent(expectedValue, "SOTBIT_AUTH_ADMIN_CONFIRM", registr.email);
            }else System.out.println("Подтверждать регистрацию пользователя не нужно");
        }else {
            if (doNeedToConfirmRegistrationUser){
                System.out.println("Проверяю статус события что рег. юзера (ожидаю '" +expectedValue+ "') с таким email   "  + registr.email + " была подтверждена админом");
                checkingTheStatusThisEvent(expectedValue, "SOTBIT_AUTH_WHOLESALER_NOTICE", registr.email);
            }else System.out.println("Подтверждать регистрацию пользователя не нужно");
        }
    }
    public void checkingTheStatusEventThatYouNeedToConfirmOfTheRegistrationOfTheOrganization(String expectedValue){
        if (doNeedToConfirmRegistrationOrganization){
            System.out.println("Проверяю статус события о необходимости подтвердить рег. организации (ожидаю '" +expectedValue+ "') с таким ИНН   "  + registr.iNNManual);
            checkingTheStatusThisEvent(expectedValue, "SOTBIT_AUTH_COMPANY_MODERATION", registr.iNNManual);
        }else {
            System.out.println("Регистрацию организации не нужно подтверждать");
        }
    }
    public void checkingTheStatusEventThatAdminRejectedUserSRegistration(String expectedValue){
        if (doNeedToConfirmRegistrationUser){
            System.out.println("Проверяю статус события об отклонении рег. пользователя (ожидаю '" +expectedValue+ "') с емаил   "  + registr.email);
            checkingTheStatusThisEvent(expectedValue, "SOTBIT_AUTH_ADMIN_REJECTED", registr.email);
        }else {
            System.out.println("Если подтверждать регистрацию пользователя не нужно, то никакие события о пользователе не создаются");
        }
    }
    public void checkingTheStatusEventThatAdminRejectedCompanyRegistration(String expectedValue){
        searchForAColumnWithEventStatuses();
        if (doNeedToConfirmRegistrationOrganization){
            System.out.println("Проверяю статус события об отклонении рег. организации (ожидаю '" +expectedValue+ "') с ИНН   "  + registr.iNNManual);
            checkingTheStatusThisEvent(expectedValue, "SOTBIT_AUTH_COMPANY_REJECTED", registr.iNNManual);
        }else {
            System.out.println("Если подтверждать регистрацию пользователя не нужно, то никакие события о пользователе не создаются");
        }
    }
    public void checkingTheStatusEventThatTheOrganizationSRegistrationHasBeenConfirmedByTheAdministrator(String expectedValue){
        System.out.println("Проверяю статус события (ожидаю '" +expectedValue+ "') что организация  с таким ИНН была подтверждена  "  + registr.iNNManual);
        checkingTheStatusThisEvent(expectedValue, "SOTBIT_AUTH_COMPANY_CONFIRM", registr.iNNManual);

//        for (int i = 1; i < 10; i++) {
//            if (driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[" + i + "]")).getText().contains(registr.iNNManual)
//                    && driver.findElements(By.xpath("((//*[@class='adm-list-table-row']))[" + i + "]//*[contains(text(), 'SOTBIT_AUTH_COMPANY_CONFIRM')]")).size() > 0
//            ){
//                count = i;
//                System.out.println("№ события что рег. организации была подтверждена админом -   " + count);
//                break;
//            }
//        }
//        Assert.assertTrue(driver.findElement(By.xpath("((//*[@class='adm-list-table-row'])[" + count + "] //*[contains(@class, 'adm-list-table-cell')])[" + countColumnWithEventStatus + "]"))
//                .getText().equals(expectedValue));
    }

    public void checkingTheStatusLastEvent(String expectedValue){
        searchForAColumnWithEventStatuses();
        Assert.assertTrue(driver.findElement(By.xpath("((//*[@class='adm-list-table-row'])[1] //*[contains(@class, 'adm-list-table-cell')])[" + countColumnWithEventStatus + "]"))
                .getText().equals(expectedValue));
    }
    public void searchForNeededColumnByUniqueAttribute (String uniqueAttribute ){
        for (int i = 1; i <= driver.findElements(By.xpath("//*[@class='adm-list-table-header'] //*[contains(@class, 'adm-list-table-cell adm-list-table-cell-sort')]")).size(); i++) {
            if (driver.findElement(By.xpath("(//*[@class='adm-list-table-header'] //*[contains(@class, 'adm-list-table-cell adm-list-table-cell-sort')])[" + i + "]"))
                    .getAttribute("onclick").contains(uniqueAttribute)){
                countColumnWithEventStatus = i;
                break;
            }
        }
    }
    public void searchForNeededColumnByUniqueTitle (String uniqueTitle){
        for (int i = 1; i <= driver.findElements(By.xpath("//*[@class='adm-list-table-header'] //*[contains(@class, 'adm-list-table-cell adm-list-table-cell-sort')]")).size(); i++) {
            if (driver.findElement(By.xpath("(//*[@class='adm-list-table-header'] //*[contains(@class, 'adm-list-table-cell adm-list-table-cell-sort')])[" + i + "]"))
                    .getAttribute("title").contains(uniqueTitle)){
                countColumnWithEventStatus = i;
                break;
            }
        }
    }
    public void checkingThatThisColumnThisCompanyHaveThisData(String dataForCompliance){
        Assert.assertTrue(driver.findElement(By.xpath("((//*[@class='adm-list-table-row'])[1] //*[contains(@class, 'adm-list-table-cell')])[" +countColumnWithEventStatus+"]"))
                .getText().contains(dataForCompliance));
    }
    public void checkingThatThisColumnThisCompanyHaveThisDate(String dateForCompliance){
        if (doNeedToConfirmRegistrationOrganization){
            Assert.assertTrue(driver.findElement(By.xpath("((//*[@class='adm-list-table-row'])[1] //*[contains(@class, 'adm-list-table-cell')])[" +countColumnWithEventStatus+"]"))
                    .getText().replaceAll("[.]", "").contains(dateForCompliance));
        }
    }
    public void confirmRegistrations(){
        registr.navigationToPageForConfirmUserRegistration();
        determineWhetherRegistrationUserNeedsToBeConfirmed();
        if (doNeedToConfirmRegistrationUser){
            registr.approveTheRegistrationOfTheLastUser();
        }
        determineWhetherRegistrationOrganizationNeedsToBeConfirmed();
        if (doNeedToConfirmRegistrationOrganization){
            approveTheRegistrationOfTheLastOrganization(registr.nameCompany);
        }
    }
    public void navigationToThtListOfCompanies(){
        driver.findElement(By.xpath("//*[text()='Список компаний']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id, 'company_result')]")));
    }
    public void openTableWithSotbitAuthStaff  (){
        driver.findElement(By.xpath("//*[@data-table-name='sotbit_auth_staff']")).click();
    }
    public void openLastPageSotbitAuthStaffTable(){
        driver.findElement(By.xpath("(//*[@class='adm-nav-page'])[last()]")).click();
    }
    public void checkingThatLastCreatedUserIsManager(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='adm-list-table-row'])[last()] /*[@class='adm-list-table-cell']")));
        waitingMilliSecond();
        tempValue = driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[last()] /*[@class='adm-list-table-cell']"))
                .getText().replaceAll("[^\\d.]", "");
        tempValue = tempValue.substring(tempValue.length() - 1);
        Assert.assertEquals("1", tempValue);
    }
    public void checkingEventThatEmployeeWasRejected(){
        System.out.println("УДАЛИТЬ " + nameCompany);
        Assert.assertTrue(driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[1] //*[contains(text(), '" + nameCompany +"')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[1] //*[contains(text(), 'SOTBIT_AUTH_COMPANY_JOIN_REQUEST_REJECTED')]")).isDisplayed());
    }
    public void checkingEventThatManagerHasAddedANewEmployeeToHisCompany(){
        Assert.assertTrue(driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[1] //*[contains(text(), '" + nameCompany +"')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[1] //*[contains(text(), '" + email +"')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[1] //*[contains(text(), 'SOTBIT_AUTH_STAFF_INVITE')]")).isDisplayed());
    }
    public void checkingEventThatEmployeeHasBeenDeletedFromCompany(){
        Assert.assertTrue(driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[1] //*[contains(text(), '" + nameCompany +"')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[1] //*[contains(text(), '" + emailEmployee +"')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[1] //*[contains(text(), 'SOTBIT_AUTH_STAFF_REMOVED')]")).isDisplayed());
    }
    public void checkingEventAboutRequestToJoinTheCompanyFromAnEmployee(){
        System.out.println("Проверяю что создалось событие после запроса работника на присоединение к компании руководителя   "  + nameCompany);
        checkingThatThereIsThisEvent("SOTBIT_AUTH_COMPANY_JOIN_REQUEST", nameCompany);
    }
    public void checkingTheStatusEventAboutRequestToJoinTheCompanyFromAnEmployee(String expectedValue){
        searchForAColumnWithEventStatuses();
        System.out.println("Проверяю статус события о запроса работника на присоединение к компании руководителя (ожидаю '" +expectedValue+ "') с именем компании   "  + nameCompany);
        checkingTheStatusThisEvent(expectedValue, "SOTBIT_AUTH_COMPANY_JOIN_REQUEST", nameCompany);
    }
    public void checkingEventAboutConfirmJoiningToTheCompanyFromAnEmployee(){
        System.out.println("Проверяю что создалось событие после подтверждения руководителем запроса работника на присоединение к компании руководителя   "  + nameCompany);
        checkingThatThereIsThisEvent("SOTBIT_AUTH_STAFF_CONFIRM", nameCompany);
    }
    public void checkingStatusEventAboutConfirmJoiningToTheCompanyFromAnEmployee(String expectedValue){
        searchForAColumnWithEventStatuses();
        System.out.println("Проверяю статус события о запроса работника на присоединение к компании руководителя (ожидаю '" +expectedValue+ "') с именем компании   "  + nameCompany);
        checkingTheStatusThisEvent(expectedValue, "SOTBIT_AUTH_STAFF_CONFIRM", nameCompany);
    }
    public void checkingEventThatTheManagerHasRejectedTheJoiningOfThisEmployeeToTheCompany(){
        System.out.println("Проверяю что создалось событие после отказа руководителем на запрос работника о присоединение к компании  "  + nameCompany);
        checkingThatThereIsThisEvent("SOTBIT_AUTH_COMPANY_JOIN_REQUEST_REJECTED", nameCompany);
    }
    public void checkingTheStatusEventAboutRejectedJoiningToTheCompanyFromAnEmployee(String expectedValue){
        searchForAColumnWithEventStatuses();
        System.out.println("Проверяю статус события после отказа руководителем на запрос работника о присоединение к компании (ожидаю '" +expectedValue+ "') с именем компании   "  + nameCompany);
        checkingTheStatusThisEvent(expectedValue, "SOTBIT_AUTH_COMPANY_JOIN_REQUEST_REJECTED", nameCompany);
    }

    public void checkingEventAboutNeededToConfirmTheCompanyAfterChanging(){
        System.out.println("Проверяю что создалось событие после подтверждения руководителем запроса работника на присоединение к компании руководителя   "  + nameCompany);
        checkingThatThereIsThisEvent("SOTBIT_AUTH_COMPANY_MODERATION", nameCompany);
    }
    public void checkingStatusOfEventAboutNeededToConfirmTheCompanyAfterChanging(String expectedValue){
        searchForAColumnWithEventStatuses();
        System.out.println("Проверяю статус события о запроса работника на присоединение к компании руководителя (ожидаю '" +expectedValue+ "') с именем компании   "  + nameCompany);
        checkingTheStatusThisEvent(expectedValue, "SOTBIT_AUTH_COMPANY_MODERATION", nameCompany);
    }
    public void checkingEventAboutConfirmTheCompanyAfterChanging(){
        System.out.println("Проверяю что создалось событие после подтверждения руководителем запроса работника на присоединение к компании руководителя   "  + nameCompany);
        checkingThatThereIsThisEvent("SOTBIT_AUTH_COMPANY_CONFIRM", nameCompany);
    }
    public void checkingStatusOfEventAboutConfirmTheCompanyAfterChanging(String expectedValue){
        searchForAColumnWithEventStatuses();
        System.out.println("Проверяю статус события о запроса работника на присоединение к компании руководителя (ожидаю '" +expectedValue+ "') с именем компании   "  + nameCompany);
        checkingTheStatusThisEvent(expectedValue, "SOTBIT_AUTH_COMPANY_CONFIRM", nameCompany);
    }
    public void checkingEventAboutRejectTheCompanyByAdminAfterChanging(){
        System.out.println("Проверяю что создалось событие после отклонения изменений в организации   "  + nameCompany);
        checkingThatThereIsThisEvent("SOTBIT_AUTH_COMPANY_CHANGES_REJECTED", nameCompany);
    }
    public void checkingStatusOfEventAboutRejectTheCompanyByAdminAfterChanging(String expectedValue){
        searchForAColumnWithEventStatuses();
        System.out.println("Проверяю статус события о запроса работника на присоединение к компании руководителя (ожидаю '" +expectedValue+ "') с именем компании   "  + nameCompany);
        checkingTheStatusThisEvent(expectedValue, "SOTBIT_AUTH_COMPANY_CHANGES_REJECTED", nameCompany);
    }



}
