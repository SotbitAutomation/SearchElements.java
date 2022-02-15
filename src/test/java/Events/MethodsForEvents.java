package Events;

import OrganizationsWithExtendedVersion.MethodsForAddingOrganizationsWithExtendedVersion;
import RegistrationAndAuthorization.MethodsForRegistrationAndAuthorization;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MethodsForEvents extends MethodsForAddingOrganizationsWithExtendedVersion {

    MethodsForAddingOrganizationsWithExtendedVersion org = new MethodsForAddingOrganizationsWithExtendedVersion();
    MethodsForRegistrationAndAuthorization registr = new MethodsForRegistrationAndAuthorization();
    int countColumnWithEventStatus;
    String expectedSuccessExec = "F";
    public void checkingEventAboutNeededToConfirmUserRegistration(){
        if (doNeedToConfirmRegistrationUser){
            System.out.println("Проверяю событие о необходимости подтвержить  рег. юзера с таким email   "  + registr.email);
            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'SOTBIT_AUTH_CONFIRM_REGISTRATION')] /following::*[contains(text(), '" + registr.email + "')]"))
                    .isDisplayed());
        }else {
            System.out.println("Если подтверждать регистрацию пользователя не нужно, то никакие события не создаются");
        }
    }
    public void checkingEventAboutConfirmUserRegistration(){
        if (doNeedToConfirmRegistrationUser){
            System.out.println("Проверяю событие о том что рег. юзера с таким email   "  + registr.email + "   была подтверждена админом");
            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'SOTBIT_AUTH_ADMIN_CONFIRM')] /following::*[contains(text(), '" + registr.email + "')]"))
                    .isDisplayed());
        }else {
            System.out.println("Если подтверждать регистрацию пользователя не нужно, то никакие события о пользователе не создаются");
            }
        }

    public void checkingEventAboutTheRegistrationOrganization(){
        if (doNeedToConfirmRegistrationOrganization){
            System.out.println("Проверяю событие о необходимости подтвердить орг. с таким ИНН   "  + registr.iNNManual);
            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'SOTBIT_AUTH_COMPANY_MODERATION')] /following::*[contains(text(), '" + registr.iNNManual + "')]"))
                    .isDisplayed());
        }else {
            System.out.println("Проверяю событие что зарегистрировалась орг. с таким ИНН   "  + registr.iNNManual);
            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'SOTBIT_AUTH_COMPANY_REGISTER')] /following::*[contains(text(), '" + registr.iNNManual + "')]"))
                    .isDisplayed());
        }
    }
    public void checkingEventAboutTheRegistrationOrganizationHasBeenApproved(){
        System.out.println("Проверяю событие что орг. с таким ИНН   "  + registr.iNNManual + " была подтверждена админом");
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'SOTBIT_AUTH_COMPANY_CONFIRM')] /following::*[contains(text(), '" + registr.iNNManual + "')]"))
                .isDisplayed());
    }
    public void checkingEventAboutRejectionUsersRegistration(){
        if (doNeedToConfirmRegistrationUser){
            System.out.println("Проверяю событие что регистрация пользователя с таким емаил   "  + registr.email + " была отклонена админом");
            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'SOTBIT_AUTH_ADMIN_REJECTED')] /following::*[contains(text(), '" + registr.email + "')]"))
                    .isDisplayed());
        }else {
            System.out.println("Если подтверждать регистрацию пользователя не нужно, то никакие события о пользователе не создаются");
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
            for (int i = 1; i < 10; i++) {
                if (driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[" + i + "]")).getText().contains(registr.email)
                && driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[" + i + "]")).getText().contains("SOTBIT_AUTH_CONFIRM_REGISTRATION")){
                    count = i;
                    System.out.println("№ события о необходимости подтвердить рег. юзера -   " + count);
                    break;
                }
            }
            Assert.assertTrue(driver.findElement(By.xpath("((//*[@class='adm-list-table-row'])[" + count + "] //*[contains(@class, 'adm-list-table-cell')])[" + countColumnWithEventStatus + "]"))
                    .getText().equals("0"));
        }
    }
    public void checkingTheStatusEventOfTheUserSRegistrationHasBeenConfirmedByTheAdmin(String expectedValue){
        if (doNeedToConfirmRegistrationUser){
            System.out.println("Проверяю статус события что рег. юзера (ожидаю '" +expectedValue+ "') с таким email   "  + registr.email + " была подтверждена админом");
            for (int i = 1; i < 10; i++) {
                if (driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[" + i + "]")).getText().contains(registr.email)
                        && driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[" + i + "]")).getText().contains("SOTBIT_AUTH_ADMIN_CONFIRM")){
                    count = i;
                    System.out.println("№ события о что рег. юзера была подтверждена админом -   " + count);
                    break;
                }
            }
            Assert.assertTrue(driver.findElement(By.xpath("((//*[@class='adm-list-table-row'])[" + count + "] //*[contains(@class, 'adm-list-table-cell')])[" + countColumnWithEventStatus + "]"))
                    .getText().equals(expectedValue));
        }else {
            System.out.println("Если подтверждать регистрацию пользователя не нужно, то никакие события о пользователе не создаются");
            }
    }
    public void checkingTheStatusEventThatYouNeedToConfirmOfTheRegistrationOfTheOrganization(String expectedValue){
        if (doNeedToConfirmRegistrationOrganization){
            System.out.println("Проверяю статус события о необходимости подтвердить рег. организации (ожидаю '" +expectedValue+ "') с таким ИНН   "  + registr.iNNManual);
            for (int i = 1; i < 10; i++) {
                if ((driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[" + i + "]")).getText().contains(registr.iNNManual))
                        && (driver.findElements(By.xpath("((//*[@class='adm-list-table-row']))[" + i + "]//*[contains(text(), 'SOTBIT_AUTH_COMPANY_MODERATION')]")).size() > 0)){
                    count = i;
                    System.out.println("№ события о необходимости подтвердить рег. организации -   " + count);
                    break;
                }
            }
            Assert.assertTrue(driver.findElement(By.xpath("((//*[@class='adm-list-table-row'])[" + count + "] //*[contains(@class, 'adm-list-table-cell')])[" + countColumnWithEventStatus + "]"))
                    .getText().equals(expectedValue));
        }else {
            System.out.println("Регистрацию организации не нужно подтверждать");
        }
    }
    public void checkingTheStatusEventThatAdminRejectedUserSRegistration(String expectedValue){
        if (doNeedToConfirmRegistrationUser){
            System.out.println("Проверяю статус события об отклонении рег. пользователя (ожидаю '" +expectedValue+ "') с емаил   "  + registr.email);
            for (int i = 1; i < 10; i++) {
                if ((driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[" + i + "]")).getText().contains(registr.email))
                        && (driver.findElements(By.xpath("((//*[@class='adm-list-table-row']))[" + i + "]//*[contains(text(), 'SOTBIT_AUTH_ADMIN_REJECTED')]")).size() > 0)){
                    count = i;
                    System.out.println("№ события об отклонении рег. пользователя -   " + count);
                    break;
                }
            }
            Assert.assertTrue(driver.findElement(By.xpath("((//*[@class='adm-list-table-row'])[" + count + "] //*[contains(@class, 'adm-list-table-cell')])[" + countColumnWithEventStatus + "]"))
                    .getText().equals(expectedValue));
        }else {
            System.out.println("Если подтверждать регистрацию пользователя не нужно, то никакие события о пользователе не создаются");
        }
    }
    public void checkingStatusEventThatTheOrganizationSRegistrationHasBeenConfirmedByTheAdministrator(String expectedValue){
        System.out.println("Проверяю статус события (ожидаю '" +expectedValue+ "') что организация  с таким ИНН была подтверждена  "  + registr.iNNManual);
        for (int i = 1; i < 10; i++) {
            if (driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[" + i + "]")).getText().contains(registr.iNNManual)
                    && driver.findElements(By.xpath("((//*[@class='adm-list-table-row']))[" + i + "]//*[contains(text(), 'SOTBIT_AUTH_COMPANY_CONFIRM')]")).size() > 0
            ){
                count = i;
                System.out.println("№ события что рег. организации была подтверждена админом -   " + count);
                break;
            }
        }
        Assert.assertTrue(driver.findElement(By.xpath("((//*[@class='adm-list-table-row'])[" + count + "] //*[contains(@class, 'adm-list-table-cell')])[" + countColumnWithEventStatus + "]"))
                .getText().equals(expectedValue));
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



}
